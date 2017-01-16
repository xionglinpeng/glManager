/**
 * Created by Administrator on 2017/1/5.
 */

angular.module('gameApp')
    .constant('gameListUrl','/game/lists')
    .constant('gamePioneerListUrl','/game/pioneerList')
    .constant('gameUrl','/game/game')
    .provider('pioneerService', function () {
        this.$get= ['$http','$state','$log','handlerExceptionService',function($http,$state,$log,handlerExceptionService) {
            return {
                /*
                 param gamePioneer 尖兵实体
                 param gid 游戏id
                 param status 上架 1  下架 0
                 */
                doGamePioneer:function (gamePioneer,gid,status) {
                    //pre validate
                    if(gamePioneer.ptype.length==0){
                        alert('至少选择一个任务类型');
                        return;
                    }
                    var gamePioneervo=gamePioneer;
                    if(status){
                        gamePioneervo.status=status;
                    }
                    var content = UM.getEditor('myEditor').getContent();
                    gamePioneervo.pdesc=content;
                    var url='/game/doGamePioneer';
                    var postCfg = {
                        headers: {'Content-Type': 'application/json; charset=UTF-8'}
                    };
                    $http.post(url, gamePioneervo, postCfg)
                        .success(function (resultData) {
                            if(resultData.data=="1"){
                                console.log(resultData.data);
                                alert('保存成功!');
                                $state.go('gamePioneerList',{"gid":gid});
                            }if(resultData.data=="2"){
                                alert('上线失败,只能有一个上线的尖兵任务!');
                                $state.go('gamePioneerList',{"gid":gid});
                            }else{
                                $log.debug(resultData.data);
                            }
                        }).error(handlerExceptionService.resourceExceptionHandler);

                },
                doGamePioneerStatus:function (gid,pid,status) {
                    var gamePioneer={
                        gid:gid,
                        pid:pid,
                        status:status
                    };
                    var url='/game/doGamePioneerStatus';
                    var postCfg = {
                        headers: {'Content-Type': 'application/json; charset=UTF-8'}
                    };
                    $http.post(url, gamePioneer, postCfg)
                        .success(function (resultData) {
                            if(resultData.data=="1"){
                                console.log(resultData.data);
                                if(status=="0"){
                                    alert('下线成功!');
                                }else if(status=="1"){
                                    alert('上线成功!');
                                }
                                window.location.reload();
                            }if(resultData.data=="2"){
                                alert('上线失败,只能有一个上线的尖兵任务!');
                            }else{
                                $log.debug(resultData.data);
                            }
                        }).error(handlerExceptionService.resourceExceptionHandler);

                }
            }
        }]

    })
    .controller('gameListCtrl',function($scope,gameListUrl,serializeService,selectData,queryData,$filter,$compile) {
        console.log('gameListCtrl');
        $scope.dtOption = function() {
            var dtOption = {
                ajax: {
                    url: gameListUrl,
                    dataSrc:function(json){
                        json.dataObject.forEach(function(value,index,array){
                            value.operation=null;
                        });
                        return json.dataObject;
                    }
                },
                //配置分页
                lengthMenu:[[5,10,25,50,100],[5,10,25,50,100]],//每页数据量
                pageLength:10,//初始每页记录条数
                "ordering": false,//不排序
                columns: [{
                    'data': 'id',
                    'title': '游戏id'
                }, {
                    'data': 'status',
                    'title': '游戏状态',
                    'render':function(value){
                        switch (value){
                            case "1":{
                                return "已上架";
                            }
                            case "0":{
                                return "已下架";
                            }
                        }
                        return "";
                    }
                    //'name':'mobile111111111'
                }, {
                    'data': 'gname',
                    'title': '游戏名称'
                }, {
                    'data': 'finnum',
                    'title': '任务完成数'
                }, {
                    'data': 'goodsgetnum',
                    'title': '礼包领取数'
                }, {
                    'data': 'createUser',
                    'title': '创建者'
                }, {
                    'data': 'putawayTime',
                    'title': '发布时间',
                    render:function(value){
                        return $filter('date')(value,"yyyy-MM-dd HH:mm:ss");
                    },
                    'orderable': false
                },  {
                    'data': 'operation',
                    'title': '操作',
                    'createdCell':function(td,value,trval,trindex,colindex){
                        $compile("<a ui-sref='gameUpdate'>编辑</a>&nbsp;&nbsp;<a ng-click='aaaa()'>下线</a>")($scope).appendTo(td);
                    }
                }]
            };
            return dtOption;
        };

        $scope.aaaa=function () {
            alert('1')
        }

        $scope.queryTable=function(){
//			console.log(selectData($scope.Api));
//			console.log(selectData($scope.Api)[0]);
//			console.log(selectData($scope.Api)[1]);
            queryData($scope.Api,gameListUrl+serializeService('glGame'));

        };

    })
    .controller('gameAddCtrl',function($scope,gameUrl,serializeService,selectData,queryData,$filter,$compile) {
        console.log('gameAddCtrl');

    })
    .controller('gameUpdateCtrl',function($scope,gameUrl,serializeService,selectData,queryData,$filter,$compile,$state) {
        console.log('gameUpdateCtrl');
        $scope.gamePioneerList=function(gid){
            $state.go('gamePioneerList',{"gid":gid})
        }
        $scope.gameGiftList=function(gid){
            $state.go('gameGiftList',{"gid":gid})
        }
    })
    .controller('gamePioneerListCtrl',function($scope,gamePioneerListUrl,serializeService,selectData,queryData,$filter,$compile,$stateParams,$state,pioneerService) {
        console.log('gamePioneerListCtrl');
        $scope.gid=$stateParams.gid;
        console.log($scope.gid);
        $scope.dtOption = function() {
            var dtOption = {
                ajax: {
                    url: gamePioneerListUrl+'?gid='+ $scope.gid,
                    dataSrc:function(json){
                        json.dataObject.forEach(function(value,index,array){
                            value.operation=null;
                        });
                        return json.dataObject;
                    }
                },
                //配置分页
                lengthMenu:[[5,10,25,50,100],[5,10,25,50,100]],//每页数据量
                pageLength:10,//初始每页记录条数
                "ordering": false,//不排序
                columns: [{
                    'data': 'pid',
                    'title': '任务id'
                }, {
                    'data': 'status',
                    'title': '任务状态',
                    'render':function(value){
                        switch (value){
                            case "1":{
                                return "已上架";
                            }
                            case "0":{
                                return "已下架";
                            }
                        }
                        return "";
                    }
                    //'name':'mobile111111111'
                }, {
                    'data': 'gname',
                    'title': '游戏'
                }, {
                    'data': 'pname',
                    'title': '任务名称'
                }, {
                    'data': 'finnum',
                    'title': '任务完成数'
                }, {
                    'data': 'createUser',
                    'title': '创建者'
                }, {
                    'data': 'putawayTime',
                    'title': '发布时间',
                    render:function(value){
                        return $filter('date')(value,"yyyy-MM-dd HH:mm:ss");
                    }//,'orderable': false
                },  {
                    'data': 'operation',
                    'title': '操作',
                    'createdCell':function( cell, cellData, rowData, rowIndex, colIndex ){
                        // $compile("<a ng-click='gamePioneerUpdate(gid,Api.row("+rowIndex+").data().pid)'>编辑</a>&nbsp;&nbsp;<a  ng-click='gamePioneerUpdateStatus(gid,Api.row("+rowIndex+").data().pid,0)'>下线</a>")($scope).appendTo(cell);
                        if(rowData.status=="0"){
                            $compile("<a ng-click='gamePioneerUpdate(gid,\""+rowData.pid+"\")'>编辑</a>&nbsp;&nbsp;<a  ng-click='gamePioneerUpdateStatus(gid,\""+rowData.pid+"\",1)'>上线</a>")($scope).appendTo(cell);
                        }else if(rowData.status=="1"){
                            $compile("<a ng-click='gamePioneerUpdate(gid,\""+rowData.pid+"\")'>编辑</a>&nbsp;&nbsp;<a  ng-click='gamePioneerUpdateStatus(gid,\""+rowData.pid+"\",0)'>下线</a>")($scope).appendTo(cell);
                        }
                    }
                }]
            };
            return dtOption;
        };

        $scope.gamePioneerAdd=function (gid) {
            $state.go('gamePioneerAdd',{"gid":gid});
        };
        $scope.gamePioneerUpdate=function (gid,pid) {
            $state.go('gamePioneerUpdate',{"gid":gid,"pid":pid });
        };
        $scope.gamePioneerUpdateStatus=function (gid,pid,status) {
            pioneerService.doGamePioneerStatus(gid,pid,status)
        };
        $scope.queryTable=function(){
//			console.log(selectData($scope.Api));
//			console.log(selectData($scope.Api)[0]);
//			console.log(selectData($scope.Api)[1]);
            queryData($scope.Api,gamePioneerListUrl+serializeService('glGame')+'&gid='+ $scope.gid);

        };

    })
    .controller('gamePioneerAddCtrl',function($scope,$stateParams,pioneerService) {
        console.log('gamePioneerAddCtrl');
        $scope.gid=$stateParams.gid;
        console.log($scope.gid);
        $scope.gamePioneer={
            gid:$scope.gid,
            ptype:[],
            putawayTime:new Date(),
            status:"0"
        };
        $scope.doGamePioneer=  pioneerService.doGamePioneer;
    })
    .controller('gamePioneerUpdateCtrl',function($scope,$stateParams,$resource,pioneerService,handlerExceptionService,$log) {
        console.log('gamePioneerUpdateCtrl');
        var getUrl='/game/getGamePioneer';
        $scope.gid=$stateParams.gid;
        $scope.pid=$stateParams.pid;

        var Pioneer = $resource(
            getUrl,
            {gid:$scope.gid, pid:$scope.pid});
        Pioneer.get(function(resultData){
            if(resultData.data){
                $scope.gamePioneer = resultData.data;
                $scope.gamePioneer.putawayTime=new Date($scope.gamePioneer.putawayTime);
                UM.getEditor('myEditor').setContent($scope.gamePioneer.pdesc, true);//true 追加
                console.log($scope.gamePioneer);
            }else{
                $log.debug(resultData.data);
            }
        },handlerExceptionService.resourceExceptionHandler);

        $scope.doGamePioneer = pioneerService.doGamePioneer;

    })
    .controller('gameGiftListCtrl',function($scope,serializeService,selectData,queryData,$filter,$compile,$stateParams,$state,pioneerService) {
        console.log('gameGiftListCtrl');
        $scope.gid=$stateParams.gid;
        console.log($scope.gid);
        var url='/game/pioneerList';
        $scope.dtOption = function() {
            var dtOption = {
                ajax: {
                    url: url+'?gid='+ $scope.gid,
                    dataSrc:function(json){
                        json.dataObject.forEach(function(value,index,array){
                            value.operation=null;
                        });
                        return json.dataObject;
                    }
                },
                //配置分页
                lengthMenu:[[5,10,25,50,100],[5,10,25,50,100]],//每页数据量
                pageLength:10,//初始每页记录条数
                "ordering": false,//不排序
                columns: [{
                    'data': 'pid',
                    'title': '任务id'
                }, {
                    'data': 'status',
                    'title': '任务状态',
                    'render':function(value){
                        switch (value){
                            case "1":{
                                return "已上架";
                            }
                            case "0":{
                                return "已下架";
                            }
                        }
                        return "";
                    }
                    //'name':'mobile111111111'
                }, {
                    'data': 'gname',
                    'title': '游戏'
                }, {
                    'data': 'pname',
                    'title': '任务名称'
                }, {
                    'data': 'finnum',
                    'title': '任务完成数'
                }, {
                    'data': 'createUser',
                    'title': '创建者'
                }, {
                    'data': 'putawayTime',
                    'title': '发布时间',
                    render:function(value){
                        return $filter('date')(value,"yyyy-MM-dd HH:mm:ss");
                    }//,'orderable': false
                },  {
                    'data': 'operation',
                    'title': '操作',
                    'createdCell':function( cell, cellData, rowData, rowIndex, colIndex ){
                        // $compile("<a ng-click='gamePioneerUpdate(gid,Api.row("+rowIndex+").data().pid)'>编辑</a>&nbsp;&nbsp;<a  ng-click='gamePioneerUpdateStatus(gid,Api.row("+rowIndex+").data().pid,0)'>下线</a>")($scope).appendTo(cell);
                        if(rowData.status=="0"){
                            $compile("<a ng-click='gamePioneerUpdate(gid,\""+rowData.pid+"\")'>编辑</a>&nbsp;&nbsp;<a  ng-click='gamePioneerUpdateStatus(gid,\""+rowData.pid+"\",1)'>上线</a>")($scope).appendTo(cell);
                        }else if(rowData.status=="1"){
                            $compile("<a ng-click='gamePioneerUpdate(gid,\""+rowData.pid+"\")'>编辑</a>&nbsp;&nbsp;<a  ng-click='gamePioneerUpdateStatus(gid,\""+rowData.pid+"\",0)'>下线</a>")($scope).appendTo(cell);
                        }
                    }
                }]
            };
            return dtOption;
        };

        $scope.gamePioneerAdd=function (gid) {
            $state.go('gamePioneerAdd',{"gid":gid});
        };
        $scope.gamePioneerUpdate=function (gid,pid) {
            $state.go('gamePioneerUpdate',{"gid":gid,"pid":pid });
        };
        $scope.gamePioneerUpdateStatus=function (gid,pid,status) {
            pioneerService.doGamePioneerStatus(gid,pid,status)
        };
        $scope.queryTable=function(){
//			console.log(selectData($scope.Api));
//			console.log(selectData($scope.Api)[0]);
//			console.log(selectData($scope.Api)[1]);
            queryData($scope.Api,url+serializeService('glGame')+'&gid='+ $scope.gid);

        };

    })













