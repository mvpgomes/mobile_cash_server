mobile_cash_server
==================

Compile:
<br>
<code>mvn clean install</code>

Run in localhost
<br>
<code>foreman s</code>

Call some endpoint:
- Receive an hello message
<code>localhost:5000/api/hello</code>
<br>
-Buy a product
<code>Make a POST request to localhost/api/buy with a json object like: <br>
 {"username" : "username", "password" : "password", "product" : "product"}
</code>
<br>
- Deploy in heroku:
<br>
<code> git push heroku master </code>

The application endpoints are implemented in the following package :
- src/main/java/com.sirs.mobilecashserver.rest
