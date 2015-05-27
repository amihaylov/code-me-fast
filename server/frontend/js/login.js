"use strict"
// on document ready
$( document ).ready(function(){
   // init stuff here
   	var status;
    $('button#submit').bind('click', function(){
    	var loginData = {
    		username: $('input#username').val(),
    		password: $('input#password').val()
    	};
    	status = CodemefastApp.loginUser(loginData);
    	console.log(status + ' ' + username);
    	switch(status) {
    		case 'nouser':
    			alert("There is no such user!");
    			break;
    		case 'no':
    			alert("Wrong password. Try again.");
    			break;
    		case 'ok':
    		default:
    			//LoadIndex.loadIndex(username);
    			break;
    	}
    });
})