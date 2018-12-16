`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   21:05:04 12/16/2018
// Design Name:   DigitalClockImpl
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Assignments/FinalProject/DigitalClockImplTest.v
// Project Name:  FinalProject
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: DigitalClockImpl
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module DigitalClockImplTest;

	// Inputs
	reg clk;
	reg mode;
	reg set;
	reg op1;
	reg op2;
	reg reset;

	// Outputs
	wire [6:0] display6;
	wire [6:0] display5;
	wire [6:0] display4;
	wire [6:0] display3;
	wire [6:0] display2;
	wire [6:0] display1;

	// Instantiate the Unit Under Test (UUT)
	DigitalClockImpl uut (
		.clk(clk), 
		.mode(mode), 
		.set(set), 
		.op1(op1), 
		.op2(op2), 
		.reset(reset), 
		.display6(display6), 
		.display5(display5), 
		.display4(display4), 
		.display3(display3), 
		.display2(display2), 
		.display1(display1)
	);

	initial begin
		// Initialize Inputs
		clk = 0;
		mode = 0;
		set = 0;
		op1 = 0;
		op2 = 0;
		reset = 0;

		// Wait 100 ns for global reset to finish
		#1;
        
		// Add stimulus here

	end
	
	always begin
		#1 clk = 1;
		#1 clk = 0;
	end
endmodule

