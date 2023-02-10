LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE work.ALL;

ENTITY inv_round IS
PORT(
    d_in        :	IN  std_logic_vector(127 downto 0);
    key	        :	IN  std_logic_vector(127 downto 0);
    last_mux_sel:	IN  STD_LOGIC;
    data_out    :	OUT std_logic_vector(127 downto 0)
    );
END inv_round;

ARCHITECTURE beh OF inv_round IS

COMPONENT inv_s_box 
PORT(
    fs_box_in    :	IN  std_logic_vector(7 downto 0);
    fs_box_out   :	OUT std_logic_vector(7 downto 0)
    );
END COMPONENT;

COMPONENT inv_shift_rows 
PORT(
    shiftrow_in     :   IN  std_logic_vector(127 downto 0);
    shiftrow_out    :   OUT std_logic_vector(127 downto 0)
    );
END COMPONENT;

COMPONENT inv_mix_column 
PORT(
    mixcolumn_in     :   IN  std_logic_vector(127 downto 0);
    mixcolumn_out    :   OUT std_logic_vector(127 downto 0)
    );
END COMPONENT;


SIGNAL shiftrow  : std_logic_vector(127 downto 0);
SIGNAL bytesub   : std_logic_vector(127 downto 0);
SIGNAL mixcolumn : std_logic_vector(127 downto 0);
SIGNAL mux       : std_logic_vector(127 downto 0);


BEGIN



	    
shiftrow_s:  inv_shift_rows
PORT MAP(
	shiftrow_in => d_in,
	shiftrow_out => shiftrow
	);

 -- generate 16 replica of 8-bit inv-S-box


sbox_16: FOR i IN 15 DOWNTO 0 GENERATE
    sbox_map:	inv_s_box
    PORT MAP(
	    fs_box_in => shiftrow(8*i+7 downto 8*i),
	    fs_box_out => bytesub(8*i+7 downto 8*i)
	    );
END GENERATE sbox_16;

mux <= bytesub XOR key;

mixcolumn_s: inv_mix_column
PORT MAP(   
	mixcolumn_in => mux,
	mixcolumn_out => mixcolumn
	);
data_out <= mixcolumn WHEN last_mux_sel='0' ELSE
             mux;

END beh;			    
			    