TITLE MASM Template
INCLUDE Irvine32.inc

.data
darray SDWORD 50 DUP(RANDOM32)
count BYTE 0
.code
main proc
mov ecx, 50

L1:
mov eax,[darray + ecx - 1]
JS signCount
call WriteInt
call Crlf
LOOP L1

signCount:
inc count

movzx eax, count
call WriteDec
call Crlf

exit
main endp
end main