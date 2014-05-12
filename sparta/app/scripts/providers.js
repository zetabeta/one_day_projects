'use strict';

angular.module('spartaApp')
  .provider('SessionProvider', function SessionProvider() {


    this.session = {'loggedIn':false };
 
    this.$get = function() {
        var session = this.session;
        return {
            getSession: function() {
                return session;
            },

            isLoggedIn: function() {
              return session.loggedIn;
            }
        };
    };
 
});
