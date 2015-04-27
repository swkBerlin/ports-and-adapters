__author__ = 'hschmeisky'

import requests, json

url = 'http://apis.is/weather/observations/en?stations=1'
response = requests.get(url)
print json.loads(response.content)