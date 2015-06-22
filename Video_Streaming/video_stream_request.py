# This is a python script that takes frames from the webcam and relays this to a
# php script on our web server.

# We convert each image to a string using base64 encoding. 30 such strings (frames) 
# are concatenated and sent to the website at once. The frames are stored and our 
# website displays it like a video.

import cv2
import numpy as np 
import requests
import time
import Image
import base64


cap = cv2.VideoCapture(0)
#
# im_str is the base64 encoded string
im_str = ""

# url to send the request
url = "http://watchmeprintagain.webege.com/php_files/webcam_stream_read_64.php"

# Initialize variables that we require
start_time = time.time()
TIME_TO_READ = 60
FRAMES_TO_READ = 30
im_str = ""
im_str_sub = ""
frame_count = 0
count = 0

# Now we continuously read the frames from the webcam for some time
while cap.isOpened():

	# frame_count keeps tab on the number of frames. If it is 30, we pack it
	# into a single string and send this as a http post request
	frame_count+=1


	_, frame = cap.read()

	# frame stores the picture taken from the webcam
	# opencv works with BGR while Image works with RGB
	# To combine both pipelines, we convert the BGR to RGB
	frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)

	im = Image.fromarray(frame)

	# We reduce the quality a bit to get more upload speed
	im.save("webcam_base64.jpeg", quality = 20)

	with open("webcam_base64.jpeg", "rb") as imgFile:
		# base64 encoding
		im_str_sub = base64.b64encode(imgFile.read())

	if frame_count == FRAMES_TO_READ:

		# We display the time it takes for the 30 concatenated strings to be
		# sent and stored in our server space
		ss = time.time()
		im_base64 = {'image':im_str}
		req = requests.post(url, data = im_base64)
		print "---------------------------------------------------------"
		print time.time() - ss
		frame_count = 0
		print "set done"
		im_str = ""

	else:
		im_str = im_str+im_str_sub+"*"

	# We count the number of frames
	count+=1
	
	# print req.text
	# Read images for the time specified
	if time.time() - start_time >= TIME_TO_READ:
		break

	if cv2.waitKey(1) & 0xFF == ord('q'):
		break

print "done"
print count

cap.release()
cv2.destroyAllWindows()