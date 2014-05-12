'use strict';

angular.module('spartaApp')

  .controller( 'MenuCtrl', function ($scope, SessionProvider) {
    $scope.isLoggedIn = function() {
        return SessionProvider.isLoggedIn();
    };
    
  });
