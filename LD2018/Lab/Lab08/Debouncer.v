`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    20:30:11 11/14/2018 
// Design Name: 
// Module Name:    Debouncer 
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
module Debouncer(
    input clk,
    input data_in,
    output reg data_out
    );
	 reg [3:0] counter = 4'b0;
	 always @(posedge clk)
	 
	 begin
	 if (!data_in) counter = 0;
	 else
	 begin
	 if (counter < 15) // replace N to number
	 begin
	 counter = counter + 1;
	 end
	 end
	 if (counter >= 15 ) data_out = 1; // N
	 else
	 data_out = 0;
	 end

endmodule
