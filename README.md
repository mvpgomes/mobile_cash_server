mobile_cash_server
==================

Compile:
<code>mvn clean install</code>

Run in localhost
<code>foreman s</code>

Call some endpoint:
- Receive an hello message
<code>localhost:5000/api/hello</code>
-Buy a product
<code>Make a POST request to localhost/api/buy with a json object like:
 {"username" : "username", "password" : "password", "product" : "product"}
</code>

- Deploy in heroku:
<code> git push heroku master </code>
