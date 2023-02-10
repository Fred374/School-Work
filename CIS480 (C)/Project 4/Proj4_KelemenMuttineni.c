#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>

// WB stage – WB_memout, WB_ALUOut, WB_RegRd, WB_ctrl
static int WB_memout, WB_ALUout, WB_RegRd, WB_ctrl, WB_wd;
// MEM stage – MEM_btgt, MEM_zero, MEM_ALUOut, MEM_rd2, MEM_RegRd, MEM_ctrl
static int MEM_btgt, MEM_zero, MEM_ALUout, MEM_rd2, MEM_RegRd, MEM_ctrl, MEM_wd, MEM_PCsrc, MEM_memout;
// EX stage – EX_pc4, EX_rd1, EX_rd2, EX_extend, EX_rt, EX_rt, EX_ctrl
static int EX_pc4, EX_rd1, EX_rd2, EX_extend, EX_rt, EX_rd, EX_ctrl, EX_btgt, EX_zero, EX_offset, EX_func, EX_RegRd, EX_ALUout;
// ID stage – ID_pc4, ID_inst
static int ID_pc4, ID_inst, ID_extend, ID_imm, ID_rt, ID_rd, ID_rd1, ID_rd2, ID_rs, ID_op, ID_ctrl;
// IF stage
static int IF_inst, IF_pc, IF_pc4, IF_pc_next;
FILE *fptr;
int reg[32];
int memory[256];
static int Pc = 512;

static int t = 0;

void initialize();
void carryout_operations();
void update_pipeline_registers();
void print_results();

void main(int argc, char *argv[]) {
	if (argv[1] == NULL) {
		printf("Argument invalid\n");
	}
	if (fptr <= 0) {
		if ((fopen(argv[1], "r")) > 0) {
			fptr = fopen(argv[1], "rb");
		} else printf("File not found\n");
	}
	initialize();
	Pc = 512; // equivalent to 0x200
	IF_pc = Pc;
	int cycles_to_halt = 1000; //maximum number of cycles to execute
	while (1) {
		carryout_operations();
		update_pipeline_registers();
		print_results();
		// The instruction word “0000 0000 0000 1100” (or 12) indicates HALT.
		// It does not stop immediately because there are four proceeding instructions in the pipeline
		// that have to be completed their execution. Thus, it needs to run 4 more cycles.
		if (IF_inst == 12) cycles_to_halt = 4;
		if (cycles_to_halt > 0) cycles_to_halt --;
		if (cycles_to_halt == 0) break;
	}
}

void initialize() {
	// Set up and initialize the register file array (e.g., register[32]) and the memory array (e.g. memory[256]).
	int num;
	// Copy the contents in the input binary file into the memory array.
	// Note that it is 128 data words and 128 instruction words, totaling 256 items in the memory array.
	// The data segment begins at memory[0] while the text segment begins at memory[128].
	for(int i=0; i<256; i++) {
		//fscanf(fptr,"%d",&num);
		fread(&num, sizeof(int),1,fptr);
		memory[i] = num;
	}
	// initialize all pipeline registers
	// Refer a figure in Section 4.1 of this document for signal names.
}


