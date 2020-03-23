// JavaScript Document
$(document).ready(function (){
var FundsTypeSelect = $(".fundstype").children("select");
var FundsDetailsSelect = $(".fundsdetails").children("select");
var FundsReplenishSelect = $(".fundsreplenish").children("select");


//给第一个下拉框的SelectChanged时间编码
FundsTypeSelect.change(function (){

//取得当前下拉框的值
var FundsTypeValue = $(this).val();
//当第一个下拉框内容改变的时候，第三个下拉框要隐藏起来
FundsDetailsSelect.parent().hide();

if(FundsTypeValue !="")
{
FundsDetailsSelect.html("");
$("<option value = ''>请选择专项分类</option>").appendTo(FundsDetailsSelect);
switch(FundsTypeValue){

case "ZY":
var FundsDetails=["国家科技重大专项(01)","国家科技重大专项(02)","国家科技重大专项(03)","国家科技重大专项(04)","技改专项","中小技改专项","国家重大科技成果转化专项","国家电子信息产业发展基金","国家中小企业发展专项","国家中小企业信用担保专项","国家公共服务平台专项"];
for(var i = 0;i<FundsDetails.length;i++){
$("<option value='"+FundsDetails[i]+"'>" + FundsDetails[i] + "</option>").appendTo(FundsDetailsSelect);
}
break;
case "ZZQ":
var FundsDetails = ["新疆自治区重大科技成果转化和产业项目统筹资金","新疆自治区工业发展资金"];
for(var i = 0;i<FundsDetails.length;i++){
$("<option value='"+FundsDetails[i]+"'>" + FundsDetails[i] + "</option>").appendTo(FundsDetailsSelect);
}
break;
}
FundsDetailsSelect.parent().show();
}
else
{
FundsDetailsSelect.parent().hide();
}
});

FundsDetailsSelect.change(function (){

var FundsDetailsValue = $(this).val();
FundsReplenishSelect.html("");
if(FundsDetailsValue != "")
{
FundsReplenishSelect.html("");
$("<option value = ''>请选择专项分类</option>").appendTo(FundsReplenishSelect);
switch(FundsDetailsValue){

case "国家电子信息产业发展基金":
var FundsReplenish = ["一般类项目","招标类项目"];
for(var i = 0;i<FundsReplenish.length;i++){
$("<option value='"+FundsReplenish[i]+"'>" + FundsReplenish[i] + "</option>").appendTo(FundsReplenishSelect);
}
break;
case "国家中小企业发展专项":
var FundsReplenish = ["产业项目","专利补助项目"];
for(var i = 0;i<FundsReplenish.length;i++){
$("<option value='"+FundsReplenish[i]+"'>" + FundsReplenish[i] + "</option>").appendTo(FundsReplenishSelect);
}
break;
case "新疆自治区重大科技成果转化和产业项目统筹资金":
var FundsReplenish = ["专项01","专项02"];
for(var i = 0;i<FundsReplenish.length;i++){
$("<option value='"+FundsReplenish[i]+"'>" + FundsReplenish[i] + "</option>").appendTo(FundsReplenishSelect);
}
break;
case "新疆自治区工业发展资金":
var FundsReplenish = ["产业项目","企业稳增长奖励"];
for(var i=0;i<FundsReplenish.length;i++){
$("<option vaiue='"+FundsReplenish[i]+"'>" + FundsReplenish[i] + "</option>").appendTo(FundsReplenishSelect);
}
break;
}
FundsReplenishSelect.parent().show();
}
else
{
FundsReplenishSelect.parent().hide();
}
});
}); 