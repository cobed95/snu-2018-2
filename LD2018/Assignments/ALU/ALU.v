`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    21:16:47 11/08/2018 
// Design Name: 
// Module Name:    ALU 
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
module ALU(
    input sw1,
    input sw2,
    input sw3,
    input sw4,
    input sw5,
    input sw6,
    input [9:0] operands,
    output reg [6:0] display0,
    output reg [6:0] display1,
    output reg [6:0] display2,
    output reg [6:0] display3,
    output reg [6:0] display4,
    output reg [6:0] display5
    );
wire [41:0] result0;
Operation0 operation0 (
	.operands(operands),
	.display0(result0[6:0]),
	.display1(result0[13:7]),
	.display2(result0[20:14]),
	.display3(result0[27:21]),
	.display4(result0[34:28]),
	.display5(result0[41:35])
);

wire [41:0] result1;
Operation1 operation1 (
	.operand(operands[5:0]),
	.display0(result1[6:0]),
	.display1(result1[13:7]),
	.display2(result1[20:14]),
	.display3(result1[27:21]),
	.display4(result1[34:28]),
	.display5(result1[41:35])
);

wire [41:0] result2;
Operation2 operation2 (
	.operands(operands),
	.display0(result2[6:0]),
	.display1(result2[13:7]),
	.display2(result2[20:14]),
	.display3(result2[27:21]),
	.display4(result2[34:28]),
	.display5(result2[41:35])
);

wire [41:0] result3;
Operation3 operation3 (
	.operands(operands),
	.display0(result3[6:0]),
	.display1(result3[13:7]),
	.display2(result3[20:14]),
	.display3(result3[27:21]),
	.display4(result3[34:28]),
	.display5(result3[41:35])
);

wire [41:0] result4;
Operation4 operation4 (
	.operand(operands[5:0]),
	.display0(result4[6:0]),
	.display1(result4[13:7]),
	.display2(result4[20:14]),
	.display3(result4[27:21]),
	.display4(result4[34:28]),
	.display5(result4[41:35])
);

wire [41:0] result5;
Operation5 operation5 (
	.operands(operands),
	.display0(result5[6:0]),
	.display1(result5[13:7]),
	.display2(result5[20:14]),
	.display3(result5[27:21]),
	.display4(result5[34:28]),
	.display5(result5[41:35])
);

wire [41:0] result6;
Operation6 operation6 (
	.operand(operands[5:0]),
	.display0(result1[6:0]),
	.display1(result1[13:7]),
	.display2(result1[20:14]),
	.display3(result1[27:21]),
	.display4(result1[34:28]),
	.display5(result1[41:35])
);

always @ (*) 
begin
	if (sw1 == 1) 
	begin
		display0 <= result1[6:0];
		display1 <= result1[13:7];
		display2 <= result1[20:14];
		display3 <= result1[27:21];
		display4 <= result1[34:28];
		display5 <= result1[41:35];
	end
	else if (sw2 == 1)
	begin
		display0 <= result2[6:0];
		display1 <= result2[13:7];
		display2 <= result2[20:14];
		display3 <= result2[27:21];
		display4 <= result2[34:28];
		display5 <= result2[41:35];
	end
	else if (sw3 == 1)
	begin
		display0 <= result3[6:0];
		display1 <= result3[13:7];
		display2 <= result3[20:14];
		display3 <= result3[27:21];
		display4 <= result3[34:28];
		display5 <= result3[41:35];
	end
	else if (sw6 == 1)
	begin
		display0 <= result6[6:0];
		display1 <= result6[13:7];
		display2 <= result6[20:14];
		display3 <= result6[27:21];
		display4 <= result6[34:28];
		display5 <= result6[41:35];
	end
	else if (sw5 == 1)
	begin
		display0 <= result5[6:0];
		display1 <= result5[13:7];
		display2 <= result5[20:14];
		display3 <= result5[27:21];
		display4 <= result5[34:28];
		display5 <= result5[41:35];
	end
	else if (sw4 == 1)
	begin
		display0 <= result4[6:0];
		display1 <= result4[13:7];
		display2 <= result4[20:14];
		display3 <= result4[27:21];
		display4 <= result4[34:28];
		display5 <= result4[41:35];
	end
	else
	begin
		display0 <= result0[6:0];
		display1 <= result0[13:7];
		display2 <= result0[20:14];
		display3 <= result0[27:21];
		display4 <= result0[34:28];
		display5 <= result0[41:35];
	end
end

endmodule
