`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    20:52:37 11/07/2018 
// Design Name: 
// Module Name:    ValueMux4to1 
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
module ValueMux4to1(
    input [4:0] Input0,
    input [4:0] Input1,
    input [4:0] Input2,
    input [4:0] Input3,
    input sel0,
    input sel1,
    output [4:0] out
    );
wire [4:0] low;
wire [4:0] high;

ValueMux valueMuxLow(Input0, Input1, sel0, low);
ValueMux valueMuxHigh(Input2, Input3, sel0, high);

ValueMux valueMuxFinal(low, high, sel1, out);

endmodule
