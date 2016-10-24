(function() {
    'use strict';

    angular
        .module('anprjApp')
        .factory('Account', Account);

    Account.$inject = ['$resource'];

    function Account ($resource) {
        var service = $resource('api/account', {}, {
            'get': { method: 'GET', params: {}, isArray: false,
                interceptor: {
                    response: function(response) {
                        // expose response
                        return response;
                    }
                }
            },
            'updateUserEmail': { url:'api/account/email', method: 'POST', params: {}, isArray: false,
                interceptor: {
                    response: function(response) {
                        // expose response
                        return response;
                    }
                }
            },
        });

        return service;
    }
})();
