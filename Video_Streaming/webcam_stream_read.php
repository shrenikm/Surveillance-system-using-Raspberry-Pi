<?php

	// When a request is sent, we save the image in the images directory-------------------------

	// get filename including extension
	$imagename_request_stream = $_FILES['image']['name'];
	
	// Now we save the file to the uploads directory
	$upload_path = "../images/";

	if(!is_writable($upload_path))
	{
		echo "Cannot upload here";
		exit;

	}

	if(move_uploaded_file($_FILES['image']['tmp_name'], $upload_path.$imagename_request_stream))
	{
		echo "Image saved";
	}

	else
	{
		echo "There was an error while trying to save the image";
	}

?>
