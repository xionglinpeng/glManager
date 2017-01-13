window.indexAppDependency.push("recommendApp");
angular.module('recommendApp',[])
	.directive('cngRecommend', function($compile){
		return {
			// name: '',
			// priority: 1,
			// terminal: true,
			// scope: {}, // {} = isolate, true = child, false/undefined = no change
			// controller: function($scope, $element, $attrs, $transclude) {},
			// require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
			// restrict: 'A', // E = Element, A = Attribute, C = Class, M = Comment
			// template: '',
			// templateUrl: '',
			// replace: true,
			// transclude: true,
			// compile: function(tElement, tAttrs, function transclude(function(scope, cloneLinkingFn){ return function linking(scope, elm, attrs){}})),
			link: function($scope, iElm, iAttrs, controller) {
				function IndexNum(){
			        $.each(iElm.find('div.dads-children'),function(){
			            var New_num=$(this).index()+1;
			            $(this).find('h3').html('关键词组'+New_num+'<input type="hidden" name="'+New_num+'">');
			        });
			    }
			    function Dad(){
			        iElm.find("div.box-body").dad({
			            draggable: 'span',
			            childrenClass:'dads-children',
			            callback: function(){
			                IndexNum();
			            }
			        });
			    }

			    function event(){
			        iElm.delegate("button","click",function(){
			            if($(this).attr("data-pain")=="remove"){
			                $(this).parents(".dads-children").animate({"height":0},500,function(){
			                    $(this).remove();
			                    IndexNum();
			                    --size;
			                });
			            }
			        });
			    }
				Dad();
        		event();
        		//获取容纳业务DOM元素的容器DIV对象
        		$scope.container = iElm.find("#container");
        		//获取业务DOM模板，模板id由属性data-template声明
        		$scope.template = $(iAttrs.template).html();
        		//为添加按钮赋值click事件
		        iElm.find("#add").click(function(event) {
		        	var size = $("div[cng-recommend]").find(".oneself").size()+1;
		        	var oneselfTemplate = $scope.template.replace(/<%=leng%>/g,size);
		        	
		        	var compileTemplate = $compile(oneselfTemplate)($scope);

		      //   	compileTemplate.find('input').iCheck({
				    //   checkboxClass: 'icheckbox_square-blue',
				    //   radioClass: 'iradio_square-blue',
				    //   increaseArea: '20%' // optional
				    // });

		        	//每次点击添加按钮，将模板追加到容器张
		        	$scope.container.append(compileTemplate);
	                Dad();
	                IndexNum();
		        });
			}
		};
	})
	.factory('recommendService', function($compile){
		return function name($scope,len){
			 var oneselfTemplate="";
			 for(let i=1;i<=len;i++){
			 	oneselfTemplate += $scope.template.replace(/<%=leng%>/g,i);
			 }
			 var compileTemplate = $compile(oneselfTemplate)($scope);
			 $scope.container.html(compileTemplate);
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
	.constant('searchKeywordsTemplate', $("searchKeywordsTemplate").html())
	.controller('focusByFigureCtrl', function(){
		console.log("focusByFigureCtrl....");	
	})
	.controller('searchKeywordsCtrl', function($scope,$http,handlerExceptionService,recommendService){
		console.log("searchKeywordsCtrl....");
		//获取所有的搜索关键词
		$http.get("/recommend/keywords")
		.success(function(data){
			$scope.keywords = data.data;
			var keywords = data.data;
			console.log(keywords);
			for(let i=1;i<=keywords.length;i++){
				if(angular.isUndefined($scope.keyword)){
					$scope.keyword = {};
				}
				if(angular.isUndefined($scope.keyword['keyword'+i])){
					$scope.keyword['keyword'+i] = {};
				}
				$scope.keyword['keyword'+i].keywordId = keywords[i-1].id;
				$scope.keyword['keyword'+i].keyword1 = keywords[i-1].keyword1;
				$scope.keyword['keyword'+i].keyword2 = keywords[i-1].keyword2;
				$scope.keyword['keyword'+i].keyword3 = keywords[i-1].keyword3;
				$scope.keyword['keyword'+i].save = keywords[i-1].save;
				$scope.keyword['keyword'+i].publish = keywords[i-1].publish;
				console.log($scope.keyword);
			}
			recommendService($scope,keywords.length);
			
		})
		.error(handlerExceptionService.httpExceptionHandler);
		
		
		$scope.save = function(){
			console.log($scope.keyword);
			//将请求对象转换此对象数据，用于服务端接收
			var dataAry = [];
			for(let item in $scope.keyword){
				if($scope.keyword.hasOwnProperty(item)){
					dataAry.push($scope.keyword[item]);
				}
			}
			//发送至服务端
			$http.post("/recommend/saveKeywords", dataAry)
			.success(function(data){
				console.log(data);
			})
			.error(handlerExceptionService.httpExceptionHandler);
		};


	});
