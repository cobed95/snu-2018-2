`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   21:10:30 11/08/2018
// Design Name:   Operation6
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Assignments/ALU/Operation6Test.v
// Project Name:  ALU
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: Operation6
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module Operation6Test;

	// Inputs
	reg [5:0] operand;

	// Outputs
	wire [6:0] display0;
	wire [6:0] display1;
	wire [6:0] display2;
	wire [6:0] display3;
	wire [6:0] display4;
	wire [6:0] display5;

	// Instantiate the Unit Under Test (UUT)
	Operation6 uut (
		.operand(operand), 
		.display0(display0), 
		.display1(display1), 
		.display2(display2), 
		.display3(display3), 
		.display4(display4), 
		.display5(display5)
	);

	initial begin
		// Initialize Inputs
		operand = 0;

		// Wait 100 ns for global reset to finish
		#100;
        
		// Add stimulus here
		operand = 6'b010100;
	end
      
endmodule

