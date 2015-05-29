var CodemefastApp = (function() {

  //Used in loginUser as callback
  var checkStatus = function(status, username){
    console.log("Inside "+status+username);
    switch(status) {
        default:
          alert("No connection!");
          break;
        case 'nouser':
          alert("There is no such user!");
          break;
        case 'no':
          alert("Wrong password. Try again.");
          break;
        case 'ok': {
          sessionStorage.setItem('username', username);
          console.log("Yes be.");
          location.href = "index.html";
          break;
        }
      }
  }

  var loginUser = function(loginData) {
    var debug = loginData;
    console.log(debug);
    $.ajax({
      type: "POST",
      url: '/api/login',
      data: loginData,
      success: function(resp){console.log(resp +' '+ loginData.username); checkStatus(resp, loginData.username);},
      error: function(error){console.log("Error " + error)}
    });
    return (status);
  };

  var getUser = function(username) {
    $.get("/api/users/" + username, function(data){
        //Do some shit with user data.
        sessionStorage.setItem('userid', data.id);
    },"json");
  };

  var getTasksByProjectAndUsername = function(projectid, adminid, username){

    $.get("/api/alltasksforproject/" + projectid + "/" + username, function(data){
      var currentUser = sessionStorage.getItem('username');
      var currentUserId = sessionStorage.getItem('userid');
      var headerContainer = $('#project-header');
      headerContainer.empty();
      headerContainer.addClass("page-header");

      console.log(data.length);
      console.log(data[0]);
      console.log(adminid);
      console.log(currentUserId);


      var taskContainer = $('#task-container');
      taskContainer.empty();
    
      if(adminid == currentUserId) {
        //Build admin window
        var role = $('<p></p>').addClass("lead").text("Role: Admin");
        var type = $('<p></p>').addClass("lead").text("Type: " + data.type);
        headerContainer.append(role).append(type);

        for (var i=0; i<data.length; i+=1){
          if(data[i].user == sessionStorage.getItem('userid')){
            var row = $('<tr></tr>');
            var cellId = $('<td></td>').text(data[i].id);
            var cellName = $('<td></td>').text(data[i].name);
            var cellDesc = $('<td></td>').text(data[i].description);
            var cellType = $('<td></td>').text(data[i].type);
            var cellDifficulty = $('<td></td>').text(data[i].difficulty);
            var cellFinished = $('<td></td>');
              if(data[i].finished === 0)
                cellFinished.text("No");
              else
                cellFinished.text("Yes");
            var cellDeadline = $('<td></td>').text(data[i].deadlinedate +"-"+ 
                            data[i].deadlinemonth +"-"+ data[i].deadlineyear);

            row.append(cellId).append(cellName).append(cellDesc).append(cellType)
                .append(cellDifficulty).append(cellFinished).append(cellDeadline);

            var rowEdit = $('<tr></tr>');
            var cellEditText = $('<td></td>');
                cellEditText.prop({"colspan": "2"});
            var editText = $("<input>");
                editText.addClass(data[i].id).text(data[i].link);
                editText.prop({"type":"text", "id": "edit-text"});

            cellEditText.append(editText);
             
            var buttonEdit = $('<button></button>').text("Finish Task")
                .addClass("btn btn-danger edit-task").addClass(data[i].id)
                .prop({"type": "button"});
            
            var cellEdit = $('<td></td>');
                cellEdit.append(buttonEdit); 
            rowEdit.append(cellEditText).append(cellEdit);
            taskContainer.append(row).append(rowEdit);
          }
        }
      }

      else {
        //Build user window with tasks
        var role = $('<p></p>').addClass("lead").text("Role: User");
        var type = $('<p></p>').addClass("lead").text("Type: " + data.type);
        headerContainer.append(role).append(type);

        for (var i=0; i<data.length; i+=1){
          if(data[i].user == sessionStorage.getItem('userid')){
            var row = $('<tr></tr>');
            var cellId = $('<td></td>').text(data[i].id);
            var cellName = $('<td></td>').text(data[i].name);
            var cellDesc = $('<td></td>').text(data[i].description);
            var cellType = $('<td></td>').text(data[i].type);
            var cellDifficulty = $('<td></td>').text(data[i].difficulty);
            var cellFinished = $('<td></td>');
              if(data[i].finished === 0)
                cellFinished.text("No");
              else
                cellFinished.text("Yes");
            var cellDeadline = $('<td></td>').text(data[i].deadlinedate +"-"+ 
                            data[i].deadlinemonth +"-"+ data[i].deadlineyear);

            row.append(cellId).append(cellName).append(cellDesc).append(cellType)
                .append(cellDifficulty).append(cellFinished).append(cellDeadline);

            var rowEdit = $('<tr></tr>');
            var cellEditText = $('<td></td>');
                cellEditText.prop({"colspan": "2"});
            var editText = $("<input>");
                editText.addClass(data[i].id);
                editText.prop({"type":"text", "id": "edit-text"});

            cellEditText.append(editText);
             
            var buttonEdit = $('<button></button>').text("Edit Task")
                .addClass("btn btn-primary edit-task").addClass(data[i].id)
                .prop({"type": "button"});
            
            var cellEdit = $('<td></td>');
                cellEdit.append(buttonEdit); 
            rowEdit.append(cellEditText).append(cellEdit);
            taskContainer.append(row).append(rowEdit);
          }
        }

      }
    },"json");
  };

  var callbackAddTasksToMenu = function(){
    $('#menu-content > li').on('click', function(){
        var id = $(this).attr('id');
        console.log('clicked');
        console.log(id);
        console.log(sessionStorage.getItem('username'))
        getProjectById(id);
    });
  };

  //Load all projects of the user in the menubar on the left
  var getProjectsByUsername = function(username){
    $.get( "/api/users/projects/" + username, function(data) {
      var container = $('#menu-content');
      container.empty();
      for (var i=0; i<data.length; i+=1){
        var item = $('<li></li>').attr("id", data[i].id).addClass("projects");
        var anchor = $('<a></a>').text(data[i].name);
        item.append(anchor);
        container.append(item);
      }
      callbackAddTasksToMenu();
    },"json");

    };

  //Load all project details when selected from the menubar on the left
  //Does not return TASKS, FIX IT BY using another reques
  var getProjectById = function(id){
    $.get( "/api/projects/" + id, function(data) {
      getTasksByProjectAndUsername(id, data.admin, sessionStorage.getItem('username'));
    },"json");
    };

  // public api
  return {
    loginUser: loginUser,
    getUser: getUser,
    getProjectsByUsername: getProjectsByUsername,
    getProjectById: getProjectById,
    getTasksByProjectAndUsername: getTasksByProjectAndUsername



  };
})();
 
