`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    21:00:49 11/08/2018 
// Design Name: 
// Module Name:    MedianFilter5Bit 
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
module MedianFilter5Bit(
    input hiddenBit,
    input [4:0] operand,
    output [4:0] filtered
    );
wire [3:0] dirty0;
wire [3:0] dirty1;
wire [3:0] dirty2;
wire [3:0] dirty3;
wire [3:0] dirty4;

assign dirty0 = {operand[1:0], hiddenBit};
assign dirty1 = operand[2:0];
assign dirty2 = operand[3:1];
assign dirty3 = operand[4:2];
assign dirty4 = {hiddenBit, operand[4:3]};

MedianFilter3Bit filter0(dirty0, filtered[0]);
MedianFilter3Bit filter1(dirty1, filtered[1]);
MedianFilter3Bit filter2(dirty2, filtered[2]);
MedianFilter3Bit filter3(dirty3, filtered[3]);
MedianFilter3Bit filter4(dirty4, filtered[4]);

endmodule
