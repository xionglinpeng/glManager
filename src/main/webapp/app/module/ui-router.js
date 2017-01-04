angular.module('gl.ui.router', ['ui.router'])
	.config(function($stateProvider,$urlRouterProvider) {
		$urlRouterProvider.when('','home');
		$stateProvider.state('home',{
			url:'/home',
			templateUrl:'app/module/index_module/home.html'
		});
	});