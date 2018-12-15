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
module DigitDecoder(
    input [4:0] number,
    output [6:0] tens,
    output [6:0] ones
    );
    parameter ZERO = 7'b1111110;
    parameter ONE = 7'b0110000;
    parameter TWO = 7'b1101101;
    parameter THREE = 7'b1111001;
    parameter FOUR = 7'b0110011;
    parameter FIVE = 7'b1011011;
    parameter SIX = 7'b1011111;
    parameter SEVEN = 7'b1110000;
    parameter EIGHT = 7'b1111111;
    parameter NINE = 7'b1111011;	
    
    always @ (*)
    begin
        case (number)
            5'd0: begin 
                tens = ZERO;
                ones = ZERO;
            end
            5'd1: begin
                tens = ZERO;
                ones = ONE;
            end
            5'd2: begin
                tens = ZERO;
                ones = TWO;
            end
            5'd3: begin
                tens = ZERO;
                ones = THREE;
            end
            5'd4 begin  
                tens = ZERO;
                ones = FOUR;
            end
            5'd5 begin
                tens = ZERO;
                ones = FIVE;
            end
            5'd6 begin
                tens = ZERO;
                ones = SIX;
            end
            5'd7 begin
                tens = ZERO;
                ones = SEVEN;
            end
            5'd8 begin
                tens = ZERO;
                ones = EIGHT;
            end
            5'd9 begin
                tens = ZERO;
                ones = NINE;
            end
            5'd10: begin 
                tens = ONE;
                ones = ZERO;
            end
            5'd11: begin
                tens = ONE;
                ones = ONE;
            end
            5'd12: begin
                tens = ONE;
                ones = TWO;
            end
            5'd13: begin
                tens = ONE;
                ones = THREE;
            end
            5'd14 begin  
                tens = ONE;
                ones = FOUR;
            end
            5'd15 begin
                tens = ONE;
                ones = FIVE;
            end
            5'd16 begin
                tens = ONE;
                ones = SIX;
            end
            5'd17 begin
                tens = ONE;
                ones = SEVEN;
            end
            5'd18 begin
                tens = ONE;
                ones = EIGHT;
            end
            5'd19 begin
                tens = ONE;
                ones = NINE;
            end
            5'd20 begin
                tens = TWO;
                ones = ZERO;
            end
        endcase 
    end
endmodule
