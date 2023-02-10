#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>

struct textInfo{
	int key;
	char name[15];
};

FILE *mount(FILE *fd, char *arg) {

	if (arg == NULL) {
		printf("Argument invalid\n");
	}
	if (fd <= 0) {
		if ((fopen(arg, "r")) > 0) {
			fd = fopen(arg, "r");
		}
		else {printf("File not found\n");}
	}
	return fd;
}

int addsub(char *buf) {
	int instruction = 0;
	char *buf2 = malloc(1024);
	int reg = 0;
	buf2 = strtok(buf,"\t");
	for(int i=3;i>0;i--) {
		buf2 = strtok(NULL,",");
		buf2 = buf2+1;
		reg = atoi(buf2);
		if (i == 3) {
			reg=reg<<11;
		} else if (i == 2) {
			reg=reg<<21;
		} else if (i == 1) {
			reg=reg<<16;
		}
		instruction += reg;
	}
	return instruction;

}

int shift(char *buf) {
	int instruction = 0;
	char *buf2 = malloc(1024);
	int reg = 0;
	buf2 = strtok(buf,"\t");
	for(int i=3;i>0;i--) {
		buf2 = strtok(NULL,",");
		buf2 = buf2+1;
		reg = atoi(buf2);
		if (i == 3) {
			reg=reg<<11;
		} else if (i == 2) {
			reg=reg<<16;
		} else if (i == 1) {
			reg=reg<<6;
		}
		instruction += reg;
	}
	return instruction;

}

int itype(char *buf) {
	int instruction = 0;
	char *buf2 = malloc(1024);
	int reg = 0;
	buf2 = strtok(buf,"\t");
	for(int i=2;i>0;i--) {
		buf2 = strtok(NULL,",");
		buf2 = buf2+1;
		reg = atoi(buf2);
		if (i == 2) {
			reg=reg<<21;
		} else if (i == 1) {
			reg=reg<<16;
		}
		instruction += reg;
	}
	buf2 = strtok(NULL, ",");
	reg = atoi(buf2);
	instruction += reg;
	return instruction;

}

int lwsw(char *buf) {
	int instruction = 0;
	char *buf2 = malloc(1024);
	int reg = 0;
	buf2 = strtok(buf,"\t");
	buf2 = strtok(NULL,",");
	buf2 = buf2+1;
	reg = atoi(buf2);
	reg = reg<<16;
	instruction += reg;
	buf2 = strtok(NULL, "(");
	reg = atoi(buf2);
	instruction += reg;
	buf2 = strtok(NULL, ")");
	buf2 = buf2+1;
	reg = atoi(buf2);
	reg = reg<<21;
	instruction += reg;
	return instruction;

}

int lui(char *buf) {
	int instruction = 0;
	char *buf2 = malloc(1024);
	int reg = 0;
	buf2 = strtok(buf,"\t");
	buf2 = strtok(NULL,",");
	buf2 = buf2+1;
	reg = atoi(buf2);
	reg=reg<<16;
	instruction += reg;
	buf2 = strtok(NULL, ",");
	reg = atoi(buf2);
	instruction += reg;
	return instruction;

}

int jtype(char *buf) {
	int instruction = 0;
	char *buf2 = malloc(1024);
	int reg = 0;
	buf2 = strtok(buf,"\t");
	buf2 = strtok(NULL,",");
	buf2 = buf2+1;
	reg = atoi(buf2);
	instruction += reg;
	return instruction;

}

int la(char *buf, FILE *fd2, struct textInfo *tI) {
	int instruction = 15<<26, instruction2 = 13<<26;
	char *buf2 = malloc(1024);
	int reg = 0, regb = 0;
	buf2 = strtok(buf,"\t");
	buf2 = strtok(NULL,",");
	buf2 = buf2+1;
	reg = atoi(buf2);
	reg = reg<<16;
	instruction += reg;
	instruction2 += reg;
	reg = reg>>16;
	reg = reg<<21;
	instruction2 += reg;
	buf2 = strtok(NULL,",");
	for(int i=0; i<128; i++) {
		if (strcmp(buf2,(tI+i*sizeof(struct textInfo))->name) == 0) {
			reg = (tI+i*sizeof(struct textInfo))->key;
			i = 128;
			regb = reg>>4;
			regb = regb<<4;
			instruction2 = instruction2 + reg - regb;
			instruction = instruction + regb;
		}
	}
	fwrite(&instruction,sizeof(instruction),1,fd2);
	return instruction2;
}


