$().ready(function(){
	//1、创建命名空间
	$.nameSpace("cn.itcast.sh05.oa.MenuItemTree");
	//2、启动继承基础把TreePanel实现继承
	$.extend(cn.itcast.sh05.oa.MenuItemTree,$.fn.TreePanel);
	//3、调用createTree方法创建树
	cn.itcast.sh05.oa.MenuItemTree.createTree({
		url:'privilegeAction_showMenuitemTreeByUid.action',
		data:null,
		id:'menuTree'
	});
});
