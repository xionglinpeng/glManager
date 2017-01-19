window.indexAppDependency.push("managerApp");
angular.module('managerApp',[])
.config(function($stateProvider,$urlRouterProvider) {
	$urlRouterProvider.when('','home');
	$stateProvider.state('register',{
		url:'/register',
		templateUrl:'/app/module/manager_module/register.html',
		controller:'registerCtrl'
	});
})
.controller('registerCtrl', function($scope,$http,handlerExceptionService){
	

	$scope.register = function(){
		console.log($scope.GlmUser);
		$http.post('/glmanager/register', $scope.GlmUser).
		error(handlerExceptionService.httpExceptionHandler).
		success(function(data){
			console.log(data);
			if(data.data=='OK'){
				alert('注册成功！');
			}
		});
	};


});

