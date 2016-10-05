(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('SettingsController', SettingsController);

    SettingsController.$inject = ['Principal', 'Auth', 'Phone', 'JhiLanguageService', '$translate'];

    function SettingsController (Principal, Auth, Phone, JhiLanguageService, $translate) {
        var vm = this;

        vm.error = null;
        vm.emailSave = emailSave;
        vm.phoneBind = phoneBind;
        vm.settingsAccount = null;
        vm.settingsPhone = {login:null, phone:null, key:null};
        vm.success = null;

        /**
         * Store the "settings account" in a separate variable, and not in the shared "account" variable.
         */
        var copyAccount = function (account) {
            return {
                activated: account.activated,
                email: account.email,
                firstName: account.firstName,
                langKey: account.langKey,
                lastName: account.lastName,
                login: account.login
            };
        };

        Principal.identity().then(function(account) {
            vm.settingsAccount = copyAccount(account);
            vm.settingsPhone.login = account.login;
        });

        function emailSave () {
            Auth.updateAccount(vm.settingsAccount).then(function() {
                vm.error = null;
                vm.success = 'OK';
                Principal.identity(true).then(function(account) {
                    vm.settingsAccount = copyAccount(account);
                });
                JhiLanguageService.getCurrent().then(function(current) {
                    if (vm.settingsAccount.langKey !== current) {
                        $translate.use(vm.settingsAccount.langKey);
                    }
                });
            }).catch(function() {
                vm.success = null;
                vm.error = 'ERROR';
            });
        }
        function phoneKeyApply() {

        }
        function phoneBind() {
            Phone.bind(vm.phoneBind).then(function() {
                vm.error = null;
                vm.success = 'OK';
            }).catch(function() {
                vm.success = null;
                vm.error = 'ERROR';
            });
        }
    }
})();
