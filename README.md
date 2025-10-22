# Inspection Robot
This is the source code for my inspection robot.
It is divided into three parts:

[/app/src/main/java/inspectbot](): controller code

[/app/src/main/python/inspectbot](): onborad python code

[/app/src/main/python/startup](): onboard startup scripts

# Referenced Libraries

SDL2GDX, availible on GitHub at [https://github.com/electronstudio/sdl2gdx]():    
Java library for interfacing with external controllers (ex. Xbox controllers)

RPi.GPIO, built-in on Raspberry Pi OS:   
Python library for interfacing with the 2x20 GPIO header on Raspberry Pi devices

adafruit-circuitpython-servokit, availible on GitHub at [https://github.com/adafruit/Adafruit_CircuitPython_ServoKit]():    
Python library for interfacing with PWM expansion board

# Referenced Tutorials

[https://learn.adafruit.com/adafruit-16-channel-pwm-servo-hat-for-raspberry-pi/using-the-python-library]():  
Basic overview of the ServoKit and GPIO libraries needed for interfacing with the PWM expansion board
