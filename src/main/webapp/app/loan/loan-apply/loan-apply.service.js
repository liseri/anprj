(function() {
    'use strict';
    angular
        .module('anprjApp')
        .factory('LoanApply', LoanApply);

    LoanApply.$inject = ['$resource', 'DateUtils'];

    function LoanApply ($resource, DateUtils) {
        var resourceUrl =  'api/loan-applies/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.applyDate = DateUtils.convertLocalDateFromServer(data.applyDate);
                        data.auditDate = DateUtils.convertLocalDateFromServer(data.auditDate);
                        data.loanDate = DateUtils.convertLocalDateFromServer(data.loanDate);
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
                    copy.auditDate = DateUtils.convertLocalDateToServer(copy.auditDate);
                    copy.loanDate = DateUtils.convertLocalDateToServer(copy.loanDate);
                    copy.completeDate = DateUtils.convertLocalDateToServer(copy.completeDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.applyDate = DateUtils.convertLocalDateToServer(copy.applyDate);
                    copy.auditDate = DateUtils.convertLocalDateToServer(copy.auditDate);
                    copy.loanDate = DateUtils.convertLocalDateToServer(copy.loanDate);
                    copy.completeDate = DateUtils.convertLocalDateToServer(copy.completeDate);
                    return angular.toJson(copy);
                }
            },
            'auditPass': {
                url: 'api/loan-applies/:id/auditPass',
                method: 'GET'
            },
            'auditReject': {
                url: 'api/loan-applies/:id/auditReject',
                method: 'GET'
            },
            'loan': {
                url: 'api/loan-applies/:id/loan',
                method: 'GET'
            },
            'repay': {
                url: 'api/loan-applies/:id/repay',
                method: 'GET'
            }
        });
    }
})();
