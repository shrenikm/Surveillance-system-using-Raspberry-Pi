import cv2
import numpy as np 
import multiprocessing as mp
import time 
import Image
import requests

url = "http://watchmeprintagain.webege.com/php_files/webcam_stream_read.php"
TIME_TO_READ = 30
count = 0
start_time = time.time()
cap = cv2.VideoCapture(0)
count = 0


# function to read and send the file image as a php post request
def post_file():
	f = {'image' : open('webcam_stream.jpeg', 'rb')}
	req = requests.post(url, files = f)



def capture_video():
	global count


	while cap.isOpened():


		_, frame = cap.read()

		# frame stores the picture taken from the webcam
		# opencv works with BGR while Image works with RGB
		# To combine both pipelines, we convert the BGR to RGB
		frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)

		im = Image.fromarray(frame)

		# We reduce the quality a bit to get more upload speed
		im.save("webcam_stream.jpeg", quality = 20)
		post_file()
		print "ok"
		count+=1

		if time.time() - start_time >= TIME_TO_READ:
			print count
			break

if __name__ == '__main__':

	
	req_process = mp.Process(target = post_file)
	cap_process = mp.Process(target = capture_video)
	req_process.start()
	cap_process.start()
	req_process.join()
	cap_process.join()
	print "done"

