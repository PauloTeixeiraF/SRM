var app = angular.module('srmApp',['ui.router','ngStorage']);
 
app.constant('urls', {
    BASE: 'http://localhost:8080/SRM',
    SRM_SERVICE_API : 'http://localhost:8080/SRM/api/v1/'
});
 
app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
 
        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: '/list',
                controller:'SRMController',
                controllerAs:'ctrl',
                resolve: {
                    cls: function ($q, SRMService) {
                        console.log('Load all credit limits');
                        var deferred = $q.defer();
                        SRMService.loadAll().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);