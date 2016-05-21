var controllers = angular.module('controllers', ['services']);

controllers.controller('LanguageController', ['$scope','$translate','$location', function($scope, $translate, $location) {
    $scope.changeLanguage = function (locale) {
        $translate.uses(locale);
        $location.search('lang', locale);
        //window.location.href = $location.absUrl();
        //window.location.reload();
    };
}]);