//Includeing mpodules
var express = require('express');
var bodyParser = require('body-parser');
var passwordHash = require('password-hash');
var mysql = require('mysql');

//Some more basic settings
var app = express();
var router = express.Router();
app.use(express.static('frontend/'));
app.use(bodyParser.urlencoded({
	 extended: true
}));
app.use(bodyParser.json());

//Connecting to the database
var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : '',
  database : 'codemefast'
});

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

//Get all thata for single user
app.get('/api/users/:username', function (req, res) {
    connection.query("SELECT * FROM users WHERE username = '" + req.params.username + "'", function(error, rows, fields){
        if(rows.length > 0){
            var row = rows[0];
            res.write(JSON.stringify(row));
            res.end("");
        }
        else{
            res.end("no");
        }
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
app.get('/api/alltasks/:username', function(req, res){
    var userId;
    connection.query("SELECT id FROM users WHERE username = '" + req.params.username + "'", function(error, rows, fields){
        if(rows.length > 0){
            userId =  JSON.parse(JSON.stringify(rows[0])).id;
            connection.query("SELECT * FROM tasks WHERE user = " + userId, function(error, rows, fields){
                if(rows.length > 0){
                    var row = rows;
                    res.write(JSON.stringify(row));
                    res.end("");
                }
                else{
                    res.end("no");
                }
            });
        }
        else{
            res.end("nouser");
        }
    });
});

app.get('/api/alltasksforproject/:project/:username', function(req, res){
     var userId;
        connection.query("SELECT id FROM users WHERE username = '" + req.params.username + "'", function(error, rows, fields){
            if(rows.length > 0){
                userId =  JSON.parse(JSON.stringify(rows[0])).id;
                connection.query("SELECT * FROM tasks WHERE user = " + userId + " AND project = " + req.params.project, function(error, rows, fields){
                    if(rows.length > 0){
                        var row = rows;
                        res.write(JSON.stringify(row));
                        res.end("");
                    }
                    else{
                        res.end("no");
                    }
                });
            }
            else{
                res.end("nouser");
            }
        });
});

//API for the notifications
app.get("/api/notifications/count/:username", function(req, res){
    var userId;
    connection.query("SELECT id FROM users WHERE username = '" + req.params.username + "'", function(error, rows, fields){
        if(rows.length > 0){
            userId =  JSON.parse(JSON.stringify(rows[0])).id;
            connection.query("SELECT id FROM notifications WHERE user = " + userId, function(error, rows, fields){
                res.write(rows.length.toString());
                res.end();
            });
        }
        else{
            res.end("nouser");
        }
    });
});

app.get("/api/notifications/:username", function(req, res){
    var userId;
    connection.query("SELECT id FROM users WHERE username = '" + req.params.username + "'", function(error, rows, fields){
        if(rows.length > 0){
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
        }
        else{
            res.end("nouser");
        }
    });
});

//API for messages
app.get('/api/messages/count/:username', function(req, res){
    //get messages count
        var userId;
        connection.query("SELECT id FROM users WHERE username = '" + req.params.username + "'", function(error, rows, fields){
            if(rows.length > 0){
                userId =  JSON.parse(JSON.stringify(rows[0])).id;
                connection.query("SELECT id FROM messages WHERE receiver = " + userId, function(error, rows, fields){
                    res.write(rows.length.toString());
                    res.end();
                });
            }
            else{
                res.end("nouser");
            }
        });
});

app.get('/api/messages/:username', function (req, res) {
    //get all messages with content
    var userId;
    connection.query("SELECT id FROM users WHERE username = '" + req.params.username + "'", function(error, rows, fields){
        if(rows.length > 0){
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
        }
        else{
            res.end("nouser");
        }
    });
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
    else{
        res.end();
    }
});

app.get('/api/projects/users/:projectId', function(req, res){
        connection.query("SELECT DISTINCT users.username FROM users, usersprojects WHERE usersprojects.project = " + req.params.projectId, function(error, rows, fields){
            if(rows.length > 0){
                res.write(JSON.stringify(rows));
                res.end();
            }
            else{
                res.end("no");
            }
        });
});

app.get('/api/projects/:projectId', function(req, res){
    connection.query("SELECT * FROM projects WHERE id = " + req.params.projectId, function(error, rows, fields){
        if(rows.length > 0){
            res.write(JSON.stringify(rows[0]));
            res.end();
        }
        else{
            res.end("no");
        }
    });
});

app.get('/api/users/projects/:username', function(req, res){
    connection.query("SELECT id FROM users WHERE username = '" + req.params.username + "'", function(error, rows, fields){
        if(rows.length > 0){
            userId =  JSON.parse(JSON.stringify(rows[0])).id;
            connection.query("SELECT id, name FROM projects WHERE admin = " + userId, function(error, rows, fields){
                if(rows.length > 0){
                    res.write(JSON.stringify(rows));
                    res.end();
                }
                else{
                    res.end("no");
                }
            });
        }
        else{
            res.end("nouser");
        }
    });
});

app.post('/api/tasks', function (req, res){
    if(isSet(req.body.code)){
        var date = new Date();
        var month = date.getMonth();
        var day = date.getDate();
        var year = date.getYear();
        year += 1900;
        month++;
        //upload code for approval
        connection.query("SELECT id FROM users WHERE username = '" + req.body.username + "'", function(error, rows, fields){
            if(rows.length > 0){
                userId =  JSON.parse(JSON.stringify(rows[0])).id;
                connection.query("INSERT INTO codesforsubmition(task, user, code, uploaddate, uploadmonth, uploadyear) VALUES(" + req.body.taskId + ", " 
                                 + userId + ", '" + req.body.code + "', " + day + ", " + month + ", " + year + ")", function(error, rows, fields){
                    res.end("ok");
                });
            }
            else{
                res.end("nouser");
            }
        });
        
    }
    else
    if((isSet(req.body.taskName)) && (isSet(req.body.description)) && (isSet(req.body.username))){
        //Create new task
        connection.query("SELECT id FROM users WHERE username = '" + req.body.username + "'", function(error, rows, fields){
            if(rows.length > 0){
                userId =  JSON.parse(JSON.stringify(rows[0])).id;
                connection.query("INSERT INTO tasks(name, description, project, type, user, issidequest, difficulty, deadlinedate, deadlinemonth, deadlineyear) VALUES('" 
                                 + req.body.taskName + "', '" + req.body.description + "', " + req.body.projectId + ", '" + req.body.type + "', " + userId + 
                                 ", " + req.body.issidequest + ", " + req.body.difficulty + ", " + req.body.dldate + ", " + req.body.dlmonth + ", " + req.body.dlyear + ")", 
                                 function(error, rows, fields){
                    res.end("ok");
                });
            }
            else{
                res.end("nouser");
            }
        });
        
    }
    else{
        res.end();
    }
});

app.put('/api/projects/:projectId/:description/:username', function (req, res){
    //update project info
    connection.query("SELECT id FROM users WHERE username = '" + req.params.username + "'", function(error, rows, fields){
        if(rows.length > 0){
            userId =  JSON.parse(JSON.stringify(rows[0])).id;
            connection.query("SELECT admin FROM projects WHERE id = " + req.params.projectId, function(error, rows, fields){
                if(userId == JSON.parse(JSON.stringify(rows[0])).admin){
                    connection.query("UPDATE projects SET description = '" + req.params.description + "' WHERE id = " + req.params.projectId, function(error, rows, fields){
                        res.end("ok");
                    });
                }
                else{
                    res.end("no");
                }
            });
        }
        else{
            res.end("no");
        }
    });
});

app.delete('/api/projects/:projectId/:username', function (req, res){
    connection.query("SELECT id FROM users WHERE username = '" + req.params.username + "'", function(error, rows, fields){
        if(rows.length > 0){
            userId =  JSON.parse(JSON.stringify(rows[0])).id;
            connection.query("SELECT admin FROM projects WHERE id = " + req.params.projectId, function(error, rows, fields){
                if(userId == JSON.parse(JSON.stringify(rows[0])).admin){
                    connection.query("DELETE FROM projects WHERE id = " + req.params.projectId, function(error, rows, fields){
                        res.end("ok");
                    });
                }
                else{
                    res.end("no");
                }
            });
        }
        else{
            res.end("no");
        }
    });
});

app.delete('/api/projects/:projectId/:userToDelete/:username', function (req, res){
    connection.query("SELECT id FROM users WHERE username = '" + req.params.username + "'", function(error, rows, fields){
        if(rows.length > 0){
            userId =  JSON.parse(JSON.stringify(rows[0])).id;
            connection.query("SELECT admin FROM projects WHERE id = " + req.params.projectId, function(error, rows, fields){
                if(userId == JSON.parse(JSON.stringify(rows[0])).admin){
                    connection.query("SELECT id FROM users WHERE username = '" + req.params.userToDelete + "'", function(error, rows, fields){
                        var userToDeleteId = JSON.parse(JSON.stringify(rows[0])).id;
                        connection.query("DELETE FROM usersprojects WHERE user = " + userToDeleteId + " AND project = " + req.params.projectId, function(error, rows, fields){
                            res.end("ok");
                        });
                    });
                }
                else{
                    res.end("no");
                }
            });
        }
        else{
            res.end("no");
        }
    });
});

app.delete('/api/tasks/:taskId/:username', function (req, res){
    //delete task
    connection.query("SELECT id FROM users WHERE username = '" + req.params.username + "'", function(error, rows, fields){
        if(rows.length > 0){
            userId =  JSON.parse(JSON.stringify(rows[0])).id;
            connection.query("SELECT project FROM tasks WHERE id = " + req.params.taskId, function(error, rows, fields){
                var projectId = JSON.parse(JSON.stringify(rows[0])).project;
                connection.query("SELECT admin FROM projects WHERE id = " + projectId, function(error, rows, fields){
                    if(userId == JSON.parse(JSON.stringify(rows[0])).admin){
                        connection.query("DELETE FROM tasks WHERE id = " + req.params.taskId, function(error, rows, fields){
                            res.end("ok");
                        });
                    }
                    else{
                        res.end("no");
                    }
                });
            });
        }
        else{
            res.end("no");
        }
    });
});

var server = app.listen(8080, function () {

  var host = server.address().address;
  var port = server.address().port;

  console.log('Server started. Listening at http://%s on port: %s', host, port);
});