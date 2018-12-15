`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    19:20:26 12/15/2018 
// Design Name: 
// Module Name:    ClockModulatorHalfSec 
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
module ClockModulatorHalfSec(
    input clk,
    output reg out
    );
	 parameter LIMIT = 26'd10;
	 parameter ZERO = 26'd0;
	 
	 reg[25:0] clk_count = ZERO;
	 always @ (posedge clk)
	 begin
		if (clk_count == LIMIT)
		begin
			out = 1;
			clk_count = ZERO;
		end
		else clk_count = clk_count + 1;
	 end
endmodule
