`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    15:45:41 11/27/2018 
// Design Name: 
// Module Name:    Counter 
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
module Counter(
    input in,
    output reg [3:0] out=4'b0000
    );

always @ (*)
begin
	if (out == 4'b1111)
	begin
		if (in == 1'b1) out = 4'b0000;
	end
	else
	begin
		if (in == 1'b1) out = out + 1;
	end
end
endmodule
