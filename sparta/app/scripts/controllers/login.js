'use strict';

angular.module('spartaApp')

  .controller('LoginCtrl', function ($scope) {

  	$scope.username = 'username';
  	$scope.password = 'password';

    $scope.login = function(SessionProvider) {
        SessionProvider.setLogin(true);
    };
  });