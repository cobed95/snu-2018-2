`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   18:46:35 11/07/2018
// Design Name:   UniversalShift4bit
// Module Name:   /csehome/yejun1204/lab6/UniversalShift4bitTest.v
// Project Name:  lab6
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: UniversalShift4bit
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module UniversalShift4bitTest;

	// Inputs
	reg m0;
	reg m1;
	reg left_in;
	reg right_in;
	reg clr;
	reg clk;
	reg [3:0] p_in;

	// Outputs
	wire left_out;
	wire right_out;
	wire [3:0] p_out;

	// Instantiate the Unit Under Test (UUT)
	UniversalShift4bit uut (
		.m0(m0), 
		.m1(m1), 
		.left_in(left_in), 
		.right_in(right_in), 
		.clr(clr), 
		.clk(clk), 
		.p_in(p_in), 
		.left_out(left_out), 
		.right_out(right_out), 
		.p_out(p_out)
	);

	initial begin
		// Initialize Inputs
		m0 = 0;
		m1 = 0;
		left_in = 0;
		right_in = 0;
		clr = 0;
		clk = 0;
		p_in = 0;

		// Wait 100 ns for global reset to finish
		#10;
      m0 = 1;
		m1 = 1;
		p_in = 4'b1001;
		#10;
		m0 = 0;
		#60;
		m0 = 1;
		m1 = 1;
		p_in = 4'b0110;
		#20;
		m0 = 1;
		m1 = 0;
		right_in = 1;
		#10;
		right_in = 0;
		#60;
		clr = 1;
		#10;
	end
	
	always begin
		#10 clk = 1;
		#10 clk = 0;
	end
      
endmodule

