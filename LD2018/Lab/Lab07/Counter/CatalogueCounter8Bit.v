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
module CatalogueCounter8Bit(
    input clk,
    input p,
    input t,
    input load,
    input [7:0] load_value, 
    input clear,
    output ripple_carry_out,
    output [7:0] out_cnt
    );
wire rco_low;
wire p_high;
wire t_high;

CatalogueCounter4Bit counter0(clk, p, t, load, load_value[3:0], clear, rco_low, out_cnt[3:0]);

assign p_high = rco_low;
assign t_high = rco_low;

CatalogueCounter4Bit counter1(clk, p_high, t_high, load, load_value[7:4], clear, ripple_carry_out, out_cnt[7:4]);

endmodule
