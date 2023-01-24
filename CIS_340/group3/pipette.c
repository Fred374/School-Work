#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#include <fcntl.h>
#include "myshell.h"

void pipette(char *buf, char *path) {

	char *argv[10];
	char *buf2 = malloc(1024);
	char *buf3 = malloc(1024);
	char *cmds[10];
	int filedes[2];
	pid_t pid;
	int i = 1;
	int j = 1;
	argv[0] = malloc(1024);

	strcpy(buf2, buf);
	cmds[0] = strtok(buf2,"|");
	strcpy(argv[0], path);

	pipe(filedes);
	while ((cmds[i] = strtok(NULL,"|")) != NULL) {
		i++;
	}
	for (i = 0; i < 10; i++) {
		strcpy(buf3, cmds[i]);
		j = 1;
		strtok(buf3, " ");
		while (argv[j-1] != NULL) {
			argv[j] = strtok(NULL, " ");
			j++;
		}
		if (isspace(buf3[0])) {
			buf3 = buf3+1;
		}
		if (i == 0) { // first command
			if ((pid = fork()) == -1) {
				perror("fork");
				exit(1);
			}
			if (0 == pid) { // child
				close(1);
				dup(filedes[1]);
				close(filedes[0]);
				close(filedes[1]);
				execute(path, buf3, argv);
				exit(0);
			}
			else {wait(0);} // parent
		}
		else if (cmds[i+1] == NULL) { // last command
			i = 10;
			if ((pid = fork()) == -1) {
				perror("fork");
				exit(1);
			}
			if (pid == 0) { // child
				close(0);
				dup(filedes[0]);
				close(filedes[0]);
				close(filedes[1]);
				execute(path, buf3, argv);
				exit(0);
			}
			else {wait(0);} // parent
		}
		else { // middle commands
			if ((pid = fork()) == -1) {
				perror("fork");
				exit(1);
			}
			if (pid == 0) { // child
				close(0);
				dup(filedes[0]);
				close(1);
				dup(filedes[1]);
				close(filedes[0]);
				close(filedes[1]);
				execute(path, buf3, argv);
				exit(0);
			}
			else {wait(0);} // parent
		}
	}
	return;
}
