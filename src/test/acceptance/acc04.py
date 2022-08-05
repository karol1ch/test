import urllib3
import json
import sys

host = sys.argv[1] + '.herokuapp.com' if len(sys.argv) > 1 else 'localhost:8080'
http = urllib3.PoolManager()
response = http.request('GET', 'http://' + host + '/products/milk')
if response.status != 200:
    sys.exit(1)
greeting = json.loads(response.data.decode('utf-8'))
if greeting['name'] != 'milk':
    sys.exit(2)
if greeting['calories'] != 100:
    sys.exit(3)
