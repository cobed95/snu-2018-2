`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    17:34:37 12/15/2018 
// Design Name: 
// Module Name:    AlarmControl24 
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
module AlarmControl24(
    input clear,
	 input mode,
    input manual_increment,
	 input manual_decrement,
    input count,
    input clk,
	 output reg ripple_carry_out,
    output [6:0] out
    );
	 parameter CLEAR = 1'b1;
	 parameter SET = 1'b1;
	 parameter COUNT = 1'b1;
	 parameter INCREMENT = 1'b1;
	 parameter DECREMENT = 1'b1;
	 parameter LIMIT= 7'd23;
	 parameter ZERO = 7'd0;
	 
	 reg[6:0] state = ZERO;
	 assign out = state;
	 
	 always @ (posedge clk)
	 begin
		if (clear == CLEAR) state = ZERO;
		else if (mode == SET) 
		begin
			if (manual_increment == INCREMENT) 
				state = state + 1;
			if (manual_decrement == DECREMENT)
				state = state + 1;
		end
		else if (count == COUNT) 
		begin
			ripple_carry_out = 0;
			if (state == LIMIT) state = ZERO;
			else
			begin
				if (state == LIMIT - 1) ripple_carry_out = 1;
				state = state + 1;
			end
		end
	 end
endmodule
