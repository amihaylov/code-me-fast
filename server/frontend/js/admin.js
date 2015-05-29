"use strict"
// on document ready
$( document ).ready(function(){
   // init stuff here
   var data = [];

    $('#submit').on('click', function(){
    	var task = {
    		name: $('#set-name').val(),
    		description: $('#set-desc').val(),
    		type: $('#set-type').val(),
    		difficulty: $('#set-diff').val(),
    		issidequest: $('#set-sideq').val(),
    		deadline: $('#set-deadline').val(),
    		user: $('#set-assign').val()
    	};
		data.push(task);
		var taskContainer = $('#task-container');
			var row = $('<tr></tr>');
			var cellName = $("<td></td>").text(data[data.length - 1].name);
	        var cellDesc = $("<td></td>").text(data[data.length - 1].description);
	        var cellType = $("<td></td>").text(data[data.length - 1].type);
	        var cellDiff = $("<td></td>").text(data[data.length - 1].difficulty);
	        var cellSideq = $("<td></td>").text(data[data.length - 1].issidequest);
	        var cellDeadline = $("<td></td>").text(data[data.length - 1].deadline);
	        var cellUser = $("<td></td>").text(data[data.length - 1].user);
	        var cellEdit = $("<button></button>").text("Edit").addClass("btn btn-warning");
	        var cellDelete = $("<button></button>").text("Delete").addClass("btn btn-danger");
	        row.append(cellName).append(cellDesc).append(cellType).append(cellDiff).append(cellSideq)
	        	.append(cellDeadline).append(cellUser).append(cellEdit).append(cellDelete);
	        taskContainer.append(row);
    });
})