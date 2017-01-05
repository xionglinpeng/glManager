/**
 * 针对game link manager全局配置
 */
/**
 * dataTables库全局配置
 */
$.extend($.fn.dataTable.defaults,{
		autoWidth:true,//控制Datatables是否自适应宽度
		serverSide:true,//默认服务器模式
		lengthMenu:[[5,10,25,50, 100],[5,10,25,50, 100]],//每页数据量
		pageLength:50,//初始每页记录条数
		processing:true,//显示处理状态
		searching:false,//因为默认服务器模式，所以默认禁用搜索
		language:{
				search : "搜索当前数据: ",
				sProcessing : "处理中...",
				sLengthMenu : "显示 _MENU_ 项结果",
				sZeroRecords : "没有匹配结果",
				sInfo : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
				sInfoEmpty : "显示第 0 至 0 项结果，共 0 项",
				sInfoFiltered : "(由 _MAX_ 项结果过滤)",
				sEmptyTable : "对不起，查询不到相关数据!",
				sLoadingRecords : "载入中...",
				oPaginate : {
						sFirst : "首页",
						sPrevious : "上页",
						sNext : "下页",
						sLast : "末页"
				},
				oAria : {
						sSortAscending : ": 以升序排列此列",
						sSortDescending : ": 以降序排列此列",
				}
		},
		columnDefs : [//定义第一列不进行排序，因为第一列一般都是序号
			{
				targets:[0,0],//第一列
				orderable:false//禁用排序
			}
		]
	});