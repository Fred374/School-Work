TITLE MASM Template
INCLUDE Irvine32.inc

.data
darray SDWORD 10,60,20,33,72,89,45,65,72,12
sample SDWORD 50
sum SDWORD 0
count BYTE 0

.code
main proc
mov ecx, 10
mov eax, 0
mov edi, 0

;L1:
;mov eax,[darray + edi]
;call WriteInt
;call Crlf
;add edi,TYPE darray
;LOOP L1

.while ecx > 0
mov eax,[darray +edi]
add edi, TYPE darray
.if eax < sample
add sum,eax
.endif
dec ecx
.endw

mov eax,sum
call WriteInt
call Crlf

exit
main endp
end main