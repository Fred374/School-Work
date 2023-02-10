// This section actually performs the actions.

// Frederick Kelemen 2715308
// 03/10/2021

// This file contains the functions called by the main
// method. These function are set to perform a specific
// action.

#include "output.h"
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

void func1() { // This function closes the whole hand
	pid_t pid;
	char *buf = malloc(50);
	char *path = malloc(50);
	for (int i = 0; i < 7; i++) {
		if ((pid = fork()) == -1) {
			perror("fork");
			exit(1);
		} else if (pid == 0) {
			sprintf(buf,"%d",i);
			strcat(path, "servo");
			strcat(path, buf);
			strcat(path, "/servoclose.py");
			execlp("python3","python3",path,(char *)NULL);
			exit(1);
		} else {
			wait(0);
		}
	}
}

void func2() { // This function opens the whole hand
	pid_t pid;
	char *buf = malloc(50);
	char *path = malloc(50);
	for (int i = 0; i < 7; i++) {
		if ((pid = fork()) == -1) {
			perror("fork");
			exit(1);
		} else if (pid == 0) {
			sprintf(buf,"%d",i);
			strcat(path, "servo");
			strcat(path, buf);
			strcat(path, "/servoopen.py");
			execlp("python3","python3",path,(char *)NULL);
			exit(1);
		} else {
			wait(0);
		}
	}
}

void func3() { // This function points with the index finger #NOTE ONLY USE AFTER func1 or func4
	execlp("python3","python3","./servo0/servoopen.py",(char *)NULL);
}

void func4() { // This function closes the index finger #NOTE ONLY USE AFTER func2 or func3
	execlp("python3","python3","./servo0/servoclose.py",(char *)NULL);
}
