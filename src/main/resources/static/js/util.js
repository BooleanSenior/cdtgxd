function zylxArrMap() {
  const map = { boolean: { 1: '动态更新', 2: '专职委员', 3: '就业培训' } }
  return map;
}
function mapflagMethod() {
  const mapflag = { boolean: { 1: '停用', 2: '启用' } }
  return mapflag;
}


function zylxArrMethod() {

  let zylxArr = [
    {
      code: '1',
      name: '动态更新'
    },
    {
      code: '2',
      name: '专职委员'
    },
    {
      code: '3',
      name: '就业培训'
    }

  ]

  return zylxArr;
}

var _temp_time ="";
function showTime(strtime) {
  //var today = new Date();
  _temp_time = strtime;
  var date1 = new Date(strtime)
  var date2 = new Date()
  var s1 = date1.getTime(), s2 = date2.getTime();
  var total = (s2 - s1) / 1000;

  var day = parseInt(total / (24 * 60 * 60));//计算整数天数

  var afterDay = total - day * 24 * 60 * 60;//取得算出天数后剩余的秒数

  var hour = parseInt(afterDay / (60 * 60));//计算整数小时数

  var afterHour = total - day * 24 * 60 * 60 - hour * 60 * 60;//取得算出小时数后剩余的秒数

  var min = parseInt(afterHour / 60);//计算整数分

  var afterMin = total - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;//取得算出分后剩余的秒数
  var _time = day + "天" + hour + "时" + min + "分" + afterMin.toFixed(2) + "秒";
  document.getElementById("nowDataTime").innerHTML = _time ;
  //console.log(_time);
  setTimeout("showTime(_temp_time)", 1000);	//每一秒重新加载
  setTimeout("app.postValue()", 1000*60*60);	//重新加载

}
