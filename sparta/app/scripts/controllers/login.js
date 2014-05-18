'use strict';

angular.module('spartaApp')

  .controller('LoginCtrl', function ($scope, $http, SessionProvider) {

    $scope.username = '';
    $scope.password = '';


    $scope.login = function() {
      $http.get('credentials.json').success(function(data) {
        for (var i = 0; i < data.users.length; i++) {
          var current = data.users[i];
          if (current.user === $scope.username ) {
            if (current.pass === $scope.password) {
              SessionProvider.logIn(true, current.user);
              $scope.dismissModal();
            }
          }
        }
      });   
    };



  });