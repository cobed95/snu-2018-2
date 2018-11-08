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
    output [6:0] display,
    output sign
    );

BinaryTo7Segment decoder(result[3:0], display);

always @ (*) 
begin
    if (result == 5'b10000) 
    begin
        sign <= 0;
    end
    else
    begin
        sign <= result[4];
    end
end

endmodule
