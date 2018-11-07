`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    21:33:45 11/07/2018 
// Design Name: 
// Module Name:    BigValueMux 
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
module BigValueMux(
    input [9:0] Input0,
    input [9:0] Input1,
    input sel,
    output reg [9:0] out
    );

always @ (*) 
begin
	if (sel == 0)
	begin
		out <= Input0;
	end
	else
	begin
		out <= Input1;
	end
end

endmodule
