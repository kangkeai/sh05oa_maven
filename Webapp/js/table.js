$().ready(function(){
	var index = 0;
	$("input[type='button']").click(function(){
		var me = $("#mygrid");
		var columns = me.find("thead *[field]");
		var table = me.find("table");
		var tbody = table.find("tbody");
		if(tbody.size()==0){
			tbody = $("<tbody></tbody>");
			table.append(tbody);
		}
		var tr = $("<tr></tr>");
		for(var i=0;i<columns.length;i++){
			var td = createTD("persons",index,$(columns[i]).attr("field"));
			tr.append(td);
		}
		tbody.append(tr);
		index++;
	});
	/**
	 * @param {Object} viewBeans 将来在action中List的名称
	 * @param {Object} index  list中的下标
	 * @param {Object} name   每个bean中的属性的名称
	 */
	function createTD(viewBeans,index,name){
		var td = $("<td></td>")
		var text = $("<input/>");
		text.attr("name",viewBeans+"["+index+"]"+"."+name);
		text.attr("type","text");
		td.append(text);
		return td;
	}
});
