`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   23:00:38 12/16/2018
// Design Name:   ClockModulatorTotal
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Assignments/FinalProject/ClockModulatorTotalTest.v
// Project Name:  FinalProject
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: ClockModulatorTotal
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module ClockModulatorTotalTest;

	// Inputs
	reg clk;

	// Outputs
	wire out;

	// Instantiate the Unit Under Test (UUT)
	ClockModulatorTotal uut (
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

