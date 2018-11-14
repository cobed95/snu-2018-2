`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    17:41:46 11/07/2018 
// Design Name: 
// Module Name:    RS_Latch_3 
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
module RS_Latch_3(
    input R0,
    input R1,
    input S,
    output Q,
    output Qn
    );
	 
assign Q = ~(R0 | R1 | Qn);
assign Qn = ~(S | Q);

endmodule
