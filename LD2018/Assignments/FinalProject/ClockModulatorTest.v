`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   21:41:14 12/16/2018
// Design Name:   ClockModulator
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Assignments/FinalProject/ClockModulatorTest.v
// Project Name:  FinalProject
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: ClockModulator
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module ClockModulatorTest;

	// Inputs
	reg clk;

	// Outputs
	wire [25: 0] clk_count_output;
	wire out;

	// Instantiate the Unit Under Test (UUT)
	ClockModulator uut (
		.clk(clk), 
		.clk_count_output(clk_count_output),
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

