(function() {
    'use strict';

    angular
        .module('anprjApp')
        .controller('SettingsController', SettingsController);

    SettingsController.$inject = ['Principal', 'Auth', 'AlertService','Phone', 'Address', 'RealIdentity', 'JhiLanguageService', '$translate'];

    function SettingsController (Principal, Auth, AlertService, Phone, Address, RealIdentity, JhiLanguageService, $translate) {
        var vm = this;

        vm.error = null;
        vm.login = null;
        vm.emailSave = emailSave;
        vm.phoneBind = phoneBind;
        vm.addressSave = addressSave;
        vm.identityBind = identityBind;
        vm.settingsAccount = null;
        vm.settingsPhone = {login:null, phone:null, key:null};
        vm.settingsAddress = {id:null, login:null, address:null, name:null, phone:null, postcode:null};
        vm.settingsIdentity = {login:null, name:null, gender:null, card:null};
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
            vm.login = account.login;
            vm.settingsPhone.login = account.login;
            vm.settingsAddress.loginn = account.login;
            vm.settingsIdentity.login = account.login;
        });
        JhiLanguageService.getAll().then(function (languages) {
            vm.languages = languages;
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
            Phone.bindKey(vm.settingsPhone).$promise;
        }
        function addressSave() {
            if (vm.settingsAddress.id !== null) {
                Address.update(vm.settingsAddress);
            } else {
                Address.save(vm.settingsAddress);
            }
        }
        function identityBind() {
            RealIdentity.save(vm.settingsIdentity);
        }
    }
})();
