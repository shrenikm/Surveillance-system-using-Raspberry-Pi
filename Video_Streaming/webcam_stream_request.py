import cv2
import numpy as np 
import requests
import time
import Image

cap = cv2.VideoCapture(0)

# url to send the request
url = "http://watchmeprint.comlu.com/php_files/webcam_stream_read.php"

# Now we continuously read the frames from the webcam for some time
start = time.time()
TIME_TO_READ = 20

while cap.isOpened():


	_, frame = cap.read()

	# frame stores the picture taken from the webcam
	# opencv works with BGR while Image works with RGB
	# To combine both pipelines, we convert the BGR to RGB
	frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)

	im = Image.fromarray(frame)

	# We reduce the quality a bit to get more upload speed
	im.save("webcam_stream.jpeg", quality = 20)

	# Request
	f = {'image' : open('webcam_stream.jpeg', 'rb')}
	req = requests.post(url, files = f)

	print "This frame done"
	print req.text

	# Read images for 20 sec
	if time.time() - start >= TIME_TO_READ:
		break

cap.release()
cv2.destroyAllWindows()
