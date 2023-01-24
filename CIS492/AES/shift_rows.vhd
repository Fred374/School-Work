LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE work.ALL;

ENTITY shift_rows IS
PORT(
    shiftrow_in     :   IN  std_logic_vector(127 downto 0);
    shiftrow_out    :   OUT std_logic_vector(127 downto 0)
    );
END shift_rows;

ARCHITECTURE beh OF shift_rows IS

-- Construct a 16X8 (16 byte X 8 bit) matrix array
-- Take the 128 bit input and devide it into 16 byte X 8 bit

TYPE matrix_index IS array (15 downto 0) OF std_logic_vector(7 downto 0);
SIGNAL matrix1, matrix2 : matrix_index;

BEGIN
-- map the 128 bit input to matrix1 so we can shift it.

vector_to_matrix1: PROCESS(shiftrow_in)
BEGIN
    FOR i IN 15 downto 0 LOOP
	matrix1(15-i) <= shiftrow_in(8*i+7 downto 8*i);
    END LOOP;
END PROCESS vector_to_matrix1;

-- matrix2 is actually matrix1 shifted as shown in the above example.

-- combinatorial logic

-- first column
matrix2(0)  <=  matrix1(0);
matrix2(1)  <=  matrix1(5);
matrix2(2)  <=  matrix1(10);
matrix2(3)  <=  matrix1(15);
-- second column
matrix2(4)  <=  matrix1(4);
matrix2(5)  <=  matrix1(9);
matrix2(6)  <=  matrix1(14);
matrix2(7)  <=  matrix1(3);
-- third column
matrix2(8)  <=  matrix1(8);
matrix2(9)  <=  matrix1(13);
matrix2(10) <=  matrix1(2);
matrix2(11) <=  matrix1(7);
-- forth column
matrix2(12) <=  matrix1(12);
matrix2(13) <=  matrix1(1);
matrix2(14) <=  matrix1(6);
matrix2(15) <=  matrix1(11);

--map matrix2 back to 128 bit vector
matrix2_to_vector: PROCESS(matrix2)
BEGIN
    FOR i IN 15 downto 0 LOOP
	shiftrow_out(8*i+7 DOWNTO 8*i) <= matrix2(15-i);
    END LOOP;
END PROCESS matrix2_to_vector;

END beh;			    