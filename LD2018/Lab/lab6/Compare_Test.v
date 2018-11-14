`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   18:13:22 11/07/2018
// Design Name:   Compare
// Module Name:   /csehome/yejun1204/lab6/Compare_Test.v
// Project Name:  lab6
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: Compare
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module Compare_Test;

	// Inputs
	reg D;
	reg CLK;

	// Outputs
	wire Latch;
	wire FF;

	// Instantiate the Unit Under Test (UUT)
	Compare uut (
		.D(D), 
		.CLK(CLK), 
		.Latch(Latch), 
		.FF(FF)
	);

	initial begin
		// Initialize Inputs
		D = 0;
		CLK = 0;
		#9 D = 1;
		#5 D = 0;
		#5 D = 1;
		#5 D = 0;
		#10 D = 1;
		#5 D = 0;
		#10 D = 1;
		#5 D = 0;

	end
	
	always begin
		#10 CLK = 1;
		#10 CLK = 0;
	end
      
endmodule

