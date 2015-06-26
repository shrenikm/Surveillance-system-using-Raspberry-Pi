# Surveillance-system-using-Raspberry-Pi
A surveillance system using Raspberry Pi. A camera is attached to the Pi 2. We are then able to monitor the live streaming video by visiting our website.

The files outside the main folder consist of the code to take a picture from the webcam and send it to our server space.

The python script uses the openCV and requests library. It takes a picture using the webcam and stores this picture in the present directory. The requests library is then used to send a http request to a php script running on our server. The image file is converted to a string using base64 encoding. A number of such frames are then concatenated with a "*" in between and sent to our php server script at once. The php script splits the string in between the "*" to get the different strings. These strings are then decoded to images and stored as frames in the images directory.

When we access the website, a javascript function displays the frames with a definite framerate as specified in the function itself. Thus we are able to view a streaming video when we visit the website.

As we are using normal http requests and not socket programming, our framerate is very low and our video has a lot of breaks in between. This is not much of an issue for our purposes.

The app folder consists of the android app that is used to control the actions of the printer. 
