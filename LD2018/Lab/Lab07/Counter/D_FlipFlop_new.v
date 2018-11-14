`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    19:30:14 11/14/2018 
// Design Name: 
// Module Name:    D_FlipFlop_new 
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
module D_FlipFlop_new(
    input D,
    input CLK,
    output reg Q
    );
	 always @(posedge CLK)
	 begin
		Q<=D;
	end
	
endmodule
