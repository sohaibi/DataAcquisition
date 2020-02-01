from selenium import webdriver
from bs4 import BeautifulSoup

driver = webdriver.Chrome()
driver.get("https://www.accuweather.com/en/us/austin-tx/78701/hourly-weather-forecast/351193")
content = driver.page_source
soup = BeautifulSoup(content)
dataStr = ''
#print(soup)
for a in soup.findAll('div', {'class': 'hourly-forecast-card-content'}):
    dataStr += a.text
data = dataStr.split()
labelStr = ['RealFeel', 'Wind', 'mph', 'Gusts:', 'Humidity',
            'Dew', 'Point', 'Max', 'UV', 'Index', '(', ')',
            'Cover', 'Rain', 'in', 'Snow', 'Ice', 'Visibility',
            'mi', 'Ceiling', 'ft']
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
driver.stop_client()
driver.close()
