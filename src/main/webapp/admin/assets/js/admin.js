// check admin Login
function login() {
    $.ajax({
        type: 'post',
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

function findteam(){
	$.ajax({
		type:"post",
		url:"/iSulphur/admin/team.do?action=find",
		data: 'team_id='+$("#team").val(),
		success:function(data)
		{
			
			if(data.msgContent)				
			{alert("成功");}
			else
			{
				alert("失败");
			}
		}
	});
	return false;
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
						"<td><a href='user-change.html'><input type='button' value='修改'  name='new'/></a><input type='submit' value='删除 ' name='delete' class='del' method='get' onsubmit='return deleteteam(data.msgContent[i].teamID)'/> </td>";

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
				'</td><td><a href="user_message.html"><button class="btn btn-default btn-flat">查看</button></a></td></tr>';
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
    			"<span class='card-title'>" + data.msgContent[i].reportTaskID+"</span><p>"+
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
			var content=document.getElementById("cur-task");
			content.empty;
			var str="";
			for(var i=0;i<data.msgContent.length;i++)
				{
				str+='<div class="card-action">'+data.msgContent[i].reportTaskID+'</div><div class="card-content"><p>任务要求：'+data.msgContent[i].taskProperty+
				'</p><p>开始时间：'+data.msgContent[i].beginTime+'</p><p>结束时间：'+data.msgContent[i].endTime+'</p><p>最多提交次数：'+data.msgContent[i].maxSubmitTime+'</p><button class="btn btn-default btn-flat">禁止提交</button>';
				}
			content.innerHTML=str;
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
					'</a></td><td>'+'<a href="user_message.html">'+data.msgContent[i].team_name+'</a></td><td class="center">'+data.msgContent[i].progress+'</td><td class="center">'
					+data.msgContent[i].harvest+'</td></tr>';
					}
				else
					{
					str+='<tr class="even gradeC">'+'<td>'+data.msgContent[i].report_id+'</td><td>'+'<a href="historical-report.html">'+data.msgContent[i].project+
					'</a></td><td>'+'<a href="user_message.html">'+data.msgContent[i].team_name+'</a></td><td class="center">'+data.msgContent[i].progress+'</td><td class="center">'
					+data.msgContent[i].harvest+'</td></tr>';
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
				str+=
				}
			
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

function deleteteam(a){
	$.ajax({
		type:"get",
		url:"/iSulphur/admin/team.do?action=del",
		data:'team_id='+a,
		success:function(data)
		{
			if(data.msgContent)
				{alert("ok");}
		}
	});
	return false;
}

function a(){
	listcur();
	listreview();
	listteaminfo2();
}