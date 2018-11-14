`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    17:27:00 11/07/2018 
// Design Name: 
// Module Name:    RS_Latch 
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
module RS_Latch(
    input R,
    input S,
    output Q,
    output Qn
    );

assign Q = ~ (R | Qn);
assign Qn = ~ (S | Q);

endmodule
