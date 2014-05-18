'use strict';

angular.module('spartaApp')
  .controller( 'MainCtrl', function ($scope, SessionProvider) {

    $scope.user = localStorage.user;

    $scope.isLoggedIn = function() {
        return SessionProvider.isLoggedIn();
    };

    $scope.logout = function() {
      SessionProvider.logIn(false, null);
    };

  });
