import cv2
import numpy as np 
import requests


cap = cv2.VideoCapture(0)

_, frame = cap.read()

# frame stores the picture taken from the webcam
# Now we save this image
cv2.imwrite("webcam.png", frame)
cv2.waitKey(0)
cv2.destroyAllWindows()

# Now we send this image as a request

# url
url = "http://watchmeprint.comlu.com/php_files/webcam_image_read.php"

f = {'file' : open('webcam.png', 'rb')}
req = requests.post(url, files = f)
print req.status_code, req.text