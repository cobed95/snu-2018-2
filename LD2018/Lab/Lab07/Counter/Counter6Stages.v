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
    input clk,
    output [2:0] out
    );
reg [2:0] state = 3'b001;
assign out = state;

wire d0;
wire d1;
wire d2;

assign d0 = state[0];
assign d1 = state[1];
assign d2 = state[2];

//wire aux;
//assign aux = state[0];

wire d3;
wire d4;
wire d5;

D_FlipFlop ff0 (
	.D(d0),
	.CLK(clk),
	.Q(d3)
);

D_FlipFlop ff1 (
	.D(d1),
	.CLK(clk),
	.Q(d4)
);

D_FlipFlop ff2 (
	.D(d2),
	.CLK(clk),
	.Q(d5)
);

always @ (*)
begin
	d2 <= state[0];
	d1 <= (~state[2] & ~state[0]) | (state[2] & state[0]);
	d0 <= (~state[2] & state[1]) | (~state[2] & state[0]);
	state = {d5, d4, d3};
end

endmodule
