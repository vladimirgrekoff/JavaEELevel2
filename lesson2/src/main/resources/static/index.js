angular.module('lesson2', []).controller("indexController", function($scope, $http) {
    const contextPath = 'http://localhost:8189/lesson2/api/v1';



//    $scope.$on('routeChangeStart', function(event, next, current) {
//        if (typeof(current) !== 'undefined') {
//            $templateCache.remove(next.templateUrl);
//        }
//    });
//
//    $scope.showNavigationPage = function () {
//    console.log('перейти на страницу навигации');///////////////////////
//        $location.path('navigation');
//    };



    $scope.loadProducts = function (offset, limit) {
            $http({
                url: contextPath + '/products',
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
            $http.get(contextPath + '/cart')
                .then(function (response) {
                $scope.cart = response.data;
        });
    };


    $scope.addProductToCart = function (productId) {
        $http.get(contextPath + '/cart/add/' + productId)
            .then(function (response) {
                productId = null;
                $scope.loadCart();
            });
    };

    $scope.deleteProductFromCart = function (productId) {
        $http.delete(contextPath + '/cart/delete/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    };

    $scope.clearCart = function () {
        $http.get(contextPath + '/cart/clear')
            .then(function (response) {
                $scope.loadCart();
            });
    };


    $scope.createOrder = function () {
        $http.post(contextPath + '/orders')
            .then(function (response) {
                $scope.loadCart();
            });
    };

    $scope.logout = function() {
      $http.post('logout', {}).then(function() {
        $rootScope.authenticated = false;
        $location.path('/welcome');
      }).error(function(data) {
        $rootScope.authenticated = false;
      });
    }

    $scope.loadProducts();
    $scope.loadCart();

});