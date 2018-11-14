`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    19:39:33 11/14/2018 
// Design Name: 
// Module Name:    seq_counter 
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
module seq_counter(
    input [2:0] in,
    output [2:0] out
    );
	 
	 assign out[2]=in[0];
	 assign out[1]=(~in[2]&~in[0])|(in[2]&in[0]);
	 assign out[0]=(~in[2]&in[1])|(~in[2]&in[0]);


endmodule
