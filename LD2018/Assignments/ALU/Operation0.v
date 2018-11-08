`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    18:45:28 11/02/2018 
// Design Name: 
// Module Name:    Operation0 
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
module Operation0(
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
wire leftSign;
wire rightSign;

assign left = operands[9:5];
assign right = operands[4:0];

DisplayController rightDisplay(right, display0, rightSign);
BinaryTo7Segment decoder1(4'd0, display1);
SignDisplay signDisplayRight(rightSign, display2);

DisplayController leftDisplay(left, display0, leftsign);
BinaryTo7Segment decoder1(4'd0, display1);
SignDisplay signDisplayLeft(leftSign, display2);

endmodule
