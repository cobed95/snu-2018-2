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
module SecondsToRemaining(
    input reset;
    input [4:0] seconds,
    input state,
    output reg [4:0] remaining;
    );
    parameter HG = 2'b00, HY = 2'b01, FG = 2'b10, FY = 2'b11;
        
    always @ (*) 
    begin
        if (reset == 1'b1)
        begin
            remaining = 4'd0;
        end
        else
        begin
            case (state)
                HG, FG: remaining = 5'd20 - seconds;
                HY, FY: remaining = 5'd5 - seconds;
            endcase
        end
    end
endmodule
