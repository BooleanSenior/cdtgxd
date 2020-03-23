// JavaScript Document
function slideUp(div){
$(div).slideUp(1000);//窗帘效果展开
}
function slidePanle1(div){
			if($(div).css("display")=="block")
      {
      $("#search-general").css("display")=="none";
			$("#search-box-icon").attr("className","hide-search");
			$("#search-box-btn").attr("alt","显示查询面板");
		}else{
			$(div).css("display")=="none";
      $("#search-general").css("display")=="block";
			$("#search-box-icon").attr("className","show-search");
			$("#search-box-btn").attr("alt","隐藏查询面板");
		}
	$(div).slideToggle(1000);//窗帘效果的切换,点一下收,点一下开,参数可以无,参数说明同上	
}
function slideToggle(div1){
      if($(div1).css("display")=="block")
      {
    //  $(div2).css("display")=="none"; 
     // $('#searchbar-icon').attr("className","show-search");
     $('#searchbar-icon').removeClass('hide-search').addClass('show-search');
	 $("#search-box-btn").attr("title","显示搜索面板");
	 //$(div1).removeClass('hide-search').addClass('show-search');
	// $(div1).attr("title","显示搜索面板");
    }else{
     // $(div2).css("display")=="block";
      $('#searchbar-icon').removeClass('show-search').addClass('hide-search')
	  $("#search-box-btn").attr("title","隐藏搜索面板");
	 // $(div1).removeClass('show-search').addClass('hide-search')
	 // $(div1).attr("title","隐藏搜索面板");
     // $('#searchbar-icon').attr("className","show-search");
      
    }
  $(div1).slideToggle(500);
  //窗帘效果的切换,点一下收,点一下开,参数可以无,参数说明同上 
}

function slideFlod(obj1,obj2){
	if($(obj2).css("display")=="block"){
	//	$("#obj1").attr("src","../themes_blue/images/icons/panel_display.png");		
		}
	else{
		$(obj2).css("display")=="none";
	//	$("#obj1").attr("src","../themes_blue/images/icons/panel_hide.png");
		}
		$(obj2).slideToggle(500);//窗帘效果的切换,点一下收,点一下开,参数可以无,参数说
	}
function slideTool(obj1,obj2){
	if($(obj2).css("display")=="block"){
		//$("#obj1").attr("src","../themes_blue/images/icons/panel_display.png");	
		 $(obj1).removeClass('hide-toolbox').addClass('show-toolbox')	
		}
	else{
		$(obj2).css("display")=="none";
		//$("#obj1").attr("src","../themes_blue/images/icons/panel_hide.png");
		 $(obj1).removeClass('show-toolbox').addClass('hide-toolbox')
		}
		$(obj2).slideToggle(700);//窗帘效果的切换,点一下收,点一下开,参数可以无,参数说
	}
/**全选**/
function selectAll(o){
if(o.checked){
$("input[name='check_demo']").attr("checked",true);
}else{
$("input[name='check_demo']").attr("checked",false);
}
}
function sl(o){
$.each($("input[name='check_demo']"),function(i,cbo){
if(!cbo.checked){
$("#check_all").attr("checked",false);
}
});
 
var t=0;
$("input[name=check_demo]").each(function(i){
 if($(this).attr("checked")==true){t++}else{t--};
 //alert(t);
 if(t==2){
  $("input[name=check_all]").attr("checked",true);
 }else{
  $("input[name=check_all]").attr("checked",false);
 }
});
}


/***tabs 标签卡的样式***/
$(function(){
        $.tabs = function(obj){
          return (this instanceof $.tabs) ? this.init.apply(this,arguments) : new $.tabs(obj)
        }
        //主动事件 通过编程触发
        //被动事件 由用户的行为触发
        $.tabs.prototype = {
          init:function(obj){
            var that = this;
            //配置属性
            $.extend(this,{
              selectedClass:"ui-tab-item-current",
              tabsSelector:">dt a",
              panelsSelector:">dd",
              click:$.noop,
              selected:0
            }, obj || { })
 
            this.ui = $(obj.selector);
            this.tabs =  this.ui.find(this.tabsSelector);
            this.panels = this.ui.find(this.panelsSelector);
             
            this.select(this.selected)
            this.tabs.live("click",function(){
              var index = that.tabs.index(this);
              that._switch.call(that,index)
              that.click.call(this,index,that);
            });
          },
          _switch:function(index){
            this.tabs.removeClass(this.selectedClass).eq(index).addClass(this.selectedClass);
            this.panels.hide().eq(index).show();
          },
          select:function(index,callback){
            index = ~~index;
            this._switch(index);
            callback && callback.call(this.tabs[index],index,this);
          },
          remove:function(index,callback){
            index = ~~index;
            this.tabs.eq(index).remove();
            this.panels.eq(index).remove();
            callback && callback.call(this.tabs[index],index,this);
          }
        }
 
        var tabs = $.tabs({
          selector:"#tabsComp",
          selected:0,
          click:function(index,instance){
            
          }
        });
      });
	  