void carryout_operations() {
	// Carry out the operations in each stage and produce signals or update register/memory
	WB_wd = WB_RegRd;
	if ((WB_ctrl>>7)&1 == 1) {
		if (((WB_ctrl>>8)&1) == 0) {
			reg[WB_wd] = WB_memout;
			WB_memout = 0;
		} else if (((WB_ctrl>>8)&1) == 1) {
			reg[WB_wd] = WB_ALUout;
			WB_memout = 0;
		}
	} // WB stage – WB_wd must be updated;
	// Need to update a destination “register” if appropriate (WB_RegWrite)

	if ((((MEM_ctrl>>4)&1 == 1)&&(MEM_zero == 1))||(((MEM_ctrl>>1)&1 == 1)&&(MEM_zero == 0))) {
		MEM_PCsrc = 1;
	}
	if ((MEM_ctrl>>6)&1 == 1) {
		MEM_memout = memory[MEM_ALUout/4];
	}
	if ((MEM_ctrl>>5)&1 == 1) {
		memory[MEM_ALUout/4] = MEM_rd2;
	} // MEM stage – MEM_PCSrc and MEM_memout must be updated
	// Need to write memory if appropriate (MEM_MemWrite)
	// when accessing memory, be sure to divide the memory address by four
	// for example, if (MEM_MemRead) MEM_memout = memory[MEM_ALUOut/4];

	EX_offset = EX_extend<<2;
	EX_btgt = EX_pc4 + EX_offset;
	EX_func = EX_extend & 0x3f;
	if (((EX_rd1 - EX_rd2) == 0)&&(((EX_ctrl>>4)&1 == 1)||((EX_ctrl>>1)&1 == 1))) {
		EX_zero = 1;
	} else EX_zero = 0;
	if ((EX_ctrl>>10)&1 == 1) {
		EX_RegRd = EX_rd;
	} else EX_RegRd = EX_rt;
	if (EX_ctrl == 1416) { // R-type
		if (EX_func == 32) EX_ALUout = EX_rd1 + EX_rd2; // add
		else if (EX_func == 34) EX_ALUout = EX_rd1 - EX_rd2; // sub
		else if (EX_func == 42) {
			if (EX_rd1 < EX_rd2) {
				EX_ALUout = 1;
			} else EX_ALUout = 0;
		} // slt
		else if (EX_func == 12) EX_ALUout = 0; // halt
	} else if ((EX_ctrl == 20) || (EX_ctrl == 6)) EX_ALUout = 0; // branch
	else if ((EX_ctrl == 704)||(EX_ctrl == 544)||(EX_ctrl == 896)) EX_ALUout = EX_rd1 + (EX_extend&0xffff);
		// addi,lw,sw
	// EX stage – EX_offset, EX_btgt, EX_Zero, EX_funct, and EX_RegRd must be updated
	// EX_ALUOut must be updated too; See below for more information.
	// Refer a table in Section 4.2 of this document for the content of “ctrl” signals.
	// Refer a table in Section 3.1.3 of this document for the function code.
	// if (EX_ctrl==1416) { // R-type
	// if (EX_funct==32) EX_ALUOut = ; // add
	// else if (EX_funct==34) EX_ALUOut = ; // sub
	// else if (EX_funct==42) EX_ALUOut = ; // slt
	// else if (EX_funct==12) EX_ALUOut = 0; // halt=
	//}
	//else if ((EX_ctrl==20) || (EX_ctrl==6)) EX_ALUOut = 0; //beq or bne
	//else if ((EX_ctrl==704) || (EX_ctrl==544) || (EX_ctrl==896)) EX_ALUOut = ; //lw or sw or addi

	ID_op = ID_inst>>26;
	ID_rs = (ID_inst>>21)&0x1f;
	ID_rt = (ID_inst>>16)&0x1f;
	ID_rd = (ID_inst>>11)&0x1f;
	ID_rd1 = reg[ID_rs];
	ID_rd2 = reg[ID_rt];
	ID_imm = ID_inst&0xffff;
	if ((ID_imm>>15)&1 == 1) {
		ID_extend = 0xffff0000 + ID_imm;
	} else ID_extend = ID_imm;
	if (ID_op == 0) { // R-type
		ID_ctrl = 1416;
	} else if (ID_op == 8) { // addi
		ID_ctrl = 896;
	} else if (ID_op == -29) { // lw
		ID_ctrl = 704;
	} else if (ID_op == -21) { // sw
		ID_ctrl = 544;
	} else if (ID_op == 4) { // beq
		ID_ctrl = 20;
	} else if (ID_op == 5) { // bne
		ID_ctrl = 6;
	}
	// ID stage – ID_pc4 and ID_inst must be updated

	IF_pc4 = IF_pc + 4;
	if (MEM_PCsrc == 1) {
		IF_pc_next = MEM_btgt;
		//MEM_PCsrc = 0;
	} else IF_pc_next = IF_pc4;
	IF_inst = memory[IF_pc/4];
	Pc = IF_pc;
	IF_pc = IF_pc_next;
	// IF stage – IF_inst, IF_pc, IF_pc4, and IF_pc_next must be updated
	// Need to update “PC” too
}


