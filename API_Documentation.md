# Rest API documentation

### Api endpoint - /api/v1/

Available commands:
- Get all users
- Get user by its id
- Create user
- Update user
- Delete user

### Get all users

GET request on /users will return all users json object

Response example:

```
[
  {
    "id": {id},
    "login": "{login}",
    "password": "{password}",
    "firstName": "{first_name}",
    "lastName": "{last_name}",
    "email": "{email}",
    "authorities": [
      {
        "id": {id_in_db},
        "authority": "{role}"
      }
    ]
  }
]
```

### Get user by its id

Get request on /users/{id} will return user json object

Response example:

```
{
	"id": {id},
	"login": "{login}",
	"password": "{password}",
	"firstName": "{first_name}",
	"lastName": "{last_name}",
	"email": "{email}",
	"authorities": [
	  {
	    "id": {id_in_db},
	    "authority": "{role}"
	  }
	]
}
```

## Authentication
All manipulation with database needs authentication object in json request:

```
...
"authentication": {
	"timestamp": {timestamp},
	"apikey": "{apikey}",
},
...
```

Where {timestamp} is current unix timestamp by utc+3 till ms f.e. 1649614815527  
And {apikey} is "{timestamp}.{actual_api_key}" encoded in bcrypt

### Create user
POST request on /users

Example request:

```{
  "authentication": {
    "timestamp": "1649614815000",
    "apikey": "{encoded api key}"
  },
  "login": "test",
  "password": "332211",
  "firstName": "TestFN",
  "lastName": "TestLN",
  "email": "test@gmail.com"
}
```

Example response:

```
{
  "id": 4,
  "login": "test",
  "password": "$2a$10$uEGFYqBSBYMzqVSO0YWbeuv93rsTNNhVvHVTD9Y9CF27/wxILLMna",
  "firstName": "TestFN",
  "lastName": "TestLN",
  "email": "test@gmail.com",
  "authorities": []
}
```

### Update user
PUT request on /users

Example request:

```
{
  "authentication": {
    "timestamp": 1649607812000,
    "apikey": "{encoded api key}"
  },
  "id": 1,
  "lastName": "{last_name}"
}
```

Example response:

```
{
  "id": 1,
  "login": "{login}",
  "password": "{password}",
  "firstName": "{first_name}",
  "lastName": "{last_name}",
  "email": "{email}",
  "authorities": [
    {
      "id": 1,
      "authority": "ROLE_USER"
    }
  ]
}
```

### Delete user
DELETE request on /users

Example request:

```
{
  "authentication": {
    "timestamp": "1649613418000",
    "apikey": "{encoded api key}"
  },
  "id": "4"
}
```

Example response:
```ok```