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
module Counter(
    input clk,
    input reset,
    input ST,
    output reg TS;
    output reg TL;
    output reg [4:0] seconds;
    );
    reg [25:0] clk_count = 26'd0;

    always @ (posedge clk) 
    begin
        if (clk_count == 26'd50000000)
        begin
            seconds = seconds + 1;
            clk_count = 26'd0;
        end
    end

    always @ (posedge reset or posedge ST)
    begin
        if (reset == 1'b1 || ST == 1'b1)
        begin
            seconds = 5'd0;
            clk_count = 26'd0;
        end
    end

    always @ (seconds)
    begin
        TS = 0;
        TL = 0;
        if (seconds == 5'd5) TS = 1;
        if (seconds == 5'd20) TL = 1;
    end
endmodule
