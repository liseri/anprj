(function() {
    'use strict';
    angular
        .module('anprjApp')
        .factory('LoanPrj', LoanPrj);

    LoanPrj.$inject = ['$resource', 'DateUtils'];

    function LoanPrj ($resource, DateUtils) {
        var resourceUrl =  'api/loan-prjs/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.activateDate = DateUtils.convertLocalDateFromServer(data.activateDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.activateDate = DateUtils.convertLocalDateToServer(copy.activateDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.activateDate = DateUtils.convertLocalDateToServer(copy.activateDate);
                    return angular.toJson(copy);
                }
            },
            'activate': {
                url: 'api/loan-prjs/activate/:id',
                method: 'GET'
            },
            'unactivate': {
                url: 'api/loan-prjs/unactivate/:id',
                method: 'GET'
            }
        });
    }
})();
