(function() {
    'use strict';
    angular
        .module('anprjApp')
        .factory('LendApply', LendApply);

    LendApply.$inject = ['$resource', 'DateUtils'];

    function LendApply ($resource, DateUtils) {
        var resourceUrl =  'api/lend-applies/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.applyDate = DateUtils.convertLocalDateFromServer(data.applyDate);
                        data.startDate = DateUtils.convertLocalDateFromServer(data.startDate);
                        data.completeDate = DateUtils.convertLocalDateFromServer(data.completeDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.applyDate = DateUtils.convertLocalDateToServer(copy.applyDate);
                    copy.startDate = DateUtils.convertLocalDateToServer(copy.startDate);
                    copy.completeDate = DateUtils.convertLocalDateToServer(copy.completeDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.applyDate = DateUtils.convertLocalDateToServer(copy.applyDate);
                    copy.startDate = DateUtils.convertLocalDateToServer(copy.startDate);
                    copy.completeDate = DateUtils.convertLocalDateToServer(copy.completeDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
