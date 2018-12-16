`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    23:06:13 12/16/2018 
// Design Name: 
// Module Name:    ClockModulatorTotalHalfSec 
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
module ClockModulatorTotalHalfSec(
	 input clk,
	 output out
    );
	 wire unit_out;
	 ClockModulatorUnitHalfSec unit(clk, unit_out);
	 ClockModulator modulator(unit_out, out);
endmodule