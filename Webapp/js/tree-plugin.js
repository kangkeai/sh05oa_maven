$().ready(function(){
	$.nameSpace("cn.itcast.sh05.oa.PrivilegeTree");
	$.extend(cn.itcast.sh05.oa.PrivilegeTree,$.fn.TreePanel);
	cn.itcast.sh05.oa.PrivilegeTree.createTree({
		url:'menuitemAction_showAllMenuitem.action',
		data:null,
		id:'tree',
		setting:{
			treeNodeKey: "mid"
		}
	});
});
