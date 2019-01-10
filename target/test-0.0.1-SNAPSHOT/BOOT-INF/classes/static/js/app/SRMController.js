'use strict';
 
angular.module('srmApp').controller('SRMController',
    ['SRMService', '$scope',  function( SRMService, $scope) {
 
        var self = this;
        self.cl = {};
        self.cls=[];
 
        self.submit = submit;
        self.getAll = getAll;
        self.create = create;
        self.update = update;
        self.remove = remove;
        self.edit = edit;
        self.reset = reset;
 
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
 
        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
 
        function submit() {
            console.log('Submitting');
            if (self.cl.id === undefined || self.cl.id === null) {
                console.log('Saving New Credit Limit', self.cl);
                create(self.cl);
            } else {
                update(self.cl, self.cl.id);
                console.log('Credit Limit updated with id ', self.cl.id);
            }
        }
 
        function create(cl) {
            console.log('About to create Credit Limit');
            SRMService.createCreditLimit(cl)
                .then(
                    function (response) {
                        console.log('Credit Limit created successfully');
                        self.successMessage = 'Avaliação de crédito criada com sucesso!';
                        self.errorMessage='';
                        self.done = true;
                        self.cl={};
                        $scope.myForm.$setPristine();
                        console.log('Carrega');
                        getAll();
                    },
                    function (errResponse) {
                        console.error('Error while creating Credit Limit');
                        self.errorMessage = 'Erro ao cadastrar uma avaliação de crédito: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
 
 
        function update(cl, id){
            console.log('About to update cl');
            SMRService.updateCreditLimit(cl, id)
                .then(
                    function (response){
                        console.log('Credit Limit updated successfully');
                        self.successMessage='Avaliação de limite de crédito atualizada com sucesso!';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Credit Limit');
                        self.errorMessage='Erro enquanto atualizava a avaliação de limite de crédito '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }
 
 
        function remove(id){
            console.log('About to remove Credit Limit with id '+id);
            SRMService.removeCreditLimit(id)
                .then(
                    function(){
                        console.log('Credit Limit '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing Credit Limit '+id +', Error :'+errResponse.data);
                    }
                );
        }
 
 
        function getAll(){
            return SRMService.getAll();
        }
 
        function edit(id) {
            self.successMessage='';
            self.errorMessage='';
            SRMService.getCreditLimit(id).then(
                function (cl) {
                    self.cl = cl;
                },
                function (errResponse) {
                    console.error('Error while removing credit limit ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.cl={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }
    ]);