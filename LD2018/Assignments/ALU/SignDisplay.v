`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    13:54:53 11/07/2018 
// Design Name: 
// Module Name:    SignDisplay 
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
module SignDisplay(
    input sign,
    output reg [6:0] display
    );

always @ (*)
begin
	case (sign)
		1: display = 7'b0000001;
		default: display = 7'b0000000;
	endcase
end

endmodule
