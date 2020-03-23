/**
 * Created by TS on 14-5-22.
 */
$(".login-l li").click(function(){
    /*
    问题：只有on和off两种状态;其中on只有当前选中的一个，off可能有很多个
    思路：先把所有的都置成off状态，再把当前选中的置为on状态
     */
    var index = $(this).index()+1;
    $(".login-r>div").css('display','none');
    $(".login-r>div:nth-child("+index+")").css('display','block');
    $(".login-l li").addClass('login-off');
    $(this).removeClass('login-off');
});