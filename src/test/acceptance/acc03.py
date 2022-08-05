import urllib3
import json
import sys

data = {
    'name': 'milk',
    'calories': 100,
    'productType': 'LIQUID'
}

host = sys.argv[1] + '.herokuapp.com' if len(sys.argv) > 1 else 'localhost:8080'
http = urllib3.PoolManager()
response = http.request('POST', 'http://' + host + '/products', body=json.dumps(data),
                        headers={'Content-Type': 'application/json'})
if response.status != 201:
    sys.exit(1)
