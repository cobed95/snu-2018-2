`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    15:11:36 11/14/2018 
// Design Name: 
// Module Name:    Counter6Stages 
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
module Counter6Stages(
    input [2:0] init,
    input clk,
    output [2:0] out
    );
reg [2:0] state;
wire d0;
wire d1;
wire d2;

assign state = init;

assign d0 = state[3];
assign d1 = (~state[0] & ~state[2]) | (state[0] & state[2]);
assign d2 = (~state[0] & state[1]) | (~state[0] & state[2]);

D_FlipFlop ff0(d0, clk, state[0]);
D_FlipFlop ff1(d1, clk, state[1]);
D_FlipFlip ff2(d2, clk, state[2]);

assign out = state;

endmodule
