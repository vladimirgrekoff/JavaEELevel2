angular.module('lesson1', []).controller('indexController', function ($scope, $http) {
    $scope.fillTable = function () {
        $http.get('http://localhost:8189/lesson1/api/v1/products')
            .then(function (response) {
//                 console.log(response);
                $scope.products = response.data;
            });
    };

    $scope.deleteProduct = function (id) {
        $http.delete('http://localhost:8189/lesson1/api/v1/products/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.createNewProduct = function () {
        // console.log($scope.newProduct);
        $http.post('http://localhost:8189/lesson1/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }

    $scope.fillTable();
});