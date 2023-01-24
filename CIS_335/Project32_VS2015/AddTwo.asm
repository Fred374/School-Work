TITLE MASM Template
INCLUDE Irvine32.inc

.data
a WORD 10
.code


main PROC
mov eax, 30

INVOKE ExitProcess, 0

main ENDP

end main