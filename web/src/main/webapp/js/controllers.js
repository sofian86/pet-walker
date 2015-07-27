'use strict';

var petwalkerControllers = angular.module('petwalkerApp', []);

petwalkerControllers.controller("UserCtrl", function ($scope, $http) {
    $scope.users = [];
    $scope.locations = [];

    $http.get('/s/users').success(function (data) {
        $scope.users = data;
    });



    $scope.selectUser = function (index) {
        $scope.locations = [];

        $scope.selectedUser = index;

        $http.get("/s/users/" + $scope.users[index]).success(function (data) {
            $scope.locations = data;
        });
    };

});
