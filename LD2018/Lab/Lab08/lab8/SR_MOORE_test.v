`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   18:50:39 11/21/2018
// Design Name:   SR_MOORE
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Lab/Lab08/lab8/SR_MOORE_test.v
// Project Name:  lab8
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: SR_MOORE
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module SR_MOORE_test;

	// Inputs
	reg clk;
	reg reset;
	reg in;

	// Outputs
	wire out;

	// Instantiate the Unit Under Test (UUT)
	SR_MOORE uut (
		.clk(clk), 
		.reset(reset), 
		.in(in), 
		.out(out)
	);

	initial begin
		// Initialize Inputs
		clk = 0;
		reset = 0;
		in = 0;

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

