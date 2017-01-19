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
			return dtApi.rows('.active').data();
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

	.directive('cngDate', function(){
		return {
			require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
			restrict: 'A',
			link: function($scope, iElm, iAttrs, controller) {
				var date = iElm.val();
				controller.$setViewValue(date);
			}
		};
	})


	.factory('datepickerService', function($compile){
		return function name($scope, $element, $attrs, $transclude){
			//daterangepicker插件封装在此指令中的默认配置
			this.daterangepickerOption = function(option){
				$element.daterangepicker($.extend({
					timePicker: true,//开启HH:mm
					timePickerSeconds:true,//开启ss
					timePicker24Hour:true,//24小时制
					showDropdowns:true,//日期下拉选择
					outsideClickConfig:false,//自定义属性(修改了插件源代码)，是否开启点击空白赋值日期
					isCustomDateFormat:true,//自定义属性(修改了插件源代码)，是否使用自定义日期格式yyyy-MM-dd HH:mm:ss。
					// clearVal:true,//自定义属性(修改了插件源代码),是否开启cancel.daterangepicker事件，点击clear时清除表单value值。
					initSetValue:false,//自定义属性(修改了插件源代码)，是否开启初始化设置默认值
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
				},option));
			};
			this.hiddenElement = function(name,model){
				//创建一个隐藏域jQuery对象
				var dateHidden = $('<input type="hidden" name="'+name+'" ng-model="'+model+'" cng-date>');
				//禁止表单元素手动操作
				$element.attr('readonly',true);
				//将隐藏域追加到表单元素之后
				$element.after(dateHidden);
				return dateHidden;
			};

			this.daterangepickerApply = function(datePicker,hidden){
				//为隐藏域value赋值
				hidden.val(datePicker.format('YYYY-MM-DD HH:mm:ss'));
				//编译元素使指令生效，赋值ng-model指令
				$compile(hidden)($scope);
			};

			this.daterangepickerCancel = function(hidden){
				//清空隐藏域的value值
				hidden.val('');
				//编译元素使指令生效，ng-model指令对应的值清空
				$compile(hidden)($scope);
			};

			this.applyEvent = function(fun){
				$element.on('apply.daterangepicker',fun);
			};


			this.cancelEvent = function(fun){
				$element.on('cancel.daterangepicker',fun);
			};
		};
	})



	.directive('cngDatepicker', function(datepickerService){
		return {
			restrict: 'A',
			controller:datepickerService,
			link: function($scope, iElm, iAttrs, controller) {
				//默认配置值
				var ngname = 'date',ngmodel='date',option = $scope.daterangepickerOption;

				if(angular.isDefined(iAttrs.ngname)){
					ngname = iAttrs.ngname;
				}
				if(angular.isDefined(iAttrs.ngmodel)){
					ngmodel = iAttrs.ngmodel;
				}
				var daterangepickerOption = $.extend(option, {singleDatePicker:true});
				
				controller.daterangepickerOption(daterangepickerOption);

				var date = controller.hiddenElement(ngname,ngmodel);
				
				controller.applyEvent(function(ev,picker){
					controller.daterangepickerApply(picker.startDate,date);
				});

				controller.cancelEvent(function(ev,picker){
					iElm.val('');
					controller.daterangepickerCancel(date);
				});
			}
		};
	})


	/**
	 * [data range picker插件封装的指令，用于为input元素添加日期控件]
	 */
	.directive('cngDaterangepicker', function(datepickerService){
		return {
			restrict: 'A', 
			controller:datepickerService,
			link: function($scope, iElm, iAttrs, controller) {
				//配置属性
				var ngmodel = iAttrs.ngmodel,
				ngname = iAttrs.ngname,
				option = $scope.daterangepickerOption;
				//获取ng-model指令属性，用于为$scope作用域赋值。
				var startDateModel = 'startDate',endDateModel = 'endDate';
				if(angular.isDefined(ngmodel)){
					let ngmodel = ngmodel.split("|");
					startDateModel = ngmodel[0];
					endDateModel = ngmodel[1];
				}
				//获取name属性配置，用于get请求序列化
				var startDateName = 'startDate',endDateName = 'endDate';
				if(angular.isDefined(ngname)){
					let ngmodel = ngname.split("|");
					startDateModel = ngmodel[0];
					startDateModel = ngmodel[1];
				}
				var startDate = controller.hiddenElement(startDateName,startDateModel),
				endDate = controller.hiddenElement(endDateName,endDateModel);

				controller.daterangepickerOption(option);
				controller.applyEvent(function(ev,picker){
					controller.daterangepickerApply(picker.startDate,startDate);
					controller.daterangepickerApply(picker.endDate,endDate);
				});
				
				controller.cancelEvent(function(ev,picker){
					iElm.val('');
					controller.daterangepickerCancel(startDate);
					controller.daterangepickerCancel(endDate);
				});
			}
		};
	})







	/**
	 * [拷贝指令，用于点击按钮赋值文本
	 * 使用方式：在按钮元素上添加指令cng-clipboard="要被复制的文本"
	 * 点击此按钮即可复制cng-clipboard的值。
	 * ]
	 */
	.directive('cngClipboard',function(){
		return function(scope, element, attrs){
			element.click(function(event) {
				//创建DOM元素
				var textarea = $('<textarea>').html(attrs.cngClipboard)
				.css({
					"position":"absolute",
					"z-index":999999,
					"top":"-9999px",
					"left":"-9999px"
				}).appendTo('body');
				//选中文本
				textarea.get(0).select();
				//执行浏览器复制命名
				document.execCommand("Copy");
				//复制完毕，删除DOM元素
				textarea.remove();

				alert('复制成功');
			});
		};
	})







	.directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function(scope, element, attrs, ngModel) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;
                element.bind('change', function(event){
                    scope.$apply(function(){
                        console.log(element[0].files);
                        modelSetter(scope, element[0].files[0]);
                    });
                    //附件预览
                    scope.file = (event.srcElement || event.target).files[0];
                    console.log(scope.file);
                    scope.getFile(scope.file.name);
                });
            }
        };
    }])
    .factory('fileReader', ["$q", "$log", function($q, $log){
        var onLoad = function(reader, deferred, scope) {
            return function () {
                scope.$apply(function () {
                    deferred.resolve(reader.result);
                });
            };
        };
        var onError = function (reader, deferred, scope) {
            return function () {
                scope.$apply(function () {
                    deferred.reject(reader.result);
                });
            };
        };
        var getReader = function(deferred, scope) {
            var reader = new FileReader();
            reader.onload = onLoad(reader, deferred, scope);
            reader.onerror = onError(reader, deferred, scope);
            return reader;
        };
        var readAsDataURL = function (file, scope) {
            var deferred = $q.defer();
            var reader = getReader(deferred, scope);
            reader.readAsDataURL(file);
            return deferred.promise;
        };
        return {
            readAsDataUrl: readAsDataURL
        };
    }]);
