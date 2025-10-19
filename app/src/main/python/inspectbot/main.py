print("starting inspect-bot main.py")

import control
import traceback
import RPi.GPIO as gpio

control.disableOutputs()

import communication

try:
    while True:
        communication.refresh()
        control.applyOutputs(communication.pwmValues)

finally:
    control.disableOutputs()
    gpio.cleanup()
