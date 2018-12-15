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
    input in,
    output reg [3:0] out
    );
	 //reg [3:0] count = 4'b0000;

    always @ (posedge in or posedge reset) 
    begin
		  if (reset) out = 4'b0000;
		  else if (in == 1'b1) out = out + 4'b0001;
    end

endmodule
