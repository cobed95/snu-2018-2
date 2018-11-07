`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    21:24:15 11/07/2018 
// Design Name: 
// Module Name:    SignExtender 
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
module SignExtender(
    input [4:0] a,
    output reg [9:0] out
    );

always @ (*)
begin
	if (a[4] == 0) 
	begin
		out <= {4'b00000, a};
	end
	else 
	begin
		out <= {4'b11111, a};
	end
end

endmodule
