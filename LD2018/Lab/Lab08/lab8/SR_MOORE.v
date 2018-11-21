`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    18:38:33 11/21/2018 
// Design Name: 
// Module Name:    SR_MOORE 
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
module SR_MOORE(
    input clk,
    input reset,
    input in,
    output reg out
    );
	 parameter S0 = 3'b000, S1 = 3'b001, S2 = 3'b010, S3 = 3'b011, S4 = 3'b100, EXIT=3'b101;

    reg [2:0] state, next;
    
    always @ (in or state)
    begin
        case (state)
            S0: if (in == 1'b1) next = S1;
                else next = S0;
            S1: if (in == 1'b1) next = S3;
                else next= S2;
            S2: if (in == 1'b1) next = S1; 
                else next = EXIT; 
            S3: if (in == 1'b1) next = S3; 
                else next = S4;
				S4: if(in==1'b1) next = S1;
					 else next = EXIT;
            EXIT: next = EXIT; 
        endcase
    end

    always @ (posedge clk or posedge reset)
    begin
        if (reset) state <= S0;
        else state <= next;
    end
	 
	 always @ (state)
	 begin
		case(state)
		S0: out=0;
		S1: out=0;
		S2: out=1;
		S3: out=0;
		S4: out=0;
		EXIT: out=0;
		endcase
	end

endmodule
