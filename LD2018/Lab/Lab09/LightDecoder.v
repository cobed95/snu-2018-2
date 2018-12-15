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
module LightDecoder(
    input [1:0] light,
    output [2:0] out
    );
    parameter GREENSIG = 2'b00, YELLOWSIG = 2'b01, REDSIG = 2'b10;
    parameter GREENLIGHT = 3'b001, YELLOWLIGHT = 3'b010, REDLIGHT = 3'b100;
    
    always @ (*)
    begin
        case (light)
            GREENSIG: out = GREENLIGHT;
            YELLOWSIG: out = YELLOWLIGHT;
            REDSIG: out = REDLIGHT;
        endcase 
    end
endmodule
