<!DOCTYPE html>
 
<html lang="en" ng-app="srmApp">
    <head>
        <title>${title}</title>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/app/app.css" rel="stylesheet"/>
    </head>
    <body>
 		<div ui-view>
 			<div class="generic-container">
		    <div class="panel panel-default">
		        <!-- Default panel contents -->
		        <div class="panel-heading"><span class="lead">Linha de Crédito </span></div>
		        <div class="panel-body">
		            <div class="formcontainer" ng-controller = "SRMController as ctrl">
		                <div class="alert alert-success" role="alert" ng-if="ctrl.cl.successMessage">{{ctrl.successMessage}}</div>
		                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
		                <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
		                    <input type="hidden" ng-model="ctrl.id" />
		                    <div class="row">
		                        <div class="form-group col-md-12">
		                            <label class="col-md-2 control-lable" for="uname">Nome</label>
		                            <div class="col-md-7">
		                                <input type="text" ng-model="ctrl.cl.name" id="uname" class="username form-control input-sm" placeholder="Entre com seu nome" required ng-minlength="3"/>
		                            </div>
		                        </div>
		                    </div>
		 
		                    <div class="row">
		                        <div class="form-group col-md-12">
		                            <label class="col-md-2 control-lable" for="creditLimit">Limite de Crédito</label>
		                            <div class="col-md-7">
		                                <input type="text" ng-model="ctrl.cl.creditLimit" id="creditLimit" class="form-control input-sm" placeholder="Entre com o limite de crédito." required ng-pattern="ctrl.onlyNumbers"/>
		                            </div>
		                        </div>
		                    </div>
		     
		                    <div class="row">
		                        <div class="form-group col-md-12">
		                            <label class="col-md-2 control-lable" for="risk">Risco</label>
		                            <div class="col-md-7">
		                            	<select ng-model="ctrl.cl.risk" id="risk" class="form-control input-sm" required>
										  <option value="" selected>Informe o risco</option>
										  <option value="A">Tipo A</option>
										  <option value="B">Tipo B</option>
										  <option value="C">Tipo C</option>
										</select>
		                            </div>
		                        </div>
		                    </div>
		 
		                    <div class="row">
		                        <div class="form-actions floatRight">
		                        	&nbsp;&nbsp;&nbsp;&nbsp;
		                            <input type="submit"  ng-click="ctrl.submit()" value="{{!ctrl.cl.id ? 'Salvar' : 'Atualizar'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
		                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Limpar</button>
		                        </div>
		                    </div>
		                </form>
		            </div>
		        </div>    
		    </div>
		    <div class="panel panel-default">
		        <!-- Default panel contents -->
		        <div class="panel-heading"><span class="lead">Lista de Limites de Crédito Avaliados </span></div>
		        <div class="panel-body" ng-controller = "SRMController as ctrl">
		            <div class="table-responsive">
		                <table class="table table-hover">
		                    <thead>
		                    <tr>
		                        <th>ID</th>
		                        <th>NOME</th>
		                        <th>LIMITE DE CRÉDITO</th>
		                        <th>TIPO DE RISCO</th>
		                        <th>TAXA DE JUROS</th>
		                        <th>VALOR FINAL</th>
		                        <th width="100"></th>
		                    </tr>
		                    </thead>
		                    <tbody>
		                    <tr ng-repeat="cl in ctrl.getAll()">
		                        <td>{{cl.id}}</td>
		                        <td>{{cl.name}}</td>
		                        <td>{{cl.creditLimit}}</td>
		                        <td>{{cl.risk}}</td>
		                        <td>{{cl.interestRate}}</td>
		                        <td>{{cl.total}}</td>
		                        <td><button type="button" ng-click="ctrl.remove(cl.id)" class="btn btn-danger custom-width">Remover</button></td>
		                    </tr>
		                    </tbody>
		                </table>      
		            </div>
		        </div>
		    </div>
		</div>
       
		</div>
        <script src="js/lib/angular.min.js"></script>
        <script src="js/lib/angular-ui-router.min.js" ></script>
        <script src="js/lib/localforage.js" ></script>
        <script src="js/lib/ngStorage.js"></script>
		<script src="js/app/app.js"></script>
		<script src="js/app/SRMController.js"></script>
		<script src="js/app/SRMService.js"></script>
    </body>
</html>