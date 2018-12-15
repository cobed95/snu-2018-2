`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    13:32:30 12/15/2018 
// Design Name: 
// Module Name:    PulseGenerator 
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
module PulseGenerator(
    input clk,
    input input_signal,
    input reset,
    output output_signal
    );
	 parameter s_0 = 2'd0;
	 parameter s_11 = 2'd1;
	 parameter s_01 = 2'd2;
	 
	 reg[1:0] state; reg [1:0] next_state;
	 
	 assign output_signal = (state == s_01) ? 1'b1 : 1'd0;
	 
	 always @ (*) begin
		case (state)
			s_0:
				next_state = (input_signal == 1'b1) ? s_01 : s_0;
			s_11:
				next_state = (input_signal == 1'b1) ? s_11 : s_0;
			s_01:
				next_state = (input_signal == 1'b1) ? s_11 : s_0;
			default:
				next_state = s_0;
		endcase
	 end
	 
	 always @ (posedge clk) begin
		state <= (reset == 1'b1) ? s_0 : next_state;
	 end
endmodule
