`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    20:42:55 11/14/2018 
// Design Name: 
// Module Name:    segment 
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
module segment(
    input [3:0] in,
    output reg [6:0] out
    );
	 
	 // 10 is used to display A
	 // 11 is used to display P
	 // 12 is used to display -
	 // 15 is used to turn off display.
	 always @ (*)
	 begin
		case (in)
			4'd0: out=7'b1111110;
			4'd1: out=7'b0110000;
			4'd2: out=7'b1101101;
			4'd3: out=7'b1111001;
			4'd4: out=7'b0110011;
			4'd5: out=7'b1011011;
			4'd6: out=7'b1011111;
			4'd7: out=7'b1110000;
			4'd8: out=7'b1111111;
			4'd9: out=7'b1111011;
			4'd10: out=7'b1110111;
			4'd11: out=7'b1100111;
			4'd12: out=7'b0000001;
			4'd15: out=7'b0000000;
			default: out=7'b0000000;
		endcase
	end
						
endmodule
