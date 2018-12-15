`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:07:01 11/27/2018 
// Design Name: 
// Module Name:    PatternRecognizer 
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
module PatternRecognizer(
    input clk,
    input X,
    input TYPE,
    output [3:0] Y
    );
wire aux0;
wire aux1;
wire [3:0] count0;
wire [3:0] count1;
	 
Type0_Mealy type0(clk, X, aux0);
Counter counter0(aux0, count0);

Type1_Mealy type1(clk, X, aux1);
Counter counter1(aux1, count1);

Mux mux(count0, count1, TYPE, Y);

endmodule
