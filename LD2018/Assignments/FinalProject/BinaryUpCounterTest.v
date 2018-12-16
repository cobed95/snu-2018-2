`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   14:22:14 12/15/2018
// Design Name:   BinaryUpCounter
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Assignments/FinalProject/BinaryUpCounterTest.v
// Project Name:  FinalProject
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: BinaryUpCounter
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module BinaryUpCounterTest;

	// Inputs
	reg clear;
	reg mode;
	reg manual_increment;
	reg manual_decrement;
	reg count;
	reg clk;

	// Outputs
	wire ripple_carry_out;
	wire [6:0] out;

	// Instantiate the Unit Under Test (UUT)
	BinaryUpCounter uut (
		.clear(clear), 
		.mode(mode), 
		.manual_increment(manual_increment), 
		.manual_decrement(manual_decrement), 
		.count(count), 
		.clk(clk), 
		.ripple_carry_out(ripple_carry_out), 
		.out(out)
	);

	initial begin
		// Initialize Inputs
		clear = 0;
		mode = 0;
		manual_increment = 0;
		manual_decrement = 0;
		count = 1;
		clk = 1;

		// Wait 100 ns for global reset to finish
        
		// Add stimulus here

	end
	always begin
		#1 clk = 0;
		#1 clk = 1;
	end
	always begin
		#1 count = 0;
		#9 count = 1;
	end
endmodule

