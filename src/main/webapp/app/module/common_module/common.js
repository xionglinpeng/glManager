angular.module('commonApp',[])
	/**
	 * [自定义异常处理服务：
	 * 如果使用的是$resource服务进行ajax操作，只需要将$resource服务请求的异常处理函数替换为handlerExceptionService.resourceExceptionHandler；
	 * 如果使用的是$http服务进行ajax操作，只需要将$http服务请求的异常处理函数替换为handlerExceptionService.httpExceptionHandler。
	 * 上述两个函数会在控制台打印出后台程序报出的错误，并且格式一致，如此设计是为了在程序上线之后也可以很轻松的看到后台程序服务抛出的异常信息，因为线上服务器开发人员无权访问。
	 * 除此只要还会跳转到404或500页面。]
	 */
	.provider('handlerExceptionService',function(){
		this.$get = function($log){
			/**
			 * [errorPage 404或者500页面跳转。]
			 * @param  {[type]} status [状态码。]
			 * @return {[type]}        [void]
			 */
			var errorPage = function(status){
				if(status==404){
					
				}else{
					
				}
			};
			/**
			 * [log 控制台日志打印。]
			 * @param  {[type]} exceptionData [服务返回的异常对象。]
			 * @param  {[type]} url           [发生异常请求的URL。]
			 * @param  {[type]} method        [发生异常请求的方法。]
			 * @param  {[type]} status        [发生异常请求的http状态码。]
			 * @param  {[type]} errorMessage  [发生异常请求的异常信息。]
			 * @return {[type]}               [void]
			 */
			var log = function(exceptionData,url,method,status,errorMessage){
				$log.error(exceptionData);
				$log.error("请求地址："+url+"\n请求方式："+method+"\n状态："+status+"\n"+data);
			};
			return {
				/**
				 * [resourceExceptionHandler $resource服务请求异常处理。]
				 * @param  {[type]} exceptionData [$resource服务异常请求的封装对象。]
				 * @return {[type]}               [void]
				 */
				resourceExceptionHandler:function(exceptionData){
					log(exceptionData,exceptionData.config.url,exceptionData.config.method,exceptionData.status,exceptionData.data.data);
					errorPage();
				},
				/**
				 * [httpExceptionHandler $http服务请求异常处理]
				 * @param  {[type]} data          [服务器返回的数据。]
				 * @param  {[type]} status        [http请求的状态码。]
				 * @param  {[type]} header        [http请求头函数。]
				 * @param  {[type]} exceptionData [$http服务异常请求的封装对象。]
				 * @return {[type]}               [void]
				 */
				httpExceptionHandler:function(data,status,header,exceptionData){
					log(exceptionData,exceptionData.url,exceptionData.method,status,data.data);
					errorPage();
				},
			};
		};
	});