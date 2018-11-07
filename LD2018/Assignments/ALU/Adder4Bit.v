`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    19:39:14 11/07/2018 
// Design Name: 
// Module Name:    Adder4Bit 
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
module Adder4Bit(
    input [4:0] a,
    input [4:0] b,
    output [5:0]out
    );

assign out = a + b;

endmodule
