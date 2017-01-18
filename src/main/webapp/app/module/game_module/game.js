/**
 * Created by Administrator on 2017/1/5.
 */

angular.module('gameApp')
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
    .provider('giftService', function () {

        this.$get= ['$http','$state','$log','handlerExceptionService','$q',function($http,$state,$log,handlerExceptionService,$q) {
            return {
                /*
                 param gameGift 礼包实体
                 param gid 游戏id
                 param status 上架 1  下架 0
                 param oldGoodsId 旧的礼包id
                 */
                doGameGift:function (gameGift,gid,status,oldGoodsId) {
                    //pre validate
                    var data=gameGift;
                    if(oldGoodsId){
                        data.oldGoodsId=oldGoodsId;
                    }
                    if(!data.isTop){
                        data.isTop=0;
                    }
                    data.gid=gid;
                    data.status=status;
                    var url='/game/doGameGift';
                    var postCfg = {
                        headers: {'Content-Type': 'application/json; charset=UTF-8'}
                    };
                    $http.post(url, data, postCfg)
                        .success(function (resultData) {
                            if(resultData.data=="1"){
                                console.log(resultData.data);
                                alert('保存成功!');
                                window.location.reload();
                            }if(resultData.data=="2"){
                                alert('置顶失败,只能有一个置顶的游戏礼包!');
                                window.location.reload();
                            }else{
                                $log.debug(resultData.data);
                            }
                        }).error(handlerExceptionService.resourceExceptionHandler);

                },
                gameGiftUpdateStatus:function(gid,goodsId,status){
                    var gameGift={
                        gid:gid,
                        goodsId:goodsId,
                        status:status
                    };
                    var url='/game/doGameGiftStatus';
                    var postCfg = {
                        headers: {'Content-Type': 'application/json; charset=UTF-8'}
                    };
                    $http.post(url, gameGift, postCfg)
                        .success(function (resultData) {
                            if(resultData.data=="1"){
                                console.log(resultData.data);
                                if(status=="0"){
                                    alert('下线成功!');
                                }else if(status=="1"){
                                    alert('上线成功!');
                                }
                                window.location.reload();
                            }else{
                                $log.debug(resultData.data);
                            }
                        }).error(handlerExceptionService.resourceExceptionHandler);
                },
                /*
                 param goodsId 礼包id
                 */
                getGiftById:function (goodsId) {
                    //pre validate
                    var gameGoods={
                        goodsId:goodsId
                    };
                    var url='/game/getGiftById';
                    var postCfg = {
                        headers: {'Content-Type': 'application/json; charset=UTF-8'}
                    };
                    return $http.post(url, gameGoods, postCfg)
                        .success(function (resultData) {

                        }).error(handlerExceptionService.resourceExceptionHandler);

                }
 /*               ,testdeffer:function (name) {
                    function okToGreet(name) {
                        if(name=="1"){
                            return true;
                        }
                    }
                    var deferred = $q.defer();
                    deferred.notify('即将问候 ' + name + '.');
                    if (okToGreet(name)) {
                        deferred.resolve('你好, ' + name + '!');
                    } else {
                        deferred.reject('拒绝问候 ' + name + ' .');
                    }
                    return deferred.promise;
                }*/
            }
        }]

    })
    .controller('gameListCtrl',function($scope,serializeService,selectData,queryData,$filter,$compile) {
        console.log('gameListCtrl');
       var gameListUrl='/game/lists';
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
    .controller('gamePioneerListCtrl',function($scope,serializeService,selectData,queryData,$filter,$compile,$stateParams,$state,pioneerService) {
        console.log('gamePioneerListCtrl');
        var gamePioneerListUrl = '/game/pioneerList';
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
    .controller('gameGiftListCtrl',function($scope,serializeService,selectData,queryData,$filter,$compile,$stateParams,$state,$uibModal,$log,giftService) {
        console.log('gameGiftListCtrl');
        $scope.gid=$stateParams.gid;
        console.log($scope.gid);
        var url='/game/getGiftList';
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
                    'data': 'goodsId',
                    'title': '礼包id'
                }, {
                    'data': 'status',
                    'title': '任务状态',
                    'createdCell':function( cell, cellData, rowData, rowIndex, colIndex ){
                        // $compile("<a ng-click='gamePioneerUpdate(gid,Api.row("+rowIndex+").data().pid)'>编辑</a>&nbsp;&nbsp;<a  ng-click='gamePioneerUpdateStatus(gid,Api.row("+rowIndex+").data().pid,0)'>下线</a>")($scope).appendTo(cell);
                        if(rowData.status==0){
                            $(cell).html("已下架");
                        }else if(rowData.status==1){
                            if(rowData.isTop==1){
                                $(cell).html("置顶");
                            }else{
                                $(cell).html("已上架");
                            }
                        }
                    }
                    //'name':'mobile111111111'
                }, {
                    'data': 'gname',
                    'title': '游戏'
                }, {
                    'data': 'goodsName',
                    'title': '礼包名称'
                }, {
                    'data': 'updateTime',
                    'title': '更新时间',
                    render:function(value){
                        return $filter('date')(value,"yyyy-MM-dd HH:mm:ss");
                    }
                }, {
                    'data': 'finishTime',
                    'title': '结束时间',
                    render:function(value){
                        return $filter('date')(value,"yyyy-MM-dd HH:mm:ss");
                    }
                },  {
                    'data': 'operation',
                    'title': '操作',
                    'createdCell':function( cell, cellData, rowData, rowIndex, colIndex ){
                        // $compile("<a ng-click='gamePioneerUpdate(gid,Api.row("+rowIndex+").data().pid)'>编辑</a>&nbsp;&nbsp;<a  ng-click='gamePioneerUpdateStatus(gid,Api.row("+rowIndex+").data().pid,0)'>下线</a>")($scope).appendTo(cell);
                        if(rowData.status=="0"){
                            $compile("<a ng-click='gameGiftUpdate(gid,\""+rowData.goodsId+"\")'>编辑</a>&nbsp;&nbsp;<a  ng-click='gameGiftUpdateStatus(gid,\""+rowData.goodsId+"\",1)'>上线</a>")($scope).appendTo(cell);
                        }else if(rowData.status=="1"){
                            $compile("<a ng-click='gameGiftUpdate(gid,\""+rowData.goodsId+"\")'>编辑</a>&nbsp;&nbsp;<a  ng-click='gameGiftUpdateStatus(gid,\""+rowData.goodsId+"\",0)'>下线</a>")($scope).appendTo(cell);
                        }
                    }
                }]
            };
            return dtOption;
        };

        $scope.gameGiftAdd=function (gid) {
            var modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'myModalContent.html',
                controller: 'gameGiftAddCtrl',
                controllerAs: '$ctrl',
                size: 'md', //eg. 'sm' 'lg'
                resolve: {
                    gid: function () {
                        return gid;
                    }
                }
            });
            modalInstance.result.then(function (result) {// $uibModalInstance.close 方法回调
                console.log(result);
            }, function (dismiss) {  //$uibModalInstance.dismiss 方法回调
                console.log(dismiss);
                $log.info('Modal dismissed at: ' + new Date());
            });
        };
        $scope.gameGiftUpdate=function (gid,goodsId) {
            var modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'myModalContent.html',
                controller: 'gameGiftUpdateCtrl',
                controllerAs: '$ctrl',
                size: 'md', //eg. 'sm' 'lg'
                resolve: {
                    gid: function () {
                        return gid;
                    },
                    goodsId: function () {
                        return goodsId;
                    },
                }
            });
            modalInstance.result.then(function (result) {// $uibModalInstance.close 方法回调
                console.log(result);
            }, function (dismiss) {  //$uibModalInstance.dismiss 方法回调
                console.log(dismiss);
                $log.info('Modal dismissed at: ' + new Date());
            });
        };




        $scope.gameGiftUpdateStatus=function (gid,goodsid,status) {
            giftService.gameGiftUpdateStatus(gid,goodsid,status);
        };
        $scope.queryTable=function(){
//			console.log(selectData($scope.Api));
//			console.log(selectData($scope.Api)[0]);
//			console.log(selectData($scope.Api)[1]);
            queryData($scope.Api,url+serializeService('glGame')+'&gid='+ $scope.gid);

        };

    })
    .controller('gameGiftAddCtrl',function($scope,$uibModal,$uibModalInstance,giftService,gid){
        console.log('gameGiftAddCtrl');
        $scope.save=function(){
            giftService.doGameGift($scope.gift,gid,0);
            $uibModalInstance.close('ok');
        };
        $scope.saveonline=function(){
            giftService.doGameGift($scope.gift,gid,1);
            $uibModalInstance.close('ok');
        };
    /*    $scope.cancel=function(){
            $uibModalInstance.dismiss('cancel');
        };*/
       $scope.getGiftById = function (goodsId) {
           var promise= giftService.getGiftById(goodsId);
           promise.success(function (resultData) {

               if(resultData.data.code==0){// success

                   $scope.gift=resultData.data.data;
                   console.log($scope.gift)
               }
           });
       };


/*        var promise = giftService.testdeffer('1');
        debugger
        promise.then(function(greeting) {
            alert('成功: ' + greeting);
        }, function(reason) {
            alert('失败鸟: ' + reason);
        }, function(update) {
            alert('收到通知: ' + update);
        });*/
    })
    .controller('gameGiftUpdateCtrl',function($scope,$uibModal,$uibModalInstance,giftService,gid,goodsId){
        console.log('gameGiftUpdateCtrl');
        $scope.save=function(){
            giftService.doGameGift($scope.gift,gid,0,goodsId);
            $uibModalInstance.close('ok');
        };
        $scope.saveonline=function(){
            giftService.doGameGift($scope.gift,gid,1,goodsId);
            $uibModalInstance.close('ok');
        };
        /*    $scope.cancel=function(){
         $uibModalInstance.dismiss('cancel');
         };*/
        $scope.getGiftById = function (goodsId) {
            var promise= giftService.getGiftById(goodsId);
            promise.success(function (resultData) {

                if(resultData.data.code==0){// success

                    $scope.gift=resultData.data.data;
                    console.log($scope.gift)
                }
            });
        };
        $scope.getGiftById(goodsId);


        /*        var promise = giftService.testdeffer('1');
         debugger
         promise.then(function(greeting) {
         alert('成功: ' + greeting);
         }, function(reason) {
         alert('失败鸟: ' + reason);
         }, function(update) {
         alert('收到通知: ' + update);
         });*/
    })













