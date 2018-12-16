`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    19:53:44 12/16/2018 
// Design Name: 
// Module Name:    DisplayControllerUnit 
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
module DisplayControllerUnit(
	 input clk,
	 input modulatedHalfSec,
    input flash,
    input [3:0] input2,
	 input [3:0] input1,
    output reg [6:0] display2,
    output reg [6:0] display1
    );
	 parameter ON = 1'd1;
	 parameter OFF = 1'd0;
	 
	 parameter HIGH = 1'd1;
	 parameter LOW = 1'd0;

	 reg state = ON; reg next_state;
	 
	 wire [6:0] display2_value;
	 segment decoder2 (
		.in(input2),
		.out(display2_value)
	 );
	 
	 wire [6:0] display1_value;
	 segment decoder1 (
		.in(input1),
		.out(display1_value)
	 );
	 
	 wire [6:0] display_off;
	 segment decoderOff (
		.in(4'd15),
		.out(display_off)
	 );
	 
	 always @ (posedge modulatedHalfSec) begin
		case (state)
			ON: if (flash == HIGH) next_state = OFF;
					 else next_state = ON;
			OFF: next_state = ON;
		endcase
	 end
	 
	 always @ (posedge clk) begin
		case (state)
			ON: begin
				display2 <= display2_value;
				display1 <= display1_value;
			end
			OFF: begin
				display2 <= display_off;
				display1 <= display_off;
			end
		endcase
	 end
	 
	 always @ (posedge modulatedHalfSec) begin
		state <= next_state;
	 end
endmodule
