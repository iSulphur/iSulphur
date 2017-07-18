//teacher

function showreview0() {
	 $.ajax({
        type: 'get',
        url: '/iSulphur/teacher/review_manager.do?action=findall',
        success: function(data) 
        { 	
        	var str="";
        	var i=0;
        	for(i=0;i<data.msgContent.length;i++)
        	str+="<tr>" + "<td>" + data.msgContent[i].review_id + "</td>" + "<td>" +
        	data.msgContent[i].report_id + "</td>" + "<td>" + data.msgContent[i].ranking + "</td>" +
			"<td><a href='update.html'><input type='button' value='修改'  name='new'/></a></td>"+"</tr>";
			document.getElementById("hello").innerHTML = str;
        }
    });
    return false;
}

function show(){
	var t = getCookie("report_id") ;
	if(t != null){
		$.ajax({
			type:"post",
			url:"/iSulphur/teacher/report_manager.do?action=choose",
			data:'report_id='+t,
			success:function(data)
			{
				if(data.msgContent)				
					{alert("hh");}
					else
					{
						alert("该报告已不存在。！");
					}
			
			}
		});
		return false;
	}
	
}

function showreview1() {
	 $.ajax({
       type: 'get',
       url: '/iSulphur/teacher/review_manager.do?action=findall1',
       success: function(data) 
       { 	
       	var str="";
       	var i=0;
       	for(i=0;i<data.msgContent.length;i++)
       	str+="<tr>" + "<td>" + data.msgContent[i].review_id + "</td>" + "<td>" +
       	data.msgContent[i].report_id + "</td>" + "<td>" + data.msgContent[i].ranking + "</td>" +
			"<td><a href='view.html'><input type='button' value='查看'  name='new'/></a></td>"+"</tr>";
			document.getElementById("world").innerHTML = str;
       }
   });
   return false;
}

function choose(obj){
	var report_id = obj.value;
	setCookie('report_id',report_id);
	window.location.href = "review.html";
}

function review(){
	$.ajax({
		type:"post",
		url:"/iSulphur/teacher/report_manager.do?action=review",
		data:'report_id='+$("#report_id").val()+'&ranking='+$("#ranking").val()+'&suggest='+$("#suggest").val(),
		success:function(data)
		{
			alert("评审已保存！");
		}
	});
	return false;
}

function review1(){
	$.ajax({
		type:"post",
		url:"/iSulphur/teacher/report_manager.do?action=review1",
		data:'report_id='+$("#report_id").val()+'&ranking='+$("#ranking").val()+'&suggest='+$("#suggest").val(),
		success:function(data)
		{
			alert("评审已上传！");
		}
	});
	return false;
}

function update(){
	$.ajax({
		type:"post",
		url:"/iSulphur/teacher/review_manager.do?action=update",
		data:'report_id='+$("#report_id").val()+'&ranking='+$("#ranking").val()+'&suggest='+$("#suggest").val(),
		success:function(data)
		{
			alert("更改成功！");
		}
	});
	return false;
}

function upload(){
	$.ajax({
		type:"post",
		url:"/iSulphur/teacher/review_manager.do?action=upload",
		data:'report_id='+$("#report_id").val()+'&ranking='+$("#ranking").val()+'&suggest='+$("#suggest").val(),
		success:function(data)
		{
			alert("上传成功啦！");
		}
	});
	return false;
}

function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

function load(){
	var task_id;
	if(getCookie('task_id')){
		task_id = getCookie('task_id');
	}
	else{
		
		task_id = GetQueryString("t");
		setCookie('task_id', task_id);
	}
	//alert(task_id);
	
	if(task_id){
	$.ajax({
        type:'get',
        url: '/iSulphur/teacher/report_manager.do?action=show',
        data:'report_task_id='+task_id,
        success: function(data) 
        {
        	var str="";
        	var i=0;
        	for(i=0;i<data.msgContent.length;i++)
        	str+="<div class='col-md-4 col-sm-4'>"+"<div class='card teal'>"+"<div class='card-content white-text'>"+
					"<span class='card-title'>报告"+(i+1)+"</span>"+"</div>"+"<div><span>团队名  "+data.msgContent[i].team_name+"</span></div>"+"<div class='card-action'>"+"<button name='report_id' value="+data.msgContent[i].report_id+" onclick='choose(this);'>详情</button>"+
					"</div>"+"</div>"+"</div>"
					document.getElementById("hhh").innerHTML = str;
        }
    });
	}
	else
	{alert("参数不存在！");}
}

function setCookie(name,value)
{
var Days = 30;
var exp = new Date();
exp.setTime(exp.getTime() + Days*24*60*60*1000);
document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

function getCookie(name)
{
var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
if(arr=document.cookie.match(reg))
return unescape(arr[2]);
else
return null;
}