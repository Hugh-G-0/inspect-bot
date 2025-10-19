import socket as socketio

socket = socketio.socket()

socket.bind(("", 2000))

socket.listen(128)

other: socketio.socket = socket.accept()[0]

pwmValues: list[int] = [0] * 16

prev = bytes()

def refresh() -> None:
    global prev, pwmValues
    
    print("looking for packet...")
    
    packet = other.recv(64)

    if len(prev) + len(packet) < 64:
        print(f"not long enough: {len(prev) + len(packet)}")
        prev += packet
        return
    combined = prev + packet

    pwmValues = [int.from_bytes(combined[i:i+4]) for i in range(0, 64, 4)]

    prev = combined[64:]
    
    print(pwmValues)
