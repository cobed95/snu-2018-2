`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    20:13:10 11/08/2018 
// Design Name: 
// Module Name:    Operation4 
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
module Operation4(
    input [5:0] operand,
    output [6:0] display0,
    output [6:0] display1,
    output [6:0] display2,
    output [6:0] display3,
    output [6:0] display4,
    output [6:0] display5
    );
wire [2:0] numOfOnes;
wire [2:0] numOfZeros;

OneCounter counter(operand, numOfOnes);
assign numOfZeros = 3'd6 - numOfOnes;

BinaryTo7Segment decoder0({1'b0, numOfOnes}, display0);

BinaryTo7Segment decoder1(4'b0000, display1);

assign display2 = 7'b0000000;

BinaryTo7Segment decoder3({1'b0, numOfZeros}, display3);

BinaryTo7Segment decoder4(4'b0000, display4);

assign display5 = 7'b0000000;

endmodule
