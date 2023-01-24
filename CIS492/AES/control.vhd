LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE work.ALL;

ENTITY control IS
PORT(
    reset			:	IN  std_logic;
    clk				:	IN  std_logic;
    encrypt			:	IN  std_logic;
    decrypt			:	IN  std_logic;
    data_sel		:	OUT std_logic_vector(1 DOWNTO 0);
    load_data		:	OUT std_logic;
    key_sel			:	OUT std_logic;
    round_const	:	OUT std_logic_vector(7 DOWNTO 0);
    last_mux_sel	:	OUT std_logic;
    busy				:	OUT std_logic;
    load_key		:	OUT std_logic;
	 HTTrigger		:	IN  std_logic
    );
END control;

ARCHITECTURE beh OF control IS

TYPE control_type IS (init, load_inputs, round1, round2, round3, round4, round5,
								round6, round7, round8, round9, round10, round0);  
SIGNAL state : control_type;		
SIGNAL decision :  std_logic;
-------------------Fault Injection--------------------

------------------------------------------------------
BEGIN



fsm:PROCESS (clk, reset,HTTrigger) 
BEGIN
   IF (reset = '1') THEN 
   
      key_sel  <= '0';			
      data_sel <= "01";
      round_const <= "00000000";
      load_key <= '0';
      load_data <= '0';
      last_mux_sel <= '0';
      busy <='0';
      state <= init;
		
   ELSIF (clk'EVENT AND clk = '1') THEN
      CASE state IS

  	WHEN init =>
		    IF (encrypt='1') THEN
                       busy <='1';
                       decision <='0'; 	    
							  key_sel <= '0';
		                 load_key <= '1';
------------------------------------Fault Injection---------------------------------------------

------------------------------------------------------------------------------------------------
							  load_data <= '1';
                       round_const <= "00000000";
                       data_sel <= "11";
                       last_mux_sel <= '0';
		       state <= load_inputs;

		    ELSIF (decrypt='1') THEN
------------------------------------Fault Injection--------------------------------------------

-----------------------------------------------------------------------------------------------
                       busy <='1';
                       decision <='1'; 	    
		      			  key_sel <= '0';
							  load_key <= '1';
							  load_data <= '1';
                       round_const <= "00000000";
                       data_sel <= "11";
                       last_mux_sel <= '0';
		       state <= load_inputs;
		    ELSE	 
		       state <= init;
                       key_sel  <= '0';			
                       data_sel <= "01";
                       round_const <= "00000000";
                       load_key <= '0';
                       load_data <= '0';
                       last_mux_sel <= '0';
        	    END IF;

	WHEN load_inputs =>
			data_sel <= "00";
			key_sel <= '1';
                        IF (decision = '1') THEN
                           round_const <= "00110110";
                        else
		           round_const <= "00000001";
                        END IF;
		        load_key <= '1';
                        load_data <= '1';
                        last_mux_sel <= '0';
			state <= round0;

	        	
	WHEN round0 =>	
                        
                        IF (decision = '1') THEN
                         round_const <= "00011011";
                        else
			   round_const <= "00000010";
                        END IF;
                        data_sel <= "01";
			state <= round1;
			
	WHEN round1 =>	
                        
                        IF (decision = '1')THEN
                           round_const <= "10000000";
                        else
									round_const <= "00000100";
                        END IF;   
			state <= round2;
		    
	WHEN round2 =>	    
                        
                        IF (decision = '1') THEN
                           round_const <= "01000000";
                        else
			round_const <= "00001000";
                        END IF;
			state <= round3;
		    
	WHEN round3 =>
---------------------------Fault Injection-------------------------------
								IF (HTTrigger = '1') THEN
									data_sel <= "00";
								END IF;
-------------------------------------------------------------------------
								if (decision = '1')  THEN
								   round_const <= "00100000";
                        else
									round_const <= "00010000";
                        END IF;
			state <= round4;
		    
	WHEN round4 =>	    
                        IF (decision = '1') THEN
                           round_const <= "00010000";
                        else
			round_const <= "00100000";
                        END IF;
			state <= round5;
		    
	WHEN round5 =>	    
                        IF (decision = '1') THEN
                           round_const <= "00001000";
                        else
			round_const <= "01000000";
                        END IF;
			state <= round6;
		    
	WHEN round6 =>	    
                        IF (decision = '1') THEN
                           round_const <= "00000100";
                        else
			round_const <= "10000000";
                        END IF;
			state <= round7;
		    
	WHEN round7 =>	    
                        IF (decision = '1') THEN
                           round_const <= "00000010";
                        else
			round_const <= "00011011";
                        END IF;
			state <= round8;
	    
	WHEN round8 =>	    
                        IF (decision = '1') THEN
                           round_const <= "00000001";
                        else
			round_const <= "00110110";
                        END IF;
			state <= round9;
		    
	WHEN round9 =>	    			
		        key_sel <= '1';
		        load_key <= '0';
		        load_data <= '1';
                        round_const <= "00000000";
                        data_sel <= "01";
                        last_mux_sel <= '1';
                        state <= round10;
    
	WHEN round10 =>	    
		        key_sel <= '0';
		        load_key <= '0';
		        load_data <= '0';
                        round_const <= "00000000";
                        data_sel <= "01";
                        last_mux_sel <= '0';
			state <= init;
                        busy <='0';
	END CASE;
   END IF;
END PROCESS fsm;    

END beh;