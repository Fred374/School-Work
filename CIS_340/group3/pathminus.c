#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#include "myshell.h"

char *pathminus(char *path, char *argv[]) {

	char *temp = malloc(1024);
	char *temp2 = malloc(1024);

	temp = strtok(path, ":");
	int flag2 = 1;

	while(flag2) {
		if(strcmp(temp, argv[2]) == 0) {
			temp = strtok(NULL, "");
			if (temp != NULL) {
				strcat(temp2, temp);
			} else if (strlen(temp2) > 1) {
				temp2[strlen(temp2)-1] = '\0';
			}
			strcpy(path, temp2);
			memset(temp2,'\0',1024*sizeof(char));
			flag2 = 0;
		} else {
			strcat(temp2, temp);
			strcat(temp2, ":");
			temp = strtok(NULL, ":");
		}
	}
	return path;
}
