"use strict"
// on document ready
$( document ).ready(function(){
   // init stuff here
   	var status;
    $('#submit').on('submit', function(){
    	var loginData = {
    		username: $('#username').val(),
    		password: $('#password').val()
    	};
		CodemefastApp.loginUser(loginData);
		return false;
    });
})