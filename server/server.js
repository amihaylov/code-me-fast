var express = require('express');
var bodyParser = require('body-parser');
var passwordHash = require('password-hash');
var mysql = require('mysql');

var app = express();

app.use(express.static('frontend/'));

var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : '',
  database : 'codemefast'
});

app.use(bodyParser.urlencoded({
	 extended: true
}));
app.use(bodyParser.json());

//Redirect to index.html
app.get('/', function (req, res) {
    
    res.sendFile('./frontend/index.html', { root: __dirname });
});

//Check if variable is set
function isSet(variable){
    if(typeof variable == 'undefined'){
        return false;
    }
    else{
        return true;
    }
}

app.get('/api', function (req, res) {
    /*connection.query("SELECT * FROM users", function(error, rows, fields){
        var a = rows;
        res.write(JSON.stringify(a));
        res.end("");
    });*/
    if(isSet(req.query.username)){
        connection.query("SELECT * FROM users WHERE username = '" + req.query.username + "'", function(error, rows, fields){
            var row = rows[0];
            res.write(JSON.stringify(row));
            res.end("");
        });
    }
    else{
        res.end();
    }
});

//Login logic
app.post('/api/login', function (req, res) {
    var username = req.body.username;
    var password = req.body.password;
    connection.query("SELECT password FROM users WHERE username = '" + username + "'", function(error, rows, fields){
        if(rows.length > 0){
            var row = rows[0];
            if(passwordHash.verify(password, JSON.parse(JSON.stringify(row)).password)){
                res.write("ok");
            }
            else{
                res.write("no");
            }
        }
        else{
            res.write("nouser");
        }
        res.end("");
    });
});

//Get uder id
function getUserId(username){
    connection.query("SELECT id FROM users WHERE username = '" + username + "'", function(error, rows, fields){
        if(rows.length > 0){
            return JSON.parse(JSON.stringify(rows[0])).id;
        }
        else{
            return null;
        }
    });
}

//API for the tsks
app.get('/api/tasks', function (req, res) {
    if((isSet(req.query.username)) && (!isSet(req.query.project))){
        //Get all tasks from database
        var userId;
        connection.query("SELECT id FROM users WHERE username = '" + req.query.username + "'", function(error, rows, fields){
            userId =  JSON.parse(JSON.stringify(rows[0])).id;
            connection.query("SELECT * FROM tasks WHERE user = " + userId, function(error, rows, fields){
                if(rows.length > 0){
                    var row = rows;
                    res.write(JSON.stringify(row));
                    res.end("");
                }
                else{
                    res.write("no");
                    res.end();
                }
            });
        });
    }
    else 
    if((isSet(req.query.username)) && (isSet(req.query.project))){
        //get all tasks for current project
        var userId;
        connection.query("SELECT id FROM users WHERE username = '" + req.query.username + "'", function(error, rows, fields){
            userId =  JSON.parse(JSON.stringify(rows[0])).id;
            connection.query("SELECT * FROM tasks WHERE user = " + userId + " AND project = " + req.query.project, function(error, rows, fields){
                if(rows.length > 0){
                    var row = rows;
                    res.write(JSON.stringify(row));
                    res.end("");
                }
                else{
                    res.write("no");
                    res.end();
                }
            });
        });
    }
    else{
        res.end();
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
        var date = new Date();
        var month = date.getMonth();
        var day = date.getDate();
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