var app = angular.module('Application', ['pascalprecht.translate','ngRoute', 'ngCookies', 'controllers', 'services', 'chart.js']);

app.config(function ($translateProvider, $routeProvider, $httpProvider, ChartJsProvider) {

    $routeProvider
        .when('/', {
            templateUrl: '/resources/angular/home/home.html',
            controller: 'HomeController'
        })
        .when('/weekly', {
            templateUrl: '/resources/angular/weekly/weekly.html',
            controller: 'WeeklyController'
        })
        .when('/contact', {
            templateUrl: '/resources/angular/contact/contact.html',
            controller: 'ContactController'
        })
        .when('/blog', {
            templateUrl: '/resources/angular/blog/blog.html',
            controller: 'BlogController'
        })
        .when('/announcements', {
            templateUrl: '/resources/angular/announcements/announcements.html',
            controller: 'AnnouncementsController'
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
        })
        .when('/statistic', {
            templateUrl: '/resources/angular/statistic/statistic.html',
            controller: 'StatisticController'
        })
        .when('/admin', {
            templateUrl: '/resources/angular/admin/admin.html',
            controller: 'AdminController'
        })
        .when('/aboutUs', {
            templateUrl: '/resources/angular/aboutUs.html'
        })
        .when('/specialOffers', {
            templateUrl: '/resources/angular/specialOffer.html'
        })
        .when('/loginOrRegister', {
            templateUrl: '/resources/angular/loginOrRegister/loginOrRegister.html',
            controller: 'LoginOrRegisterController'
        });

    $httpProvider.defaults.headers.common = {Accept: "application/json"};
    $httpProvider.defaults.headers.post = {"Content-Type": "application/json;charset=utf-8"};
    $httpProvider.defaults.xsrfCookieName = 'XSRF-TOKEN';
    $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-Token';


    $translateProvider.useUrlLoader('/messageBundle');
    $translateProvider.useStorage('UrlLanguageStorage');
    $translateProvider.preferredLanguage('en');
    $translateProvider.fallbackLanguage('en');

    ChartJsProvider.setOptions({
      colours: ['#FF5252', '#FF8A80'],
      responsive: false
    });
    ChartJsProvider.setOptions('Line', {
      datasetFill: false
    });

});