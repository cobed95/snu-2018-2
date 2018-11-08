`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:26:29 11/07/2018 
// Design Name: 
// Module Name:    Operation1 
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
module Operation1(
    input [5:0] operand,
    output [6:0] display0,
	 output [6:0] display1,
	 output [6:0] display2,
	 output [6:0] display3,
	 output [6:0] display4,
	 output [6:0] display5
    );

wire [3:0] binary5bit0;
wire [3:0] binary5bit1;
wire [3:0] binary5bit2;
wire [3:0] binary5bit3;
wire [3:0] binary5bit4;
wire [3:0] binary5bit5;

assign binary5bit0 = {3'b000, operand[0]};

assign binary5bit1 = {3'b000, operand[1]};

assign binary5bit2 = {3'b000, operand[2]};

assign binary5bit3 = {3'b000, operand[3]};

assign binary5bit4 = {3'b000, operand[4]};

assign binary5bit5 = {3'b000, operand[5]};

BinaryTo7Segment decoder0(binary5bit0, display0);
BinaryTo7Segment decoder1(binary5bit1, display1);
BinaryTo7Segment decoder2(binary5bit2, display2);
BinaryTo7Segment decoder3(binary5bit3, display3);
BinaryTo7Segment decoder4(binary5bit4, display4);
BinaryTo7Segment decoder5(binary5bit5, display5);

endmodule
