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
assign left = operands[9:5];
assign right = operands[4:0];

BinaryTo7Segment right0(right, display0);
Negative16 right1(right, display1);
SignDisplay right2(right[4], display2);

BinaryTo7Segment left0(left, display3);
Negative16 left1(left, display4);
SignDisplay left2(left[4], display5);

endmodule
