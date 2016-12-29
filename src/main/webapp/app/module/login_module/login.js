angular.module('loginApp', ['ngResource'])
	//获取加密秘钥的URL
	.constant('encryptUrl', "login/publicKey")
	.directive('cngEncrypt', ['$resource', 'encryptUrl', function($resource, encryptUrl) {
		return {
			// require属性声明对控制器的依赖。
			require: 'ngModel',
			restrict: 'E',
			link: function($scope, iElm, iAttrs, controller) {
				//获取加密秘钥
				var resource = $resource(encryptUrl);
				console.log(resource);
				var secretKey = null;
				new resource().$get().then(function(result){
					if(result.code){
						secretKey = result.data;
					}
				});
				//使用秘钥加密
				var encrypt = function() {
					var encrypt = new JSEncrypt();
					encrypt.setPublicKey(secretKey);
					$scope.encryptPassword = encrypt.encrypt(controller.$viewValue);
				};
				//重置ng-model指令UI渲染器，使其进行加密操作
				controller.$render = function() {
					if (!angular.isUndefined(controller.$viewValue)) {
						encrypt();
					}
				};
			}
		};
	}])
	.controller('loginCtrl', ['$scope','$document', function($scope,$document) {
		
		/**
		 * [login 登录]
		 * @return {[type]} [void]
		 */
		$scope.login = function() {
			$scope.password = $scope.encryptPassword;
		};
	}]);