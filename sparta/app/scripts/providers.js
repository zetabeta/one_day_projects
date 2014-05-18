'use strict';

angular.module('spartaApp')
  .provider('SessionProvider', function SessionProvider() {


    this.session = { loggedIn : localStorage.getItem('loggedIn') , user: localStorage.getItem('user') };
 
    this.$get = function() {

        var session = this.session;

        return {

            getSession: function() {
              return session;
            },

            isLoggedIn: function() {
              return session.loggedIn;
            },

            logIn: function(loggedIn, user) {
              session.loggedIn = loggedIn;
              session.user = user;
              if (user !== null) {
                localStorage.setItem('loggedIn' , loggedIn);
                localStorage.setItem('user', user);
              } else {
                localStorage.removeItem('loggedIn');
                localStorage.removeItem('user');
              }
            },

            getUser: function() {
              return session.user;
            }
        };
    };
 
});
