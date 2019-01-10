'use strict';
 
angular.module('srmApp').factory('SRMService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
                loadAll: loadAllCreditLimits,
                getAll: getAllCreditLimits,
                getCreditLimit: getCreditLimit,
                createCreditLimit: createCreditLimit,
                updateCreditLimit: updateCreditLimit,
                removeCreditLimit: removeCreditLimit
            };
 
            return factory;
 
            function loadAllCreditLimits() {
                console.log('Fetching all CreditLimits');
                var deferred = $q.defer();
                $http.get(urls.SRM_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all credit limits');
                            $localStorage.cls = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.log('Error while loading credit limits');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function getAllCreditLimits(){
                return $localStorage.cls;
            }
 
            function getCreditLimit(id) {
                console.log('Fetching Credit Limit with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.SRM_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Credit Limit with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.log('Error while loading Credit Limit with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function createCreditLimit(cl) {
                console.log('Creating Credit Limit');
                var deferred = $q.defer();
                $http.post(urls.SRM_SERVICE_API + "create", cl)
                    .then(
                        function (response) {
                            loadAllCreditLimits();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.log('Error while creating Credit Limit : '+errResponse.data);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function updateCreditLimit(cl, id) {
                console.log('Updating Credit Limit with id '+id);
                var deferred = $q.defer();
                $http.put(urls.SRM_SERVICE_API + id, cl)
                    .then(
                        function (response) {
                            loadAllCreditLimits();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.log('Error while updating Credit Limit with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function removeCreditLimit(id) {
                console.log('Removing CreditLimit with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.SRM_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllCreditLimits();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.log('Error while removing CreditLimit with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
        }
    ]);