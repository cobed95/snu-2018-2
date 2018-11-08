`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    19:48:25 11/07/2018 
// Design Name: 
// Module Name:    DisplayController 
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
module DisplayController(
    input [4:0] result,
    output reg [6:0] display,
    output reg sign
    );
wire [6:0] displayResult;

BinaryTo7Segment decoder(
	.binary(result[3:0]),
	.display(displayResult)
);

always @ (*) 
begin
    if (result == 5'b10000) 
    begin
        sign <= 0;
		  display <= displayResult;
    end
    else
    begin
        sign <= result[4];
		  display <= displayResult;
    end
end

endmodule
