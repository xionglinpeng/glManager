window.indexAppDependency.push("ugcManagementApp");
angular.module('ugcManagementApp', ['commonApp'])
    .config(function ($stateProvider) {
        $stateProvider.state('ugcManagement', {
            url: '/ugcManagement',
            templateUrl: '/app/module/ugc_management_module/ugcManagement.html',
            controller: 'ugcManagementCtrl'
        });
    })
    .controller('ugcManagementCtrl', function ($scope,$http,$compile,serializeService, selectData, queryData) {
        var tableUrl = '/ugcManagement/lists';
        var closeUrl = '/ugcManagement/closeBroadcast';

        $scope.dtOption = function () {
            var dtOption = {
                ajax: {
                    url: tableUrl,
                },
                columns: [{
                    'data': 'publishDate',
                    'title': '日期',

                }, {
                    'data': 'id',
                    'title': '编号'
                }, {
                    'data': 'content',
                    'title': '内容'
                }, {
                    'data': 'userInfo.concernedGame',
                    'title': '游戏类型',
                    'render': function (data, type, rowData, meta) {
                        var concernedGames = "";
                        if(data == null){
                            return concernedGames;
                        }
                        for(var uc=0;uc<data.length;uc++){
                            concernedGames+=concernedGames+","+data[uc].gname;
                        }
                        return concernedGames;
                    }
                }, {
                    'data': 'userInfo.playingGame.gname',
                    'title': '游戏名称',
                    'render': function (data, type, rowData, meta) {
                        return data == null ? "" : data;
                    }
                }, {
                    'data': 'isReport',
                    'title': '是否举报',
                    'render': function (data, type, rowData, meta) {
                        return data == 'ture' ? '是' : '否';
                    }
                }, {
                    'data': 'userInfo.nickname',
                    'title': '发帖人',
                }, {
                        'data': 'status',
                        'title': '状态',
                    'render': function (data, type, rowData, meta) {
                        return (data == '1' || data == null) ? 'open' : 'close';
                    }
                }, {
                    'data': 'operation',
                    'title': '操作',
                    'orderable': false,
                    'createdCell': function (td, value, trval, trindex, colindex) {
                        $compile("<a ng-click='asd()'>编辑</a>&nbsp;&nbsp;<a ng-click='closeBroadcast("+trindex+")'>关贴</a>")($scope).appendTo(td);
                    }
                }
                ]
            };
            return dtOption;
        };

        //关贴操作
        $scope.closeBroadcast = function(trindex){

            if(!confirm("确定要关贴吗？")){
                return;
            }

            var id = $scope.Api.row(trindex).data().id;
            var postCfg = {
             headers: {'Content-Type': 'application/json; charset=UTF-8'}
             };
             $http.post(closeUrl,{id:id,status:2}, postCfg)
             .success(function (data) {
                if(data.resultStatus == 1){
                    alert("关贴成功！");
                    $scope.queryTable();
                }
             }).error(function(data){
             console.log('request failed!');
             });
        }

        $scope.queryTable = function () {
            queryData($scope.Api, tableUrl + serializeService('ugcManagementId'));



        };

    });