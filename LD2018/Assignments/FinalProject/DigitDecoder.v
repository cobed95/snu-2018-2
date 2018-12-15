`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    18:38:30 12/15/2018 
// Design Name: 
// Module Name:    DigitDecoder 
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
module DigitDecoder(
    input [6:0] in,
    output reg [3:0] high,
    output reg [3:0] low
    );
	 wire [3:0] lowCandidate;
	 assign lowCandidate = in[3:0];
	 
	 always @ (*) begin
		if (in < 7'd10) high = 4'd0;
		else if (in >= 7'd10 && in < 7'd20) high = 4'd1;
		else if (in >= 7'd20 && in <7'd30) high = 4'd2;
		else if (in >= 7'd30 && in < 7'd40) high = 4'd3;
		else if (in >= 7'd40 && in <7'd50) high = 4'd4;
		else if (in >= 7'd50 && in < 7'd60) high = 4'd5;
		else if (in >= 7'd60 && in <7'd70) high = 4'd6;
		else if (in >= 7'd70 && in < 7'd80) high = 4'd7;
		else if (in >= 7'd80 && in <7'd90) high = 4'd8;
		else if (in >= 7'd90 && in < 7'd100) high = 4'd9;
		
		if (lowCandidate >= 10) low = lowCandidate - 10;
		else low = lowCandidate;
	 end
endmodule
