<?php

	// When a request is sent, we save the image name in a text file-----------------------------

	// get filename including extension
	$filename_request = $_FILES['file']['name'];
	//append to the text file
	@$fp = fopen("../text_files/webcam_name.txt", "w+");
	if(!$fp)
	{
		echo "ERROR : Could not be written";
		exit;
	}

	fwrite($fp, $filename_request, strlen($filename_request));
	fclose($fp);

	echo "Written to file";


	// Now we need to save this image into our images folder---------------------------------------


	// we read the filename from the text file
	@$fp = fopen("../text_files/webcam_name.txt", "r");
	if(!$fp)
	{
		echo "ERROR : Could not be read";
		exit;
	}

	$filename = fgets($fp, 1000);
	fclose($fp);

	echo "File read";

	// Now we save the file to the uploads directory
	$upload_path = "../images/";

	// echo $upload_path.$filename;



	if(!is_writable($upload_path))
	{
		echo "Cannot upload here";
		exit;

	}

	if(move_uploaded_file($_FILES['file']['tmp_name'], $upload_path.$filename))
	{
		echo "Image saved";
	}

	else
	{
		echo "There was an error while trying to save the image";
	}
