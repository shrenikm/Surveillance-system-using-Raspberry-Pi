import cv2
import numpy as np 
import requests
import time
import Image
import multiprocessing as mp

def post_file(url):
	f = {'image' : open('webcam_stream.jpeg', 'rb')}
	req = requests.post(url, files = f)

cap = cv2.VideoCapture(0)

# url to send the request
url = "http://watchmeprintagain.webege.com/php_files/webcam_stream_read.php"

# Now we continuously read the frames from the webcam for some time
start_time = time.time()
TIME_TO_READ = 30

count = 0
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
	

	ss = time.time()
	pr = mp.Process(target = post_file, args=(url,))
	pr.start()

	print "This frame done"
	# print req.text
	pr.join()
	print time.time() - ss
	count+=1
	# Read images for the time specified
	if time.time() - start_time >= TIME_TO_READ:
		break

	if cv2.waitKey(1) & 0xFF == ord('q'):
		break

print "done"
print count
cap.release()
cv2.destroyAllWindows()
