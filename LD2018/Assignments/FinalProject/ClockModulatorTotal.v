`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    22:59:13 12/16/2018 
// Design Name: 
// Module Name:    ClockModulatorTotal 
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
module ClockModulatorTotal(
	 input clk,
	 output out
    );
	 wire unit_out;
	 ClockModulatorUnit unit(clk, unit_out);
	 ClockModulator modulator(unit_out, out);
endmodule
