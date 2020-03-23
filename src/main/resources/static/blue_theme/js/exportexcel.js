// JavaScript Document
function AutomateExcel()
{

var oXL = new ActiveXObject("Excel.Application"); //创建应该对象
var oWB = oXL.Workbooks.Add();//新建一个Excel工作簿
var oSheet = oWB.ActiveSheet;//指定要写入内容的工作表为活动工作表
var table = document.all.data;//指定要写入的数据源的id
var hang = table.rows.length;//取数据源行数
alert(hang);
var lie = table.rows(0).cells.length;//取数据源列数
alert(lie);

// Add table headers going cell by cell.
for (i=0;i<hang;i++){//在Excel中写行
for (j=0;j<lie;j++){//在Excel中写列
//定义格式
oSheet.Cells(i+1,j+1).NumberFormatLocal = "@";
//!!!!!!!上面这一句是将单元格的格式定义为文本
oSheet.Cells(i+1,j+1).Font.Bold = true;//加粗
oSheet.Cells(i+1,j+1).Font.Size = 10;//字体大小
oSheet.Cells(i+1,j+1).value = table.rows(i).cells(j).innerText;//向单元格写入值
}
}
oXL.Visible = true;
oXL.UserControl = true;
}

 function AddMore(){
            var more = document.getElementById("file");
            var br = document.createElement("br");
            var input = document.createElement("input");
            var button = document.createElement("input");
           
            input.type = "file";
            input.name = "file";
           
            button.type = "button";
            button.value = "删除";
           
            more.appendChild(br);
            more.appendChild(input);
            more.appendChild(button);
           
            button.onclick = function(){
                more.removeChild(br);
                more.removeChild(input);
                more.removeChild(button);
            };
        }