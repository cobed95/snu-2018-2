`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   17:52:47 11/07/2018
// Design Name:   D_FlipFlop
// Module Name:   /csehome/yejun1204/lab6/D_FlipFlop_Test.v
// Project Name:  lab6
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: D_FlipFlop
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module D_FlipFlop_Test;

	// Inputs
	reg D;
	reg CLK;

	// Outputs
	wire Q;

	// Instantiate the Unit Under Test (UUT)
	D_FlipFlop uut (
		.D(D), 
		.CLK(CLK), 
		.Q(Q)
	);

	initial begin
		// Initialize Inputs
		D = 1;
		CLK = 1;

		// Wait 100 ns for global reset to finish
		#100;
        
		// Add stimulus here
		CLK = 0;
		#100;
		D = 0;
		CLK = 1;
		#100;
		CLK = 0;
		#100;
		D = 1;
		CLK = 1;
		#100;
		CLK = 0;

	end
      
endmodule

