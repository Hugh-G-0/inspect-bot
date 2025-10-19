import devices

def applyOutputs(outputs: list[int]) -> None:
    
    print("applying outputs" + str(outputs))
    for n, i in enumerate(devices.devices):

        if type(i) is devices.GearMotor:
            i.setDutyCycle65535(outputs[n])

        elif type(i) is devices.ServoMotor:
            i.setAngle(outputs[n])

def disableOutputs() -> None:
    print("disabling all outputs")
    
    devices.disableAll()
        
