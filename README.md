# Code_Sharing_Platform

This is a RESTful spring boot application that allows you to share your code with the other developers. You don't need to sign up on this. All you just need to do 
is to post your code, generate UUID and share it with your colleagues.

Programming is full of little secrets. Sometimes, even within the same company, there is some secret code that should be hidden from most of the colleagues. So you can
set some restrictions and limitations on the code snippets you post and that piece of code will only be available to certain people of your choice, and it may be 
deleted in the future to hide the traces. 

### super secret snippets
* limit on the number of views will allow viewing the snippet only a certain number of times, after which the snippet is deleted from the database.
* limit on the viewing time will allow viewing a code snippet for a certain period of time, and after its expiration, the code snippet is deleted from the database.



### API endpoints
* Get JSON with the code snippet with given id
```sh
GET: /api/code/{id}
```
* Add new code snippet. JSON object should have fields code, time and views. Return id which gives acces to code snippet.
```sh
POST: /api/code/new
```
* Return a JSON array with 10 most recently uploaded code snippets sorted from the newest to the oldest.
```sh
GET: /api/code/latest
```

### Web endpoints
* Return HTML that contains the code snippet with given id 
```sh
/code/id
```
* Return HTML where you can add new code snippet
```sh
/code/new
```
* Return HTML that contains 10 most recently uploaded code snippets
```sh
/code/latest
```
