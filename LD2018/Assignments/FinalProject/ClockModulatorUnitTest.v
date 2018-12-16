`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   22:53:13 12/16/2018
// Design Name:   ClockModulatorUnit
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Assignments/FinalProject/ClockModulatorUnitTest.v
// Project Name:  FinalProject
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: ClockModulatorUnit
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module ClockModulatorUnitTest;

	// Inputs
	reg clk;

	// Outputs
	wire out;

	// Instantiate the Unit Under Test (UUT)
	ClockModulatorUnit uut (
		.clk(clk), 
		.out(out)
	);

	initial begin
		// Initialize Inputs
		clk = 0;

		// Wait 100 ns for global reset to finish
		#1;
        
		// Add stimulus here

	end
   
	always begin
		#1 clk = 1;
		#1 clk = 0;
	end
endmodule

