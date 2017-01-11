window.indexAppDependency.push("adviceFeedbackApp");
angular.module('adviceFeedbackApp', ['commonApp'])
	.config(function($stateProvider) {
		$stateProvider.state('adviceFeedback',{
			url:'/adviceFeedback',
			templateUrl:'/app/module/advice_feedback_module/adviceFeedback.html',
			controller:'adviceFeedbackCtrl'
		});
	})
	.constant('adviceFeedbackListUrl', '/app/module/test_module/adviceFeedback.json')
	.controller('adviceFeedbackCtrl', function($scope,adviceFeedbackListUrl,queryData,$filter){

		$scope.dtOption = function(){
			return {
				ajax:{url:adviceFeedbackListUrl},
				serverSide:false,
				columns:[
					{data:'time',name:'',title:'时间',
					render:function(data){
						return $filter('date')(data,'yyyy-MM-dd HH:mm:ss');
					}},
					{data:'number',name:'',title:'反馈编号'},
					{data:'nickname',name:'',title:'用户昵称'},
					{data:'mobile',name:'',title:'手机号'},
					{data:'content',name:'',title:'反馈内容'},
					{data:'status',name:'',title:'处理状态'},
					{data:'operation',name:'operation',title:'操作',orderable:false,
					render:function(data){
						return "<a>查看</a>&nbsp;&nbsp;<a>关贴</a>";
					}},

				]
			};
		};

		$scope.queryTable = function(){
			// queryData($scope.Api,adviceFeedbackListUrl);
			console.log($scope.asd);
		};
	});