$(document).ready(function() {
	$(function(){
	   $("#menu-your-projects").menu();    //jQueryUI method that shows the <ul> as a menu.
	   $("#menu-your-projects").hide();
	});

	$(function(){
	    $("#menubar-your-projects").click(function(){    //show the menu when "Menu" is clicked.
	        $("#menu-your-projects").show(); //it is here that we make the menu behave as a drop-down menu. Or else it will be visible at all times.
	    });
	});

	$(function(){
	    $("#menubar-your-projects").mouseleave(function(){    //mouseleave fires only when the mouse pointer leaves all the child elements also. This is needed because we need the menu to be shown while the pointer is ON the menu.
	        $("#menu-your-projects").slideUp("fast");
	        $("#menubar-your-projects").blur();
	    });
	});

	$(function(){
	    $("a").click(function(){    //this is the piece of code that closes the menu after an item is clicked.
	        $("#menu-your-projects").slideUp("fast");
	        $("#menubar-your-projects").blur();
	    });
	});

	$(function(){
	   $("#menu-search-projects").menu();    //jQueryUI method that shows the <ul> as a menu.
	   $("#menu-search-projects").hide();
	});

	$(function(){
	    $("#menubar-search-projects").click(function(){    //show the menu when "Menu" is clicked.
	        $("#menu-search-projects").show(); //it is here that we make the menu behave as a drop-down menu. Or else it will be visible at all times.
	    });
	});

	$(function(){
	    $("#menubar-search-projects").mouseleave(function(){    //mouseleave fires only when the mouse pointer leaves all the child elements also. This is needed because we need the menu to be shown while the pointer is ON the menu.
	        $("#menu-search-projects").slideUp("fast");
	        $("#menubar-search-projects").blur();
	    });
	});

	$(function(){
	    $("a").click(function(){    //this is the piece of code that closes the menu after an item is clicked.
	        $("#menu-search-projects").slideUp("fast");
	        $("#menubar-search-projects").blur();
	    });
	});
});