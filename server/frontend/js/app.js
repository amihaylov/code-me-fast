var CodemefastApp = (function() {

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
          sessionStorage.setItem(loginData.username);
          console.log("Yes be.");
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
    $.get({"/api/" + username, function(data){
        //Do some shit with user data.
      }
    },"json");
  };

  var getProjectByUsername = function(username){
    $.get({ "/api/users/projects/" + username, function(data) {
      //DO Something
    },"json");

    };
  }

  var getProjectById = function(id){
    $.get({ "/api/projects/" + id, function(data) {
      //DO Something
    },"json");

    };
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
 
