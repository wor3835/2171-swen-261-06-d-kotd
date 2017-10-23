/**
 * This module exports a map of functions used in processing Ajax responses.
 */
define(function(){
    'use strict';

    return {
      
      /**
       * This function is a generic Ajax error handler.
       * It logs an error to the console and pops-up an alert.
       */
      handleErrorResponse: function(xhr, textStatus, error) {
        // error handling
        if (xhr.status === 404) {
          alert('This Ajax call has not been implemented.');
        } else if (xhr.status === 500) {
          alert('This Ajax call threw an exception: ' + error);
        } else if (xhr.status === 0) {
          alert('The WebCheckers server is down.');
        } else {
          alert('Unknown error (status=' + xhr.status + ') error: ' + error);
        }
      }
    };
});