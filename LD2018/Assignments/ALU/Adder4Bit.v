`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    19:39:14 11/07/2018 
// Design Name: 
// Module Name:    Adder4Bit 
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
module Adder4Bit(
    input [4:0] a,
    input [4:0] b,
    output reg sign,
    output reg [4:0] out
    );

always @ (*) 
begin

    if (a[4] == 0 && b[4] == 0) 
    begin
        sign <= 0;
        out <= a[3:0] + b[3:0];
    end
    else if (a[4] == 0 && b[4] == 1)
    begin
        if (a[3:0] >= b[3:0])
        begin
            sign <= 0;
            out <= a[3:0] - b[3:0];
        end
        else 
        begin
            sign <= 1;
            out <= b[3:0] - a[3:0];
        end
    end
    else if (a[4] == 1 && b[4] == 0)
    begin
        if (b[3:0] >= a[3:0])
        begin
            sign <= 0;
            out <= b[3:0] - a[3:0];
        end
        else 
        begin
            sign <= 1;
            out <= a[3:0] - b[3:0];
        end
    end
    else
    begin
        sign <= 1;
        out <= a[3:0] + b[3:0];
    end
end

endmodule
