`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   20:33:28 12/16/2018
// Design Name:   DisplayControllerUnit
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Assignments/FinalProject/DisplayControllerUnitTest.v
// Project Name:  FinalProject
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: DisplayControllerUnit
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module DisplayControllerUnitTest;

	// Inputs
	reg clk;
	reg modulatedHalfSec;
	reg flash;
	reg [3:0] input2;
	reg [3:0] input1;

	// Outputs
	wire [6:0] display2;
	wire [6:0] display1;

	// Instantiate the Unit Under Test (UUT)
	DisplayControllerUnit uut (
		.clk(clk),
		.modulatedHalfSec(modulatedHalfSec), 
		.flash(flash), 
		.input2(input2), 
		.input1(input1), 
		.display2(display2), 
		.display1(display1)
	);

	initial begin
		// Initialize Inputs
		clk = 0;
		modulatedHalfSec = 0;
		flash = 1;
		input2 = 0;
		input1 = 0;

		// Wait 100 ns for global reset to finish
		#1;
		repeat (10) begin
			input1 = input1 + 1;
			#10;
		end
        
		// Add stimulus here

	end
      
	always begin
		#1 modulatedHalfSec = 1;
		#1 modulatedHalfSec = 0;
	end
	
	always begin
		#0.01 clk = 1;
		#0.01 clk = 0;
	end
endmodule

