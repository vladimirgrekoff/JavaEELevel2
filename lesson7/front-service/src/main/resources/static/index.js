var app = angular.module('lesson7', ['ngRoute', 'ngStorage'])
    .config(function ($routeProvider, $httpProvider) {

    $routeProvider.when('/login',
    {
    templateUrl:'loginTemplate.html',
    controller:'loginController',
    controllerAs:'login'
    })
    .when('/navigation',
    {
    templateUrl:'navigationTemplate.html',
    controller:'navigationController',
    controllerAs:'navigation'
    })
    .when('/cart',
    {
    templateUrl:'cartTemplate.html',
    controller:'cartController',
    controllerAs:'cart'
    })
    .when('/products',
    {
    templateUrl:'productTemplate.html',
    controller:'productController',
    controllerAs:'products'
    })
    .otherwise({
    redirectTo: 'login'
    });

});