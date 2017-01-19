window.indexAppDependency.push("gameAdvManagementApp");
angular.module('gameAdvManagementApp', ['commonApp','ui.bootstrap.datetimepicker','angular-bootstrap-select','angular-bootstrap-select.extra'])
    .config(function ($stateProvider) {
        $stateProvider.state('gameAdvManagement', {
            url: '/gameAdvManagement',
            templateUrl: '/app/module/gameadv_management_module/gameAdvManagement.html',
            controller: 'gameAdvManagementCtrl'
        });
    })
    .config(function ($stateProvider) {
    $stateProvider.state('associatedGame', {
        url: '/associatedGame',
        templateUrl: '/app/module/gameadv_management_module/associatedGame.html',
        controller: 'associatedGameCtrl'
    });
    })
    
    .controller('gameAdvManagementCtrl', function ($scope,$http,$compile,serializeService, selectData, queryData,fileReader) {
        console.log("gameAdvManagementCtrl")
        var tableUrl = '/gameAdvManagement/lists';
        var offlineUrl =  '/gameAdvManagement/offlineGameAdv';
        $scope.dtOption = function () {
            var dtOption = {
                ajax: {
                    url: tableUrl,
                },
                columns: [{
                    'data': 'id',
                    'title': '专题ID'
                }, {
                    'data': 'status',
                    'title': '专题状态',
                    'render': function (data, type, rowData, meta) {
                        return (data == '1' || data == null) ? '上架' : '下架';
                    }
                }, {
                    'data': 'game.gname',
                    'title': '游戏',

                }, {
                    'data': 'advName',
                    'title': '专题名称',

                }, {
                    'data': 'creater',
                    'title': '创建者',

                }, {
                    'data': 'activeTime',
                    'title': '发布时间',
                }, {
                    'data': 'operation',
                    'title': '操作',
                    'orderable': false,
                    'createdCell': function (td, value, trval, trindex, colindex) {
                        $compile("<a ng-click='asd()'>编辑</a>&nbsp;&nbsp;<a ng-click='offlineGameAdv("+trindex+")'>下架</a>")($scope).appendTo(td);
                    }
                }
                ]
            };
            return dtOption;
        };

        var postCfg = {
            headers: {'Content-Type': 'application/json; charset=UTF-8'}
        };

        //关贴操作
        $scope.offlineGameAdv = function(trindex){
            if(!confirm("确定要下架吗？")){
                return;
            }
            var id = $scope.Api.row(trindex).data().id;

             $http.post(offlineUrl,{id:id,status:2}, postCfg)
             .success(function (data) {
                if(data.resultStatus == 1){
                    alert("操作成功！");
                    $scope.queryTable();
                }
             }).error(function(data){
             console.log('request failed!');
             });
        }
        $scope.queryTable = function () {
            queryData($scope.Api, tableUrl + serializeService('gameAdvManagementId'));
        };



    })
    .controller('associatedGameCtrl', function ($scope,$http,$compile ,fileReader) {
        console.log("associatedGameCtrl");
        var postCfg = {
            headers: {'Content-Type': 'application/json; charset=UTF-8'},
            cache:true
        };
        $scope.gameAdv = {
            advName:"",
            advImg:"",
            advUrl:"",
            game:{},
            insertTime:new Date(),
            activeTime:new Date(),
            status:1,
            creater:"",
            imageSrc:"",//图片的base64
            gid:""//游戏的id
        }

        //加载用户列表
        var urlGame = '/gameAdvManagement/getAllGame';
        $scope.getAllGame=function($http,urlGame,postCfg){
            $http.post(urlGame,  null, postCfg)
                .success(function (data) {
                    $scope.GameCentres = data.GameCentres;
                    $scope.gameAdv.game=$scope.GameCentres[0];
                }).error(function(data){
                    console.log('request failed!');
                });
        };
        $scope.getAllGame($http,urlGame,postCfg);


        $scope.getFile = function (filename) {
            fileReader.readAsDataUrl($scope.file, $scope)
                .then(function(result) {
                    $scope.gameAdv.imageSrc = result;
                });
            $scope.gameAdv.fileName = filename;
        };

        //保存关联专题
        var saveAssociatedUrl = '/gameAdvManagement/saveAssociatedGame';
        $scope.saveAssociatedGame = function(){
            if ($scope.gameAdv.imageSrc == null) {
               alert("表单填写不正确！");
                return;
            }

            //处理下游戏的id
            $scope.gameAdv.gid=$scope.gameAdv.game.id;

            $http.post(saveAssociatedUrl, $scope.gameAdv , postCfg)
                .success(function (data) {
                    console.log(data)
                    if(data.code == 0){
                        alert("关联失败！");
                    }else{
                        alert("关联成功！");

                    }
                }).error(function(data){
                    console.log('request failed!');
                });



        }


    })

;
