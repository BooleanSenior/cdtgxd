// JavaScript Document
onload = Init;
function opendg(dgurl,dgw,dgh,title)
{
  $.dialog({ 
    title:title,
    width: dgw,
    height: dgh,
    content:'url:'+ dgurl});
}
          
function apply_confirm(content) <!--调用方法-->
{
    event.returnValue = confirm(content);
}