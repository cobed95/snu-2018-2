`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:17:53 12/15/2018 
// Design Name: 
// Module Name:    DigitalClock 
// Project Name: 
// Target Devices: 
// Tool versions: 
// Description: 
//
// Dependencies: 
//
// Revision: 
// Revision 0.01 - File Created
// Additional Comments: 
//
//////////////////////////////////////////////////////////////////////////////////
module DigitalClock(
	 input clk,
    input mode,
    input set,
    input op1,
    input op2,
    input reset,
	 output reg [1:0] display,
    output reg [2:0] flash,
    output reg [20:0] out_time
	 );
	 
	 // States
	 parameter INIT = 5'd0;
	 parameter CLOCK24 = 5'd1;
	 parameter CLOCK12 = 5'd2;
	 parameter SETCLOCK24_0 = 5'd3;
	 parameter SETCLOCK24_1 = 5'd4;
	 parameter SETCLOCK12_0 = 5'd5;
	 parameter SETCLOCK12_1 = 5'd6;
	 parameter SETCLOCK12_2 = 5'd7;
	 parameter ALARM24 = 5'd8;
	 parameter ALARM12 = 5'd9;
	 parameter SETALARM24_0 = 5'd10;
	 parameter SETALARM24_1 = 5'd11;
	 parameter SETALARM12_0 = 5'd12;
	 parameter SETALARM12_1 = 5'd13;
	 parameter SETALARM12_2 = 5'd14;
	 parameter STOPWATCHSTOP = 5'd15;
	 parameter STOPWATCHGO = 5'd16;
	 parameter ALARMING24 = 5'd17;
	 parameter ALARMING12 = 5'd18;
	 
	 // Values
	 parameter HIGH = 1'b1;
	 parameter LOW = 1'b0;
	 
	 // Display Modes
	 parameter DISPLAY24 = 2'd0;
	 parameter DISPLAY12 = 2'd1;
	 parameter DISPLAY60 = 2'd2;
	 
	 // Stopwatch States
	 parameter IDLE = 1'b0;
	 parameter COUNTING = 1'b1;
	 
	 // Was Setting
	 parameter AMPM = 3'b100;
	 parameter HOURS = 3'b010;
	 parameter MINUTES = 3'b001;
	 parameter NOTHING = 3'b000;
	 
	 //Data
	 reg [4:0] state = INIT; reg[4:0] next_state;
	 reg modulatedSecReg;
	 reg modulatedCentiSecReg;
	 reg alarm_is_set = LOW;
	 reg [20:0] stopwatch_time_data;
	 reg [20:0] out_time_data;
	 reg [20:0] alarm_time_data = 21'b111111111111111111111;
	 reg [2:0] was_setting = NOTHING;
	 reg [2:0] was_setting_alarm = NOTHING;
	 reg [1:0] was_in_display_mode = DISPLAY24;
	 reg [1:0] was_in_alarm_display_mode = DISPLAY24;
	 reg stopwatch_was = IDLE;
	 
	 //Control
	 reg clear;
	 reg seconds_clear;
	 reg stopwatch_clear;
	 reg alarm_clear;
	 reg alarm_seconds_clear;
	 reg [2:0] setting;
	 reg [2:0] alarm_setting;
	 reg count;
	 reg stopwatch_count;

	 //Auxiliary Wires
	 wire modulatedSec;
	 wire modulatedCentiSec;
	 wire modeDebounced;
	 wire setDebounced;
	 wire op1Debounced;
	 wire op2Debounced;
	 wire resetDebounced;
	 wire modePulse;
	 wire setPulse;
	 wire op1Pulse;
	 wire op2Pulse;
	 wire resetPulse;
	 
	 //reg modulatedSecReg;
	 ClockModulatorUnit clockModulator (
		.clk(clk),
		.out(modulatedSec)
	 );
	 	 
	 //reg modulatedCentiSecReg;
	 ClockModulatorUnitCentiSec clockModulatorCentiSec (
		.clk(clk),
		.out(modulatedCentiSec)
	 );
	 
	 Debouncer debouncerMode (
		.clk(clk),
		.data_in(mode),
		.data_out(modeDebounced)
	 );
	 
	 Debouncer debouncerSet (
		.clk(clk),
		.data_in(set),
		.data_out(setDebounced)
	 );
	 
	 Debouncer debouncerOp1 (
		.clk(clk),
		.data_in(op1),
		.data_out(op1Debounced)
	 );
	 
	 Debouncer debouncerOp2 (
		.clk(clk),
		.data_in(op2),
		.data_out(op2Debounced)
	 );
	 
	 Debouncer debouncerReset (
		.clk(clk),
		.data_in(reset),
		.data_out(resetDebounced)
	 );

	 PulseGenerator pulseGeneratorMode (
		.clk(clk),
		.input_signal(modeDebounced),
		.reset(reset),
		.output_signal(modePulse)
	 );
	 
	 PulseGenerator pulseGeneratorSet (
		.clk(clk),
		.input_signal(setDebounced),
		.reset(rest),
		.output_signal(setPulse)
	 );
	 
	 PulseGenerator pulseGeneratorOp1 (
		.clk(clk),
		.input_signal(op1Debounced),
		.reset(reset),
		.output_signal(op1Pulse)
	 );
	 
	 PulseGenerator pulseGeneratorOp2 (
		.clk(clk),
		.input_signal(op2Debounced),
		.reset(reset),
		.output_signal(op2Pulse)
	 );
	 
	 PulseGenerator pulseGeneratorReset (
		.clk(clk),
		.input_signal(reset),
		.reset(reset),
		.output_signal(resetPulse)
	 );
	 
	 wire rco_centi;
	 wire [6:0] stopwatch_time_right;
	 BinaryUpCounter100 centiseconds (
		.clear(stopwatch_clear),
		.mode(1'b0),
		.manual_increment(1'b0),
		.manual_decrement(1'b0),
		.count(stopwatch_count),
		.clk(clk),
		.ripple_carry_out(rco_centi),
		.out(stopwatch_time_right)
	 );
	 
	 wire rco_sec_stop;
	 wire [6:0] stopwatch_time_mid;
	 BinaryUpCounter stopwatch_seconds (
		.clear(stopwatch_clear),
		.mode(1'b0),
		.manual_increment(1'b0),
		.manual_decrement(1'b0),
		.count(rco_centi),
		.clk(clk),
		.ripple_carry_out(rco_sec_stop),
		.out(stopwatch_time_mid)
	 );
	 
	 wire rco_min_stop;
	 wire [6:0] stopwatch_time_left;
	 BinaryUpCounter stopwatch_minutes (
		.clear(stopwatch_clear),
		.mode(1'b0),
		.manual_increment(1'b0),
		.manual_decrement(1'b0),
		.count(rco_sec_stop),
		.clk(clk),
		.ripple_carry_out(rco_min_stop),
		.out(stopwatch_time_left)
	 );
	 
	 wire rco_sec;
	 wire [6:0] out_time_right;
	 BinaryUpCounter seconds (
		.clear(seconds_clear),
		.mode(1'b0),
		.manual_increment(1'b0),
		.manual_decrement(1'b0),
		.count(count),
		.clk(clk),
		.ripple_carry_out(rco_sec),
		.out(out_time_right)
	 );
	 
	 wire rco_min;
	 wire [6:0] out_time_mid;
	 BinaryUpCounter minutes (
		.clear(clear),
		.mode(setting[0]),
		.manual_increment(op1Pulse),
		.manual_decrement(op2Pulse),
		.count(rco_sec),
		.clk(clk),
		.ripple_carry_out(rco_min),
		.out(out_time_mid)
	 );
	 
	 wire rco_hour;
	 wire [6:0] out_time_left;
	 BinaryUpCounter24 hours (
		.clear(clear),
		.mode(setting[1]),
		.ampm(setting[2]),
		.manual_increment(op1Pulse),
		.manual_decrement(op2Pulse),
		.count(rco_min),
		.clk(clk),
		.ripple_carry_out(rco_hour),
		.out(out_time_left)
	 );
	 
	 wire [6:0] alarm_time_right;
	 AlarmSecondsRegister alarm_seconds (
		.clk(clk),
		.clear(alarm_seconds_clear),
		.out(alarm_time_right)
	 );
	 
	 wire rco_min_alarm;
	 wire [6:0] alarm_time_mid;
	 AlarmCounter alarm_minutes ( // Go from here.
		.clear(alarm_clear),
		.mode(alarm_setting[0]),
		.manual_increment(op1Pulse),
		.manual_decrement(op2Pulse),
		.count(1'b0),
		.clk(clk),
		.ripple_carry_out(rco_min_alarm),
		.out(alarm_time_mid)
	 );
	 
	 wire rco_hour_alarm;
	 wire [6:0] alarm_time_left;
	 AlarmCounter24 alarm_hours (
		.clear(alarm_clear),
		.mode(alarm_setting[1]),
		.ampm(alarm_setting[2]),
		.manual_increment(op1Pulse),
		.manual_decrement(op2Pulse),
		.count(1'b0),
		.clk(clk),
		.ripple_carry_out(rco_hour_alarm),
		.out(alarm_time_left)
	 );
	 
	 always @ (reset or modePulse or setPulse or op1Pulse or op2Pulse or stopwatch_time_data or out_time_data or alarm_time_data or alarm_is_set) begin
		if (reset == HIGH) next_state = INIT;
		else
		begin
		case (state)
			INIT: 
				next_state = CLOCK24;
			CLOCK24:
			begin
				//was_setting = NOTHING;
				//was_in_display_mode = DISPLAY24;
				if (setPulse == HIGH) next_state = SETCLOCK24_0;
				else if (modePulse == HIGH) 
				begin
					if (was_in_alarm_display_mode == DISPLAY24)
					begin
						if (was_setting_alarm == NOTHING) next_state = ALARM24;
						else if (was_setting_alarm == AMPM || was_setting == HOURS) next_state = SETALARM24_0;
						else if (was_setting_alarm == MINUTES) next_state = SETALARM24_1;
					end
					else if (was_in_alarm_display_mode == DISPLAY12)
					begin
						if (was_setting_alarm == NOTHING) next_state = ALARM24;
						else if (was_setting_alarm == AMPM) next_state = SETALARM12_0;
						else if (was_setting_alarm == HOURS) next_state = SETALARM12_1;
						else if (was_setting_alarm == MINUTES) next_state = SETALARM12_2;
					end
				end
				else if (op1Pulse == HIGH) next_state = CLOCK12;
				else if (alarm_is_set == HIGH && out_time_data[20:7] == alarm_time_data[20:7]) 
					next_state = ALARMING24;
				else next_state = CLOCK24;
			end
			CLOCK12:
			begin
				//was_setting = NOTHING;
				//was_in_display_mode = DISPLAY12;
				if (setPulse == HIGH) next_state = SETCLOCK12_0;
				else if (modePulse == HIGH) 
				begin
					if (was_in_alarm_display_mode == DISPLAY24)
					begin
						if (was_setting_alarm == NOTHING) next_state = ALARM12;
						else if (was_setting_alarm == AMPM || was_setting == HOURS) next_state = SETALARM24_0;
						else if (was_setting_alarm == MINUTES) next_state = SETALARM24_1;
					end
					else if (was_in_alarm_display_mode == DISPLAY12)
					begin
						if (was_setting_alarm == NOTHING) next_state = ALARM12;
						else if (was_setting_alarm == AMPM) next_state = SETALARM12_0;
						else if (was_setting_alarm == HOURS) next_state = SETALARM12_1;
						else if (was_setting_alarm == MINUTES) next_state = SETALARM12_2;
					end
				end
				else if (op1Pulse == HIGH) next_state = CLOCK24;
				else if (alarm_is_set == HIGH && out_time_data[20:7] == alarm_time_data[20:7]) 
					next_state = ALARMING12;
				else next_state = CLOCK12;
			end
			SETCLOCK24_0:
			begin
				//was_setting = HOURS;
				//was_in_display_mode = DISPLAY24;
				if (setPulse == HIGH) next_state = SETCLOCK24_1;
				else if (modePulse == HIGH) 
				begin
					if (was_in_alarm_display_mode == DISPLAY24)
					begin
						if (was_setting_alarm == NOTHING) next_state = ALARM24;
						else if (was_setting_alarm == AMPM || was_setting == HOURS) next_state = SETALARM24_0;
						else if (was_setting_alarm == MINUTES) next_state = SETALARM24_1;
					end
					else if (was_in_alarm_display_mode == DISPLAY12)
					begin
						if (was_setting_alarm == NOTHING) next_state = ALARM24;
						else if (was_setting_alarm == AMPM) next_state = SETALARM12_0;
						else if (was_setting_alarm == HOURS) next_state = SETALARM12_1;
						else if (was_setting_alarm == MINUTES) next_state = SETALARM12_2;
					end
				end
				else next_state = SETCLOCK24_0;
			end
			SETCLOCK24_1:
			begin
				//was_setting = MINUTES;				
				//was_in_display_mode = DISPLAY24;
				if (setPulse == HIGH) next_state = CLOCK24;
				else if (modePulse == HIGH) 
				begin
					if (was_in_alarm_display_mode == DISPLAY24)
					begin
						if (was_setting_alarm == NOTHING) next_state = ALARM24;
						else if (was_setting_alarm == AMPM || was_setting == HOURS) next_state = SETALARM24_0;
						else if (was_setting_alarm == MINUTES) next_state = SETALARM24_1;
					end
					else if (was_in_alarm_display_mode == DISPLAY12)
					begin
						if (was_setting_alarm == NOTHING) next_state = ALARM24;
						else if (was_setting_alarm == AMPM) next_state = SETALARM12_0;
						else if (was_setting_alarm == HOURS) next_state = SETALARM12_1;
						else if (was_setting_alarm == MINUTES) next_state = SETALARM12_2;
					end
				end
				else next_state = SETCLOCK24_1;
			end
			SETCLOCK12_0:
			begin
				//was_setting = AMPM;
				//was_in_display_mode = DISPLAY12;
				if (setPulse == HIGH) next_state = SETCLOCK12_1;
				else if (modePulse == HIGH) 
				begin
					if (was_in_alarm_display_mode == DISPLAY24)
					begin
						if (was_setting_alarm == NOTHING) next_state = ALARM12;
						else if (was_setting_alarm == AMPM || was_setting == HOURS) next_state = SETALARM24_0;
						else if (was_setting_alarm == MINUTES) next_state = SETALARM24_1;
					end
					else if (was_in_alarm_display_mode == DISPLAY12)
					begin
						if (was_setting_alarm == NOTHING) next_state = ALARM12;
						else if (was_setting_alarm == AMPM) next_state = SETALARM12_0;
						else if (was_setting_alarm == HOURS) next_state = SETALARM12_1;
						else if (was_setting_alarm == MINUTES) next_state = SETALARM12_2;
					end
				end
				else next_state = SETCLOCK12_0;
			end
			SETCLOCK12_1:
			begin
				//was_setting = HOURS;
				//was_in_display_mode = DISPLAY12;
				if (setPulse == HIGH) next_state = SETCLOCK12_2;
				else if (modePulse == HIGH) 
				begin
					if (was_in_alarm_display_mode == DISPLAY24)
					begin
						if (was_setting_alarm == NOTHING) next_state = ALARM12;
						else if (was_setting_alarm == AMPM || was_setting == HOURS) next_state = SETALARM24_0;
						else if (was_setting_alarm == MINUTES) next_state = SETALARM24_1;
					end
					else if (was_in_alarm_display_mode == DISPLAY12)
					begin
						if (was_setting_alarm == NOTHING) next_state = ALARM12;
						else if (was_setting_alarm == AMPM) next_state = SETALARM12_0;
						else if (was_setting_alarm == HOURS) next_state = SETALARM12_1;
						else if (was_setting_alarm == MINUTES) next_state = SETALARM12_2;
					end
				end
				else next_state = SETCLOCK12_1;
			end
			SETCLOCK12_2:
			begin
				//was_setting = MINUTES;
				//was_in_display_mode = DISPLAY12;
				if (setPulse == HIGH) next_state = CLOCK12;
				else if (modePulse == HIGH) 
				begin
					if (was_in_alarm_display_mode == DISPLAY24)
					begin
						if (was_setting_alarm == NOTHING) next_state = ALARM12;
						else if (was_setting_alarm == AMPM || was_setting == HOURS) next_state = SETALARM24_0;
						else if (was_setting_alarm == MINUTES) next_state = SETALARM24_1;
					end
					else if (was_in_alarm_display_mode == DISPLAY12)
					begin
						if (was_setting_alarm == NOTHING) next_state = ALARM12;
						else if (was_setting_alarm == AMPM) next_state = SETALARM12_0;
						else if (was_setting_alarm == HOURS) next_state = SETALARM12_1;
						else if (was_setting_alarm == MINUTES) next_state = SETALARM12_2;
					end
				end
				else next_state = SETCLOCK12_2;
			end
			ALARM24:
			begin
				//was_setting_alarm = NOTHING;
				if (setPulse == HIGH) next_state = SETALARM24_0;
				else if (modePulse == HIGH) 
				begin
					if (stopwatch_was == IDLE) next_state = STOPWATCHSTOP;
					else if (stopwatch_was == COUNTING) next_state = STOPWATCHGO;
				end
				else if (op1Pulse == HIGH) next_state = ALARM12;
				else if (op2Pulse == HIGH) alarm_is_set = LOW;
				else next_state = ALARM24;
			end
			ALARM12:
			begin
				//was_setting_alarm = NOTHING;
				if (setPulse == HIGH) next_state = SETALARM12_0;
				else if (modePulse == HIGH) 
				begin
					if (stopwatch_was == IDLE) next_state = STOPWATCHSTOP;
					else if (stopwatch_was == COUNTING) next_state = STOPWATCHGO;
				end
				else if (op1Pulse == HIGH) next_state = ALARM24;
				else if (op2Pulse == HIGH) alarm_is_set = LOW;
				else next_state = ALARM12;
			end
			SETALARM24_0:
			begin
				//was_setting_alarm = HOURS;
				//was_in_alarm_display_mode = DISPLAY24;
				if (setPulse == HIGH) next_state = SETALARM24_1;
				else if (modePulse == HIGH) 
				begin
					if (stopwatch_was == IDLE) next_state = STOPWATCHSTOP;
					else if (stopwatch_was == COUNTING) next_state = STOPWATCHGO;
				end
				else next_state = SETALARM24_0;
			end
			SETALARM24_1:
			begin
				//was_setting_alarm = MINUTES;
				//was_in_alarm_display_mode = DISPLAY24;
				if (setPulse == HIGH) 
				begin
					alarm_is_set = HIGH;
					next_state = ALARM24;
				end
				else if (modePulse == HIGH) 
				begin
					if (stopwatch_was == IDLE) next_state = STOPWATCHSTOP;
					else if (stopwatch_was == COUNTING) next_state = STOPWATCHGO;
				end
				else next_state = SETALARM24_1;
			end
			SETALARM12_0:
			begin
				//was_setting_alarm = AMPM;
				//was_in_alarm_display_mode = DISPLAY12;
				if (setPulse == HIGH) next_state = SETALARM12_1;
				else if (modePulse == HIGH) 
				begin
					if (stopwatch_was == IDLE) next_state = STOPWATCHSTOP;
					else if (stopwatch_was == COUNTING) next_state = STOPWATCHGO;
				end
				else next_state = SETALARM12_0;
			end
			SETALARM12_1:
			begin
				//was_setting_alarm = HOURS;
				//was_in_alarm_display_mode = DISPLAY12;
				if (setPulse == HIGH) next_state = SETALARM12_2;
				else if (modePulse == HIGH) 
				begin
					if (stopwatch_was == IDLE) next_state = STOPWATCHSTOP;
					else if (stopwatch_was == COUNTING) next_state = STOPWATCHGO;
				end
				else next_state = SETALARM12_1;
			end
			SETALARM12_2:
			begin
				//was_setting_alarm = MINUTES;
				//was_in_alarm_display_mode = DISPLAY12;
				if (setPulse == HIGH) 
				begin
					alarm_is_set = HIGH;
					next_state = ALARM12;
				end
				else if (modePulse == HIGH) 
				begin
					if (stopwatch_was == IDLE) next_state = STOPWATCHSTOP;
					else if (stopwatch_was == COUNTING) next_state = STOPWATCHGO;
				end
				else next_state = SETALARM12_2;
			end
			STOPWATCHSTOP:
				if (modePulse == HIGH) 
				begin
					//stopwatch_was = IDLE;
					if (was_in_display_mode == DISPLAY24)
					begin
						if (was_setting == NOTHING) next_state = CLOCK24;
						else if (was_setting == AMPM || was_setting == HOURS) next_state = SETCLOCK24_0;
						else if (was_setting == MINUTES) next_state = SETCLOCK24_1;
					end
					else if (was_in_display_mode == DISPLAY12)
					begin
						if (was_setting == NOTHING) next_state = CLOCK12;
						else if (was_setting == AMPM) next_state = SETCLOCK12_0;
						else if (was_setting == HOURS) next_state = SETCLOCK12_1;
						else if (was_setting == MINUTES) next_state = SETCLOCK12_2;
					end
				end
				else if (setPulse == HIGH) next_state = STOPWATCHGO;
				else next_state = STOPWATCHSTOP;
			STOPWATCHGO:
				if (modePulse == HIGH) 
				begin
					//stopwatch_was = COUNTING;
					if (was_in_display_mode == DISPLAY24)
					begin
						if (was_setting == NOTHING) next_state = CLOCK24;
						else if (was_setting == AMPM || was_setting == HOURS) next_state = SETCLOCK24_0;
						else if (was_setting == MINUTES) next_state = SETCLOCK24_1;
					end
					else if (was_in_display_mode == DISPLAY12)
					begin
						if (was_setting == NOTHING) next_state = CLOCK12;
						else if (was_setting == AMPM) next_state = SETCLOCK12_0;
						else if (was_setting == HOURS) next_state = SETCLOCK12_1;
						else if (was_setting == MINUTES) next_state = SETCLOCK12_2;
					end
				end
				else if (stopwatch_time_data == 21'b011101101110111100011)
					next_state = STOPWATCHSTOP;
				else if (setPulse == HIGH) next_state = STOPWATCHSTOP;
				else next_state = STOPWATCHGO;
			ALARMING24:
				if (op2Pulse == HIGH) 
				begin
					alarm_is_set = LOW;
					next_state = CLOCK24;
				end
				else next_state = ALARMING24;
			ALARMING12:
				if (op2Pulse == HIGH) next_state = CLOCK24;
				else next_state = ALARMING12;
			default: next_state = CLOCK12;
		endcase
		end
	 end
	 
	 always @ (posedge clk) begin
		state <= next_state;
		modulatedSecReg <= modulatedSec;
		modulatedCentiSecReg <= modulatedCentiSec;
		out_time_data <= {out_time_left, out_time_mid, out_time_right};
		stopwatch_time_data <= {stopwatch_time_left, stopwatch_time_mid, stopwatch_time_right};
		alarm_time_data <= {alarm_time_left, alarm_time_mid, alarm_time_right};
	 end
	 
	 always @ (posedge clk) begin
		case (state)
			INIT: begin
				display = DISPLAY24;
				flash = 3'b000;
				out_time = 20'd0;
				clear = HIGH;
				seconds_clear = HIGH;
				stopwatch_clear = HIGH;
				alarm_clear = HIGH;
				alarm_seconds_clear = HIGH;
				setting = 3'b000;
				alarm_setting = 3'b000;
				count = LOW;
				stopwatch_count = LOW;
				was_setting = NOTHING;
				was_setting_alarm = NOTHING;
				was_in_display_mode = DISPLAY24;
				was_in_alarm_display_mode = DISPLAY24;
				stopwatch_was = IDLE;
			end
			CLOCK24: begin
				display = DISPLAY24;
				flash = 3'b000;
				clear = LOW;
				seconds_clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				alarm_seconds_clear = LOW;
				setting = 3'b000;
				alarm_setting = 3'b000;
				count = modulatedSecReg;
				stopwatch_count = LOW;
				out_time = out_time_data;
				was_setting = NOTHING;
				was_in_display_mode = DISPLAY24;
			end
			CLOCK12: begin
				display = DISPLAY12;
				flash = 3'b000;
				clear = LOW;
				seconds_clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				alarm_seconds_clear = LOW;
				setting = 3'b000;
				alarm_setting = 3'b000;
				count = modulatedSecReg;
				stopwatch_count = LOW;
				out_time = out_time_data;
				was_setting = NOTHING;
				was_in_display_mode = DISPLAY24;
			end
			SETCLOCK24_0: begin
				display = DISPLAY24;
				flash = 3'b100;
				clear = LOW;
				seconds_clear = HIGH;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				alarm_seconds_clear = LOW;
				setting = 3'b010;
				alarm_setting = 3'b000;
				count = LOW;
				stopwatch_count = LOW;
				out_time = out_time_data;
				was_setting = HOURS;
				was_in_display_mode = DISPLAY24;
			end
			SETCLOCK24_1: begin
				display = DISPLAY24;
				flash = 3'b010;
				clear = LOW;
				seconds_clear = HIGH;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				alarm_seconds_clear = LOW;
				setting = 3'b001;
				alarm_setting = 3'b000;
				count = LOW;
				stopwatch_count = LOW;
				out_time = out_time_data;
				was_setting = MINUTES;
				was_in_display_mode = DISPLAY24;
			end
			SETCLOCK12_0: begin
				display = DISPLAY12;
				flash = 3'b100;
				clear = LOW;
				seconds_clear = HIGH;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				alarm_seconds_clear = LOW;
				setting = 3'b100;
				alarm_setting = 3'b000;
				count = LOW;
				stopwatch_count = LOW;
				out_time = out_time_data;
				was_setting = AMPM;
				was_in_display_mode = DISPLAY12;
			end
			SETCLOCK12_1: begin
				display = DISPLAY12;
				flash = 3'b010;
				clear = LOW;
				seconds_clear = HIGH;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				alarm_seconds_clear = LOW;
				setting = 3'b010;
				alarm_setting = 3'b000;
				count = LOW;
				stopwatch_count = LOW;
				out_time = out_time_data;
				was_setting = HOURS;
				was_in_display_mode = DISPLAY12;
			end
			SETCLOCK12_2: begin
				display = DISPLAY12;
				flash = 3'b001;
				clear = LOW;
				seconds_clear = HIGH;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				alarm_seconds_clear = LOW;
				setting = 3'b001;
				alarm_setting = 3'b000;
				count = LOW;
				stopwatch_count = LOW;
				out_time = out_time_data;
				was_setting = MINUTES;
				was_in_display_mode = DISPLAY12;
			end
			ALARM24: begin
				display = DISPLAY24;
				flash = 3'b000;
				clear = LOW;
				seconds_clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = !alarm_is_set;
				alarm_seconds_clear = !alarm_is_set;
				setting = 3'b000;
				alarm_setting = 3'b000;
				if (was_setting == NOTHING)
					count = modulatedSecReg;
				else count = LOW;
				stopwatch_count = LOW;
				out_time = alarm_time_data;
				was_setting_alarm = NOTHING;
				was_in_alarm_display_mode = DISPLAY24;
			end
			ALARM12: begin
				display = DISPLAY12;
				flash = 3'b000;
				clear = LOW;
				seconds_clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = !alarm_is_set;
				alarm_seconds_clear = !alarm_is_set;
				setting = 3'b000;
				alarm_setting = 3'b000;
				if (was_setting == NOTHING)
					count = modulatedSecReg;
				else count = LOW;
				stopwatch_count = LOW;
				out_time = alarm_time_data;
				was_setting_alarm = NOTHING;
				was_in_alarm_display_mode = DISPLAY12;
			end
			SETALARM24_0: begin
				display = DISPLAY24;
				flash = 3'b100;
				clear = LOW;
				seconds_clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				alarm_seconds_clear = HIGH;
				setting = 3'b000;
				alarm_setting = 3'b010;
				if (was_setting == NOTHING)
					count = modulatedSecReg;
				else count = LOW;
				stopwatch_count = LOW;
				out_time = alarm_time_data;
				was_setting_alarm = HOURS;
				was_in_alarm_display_mode = DISPLAY12;
			end
			SETALARM24_1: begin
				display = DISPLAY24;
				flash = 3'b010;
				clear = LOW;
				seconds_clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				alarm_seconds_clear = HIGH;
				setting = 3'b000;
				alarm_setting = 3'b001;
				if (was_setting == NOTHING)
					count = modulatedSecReg;
				else count = LOW;
				stopwatch_count = LOW;
				out_time = alarm_time_data;
				was_setting_alarm = MINUTES;
				was_in_alarm_display_mode = DISPLAY24;
			end
			SETALARM12_0: begin
				display = DISPLAY12;
				flash = 3'b100;
				clear = LOW;
				seconds_clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				alarm_seconds_clear = HIGH;
				setting = 3'b000;
				alarm_setting = 3'b100;
				if (was_setting == NOTHING)
					count = modulatedSecReg;
				else count = LOW;
				stopwatch_count = LOW;
				out_time = alarm_time_data;
				was_setting_alarm = AMPM;
				was_in_alarm_display_mode = DISPLAY12;
			end
			SETALARM12_1: begin
				display = DISPLAY12;
				flash = 3'b010;
				clear = LOW;
				seconds_clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				alarm_seconds_clear = HIGH;
				setting = 3'b000;
				alarm_setting = 3'b010;
				if (was_setting == NOTHING)
					count = modulatedSecReg;
				else count = LOW;
				stopwatch_count = LOW;
				out_time = alarm_time_data;
				was_setting_alarm = HOURS;
				was_in_alarm_display_mode = DISPLAY12;
			end
			SETALARM12_2: begin
				display = DISPLAY12;
				flash = 3'b001;
				clear = LOW;
				seconds_clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				alarm_seconds_clear = HIGH;
				setting = 3'b000;
				alarm_setting = 3'b001;
				if (was_setting == NOTHING)
					count = modulatedSecReg;
				else count = LOW;
				stopwatch_count = LOW;
				out_time = alarm_time_data;
				was_setting_alarm = MINUTES;
				was_in_alarm_display_mode = DISPLAY12;
			end
			STOPWATCHSTOP: begin
				display = DISPLAY60;
				flash = 3'b000;
				clear = LOW;
				seconds_clear = LOW;
				stopwatch_clear = op1Pulse;
				alarm_clear = LOW;
				alarm_seconds_clear = LOW;
				setting = 3'b000;
				alarm_setting = 3'b000;
				if (was_setting == NOTHING)
					count = modulatedSecReg;
				else count = LOW;
				stopwatch_count = LOW;
				out_time = stopwatch_time_data;
				stopwatch_was = IDLE;
			end
			STOPWATCHGO: begin
				display = DISPLAY60;
				flash = 3'b000;
				clear = LOW;
				seconds_clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				alarm_seconds_clear = LOW;
				setting = 3'b000;
				alarm_setting = 3'b000;
				if (was_setting == NOTHING)
					count = modulatedSecReg;
				else count = LOW;
				stopwatch_count = modulatedCentiSecReg;
				out_time = stopwatch_time_data;
				stopwatch_was = COUNTING;
			end
			ALARMING24: begin
				display = DISPLAY24;
				flash = 3'b111;
				clear = LOW;
				seconds_clear = LOW;
				stopwatch_clear = LOW; 
				alarm_clear = HIGH;
				alarm_seconds_clear = HIGH;
				setting = 3'b000;
				alarm_setting = 3'b000;
				if (was_setting == NOTHING)
					count = modulatedSecReg;
				else count = LOW;
				stopwatch_count = LOW;
				out_time = out_time_data;
			end
			ALARMING12: begin
				display = DISPLAY12;
				flash = 3'b111;
				clear = LOW;
				seconds_clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = HIGH;
				setting = 3'b000;
				alarm_setting = 3'b000;
				if (was_setting == NOTHING)
					count = modulatedSecReg;
				else count = LOW;
				stopwatch_count = LOW;
				out_time = out_time_data;
			end
		endcase
	 end
endmodule
