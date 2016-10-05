(function() {
    'use strict';
    angular
        .module('anprjApp')
        .factory('Phone', Phone);

    Phone.$inject = ['$resource', 'DateUtils'];

    function Phone ($resource, DateUtils) {
        var resourceUrl =  'api/phones/:id';

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
            'bindApply': {
                url: 'api/phones/apply',
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    return angular.toJson(copy);
                }
            },
            'bindKey': {
                url: 'api/phones/bind',
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
