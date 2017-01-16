window.indexAppDependency.push("adviceFeedbackApp");
angular.module('adviceFeedbackApp', ['commonApp'])
	.config(function($stateProvider) {
		$stateProvider.state('adviceFeedback',{
			url:'/adviceFeedback',
			templateUrl:'/app/module/advice_feedback_module/adviceFeedback.html',
			controller:'adviceFeedbackCtrl'
		});
	})
	.constant('adviceFeedbackListUrl', '/adviceFeedback/lists')
	.controller('adviceFeedbackCtrl', function(
			$scope,$filter,$compile,$http,
			handlerExceptionService,adviceFeedbackListUrl,
			queryData,serializeService,modalService){
		

		/**
		 * [operationDom 表格操作列DOM元素]
		 * @param  {[type]} rowIndex [行索引，用于click事件。]
		 * @param  {[type]} status   [意见反馈处理状态]
		 * @return {[type]}          [DOM元素字符串]
		 */
		var operationDom = function(rowIndex,status){
			var lookOverAdviceFeedback = "<button ng-click='lookOverAdviceFeedback("+ rowIndex+ ")' class='btn btn-success'>查看</button>";
			var blank = "&nbsp;&nbsp;";
			var closeAdviceFeedbackDisabled= "<button class='btn btn-info disabled'>处理</button>";
			var closeAdviceFeedback = "<button ng-click='closeAdviceFeedback("+rowIndex+")' class='btn btn-info'>处理</button>";
			return lookOverAdviceFeedback+blank+(status?closeAdviceFeedbackDisabled:closeAdviceFeedback);
		};


		/**
		 * [dtOption 反馈信息表格数据的配置选项]
		 * @return {[type]} [表格数据的配置选项]
		 */
		$scope.dtOption = function(){
			return {
				ajax:{
					url:adviceFeedbackListUrl,
					error:handlerExceptionService.dataTableExceptionHandler
				},
				order:[1,'desc'],
				columns:[
					{
						data : 'insertTime',
						name : 'insertDate',
						title : '时间',
						render : function(data) {
							return $filter('date')(data,'yyyy-MM-dd HH:mm:ss');
						}
					},
					{
						data : 'id',
						name : '_id',
						title : '反馈编号'
					},
					{
						data : 'nickname',
						name : 'nickname',
						title : '用户昵称'
					},
					{
						data : 'glUser.mobile',
						name : 'glUser.mobile',
						title : '手机号',
						orderable : false,
						render:function(data){
							return data?data:'';
						}
					},
					{
						data : 'content',
						name : 'content',
						title : '反馈内容'
					},
					{
						data : 'status',
						name : 'status',
						title : '处理状态',
						render:function(data){
							return data==='processed'?"已处理":"未处理";
						}
					},
					{
						data : 'operation',
						name : 'operation',
						title : '操作',
						orderable : false,
						createdCell : function(td, cellData,rowData, rowIndex, cellIndex) {
							var status = rowData.status=='processed';
							$(operationDom(rowIndex,status)).appendTo(td);
						}
					},
				],
				drawCallback:function(settings){
					$compile(this)($scope);
				}
			};
		};

		/**
		 * [queryTable 查询表格数据]
		 */
		$scope.queryTable = function(){
			console.log($scope.startDate);
			console.log($scope.endDate);
			console.log(adviceFeedbackListUrl+serializeService('adviceFeedbackId'));
			console.log($scope.sss);
//			
//			queryData($scope.Api,adviceFeedbackListUrl+serializeService('adviceFeedbackId'));
		};

		/**
		 * [lookOverAdviceFeedback 查看反馈信息详情]
		 * @param  {[type]} rowIndex [当前反馈信息的行索引]
		 */
		$scope.lookOverAdviceFeedback = function(rowIndex){
			$scope.singleAdviceFeedback = $scope.Api.row(rowIndex).data();
			//显示弹窗
			modalService('adviceFeedbackModal');
		};

		/**
		 * [closeAdviceFeedback 关闭反馈信息]
		 * @param  {[type]} rowIndex [当前反馈信息的行索引]
		 */
		$scope.closeAdviceFeedback = function(rowIndex){
			//获取当前关闭行的数据
			var rowData = $scope.Api.row(rowIndex).data();
			$http({
				url:"/adviceFeedback/dispose/"+rowData.id,
				method:"PUT",
			}).success(function(data){
				if(data.data.updatedExisting){
					alert('处理成功！');
				}
				//重新渲染表格
				$scope.Api.ajax.reload();
			}).error(handlerExceptionService.httpExceptionHandler);
		};
		
	});