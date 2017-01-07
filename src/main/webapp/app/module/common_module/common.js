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
				$log.error("请求地址："+url+"\n请求方式："+method+"\n状态："+status+"\n"+errorMessage);
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
	})
	.factory('serializeService', function($document){
		return function serializeObj(formId){
			return  "?"+$document.find('#'+formId).serialize();
		};
	})

	.factory('selectData', function(){
		return function name(dataTableApi){
			return dataTableApi.rows('.active').data();
		};
	})
	.directive('cngDatatable', function(){
		return {
			scope: {//声明隔离作用域
				dtOption:"&cngPropertyDtOption",//用于接收dataTables配置选项
				dtReturnType:"@cngPropertyDtReturnType",//用于设置渲染表格之后的返回类型jQuery或Api
				dtReturnValue:"=cngPropertyDtReturnValue",//用于接收dataTables返回的值
				tableClass:"@cngPropertyTableClass",//用于设置表格的样式
				tableCaption:"@cngPropertyTableCaption",//设置表格caption元素内容
				active:"@cngPropertyActive",//是否开启复选框序号
				checkboxConfing:"=cngPropertyCheckboxConfing" //复选框序号配置
			},
			restrict: 'E', // E = Element, A = Attribute, C = Class, M = Comment
			template: '<table id="aa" class="{{tableClass}}" role="grid">'+
					   		'<caption>{{tableCaption}}</caption>'+
					   '</table>',
			replace: true,//模板替换元素
			link: function($scope, iElm, iAttrs, controller) {
				//dataTable配置对象
				var dtOption = $scope.dtOption();
				//开启复选框序号
				if(Boolean(eval($scope.active))){
					let checkbox = {
						"dataSrc" : function(dataAry){
							dataAry.forEach(function(value,index,array){
								value.checkboxs="<input type='checkbox'>"+(index+1);
								value.operation=null;
							});
							return dataAry;
						},
						"headerCallback":function(Api,thead){
							$(thead).find('input:checkbox').click(function(){
					    		var nodesTable = Api.column('serial:name').nodes();
								if ($(this).is(":checked")) {
									nodesTable.each(function(value, index, arrays) {
										$(value).find("input[type='checkbox']").get(0).checked=true;
										$(value).parent("tr").addClass('active');
									});
								} else {
									nodesTable.each(function(value, index, arrays) {
										$(value).find("input[type='checkbox']").get(0).checked=false;
										$(value).parent("tr").removeClass('active');
									});
								}
					    	});
						},
						"rowCallback":function(row){
							var rowDom = $(row);
					    	rowDom.find('input:checkbox').click(function(){
					    		if($(this).is(':checked')){
					    			rowDom.addClass('active');
					    		}else{
					    			rowDom.removeClass('active');
					    		}
					    	});
						}
					};
					dtOption.columns.unshift({
						'data':'checkboxs',
						'title':'<input type="checkbox">序号',
						'name':'serial',
						'orderable': false
					});
					if(angular.isUndefined(dtOption.headerCallback)){
						dtOption.headerCallback = function(thead, data, start, end, display){
							checkbox.headerCallback($scope.Table,thead);
						};
					}
					if(angular.isUndefined(dtOption.rowCallback)){
						dtOption.rowCallback = function( row, data, index){
							checkbox.rowCallback(row);
						};
					}
					if(angular.isUndefined(dtOption.dataSrc)){
						dtOption.ajax.dataSrc = function(json){
							return checkbox.dataSrc(json.dataObject);
						};
					}
					//开启序号复选框时，给予业务端自定义配置
					$scope.checkboxConfing = checkbox;
				}
				
				//设置表格class样式
				if(angular.isUndefined($scope.tableClass)){
					$scope.tableClass = 'table table-bordered table-hover';
				}
				//样式dataTable是dataTables.bootstrap.css扩展
				$scope.tableClass = $scope.tableClass+" dataTable";
				
				//执行dataTable
				if($scope.dtReturnType === "Api"||angular.isUndefined($scope.dtReturnType)){
					$scope.dtReturnValue = iElm.DataTable(dtOption);
					//赋值dataTable api对象给headerCallback调用
					$scope.Table = $scope.dtReturnValue;
				}else if($scope.dtReturnType === "jQuery"){
					$scope.dtReturnValue = iElm.dataTable(dtOption);
					//赋值dataTable api对象给headerCallback调用
					$scope.Table = $scope.dtReturnValue.api();
				}else{
					$scope.dtReturnValue = null;
				}
			}
		};
	});
