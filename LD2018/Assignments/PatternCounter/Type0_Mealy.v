`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    15:39:31 11/27/2018 
// Design Name: 
// Module Name:    Type0_Mealy 
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
module Type0_Mealy(
    input clk,
    input x,
    output reg out
    );
	 parameter S0 = 2'b00, S1 = 2'b01, S2 = 2'b10;
	 
	 reg [2:0] state=S0, next;
	 
	 always @ (x or state)
	 begin
		out = 0;
		case (state)
			S0: if (x == 1'b1) next = S1;
					else next = S0;
			S1: if (x == 1'b1) next = S1;
					else next = S2;
			S2: if (x == 1'b1) 
					begin
						next = S1;
						out = 1;
					end
					else next = S0;
		endcase
	 end
	 
	 always @ (negedge clk)
	 begin
		state <= next;
	 end
endmodule
