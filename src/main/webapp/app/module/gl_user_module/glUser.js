window.indexAppDependency.push("glUserApp");


var glUserApp = angular.module('glUserApp', ['commonApp']);



/**
 * [当前指令用于nav菜单样式的触发]
 */
glUserApp.directive('cngNavActive',function($location){
	return function($scope, iElm, iAttrs){
		var lis = iElm.find('li');
		
		var path = $location.$$path.split('/');
		var endPath = path[path.length-1];
		lis.each(function(index,elm){
		 	$(this).click(function(){
		 		lis.removeClass('active');
		 		$(this).addClass('active');
		 	});
		});
	};
});



/**
 * [glUser模块路由配置]
 */
glUserApp.config(function($stateProvider) {
	$stateProvider.state('glUser',{
		url:'/glUser',
		templateUrl:'/app/module/gl_user_module/glUser.html',
		controller:'glUserCtrl'
	});
	$stateProvider.state('glUser.detail',{
		url:'/detail/:id/photo',
		templateUrl:'/app/module/gl_user_module/glUserDetail.html',
		controller:'glUserDetailCtrl',
	});
	//用户相册
	$stateProvider.state('glUser.detail.photo',{
		url:'/photo',
		templateUrl:'/app/module/gl_user_module/glUserPhoto.html',
		controller:'photoCtrl'
	});
	//用户动态
	$stateProvider.state('glUser.detail.dynamic',{
		url:'/dynamic',
		templateUrl:'/app/module/gl_user_module/glUserDynamic.html',
		controller:'dynamicCtrl'
	});
	//用户礼包
	$stateProvider.state('glUser.detail.giftBag',{
		url:'/giftBag',
		templateUrl:'/app/module/gl_user_module/glUserGiftBag.html',
		controller:'giftBagCtrl'
	});
	//用户尖兵任务
	$stateProvider.state('glUser.detail.pioneer',{
		url:'/pioneer',
		templateUrl:'/app/module/gl_user_module/glUserPioneer.html',
		controller:'pioneerCtrl'
	});
});



/**
 * 常量：加载表格数据的URL
 */
glUserApp.constant('glUserTableUrl','/glUser/lists');




/**
 * [description]
 * @param  {[type]} $rootScope       [...]
 * @param  {[type]} $scope           [...]
 * @param  {[type]} glUserTableUrl   [加载表格数据的URL常量]
 * @param  {[type]} serializeService [序列化表单服务，common.js创建]
 * @param  {[type]} selectData       [获取表格选择行数据服务，common.js创建]
 * @param  {[type]} queryData        [查询表格数据服务，common.js创建]
 * @param  {[type]} $compile         [AngualrJS编译服务]
 * @param  {[type]} $state)          [ui.router模块的路由状态服务，用于路由切换之间传递参数]
 */
