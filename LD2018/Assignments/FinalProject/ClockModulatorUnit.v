`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    22:49:17 12/16/2018 
// Design Name: 
// Module Name:    ClockModulatorUnit 
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
module ClockModulatorUnit(
	 input clk,
	 output reg out
    );
	 parameter LIMIT = 26'd50000000;
	 parameter ZERO = 26'd0;
	 
	 parameter HIGH = 1'b1;
	 parameter LOW = 1'b0;
	 
	 reg [25:0] clk_count = ZERO;
	 
	 always @ (posedge clk) 
	 begin
		if (clk_count == LIMIT - 1) clk_count = ZERO;
		else clk_count = clk_count +1;
	 end
	 
	 always @ (clk_count)
	 begin
		if (clk_count == LIMIT - 1) out = HIGH;
		else out = LOW;
	 end

endmodule
