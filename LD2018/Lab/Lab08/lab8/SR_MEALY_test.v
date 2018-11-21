`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   19:43:50 11/14/2018
// Design Name:   CatalogueCounter4Bit
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Lab/Lab07/Counter/CatalogueCounter4Bit_test.v
// Project Name:  Counter
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: CatalogueCounter4Bit
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module SR_MEALY_test;

	// Inputs
	reg clk;
	reg in;
	reg reset;

	// Outputs
	wire out;
	

	// Instantiate the Unit Under Test (UUT)
	SR_MEALY uut (
		.clk(clk), 
		.in(in), 
		.reset(reset), 
		.out(out)
	);

	initial begin
		// Initialize Inputs
		clk = 0;
		in = 0;
		reset = 0;

		// Wait 100 ns for global reset to finish
		#5; in=0; #20 in=1;
		#20; in=0; #20 in=1;
		#20; in=1; #20 in=1;
		#20; in=0; #20 in=1;
		#20; in=0; #20 in=0;
		#20; in=1; #20 in=0;
		#20; reset=1; #20 reset=0;
		#20; in=0; #20 in=1; #20 in=0;

	end
	
	
	always begin
		#10 clk=1;
		#10 clk=0;
	end
        
		// Add stimulus here

	
      
endmodule

