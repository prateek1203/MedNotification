var customerFactory = function($http){
    var factory = {};
    factory.getCustomers= function (){
        var response = $http.get("data/customers.json");
        response.success(function(data){
            console.log(data);
        })
        return "helloworld";
    }
    return factory;
}
console.log(customerFactory);

angular.module("welcome").factory('CustomerFactory', customerFactory);