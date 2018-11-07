`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:11:38 11/07/2018 
// Design Name: 
// Module Name:    Negative16 
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
module Negative16(
    input [4:0] negative16,
    output reg [6:0] display
    );
	 
always @ (*) 
begin
	case (negative16)
		5'b10000: display = 7'b0110000;
		default: display = 7'b1111110;
	endcase
end

endmodule
