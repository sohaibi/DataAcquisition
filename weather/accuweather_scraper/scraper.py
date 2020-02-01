import requests
import urllib.request
import time
from bs4 import BeautifulSoup

url = 'https://www.accuweather.com/en/us/austin-tx/78701/hourly-weather-forecast/351193'
user_agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36"
headers = {'User-Agent': user_agent}
response = requests.get(url, headers=headers)
soup = BeautifulSoup(response.text, features="html.parser")
dataStr = ''
#print(soup)
for a in soup.findAll('div', {'class': 'hourly-forecast-card-content'}):
    dataStr += a.text
data = dataStr.split()
labelStr = ['RealFeel', 'Wind', 'mph', 'Gusts:', 'Humidity',
            'Dew', 'Point', 'Max', 'UV', 'Index', '(', ')',
            'Cover', 'Rain', 'in', 'Snow', 'Ice', 'Visibility',
            'mi', 'Ceiling', 'Cloud', 'ft']
fData = []
for s in data:
    makeNew = False
    canAdd = True
    for s1 in labelStr:
        if (s1 in s):
            if (labelStr[0] in s):
                makeNew = True
            canAdd = False
    if (makeNew):
         fData.append([])
    if (canAdd):
         fData[-1].append(s)
print(fData)
