`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   17:55:55 11/14/2018
// Design Name:   Counter6Stages
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Lab/Lab07/Counter/counter6stages_test.v
// Project Name:  Counter
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: Counter6Stages
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module counter6stages_test;

	// Inputs
	reg clk;

	// Outputs
	wire [2:0] out;

	// Instantiate the Unit Under Test (UUT)
	Counter6Stages uut (
		.clk(clk), 
		.out(out)
	);

	initial begin
		// Initialize Inputs
		clk = 0;
		#5;
	

		// Wait 100 ns for global reset to finish
		
	end
			
		// Add stimulus here
	always begin
		#10 clk=1;
		#10 clk=0;
	end
	
      
endmodule

