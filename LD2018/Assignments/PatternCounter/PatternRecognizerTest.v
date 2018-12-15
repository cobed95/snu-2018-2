`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   15:55:43 11/27/2018
// Design Name:   PatternRecognizer
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Assignments/PatternCounter/PatternRecognizerTest.v
// Project Name:  PatternCounter
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: PatternRecognizer
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module PatternRecognizerTest;

	// Inputs
	reg clk;
	reg X;
	reg TYPE;
	reg [27:0] seq;
	reg [4:0] i;

	// Outputs
	wire [3:0] Y;

	// Instantiate the Unit Under Test (UUT)
	PatternRecognizer uut (
		.clk(clk), 
		.X(X), 
		.TYPE(TYPE), 
		.Y(Y)
	);

	initial begin
		// Initialize Inputs
		clk = 0;
		X = 0;
		TYPE = 0;
		/*
		seq = 28'b0101101011010101011010110101;
		i = 27;
		repeat (28) begin
			#10 X = seq[i];
			i = i - 1;
		end
		#10;
		*/
		
		/*
		seq = 28'b0100100100101010110101101001;
		i = 27;
		TYPE = 1;
		repeat (28) begin
			#10 X = seq[i];
			i = i - 1;
		end
		#10;
		*/
		
		/*
		seq = 28'b0101110010010100110010110101;
		i = 27;
		TYPE = 0;
		repeat (28) begin
			#10 X = seq[i];
			i = i - 1;
		end
		#10;
		*/
		
		
		seq = 28'b1010101001010101011001000101;
		i = 0;
		TYPE = 1;
		repeat (28) begin
			#10 X = seq[i];
			i = i + 1;
		end
		
	end
	
	always begin
		#5 clk = 1;
		#5 clk = 0;
	end
      
endmodule

