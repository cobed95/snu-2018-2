`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    20:36:10 11/14/2018 
// Design Name: 
// Module Name:    Counter8bit_debouncer 
// Project Name: 
// Target Devices: 
// Tool versions: 
// Description: 
//
// Dependencies: 
//
// Revision: 
// Revision 0.01 - File Created
// Additional Comments: 
//
//////////////////////////////////////////////////////////////////////////////////
module Counter8bit_debouncer(
    input osc,
	 input clk,
    input load,
    input [7:0] load_value,
    input clear,
    output [6:0] display1,
    output [6:0] display2
    );
	 wire clk_de;
	 wire ripple;
	 wire [7:0] out_cnt;
	 Debouncer debouncer(osc,clk,clk_de);
	 
	 CatalogueCounter8Bit count(clk_de,1,1,load,load_value,clear,ripple,out_cnt);
	 segment x1(out_cnt[7:4],display1);
	 segment x2(out_cnt[3:0],display2); 
	 


endmodule
