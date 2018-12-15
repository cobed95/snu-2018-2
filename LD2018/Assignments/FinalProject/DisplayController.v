`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    18:25:04 12/15/2018 
// Design Name: 
// Module Name:    DisplayController 
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
module DisplayController(
	 input clk,
    input [2:0] flash,
    input display_mode,
    input [20:0] out_time,
    output reg [6:0] display6,
    output reg [6:0] display5,
    output reg [6:0] display4,
    output reg [6:0] display3,
    output reg [6:0] display2,
    output reg [6:0] display1
    );
	 parameter HIGH = 1'b1;
	 parameter LOW = 1'b0;
	 
	 parameter DISPLAY24 = 1'd0;
	 parameter DISPLAY12 = 1'd1;
	 
	 wire[6:0] left;
	 wire[6:0] mid;
	 wire[6:0] right;
	 
	 assign left = out_time[20:14];
	 assign mid = out_time[13:7];
	 assign right = out_time[6:0];
	 
	 wire [3:0] left_high;
	 wire [3:0] left_low;
	 DigitDecoder digitDecoderLeft (
		.in(left),
		.high(left_high),
		.low(left_low)
	 );
	 
	 wire [3:0] mid_high;
	 wire [3:0] mid_low;
	 DigitDecoder digitDecoderMid (
		.in(mid),
		.high(mid_high),
		.low(mid_low)
	 );
	 
	 wire [3:0] right_high;
	 wire [3:0] right_low;
	 DigitDecoder digitDecoderRight (
		.in(right),
		.high(right_high),
		.low(right_low)
	 );
	 
	 wire [6:0] display_A;
	 segment decoderA (
		.in(4'd10),
		.out(display_A)
	 );
	 
	 wire [6:0] display_P;
	 segment decoderB (
		.in(4'd11),
		.out(display_P)
	 );
	 
	 wire [6:0] display_off;
	 segment decoderOff (
		.in(4'd15),
		.out(display_off)
	 );
	 
	 wire [6:0] display_left_high;
	 segment decoderLeftHigh (
		.in(left_high),
		.out(display_left_high)
	 );
	 
	 wire [6:0] display_left_low;
	 segment decoderLeftLow (
		.in(left_low),
		.out(display_left_low)
	 );
	 
	 wire [6:0] display_mid_high;
	 segment decoderMidHigh (
		.in(mid_high),
		.out(display_mid_high)
	 );
	 
	 wire [6:0] display_mid_low;
	 segment decoderMidLow (
		.in(mid_low),
		.out(display_mid_low)
	 );
	 
	 wire [6:0] display_right_high;
	 segment decoderRightHigh (
		.in(right_high),
		.out(display_right_high)
	 );
	 
	 wire [6:0] display_right_low;
	 segment decoderRightLow (
		.in(right_low),
		.out(display_right_low)
	 );
	 
	 wire modulatedHalfSec;
	 ClockModulatorHalfSec halfSec (
		.clk(clk),
		.out(modulatedHalfSec)
	 );
	 
	 reg [6:0] display6_aux;
	 reg [6:0] display5_aux;
	 reg [6:0] display4_aux;
	 reg [6:0] display3_aux;
	 reg [6:0] display2_aux;
	 reg [6:0] display1_aux;
	 
	 always @ (*) begin
		if (display_mode == DISPLAY24)
		begin
			display6_aux <= display_left_high;
			display5_aux <= display_left_low;
			display4_aux <= display_mid_high;
			display3_aux <= display_mid_low;
			display2_aux <= display_right_high;
			display1_aux <= display_right_low;
		end
		else
		begin
			if (left > 7'd12) display6_aux <= display_P;
			else display6_aux <= display_A;
			display5_aux <= display_off;
			display4_aux <= display_left_high;
			display3_aux <= display_left_low;
			display2_aux <= display_mid_high;
			display1_aux <= display_mid_low;
		end
	 end
	 
	 always @ (posedge modulatedHalfSec) begin
		if (flash[2] == HIGH) 
		begin
			if (display6 == display6_aux) display6 <= display_off;
			else display6 <= display6_aux;
			if (display5 == display5_aux) display5 <= display_off;
			else display5 <= display5_aux;
		end
		else
		begin
			display6 <= display6_aux;
			display5 <= display5_aux;
		end
		if (flash[1] == HIGH)
		begin
			if (display4 == display4_aux) display4 <= display_off;
			else display4 <= display4_aux;
			if (display3 == display3_aux) display3 <= display_off;
			else display3 <= display3_aux;
		end
		else
		begin
			display4 <= display4_aux;
			display3 <= display3_aux;
		end
		if (flash[0] == HIGH)
		begin
			if (display4 == display4_aux) display4 <= display_off;
			else display4 <= display4_aux;
			if (display3 == display3_aux) display3 <= display_off;
			else display3 <= display3_aux;
		end
		else
		begin
			display2 <= display2_aux;
			display1 <= display1_aux;
		end
	 end
endmodule
