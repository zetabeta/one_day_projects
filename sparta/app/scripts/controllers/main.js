'use strict';

angular.module('spartaApp')
  .controller( 'MainCtrl', function ($scope, SessionProvider) {
    $scope.isLoggedIn = function() {
        return SessionProvider.isLoggedIn();
    };
  });
