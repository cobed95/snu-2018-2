`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    19:42:59 11/07/2018 
// Design Name: 
// Module Name:    Mux 
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
module Mux(
    input [6:0] Input0,
    input [6:0] Input1,
    input sel,
    output reg [6:0] out
    );

always @ (*) 
begin
	if (sel == 1'b0)
	begin
		out <= Input0;
	end
	else 
	begin
		out <= Input1;
	end
end

endmodule
