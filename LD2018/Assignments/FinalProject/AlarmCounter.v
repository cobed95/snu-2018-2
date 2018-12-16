`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    01:30:35 12/17/2018 
// Design Name: 
// Module Name:    AlarmCounter 
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
module AlarmCounter(
    input clear,
	 input mode,
    input manual_increment,
	 input manual_decrement,
    input count,
    input clk,
	 output ripple_carry_out,
    output [6:0] out
    );
	 parameter CLEAR = 1'b1;
	 parameter SET = 1'b1;
	 parameter COUNT = 1'b1;
	 parameter INCREMENT = 1'b1;
	 parameter DECREMENT = 1'b1;
	 parameter LIMIT= 7'd59;
	 parameter ZERO = 7'd0;
	 parameter UNSET = 7'b1111111;
	 
	 reg[6:0] state = UNSET;
	 assign out = state;
	 reg rco_reg;
	 assign ripple_carry_out = rco_reg;

	 always @ (posedge clk or posedge clear)
	 begin
		if (clear == CLEAR) state = UNSET;
		else if (mode == SET) 
		begin
			if (manual_increment == INCREMENT) 
				if (state == UNSET || state == LIMIT) state = ZERO;
				else state = state + 1;
			if (manual_decrement == DECREMENT)
				if (state == UNSET || state == ZERO) state = LIMIT;
				else state = state - 1;
		end
		else if (count == COUNT) 
		begin
			if (state == LIMIT) 
			begin 
				state = ZERO;
				rco_reg = 1;
			end
			else state = state + 1;
		end
	 end
	 /*
	 always @ (posedge clk)
	 begin
		case (rco_reg)
			1'b1: rco_reg = 0;
			1'b0: if (count == COUNT && state == LIMIT) rco_reg = 1;
						else rco_reg = 0;
		endcase
	 end
	 */
endmodule
