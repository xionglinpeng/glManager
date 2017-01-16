window.indexAppDependency.push("ugcNanagementApp");
angular.module('ugcNanagementApp', ['commonApp'])
    .config(function ($stateProvider) {
        $stateProvider.state('ugcManagement', {
            url: '/ugcManagement',
            templateUrl: '/app/module/ugc_management_module/ugcManagement.html',
            controller: 'ugcManagementCtrl'
        });
    })
    .constant('tableUrl', '/ugcManagement/lists')
    .controller('ugcManagementCtrl', function ($scope, tableUrl, serializeService, selectData, queryData) {

        $scope.dtOption = function () {
            var dtOption = {
                ajax: {
                    url: tableUrl,
                },
                columns: [{
                    'data': 'publishDate',
                    'title': '日期'
                }, {
                    'data': 'id',
                    'title': '编号',
                }, {
                    'data': 'content',
                    'title': '内容'
                }, {
                    'data': 'userInfo.concernedGame',
                    'title': '游戏类型',
                    'render': function (data, type, rowData, meta) {
                        var concernedGames = "";
                        for(var uc=0;uc<data.length;uc++){
                            concernedGames+=concernedGames+","+data[uc].gname;
                        }
                        return concernedGames;
                    }
                }, {
                    'data': 'userInfo.playingGame.gname',
                    'title': '游戏名称',
                }, {
                    'data': 'as',
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
                }, {
                        'data': 'operation',
                        'title': '操作',
                        'orderable': false,
                        'render': function () {
                            return "<a>查看用户</a>&nbsp;&nbsp;<a>禁号操作</a>";
                        }
                    }]
            };
            return dtOption;
        };


        $scope.queryTable = function () {
//			console.log(selectData($scope.Api));
//			console.log(selectData($scope.Api)[0]);
//			console.log(selectData($scope.Api)[1]);
            queryData($scope.Api, tableUrl + serializeService('glUserId'));
        };

    });