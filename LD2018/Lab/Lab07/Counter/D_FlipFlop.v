`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    17:29:41 11/07/2018 
// Design Name: 
// Module Name:    D_FlipFlop 
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
module D_FlipFlop(
    input D,
    input CLK,
    output Q
    );

wire Q0;
wire Q1;
wire Q2;
wire Q3;
wire Qn;

RS_Latch rs_latch0(~D, CLK, Q0, Q1);
RS_Latch_3 rs_latch_3(Q1, CLK, D, Q2, Q3);
RS_Latch rs_latch1(Q1, Q2, Q, Qn);

endmodule
