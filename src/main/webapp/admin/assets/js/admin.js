// check admin Login
function login() {
    $.ajax({
        type:'post',
        url: '/iSulphur/admin/admin.do?action=login',
        data: $('form').serialize(),
        success: function (data) {
            if(data.msgContent == 1){
            	window.location.href="current-task.html";
            }
            else {
            	alert("登陆失败！");
            }

        }
    });
    return false;
}

function updatepwd(){
	$.ajax({
		type:"post",
		url:"/iSulphur/admin/admin.do?action=update_pwd",
		data:'password='+$("#password").val(),
		success:function(data)
		{
			
			if(data.msgContent==1){
				alert("修改成功");
			}
			else
			{
				alert("修改失败");
			}
		}
	});
	return false;
}
function updateinfo(){
	$.ajax({
		type:"post",
		url:"/iSulphur/admin/admin.do?action=update_info",
		data: 'admin_name='+$("#name").val()+'&admin_phone='+$("#phone").val(),
		success:function(data)
		{
			
			if(data.msgContent==1)				
			alert("修改成功");
			else
			{
				alert("修改失败");
			}
		}
	});
	return false;
}

/*function listteaminfo(){
	$.ajax({
		type:"post",
		url:"/iSulphur/admin/team.do?action=findall",
		data:,
		success:function(data)
		{
	});
}
*/
function addteam(){
		$.ajax({
			type:"post",
			url:"/iSulphur/admin/team.do?action=add",
			data:'team_id='+$("#teamID").val()+'&team_name='+$("#team").val()+'&project='+$("#project").val()+'&team_leader='+$("#name").val()+'&leader_phone='+$("#phone").val()+'&leader_mail='+$("#mail").val(),
			success:function(data)
			{
				if(data.msgContent == 1){
		            alert("添加成功");
		            }
		            else {
		            	alert("添加失败！");
		            }
			}
		});
		return false;
}

function findteam(obj){
	
	var team_id = obj.value;
	setCookie('team_id', team_id);
	window.location.href = "user_message.html";
}

function show(){
	var t = getCookie("team_id") ;
	if(t != null){
		$.ajax({
			type:"post",
			url:"/iSulphur/admin/team.do?action=find",
			data:'team_id='+t,
			success:function(data)
			{
				
				document.getElementById("proname").value=data.msgContent.project;
				document.getElementById("name").value=data.msgContent.team_leader;
				document.getElementById("mail").value=data.msgContent.leader_mail;
			}
		});
	}
	else{
		alert("no team id!");
	}
}

function findteam2(obj){
	var team_id = obj.value;
	setCookie('team_id', team_id);
	window.location.href = "user_change.html";
}

