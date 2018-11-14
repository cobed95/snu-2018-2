`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   19:41:45 11/14/2018
// Design Name:   seq_counter
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Lab/Lab07/Counter/seq_counter_test.v
// Project Name:  Counter
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: seq_counter
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module seq_counter_test;

	// Inputs
	reg [2:0] in;

	// Outputs
	wire [2:0] out;

	// Instantiate the Unit Under Test (UUT)
	seq_counter uut (
		.in(in), 
		.out(out)
	);

	initial begin
		// Initialize Inputs
		in = 0;

		// Wait 100 ns for global reset to finish
		#100;
		repeat(7) begin
			in=in+1;
			#100;
		end;
		// Add stimulus here

	end
      
endmodule

