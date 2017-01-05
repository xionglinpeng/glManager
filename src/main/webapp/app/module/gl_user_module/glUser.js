window.indexAppDependency.push("glUserApp");
angular.module('glUserApp', ['commonApp']).controller('glUserCtrl',
	function($scope) {

		$scope.glUserListDataTableOption = function() {
			return {
				serverSide: false, // 开启服务器模式
				ajax: {
					url: "/app/module/test_module/daraTables.json",
					dataSrc: function(json) {
						return json.datas;
					}
				},
				columns: [{
					'data': 'name',
					'title': '姓名'
				}, {
					'data': 'age',
					'title': '年龄'
				}, {
					'data': 'sex',
					'title': '性别'
				}, {
					'data': 'height',
					'title': '身高'
				}, {
					'data': 'weight',
					'title': '体重',
					render:function(data){
						return data;
					}
				}, {
					'data': 'beautiful',
					'title': '漂亮指数'
				}]
			};
		};
		setTimeout(function(){
			console.log($scope.Api);
		},3000);
		
		
	});