function setCookie(name,value)
{
var Days = 30;
var exp = new Date();
exp.setTime(exp.getTime() + Days*24*60*60*1000);
document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

function show2(){
	var t = getCookie("team_id") ;
	if(t != null){
		$.ajax({
			type:"post",
			url:"/iSulphur/admin/team.do?action=find",
			data:'team_id='+t,
			success:function(data)
			{
				document.getElementById("teamID").value=data.msgContent.team_id;
				document.getElementById("team").value=data.msgContent.team_name;
				document.getElementById("project").value=data.msgContent.project;
				document.getElementById("name").value=data.msgContent.team_leader;
				document.getElementById("phone").value=data.msgContent.leader_phone;
				document.getElementById("mail").value=data.msgContent.leader_mail;
			}
		});
	}
	else{
		alert("no team id!");
	}
	
}

function getCookie(name)
{
var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
if(arr=document.cookie.match(reg))
return unescape(arr[2]);
else
return null;
}

function updateteaminfo(){
	$.ajax({
		type:"post",
		url:"/iSulphur/admin/team.do?action=update_info",
		data: 'team_id='+$("#teamID").val()+'&team_name='+$("#team").val()+'&project='+$("#project").val()+'&team_leader='+$("#name").val()+'&leader_phone='+$("#phone").val()+'&leader_mail='+$("#mail").val(),
		success:function(data)
		{
			 
			if(data.msgContent==1)				
			alert("修改成功");
			else
			{
				alert("修改失败");
			}
		}
	});
	return false;
}

function listteaminfo(){
	$.ajax({
		type:"get",
		url:"/iSulphur/admin/team.do?action=findall",
		success:function(data)
		{
				var str = "";
				$("#table-content").empty();
				for(var i = 0; i<data.msgContent.length; i++) {
					str += "<tr>" + "<td>" + data.msgContent[i].teamID + "</td>" + "<td>" + data.msgContent[i].teamName + "</td>" + "<td>" + data.msgContent[i].project + "</td>" +
						"<td><a href='user-change.html'> <button name='teamID' value="+data.msgContent[i].teamID+" onclick='findteam2(this);'>修改</button></a><button name='teamID' value="+data.msgContent[i].teamID+" onclick='return deleteteam(this);'>删除</button></td>";

				}
				document.getElementById("table-content").innerHTML = str;
				$("input[name='delete']").click(function(){
    			$(this).parent().parent().remove();
    		})
    		
			
		
		}
	});
	return false;
}

function listteaminfo2(){
	$.ajax({
		type:"get",
		url:"/iSulphur/admin/team.do?action=findall",
		success:function(data)
		{
			var content=document.getElementById("cur-teaminfo");
			content.empty;
			var str="";
			for(var i=0;i<data.msgContent.length;i++)
				{
				str+='<tr class="odd gradeX"><td><a href="history-report.html">'+data.msgContent[i].project+'</a></td><td>'+data.msgContent[i].team_name+
				'</td><td><a href="user_message.html"><button class="btn btn-default btn-flat" name="teamID" value='+data.msgContent[i].teamID+' onclick="findteam(this);">查看</button></a></td></tr>';
				}
			content.innerHTML=str;					
		
		}
	});
	return false;
}

function addtask(){
	$.ajax({
		type:"post",
		url:"/iSulphur/admin/task.do",
		data:'report_task_id='+$("#report_task_id").val()+'&task_property='+$("#task_property").val()+'&begin_time='+$("#begin_time").val()+'&end_time='+$("#end_time").val()+'&max_submit_time='+$("#max_submit_time").val()+'&task_remake='+$("#task_remake").val()+'&task_status='+"100",
		success:function(data)
		{
			if(data.msgContent==1)				
				{alert("添加成功");}
				else
				{
					alert("添加失败");
				}
		
		}
	});
	return false;
}

function listhis(){
	$.ajax({
		type:"get",
		url:"/iSulphur/admin/task.do?action=findhis",
		success:function(data)
		{
			var content=document.getElementById("task-content");
			content.empty;
			var str="";
			for(var i=0;i<data.msgContent.length;i++)
			{
    			str=str+"<div class='col-md-4 col-sm-4'>"+
    			"<div class='card teal'>"+
    			"<div class='card-content white-tex' style='color:white;'>"+
    			"<span class='card-title'>" + data.msgContent[i].reportTaskId+"</span><p>"+
    			"任务要求：<span>"+ data.msgContent[i].taskProperty +
    			"</span>"+"</p><p>开始时间：<span>"+ data.msgContent[i].beginTime +"</span></p><p>结束时间：<span>"+  data.msgContent[i].endTime +
    			"</span><p>最多提交次数:<span>" + data.msgContent[i].maxSubmitTime+
    			"</span></p> </div> <div class='card-action'>"+"<a href='historical-report.html'>"+
    			"详情</a><a href='#'>删除</a> </div> </div> </div>";
			}
			content.innerHTML=str;
		}
	});
	return false;
}

function listcur(){
	$.ajax({
		type:"get",
		url:"/iSulphur/admin/task.do?action=findcur",
		success:function(data)
		{
			var content1=document.getElementById("cur-task");
			content1.empty;
			var str="";
			for(var i=0;i<data.msgContent.length;i++)
				{
				str+='<div class="card-action">'+data.msgContent[i].reportTaskId+'</div><div class="card-content"><p>任务要求：'+data.msgContent[i].taskProperty+
				'</p><p>开始时间：'+data.msgContent[i].beginTime+'</p><p>结束时间：'+data.msgContent[i].endTime+'</p><p>最多提交次数：'+data.msgContent[i].maxSubmitTime+'</p>';
				}
			content1.innerHTML=str;
			
			var	content2=document.getElementById("time-task");
			content2.empty;
			var str="";
			for(var i=0;i<data.msgContent.length;i++)
				{
				str+='<p>'+data.msgContent[i].reportTaskId+'</p><p>任务要求：'+data.msgContent[i].taskProperty+
				'</p><p>开始时间：'+data.msgContent[i].beginTime+'</p><p>结束时间：'+data.msgContent[i].endTime+'</p><p>最多提交次数：'+data.msgContent[i].maxSubmitTime+'</p>';
				}
			content2.innerHTML=str;
		}
	});
	return false;
}

function listrep(){
	$.ajax({
		type:"get",
		url:"/iSulphur/admin/report.do?action=findall",
		success:function(data)
		{
			var content=document.getElementById("hisreport-content");
			content.empty;
			var str="";
			for(var i=0;i<data.msgContent.length;i++)
				{
				if(i%2==0)
					{
					str+='<tr class="odd gradeX">'+'<td>'+data.msgContent[i].report_id+'</td><td>'+'<a href="historical-report.html">'+data.msgContent[i].project+
					'</a></td><td>'+'<a href="user_message.html">'+data.msgContent[i].team_name+'</a></td><td class="center">'+data.msgContent[i].progress+'</td><div><div class="fixWidth"><div class="demo"><div id="trigger1">未评价</div></div></div></div></td></tr>';
					}
				else
					{
					str+='<tr class="even gradeC">'+'<td>'+data.msgContent[i].report_id+'</td><td>'+'<a href="historical-report.html">'+data.msgContent[i].project+
					'</a></td><td>'+'<a href="user_message.html">'+data.msgContent[i].team_name+'</a></td><td class="center">'+data.msgContent[i].progress+'</td><div><div class="fixWidth"><div class="demo"><div id="trigger1">未评价</div></div></div></div></td></tr>';
					}
				}
			content.innerHTML=str;
		}
	});
	return false;
}

function listreview(){
	$.ajax({
		type:"get",
		url:"/iSulphur/admin/report.do?action=findrev",
		success:function(data)
		{
			var content=document.getElementById("cur-teareview");
			content.empty;
			var str="";
			for(var i=0;i<data.msgContent.length;i++)
				{
				str+='<tr class="odd gradeX"><td>'+data.msgContent[i].review_id+'</td><td>'+data.msgContent[i].report_id+'</td><td>'+data.msgContent[i].ranking
				+'</td><td>'+data.msgContent[i].suggest+'</td></tr>';
				}
			content.innerHTML=str;
		}
	});
	return false;
}

function addresult(){
	$.ajax({
		type:"post",
		url:"/iSulphur/admin/report.do?action=add_result",
		data:'report_id='+$("#report_id").val()+'&final_result='+$("#final_result").val(),
		success:function(data)
		{
			if(data.msgContent)
				{alert("ok");}
		}
	});
	return false;
}

function setstatus1(){
	$.ajax({
		type:"post",
		url:"/iSulphur/admin/task.do?action=set_status",
		data:'status='+"300",
		success:function(data)
		{
			if(data.msgContent)
				{alert("ok");}
		}
	});
	return false;
}

function setstatus2(){
	$.ajax({
		type:"post",
		url:"/iSulphur/admin/task.do?action=set_status",
		data:'status='+"500",
		success:function(data)
		{
			if(data.msgContent)
				{alert("ok");}
		}
	});
	return false;
}

function deleteteam(obj){
	$.ajax({
		type:"get",
		url:"/iSulphur/admin/team.do?action=del",
		data:'team_id='+obj.value,
		success:function(data)
		{
			alert("ok");
		}
	});
	return false;
}

function a(){
	listcur();
	listreview();
	listteaminfo2();
}

function task(){
	$.ajax({
		type:"get",
		url:"/iSulphur/admin/url.do",
		data:'task_id='+$("#id").val(),
		success:function(data)
		{
			var content=document.getElementById("web");
			content.empty;
			var str="";
			str=data.msgContent;
			content.innerHTML='网址：<a href="'+str+'">'+str+'</a>';
		}
	});
	return false;
}

function info(){
	$.ajax({
		type:"get",
		url:"/iSulphur/admin/admin.do?action=get_info",
		success:function(data)
		{
			document.getElementById("name").value=data.msgContent.adminName;
			document.getElementById("phone").value=data.msgContent.adminPhone;
		}
	});
	return false;
}