LIBRARY ieee;
USE ieee.std_logic_1164.ALL;


ENTITY inv_s_box IS
PORT(
    fs_box_in    :	IN  std_logic_vector(7 downto 0);  --forward s_box
    fs_box_out   :	OUT std_logic_vector(7 downto 0)
    );
END inv_s_box;

ARCHITECTURE beh OF inv_s_box IS
    
BEGIN

-- see the Federal Information Processing Standards Publication197 November 26 2001
-- to understand this table.

WITH fs_box_in(7 downto 0) SELECT
    
fs_box_out(7 downto 0) <= 
		    --first row
		    "01100010" WHEN "00000000", --(X"52")
		    "00001001" WHEN "00000001", --(X"09") 
		    "01101010" WHEN "00000010", --(X"6a")
		    "11010101" WHEN "00000011", --(X"D5")
		    "00110000" WHEN "00000100", --(X"30")
		    "00110110" WHEN "00000101", --(X"36")
		    "10100101" WHEN "00000110", --(X"A5") 
		    "00111000" WHEN "00000111", --(X"38") 
		    "10111111" WHEN "00001000", --(X"BF") 
		    "01000000" WHEN "00001001", --(X"40") 
		    "10100011" WHEN "00001010", --(X"A3") 
		    "10011110" WHEN "00001011", --(X"9E") 
		    "10000001" WHEN "00001100", --(X"81") 
		    "11110011" WHEN "00001101", --(X"F3") 
		    "11010111" WHEN "00001110", --(X"D7") 
		    "11111011" WHEN "00001111", --(X"FB") 
		    --second row
		    "01111100" WHEN "00010000", --(X"7C") 
		    "11100011" WHEN "00010001", --(X"E3")
		    "00111001" WHEN "00010010", --(X"39")
		    "10000010" WHEN "00010011", --(X"82")
		    "10011011" WHEN "00010100", --(X"9B")
		    "00101111" WHEN "00010101", --(X"2F")
		    "11111111" WHEN "00010110", --(X"FF")
		    "10000111" WHEN "00010111", --(X"87")
		    "00110100" WHEN "00011000", --(X"34")
		    "10001110" WHEN "00011001", --(X"8E")
		    "01000011" WHEN "00011010", --(X"43")
		    "01000100" WHEN "00011011", --(X"44")
		    "11000100" WHEN "00011100", --(X"C4")
		    "11011110" WHEN "00011101", --(X"DE")
		    "11101001" WHEN "00011110", --(X"E9")
		    "11001011" WHEN "00011111", --(X"CB")
		    --third row
		    "01010100" WHEN "00100000", --(X"54")
		    "01111011" WHEN "00100001", --(X"7B")
		    "10010100" WHEN "00100010", --(X"94")
		    "00110010" WHEN "00100011", --(X"32")
		    "10100110" WHEN "00100100", --(X"A6")
		    "11000010" WHEN "00100101", --(X"C2")
		    "00100011" WHEN "00100110", --(X"23")
		    "00111101" WHEN "00100111", --(X"3D")
		    "11101110" WHEN "00101000", --(X"EE")
		    "01001100" WHEN "00101001", --(X"4C")
		    "10010101" WHEN "00101010", --(X"95")
		    "00001011" WHEN "00101011", --(X"0B")
		    "01000010" WHEN "00101100", --(X"42")
		    "11111010" WHEN "00101101", --(X"FA")
		    "11000011" WHEN "00101110", --(X"C3")
		    "01001110" WHEN "00101111", --(X"4E")
		    --forth row
		    "00001000" WHEN "00110000", --(X"08")
		    "00101110" WHEN "00110001", --(X"2E")
		    "10100001" WHEN "00110010", --(X"A1")
		    "01100110" WHEN "00110011", --(X"66")
		    "00101000" WHEN "00110100", --(X"28")
		    "11011001" WHEN "00110101", --(X"D9")
		    "00100100" WHEN "00110110", --(X"24")
		    "10110010" WHEN "00110111", --(X"B2")
		    "01110110" WHEN "00111000", --(X"76")
		    "01011011" WHEN "00111001", --(X"5B")
		    "10100010" WHEN "00111010", --(X"A2")
		    "01001001" WHEN "00111011", --(X"49")
		    "01101101" WHEN "00111100", --(X"6D")
		    "10001011" WHEN "00111101", --(X"8B")
		    "11010001" WHEN "00111110", --(X"D1")
		    "00100101" WHEN "00111111", --(X"25")
		    --fifth row
		    "01110010" WHEN "01000000", --(X"72") 
		    "11111000" WHEN "01000001", --(X"F8") 
		    "11110110" WHEN "01000010", --(X"F6") 
		    "01100100" WHEN "01000011", --(X"64") 
		    "10000110" WHEN "01000100", --(X"86") 
		    "01101000" WHEN "01000101", --(X"68") 
		    "10011000" WHEN "01000110", --(X"98") 
		    "00010110" WHEN "01000111", --(X"16") 
		    "11010100" WHEN "01001000", --(X"D4") 
		    "10100100" WHEN "01001001", --(X"A4") 
		    "01011100" WHEN "01001010", --(X"5C") 
		    "11001100" WHEN "01001011", --(X"CC") 
		    "01011101" WHEN "01001100", --(X"5D") 
		    "01100101" WHEN "01001101", --(X"65") 
		    "10110110" WHEN "01001110", --(X"B6") 
		    "10010010" WHEN "01001111", --(X"92") 
		    --sixth row
		     "01101100" WHEN "01010000", --(X"6C")
			 "01110000" WHEN "01010001", --(X"70")
			 "01001000" WHEN "01010010", --(X"48")
			 "01010000" WHEN "01010011", --(X"50")
			 "11111101" WHEN "01010100", --(X"FD")
			 "11101101" WHEN "01010101", --(X"ED")
			 "10111001" WHEN "01010110", --(X"B9")
			 "11011010" WHEN "01010111", --(X"DA")
			 "01011110" WHEN "01011000", --(X"5E")
			 "00010101" WHEN "01011001", --(X"15")
			 "01000110" WHEN "01011010", --(X"46")
			 "01010111" WHEN "01011011", --(X"57")
			 "10100111" WHEN "01011100", --(X"A7")
			 "10001101" WHEN "01011101", --(X"8D")
			 "10011101" WHEN "01011110", --(X"9D")
			 "10000100" WHEN "01011111", --(X"84")

		    --seventh row
		    "10010000" WHEN "01100000", --(X"90")
		    "11011000" WHEN "01100001", --(X"D8")
		    "10101011" WHEN "01100010", --(X"AB")
		    "00000000" WHEN "01100011", --(X"00")
		    "10001100" WHEN "01100100", --(X"8C")
		    "10111100" WHEN "01100101", --(X"BC")
		    "11010011" WHEN "01100110", --(X"D3")
		    "00001010" WHEN "01100111", --(X"0A")
		    "11110111" WHEN "01101000", --(X"F7")
		    "11100100" WHEN "01101001", --(X"E4")
		    "01011000" WHEN "01101010", --(X"58")
		    "00000101" WHEN "01101011", --(X"05")
		    "10111000" WHEN "01101100", --(X"B8")
		    "10110011" WHEN "01101101", --(X"B3")
		    "01000101" WHEN "01101110", --(X"45")
		    "00000110" WHEN "01101111", --(X"06")
		    --eighth row
		    "11010000" WHEN "01110000", --(X"D0")
		    "00101100" WHEN "01110001", --(X"2C")
		    "00011110" WHEN "01110010", --(X"1E")
		    "10001111" WHEN "01110011", --(X"8F")
		    "11001010" WHEN "01110100", --(X"CA")
		    "00111111" WHEN "01110101", --(X"3F")
		    "00001111" WHEN "01110110", --(X"0F")
		    "00000010" WHEN "01110111", --(X"02")
		    "11000001" WHEN "01111000", --(X"C1")
		    "10101111" WHEN "01111001", --(X"AF")
		    "10111101" WHEN "01111010", --(X"BD")
		    "00000011" WHEN "01111011", --(X"03")
		    "00000001" WHEN "01111100", --(X"01")
		    "00010011" WHEN "01111101", --(X"13")
		    "10001010" WHEN "01111110", --(X"8A")
		    "01101011" WHEN "01111111", --(X"6B")
		    --ninth row
		    "00111010" WHEN "10000000", --(X"3A") 
		    "10010001" WHEN "10000001", --(X"91") 
		    "00010001" WHEN "10000010", --(X"11") 
		    "01000001" WHEN "10000011", --(X"41") 
		    "01001111" WHEN "10000100", --(X"4F") 
		    "01100111" WHEN "10000101", --(X"67") 
		    "11011100" WHEN "10000110", --(X"DC") 
		    "11101010" WHEN "10000111", --(X"EA") 
		    "10010111" WHEN "10001000", --(X"97") 
		    "11110010" WHEN "10001001", --(X"F2") 
		    "11001111" WHEN "10001010", --(X"CF") 
		    "11001110" WHEN "10001011", --(X"CE") 
		    "11110000" WHEN "10001100", --(X"F0") 
		    "10110100" WHEN "10001101", --(X"B4") 
		    "11100110" WHEN "10001110", --(X"E6") 
		    "01110011" WHEN "10001111", --(X"73") 
		    --tenth row
		    "10010110" WHEN "10010000", --(X"96")
		    "10101100" WHEN "10010001", --(X"AC")
		    "01110100" WHEN "10010010", --(X"74")
		    "00100010" WHEN "10010011", --(X"22")
		    "11100111" WHEN "10010100", --(X"E7")
		    "10101101" WHEN "10010101", --(X"AD")
		    "00110101" WHEN "10010110", --(X"35")
		    "10000101" WHEN "10010111", --(X"85")
		    "11100010" WHEN "10011000", --(X"E2")
		    "11111001" WHEN "10011001", --(X"F9")
		    "00110111" WHEN "10011010", --(X"37")
		    "11101000" WHEN "10011011", --(X"E8")
		    "00011100" WHEN "10011100", --(X"1C")
		    "01110101" WHEN "10011101", --(X"75")
		    "11011111" WHEN "10011110", --(X"DF")
		    "01101110" WHEN "10011111", --(X"6E")
		    --eleventh row
		    "01000111" WHEN "10100000", --(X"47") 
		    "11110001" WHEN "10100001", --(X"F1") 
		    "00011010" WHEN "10100010", --(X"1A") 
		    "01110001" WHEN "10100011", --(X"71") 
		    "00011101" WHEN "10100100", --(X"1D") 
		    "00101001" WHEN "10100101", --(X"29") 
		    "11000101" WHEN "10100110", --(X"C5") 
		    "10001001" WHEN "10100111", --(X"89") 
		    "01101111" WHEN "10101000", --(X"6F") 
		    "10110111" WHEN "10101001", --(X"B7") 
		    "01100010" WHEN "10101010", --(X"62") 
		    "00001110" WHEN "10101011", --(X"0E") 
		    "10101010" WHEN "10101100", --(X"AA") 
		    "00011000" WHEN "10101101", --(X"18") 
		    "10111110" WHEN "10101110", --(X"BE") 
		    "00011011" WHEN "10101111", --(X"1B") 
		    --twelveth row
		    "11111100" WHEN "10110000", --(X"FC")
		    "01010110" WHEN "10110001", --(X"56")
		    "00111110" WHEN "10110010", --(X"3E")
		    "01001011" WHEN "10110011", --(X"4B")
		    "11000110" WHEN "10110100", --(X"C6")
		    "11010010" WHEN "10110101", --(X"D2")
		    "01111001" WHEN "10110110", --(X"79")
		    "00100000" WHEN "10110111", --(X"20")
		    "10011010" WHEN "10111000", --(X"9A")
		    "11011011" WHEN "10111001", --(X"DB")
		    "11000000" WHEN "10111010", --(X"C0")
		    "11111110" WHEN "10111011", --(X"FE")
		    "01111000" WHEN "10111100", --(X"78")
		    "11001101" WHEN "10111101", --(X"CD")
		    "01011010" WHEN "10111110", --(X"5A")
		    "11110100" WHEN "10111111", --(X"F4")
		    --thirteenth row
		    "00011111" WHEN "11000000", --(X"1F") 
		    "11011101" WHEN "11000001", --(X"DD") 
		    "10101000" WHEN "11000010", --(X"A8") 
		    "00110011" WHEN "11000011", --(X"33") 
		    "10001000" WHEN "11000100", --(X"88")
		    "00000111" WHEN "11000101", --(X"07") 
		    "11000111" WHEN "11000110", --(X"C7") 
		    "00110001" WHEN "11000111", --(X"31") 
		    "10110001" WHEN "11001000", --(X"B1") 
		    "00010010" WHEN "11001001", --(X"12") 
		    "00010000" WHEN "11001010", --(X"10") 
		    "01011001" WHEN "11001011", --(X"59") 
		    "00100111" WHEN "11001100", --(X"27") 
		    "10000000" WHEN "11001101", --(X"80") 
		    "11101100" WHEN "11001110", --(X"EC") 
		    "01011111" WHEN "11001111", --(X"5F") 
 		    --forteenth row
		    "01100000" WHEN "11010000", --(X"60") 
		    "01010001" WHEN "11010001", --(X"51") 
		    "01111111" WHEN "11010010", --(X"7F") 
		    "10101001" WHEN "11010011", --(X"A9") 
		    "00011001" WHEN "11010100", --(X"19") 
		    "10110101" WHEN "11010101", --(X"B5") 
		    "01001010" WHEN "11010110", --(X"4A") 
		    "00001101" WHEN "11010111", --(X"0D") 
		    "00101101" WHEN "11011000", --(X"2D") 
		    "11100101" WHEN "11011001", --(X"E5") 
		    "01111010" WHEN "11011010", --(X"7A") 
		    "10011111" WHEN "11011011", --(X"9F") 
		    "10010011" WHEN "11011100", --(X"93") 
		    "11001001" WHEN "11011101", --(X"C9") 
		    "10011100" WHEN "11011110", --(X"9C") 
		    "11101111" WHEN "11011111", --(X"EF") 
		    --fifteenth row
		    "10100000" WHEN "11100000", --(X"A0") 
		    "11100000" WHEN "11100001", --(X"E0") 
		    "00111011" WHEN "11100010", --(X"3B") 
		    "01001101" WHEN "11100011", --(X"4D") 
		    "10101110" WHEN "11100100", --(X"AE") 
		    "00101010" WHEN "11100101", --(X"2A") 
		    "11110101" WHEN "11100110", --(X"F5") 
		    "10110000" WHEN "11100111", --(X"B0") 
		    "11001000" WHEN "11101000", --(X"C8") 
		    "11101011" WHEN "11101001", --(X"EB") 
		    "10111011" WHEN "11101010", --(X"BB") 
		    "00111100" WHEN "11101011", --(X"3C") 
		    "10000011" WHEN "11101100", --(X"83") 
		    "01010011" WHEN "11101101", --(X"53") 
		    "10011001" WHEN "11101110", --(X"99") 
		    "01100001" WHEN "11101111", --(X"61") 
		    --sixteenth row
 		    "00010111" WHEN "11110000", --(X"17")
		    "00101011" WHEN "11110001", --(X"2B")
		    "00000100" WHEN "11110010", --(X"04")
		    "01111110" WHEN "11110011", --(X"7E")
		    "10111010" WHEN "11110100", --(X"BA")
		    "01110111" WHEN "11110101", --(X"77")
		    "11010110" WHEN "11110110", --(X"D6")
		    "00100110" WHEN "11110111", --(X"26")
		    "11100001" WHEN "11111000", --(X"E1")
		    "01101001" WHEN "11111001", --(X"69")
		    "00010100" WHEN "11111010", --(X"14")
		    "01100011" WHEN "11111011", --(X"63")
		    "01010101" WHEN "11111100", --(X"55")
		    "00100001" WHEN "11111101", --(X"21")
		    "00001100" WHEN "11111110", --(X"0C")
		    "01111101" WHEN "11111111", --(X"7D")
		    
		    "XXXXXXXX" WHEN OTHERS;
END beh;			    