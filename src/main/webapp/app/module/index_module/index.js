window.indexAppDependency.unshift("ui.router");
/*window.indexAppDependency.unshift("angular-bootstrap-select");
window.indexAppDependency.unshift("angular-bootstrap-select.extra");*/
angular.module('indexApp',window.indexAppDependency)
.config(function($stateProvider,$urlRouterProvider) {
	$urlRouterProvider.when('','home');
	$stateProvider.state('home',{
		url:'/home',
		templateUrl:'/app/module/index_module/home.html'
	});
	
})
.controller('indexCtrl', function($scope,$http,handlerExceptionService){

	$scope.quit = function(){
		$http({
			url:'/glm',
			method:'DELETE'
		}).error(handlerExceptionService.httpExceptionHandler)
		.success(function(data){
			if(data.data=="OK"){
				window.location.href="glm";
			}
		});
	};
});

