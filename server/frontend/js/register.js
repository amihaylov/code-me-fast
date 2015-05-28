"use strict"
// on document ready
$( document ).ready(function(){
   // init stuff here
    $('#submit').on('click', function(){
    	console.log("success")
    	var loginData = {
    		username: $('#username').val(),
    		email: $('#email').val,
    		password: $('#password').val()
    	};
    	if($('#password').val().length > 7 &&
    		$('#password').val() === $('#password-confirm').val()) {
			//POST new user
			location.href = "login.html";
		}
		else
			alert("Your password confirmation doesnt match or is less than 8 symbols. Please retype again.");
		return false;
    });
}) 
