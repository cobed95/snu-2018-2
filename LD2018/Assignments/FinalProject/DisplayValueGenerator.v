`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    20:11:23 12/16/2018 
// Design Name: 
// Module Name:    DisplayValueGenerator 
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
module DisplayValueGenerator(
	 input clk,
	 input [1:0] display_mode,
	 input [20:0] out_time,
	 input [1:0] target,
	 output reg [3:0] highResult,
	 output reg [3:0] lowResult
    );
	 parameter LEFT = 2'd2;
	 parameter MID = 2'd1;
	 parameter RIGHT = 2'd0;
	 
	 parameter DISPLAY24 = 2'd0;
	 parameter DISPLAY12 = 2'd1;
	 parameter DISPLAY60 = 2'd2;
	 
	 parameter A = 4'd10;
	 parameter P = 4'd11;
	 parameter OFF = 4'd15;
	 parameter HYPHEN = 4'd12;
	 
	 wire[6:0] high;
	 wire[6:0] mid;
	 wire[6:0] low;
	 
	 assign high = out_time[20:14];
	 assign mid = out_time[13:7];
	 assign low = out_time[6:0];
	 
	 wire [3:0] high_high;
	 wire [3:0] high_low;
	 DigitDecoder decoderHigh (
		.in(high),
		.high(high_high),
		.low(high_low)
	 );
	 
	 wire [3:0] mid_high;
	 wire [3:0] mid_low;
	 DigitDecoder decoderMid (
		.in(mid),
		.high(mid_high),
		.low(mid_low)
	 );
	 
	 wire [3:0] low_high;
	 wire [3:0] low_low;
	 DigitDecoder decoderLow (
		.in(low),
		.high(low_high),
		.low(low_low)
	 );
	 
	 always @ (posedge clk) begin
		if (target == LEFT) 
		begin
			if (high == 7'b1111111)
			begin
				highResult <= HYPHEN;
				lowResult <= HYPHEN;
			end
			else if (display_mode == DISPLAY24)
			begin
				highResult <= high_high;
				lowResult <= high_low;
			end
			else if (display_mode == DISPLAY12)
			begin
				if (high >= 7'd12) highResult <= P;
				else highResult <= A;
				lowResult <= OFF;
			end
			else if (display_mode == DISPLAY60)
			begin
				if (high < 7'd10) highResult <= OFF;
				else highResult <= high_high;
				lowResult <= high_low;
			end
		end
		else if (target == MID)
		begin
			if (mid == 7'b1111111)
			begin
				highResult <= HYPHEN;
				lowResult <= HYPHEN;
			end
			else if (display_mode == DISPLAY24)
			begin
				highResult <= mid_high;
				lowResult <= mid_low;
			end
			else if (display_mode == DISPLAY12)
			begin
				if (high_high == 7'd0 && high_low == 7'd0)
				begin
					highResult <= 4'd1;
					lowResult <= 4'd2;
				end
				else
				begin
					highResult <= high_high;
					lowResult <= high_low;
				end
			end
			else if (display_mode == DISPLAY60)
			begin
				if (high < 7'd10) highResult <= OFF;
				else highResult <= high_high;
				lowResult <= high_low;
			end
		end
		else if (target == RIGHT)
		begin
			if (low == 7'b1111111)
			begin
				highResult <= HYPHEN;
				lowResult <= HYPHEN;
			end
			else if (display_mode == DISPLAY24)
			begin
				highResult <= low_high;
				lowResult <= low_low;
			end
			else if (display_mode == DISPLAY12)
			begin
				highResult <= mid_high;
				lowResult <= mid_low;
			end
		end
	 end
endmodule
