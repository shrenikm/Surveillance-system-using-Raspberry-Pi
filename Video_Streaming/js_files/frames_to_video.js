// A javascript function to take the image frames stored in our /images directory
// and display them in succession 

// The video produced by the script still has a lot of breaks but is 
// enough for our purposes

function show_image(frame_number) {

	// We put a timestamp with the image url to prevent browser caching
	// and displaying the same image
	var d = new Date();
    var img = document.createElement("img");
    img.src = "images/webcam_base64_"+frame_number.toString()+".jpeg?ver=" + d.getTime();
    img.width = 640;
    img.height = 480;
    img.style.top = "265px";
    // img.style.left = "420px";
    //The next two lines makes sure that the image is centered
    img.style.left = "50%";
    img.style.transform = "translateX(-50%)";
    img.style.position = "absolute";
    img.alt = "webcam picture";

    // This next line will just add it to the html
    document.body.appendChild(img);    
}

//j is the frame number which has to be displayed next. It has to be
// incremented each time

//number_of_frames is the total number of frames that we are producing out of the client side python script
// For now we keep it at 30
var j = -1;
var number_of_frames = 30;
setInterval(function(){show_image((++j)%number_of_frames)}, 300);