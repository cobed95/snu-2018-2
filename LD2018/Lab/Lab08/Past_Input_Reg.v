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
module Past_Input_Reg(
    input clk,
    input in,
    output reg [2:0] out
    );
    reg [2:0] past = 3'b000;

    always @ (in)
    begin
        past = {past[1:0], in};
        out = past;
    end
endmodule
