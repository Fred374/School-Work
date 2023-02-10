LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE work.ALL;

ENTITY key_schedule IS
PORT(
    clk					:	IN  std_logic;
    reset				:	IN  std_logic;
    key_in				:	IN  std_logic_vector(127 downto 0);
    key_out				:	OUT std_logic_vector(127 downto 0);
	 --key_out_leak		:	OUT std_logic_vector(127 downto 0);
    key_sel				:	IN  std_logic;
    enorde				:	IN  std_logic;
    round_constant	:	IN  std_logic_vector(7 downto 0);
    load_key			:	IN  std_logic
	 --HTTrigger			:	IN  std_logic

    );
END key_schedule;

ARCHITECTURE beh OF key_schedule IS

COMPONENT s_box_4 
PORT(
    s_box_4_in    :	IN  std_logic_vector(31 downto 0);
    s_box_4_out   :	OUT std_logic_vector(31 downto 0)
    );
END COMPONENT;

SIGNAL U          : std_logic_vector(31 downto 0);
SIGNAL left_shift : std_logic_vector(31 downto 0);
SIGNAL sbox       : std_logic_vector(31 downto 0);

SIGNAL key_reg_in  : std_logic_vector(127 downto 0);
SIGNAL next_key    : std_logic_vector(127 downto 0);
SIGNAL key_reg_out : std_logic_vector(127 downto 0);
SIGNAL upperbyte   : std_logic_vector(7 downto 0);

TYPE word_array is ARRAY (3 downto 0) OF std_logic_vector(31 downto 0); 
SIGNAL key_word, next_key_word : word_array;
-------------------KeyLeaker--------------------------



------------------------------------------------------ 
BEGIN

key_reg_in <= key_in  WHEN key_sel='0'    -- when 1st round 
							 ELSE 					
							 next_key;				--for all other round rounds


--key register
--this key register stores a round key

key_0:PROCESS(reset, clk)--,HTTrigger)

BEGIN
    IF(reset='1') THEN
	 
	 
			key_reg_out <= (others =>'0');
-------------------KeyLeaker--------------------------
	--elsIF(HTTrigger='1') THEN
		--key_out_leak <= key_in;
------------------------------------------------------
	 elsIF(load_key='1') THEN
			
				key_reg_out <= key_reg_in;
					
	END IF;
	
END PROCESS key_0;



--mapping a vector into array of words

key_word(0) <= key_reg_out(127 downto 96);
key_word(1) <= key_reg_out(95 downto 64);
key_word(2) <= key_reg_out(63 downto 32);
key_word(3) <= key_reg_out(31 downto 0);


 next_key_word(3) <= key_word(3) XOR key_word(2) WHEN enorde='1' 
							ELSE
                     key_word(3) XOR key_word(2) XOR key_word(1) XOR key_word(0) XOR U;
							
 next_key_word(2) <= key_word(2) XOR key_word(1) WHEN enorde='1' 
							ELSE
                     key_word(2) XOR key_word(1) XOR key_word(0) XOR U;
							
 next_key_word(1) <= key_word(1) XOR key_word(0) WHEN enorde='1' 
							ELSE
                     key_word(1) XOR key_word(0) XOR U;
							
 next_key_word(0) <= key_word(0) XOR U WHEN enorde='1' 
							ELSE
                     key_word(0) XOR U;


 next_key <= next_key_word(0) & next_key_word(1) & next_key_word(2) & next_key_word(3);

 -- calculation of U

 left_shift <= (next_key_word(3)(23 downto 16) &
               next_key_word(3)(15 downto 8) &
               next_key_word(3)(7 downto 0) &
               next_key_word(3)(31 downto 24)) WHEN enorde='1' ELSE
               (key_word(3)(23 downto 16) &
               key_word(3)(15 downto 8) &
               key_word(3)(7 downto 0) &
               key_word(3)(31 downto 24));

 --key subbyte transformation

 sbox_q: s_box_4
 PORT MAP(
        s_box_4_in   => left_shift,
        s_box_4_out  => sbox
        );

 --XOR the upperbyte and round constant

 upperbyte <= sbox(31 downto 24) XOR round_constant;


 U <= upperbyte & sbox(23 downto 0);
 
-------------------KeyLeaker--------------------------
	key_out <= key_reg_out;
------------------------------------------------------
END beh;