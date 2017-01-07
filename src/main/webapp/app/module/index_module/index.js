window.indexAppDependency.unshift("ui.router");
angular.module('indexApp',window.indexAppDependency)
.config(function($stateProvider,$urlRouterProvider) {
	$urlRouterProvider.when('','home');
	$stateProvider.state('home',{
		url:'/home',
		templateUrl:'/app/module/index_module/home.html'
	});
	
})
.controller('indexCtrl', function($scope){
	console.log(1111111111);
});
