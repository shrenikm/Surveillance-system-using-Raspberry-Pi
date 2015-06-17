<!DOCTYPE html>
<html lang = "en-US">

	<head>
		<title>
			Image Display
		</title>
		<h1>
			Webcam Image
		</h1>
	</head>

	<body>
		<!-- Display the image -->
		<?php
		// header("Content-Type: image/png");
		// readfile("../images/webcam.png");
		echo '<img src="../images/webcam.png" alt = "" />';
		?>
	</body>
</html>