glUserApp.controller('glUserCtrl',function(
		$rootScope,$scope,$http,glUserTableUrl,handlerExceptionService,serializeService,selectData,queryData,$compile,$state) {

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
	 * 加载游戏类型
	 */
	$http.get('/glUser/gameType')
	.error(handlerExceptionService.httpExceptionHandler)
	.success(function(data){
		$scope.gameType = {};
		for(let item of data.data){
			$scope.gameType[item.gtName] = item.gtDesc;
		}
	});
	
	
	
	
	/**
	 * [dtOption 表格插件dataTales配置对象]
	 * 此函数的函数名必须与common.js下的指令cngDatatable的属性&cngPropertyDtOption保持一致。
	 * @return {[type]} [dataTales配置对象]
	 */
	$scope.dtOption = function() {
		return {
			ajax: {
				url: glUserTableUrl,
			},
			order:[1,'desc'],//dataTable默认是以第0列排序，此处设置默认已第一列进行排序
			columns: [{
				'data': 'nickname',
				'title': '昵称',
				'name': 'nickname'
			}, {
				'data': 'mobile',
				'title': '手机号',
				'name':'mobile'
			}, {
				'data': 'status',
				'title': '状态',
				'name': 'status',
				'render':function(data){
					return data==='NORMAL'?'正常':(data==='FORBID'?'封禁':data);
				}
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
				'name': 'favoriteTypes',
				render:function(data){
					if($.type(data)==='array'){
						var favoriteTypes = "";
						data.forEach(function(elm,index,array){
							favoriteTypes+="["+$scope.gameType[elm.gtName]+"] ";
						});
						return favoriteTypes;
					}else{
						return data;
					}
				}
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
					var dom = "<a ng-click='stateUserDetail("+rowIndex+")' class='btn btn-success'" +
							">查看用户</a>&nbsp;&nbsp;" +
							"<a ng-click='banAccount(\"FORBID\",\""+rowData.id+"\");' class='btn btn-info'>禁号操作</a>";
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
		 queryData($scope.Api,glUserTableUrl+serializeService('glUserId'));
	};
	

	/**
	 * [stateUserDetail 子路由切换]
	 * 因为需要向子路由传递参数，所以使用$state的go函数。
	 * 由click事件触发此函数，调用子路由，并且传递参数。
	 * @param  {[type]} rowIndex [参数数据所在表格行的索引]
	 */
	$scope.stateUserDetail = function(rowIndex){
		$state.go('glUser.detail',$scope.Api.row(rowIndex).data());
	};
	

	/**
	 * [banAccount 禁止账号操作]
	 * @param  {[type]} rowIndex [参数数据所在表格行的索引]
	 */
	$scope.banAccount = function(status,glUserId){
		$http({
			url:'/glUser/forbidNormal/'+status+'/'+glUserId,
			method:'PUT'
		}).error(handlerExceptionService.httpExceptionHandler)
		.success(function(data){
			if(data.data=='OK'){
				alert("操作成功");
				$scope.Api.ajax.reload();
				$rootScope.glUserDetail.status=status;
			}
		});
	};
	
});



/**
 * [子路由：用户详情 控制器]
 * @param  {[type]} $scope              [...]
 * @param  {[type]} $rootScope          [用于隐藏主路由视图]
 * @param  {[type]} $stateParams  		[ui.router模块的路由状态参数服务，用于获取主路由传递的参数]
 */
glUserApp.controller('glUserDetailCtrl',function($scope,$rootScope,$stateParams,$state,$http,handlerExceptionService){
	
	//控制主路由隐藏
	$rootScope.isView=false;
	
	//执行首次加载默认路由视图
	$state.go('glUser.detail.photo');
	
	//查询用户详情数据
	var glUserDetailHttp = $http.get("/glUser/glUserDetail/"+$stateParams.id);
	glUserDetailHttp.error(handlerExceptionService.httpExceptionHandler);
	glUserDetailHttp.success(function(result){
		//用户信息
		$rootScope.glUserDetail = result.data.glUser;
		//用户数据数量信息
		$scope.data = result.data;
	});
	

	
});




/**
 * [用户相册]
 * @param  {[type]}       [description]
 * @return {[type]}       [description]
 */
glUserApp.controller('photoCtrl',function($scope,$http,$stateParams,handlerExceptionService){
	

	/**
	 * [photoHttp 初始化查询当前用户的所有相册。]
	 * @type {[type]}
	 */
	var photoHttp = $http.get('/glUser/photo/'+$stateParams.id);
	photoHttp.error(handlerExceptionService.httpExceptionHandler);
	photoHttp.success(function(result){
		$scope.photo = result.data;
	});
	
	/**
	 * [getPhoto 相册图片大图]
	 * @param  {[type]} photo [点击的相册对象]
	 */
	$scope.getPhoto = function(photo){
		$scope.singlePhoto = photo;
	};
});



/**
 * [用户动态]
 * @param  {[type]}       [description]
 * @return {[type]}       [description]
 */
glUserApp.controller('dynamicCtrl',function($scope,$http,$stateParams,handlerExceptionService){

	//初始化查询用户董涛
	var dynamicHttp = $http.get('/glUser/dynamic/'+$stateParams.id);
	dynamicHttp.error(handlerExceptionService.httpExceptionHandler);
	dynamicHttp.success(function(result){
		$scope.dynamic = result.data;
	});
	

	/**
	 * [getDynamic 动态详情弹窗数据]
	 * @param  {[type]} dynamic [当前弹出的动态对象]
	 */
	$scope.getDynamic = function(dynamic){
		$scope.singleDynamic = dynamic;
	};


	/**
	 * [delDynamic 逻辑关闭动态]
	 * @return {[type]} [description]
	 */
	$scope.closeDynamic = function(dynamic){
		var delDynamicHttp = $http({
			url:'/glUser/colseDynamic/'+$scope.singleDynamic.id+'/2',
			method:'PUT'
		});
		delDynamicHttp.error(handlerExceptionService.httpExceptionHandler);
		delDynamicHttp.success(function(result){
			if(result.data=='OK'){
				alert('关闭成功！');
			}
		});
	};
});



/**
 * [用户礼包]
 * @param  {[type]}       [description]
 * @return {[type]}       [description]
 */
glUserApp.controller('giftBagCtrl',function($scope,$http,$stateParams,handlerExceptionService){

	/**
	 * [giftBagHttp 初始化加载当前用户的礼包]
	 * @type {[type]}
	 */
	var giftBagHttp = $http.get('/glUser/giftBag/'+$stateParams.id);
	giftBagHttp.error(handlerExceptionService.httpExceptionHandler);
	giftBagHttp.success(function(result){
		$scope.giftBag = result.data;
		console.log($scope.giftBag);
	});
	

	/**
	 * [getDiftBag 礼包详情弹窗数据]
	 * @param  {[type]} dynamic [当前弹出的动态对象]
	 */
	$scope.getDiftBag = function(giftBag){
		$scope.singleGiftBag = giftBag;
	};


});


/**
 * 用户尖兵任务的URL
 */
glUserApp.constant('pioneerUrl', '/glUser/pioneer');



/**
 * [用户尖兵任务]
 * @param  {[type]}       [description]
 * @return {[type]}       [description]
 */
glUserApp.controller('pioneerCtrl',function($scope,$http,$compile,$stateParams,$filter,modalService,pioneerUrl,queryData,handlerExceptionService){
	
	
	$scope.dtOption = function(){
		return {
			ajax:{url:pioneerUrl+"?uid="+$stateParams.id},
			order:[1,'desc'],
			pageLength:10,
			language:{
				sEmptyTable : "<div class='text-center'>没有尖兵任务</div>"
			},
			columns:[
			    {'data':'imgUrl','name':'imgUrl','title':'游戏LOGO','render':function(data){
			    	return '<img src="'+data+'" class="img-circle" width="50px"></img>';
			    }},
			    {'data':'finishTime','name':'finishTime','title':'完成时间','render':function(data){
			    	return data?$filter('date')(data,'yyyy-MM-dd HH:mm:ss'):data;
			    }},
			    {'data':'status','name':'status','title':'状态','render':function(data){
			    	return data?(data==2?'已完成':'进行中'):data;
			    }},
			    {'data':'gname','name':'gname','title':'游戏名称'},
			    {'data':'operation','title':'任务说明','orderable':false,
			    'createdCell':function(td, cellData, rowData, rowIndex, colIndex){
			    	$compile('<button class="btn btn-success" ng-click="lookTaskExplain('+rowIndex+')">查看任务说明</button>')($scope).appendTo(td);
			    }},
			]
		};
	};
	
	$scope.readStatus = function(status){
		queryData($scope.pioneerApi,pioneerUrl+"?status="+status+"&&uid="+$stateParams.id);
	};

	/**
	 * [lookTaskExplain 查看任务说明]
	 * @param  {[type]} rowIndex [查询的任务行索引]
	 */
	$scope.lookTaskExplain = function(rowIndex){
		var rowData = $scope.pioneerApi.row(rowIndex).data();
		var pioneerDetailHttp = $http.get('/glUser/pioneerDetail/'+rowData.gid);
		pioneerDetailHttp.error(handlerExceptionService.httpExceptionHandler);
		pioneerDetailHttp.success(function(data){
			if(data.data===0){
				alert("尖兵任务不存在。");
			}else{
				$scope.pioneerDetail = data.data;
				modalService('gameTaskModal');
			}
		});
	};
	

});

