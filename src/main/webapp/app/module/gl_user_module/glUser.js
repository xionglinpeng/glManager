window.indexAppDependency.push("glUserApp");
angular.module('glUserApp', ['commonApp'])
	.config(function($stateProvider) {
		$stateProvider.state('glUser',{
			url:'/glUser',
			templateUrl:'/app/module/gl_user_module/glUser.html',
			controller:'glUserCtrl'
		});
	})
	.constant('tableUrl','/glUser/lists')
	.controller('glUserCtrl',function($scope,tableUrl,serializeService,selectData) {

		$scope.glUserListDataTableOption = function() {
			var dtOption = {
				ajax: {
					url: tableUrl,
				},
				columns: [{
					'data': 'nickname',
					'title': '用户昵称'
				}, {
					'data': 'mobile',
					'title': '用户手机号',
					'name':'mobile111111111'
				}, {
					'data': 'status',
					'title': '用户状态'
				}, {
					'data': 'playingGame.gname',
					'title': '所属游戏'
				}, {
					'data': 'favoriteTypes',
					'title': '游戏类型',
				}, {
					'data': 'gender',
					'title': '性别',
					'render':function(data,type,rowData,meta){
						return data=='M'?'男':(data=='F'?'女':data);
					}
				}, {
					'data': 'operation',
					'title': '操作',
					'orderable': false,
					'render':function(){
						return "<a>查看用户</a>&nbsp;&nbsp;<a>禁号操作</a>";
					}
				}]
			};
			return dtOption;
		};
		

		
		$scope.queryTable=function(){
//			console.log(selectData($scope.Api));
//			console.log(selectData($scope.Api)[0]);
//			console.log(selectData($scope.Api)[1]);
			 $scope.Api.ajax.url(tableUrl+serializeService('glUserId')).load();
		};
		
	});