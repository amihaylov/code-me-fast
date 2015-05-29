"use strict"
// on document ready
$( document ).ready(function(){
   // init stuff here
   	var status;
    $('#submit').on('click', function(){
    	var loginData = {
    		username: $('#username').val(),
    		password: $('#password').val()
    	};
		CodemefastApp.loginUser(loginData);
    });
})