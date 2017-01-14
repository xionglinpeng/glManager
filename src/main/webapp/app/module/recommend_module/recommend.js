window.indexAppDependency.push("recommendApp");
angular.module('recommendApp',[])
	.directive('cngRecommend', function($compile,recommendEventService,iCheckService){
		return {
			link: function($scope, iElm, iAttrs, controller) {
				recommendEventService($scope,iElm);
        		//获取容纳业务DOM元素的容器DIV对象
        		$scope.container = iElm.find("#container");
        		//获取业务DOM模板，模板id由属性data-template声明
        		$scope.template = $(iAttrs.template).html();
        		//为添加按钮赋值click事件
		        iElm.find("#add").click(function(event) {
		        	//获取页面已有的搜索关键词组数量
		        	var size = iElm.find(".oneself").size()+1;
		        	//替换自定义表达式<%=leng%>
		        	var oneselfTemplate = $scope.template.replace(/<%=leng%>/g,size);
		        	//是新添加的搜索关键词组默认勾选以及排序。
		        	if(angular.isUndefined($scope.keyword)){
						$scope.keyword = {};
					}
					if(angular.isUndefined($scope.keyword['keyword'+size])){
						$scope.keyword['keyword'+size] = {};
					}
					$scope.keyword['keyword'+size].sort = size;
					$scope.keyword['keyword'+size].save = false;
					$scope.keyword['keyword'+size].keyword1 = undefined;
					$scope.keyword['keyword'+size].keyword2 = undefined;
					$scope.keyword['keyword'+size].keyword3 = undefined;
					//清空表达式
					var jqOneselfTemplate = $(oneselfTemplate);
					jqOneselfTemplate.find('input[type="checkbox"][id="save"]').attr('cng-recommend-icheck','');
					jqOneselfTemplate.find('input[type="checkbox"][id="publish"]').attr('cng-recommend-icheck','');
					
		        	//编译新搜索关键词组HTML字符串，是AngularJS指令生效。
		        	var compileTemplate = $compile(jqOneselfTemplate)($scope);
		        	//每次点击添加按钮，将模板追加到容器张
		        	$scope.container.append(compileTemplate);
	                recommendEventService($scope,iElm);
	                //处理复选框
	                iCheckService(compileTemplate);
		        });
			}
		};
	})



	/**
	 * [推荐系统拖拽事件以及删除事件服务]
	 */
	.factory('recommendEventService', function(){
		return function name($scope,iElm){
			if(angular.isUndefined(iElm)){
				iElm = $("div[cng-recommend]");
			}
			function IndexNum(){
		        $.each(iElm.find('div.dads-children'),function(){
		            var New_num=$(this).index()+1;
		            $(this).find('h3').html('关键词组'+New_num+'<input id="currentSort" type="hidden" value="'+New_num+'">');
		        });
		        //重定义排序
		        $.each(iElm.find('h3 #currentSort:hidden'),function(){
		        	var currentSort = this.value;
		        	var oldSort = $(this).parent("h3").next("#sort").val();
		        	$scope.keyword['keyword'+oldSort].sort = currentSort;
		        });
		    }
		    (function Dad(){
		        iElm.find("div.box-body").dad({
		            draggable: 'span',
		            childrenClass:'dads-children',
		            callback: function(){
		                IndexNum();
		            }
		        });
		    })();
		    (function event(){
		        iElm.delegate("button","click",function(){
		            if($(this).attr("data-pain")=="remove"){
		                $(this).parents(".dads-children").animate({"height":0},500,function(){
		                    $(this).remove();
		                    IndexNum();
		                });
		            }
		        });
		    })();
		};
	})





	/**
	 * [推荐系统初始化数据渲染DOM元素服务]
	 * @param  {[type]} $compile   [编译服务，编译追加到容器的HTML字符串]
	 * @param  {String} recommendEventService [推荐系统拖拽事件以及删除事件服务]
	 * @param  {String} $scope [控制器作用域，因为服务不能注入$scope服务对象]
	 * @param  {String} len [初始化元素对象数量]
	 */
	.factory('recommendService', function($compile,recommendEventService,iCheckService){
		return function name($scope,len){
			 var oneselfTemplate="";
			 for(let i=1;i<=len;i++){
				 //$scope.template已在指令cngRecommend获取声明
			 	oneselfTemplate += $scope.template.replace(/<%=leng%>/g,i);
			 }
			 var compileTemplate = $compile(oneselfTemplate)($scope);
			 //$scope.container已在指令cngRecommend获取声明
			 $scope.container.html(compileTemplate);
			 //处理拖拽事件
			 recommendEventService($scope);
			 //处理复选框
			 iCheckService(compileTemplate);
			 
		};
	})




	/**
	 * [复选框插件iCheck服务]
	 */
	.factory('iCheckService', function(){
		return function name(jqDom){
			var checkbox = jqDom.find('input:checkbox');
			checkbox.iCheck({
		    	checkboxClass: 'icheckbox_square-blue',
		    	radioClass: 'iradio_square-blue',
		    	increaseArea: '20%' // optional
			});
		};
	})






	/**
	 * [复选框选择事件指令]
	 */
	.directive('cngRecommendIcheck',function(){
		return function($scope,iElm,iAttrs){
			iElm.on('ifChecked ifUnchecked',function(){
				$scope.keyword['keyword'+iAttrs.recommendsort][iAttrs.recommendtype] = $(this).is(':checked');
			});
			if(iAttrs.cngRecommendIcheck==='true'){
				iElm.iCheck('check');
			}
		};
	})







	.config(function($stateProvider) {
		$stateProvider.state('focusByFigure',{
			url:"/focusByFigure",
			templateUrl:"/app/module/recommend_module/focusByFigure.html",
			controller:'focusByFigureCtrl'
		});
		$stateProvider.state('searchKeywords',{
			url:"/searchKeywords",
			templateUrl:'/app/module/recommend_module/searchKeywords.html',
			controller:'searchKeywordsCtrl'
		});
	})







	.controller('focusByFigureCtrl', function(){
		$scope.save = function(){
			//将请求对象转换此对象数据，用于服务端接收
			var dataAry = [];
			for(let item in $scope.keyword){
				if($scope.keyword.hasOwnProperty(item)){
					var keyword = $scope.keyword[item];	
					//删除取消保存的对象
					if(!$scope.keyword[item].save){
						delete $scope.keyword[item];
					}
					//转换成数组
					dataAry.push(keyword);
				}
			}
			//发送至服务端
			$http.post("/recommend/saveKeywords", dataAry)
			.success(function(data){
				if(data.data=="OK"){
					alert("保存成功");
					//重新加载页面数据
					getKeywords();
				}
			})
			.error(handlerExceptionService.httpExceptionHandler);
		};
	})
	






	.controller('searchKeywordsCtrl', function($scope,$http,handlerExceptionService,recommendService){
		//获取所有的搜索关键词
		var getKeywords = function(){
			$http.get("/recommend/keywords")
			.success(function(data){
				var keywords = data.data;
				for(let i=1;i<=keywords.length;i++){
					//如果没有，这创建对象
					if(angular.isUndefined($scope.keyword)){
						$scope.keyword = {};
					}
					if(angular.isUndefined($scope.keyword['keyword'+i])){
						$scope.keyword['keyword'+i] = {};
					}
					var keywordObj = 'keyword'+i;
					var j = i-1;
					//赋值，显示表单元素值
					$scope.keyword[keywordObj].keywordId = keywords[j].id;
					$scope.keyword[keywordObj].keyword1 = keywords[j].keyword1;
					$scope.keyword[keywordObj].keyword2 = keywords[j].keyword2;
					$scope.keyword[keywordObj].keyword3 = keywords[j].keyword3;
					$scope.keyword[keywordObj].save = keywords[j].save;
					$scope.keyword[keywordObj].publish = keywords[j].publish;
					$scope.keyword[keywordObj].sort = keywords[j].sort;
					//下面的两个属性用于展示搜索关键字状态，不与数据库字段关联
					$scope.keyword[keywordObj].saveStatus = keywords[j].save;
					$scope.keyword[keywordObj].publishStatus = keywords[j].publish;
				}
				//执行HTML DOM操作，显示页面元素
				recommendService($scope,keywords.length);
			})
			.error(handlerExceptionService.httpExceptionHandler);
		};

		//执行初始化加载页面数据
		getKeywords();
		
		/**
		 * [save 保存操作]
		 */
		$scope.save = function(){
			//将请求对象转换此对象数据，用于服务端接收
			var dataAry = [];
			for(let item in $scope.keyword){
				if($scope.keyword.hasOwnProperty(item)){
					var keyword = $scope.keyword[item];	
					//删除取消保存的对象
					if(!$scope.keyword[item].save){
						delete $scope.keyword[item];
					}
					//转换成数组
					dataAry.push(keyword);
				}
			}
			//发送至服务端
			$http.post("/recommend/saveKeywords", dataAry)
			.success(function(data){
				if(data.data=="OK"){
					alert("保存成功");
					//重新加载页面数据
					getKeywords();
				}
			})
			.error(handlerExceptionService.httpExceptionHandler);
		};


	});
