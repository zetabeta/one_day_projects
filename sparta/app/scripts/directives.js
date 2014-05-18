'use strict';

angular.module('spartaApp')
  .directive('zModal', function() {
   return {
     restrict: 'A',
     link: function(scope, element) {
       scope.dismissModal = function() {
           element.modal('hide');
       };
     }
   };
});