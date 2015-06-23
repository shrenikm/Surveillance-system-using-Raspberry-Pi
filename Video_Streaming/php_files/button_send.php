<?php

	$txt = $_POST["state"];
	file_put_contents("../text_files/button_text.txt", $txt);
	echo "Written to file";

?>