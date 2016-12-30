angular.module('loginApp', ['ngResource','commonApp'])
	//获取加密秘钥的URL
	.constant('encryptUrl', "login/publicKey")
	//登录的URL
	.constant('loginUrl', "login/login")
	//自动登录单选框所应用的指令
	.directive('cngChecked',function(){
		return function($scope,iElm,iAttrs){
			//这个是iCheck.js的在自定义事件
			iElm.on('ifChecked ifUnchecked',function(){
				$scope.user.autoLogin = $(this).is(':checked');
		    });
		};
	})
	/**
	 * [此指令用户密码加密，加密使用的是RSA算法]
	 * @param  {[type]} $resource [这个是ngResource模块的服务]
	 * @param  {[type]} encryptUrl [获取加密秘钥的URL常量]
	 * @param  {[type]} handlerExceptionService [自定义异常处理服务]
	 */
	.directive('cngEncrypt', ['$resource','encryptUrl','handlerExceptionService', function($resource,encryptUrl,handlerExceptionService) {
		return {
			// require属性声明对控制器的依赖。
			require: 'ngModel', //依赖于angularJS的ngModel指令控制器。
			restrict: 'E',//此指令只允许作为元素使用。
			link: function($scope, iElm, iAttrs, controller) {//链接函数用于密码加密
				//使用$respurce服务获取加密秘钥
				var resource = $resource(encryptUrl);
				var secretKey = null;
				new resource().$get().then(function(result){
					secretKey = result.data;
				},handlerExceptionService.resourceExceptionHandler);
				//使用秘钥加密(秘钥加密使用的第三方插件库(jsencrypt.js)。
				var encrypt = function() {
					var encrypt = new JSEncrypt();
					//设置公钥
					encrypt.setPublicKey(secretKey);
					//加密，并且将加密后的数据赋给作用域的user对象的一个新的属性。
					$scope.user.encryptPassword = encrypt.encrypt(controller.$viewValue);
				};
				//重置ng-model指令UI渲染器，使其进行加密操作
				//在ngModel指令绑定的属性值发生变化时将会调用此函数。
				controller.$render = function() {
					if (!angular.isUndefined(controller.$viewValue)) {
						encrypt();
					}
				};
			}
		};
	}])
	.controller('loginCtrl', ['$scope','$resource','loginUrl','handlerExceptionService','$log', function($scope,$resource,loginUrl,handlerExceptionService,$log) {

		/**
		 * [login 登录]
		 * @return {[type]} [void]
		 */
		$scope.login = function() {
			console.log($resource);
			var resource = new ($resource(loginUrl))({
				"email":$scope.user.email,
				"password":$scope.user.encryptPassword,
				"validCode":$scope.user.validCode,
				"autoLogin":angular.isUndefined($scope.user.autoLogin)?false:$scope.user.autoLogin
			});
			console.log(resource);
			resource.$save().then(function(result){
				if(result.data==='login success!'){
					loginSuccess();
				}else if(result.data==='login fail!'){
					loginFail();
				}else{
					$log.debug(result.data);
				}
			},handlerExceptionService.resourceExceptionHandler);
		};


		var loginSuccess = function(){

		};

		var loginFail = function(){

		};
	}]);