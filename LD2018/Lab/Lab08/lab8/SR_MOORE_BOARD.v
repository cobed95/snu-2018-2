`timescale 1ns / 1ps

module SR_MOORE_BOARD(
    input osc,
    input clk_aux,
    input in,
    input reset,
    output [2:0] past,
    output out
    );
    wire clk;

    Debouncer debouncer(osc, clk_aux, clk);
	 Past_Input_Reg pastReg(clk, in, past);
    SR_MOORE sr_moore(clk,reset, in,out);

endmodule    
