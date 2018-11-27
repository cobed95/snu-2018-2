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
module SR_MOORE_BOARD(
    input osc,
    input clk_aux,
    input in,
    input reset,
    output past0, 
    output past1, 
    output past2,
    output out
    );
    wire clk;

    Debouncer debouncer(osc, clk_aux, clk);

    SR_MOORE sr_moore(clk, in, reset, out);

endmodule    
