/*var app = angular.module('welcome' , ['ngRoute']);

app.config(function ($routeProvider){
    $routeProvider.when('/', {
        controller : 'HomeController' , 
        templateUrl : 'welcome.html' 
    });
    $routeProvider.when('/search' , {
        controller : 'SearchController', 
        templateUrl:  'search.html'
    });
    $routeProvider.when("/login", {
        controller : 'LoginController', 
        templateUrl:  'login.html'        
    });
});



app.controller('WelcomeController', function($scope, CustomerFactory) {
    console.log("Welcome App Intialized  " + CustomerFactory); 
    console.log(CustomerFactory.getCustomers());
});


app.controller('HomeController', function ($scope, $http , $location){
    console.log("home controller initialized....... " );
        
});


app.controller('LoginController', function ($scope, $http , $location){
    console.log("Login controller initialized....... ");
    $scope.login = function(){
        var userJSON = angular.toJson($scope.user);
        console.log(userJSON);
        $scope.currentUser= true;
    }
  
});

app.controller('SearchController', function ($scope, $http , $location){
    console.log("SearchController initialized......." );
    var response = $http.get('/data/customers.json');
    response.success(function(data){
        console.log(data);
        $scope.customers = data;
    })
    
    
});



*/