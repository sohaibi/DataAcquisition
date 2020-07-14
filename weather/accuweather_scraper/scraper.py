from urllib.request import Request, urlopen
from bs4 import BeautifulSoup
import json


#function returns a dictionary where the keys relate to weather attributes
#keys: chances, status, visibility, UV_index, pressure, humidity, wind, now, sun_rise, and temp


weather = {}
url = 'https://weather.com/weather/today/l/a9e8362791a8366662d2f306c08fc5496d43c98ec529f1044339f09454cc23a9'
req = Request(url ,headers={'User-Agent': 'Mozilla/5.0'})
page = urlopen(req).read() # need this line to overrun mod security
soup = BeautifulSoup(page, 'html.parser')


# Loads data from Json file to request properly
def load_data():
    with open("req_data.json") as f:
        json_data = json.load(f)
    for tag in json_data["tags"]:
        html_tag = tag["html_tag"]
        for attr in tag["attr_tags"]:
            for attr_data in tag["attr_tags"][attr]:
                class_data = tag["attr_tags"][attr][attr_data] 
                return_data(attr_data, html_tag, attr, class_data)
    print(weather)


'''
name (name) = soup.find("span" (type), attrs={'class' (classification): "_-_-components-src-atom-WeatherData-Wind-Wind--windWrapper--3Ly7c undefined" (classname)})
weather['wind'] = wind
'''
def return_data(name, tag_type, classification, classname):
    data = soup.find(tag_type, attrs={classification: classname})
    if data is not None:
        data = data.text.strip()
        weather[name] = data
    else:
        print(f"{name} from the tag {tag_type} did not pull up any data. Ignoring.")


load_data()