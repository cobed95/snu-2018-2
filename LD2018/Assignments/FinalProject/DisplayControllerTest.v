`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   20:18:17 12/15/2018
// Design Name:   DisplayController
// Module Name:   /csehome/cobed95/snu-2018-2/LD2018/Assignments/FinalProject/DisplayControllerTest.v
// Project Name:  FinalProject
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: DisplayController
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module DisplayControllerTest;

	// Inputs
	reg clk;
	reg [2:0] flash;
	reg display_mode;
	reg [20:0] out_time;

	// Outputs
	wire [6:0] display6;
	wire [6:0] display5;
	wire [6:0] display4;
	wire [6:0] display3;
	wire [6:0] display2;
	wire [6:0] display1;

	// Instantiate the Unit Under Test (UUT)
	DisplayController uut (
		.clk(clk), 
		.flash(flash), 
		.display_mode(display_mode), 
		.out_time(out_time), 
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
		flash = 0;
		display_mode = 0;
		out_time = 0;

		// Wait 100 ns for global reset to finish
		#1;
		repeat(1000) begin
			out_time = out_time + 1;
			#20;
		end
        
		// Add stimulus here
		

	end
   always begin
		#0.1 clk = 1;
		#0.1 clk = 0;
	end
endmodule

