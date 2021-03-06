`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:34:20 12/15/2018 
// Design Name: 
// Module Name:    ClockModulator 
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
module ClockModulator(
    input unit_out,
    output out
    );
	 parameter LIMIT = 26'd10;
	 parameter ZERO = 26'd0;
	 
	 parameter HIGH = 1'b1;
	 parameter LOW = 1'b0;
	 
	 reg state=HIGH; 
	 assign out = state;
	 
	 always @ (posedge unit_out)
	 begin
		if (state == HIGH) state <= LOW;
		else if (state == LOW) state <= HIGH;
	 end
endmodule
