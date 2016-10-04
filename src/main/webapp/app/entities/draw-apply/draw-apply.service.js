(function() {
    'use strict';
    angular
        .module('anprjApp')
        .factory('DrawApply', DrawApply);

    DrawApply.$inject = ['$resource', 'DateUtils'];

    function DrawApply ($resource, DateUtils) {
        var resourceUrl =  'api/draw-applies/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.applyDate = DateUtils.convertLocalDateFromServer(data.applyDate);
                        data.canceledDate = DateUtils.convertLocalDateFromServer(data.canceledDate);
                        data.completedDate = DateUtils.convertLocalDateFromServer(data.completedDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.applyDate = DateUtils.convertLocalDateToServer(copy.applyDate);
                    copy.canceledDate = DateUtils.convertLocalDateToServer(copy.canceledDate);
                    copy.completedDate = DateUtils.convertLocalDateToServer(copy.completedDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.applyDate = DateUtils.convertLocalDateToServer(copy.applyDate);
                    copy.canceledDate = DateUtils.convertLocalDateToServer(copy.canceledDate);
                    copy.completedDate = DateUtils.convertLocalDateToServer(copy.completedDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
