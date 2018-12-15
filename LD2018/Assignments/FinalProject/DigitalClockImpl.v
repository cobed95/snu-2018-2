`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    19:13:17 12/15/2018 
// Design Name: 
// Module Name:    DigitalClockImpl 
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
module DigitalClockImpl(
	 input clk,
    input mode,
    input set,
    input op1,
    input op2,
    input reset,
	 output [6:0] display6,
    output [6:0] display5,
    output [6:0] display4,
    output [6:0] display3,
    output [6:0] display2,
    output [6:0] display1
    );
	 wire display_mode;
    wire [2:0] flash;
    wire [20:0] out_time;
	 
	 DigitalClock digitalClock(clk, mode, set, op1, op2, reset, display_mode, flash, out_time);
	 DisplayController displayController(clk, flash, display_mode, out_time, display6, display5, display4, display3, display2, display1);
endmodule
