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

module CatalogueCounter4Bit_test;

	// Inputs
	reg clk;
	reg p;
	reg t;
	reg load;
	reg [3:0] load_value;
	reg clear;

	// Outputs
	wire ripple_carry_out;
	wire [3:0] out_cnt;
	

	// Instantiate the Unit Under Test (UUT)
	CatalogueCounter4Bit uut (
		.clk(clk), 
		.p(p), 
		.t(t), 
		.load(load), 
		.load_value(load_value), 
		.clear(clear), 
		.ripple_carry_out(ripple_carry_out), 
		.out_cnt(out_cnt)
	);

	initial begin
		// Initialize Inputs
		clk = 0;
		p = 0;
		t = 0;
		load = 0;
		load_value = 0;
		clear = 0;

		// Wait 100 ns for global reset to finish
		p=1;
		t=1;
		#300;
		clear=1;
		#20;
		clear=0;
		load=1;
		load_value=4'b0011;
		#20;
		load=0;
		#100;
		p=0;
		t=0;
		#300;
	end
	
	
	always begin
		#5 clk=0;
		#5 clk=1;
	end
        
		// Add stimulus here

	
      
endmodule

