(function() {
    'use strict';
    angular
        .module('anprjApp')
        .factory('Enterprise', Enterprise);

    Enterprise.$inject = ['$resource'];

    function Enterprise ($resource) {
        var resourceUrl =  'api/enterprises/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