void update_pipeline_registers() {
	// Update the pipeline registers
	// WB stage – WB_memout, WB_ALUOut, WB_RegRd, WB_ctrl
	WB_memout = MEM_memout;
	WB_ALUout = MEM_ALUout;
	WB_RegRd = MEM_RegRd;
	WB_ctrl = MEM_ctrl;
	// MEM stage – MEM_btgt, MEM_zero, MEM_ALUOut, MEM_rd2, MEM_RegRd, MEM_ctrl
	if (MEM_PCsrc == 1) {
		// WIPE EX
		EX_ctrl = 0;
		EX_rd1 = 0;
		EX_rd2 = 0;
		EX_extend = 0;
		EX_rt = 0;
		EX_rd = 0;
		EX_ctrl = 0;
		EX_btgt = 0;
		EX_zero = 0;
		EX_offset = 0;
		EX_func = 0;
		EX_RegRd = 0;
		EX_ALUout = 0;
		// WIPE ID
		ID_ctrl = 0;
		ID_inst = 0;
		ID_extend = 0;
		ID_imm = 0;
		ID_rt = 0;
		ID_rd = 0;
		ID_rd1 = 0;
		ID_rd2 = 0;
		ID_rs = 0;
		ID_op = 0;
		// WIPE IF
		IF_inst = 0;
		MEM_PCsrc = 0;
	}	// WIPE EX,ID,IF instructions
	MEM_btgt = EX_btgt;
	MEM_memout = 0;
	MEM_zero = EX_zero;
	MEM_ALUout = EX_ALUout;
	MEM_rd2 = EX_rd2;
	MEM_RegRd = EX_RegRd;
	MEM_ctrl = EX_ctrl;
	// EX stage – EX_pc4, EX_rd1, EX_rd2, EX_extend, EX_rt, EX_rt, EX_ctrl
	EX_pc4 = ID_pc4;
	EX_rd1 = ID_rd1;
	EX_rd2 = ID_rd2;
	EX_extend = ID_extend;
	EX_rt = ID_rt;
	EX_rd = ID_rd;
	EX_ctrl = ID_ctrl;
	// ID stage – ID_pc4, ID_inst
	ID_pc4 = IF_pc4;
	ID_inst = IF_inst;
	// your code
}

void print_results() {
	// print the content of PC, data memory, register file and pipeline registers as follows
	// (use hexadecimal for PC and pipeline registers; use decimal for memory and register contents)
	printf("PC = %x\n", Pc);//PC = 208

	t++;
	printf("\nREAL COUNT = %d\n\n", t);

	printf("DM %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d\n", memory[0],memory[1],
		memory[2],memory[3],memory[4],memory[5],memory[6],memory[7],memory[8],
		memory[9],memory[10],memory[11],memory[12],memory[13],memory[14],memory[15]);
		//DM 0 0 1 2 0 0 0 0 0 0 0 0 0 0 0 0
	printf("RegFile %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d\n", reg[0],reg[1],
		reg[2],reg[3],reg[4],reg[5],reg[6],reg[7],reg[8],reg[9],reg[10],reg[11],
		reg[12],reg[13],reg[14],reg[15]);
		//RegFile 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
	printf("IF/ID (pc4,inst) %x %08x\n", ID_pc4, ID_inst);//IF/ID (pc4, inst) 20c 8c250004
	printf("ID/EX (pc4, rd1, rd2, extend, rt, rd, ctrl) %x %x %x %x %x %x %x\n",
		EX_pc4, EX_rd1, EX_rd2, EX_extend, EX_rt, EX_rd, EX_ctrl);
		//ID/EX (pc4, rd1, rd2, extend, rt, rd, ctrl) 208 0 0 0 1 4 2c0
	printf("EX/MEM (btgt, zero, ALUout, rd2, RegRd, ctrl) %x %x %x %x %x %x\n",
		MEM_btgt, MEM_zero, MEM_ALUout, MEM_rd2, MEM_RegRd, MEM_ctrl);
		//EX/MEM (btgt, zero, ALUOut, rd2, RegRd, ctrl) 204 0 8 0 1 180
	printf("MEM/WB (memout, ALUout, RegRd, ctrl) %x %x %x %x\n", WB_memout,
		WB_ALUout, WB_RegRd, WB_ctrl);//MEM/WB (memout, ALUOut, RegRd, ctrl) 0 0 0 0
}
