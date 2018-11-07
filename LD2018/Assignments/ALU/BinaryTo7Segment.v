`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    13:43:42 11/07/2018 
// Design Name: 
// Module Name:    BinaryTo7Segment 
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
module BinaryTo7Segment(
    input [4:0] binary,
    output reg [6:0] display
    );

always @ (*) 
begin
	case (binary)
		5'b00000: display = 7'b1111110;
		5'b00001: display = 7'b0110000;
		5'b00010: display = 7'b1101101;
		5'b00011: display = 7'b1111001;
		5'b00100: display = 7'b0110011;
		5'b00101: display = 7'b1011011;
		5'b00110: display = 7'b1011111;
		5'b00111: display = 7'b1110000;
		5'b01000: display = 7'b1111111;
		5'b01001: display = 7'b1111011;
		5'b01010: display = 7'b1110111;
		5'b01011: display = 7'b0011111;
		5'b01100: display = 7'b1001110;
		5'b01101: display = 7'b0111101;
		5'b01110: display = 7'b1001111;
		5'b01111: display = 7'b1000111;
		5'b10000: display = 7'b1111110;
		5'b10001: display = 7'b1000111;
		5'b10010: display = 7'b1001111;
		5'b10011: display = 7'b0111101;
		5'b10100: display = 7'b1001110;
		5'b10101: display = 7'b0011111;
		5'b10110: display = 7'b1110111;
		5'b10111: display = 7'b1111011;
		5'b11000: display = 7'b1111111;
		5'b11001: display = 7'b1110000;
		5'b11010: display = 7'b1011111;
		5'b11011: display = 7'b1011011;
		5'b11100: display = 7'b0110011;
		5'b11101: display = 7'b1111001;
		5'b11110: display = 7'b1101101;
		5'b11111: display = 7'b0110000;
		default: display = 7'b0000000;
	endcase

end

endmodule
