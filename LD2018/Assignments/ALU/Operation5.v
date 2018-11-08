`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    20:35:05 11/08/2018 
// Design Name: 
// Module Name:    Operation5 
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
module Operation5(
    input [9:0] operands,
    output [6:0] display0,
    output [6:0] display1,
    output [6:0] display2,
    output [6:0] display3,
    output [6:0] display4,
    output [6:0] display5
    );
wire toRight;
wire [5:0] binary;
wire [2:0] shamt;
wire [5:0] shifted;

assign toRight = operands[9];
assign binary = operands[8:3];
assign shamt = operands[2:0];

Shifter shifter(toRight, binary, shamt, shifted);

Operation1 operation1(shifted, display0, display1, display2, display3, display4, display5);

endmodule
