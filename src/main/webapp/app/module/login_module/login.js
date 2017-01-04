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
	 * [验证码指令，绑定click事件。]
	 * @return {[type]}             [void]
	 */
	.directive('cngKaptcha',function(){
		return function($scope,iElm,iAttrs){
			iElm.on('click',function(){
				$(this).attr('src',"kaptcha.jpg?"+(new Date().getTime()));
			});
		};
	})
	/**
	 * [此指令用户密码加密，加密使用的是RSA算法]
	 * @param  {[type]} $resource [这个是ngResource模块的服务]
	 * @param  {[type]} encryptUrl [获取加密秘钥的URL常量]
	 * @param  {[type]} handlerExceptionService [自定义异常处理服务]
	 */
	.directive('cngEncrypt', function($resource,encryptUrl,handlerExceptionService) {
		return {
			// require属性声明对控制器的依赖。
			require: 'ngModel', //依赖于angularJS的ngModel指令控制器。
			restrict: 'E',//此指令只允许作为元素使用。
			link: function($scope, iElm, iAttrs, controller) {//链接函数用于密码加密
				//使用$respurce服务获取加密秘钥
				var resource = $resource(encryptUrl);
				var secretKey = null;
				new resource().$get().then(function(result){
					secretKey = result.data.publicKey;
					autoLogin(result.data);
				},handlerExceptionService.resourceExceptionHandler);
				//当用户选择自动登录时进行自动登录操作，
				//定义登录账号密码
				var autoLogin = function(data){
					if(angular.isDefined(data.login)){
						$scope.user.email = data.login.email;
						$scope.user.password = data.login.password;
						$scope.user.encryptPassword = data.login.password;
					}
				};
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
					if (angular.isDefined(controller.$viewValue)) {
						//限制的密码长度不能超过24位，当密码长度大于等于100位时即是有自动登录cookie
						if(controller.$viewValue.length<100){
							encrypt();
						}else{
							//设置登录界面文本框自动登录时显示的数据
							controller.$setViewValue(controller.$viewValue.substring(0,24));
						}
					}
				};
			}
		};
	})
	.controller('loginCtrl', function($scope,$resource,loginUrl,handlerExceptionService,$log,$location) {

		$scope.user={};
		
		$scope.$watch('user.kaptcha',function(newValue,oldValue){
			$scope.loginForm.kaptcha.error = false;
		});
		$scope.$watch('user.email',function(newValue,oldValue){
			$scope.loginForm.error = false;
		});
		$scope.$watch('user.password',function(newValue,oldValue){
			$scope.loginForm.error = false;
		});
		
		/**
		 * [login 登录]
		 * @return {[type]} [void]
		 */
		$scope.login = function() {
			var resource = new ($resource(loginUrl))({
				"email":$scope.user.email,
				"password":$scope.user.encryptPassword,
				"kaptcha":$scope.user.kaptcha,
				"autoLogin":angular.isUndefined($scope.user.autoLogin)?false:$scope.user.autoLogin
			});
			resource.$save().then(function(resultData){
				if(resultData.data.situation==='login success!'){
					loginSuccess();
				}else if(resultData.data.situation==='login fail!'){
					loginFail(resultData.data.failMsg);
				}else{
					$log.debug(resultData.data);
				}
			},handlerExceptionService.resourceExceptionHandler);
		};

		/**
		 * [loginSuccess 处理登录成功]
		 * @return {[type]} [void]
		 */
		var loginSuccess = function(){
			window.location.reload();
		};
		
		/**
		 * [loginFail 处理登录失败]
		 * @param  {[type]} failMsg [失败的信息]
		 * @return {[type]}         [void]
		 */
		var loginFail = function(failMsg){
			if(failMsg==='kaptcha validated faild!'){
				$scope.loginForm.kaptcha.error = true;
			}else if(failMsg==='login error!'){
				$scope.loginForm.error = true;
			}
		};
	});