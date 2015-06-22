<!-- This php script accepts the base 64 encoded image string that consists of -->
<!-- the strings of 30 images, separated by a '*' -->

<!-- The script splits up the string into its 30 parts and decodes each of the  -->
<!-- strings into an image -->

<!-- It then stores each of the 30 images with a different name (using frame number) -->
<!-- in our /images directory -->

<?php

	$encodedString = str_replace(' ','+',$_POST['image']);
	$decoded_array = explode("*", $encodedString);
	// Splitting the string between the '*'
	// Each of the strings are in an array
	// We take each element, decode it, and store it through a temporary image file
	$count = 0;
	foreach($decoded_array as $imd)
	{
		$decoded = base64_decode($imd);
		$decoded = imagecreatefromstring($decoded);
		imagejpeg($decoded, "temp.jpeg");
		copy("temp.jpeg","../images/webcam_base64_".$count.".jpeg");
		$count+=1;
	}




?>