LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE work.ALL;

ENTITY s_box_4 IS
PORT(
    s_box_4_in    :	IN  std_logic_vector(31 DOWNTO 0);
    s_box_4_out   :	OUT std_logic_vector(31 DOWNTO 0)
    );
END s_box_4;

ARCHITECTURE beh OF s_box_4 IS
--component instantiation
COMPONENT s_box 
PORT(
    fs_box_in    :	IN  std_logic_vector(7 DOWNTO 0);
    fs_box_out   :	OUT std_logic_vector(7 DOWNTO 0)
    );
END COMPONENT;

BEGIN
--generating 4 s-boxes 
sbox_4: FOR i IN 3 DOWNTO 0 GENERATE
    sbox_map:	s_box
    PORT MAP(
	    fs_box_in => s_box_4_in(8*i+7 downto 8*i),
	    fs_box_out => s_box_4_out(8*i+7 downto 8*i)
	    );
END GENERATE sbox_4;	    

END beh;	