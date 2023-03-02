app.controller("loginController", function($rootScope, $scope, $http, $location, $window, $localStorage) {
//    const contextPath = 'http://localhost:8187/lesson7-auth/api/v1';
    const contextPath = 'http://localhost:5555/auth/api/v1';

    $scope.$on('routeChangeStart', function(event, next, current) {
        if (typeof(current) != 'undefined') {
            $templateCache.remove(next.templateUrl);
        }
    });

    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    };

    $scope.loginUser = function() {
        $http.post(contextPath + '/authenticate', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    console.log('ответ ' + response.data.token);////////////////////////////////////////////////////////

                    $http.defaults.headers.common.Authorization ='Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                    $scope.getCurrentUserRoles();/////////////////////////////////
                    $location.path('/navigation');
                }
            }, function errorCallback(response) {
                console.log('ошибки ' + response);////////////////////////////////////////////////////////
//                $scope.error = response;
            });
    };

    $rootScope.hasRole = function(check) {
        var roles = $localStorage.currentRoles;
        if (roles != null) {
            for (i=0; i<roles.length; i++){
                if (check == roles[i].name) {
                    return true;
                }
            }
        }
        return false;
    };

    $rootScope.clearRole = function() {
        if($localStorage.currentRoles){
            var roles = null;
            $scope.roles = null;
            delete $localStorage.currentRoles;
        }
    };

    $scope.tryLogout = function() {
        if($rootScope.logout){
            $rootScope.logout();
        } else{
            $location.path('/login')
        }
    };

    $rootScope.logout = function() {
        $scope.clearUser();
        $scope.clearRole();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
        $location.path('/login')
    };

    $rootScope.clearUser = function () {
        if($localStorage.springWebUser){
            delete $localStorage.springWebUser;
            $http.defaults.headers.common.Authorization = '';
        }
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };


    $scope.getCurrentUserRoles = function() {
                console.log('запрос ролей');////////////////////////////////////////////////////////
        $http.get(contextPath + '/profile/roles')
            .then(function successCallback(response) {
                console.log('роли ' + response);////////////////////////////////////////////////////////
                $localStorage.currentRoles = response.data.roles;
            }, function errorCallback(response) {
                alert('UNAUTHORIZED');
            });

//       try {
//            let jwt = $localStorage.springWebUser.token;
//            let payload = JSON.parse(atob(jwt.split('.')[1]));
//                console.log('Token ' + payload.roles);////////////////////////////////////////////////////////
//            let currentTime = parseInt(new Date().getTime() / 1000);
//            if (currentTime > payload.exp) {
//                console.log("Token is expired!!!");
//                delete $localStorage.springWebUser;
//                $http.defaults.headers.common.Authorization = '';
//            }
//        } catch (e) {
//        }
    };


});