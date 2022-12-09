# Springboot-jms-ibm-mq
Springboot app to publish/consume xml message to IBM MQ server (Marshalling/Unmarshalling using Jaxb)


# To publish message to IBM MQ 

Use Postman to post this message.
POST: http://localhost:8989/api/user

Message:

{
	"user": {
		"userId": "1",
		"userName": "Rajesh Kawali",
		"contactNumber": "9900990099",
		"dateOfBirth": "01/11/1993"
	},
	"address": {
		"city": "Mumbai",
		"state": "Maharashtra",
		"country": "India",
		"pin": "400001"
	}
}
