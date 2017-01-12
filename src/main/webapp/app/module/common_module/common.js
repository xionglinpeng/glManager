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
				/**
				 * [dataTableExceptionHandler dataTbales请求异常处理]
				 * @param  {[type]} error [dataTable请求异常返回的数据对象]
				 */
				dataTableExceptionHandler:function(error){
					log("","","GET",error.status,error.responseJSON.data);
					errorPage();
				}
			};
		};
	})

	/**
	 * [显示弹窗服务]
	 * @param  {[type]} [弹窗ID]
	 */
	.factory('modalService', function($document){
		return function name(modalId){
			$document.find('#'+modalId).modal();
		};
	})

	/**
	 * [form表单序列化服务，内部使用的是jQuery的serialize函数。]
	 * @param {[formId]} [form表单的id值]
	 * @return {[type]}  [序列化的字符串，以'?'开头，例："?name=123&age=321..."]
	 */
	.factory('serializeService', function($document){
		return function serializeObj(formId){
			return  "?"+$document.find('#'+formId).serialize();
		};
	})

	/**
	 * [dataTable相关服务，用于获取选中行的数据]
	 * @param  {[dtApi]} 	[dataTable对象]
	 * @return {[type]}  [选中行的原生数据，非表格展示数据]
	 */
	.factory('selectData', function(){
		return function name(dtApi){
			return dataTableApi.rows('.active').data();
		};
	})

	/**
	 * [dataTable表格条件查询]
	 * @param  {[dtApi]} 	[dataTable对象]
	 * @param  {[url]} 		[查询url]
	 * @return {[type]}     [void]
	 */
	.factory('queryData', function(){
		return function name(dtApi,url){
			dtApi.ajax.url(url).load();
		};
	})

	/**
	 * [dataTable指令]
	 */
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
			template: '<table id="dataTable" class="{{tableClass}}" role="grid">'+
					   		'<caption>{{tableCaption}}</caption>'+
					   '</table>',
			replace: true,//模板替换元素
			link: function($scope, iElm, iAttrs, controller) {

				$scope.checkboxConfing = {
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

				//dataTable配置对象
				var dtOption = $scope.dtOption();
				//开启复选框序号
				if(Boolean(eval($scope.active))){
					
					//设置表格索引列
					dtOption.columns.unshift({
						'data':'checkboxs',
						'title':'<input type="checkbox">序号',
						'name':'serial',
						'orderable': false
					});
					if(angular.isUndefined(dtOption.headerCallback)){
						dtOption.headerCallback = function(thead, data, start, end, display){
							//在客户端模式下headerCallback将会执行两次，第一次是表哥还没有渲染的时候，
							//第二次是表格已经渲染完毕，有返回值得时候，
							//因为第一回调时$scope.Table为undefined，所以这里判断$scope.Table是否为undefined，
							//以第二次回调为准。
							if(angular.isDefined($scope.Table)){
								$scope.checkboxConfing.headerCallback($scope.Table,thead);
							}
						};
					}
					if(angular.isUndefined(dtOption.rowCallback)){
						dtOption.rowCallback = function( row, data, index){
							$scope.checkboxConfing.rowCallback(row);
						};
					}
					if(angular.isUndefined(dtOption.dataSrc)){
						dtOption.ajax.dataSrc = function(json){
							return $scope.checkboxConfing.dataSrc(json.dataObject);
						};
					}
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
	})

	/**
	 * [data range picker插件封装的指令，用于为input元素添加日期控件]
	 */
	.directive('cngDaterangepicker', function(){
		// Runs during compile
		return {
			// name: '',
			// priority: 1,
			// terminal: true,
//			scope: {}, // {} = isolate, true = child, false/undefined = no change
			// controller: function($scope, $element, $attrs, $transclude) {},
			// require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
			restrict: 'A', // E = Element, A = Attribute, C = Class, M = Comment
			// template: function(iElm,Aa){
			// 	return iElm.after(
			// 	'<input type="hidden" id="startDate" name="startTime">'+
			// 	'<input type="hidden" id="endDate" name="endTime">');
			// },
			// templateUrl: '',
//			replace: true,
			// transclude: true,
			// compile: function(tElement, tAttrs, function transclude(function(scope, cloneLinkingFn){ return function linking(scope, elm, attrs){}})),
			link: function($scope, iElm, iAttrs, controller) {
				console.log(iAttrs);
				var ngmodel = iAttrs.ngmodel;

				var startDate = $('<input type="text" id="startDate" ng-model="startDate" name="startTime">');
				var endDate = $('<input type="hidden" id="endDate" name="endTime">');
				iElm.after(endDate);
				iElm.after(startDate);

				iElm.daterangepicker({
					timePicker: true,
					timePickerSeconds:true,
					timePicker24Hour:true,
					showDropdowns:true,
					outsideClickConfig:false,
					 isCustomDateFormat:true,
					clearVal:true,
					initSetValue:false,
					locale: {
				        "separator": " - ",
				        "applyLabel": "确认",
				        "cancelLabel": "清除",
				        "fromLabel": "From",
				        "toLabel": "To",
				        "customRangeLabel": "Custom",
				        "weekLabel": "W",
				        "daysOfWeek": ["日","一","二","三","四","五","六"],
				        "monthNames": ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
				        "firstDay": 1
				    }
				});
				iElm.on('apply.daterangepicker',function(ev,picker){
					startDate.val(picker.startDate.format('YYYY-MM-DD HH:mm:ss'));
					endDate.val(picker.endDate.format('YYYY-MM-DD HH:mm:ss'));

					//如果设置了ngModel属性（注意：不是ng-model指令）
					if(angular.isDefined(ngmodel)){
						//分割ngmodel属性值
						for(let item of ngmodel.split("|")){
							console.log(item.indexOf("."))
							//以.分割符进行分割迭代
							for(let property of item.split(".")){
								if(angular.isUndefined($scope[property])){
									$scope[property] = {};
								}else{
									
								}
							}
						}
					}
				});
			}
		};
	});
