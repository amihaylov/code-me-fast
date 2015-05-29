// on document ready
$( document ).ready(function(){
   // init stuff here
   var user = sessionStorage.getItem('username');
   	CodemefastApp.getUser(user);

    CodemefastApp.getProjectsByUsername(user);
    CodemefastApp.getUser(user);

    $('.logout').bind('click', function(){
    	sessionStorage.setItem('username', '');
    	location.href = "login.html";
    });
})