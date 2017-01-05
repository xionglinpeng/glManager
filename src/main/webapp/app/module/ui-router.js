window.indexAppDependency.push("gl.ui.router");
angular.module('gl.ui.router', ['ui.router'])
	.config(function($stateProvider,$urlRouterProvider) {
		$urlRouterProvider.when('','home');
		$stateProvider.state('home',{
			url:'/home',
			templateUrl:'/app/module/index_module/home.html'
		});
		$stateProvider.state('glUser',{
			url:'/glUser',
			templateUrl:'/app/module/gl_user_module/glUser.html',
			controller:'glUserCtrl'
		});
	});