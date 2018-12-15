`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    15:12:41 11/27/2018 
// Design Name: 
// Module Name:    Type1_Mealy 
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
module Type1_Mealy(
    input clk,
    input x,
    output reg out
    );
	 parameter S0 = 2'b00, S1 = 2'b01, S2 = 2'b10, S3 = 2'b11;
	 
	 reg [2:0] state=S0, next;
	 
	 always @ (x or state)
	 begin
		out = 0;
		case (state)
			S0: if (x == 1'b1) next = S0;
					else next = S1;
			S1: if (x == 1'b1) next = S2;
					else next = S1;
			S2: if (x == 1'b1) next = S0;
					else next = S3;
			S3: if (x == 1'b1) 
					begin
						next = S2;
						out = 1;
					end
					else next = S1;
		endcase
	 end
	 
	 always @ (negedge clk)
	 begin
		state <= next;
	 end
endmodule
