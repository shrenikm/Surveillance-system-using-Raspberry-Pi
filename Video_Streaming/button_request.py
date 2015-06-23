import requests

r = requests.get("http://watchmeprintagain.webege.com/php_files/button_read.php", "state")
website_content =  r.content.split()
print website_content[0]