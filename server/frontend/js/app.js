var CodemefastApp = (function() {

  //Used in loginUser as callback
  var checkStatus = function(status, username){
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
        case 'ok':
          sessionStorage.setItem('username', loginData.username);
          console.log("Yes be.");
          location.href = "index.html";
          break;
      }
  }

  var loginUser = function(loginData) {
    $.ajax({
      type: "POST",
      url: '../api/login',
      data: loginData,
      success: function(resp){checkStatus(resp, loginData.username);},
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

  //Load all projects of the user in the menubar on the left
  var getProjectsByUsername = function(username){
    $.get( "/api/users/projects/" + username, function(data) {
      var container = $('#menu-content');
      container.empty();
      for (var i=0; i<data.length; i+=1){
        var item = $('<li></li>');
        var anchor = $('<a></a>').text(data[i].name).attr("id", data[i].id).addClass("projects");
        item.append(anchor);
        container.append(item);
      }

    },"json");

    };

  //Load all project details when selected from the menubar on the left
  //Does not return TASKS, FIX IT BY using another reques
  var getProjectById = function(id){
    $.get( "/api/projects/" + id, function(data) {
    },"json");

    };

  var getTasksByProjectAndUsername = function(projectid, username){
    $.get("api/alltasksforproject/" + projectid + "/" + username, function(){
      $.get( "/api/projects/" + id, function(data) {
      var currentUser = sessionStorage.getItem('username');
      var headerContainer = $('#project-header');
      headerContainer.empty();
      headerContainer.addClass("page-header");

      var taskContainer = $('#task-container');
      taskContainer.empty();

      if(data.admin === currentUser) {
        //Build admin window
        var role = $('<p></p>').addClass("lead").text("Role: Admin");
        var type = $('<p></p>').addClass("lead").text("Type: " + data.type);
        headerContainer.append(role).append(type);
      }
      else {
        //Build user window with tasks
        var role = $('<p></p>').addClass("lead").text("Role: User");
        var type = $('<p></p>').addClass("lead").text("Type: " + data.type);
        headerContainer.append(role).append(type);

        //DEBUG
        console.log(sessionStorage.getItem('username'));

        for (var i=0; i<data.length; i+=1){
          if(data[i].user === sessionStorage.getItem('userid')){
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
          }
        }

      }
    },"json");
  }


  //TODO Make selector for id
  var updateBook = function(book) {
    $.ajax({
      type: "PUT",
      url: '/books/' + book.title,
      data: book
    });
  };

  //TODO Make selector for id
  var deleteBook = function(book) {
    $.ajax({
      type: "DELETE",
      url: '/books/' + book.title,
      data: book
    });
  };

  var displayList = function() {

    $.get( "/books", function( data ) {
      //$( ".result" ).html( data );
      var container = $("#books-database > tbody");
      container.empty();

      //Add the database
      for (var i=0; i<data.length; i+=1){
        //Add classes TODO!
        var row = $("<tr></tr>");
        var cellTitle = $("<td></td>").text(data[i].title);
        var cellAuthor = $("<td></td>").text(data[i].author);
        var cellImageSource = $("<td></td>").text(data[i].imgSrc);
        var cellReview = $("<td></td>").text(data[i].review);
        var cellPrice = $("<td></td>").text(data[i].price);
        var cellDateOfPub = $("<td></td>").text(data[i].dateOfPub);
        var cellRating = $("<td></td>").text(data[i].rating);
        var cellNumOfSales = $("<td></td>").text(data[i].numOfSales);
        var cellPromotions = $("<td></td>").text(data[i].promotions);

        row.append(cellTitle).append(cellAuthor).append(cellImageSource).append(cellReview)
            .append(cellPrice).append(cellDateOfPub).append(cellRating).append(cellNumOfSales)
            .append(cellPromotions);
        container.append(row);
      }

    },"json");
    
  };

  var display = function(data) {
    var container = $("#inner-content");
    container.empty();

    //Add the database
    for (var i=0; i<data.length; i+=1){
      //Add classes TODO!
      var row = $("<div></div>").addClass("row item");
      var cellImage = $("<div></div>").addClass("col-md-3 items").prepend($('<img>',{class: 'img', src: data[i].imgSrc}));
      var cellReview = $("<div></div>").addClass("col-md-3 items").text(data[i].review);
      var cellPrice = $("<div></div>").addClass("col-md-3 items");
      var shoppingCart = $("<i></i>").addClass("fa fa-shopping-cart").text(data[i].price);
      cellPrice.append(shoppingCart);

      row.append(cellImage).append(cellReview).append(cellPrice);
      container.append(row);
    }
  };

  var displayImgReviewPrice = function() {
    $.get( "/books", function( data ) {
      display(data);
    },"json");
    
  };

  var searchForPromoAndDisplay = function(){
    $.get( "/books", function( data ) {
      var container = $("#inner-content");
      container.empty();

      //Add the database
      for (var i=0; i<data.length; i+=1){
        //Add classes TODO!
        if(typeof(data[i].promotions) !== 'undefined' || data[i].promotions.length()>0) {
          var row = $("<div></div>").addClass("row item");
          var cellImage = $("<div></div>").addClass("col-md-3 items").prepend($('<img>',{class: 'img', src: data[i].imgSrc}));
          var cellReview = $("<div></div>").addClass("col-md-3 items").text(data[i].review);
          var cellPromo = $("<div></div>").addClass("col-md-3 items").text(data[i].promotions);
          var cellPrice = $("<div></div>").addClass("col-md-3 items");
          var shoppingCart = $("<i></i>").addClass("fa fa-shopping-cart").text(data[i].price);
          cellPrice.append(shoppingCart);

          row.append(cellImage).append(cellReview).append(cellPromo).append(cellPrice);
          container.append(row);
        }
      }
    },"json");
  };

  //Sorting the whole data descending and returning top 5 selled items
  var displayMostSelled = function(){
    $.get( "/books", function( data ) {
      var container = $("#inner-content");
      container.empty();
      
      data.sort(function(a, b){
        var keyA = a.numOfSales,
        keyB = b.numOfSales;
        // Compare the 2 number of sales
        if(keyA > keyB) return -1;
        if(keyA < keyB) return 1;
        return 0;
      });

      //Add the database
      for (var i=0; i<5; i+=1){
        //Add classes TODO!
          var row = $("<div></div>").addClass("row item");
          var cellImage = $("<div></div>").addClass("col-md-3 items").prepend($('<img>',{class: 'img', src: data[i].imgSrc}));
          var cellReview = $("<div></div>").addClass("col-md-3 items").text(data[i].review);
          var cellPromo = $("<div></div>").addClass("col-md-3 items").text(data[i].promotions);
          var cellPrice = $("<div></div>").addClass("col-md-3 items");
          var shoppingCart = $("<i></i>").addClass("fa fa-shopping-cart").text(data[i].price);
          var cellNumOfSales = $("<i></i>").addClass("col-md-3 items").text(data[i].numOfSales);
          cellPrice.append(shoppingCart);

          row.append(cellImage).append(cellReview).append(cellPromo)
              .append(cellPrice).append(cellNumOfSales);
          container.append(row);
      }
    },"json");
  };

  var displayTopRated = function(){
  $.get( "/books", function( data ) {
    var container = $("#inner-content");
    container.empty();
    
    data.sort(function(a, b){
      var keyA = a.rating,
      keyB = b.rating;
      // Compare the 2 number of sales
      if(keyA > keyB) return -1;
      if(keyA < keyB) return 1;
      return 0;
    });

    //Add the database
    for (var i=0; i<5; i+=1){
      //Add classes TODO!
        var row = $("<div></div>").addClass("row item");
        var cellImage = $("<div></div>").addClass("col-md-3 items").prepend($('<img>',{class: 'img', src: data[i].imgSrc}));
        var cellReview = $("<div></div>").addClass("col-md-3 items").text(data[i].review);
        var cellPromo = $("<div></div>").addClass("col-md-3 items").text(data[i].promotions);
        var cellPrice = $("<div></div>").addClass("col-md-3 items");
        var shoppingCart = $("<i></i>").addClass("fa fa-shopping-cart").text(data[i].price);
        var cellRating = $("<i></i>").addClass("col-md-3 items").text(data[i].rating);
        cellPrice.append(shoppingCart);

        row.append(cellImage).append(cellReview).append(cellPromo)
            .append(cellPrice).append(cellRating);
        container.append(row);
     }
   },"json");
  };

  var searchByTitleAndDisplay = function(title) {

    $.get( "/books/" + title, function( data ) {
      if(typeof(data)!=='undefined'){
        display(data);
      }
      else
        alert("No such book!");

    },"json");
    
  };

  var searchByAuthorAndDisplay = function(author) {

    $.get( "/authors/" + author, function( data ) {
      if(typeof(data)!=='undefined'){
        display(data);
      }
      else
        alert("No such author!");

    },"json");
    
  };

  var searchByDateAndDisplay = function(date) {

    $.get( "/dates/" + date, function( data ) {
      if(typeof(data)!=='undefined'){
        display(data);
      }
      else
        alert("No such book published on that date! Mind the YYYY-MM format!");

    },"json");
    
  };


  // public api
  return {
    loginUser: loginUser,
    updateBook: updateBook,
    deleteBook: deleteBook,
    displayList: displayList,
    displayImgReviewPrice: displayImgReviewPrice,
    searchByTitleAndDisplay: searchByTitleAndDisplay,
    searchByAuthorAndDisplay: searchByAuthorAndDisplay,
    searchByDateAndDisplay: searchByDateAndDisplay,
    searchForPromoAndDisplay: searchForPromoAndDisplay,
    displayMostSelled: displayMostSelled,
    displayTopRated: displayTopRated
  };
})();
 