void main(int argc, char *argv[]) {
	FILE *fd = 0, *fd2;
	char *buf = malloc(1024);
	char *buf2;
	int i = 0, reg = 0, regb = 0, j = 0, counter = 1, tICount = 0;
	umask(0);
	fd2 = fopen("file.out", "w+");
	fd = mount(fd, argv[1]);
	struct textInfo *tI = (struct textInfo*)malloc(128*sizeof(struct textInfo));
	while (counter != 0) {
		if (fscanf(fd,"%[^\n]s",buf) == EOF) {
			rewind(fd);
		} else {
			fgetc(fd);
			if (strcmp(buf, "\t.data") == 0) {
				for (i = 0; i < 128; i++) {
					buf2 = malloc(1024);
					fscanf(fd,"%[^\n]s",buf);
					fgetc(fd);
					if(strcmp(buf,"\t.text") != 0) {
						strcpy(buf2,buf);
						buf2 = strtok(buf2,"\t");
						if (strstr(buf2,":") != NULL) {
							buf2[strlen(buf2)-1] = '\0';
							(tI+tICount*sizeof(struct textInfo))->key = i*4;
							strcpy((tI+tICount*sizeof(struct textInfo))->name,buf2);
							buf2 = strtok(NULL,"\t");
							tICount++;
						}
						if (strcmp(buf2,".space") == 0) {
							buf2 = strtok(NULL,"\t");
							reg = atoi(buf2);
							reg = (reg+3)/4;
							for (int k=0; k<reg; k++) {
								fwrite(&regb,sizeof(regb),1,fd2);
							}
						} else if (strcmp(buf2,".word") == 0) {
							buf2 = strtok(NULL,",");
							while (buf2 != NULL) {
								reg = atoi(buf2);
								fwrite(&reg,sizeof(reg),1,fd2);
								buf2 = strtok(NULL,",");
							}
						}
					} else {
						for (i; i<128; i++) {
							reg = 0;
							fwrite(&reg,sizeof(reg),1,fd2);
						}
						i = 128;
					}
				}
			} if (strcmp(buf, "\t.text") == 0 && (i>0 || counter>1)) {
				for (i = 0; i < 128; i++) {
					buf2 = malloc(1024);
					if (fscanf(fd,"%[^\n]s",buf) == EOF) {
						for (j = 127; j>i; j--) {
							reg = 0;
							fwrite(&reg,sizeof(reg),1,fd2);
						}
						i = 128;
					} else {
						strcpy(buf2,buf);
						fgetc(fd);
						buf2 = strtok(buf2,"\t");
						if (strstr(buf,":") != NULL) {
							buf2 = strtok(buf2,":");
							(tI+tICount*sizeof(struct textInfo))->key = 512+i*4;
							strcpy((tI+tICount*sizeof(struct textInfo))->name,buf2);
							tICount++;
							buf=buf+strlen(buf2)+1;
							strcpy(buf2,buf);
							buf2 = strtok(buf2,"\t");
						}
						if (strcmp(buf2,"add")==0) {
							reg = 0;
							reg = reg+addsub(buf);
							reg += 2<<4;
						}
						if (strcmp(buf2,"sub")==0) {
							reg = 0;
							reg = reg+addsub(buf);
							reg += 2<<4+2;
						}else if (strcmp(buf2, "sll") == 0) {
							reg = 0;
							reg = reg+shift(buf);
							reg += 2<<4+2;
						}else if (strcmp(buf2, "srl") == 0) {
							reg = 0;
							reg = reg+shift(buf);
							reg += 2<<4+2;
						}else if (strcmp(buf2, "slt") == 0) {
							reg = 0;
							reg = reg+addsub(buf);
							reg += 2<<4+2;
						}else if (strcmp(buf2, "addi") == 0) {
							reg = 1<<30;
							reg = reg+itype(buf);
						}else if (strcmp(buf2, "lui") == 0) {
							reg = 15<<26;
							reg = reg+lui(buf);
						}else if (strcmp(buf2, "ori") == 0) {
							reg = 13<<26;
							reg = reg+itype(buf);
						}else if (strcmp(buf2, "lw") == 0) {
							reg =35<<26;
							reg = reg+lwsw(buf);
						}else if (strcmp(buf2, "sw") == 0) {
							reg = 43<<26;
							reg = reg+lwsw(buf);
						}else if (strcmp(buf2, "beq") == 0) {
							reg = 8<<26;
							reg = reg+itype(buf);
						}else if (strcmp(buf2, "bne") == 0) {
							reg = 9<<26;
							reg = reg+itype(buf);
						}else if (strcmp(buf2, "j") == 0) {
							reg = 2<<26;
							reg = reg+jtype(buf);
						}else if (strcmp(buf2, "la") == 0) {
							reg = la(buf,fd2,tI);
							i++;
						}
						fwrite(&reg,sizeof(reg),1,fd2);
						//printf("%d\n",reg);
					}
				}
			counter = -1;
			}
		}
	counter++;
	}
	fclose(fd);
	fclose(fd2);
}
