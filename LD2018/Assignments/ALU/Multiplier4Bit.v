`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    21:03:03 11/07/2018 
// Design Name: 
// Module Name:    Multiplier4Bit 
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
module Multiplier4Bit(
    input [4:0] a,
    input [4:0] b,
    output [9:0] out
    );
wire [9:0] signExtendedA;
wire [9:0] signExtendedB;
wire [19:0] signExtendedAnswer;

SignExtender signExtenderA(a, signExtendedA);
SignExtender signExtenderB(b, signExtendedB);

assign signExtendedAnswer = signExtendedA * signExtendedB;
assign out = signExtendedAnswer[9:0];

endmodule
