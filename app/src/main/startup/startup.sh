#!/bin/bash

echo "starting inspect-bot"

python3 autoshutdown.py &

while true
do
	python3 /home/hughg/Documents/inspect-bot-python/main.py
done
