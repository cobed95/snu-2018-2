`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    16:13:09 12/17/2018 
// Design Name: 
// Module Name:    AlarmSecondsRegister 
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
module AlarmSecondsRegister(
	 input clk,
    input clear,
    output reg [6:0] out
    );
	 parameter UNSET = 7'b1111111;
	 parameter ZERO = 7'b0000000;
	 parameter HIGH = 1'b1;
	 parameter LOW = 1'b0;	

	always @ (posedge clk or posedge clear) 
	begin
		if (clear == HIGH) out <= UNSET;
		else out <= ZERO;
	end
endmodule
