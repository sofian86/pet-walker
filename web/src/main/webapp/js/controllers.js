var petwalkerControllers = angular.module('petwalkerApp', []);

petwalkerControllers.controller("UserCtrl", function ($scope, $http) {
    $http.get('/s/users').success(function (data) {
        $scope.users = data;
    });
});
