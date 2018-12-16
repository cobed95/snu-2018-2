`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   21:14:29 12/16/2018
// Design Name:   DigitalClock
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Assignments/FinalProject/DigitalClockTest.v
// Project Name:  FinalProject
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: DigitalClock
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module DigitalClockTest;

	// Inputs
	reg clk;
	reg mode;
	reg set;
	reg op1;
	reg op2;
	reg reset;

	// Outputs
	wire display;
	wire [2:0] flash;
	wire [20:0] out_time;

	// Instantiate the Unit Under Test (UUT)
	DigitalClock uut (
		.clk(clk), 
		.mode(mode), 
		.set(set), 
		.op1(op1), 
		.op2(op2), 
		.reset(reset), 
		.display(display), 
		.flash(flash), 
		.out_time(out_time)
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
		#100;
        
		// Add stimulus here
	end
      
	always begin
		#0.001 clk = 1;
		#0.001 clk = 0;
	end
endmodule

