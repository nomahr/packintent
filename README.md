# packintent
Test project for Google Cloud Messaging

# testing with GCM
```python
import requests
import json

gcm_url = 'https://android.googleapis.com/gcm/send'
server_key = '<ENTER__SERVER__KEY__HERE>'

def gcm_send_downstream_message( in_message, in_topic=None ):
	topic = in_topic
	if topic is None:
		topic = '/topics/global'

	headers = { 'Authorization' : 'key={}'.format( server_key ), 'Content-Type' : 'application/json' }
	data = { 'message' : in_message }
	notification = { 'title' : 'GCM Message', 'body' : in_message, 'icon' : 'myicon' }
	json_data = json.dumps( { 'to' : topic, 'data' : data, 'notification' : notification } )

	r = requests.post( gcm_url, headers=headers, data=json_data )

	print 'status={} text={}'.format( r.status_code, r.text )

def main():
	gcm_send_downstream_message( 'GCM background message' )
	# gcm_send_downstream_message( 'GCM foreground message' )
	

if __name__ == '__main__':
	main()
```

