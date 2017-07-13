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
						"<td><a href='user-change.html'><input type='button' value='修改'  name='new'/></a><input type='submit' value='删除 ' name='delete' class='del'/> </td>";

				}
				document.getElementById("table-content").innerHTML = str;
				$("input[name='delete']").click(function(){
    			$(this).parent().parent().remove();
    		})
    		
			
		
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
			if(data.msgContent)
				{alert("ok");}
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
			if(data.msgContent)
				{alert("hhhh");}
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
			if(data.msgContent)
				{alert("ok");}
		}
	});
	return false;
}