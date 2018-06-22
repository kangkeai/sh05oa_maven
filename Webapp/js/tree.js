var tree = {
	setting:{
		isSimpleData: true,
		treeNodeKey: "mid",
		treeNodeParentKey: "pid",
		showLine: true,
		root:{ 
			isRoot:true,
			nodes:[]
		}
	},
	loadTree:function(){
		/**  说明：
		 * 		第三个参数为回调函数,该回调函数是在服务器成功响应的时候才要触发该函数
		 *      第三个参数的回调函数和客户端的js代码也是异步的过程$("#tree").zTree(tree.setting,data);
		 * @param {Object} data
		 */
		$.post("menuitemAction_showAllMenuitem.action",null,function(data){
			$("#tree").zTree(tree.setting,data);
		});
	}
};

$().ready(function(){
	tree.loadTree();
});
