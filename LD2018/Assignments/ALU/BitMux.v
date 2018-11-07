`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    20:18:13 11/07/2018 
// Design Name: 
// Module Name:    BitMux 
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
module BitMux(
    input Input0,
    input Input1,
    input sel,
    output reg out
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
