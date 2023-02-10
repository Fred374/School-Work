import smbus, time

bus = smbus.SMBus(1)
addr = 0x40

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

bus.write_word_data(addr, 0x0c, 500)
time.sleep(3)
bus.write_word_data(addr, 0x0c, -100)
