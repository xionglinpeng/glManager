window.indexAppDependency.push("glUserApp");
angular.module('glUserApp', ['commonApp'])
	.config(function($stateProvider) {
		$stateProvider.state('glUser',{
			url:'/glUser',
			templateUrl:'/app/module/gl_user_module/glUser.html',
			controller:'glUserCtrl'
		});
		$stateProvider.state('glUser.detail',{
			url:'/detail/:id',
			templateUrl:'/app/module/gl_user_module/glUserDetail.html',
			controller:'glUserDetailCtrl',
		});
		//用户相册
		$stateProvider.state('glUser.detail.photo',{
			url:'/photo',
			templateUrl:'/app/module/gl_user_module/glUserPhoto.html',
		});
		//用户动态
		$stateProvider.state('glUser.detail.dynamic',{
			url:'/dynamic',
			templateUrl:'/app/module/gl_user_module/glUserDynamic.html',
		});
		//用户礼包
		$stateProvider.state('glUser.detail.giftBag',{
			url:'/giftBag',
			templateUrl:'/app/module/gl_user_module/glUserGiftBag.html',
		});
		//用户尖兵任务
		$stateProvider.state('glUser.detail.pioneer',{
			url:'/pioneer',
			templateUrl:'/app/module/gl_user_module/glUserPioneer.html',
		});
	})
	/**
	 * 常量：加载表格数据的URL
	 */
	.constant('tableUrl','/glUser/lists')
	/**
	 * [description]
	 * @param  {[type]} $rootScope       [...]
	 * @param  {[type]} $scope           [...]
	 * @param  {[type]} tableUrl         [加载表格数据的URL常量]
	 * @param  {[type]} serializeService [序列化表单服务，common.js创建]
	 * @param  {[type]} selectData       [获取表格选择行数据服务，common.js创建]
	 * @param  {[type]} queryData        [查询表格数据服务，common.js创建]
	 * @param  {[type]} $compile         [AngualrJS编译服务]
	 * @param  {[type]} $state)          [ui.router模块的路由状态服务，用于路由切换之间传递参数]
	 */
	.controller('glUserCtrl',function(
			$rootScope,$scope,tableUrl,serializeService,selectData,queryData,$compile,$state) {

		//控制当前视图显示（默认）
		$rootScope.isView=true;

		/**
		 * [isGlUserViewTrue 控制当前视图显示]
		 * 此函数由子路由视图调用，当从子路由切换至主路由是，调用此函数使主路由视图显示。
		 */
		$rootScope.isGlUserViewTrue=function(){
			$rootScope.isView=true;
		};
		
		/**
		 * [dtOption 表格插件dataTales配置对象]
		 * 此函数的函数名必须与common.js下的指令cngDatatable的属性&cngPropertyDtOption保持一致。
		 * @return {[type]} [dataTales配置对象]
		 */
		$scope.dtOption = function() {
			return {
				ajax: {
					url: tableUrl,
				},
				order:[1,'desc'],//dataTable默认是以第0列排序，此处设置默认已第一列进行排序
				columns: [{
					'data': 'nickname',
					'title': '用户昵称',
					'name': 'nickname'
				}, {
					'data': 'mobile',
					'title': '用户手机号',
					'name':'mobile'
				}, {
					'data': 'status',
					'title': '用户状态',
					'name': 'status'
				}, {
					'data': 'playingGame',
					'title': '所属游戏',
					'name': 'playingGame.gname',
					'render':function(data){
						return data?data.gname:"";
					}
				}, {
					'data': 'favoriteTypes',
					'title': '游戏类型',
					'name': 'favoriteTypes'
				}, {
					'data': 'gender',
					'title': '性别',
					'name': 'gender',
					'render':function(data,type,rowData,meta){
						return data=='M'?'男':(data=='F'?'女':data);
					}
				}, {
					'data': 'operation',
					'title': '操作',
					'orderable': false,
					'createdCell':function(td, cellData, rowData, rowIndex, colIndex){
						var dom = "<a ng-click='stateUserDetail("+rowIndex+")' " +
								">查看用户</a>&nbsp;&nbsp;" +
								"<a ng-click='banAccount("+rowIndex+");'>禁号操作</a>";
						$compile(dom)($scope).appendTo(td);
					}
				}]
			};
		};

		/**
		 * [queryTable 查询表格数据]
		 * 由查询按钮click事件触发
		 * jQuery序列化表单参数。
		 */
		$scope.queryTable=function(){
			 queryData($scope.Api,tableUrl+serializeService('glUserId'));
		};
		
		/**
		 * [stateUserDetail 子路由切换]
		 * 因为需要向子路由传递参数，所以使用$state的go函数。
		 * 由click事件触发此函数，调用子路由，并且传递参数。
		 * @param  {[type]} rowIndex [参数数据所在表格行的索引]
		 */
		$scope.stateUserDetail = function(rowIndex){
			console.log($scope.Api.row(rowIndex).data());
			$state.go('glUser.detail',$scope.Api.row(rowIndex).data());
		};
		
		/**
		 * [banAccount 禁止账号操作]
		 * @param  {[type]} rowIndex [参数数据所在表格行的索引]
		 */
		$scope.banAccount = function(rowIndex){
			$scope.Api.row(rowIndex).data();
			alert("禁止账号操作");
		};
		
	})
	/**
	 * [子路由：用户详情 控制器]
	 * @param  {[type]} $scope              [...]
	 * @param  {[type]} $rootScope          [用于隐藏主路由视图]
	 * @param  {[type]} $stateParams  		[ui.router模块的路由状态参数服务，用于获取主路由传递的参数]
	 */
	.controller('glUserDetailCtrl',function($scope,$rootScope,$stateParams,$state,$http,handlerExceptionService){
		
		//控制主路由隐藏
		$rootScope.isView=false;
		
		$state.go('glUser.detail.photo');
		
		$scope.imgs = "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0" +
		"iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3J" +
		"nLzIwMDAvc3ZnIiB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgdmlld0JveD0iMCAwIDE0MCA" +
		"xNDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjxkZWZzLz48cmVjdCB3aWR0aD0iMTQwI" +
		"iBoZWlnaHQ9IjE0MCIgZmlsbD0iI0VFRUVFRSIvPjxnPjx0ZXh0IHg9IjQ1LjUiIHk9IjcwIiB" +
		"zdHlsZT0iZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWw" +
		"sIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXp" +
		"lOjEwcHQ7ZG9taW5hbnQtYmFzZWxpbmU6Y2VudHJhbCI+MTQweDE0MDwvdGV4dD48L2c+PC" +
		"9zdmc+";
		
		console.log($stateParams);
		
		$http.get("/glUser/glUserDetail?userid="+$stateParams.id)
		.success(function(data){
			console.log(data);
			$scope.glUser = data.data.glUser;
		}).error(handlerExceptionService.httpExceptionHandler);
		
		
		
		
	});