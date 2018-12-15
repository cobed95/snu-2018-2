`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    15:11:36 11/14/2018 
// Design Name: 
// Module Name:    Counter6Stages 
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
module CarSensor(
    input clk,
    input reset,
    input CAR_waiting,
    output reg out
    );

    always @ (posedge clk or posedge reset) 
    begin
        if (reset == 1'b1) out = 0;
        else out = CAR_waiting;
    end

endmodule
