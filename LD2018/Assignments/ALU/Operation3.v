`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    21:01:56 11/07/2018 
// Design Name: 
// Module Name:    Operation3 
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
module Operation3(
    input [9:0] operands,
    output [6:0] display0,
    output [6:0] display1,
    output [6:0] display2,
    output [6:0] display3,
    output [6:0] display4,
    output [6:0] display5
    );
wire [4:0] left;
wire [4:0] right;
wire [9:0] result;
wire sign;
wire [9:0] complement;
wire [9:0] absoluteVal;

assign left = operands[9:5];
assign right = operands[4:0];

Multiplier4Bit multiplier(left, right, result);

assign sign = result[9];
assign complement = 11'b10000000000 - result;
BigValueMux bigValueMux(result, complement, sign, absoluteVal);

BinaryTo7Segment decoder0({0, absoluteVal[3:0]}, display0);
BinaryTo7Segment decoder1({0, absoluteVal[7:4]}, display1);
BinaryTo7Segment decoder2(5'b00000, display2);

SignDisplay signDisplay(sign, display3);

endmodule
