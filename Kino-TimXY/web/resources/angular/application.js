var app = angular.module('Application', ['pascalprecht.translate','ngRoute', 'ngCookies', 'controllers', 'services']);

app.config(function ($translateProvider, $routeProvider, $httpProvider) {

    $routeProvider
        .when('/', {
            templateUrl: '/resources/angular/home/home.html',
            controller: 'HomeController'
        })
        .when('/contact', {
            templateUrl: '/resources/angular/contact/contact.html',
            controller: 'ContactController'
        })
        .when('/login', {
            templateUrl: '/resources/angular/login/login.html',
            controller: 'LoginController'
        })
        .when('/login/:confirm', {
            templateUrl: '/resources/angular/login/login.html',
            controller: 'LoginController'
        })
        .when('/reset', {
            templateUrl: '/resources/angular/reset/reset.html',
            controller: 'ResetController'
        })
        .when('/newPassword/:token', {
            templateUrl: '/resources/angular/newPassword/newPassword.html',
            controller: 'NewPasswordController'
        })
        .when('/reserveTicket/:movieId', {
            templateUrl: '/resources/angular/reserveTicket/reserveTicket.html',
            controller: 'ReserveTicketController'
        })
        .when('/registration', {
            templateUrl: '/resources/angular/registration/registration.html',
            controller: 'RegistrationController'
        });

    $httpProvider.defaults.headers.common = {Accept: "application/json"};
    $httpProvider.defaults.headers.post = {"Content-Type": "application/json;charset=utf-8"};
    $httpProvider.defaults.xsrfCookieName = 'XSRF-TOKEN';
    $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-Token';


    $translateProvider.useUrlLoader('/messageBundle');
    $translateProvider.useStorage('UrlLanguageStorage');
    $translateProvider.preferredLanguage('en');
    $translateProvider.fallbackLanguage('en');

});