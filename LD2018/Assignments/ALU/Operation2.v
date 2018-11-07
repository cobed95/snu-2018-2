`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:51:07 11/07/2018 
// Design Name: 
// Module Name:    Operation2 
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
module Operation2(
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
wire over16;
wire sign;
wire [5:0] result;
wire [4:0] firstDigit;
wire [4:0] secondDigit;

assign left = operands[9:5];
assign right = operands[4:0];
assign over16 = (~left[4] & ~right[4]) | (left[4] & right[4]);

Adder4Bit adder(left, right, result);

BitMux bitMux(result[4], result[5], over16, sign);

ValueMux valueMux0(result[4:0], {0, result[3:0]}, over16, firstDigit);
BinaryTo7Segment decoder0(firstDigit, display0);

ValueMux4to1 valueMux4to1(5'b00000, 5'b00000, {4'b0000, result[4]}, {4'b0000, result[5]}, sign, over16, secondDigit);
BinaryTo7Segment decoder1(secondDigit, display1);

BinaryTo7Segment decoder2(5'b00000, display2);

SignDisplay signDisplay(sign, display3);

endmodule
