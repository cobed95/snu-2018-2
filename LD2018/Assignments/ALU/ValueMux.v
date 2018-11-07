`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    20:14:12 11/07/2018 
// Design Name: 
// Module Name:    ValueMux 
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
module ValueMux(
    input [4:0] Input0,
    input [4:0] Input1,
    input sel,
    output reg [4:0] out
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
