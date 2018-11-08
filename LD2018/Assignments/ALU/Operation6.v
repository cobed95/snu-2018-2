`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    21:06:23 11/08/2018 
// Design Name: 
// Module Name:    Operation6 
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
module Operation6(
    input [5:0] operand,
    output [6:0] display0,
    output [6:0] display1,
    output [6:0] display2,
    output [6:0] display3,
    output [6:0] display4,
    output [6:0] display5
    );
wire hiddenBit;
wire [4:0] dirty;
wire [4:0] filtered;

assign hiddenBit = operand[5];
assign dirty = operand[4:0];

MedianFilter5Bit filter(hiddenBit, dirty, filtered);

BinaryTo7Segment decoder0({3'b000, filtered[0]}, display0);
BinaryTo7Segment decoder1({3'b000, filtered[1]}, display1);
BinaryTo7Segment decoder2({3'b000, filtered[2]}, display2);
BinaryTo7Segment decoder3({3'b000, filtered[3]}, display3);
BinaryTo7Segment decoder4({3'b000, filtered[4]}, display4);
assign display5 = 7'b0000000;

endmodule
