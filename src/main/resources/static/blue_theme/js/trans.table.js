// JavaScript Document

  $().ready(function(){ 
             
             $(".stripetb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数   
             		$(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数   
             				$(this).removeClass("over");}) //移除该行的class   
             //$(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
		$(".discolor>tr:odd").addClass("tr-odd-bg");
		$(".discolor>tr:even").addClass("tr-even-bg");
		$('.discolor>tr').click(function() {
			//判断当前是否选中
			var hasSelected=$(this).hasClass('selected');
			//如果选中，则移出selected类，否则就加上selected类
			$(this)[hasSelected?"removeClass":"addClass"]('selected')
				//查找内部的checkbox,设置对应的属性。
				.find(':checkbox').attr('checked',!hasSelected);
		});
		//全选/取消链接
		$("#selectAll").click(function(){
				//alert($(this).html()=="全选");
				//重新设定链接中的文本
				//$(this).html()?"全选":"取消"!"全选";
						//设置全部复选框为选中状态
						$('.discolor>tr').find(':checkbox').attr("checked","checked");
						//设置高亮
						$('.discolor>tr').addClass("selected","selected");
			});
			$("#selectCancel").click(function(){
							$('.discolor>tr').find(':checkbox').removeAttr("checked","checked");
							$('.discolor>tr').removeClass("selected","selected");
			});
		// 如果复选框默认情况下是选择的，则高色.
		$('.discolor>tr:has(:checked)').addClass('selected');
  })
    
$(function(){
		$(".discolor_form tr:odd").addClass("tr-odd-bg"); //先排除第一行,然后给奇数行添加样式
		$(".discolor_form tr:even").addClass("tr-even-bg"); //先排除第一行,然后给偶数行添加样式
	})
	 
 