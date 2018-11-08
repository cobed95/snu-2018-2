`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    20:56:01 11/08/2018 
// Design Name: 
// Module Name:    MedianFilter3Bit 
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
module MedianFilter3Bit(
    input [2:0] in,
    output reg out
    );

always @ (*) 
begin
	case (in) 
		3'b000: out <= 1'b0;
		3'b001: out <= 1'b0;
		3'b010: out <= 1'b0;
		3'b011: out <= 1'b1;
		3'b100: out <= 1'b0;
		3'b101: out <= 1'b1;
		3'b110: out <= 1'b1;
		3'b111: out <= 1'b1;
		default: out <= 1'b0;
	endcase
end

endmodule
