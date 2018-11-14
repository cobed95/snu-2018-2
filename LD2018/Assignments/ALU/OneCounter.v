`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    20:16:54 11/08/2018 
// Design Name: 
// Module Name:    OneCounter 
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
module OneCounter(
    input [5:0] binary,
    output reg [2:0] numOfOnes
    );

always @ (binary) 
begin
	numOfOnes = 3'd0;
	if (binary[0] == 1)
	begin
		numOfOnes = numOfOnes + 1;
	end
	if (binary[1] == 1)
	begin
		numOfOnes = numOfOnes + 1;
	end
	if (binary[2] == 1)
	begin
		numOfOnes = numOfOnes + 1;
	end
	if (binary[3] == 1)
	begin
		numOfOnes = numOfOnes + 1;
	end
	if (binary[4] == 1)
	begin
		numOfOnes = numOfOnes + 1;
	end
	if (binary[5] == 1)
	begin
		numOfOnes = numOfOnes + 1;
	end
end

endmodule
