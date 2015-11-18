Spezification: HTTP API
=======================

General
-------

*   METHOD /path HTTP/1.1

### Errors

If a request is invalid or an error occurs during a request, the server will respond with a JSON simmilar to this one:

    {
      "success": false,
      "errorCode": 101,
      "errorMessage": "Register request with wrong content type. canceling."
    }

Do not mix the internal `errorCode` with a HTTP status code. Furthermore the server will set a corresponding status Code.

Possible error codes:

*   `101`: The Content-Type header field is not JSON (application/json), as it should be
*   `201`: The requested user name for a new user is allready taken
*   `202`: The user name is invalid
*   `203`: The password is invalid
*   `303`: An unknown error occoured in the Database


User Functions
--------------

### `POST /leruka/register` ###

##### Header:

    Accept: application/json
    Content-Type: application/json
    Charset: utf-8

##### Content:

A JSON object similar to the following:

    {
      "userName": "Max",
      "passwordHash": "973ed5b8f3b4a8b326a74303ed24e297d2d5c40f9c2a9a011880598b68f9b185"
    }

*   `userName`: The name the new user should have
*   `passwordHash`: The users password encrypted in SHA-2 (256)

##### Response:

A JSON object. For example:

    {
      "success": true,
      "sessionID": "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
    }

`sessionID` is a valid UUID.
