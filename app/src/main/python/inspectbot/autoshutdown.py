import RPi.GPIO as gpio
import os

gpio.setmode(gpio.BCM)

gpio.setup(21, gpio.IN)

if __name__ == "__main__":
	while True:
		if gpio.input(21):
			os.system("sudo shutdown now")
