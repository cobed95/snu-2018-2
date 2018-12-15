`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    15:51:41 11/27/2018 
// Design Name: 
// Module Name:    Mux 
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
module Mux(
    input [3:0] a,
    input [3:0] b,
    input sel,
    output reg [3:0] out
    );

always @ (*) 
begin
	if (sel == 1'b1) out = b;
	else out = a;
end

endmodule
