`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    13:31:20 12/15/2018 
// Design Name: 
// Module Name:    BinaryUpCounter 
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
module BinaryUpCounter(
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
	 
	 reg[6:0] state = ZERO;
	 assign out = state;
	 reg rco_reg=0; 
	 assign ripple_carry_out = rco_reg;
	 
	 always @ (posedge clk)
	 begin
		rco_reg = 0;
		if (clear == CLEAR) 
			state = ZERO;		
		else if (mode == SET) 
		begin
			if (manual_increment == INCREMENT) 
				if (state == LIMIT)
					state = ZERO;
				else state = state + 1;
			if (manual_decrement == DECREMENT)
				if (state == ZERO)
					state = LIMIT;
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
			1'b1: next_rco_reg = 0;
			1'b0: if (count == COUNT && state == LIMIT) next_rco_reg = 1;
						else next_rco_reg = 0;
		endcase
	 end
	 
	 always @ (state)
	 begin
		rco_reg <= next_rco_reg;
	 end
	 */
endmodule
