import urllib3
import json
import sys

host = sys.argv[1] + '.herokuapp.com' if len(sys.argv) > 1 else 'localhost:8080'
http = urllib3.PoolManager()
response = http.request('DELETE', 'http://' + host + '/products/milk')
if response.status != 204:
    sys.exit(1)
