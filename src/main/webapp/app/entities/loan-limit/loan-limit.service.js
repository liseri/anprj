(function() {
    'use strict';
    angular
        .module('anprjApp')
        .factory('LoanLimit', LoanLimit);

    LoanLimit.$inject = ['$resource'];

    function LoanLimit ($resource) {
        var resourceUrl =  'api/loan-limits/:id';

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
