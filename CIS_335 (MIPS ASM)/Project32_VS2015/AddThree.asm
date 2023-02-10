TITLE MASM Template
INCLUDE Irvine32.inc

.data
a WORD 100
.code


main PROC
mov ebx, 30
push ebx
push 40
call Ex5Sub
call DumpRegs
INVOKE ExitProcess, 0

main ENDP

Ex5Sub PROC
pusha
mov ebx, 80
popa
ret
Ex5Sub ENDP

end main