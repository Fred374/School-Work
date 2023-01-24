#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#include "myshell.h"

void execute(char *path, char *buf, char *argv[]) {

	char *pathCopy = malloc(1024);
	char *fullPath = malloc(1024);
	char *pathPTR;
	pid_t pid;

	if ((pid = fork()) == -1) {
		perror("fork");
		exit(1);
	}
	if (0 == pid) {
		strcpy(pathCopy, path);
		pathPTR = strtok(pathCopy, ":");
		strcpy(fullPath, buf);
		while(access(fullPath, F_OK) == -1 && pathPTR != NULL) {
			strcpy(fullPath, pathPTR);
			strcat(fullPath, "/");
			strcat(fullPath, buf);
			pathPTR = strtok(NULL, ":");
			execv(fullPath, argv);
		}
		if(execv(fullPath, argv) == -1) {
			int errvalue = errno;
			printf( "%s\n", strerror( errvalue ) );
		}
		exit(0);
	}
	else {
		wait(0);
	}
	return;
}
