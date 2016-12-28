angular.module('loginApp', ['ngResource'])
	//获取加密秘钥的URL
	.constant('encryptUrl', "")
	.directive('cngEncrypt', ['$resource', 'encryptUrl', function($resource, encryptUrl) {
		return {
			// require属性声明对控制器的依赖。
			require: 'ngModel',
			restrict: 'E',
			link: function($scope, iElm, iAttrs, controller) {
				//获取加密秘钥
				// var resource = $resource(encryptUrl);
				// console.log(resource.query());
				var secretKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL9kad7UFxBsa7dJEmcCcTvvybbPNl3sluIvz1/YgqqT5JEIqo5b/CbeIp9SA6J44AWof8/a0Kw2FEyTCFwx9mAz4C7xgcttgIRscRrZMxUNYdvipL3X/S088eiElkXQETM/C9RZX/PCQGD/v+hJLaGHAuSGHpZWuEZC+AWUJaqDAgMBAAECgYEAm1Wd73HnPjcgvWMbAmujB2g4KJzInTZVx0HSZxHMCzF5ApUsB9p0Lf9/zhq8RHMiLrVw9nu7fOlvVC7JTDpooIanDRf95FEABY97mt/02bwAIUDeNZ/JXOhx8SPzTJpzDsZ9OPgJwB6veDtnHqLwDMh91ybSD5RODO7kyp/1oLECQQD0b40N0frPzge+gnRfvGne+V2LA/4neNVCYF7aAsjpZ/ZHsWsJfGWJmwbP33OeT1oFEtwK8f0Q9cyiXr7PI55fAkEAyHJvAvxAoMGxdeALzuAN3wiuY8wwHqRMV49YVZFle4Rk4JQQNX3FVj9XsJFyR6uepeCsBYByn3SOryeFLhoeXQJAIfWRgjcHAnHcT5JuOpLBnrHEVjjyyWcXMcaTxvQWF59S7vjuJv46WRjJpH2l4XuixQuuuHKQNxf/GxvQpJSNPwJADDNS6HacJGzFtWwGq/fdQEcR/kcOYr8TWn0CHms0Jxl+3mCEwAzAtygx2TBxKUWb6XLMZaVU1LnOeYDJTW4PjQJAUXfYzAVOz78fIfhIcipbh56D2/jPLiel9LFNEksCimqdxLTBY3vtAqggJlNvID44RsZT3ULPxIXwUO/1IrFm3A==";
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