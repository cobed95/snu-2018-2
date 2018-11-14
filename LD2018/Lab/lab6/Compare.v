`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    18:12:09 11/07/2018 
// Design Name: 
// Module Name:    Compare 
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
module Compare(
    input D,
    input CLK,
    output Latch,
    output FF
    );

D_Latch d_latch(D, CLK, Latch);
D_FlipFlop d_flipflop(D, ~CLK, FF);

endmodule
