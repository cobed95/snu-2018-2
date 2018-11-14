`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   18:01:10 11/07/2018
// Design Name:   RS_Latch_3
// Module Name:   /csehome/yejun1204/lab6/RS_Latch_3_Test.v
// Project Name:  lab6
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: RS_Latch_3
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module RS_Latch_3_Test;

	// Inputs
	reg R0;
	reg R1;
	reg S;

	// Outputs
	wire Q;
	wire Qn;

	// Instantiate the Unit Under Test (UUT)
	RS_Latch_3 uut (
		.R0(R0), 
		.R1(R1), 
		.S(S), 
		.Q(Q), 
		.Qn(Qn)
	);

	initial begin
		// Initialize Inputs
		R0 = 0;
		R1 = 0;
		S = 0;

		// Wait 100 ns for global reset to finish
		#100;
        
		// Add stimulus here

		S = 1;
		#100;
		S = 0;
		R1 = 1;
		#100;
		S = 1;
		#100;
		R0 = 1;
		R1 = 0;
		S = 0;
		#100;
		S = 1;
		#100;
		S = 0;
		R1 = 1;
		#100;
		S = 1;
		#100;
	end
      
endmodule

