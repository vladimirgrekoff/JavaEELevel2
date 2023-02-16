var app = angular.module('lesson3', ['ngRoute', 'ngStorage'])
    .config(function ($routeProvider, $httpProvider) {

    $routeProvider.when('/login',
    {
    templateUrl:'loginTemplate.html',
    controller:'loginController',
    controllerAs:'login'
    })
    .when('/cart',
    {
    templateUrl:'cartTemplate.html',
    controller:'cartController',
    controllerAs:'cart'
    })
    .otherwise({
    redirectTo: 'login'
    });


});