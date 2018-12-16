`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   19:27:50 12/16/2018
// Design Name:   DigitDecoder
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Assignments/FinalProject/DigitDecoderTest.v
// Project Name:  FinalProject
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: DigitDecoder
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module DigitDecoderTest;

	// Inputs
	reg [6:0] in;

	// Outputs
	wire [3:0] high;
	wire [3:0] low;

	// Instantiate the Unit Under Test (UUT)
	DigitDecoder uut (
		.in(in), 
		.high(high), 
		.low(low)
	);

	initial begin
		// Initialize Inputs
		in = 0;

		// Wait 100 ns for global reset to finish
		#1;
		repeat(100000) begin
			in = in + 1;
			#1;
		end
        
		// Add stimulus here

	end
      
endmodule

