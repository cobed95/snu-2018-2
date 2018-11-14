`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   17:35:24 11/07/2018
// Design Name:   D_Latch
// Module Name:   /csehome/yejun1204/lab6/D_Latch_Test.v
// Project Name:  lab6
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: D_Latch
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module D_Latch_Test;

	// Inputs
	reg D;
	reg E;

	// Outputs
	wire Q;

	// Instantiate the Unit Under Test (UUT)
	D_Latch uut (
		.D(D), 
		.E(E), 
		.Q(Q)
	);

	initial begin
		// Initialize Inputs
		D = 1;
		E = 1;

		// Wait 100 ns for global reset to finish
		#100;
        
		// Add stimulus here
		D = 0;
		#100;
		
		E = 1;
		D = 1;
		
		#100;
		E = 0;
		D = 0;
		
		#100;
		D = 1;
		E = 0;
		
		#100;
		D = 0;
		E = 0;
	end
      
endmodule

