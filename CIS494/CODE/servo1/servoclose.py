import smbus, time

bus = smbus.SMBus(1)
addr = 0x40

## Running this program will move the servo to 0, 45, and 90 degrees with 5 second pauses in between with a 50 Hz PWM signal.

bus.write_byte_data(addr, 0, 0x20)
time.sleep(.25)
bus.write_byte_data(addr, 0, 0x10)
time.sleep(.25)
bus.write_byte_data(addr, 0xfe, 0x88)
time.sleep(.25)
bus.write_byte_data(addr, 0, 0x20)
time.sleep(.25)

bus.write_word_data(addr, 0x0a, 0)
time.sleep(.25)

bus.write_word_data(addr, 0x0c, 100)
time.sleep(3)
bus.write_word_data(addr, 0x0c, -100)
