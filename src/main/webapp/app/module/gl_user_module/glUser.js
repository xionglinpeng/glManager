window.indexAppDependency.push("glUserApp");
angular.module('glUserApp', ['commonApp']).controller('glUserCtrl',
	function($scope) {

		$scope.glUserListDataTableOption = function() {
			return {
				ajax: {
					url: "/glUser/lists",
					dataSrc: function(json) {
						return json.datas;
					}
				},
				columns: [{
					'data': 'name',
					'title': '用户昵称'
				}, {
					'data': 'age',
					'title': '用户手机号'
				}, {
					'data': 'sex',
					'title': '用户状态'
				}, {
					'data': 'height',
					'title': '所属游戏'
				}, {
					'data': 'weight',
					'title': '游戏类型',
				}, {
					'data': 'beautiful',
					'title': '性别'
				}, {
					'data': 'beautiful',
					'title': '操作'
				}]
			};
		};
		setTimeout(function(){
			console.log($scope.Api);
		},3000);
		
		
	});