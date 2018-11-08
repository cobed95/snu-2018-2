`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    21:03:03 11/07/2018 
// Design Name: 
// Module Name:    Multiplier4Bit 
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
module Multiplier4Bit(
    input [4:0] a,
    input [4:0] b,
    output reg sign,
    output reg [7:0] out
    );

always @ (*) 
begin
    if (a[4] == b[4])
    begin
        sign <= 0;
    end
    else 
    begin
        sign <= 0;
    end
    out <= a[3:0] * b[3:0];
end

endmodule
