var express = require('express');
var bodyParser = require('body-parser');
var passwordHash = require('password-hash');
var mysql = require('mysql');

var app = express();
var router = express.Router();

router.use(function(req, res, next) {
    // do logging
    console.log('Something is happening.');
    next(); // make sure we go to the next routes and don't stop here
});

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

app.use('/api', router);



//Redirect to index.html
app.get('/', function (req, res) {
    
    res.sendFile('./frontend/index.html', { root: __dirname });
});

//Redirect to login page
app.get('/login', function (req, res) {
    
    res.sendFile('./frontend/login.html', { root: __dirname });
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

//var api = {username : {username : 'projectId'}, projects: {projectId : 'projectId'}};

app.get('/api/:username', function (req, res) {
    connection.query("SELECT * FROM users WHERE username = '" + req.params.username + "'", function(error, rows, fields){
        var row = rows[0];
        res.write(JSON.stringify(row));
        res.end("");
    });
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

//API for the notifications
app.get('/api/notifications', function (req, res) {
    if(req.query.getContent == "false"){
        //get notification count
        var userId;
        connection.query("SELECT id FROM users WHERE username = '" + req.query.username + "'", function(error, rows, fields){
            userId =  JSON.parse(JSON.stringify(rows[0])).id;
            connection.query("SELECT id FROM notifications WHERE user = " + userId, function(error, rows, fields){
                res.write(rows.length.toString());
                res.end();
            });
        });
    }
    else{
        //get all notifications with content
        var userId;
        connection.query("SELECT id FROM users WHERE username = '" + req.query.username + "'", function(error, rows, fields){
            userId =  JSON.parse(JSON.stringify(rows[0])).id;
            connection.query("SELECT * FROM notifications WHERE user = " + userId, function(error, rows, fields){
                if(rows.length > 0){
                    res.write(JSON.stringify(rows));
                    res.end();
                }
                else{
                    res.write("no");
                    res.end();
                }
            });
        });
    }
});

app.get('/api/messages', function (req, res) {
    if(req.query.getContent == "false"){
        //get messages count
        var userId;
        connection.query("SELECT id FROM users WHERE username = '" + req.query.username + "'", function(error, rows, fields){
            userId =  JSON.parse(JSON.stringify(rows[0])).id;
            connection.query("SELECT id FROM messages WHERE receiver = " + userId, function(error, rows, fields){
                res.write(rows.length.toString());
                res.end();
            });
        });
    }
    else{
        //get all messages with content
        var userId;
        connection.query("SELECT id FROM users WHERE username = '" + req.query.username + "'", function(error, rows, fields){
            userId =  JSON.parse(JSON.stringify(rows[0])).id;
            connection.query("SELECT * FROM messages WHERE receiver = " + userId, function(error, rows, fields){
                if(rows.length > 0){
                    res.write(JSON.stringify(rows));
                    res.end();
                }
                else{
                    res.write("no");
                    res.end();
                }
            });
        });
    }
});

app.post('/api/projects', function (req, res){
    if((isSet(req.body.project)) && (isSet(req.body.newUser)) && (isSet(req.body.username))){
        //Add user to project
        var userId;
        connection.query("SELECT id FROM users WHERE username = '" + req.body.newUser + "'", function(error, rows, fields){
            userId =  JSON.parse(JSON.stringify(rows[0])).id;
            connection.query("INSERT INTO usersprojects(user, project) VALUES(" + userId + ", " + req.body.project + ")", function(error, rows, fields){
                res.end();
            });
        });
    }
    else
    if((isSet(req.body.projectName)) && (isSet(req.body.username))){
        //Create new project
        var userId;
        connection.query("SELECT id FROM users WHERE username = '" + req.body.username + "'", function(error, rows, fields){
            userId =  JSON.parse(JSON.stringify(rows[0])).id;
            connection.query("INSERT INTO projects(name, description, type, admin) VALUES('" + req.body.projectName + "', '" 
                             + req.body.projectDescription + "', " + req.body.type + ", " + userId + ")", function(error, rows, fields){
                res.end();
            });
        });
    }
});

app.get('/projects/:projectId', function(req, res){
    connection.query("SELECT * FROM projects WHERE id = " + req.params.projectId, function(error, rows, fields){
        if(rows.length > 0){
            res.write(JSON.stringify(rows[0]));
            res.end();
        }
        else{
            res.end();
        }
    });
});

app.get('api/users/projects/:username', function(req, res){
    
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