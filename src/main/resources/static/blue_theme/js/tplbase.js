// JavaScript Document
//infoid 为选卡内容区域DIV的ID。cardid传this。调用是注意''的使用。
//---------------------------------------------------
function showCard(cardid, infoid, clsName){
    var cardList = cardid.parentNode.getElementsByTagName("li");
    for (i = 0; i < cardList.length; i++) {
        if (cardid == cardList[i]) {
            cardList[i].className = clsName + "-on";
            document.getElementById(infoid + i).style.display = "block";
        }
        else {
            cardList[i].className = clsName + "-off";
            document.getElementById(infoid + i).style.display = "none";
        }
    }
}


//折叠层效果
$(".query-btn").click(function(){
            $(".query-tab").slideToggle(500);
 });
