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
	 output reg display,
    output reg [2:0] flash,
    output reg [20:0] out_time
    );
	 parameter INIT = 5'd0;
	 parameter CLOCK24 = 5'd1;
	 parameter CLOCK12 = 5'd2;
	 parameter SETCLOCK24_0 = 5'd3;
	 parameter SETCLOCK24_1 = 5'd4;
	 parameter SETCLOCK24_2 = 5'd5;
	 parameter SETCLOCK12_0 = 5'd6;
	 parameter SETCLOCK12_1 = 5'd7;
	 parameter SETCLOCK12_2 = 5'd8;
	 parameter ALARM24 = 5'd9;
	 parameter ALARM12 = 5'd10;
	 parameter SETALARM24_0 = 5'd11;
	 parameter SETALARM24_1 = 5'd12;
	 parameter SETALARM24_2 = 5'd13;
	 parameter SETALARM12_0 = 5'd14;
	 parameter SETALARM12_1 = 5'd15;
	 parameter SETALARM12_2 = 5'd16;
	 parameter STOPWATCHSTOP = 5'd17;
	 parameter STOPWATCHGO = 5'd18;
	 parameter ALARMING24 = 5'd19;
	 parameter ALARMING12 = 5'd20;
	 
	 parameter HIGH = 1'b1;
	 parameter LOW = 1'b0;
	 
	 parameter DISPLAY24 = 1'd0;
	 parameter DISPLAY12 = 1'd1;
	 
	 //Data
	 reg [4:0] state = INIT; reg[4:0] next_state;
	 reg alarm_is_set;
	 
	 //Control
	 reg clear;
	 reg stopwatch_clear;
	 reg alarm_clear;
	 reg[3:0] setting;
	 reg[3:0] alarm_setting;
	 reg count;
	 reg stopwatch_count;

	 //Auxiliary Wires
	 wire modulatedSec;
	 wire modulatedCentiSec;
	 wire modeDebounced;
	 wire setDebounced;
	 wire op1Debounced;
	 wire op2Debounced;
	 wire modePulse;
	 wire setPulse;
	 wire op1Pulse;
	 wire op2Pulse;
	 
	 ClockModulator clockModulator (
		.clk(clk),
		.out(modulatedSec)
	 );
	 	 
	 ClockModulatorCentiSec clockModulatorCentiSec (
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
	 
	 wire rco_centi;
	 wire [6:0] stopwatch_time_right;
	 BinaryUpCounter100 centiseconds (
		.clear(stopwatch_clear),
		.mode(0),
		.manual_increment(0),
		.manual_decrement(0),
		.count(stopwatch_count),
		.clk(clk),
		.ripple_carry_out(rco_centi),
		.out(stopwatch_time_right)
	 );
	 
	 wire rco_sec_stop;
	 wire [6:0] stopwatch_time_mid;
	 BinaryUpCounter stopwatch_seconds (
		.clear(stopwatch_clear),
		.mode(0),
		.manual_increment(0),
		.manual_decrement(0),
		.count(rco_centi),
		.clk(clk),
		.ripple_carry_out(rco_sec_stop),
		.out(stopwatch_time_mid)
	 );
	 
	 wire rco_min_stop;
	 wire [6:0] stopwatch_time_left;
	 BinaryUpCounter stopwatch_minutes (
		.clear(stopwatch_clear),
		.mode(0),
		.manual_increment(0),
		.manual_decrement(0),
		.count(rco_sec_stop),
		.clk(clk),
		.ripple_carry_out(rco_min_stop),
		.out(stopwatch_time_left)
	 );
	 
	 wire rco_sec;
	 wire [6:0] out_time_right;
	 BinaryUpCounter seconds (
		.clear(clear),
		.mode(setting[0]),
		.manual_increment(op1Pulse),
		.manual_decrement(op2Pulse),
		.count(count),
		.clk(clk),
		.ripple_carry_out(rco_sec),
		.out(out_time_right)
	 );
	 
	 wire rco_min;
	 wire [6:0] out_time_mid;
	 BinaryUpCounter minutes (
		.clear(clear),
		.mode(setting[1]),
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
		.mode(setting[2]),
		.ampm(setting[3]),
		.manual_increment(op1Pulse),
		.manual_decrement(op2Pulse),
		.count(rco_min),
		.clk(clk),
		.ripple_carry_out(rco_hour),
		.out(out_time_left)
	 );
	 
	 wire rco_sec_alarm;
	 wire [6:0] alarm_time_right;
	 BinaryUpCounter alarm_seconds (
		.clear(alarm_clear),
		.mode(alarm_setting[0]),
		.manual_increment(op1Pulse),
		.manual_decrement(op2Pulse),
		.count(0),
		.clk(clk),
		.ripple_carry_out(rco_sec_alarm),
		.out(alarm_time_right)
	 );
	 
	 wire rco_min_alarm;
	 wire [6:0] alarm_time_mid;
	 BinaryUpCounter alarm_minutes (
		.clear(alarm_clear),
		.mode(alarm_setting[1]),
		.manual_increment(op1Pulse),
		.manual_decrement(op2Pulse),
		.count(0),
		.clk(clk),
		.ripple_carry_out(rco_min_alarm),
		.out(alarm_time_mid)
	 );
	 
	 wire rco_hour_alarm;
	 wire [6:0] alarm_time_left;
	 BinaryUpCounter24 alarm_hours (
		.clear(alarm_clear),
		.mode(alarm_setting[2]),
		.ampm(alarm_setting[3]),
		.manual_increment(op1Pulse),
		.manual_decrement(op2Pulse),
		.count(0),
		.clk(clk),
		.ripple_carry_out(rco_hour_alarm),
		.out(alarm_time_left)
	 );
	 
	 /*
	 input clear,
	 input mode,
    input manual_increment,
	 input manual_decrement,
    input count,
    input clk,
	 output reg ripple_carry_out,
    output [5:0] out
	 */
	 
	 always @ (mode or set or reset or op1 or op2) begin
		case (state)
			INIT: 
				if (reset == HIGH) next_state = INIT;
				else next_state = CLOCK24;
			CLOCK24:
				if (reset == HIGH) next_state = INIT;
				else if (set == HIGH) next_state = SETCLOCK24_0;
				else if (mode == HIGH) next_state = ALARM24;
				else if (op1 == HIGH) next_state = CLOCK12;
				else if (alarm_is_set == HIGH 
				&& {out_time_left, out_time_mid, out_time_right} == {alarm_time_left, alarm_time_mid, alarm_time_right}) 
					next_state = ALARMING24;
				else next_state = CLOCK24;
			CLOCK12:
				if (reset == HIGH) next_state = INIT;
				else if (set == HIGH) next_state = SETCLOCK12_0;
				else if (mode == HIGH) next_state = ALARM12;
				else if (op1 == HIGH) next_state = CLOCK24;
				else if (alarm_is_set == HIGH 
				&& {out_time_left, out_time_mid, out_time_right} == {alarm_time_left, alarm_time_mid, alarm_time_right}) 
					next_state = ALARMING12;
				else next_state = CLOCK12;
			SETCLOCK24_0:
				if (reset == HIGH) next_state = INIT;
				else if (set == HIGH) next_state = SETCLOCK24_1;
				else if (mode == HIGH) next_state = ALARM24;
				else next_state = SETCLOCK24_0;
			SETCLOCK24_1:
				if (reset == HIGH) next_state = INIT;
				else if (set == HIGH) next_state = SETCLOCK24_2;
				else if (mode == HIGH) next_state = ALARM24;
				else next_state = SETCLOCK24_1;
			SETCLOCK24_2:
				if (reset == HIGH) next_state = INIT;
				else if (set == HIGH) next_state = CLOCK24;
				else if (mode == HIGH) next_state = ALARM24;
				else next_state = SETCLOCK24_2;
			SETCLOCK12_0:
				if (reset == HIGH) next_state = INIT;
				else if (set == HIGH) next_state = SETCLOCK12_1;
				else if (mode == HIGH) next_state = ALARM12;
				else next_state = SETCLOCK12_0;
			SETCLOCK12_1:
				if (reset == HIGH) next_state = INIT;
				else if (set == HIGH) next_state = SETCLOCK12_2;
				else if (mode == HIGH) next_state = ALARM12;
				else next_state = SETCLOCK12_1;
			SETCLOCK12_2:
				if (reset == HIGH) next_state = INIT;
				else if (set == HIGH) next_state = CLOCK12;
				else if (mode == HIGH) next_state = ALARM12;
				else next_state = SETCLOCK12_2;
			ALARM24:
				if (reset == HIGH) next_state = INIT;
				else if (set == HIGH) next_state = SETALARM24_0;
				else if (mode == HIGH) next_state = STOPWATCHSTOP;
				else next_state = ALARM24;
			ALARM12:
				if (reset == HIGH) next_state = INIT;
				else if (set == HIGH) next_state = SETALARM12_0;
				else if (mode == HIGH) next_state = STOPWATCHSTOP;
				else next_state = ALARM12;
			SETALARM24_0:
				if (reset == HIGH) next_state = INIT;
				else if (set == HIGH) next_state = SETALARM24_1;
				else if (mode == HIGH) next_state = STOPWATCHSTOP;
				else next_state = SETALARM24_0;
			SETALARM24_1:
				if (reset == HIGH) next_state = INIT;
				else if (set == HIGH) next_state = SETALARM24_2;
				else if (mode == HIGH) next_state = STOPWATCHSTOP;
				else next_state = SETALARM24_1;
			SETALARM24_2:
				if (reset == HIGH) next_state = INIT;
				else if (set == HIGH) 
				begin
					alarm_is_set = HIGH;
					next_state = ALARM24;
				end
				else if (mode == HIGH) next_state = STOPWATCHSTOP;
				else next_state = SETALARM24_2;
			SETALARM12_0:
				if (reset == HIGH) next_state = INIT;
				else if (set == HIGH) next_state = SETALARM12_1;
				else if (mode == HIGH) next_state = STOPWATCHSTOP;
				else next_state = SETALARM12_0;
			SETALARM12_1:
				if (reset == HIGH) next_state = INIT;
				else if (set == HIGH) next_state = SETALARM12_2;
				else if (mode == HIGH) next_state = STOPWATCHSTOP;
				else next_state = SETALARM12_1;
			SETALARM12_2:
				if (reset == HIGH) next_state = INIT;
				else if (set == HIGH) 
				begin
					alarm_is_set = HIGH;
					next_state = ALARM12;
				end
				else if (mode == HIGH) next_state = STOPWATCHSTOP;
				else next_state = SETALARM12_2;
			STOPWATCHSTOP:
				if (reset == HIGH) next_state = INIT;
				else if (mode == HIGH) next_state = CLOCK24;
				else if (setPulse == HIGH) next_state = STOPWATCHGO;
				else next_state = STOPWATCHSTOP;
			STOPWATCHGO:
				if (reset == HIGH) next_state = INIT;
				else if (mode == HIGH) next_state = CLOCK24;
				else if ({stopwatch_time_left, stopwatch_time_mid, stopwatch_time_right} == 21'b011101101110111100011)
					next_state = STOPWATCHSTOP;
				else if (setPulse == HIGH) next_state = STOPWATCHSTOP;
				else next_state = STOPWATCHGO;
			ALARMING24:
				if (reset == HIGH) next_state = INIT;
				else if (op2Pulse == HIGH) next_state = CLOCK24;
				else next_state = ALARMING24;
			ALARMING12:
				if (reset == HIGH) next_state = INIT;
				else if (op2Pulse == HIGH) next_state = CLOCK24;
				else next_state = ALARMING12;
			default: next_state = CLOCK12;
		endcase
	 end
	 
	 always @ (posedge clk) begin
		state <= next_state;
	 end
	 
	 always @ (state or op1 or op2) begin
		case (state)
			INIT: begin
				display = DISPLAY24;
				flash = 3'b000;
				out_time = 20'd0;
				clear = HIGH;
				stopwatch_clear = HIGH;
				alarm_clear = HIGH;
				setting = 4'b0000;
				alarm_setting = 4'b0000;
				count = LOW;
				stopwatch_count = LOW;
			end
			CLOCK24: begin
				display = DISPLAY24;
				flash = 3'b000;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b0000;
				alarm_setting = 4'b0000;
				count = modulatedSec;
				stopwatch_count = LOW;
				out_time = {out_time_left, out_time_mid, out_time_right};
			end
			CLOCK12: begin
				display = DISPLAY12;
				flash = 3'b000;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b0000;
				alarm_setting = 4'b0000;
				count = modulatedSec;
				stopwatch_count = LOW;
				out_time = {out_time_left, out_time_mid, out_time_right};
			end
			SETCLOCK24_0: begin
				display = DISPLAY24;
				flash = 3'b100;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b0100;
				alarm_setting = 4'b0000;
				count = LOW;
				stopwatch_count = LOW;
				out_time = {out_time_left, out_time_mid, out_time_right};
			end
			SETCLOCK24_1: begin
				display = DISPLAY24;
				flash = 3'b010;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b0010;
				alarm_setting = 4'b0000;
				count = LOW;
				stopwatch_count = LOW;
				out_time = {out_time_left, out_time_mid, out_time_right};
			end
			SETCLOCK24_2: begin
				display = DISPLAY24;
				flash = 3'b001;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b0001;
				alarm_setting = 4'b0000;
				count = LOW;
				stopwatch_count = LOW;
				out_time = {out_time_left, out_time_mid, out_time_right};
			end
			SETCLOCK12_0: begin
				display = DISPLAY12;
				flash = 3'b100;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b1000;
				alarm_setting = 4'b0000;
				count = LOW;
				stopwatch_count = LOW;
				out_time = {out_time_left, out_time_mid, out_time_right};
			end
			SETCLOCK12_1: begin
				display = DISPLAY12;
				flash = 3'b010;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b0100;
				alarm_setting = 4'b0000;
				count = LOW;
				stopwatch_count = LOW;
				out_time = {out_time_left, out_time_mid, out_time_right};
			end
			SETCLOCK12_2: begin
				display = DISPLAY12;
				flash = 3'b001;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b0010;
				alarm_setting = 4'b0000;
				count = LOW;
				stopwatch_count = LOW;
				out_time = {out_time_left, out_time_mid, out_time_right};
			end
			ALARM24: begin
				display = DISPLAY24;
				flash = 3'b000;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = op2Pulse;
				setting = 4'b0000;
				alarm_setting = 4'b0000;
				count = modulatedSec;
				stopwatch_count = LOW;
				out_time = {alarm_time_left, alarm_time_mid, alarm_time_right};
			end
			ALARM12: begin
				display = DISPLAY12;
				flash = 3'b000;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = op2Pulse;
				setting = 4'b0000;
				alarm_setting = 4'b0000;
				count = modulatedSec;
				stopwatch_count = LOW;
				out_time = {alarm_time_left, alarm_time_mid, alarm_time_right};
			end
			SETALARM24_0: begin
				display = DISPLAY24;
				flash = 3'b100;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b0000;
				alarm_setting = 4'b0100;
				count = modulatedSec;
				stopwatch_count = LOW;
				out_time = {alarm_time_left, alarm_time_mid, alarm_time_right};
			end
			SETALARM24_1: begin
				display = DISPLAY24;
				flash = 3'b010;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b0000;
				alarm_setting = 4'b0010;
				count = modulatedSec;
				stopwatch_count = LOW;
				out_time = {alarm_time_left, alarm_time_mid, alarm_time_right};
			end
			SETALARM24_2: begin
				display = DISPLAY24;
				flash = 3'b001;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b0000;
				alarm_setting = 4'b0001;
				count = modulatedSec;
				stopwatch_count = LOW;
				out_time = {alarm_time_left, alarm_time_mid, alarm_time_right};
			end
			SETALARM12_0: begin
				display = DISPLAY12;
				flash = 3'b100;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b0000;
				alarm_setting = 4'b1000;
				count = modulatedSec;
				stopwatch_count = LOW;
				out_time = {alarm_time_left, alarm_time_mid, alarm_time_right};
			end
			SETALARM12_1: begin
				display = DISPLAY12;
				flash = 3'b010;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b0000;
				alarm_setting = 4'b0100;
				count = modulatedSec;
				stopwatch_count = LOW;
				out_time = {alarm_time_left, alarm_time_mid, alarm_time_right};
			end
			SETALARM12_2: begin
				display = DISPLAY12;
				flash = 3'b001;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b0000;
				alarm_setting = 4'b0010;
				count = modulatedSec;
				stopwatch_count = LOW;
				out_time = {alarm_time_left, alarm_time_mid, alarm_time_right};
			end
			STOPWATCHSTOP: begin
				display = DISPLAY24;
				flash = 3'b000;
				clear = LOW;
				stopwatch_clear = op1Pulse;
				alarm_clear = LOW;
				setting = 4'b0000;
				alarm_setting = 4'b0000;
				count = modulatedSec;
				stopwatch_count = LOW;
				out_time = {stopwatch_time_left, stopwatch_time_mid, stopwatch_time_right};
			end
			STOPWATCHGO: begin
				display = DISPLAY24;
				flash = 3'b000;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b0000;
				alarm_setting = 4'b0000;
				count = modulatedSec;
				stopwatch_count = HIGH;
				out_time = {stopwatch_time_left, stopwatch_time_mid, stopwatch_time_right};
			end
			ALARMING24: begin
				display = DISPLAY24;
				flash = 3'b111;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b0000;
				alarm_setting = 4'b0000;
				count = modulatedSec;
				stopwatch_count = LOW;
				out_time = {alarm_time_left, alarm_time_mid, alarm_time_right};
			end
			ALARMING12: begin
				display = DISPLAY12;
				flash = 3'b111;
				clear = LOW;
				stopwatch_clear = LOW;
				alarm_clear = LOW;
				setting = 4'b0000;
				alarm_setting = 4'b0000;
				count = modulatedSec;
				stopwatch_count = LOW;
				out_time = {alarm_time_left, alarm_time_mid, alarm_time_right};
			end
		endcase
	 end
endmodule
