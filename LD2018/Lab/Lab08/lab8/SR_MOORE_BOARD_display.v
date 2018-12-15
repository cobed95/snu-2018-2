`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    19:37:07 11/21/2018 
// Design Name: 
// Module Name:    SR_MOORE_BOARD_display 
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
module SR_MOORE_BOARD_display(
    input osc,
    input clk_aux,
    input in,
    input reset,
    output [2:0] past,
    output out,
    output [6:0] display
    );
	 wire clk;
	 wire [3:0] count;
	 wire out_aux;

	 Debouncer debouncer(osc, clk_aux, clk);
	 Past_Input_Reg pastReg(clk, in, past);
    SR_MOORE sr_moore(clk,reset, in,out_aux);
	 Counter counter(clk, reset, out_aux, count);
	 assign out = out_aux;
	 segment seg(count, display);
	 

endmodule
