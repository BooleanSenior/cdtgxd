// JavaScript Document
/**左侧菜单js**/
function hidemenu()
  { 
    document.getElementById("hidemenu").style.display = "none";
	document.getElementById("showmenu").style.display = "block";
	document.getElementById("subframe").style.display= "none";
	document.getElementById("main-content").className= "main-content-hide";
	document.getElementById("col-sub").className= "col-sub-hide";	
	resetFrame();

}
 function showmenu()
  { 
	document.getElementById("hidemenu").style.display = "block";
	document.getElementById("showmenu").style.display = "none";
	document.getElementById("subframe").style.display= "block";
    document.getElementById("main-content").className= "main-content";
	document.getElementById("col-sub").className= "col-sub";	
   resetFrame();

}
<!--隐藏左侧菜单后 auto mainframe width-->
function resetFrame(){
	var h=document.documentElement.clientHeight;
  	var j=h-121;
	
  	document.getElementById('col-sub').style.height=j+"px";
	document.getElementById('mainframe').style.height=j+"px";
   // var w=  window.parent.document.documentElement.clientWidth;
	//var show= document.getElementById("showmenu").style.display;	
	
	//if(show == "block"){
  //  var t= w-50;
  //  document.getElementById('mainframe').style.width=t+"px";  
	
	
//	document.getElementById('subframe').style.width=50+"px";  
	//}
	//else{
	//var k=w-280;
	//document.getElementById('mainframe').style.width=k+"px";	
	//document.getElementById('subframe').style.width=280+"px"; 
	
//	}	
	}
<!-- Iframe Auto Height  和mainframe width 与resizeFramedbClick的区别是增加了窗体初始化最大化的js调用-->
function resizeFrame(divid){
	resizeMax();
  	var h=document.documentElement.clientHeight;
  	var j=h-121;
  	document.getElementById(divid).style.height=j+"px";	
	document.getElementById('col-sub').style.height=j+"px";
	//var show= document.getElementById("showmenu").style.display;
	//var w= document.documentElement.clientWidth;	
	//if(show == "block"){
  //  var t= w-50;
  // document.getElementById('mainframe').style.width=t+"px";
  // document.getElementById('subframe').style.width=50+"px"; 
	//}
	//else{
	//var k=w-280;
	//document.getElementById('mainframe').style.width=k+"px";	
	//document.getElementById('subframe').style.width=280+"px"; 		
	//}
  	}
<!--窗体初始化最大化-->
function resizeMax(){
    window.moveTo(0,0);        
    window.resizeTo(screen.availWidth,screen.availHeight); 
    window.outerWidth=screen.availWidth;        
    window.outerHeight=screen.availHeight;  
	}
<!-- Iframe Auto Height  和mainframe width-->	
function resizeFramedbClick(divid){
  	var h=document.documentElement.clientHeight;
  	var j=h-121;
  	document.getElementById(divid).style.height=j+"px";
	document.getElementById('col-sub').style.height=j+"px";
	//var show= document.getElementById("showmenu").style.display;
	//var w= document.documentElement.clientWidth;	
	//if(show == "block"){
   // var t= w-50;
 //  document.getElementById('mainframe').style.width=t+"px"; 
   //  document.getElementById('subframe').style.width=50+"px"; 
	//}
	//else{
	//var k=w-230;
	//document.getElementById('mainframe').style.width=k+"px";	
	//	document.getElementById('subframe').style.width=280+"px"; 				
	//}
  	}


<!--navbar switch -->
function switchNav(thisObj)
{
//if(thisObj.className == "nav_on")return;
var tabObj = thisObj.parentNode.id;
var tabList = document.getElementById(tabObj).getElementsByTagName("li");
var links=  document.getElementById(tabObj).getElementsByTagName("a");
  for(i=0;i<tabList.length;i++){
   if(thisObj == tabList[i]) {
       var str=tabList[i].className.split('_')[0];
		tabList[i].className = str + "-on";
		links[i].setAttribute("class", "current");/*ie7不支持setAttribute*/
		links[i].setAttribute("className", "current");/*links[i].className="current"; 这是用dom的方法，也可以*/
        showmenu();
   }
   else{
     var str=tabList[i].className.split('_')[0];
		tabList[i].className = str + "-off";
		links[i].setAttribute("class", " ");
		links[i].className=" ";
    }
	}
}
