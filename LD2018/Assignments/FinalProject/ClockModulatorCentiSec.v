`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:48:27 12/15/2018 
// Design Name: 
// Module Name:    ClockModulatorCentiSec 
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
module ClockModulatorCentiSec(
    input clk,
    output reg out
    );
	 parameter LIMIT = 19'd500000;
	 parameter ZERO = 19'd0;
	 
	 reg[18:0] clk_count = ZERO;
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