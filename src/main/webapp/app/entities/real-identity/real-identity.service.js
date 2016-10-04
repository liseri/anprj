(function() {
    'use strict';
    angular
        .module('anprjApp')
        .factory('RealIdentity', RealIdentity);

    RealIdentity.$inject = ['$resource', 'DateUtils'];

    function RealIdentity ($resource, DateUtils) {
        var resourceUrl =  'api/real-identities/:id';

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
            }
        });
    }
})();
