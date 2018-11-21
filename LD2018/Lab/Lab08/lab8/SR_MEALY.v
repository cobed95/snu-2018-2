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
module SR_MEALY(
    input clk,
    input in,
    input reset,
    output reg out
    );

    parameter S0 = 3'b000, S1 = 3'b001, S2 = 3'b010, S3 = 3'b011, EXIT = 3'b100;

    reg [2:0] state, next;
    
    always @ (in or state)
    begin
        out = 0;
        case (state)
            S0: if (in == 1'b1) next = S1;
                else next = S0;
            S1: if (in == 1'b1) next = S3;
                else 
                begin  
                    next = S2; 
                    out = 1;
                end
            S2: if (in == 1'b1) next = S1; 
                else next = EXIT; 
            S3: if (in == 1'b1) next = S3; 
                else next = S2; 
            EXIT: next = EXIT; 
        endcase
    end

    always @ (posedge clk or posedge reset)
    begin
        if (reset) state <= S0;
        else state <= next;
    end
endmodule
