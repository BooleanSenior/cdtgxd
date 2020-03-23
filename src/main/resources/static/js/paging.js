$('.ui-page-item ui-page-item-first').on('click', function() {
    debugger;
    $("#mypageIndex").val("0");
    app.postValue();
})
$('.ui-page-item ui-page-last').on('click', function() {
    $("#mypageIndex").val($("#allpage").val());
    app.postValue();
})
$('.ui-page-item ui-page-item-prev').on('click', function() {
    var index = $("#mypageIndex").val();
    if(parseInt(index) > 0) {
        $("#mypageIndex").val(parseInt(index) - 1);
        app.postValue();
    } else {
        alert('已经是第一页了');
    }
})

$('.ui-page-item ui-page-next').on('click', function() {
    var index = $("#mypageIndex").val();
    if(parseInt(index) < parseInt($("#allpage").val())) {
        $("#mypageIndex").val(parseInt(index) + 1);
        app.postValue();
    } else {
        alert('已经是最后一页了');
    }
})