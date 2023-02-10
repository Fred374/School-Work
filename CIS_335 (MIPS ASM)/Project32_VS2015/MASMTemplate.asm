TITLE MASM Template
INCLUDE Irvine32.inc

.data
myname BYTE "CIS 335 Fred Kelemen Group 18"

.code
main PROC

; to print out

mov edx, OFFSET myname
call WriteString

exit
main ENDP
END main