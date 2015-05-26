var express = require('express');
var bodyParser = require('body-parser');
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

app.post('/api', function (req, res) {
    
});

app.post('/api/login', function (req, res) {
    var username = req.body.username;
    var password = req.body.password;
});

app.post('/api/tasks', function (req, res) {
    
});

app.post('/api/notifications', function (req, res) {
    
});

app.post('/api/messages', function (req, res) {
    
});

var server = app.listen(8080, function () {

  var host = server.address().address;
  var port = server.address().port;

  console.log('Server started. Listening at http://%s on port: %s', host, port);
});