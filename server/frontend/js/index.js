"use strict"
// on document ready
$( document ).ready(function(){
   // init stuff here
   	CodemefastApp.getUser(sessionStorage.getItem('username'));

    $('#your-projects').on('click', function(){
    	CodemefastApp.getProjectsByUsername(sessionStorage.getItem('username'));
    });

    $('.projects').on('click', function(){
    	var id = $(this).attr('id');
    	CodemefastApp.getTasksByProjectAndUsername(id, sessionStorage.getItem('username'));
    });
})