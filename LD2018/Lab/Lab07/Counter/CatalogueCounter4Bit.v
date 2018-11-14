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
module CatalogueCounter4Bit(
    input clk,
    input p,
    input t,
    input load,
    input [3:0] load_value, 
    input clear,
    output ripple_carry_out,
    output reg [3:0] out_cnt
    );
reg [3:0] cnt = 4'b0000;
assign out_cnt = cnt;

always @ (posedge clk)
begin
    if (p == 1 & t == 1)
    begin
        if (clear == 1)
        begin
            cnt <= 4'b0000;
        end
        else if (load == 1)
        begin
            cnt <= load_value;
        end
        else 
        begin
            if (cnt == 4'b1110)
            begin
                ripple_carry_out <= 1;
            end
            else 
            begin
                ripple_carry_out <= 0;
            end
            if (cnt == 4'b1111)
            begin
                cnt <= 4'b0000;
            end
            else
            begin
                cnt <= cnt + 1;
            end
        end
    end 
end

endmodule
