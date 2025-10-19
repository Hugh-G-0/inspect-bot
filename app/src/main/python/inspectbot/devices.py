import board
import busio
import adafruit_pca9685
from adafruit_servokit import ServoKit

import RPi.GPIO as gpio

i2c = busio.I2C(board.SCL, board.SDA)
hat = adafruit_pca9685.PCA9685(i2c)

kit = ServoKit(channels=16)

hat.frequency = 1024

gpio.setmode(gpio.BCM)

for i in (17, 27, 22):
    gpio.setup(i, gpio.OUT)
    gpio.output(i, True)

class GearMotor:
    
    def __init__(self, pwmID: int, gpioID: int) -> None:
        self.pwmID  = pwmID
        self.gpioID = gpioID
        
    def enable(self) -> None:
        gpio.output(self.gpioID, False)
    
    def disable(self) -> None:
        gpio.output(self.gpioID, True)
    
    def setDutyCycle65535(self, dutyCycle: int) -> None:
        
        if abs(32768 - dutyCycle) <= 2:
            self.disable()
        else:
            self.enable()
        
        hat.channels[self.pwmID].duty_cycle = dutyCycle

class ServoMotor:
    
    def __init__(self, pwmID: int, angle: int, lPulseUS: int = 1000, hPulseUS: int = 1000) -> None:
        self.servo = kit.servo[pwmID]
        
        self.servo.actuation_range = angle
        
        self.servo.set_pulse_width_range(lPulseUS, hPulseUS)
    
    def setAngle(self, angle: int) -> None:
        self.servo.angle = round(angle)
        
class Unbound: pass

devices = [Unbound() for i in range(16)]

devices[0] = GearMotor(0, 27)
devices[1] = GearMotor(1, 17)
devices[2] = GearMotor(2, 22)

def disableAll() -> None:
    
    for i in devices:
        try:
            i.disable()
        except:
            pass
