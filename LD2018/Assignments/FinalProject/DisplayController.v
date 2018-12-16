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
    output [6:0] display6,
    output [6:0] display5,
    output [6:0] display4,
    output [6:0] display3,
    output [6:0] display2,
    output [6:0] display1
    );
	 parameter HIGH = 1'b1;
	 parameter LOW = 1'b0;
	 
	 parameter LEFT = 2'd2;
	 parameter MID = 2'd1;
	 parameter RIGHT = 2'd0;
	 
	 wire [3:0] left_high;
	 wire [3:0] left_low;
	 DisplayValueGenerator genLeft(clk, display_mode, out_time, LEFT, left_high, left_low);
	 
	 wire [3:0] mid_high;
	 wire [3:0] mid_low;
	 DisplayValueGenerator genMid(clk, display_mode, out_time, MID, mid_high, mid_low);
	 
	 wire [3:0] right_high;
	 wire [3:0] right_low;
	 DisplayValueGenerator genRight(clk, display_mode, out_time, RIGHT, right_high, right_low);
	 
	 wire modulatedHalfSec;
	 ClockModulatorTotalHalfSec halfSec(clk, modulatedHalfSec);
	 
	 DisplayControllerUnit leftUnit(clk, modulatedHalfSec, flash[2], left_high, left_low, display6, display5);
	 DisplayControllerUnit midUnit(clk, modulatedHalfSec, flash[1], mid_high, mid_low, display4, display3);
	 DisplayControllerUnit rightUnit(clk, modulatedHalfSec, flash[0], right_high, right_low, display2, display1);
endmodule
