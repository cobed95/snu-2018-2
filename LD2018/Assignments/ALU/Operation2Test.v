`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   20:25:58 11/07/2018
// Design Name:   Operation2
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Assignments/ALU/Operation2Test.v
// Project Name:  ALU
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: Operation2
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module Operation2Test;

	// Inputs
	reg [9:0] operands;

	// Outputs
	wire [6:0] display0;
	wire [6:0] display1;
	wire [6:0] display2;
	wire [6:0] display3;
	wire [6:0] display4;
	wire [6:0] display5;

	// Instantiate the Unit Under Test (UUT)
	Operation2 uut (
		.operands(operands), 
		.display0(display0), 
		.display1(display1), 
		.display2(display2), 
		.display3(display3), 
		.display4(display4), 
		.display5(display5)
	);

	initial begin
		// Initialize Inputs
		operands = 0;

		// Wait 100 ns for global reset to finish
		#1;
        
		// Add stimulus here
		repeat (1024) begin
			operands = operands + 1;
			#1;
		end
	end
      
endmodule

