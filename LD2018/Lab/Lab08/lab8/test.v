`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   19:48:23 11/21/2018
// Design Name:   SR_MOORE_BOARD_display
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Lab/Lab08/lab8/test.v
// Project Name:  lab8
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: SR_MOORE_BOARD_display
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module test;

	// Inputs
	reg osc;
	reg clk_aux;
	reg in;
	reg reset;

	// Outputs
	wire [2:0] past;
	wire out;
	wire [6:0] display;

	// Instantiate the Unit Under Test (UUT)
	SR_MOORE_BOARD_display uut (
		.osc(osc), 
		.clk_aux(clk_aux), 
		.in(in), 
		.reset(reset), 
		.past(past), 
		.out(out), 
		.display(display)
	);

	initial begin
		// Initialize Inputs
		osc = 0;
		clk_aux = 0;
		in = 0;
		reset = 0;

		// Wait 100 ns for global reset to finish
		#5; in=0; #20 in=1;
		#20; in=0; #20 in=1;
		#20; in=1; #20 in=1;
		#20; in=0; #20 in=1;
		#20; in=0; #20 in=0;
		#20; in=1; #20 in=0;
		#20; reset=1; #20 reset=0;
		#20; in=0; #20 in=1; #20 in=0;
	end
		always begin
			#1 osc=1;
			#1 osc=0;
		end
		
		always begin
			#15 clk_aux=1;
			#15 clk_aux=0;
		end

      
endmodule

