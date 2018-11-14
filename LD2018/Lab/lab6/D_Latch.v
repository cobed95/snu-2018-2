`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    17:28:42 11/07/2018 
// Design Name: 
// Module Name:    D_Latch 
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
module D_Latch(
    input D,
    input E,
    output Q
    );

wire R;
wire S;
wire Qn;

assign R = ~D & E;
assign S = D & E;
RS_Latch rs_latch(R, S, Q, Qn);

endmodule
