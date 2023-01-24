#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#include "myshell.h"

int main(void) {
	int flag = 1;
	char *buf = malloc(1024);
	char *buf2 = malloc(1024);
	char *buf3 = malloc(1024);
	char *argv[10];
	char *temp = malloc(1024);
	char *temp2 = malloc(1024);
	char *path = malloc(1024);

	path[0] = '\0';

	while(flag) {
		int i = 1;
		printf("input here:");
		scanf("%1024[^\n]", buf);
		getchar();
		strcpy(buf3,buf);
		buf2 = strtok(buf, " ");
		buf2 = temp;
		//strcat(path, buf);
		argv[0] = path;
		while(argv[i-1] != NULL) {
			argv[i] = strtok(NULL, " ");
			i++;
		}
		if((strcmp(buf, "quit") == 0) || (strcmp(buf, "exit") == 0)) {
			flag = 0;
		}
		else if(strcmp(buf, "cd") == 0) {
        		if (chdir(argv[1]) == -1) {
        			printf("Change directory unsuccessful: %s not found.\n", argv[1]);
        		}
		}
		else if(strcmp(buf, "path") == 0) {

			if (argv[1] == NULL) {
				printf("%s\n", path);

			} else if (strcmp(argv[1], "+") == 0) {
			    if (path[0] != '\0') {
			        strcat(path, ":");
			    }
				strcat(path, argv[2]);
			} else if (strcmp(argv[1], "-") == 0) {
				path = pathminus(path, argv);
			} else {
				printf("Unrecognized arguments for path command.\n");
			}
		}
		else {
			if (strstr(buf3, "|") != NULL) {
				pipette(buf3, path);
			}
			else {
				execute(path, buf, argv);
			}
		}
	}
	return 0;
}
