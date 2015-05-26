var express = require('express');
var bodyParser = require('body-parser');
var passwordHash = require('password-hash');
var app = express();
app.use(express.static('frontend/'));

app.use(bodyParser.urlencoded({
	 extended: true
}));
app.use(bodyParser.json());

app.get('/', function (req, res) {
    
    res.sendFile('./frontend/index.html', { root: __dirname });
});

function isSet(variable){
    if(typeof variable == 'undefined'){
        return false;
    }
    else{
        return true;
    }
}

app.get('/api', function (req, res) {
    if(isSet(req.body.username)){
        //TODO: return data for the user
    }
});

app.post('/api/login', function (req, res) {
    var username = req.body.username;
    var password = req.body.password;
    password = passwordHash.generate(password);
});

app.get('/api/tasks', function (req, res) {
    if((isSet(req.body.username)) && (!isSet(req.body.project))){
        //TODO: Get all tasks from database
    }
    else 
    if((isSet(req.body.username)) && (isSet(req.body.project))){
        //TODO: get all tasks for current project
    }
});

app.get('/api/notifications', function (req, res) {
    if(req.body.getContent == "false"){
        //TODO: get notification count
    }
    else{
        //TODO: get all notifications with content
    }
});

app.get('/api/messages', function (req, res) {
    if(req.body.getContent == "false"){
        //TODO: get messages count
    }
    else{
        //TODO: get all messages with content
    }
});

app.post('/api/projects', function (req, res){
    if((isSet(req.body.project)) && (isSet(req.body.newUser)) && (isSet(req.body.username))){
        //TODO: Add user to project
    }
    else
    if((isSet(req.body.projectName)) && (isSet(req.body.username))){
        //TODO: Create new project
    }
});

app.post('/api/tasks', function (req, res){
    if(isSet(req.body.code)){
        //TODO: upload code for approval
    }
    else
    if((isSet(req.body.taskName)) && (isSet(req.body.description)) && (isSet(req.body.username))){
        //TODO: Create new task
    }
    else
    if((isSet(req.body.task)) && (isSet(req.body.newUser))){
        //TODO: Add task to user
    }
});

app.put('api/projects', function (req, res){
    //TODO: update project info
});

app.put('api/tasks', function (req, res){
    //TODO: update task description
});

app.delete('api/projects', function (req, res){
    if(!isSet(req.body.user)){
        //TODO: Delete project
    }
    else{
        //TODO: Remove user form project
    }
});

app.delete('api/tasks', function (req, res){
    //TODO: delete task
});

var server = app.listen(8080, function () {

  var host = server.address().address;
  var port = server.address().port;

  console.log('Server started. Listening at http://%s on port: %s', host, port);
});