/**信息列表的横向 onload = Init;滚动**/
window.onload = Init;
function Init()
{   
   if(document.getElementById("listscroll")){
		var objW = document.getElementById("listscroll");	
       var sum=0;
	for(var i=1;i<document.getElementById("listscroll").rows[0].cells.length;i++)
	{	
	var a = objW.rows[0].cells[i].width;	
	var b=parseInt(a);
	sum = sum + b;
}
   var clientW= document.documentElement.clientWidth;
   if(sum> clientW){
	document.getElementById("listscroll").width = sum + "px";}
	else{
		document.getElementById("listscroll").width= clientW  +"px";
		}
	var a=document.getElementById("listscroll").width;
		}	
}

	  
/**复合表格的多层 显示隐藏**/
 function initIt(){
    divCount = document.getElementsByTagName("DIV");
    for (i=0; i<divCount.length; i++) {
      obj = divCount[i];
      if (obj.className == "ui-flod-cnt" || obj.className == "child1") obj.style.display = "none";
	  if (obj.className == "block" ) obj.style.display = "block";
    }
  }

  function expandIt(el) {
    var obj = document.getElementById("page" + el);
	var obj2 = document.getElementById("main" + el);
	
    if (obj.style.display == "none") {
      obj.style.display = "block";
      obj2.getElementsByTagName('img')[0].src='../themes_blue/images/icons/panel_display.png';
	  obj2.className = "ui-flod-title";
      //obj2.all.tags('img')[1].src='../../images/1A/icon/folder.gif';
    }
    else {
      obj.style.display = "none";
      obj2.getElementsByTagName('img')[0].src='../themes_blue/images/icons/panel_hide.png';
	  obj2.className = "ui-flod-title";
      //obj2.all.tags('img')[1].src='../../images/1A/icon/folderclose.gif';
    }
  }
  onload = initIt;
 /**也是图标型的**/ 
   function initThis(){
    divCount = document.getElementsByTagName("DIV");
    for (i=0; i<divCount.length; i++) {
      obj = divCount[i];
      if (obj.className == "child") obj.style.display = "none";
	  if (obj.className == "block" ) obj.style.display = "block";
    }
  }

  function expandThis(el) {
    var obj = document.getElementById("page" + el);
	var obj2 = document.getElementById("main" + el);
	
    if (obj.style.display == "none") {
      obj.style.display = "block";
      obj2.getElementsByTagName('img')[0].src='../images/icons/icon_panle_off.png';
	  
      //obj2.all.tags('img')[1].src='../../images/1A/icon/folder.gif';
    }
    else {
      obj.style.display = "none";
      obj2.getElementsByTagName('img')[0].src='../images/icons/icon_panle_on.png';
	  
      //obj2.all.tags('img')[1].src='../../images/1A/icon/folderclose.gif';
    }
  }
  onload = initThis;
  
 //infoid 为选卡内容区域DIV的ID。cardid传this。调用是注意''的使用。
function showTab(tabid,infoid)
{
 var tabList = tabid.parentNode.getElementsByTagName("li");
  for(i=0;i<tabList.length;i++){
   if(tabid == tabList[i]) {
	   var str=tabList[i].className.split('_')[0];
		tabList[i].className = str + "_on";
		document.getElementById(infoid + i).style.display="block"; 
   }
   else{
	    var str=tabList[i].className.split('_')[0];
		tabList[i].className = str + "_off";
		document.getElementById(infoid + i).style.display="none";
    }
	}
}
