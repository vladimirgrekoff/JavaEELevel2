app.controller("cartController", function($rootScope, $scope, $http, $location, $window, $localStorage) {
//    const contextPathCore = 'http://localhost:8189/lesson7-core/api/v1';
//    const contextPathCart = 'http://localhost:8190/lesson7-cart/api/v1';
    const contextPathCore = 'http://localhost:5555/core/api/v1';
    const contextPathCart = 'http://localhost:5555/cart/api/v1';



    $scope.$on('routeChangeStart', function(event, next, current) {
        if (typeof(current) !== 'undefined') {
            $templateCache.remove(next.templateUrl);
        }
    });

    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization;
    };


    $scope.showNavigationPage = function () {
        $location.path('navigation');
    };



    $scope.loadProducts = function (offset, limit) {
            $http({
                url: contextPathCore + '/products',
                method: 'GET',
                params: {
                    min_price: $scope.filter ? $scope.filter.min_price : null,
                    max_price: $scope.filter ? $scope.filter.max_price : null,
                    part_title: $scope.filter ? $scope.filter.part_title : null,
                    offset: offset,
                    limit: limit
                }
            }).then(function (response) {
                $scope.ProductsList = response.data.content;
        });
    };



    $scope.loadCart = function () {
            $http.get(contextPathCart + '/cart')
                .then(function (response) {
                $scope.cart = response.data;
        });
    };


    $scope.addProductToCart = function (productId) {
        $http.get(contextPathCart + '/cart/add/' + productId)
            .then(function (response) {
                productId = null;
                $scope.loadCart();
            });
    };

    $scope.deleteProductFromCart = function (productId) {
        $http.delete(contextPathCart + '/cart/delete/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    };

    $scope.clearCart = function () {
        $http.delete(contextPathCart + '/cart/clear')
            .then(function (response) {
                $scope.loadCart();
            });
    };


    $scope.createOrder = function () {
        $http.post(contextPathCore + '/orders')
            .then(function (response) {
                $scope.loadCart();
            });
    };

//    $scope.logout = function() {
//      $http.post('logout', {}).then(function() {
//        $rootScope.authenticated = false;
//        $location.path('/login');
//      }).error(function(data) {
//        $rootScope.authenticated = false;
//      });
//    };

    $scope.tryLogout = function() {
        if($rootScope.logout){
            $rootScope.logout();
        } else {
            $location.path('welcome')
        }
    };

    $scope.loadProducts();
    $scope.loadCart();

});