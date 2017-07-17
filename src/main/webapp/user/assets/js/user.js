function login() {
    $.ajax({
        type:'post',
        url: '/iSulphur/user/user.do?action=login',
        data: $('form').serialize(),
        success: function (data) {
            if(data.msgContent == 1){
            	window.location.href="home.html";
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
		url:"/iSulphur/user/user.do?action=update_password",
		data:'current_password='+$("#cur-password").val()+'&new_password='+$("#password").val()+'&confirm_password='+$("#pass").val(),
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
		url:"/iSulphur/user/user.do?action=update_team",
		data: 'team_name='+$("#team").val()+'&project='+$("#project").val()+'&team_leader='+$("#name").val()+'&leader_phone='+$("#phone").val()+'&leader_mail='+$("#mail").val(),
		success:function(data)
		{
			
			if(data.msgContent==1)				
			{alert("修改成功");}
			else
			{
				alert("修改失败");
			}
		}
	});
	return false;
}

function listallagend(){
	$.ajax({
		type:"get",
		url:"/iSulphur/user/user.do?action=agenda_report",
		success:function(data)
		{
			var content=document.getElementById("agenda-rep");
			content.empty;
			var str="";
			for(var i=0;i<data.msgContent.length;i++)
				{
				str+='<a href="#!" class="collection-item">'+data.msgContent[i].report_id+'</a>';
				}
			content.innerHTML=str;
		}
	});
	return false;
}

function online(){
	$.ajax({
		type:"post",
		url:"/iSulphur/user/user.do?action=edit",
		data:'report_task_id'+$("#report_task_id").val()+'&team_name='+$("#team-name").val()+'&project='+$("#projection").val()+'&team_leader='+$("#principal").val()+'&leader_phone='+$("#phone").val()+'&leader_mail='+$("#email").val()+'&progress='+$("#progress").val()+'&harvest='+$("#cur-achieve").val()+'&next_aim='+$("#plan").val(),
		success:function(data)
		{			
			alert('ok');
		}
	});
	return false;
}

function upload(){
	$.ajax({
		type:"post",
		url:"/iSulphur/user/user.do?action=upload",
		data:'path='+$("#file").val(),
		success:function(data)
		{			
			alert('hhhhhhh');
		}
	});
	return false;
}

function listtask(){
	$.ajax({
		type:"post",
		url:"/iSulphur/user/user.do?action=getTask",
		success:function(data)
		{			
			var content=document.getElementById("curtask");
			content.empty;
			var str="";
			for(var i=0;i<data.msgContent.length;i++)
				{
				str+='<div class="card-action">'+data.msgContent[i].reportTaskID+'</div><div class="card-content"><p>任务要求：'+data.msgContent[i].taskProperty+
				'</p><p>开始时间：'+data.msgContent[i].beginTime+'</p><p>结束时间：'+data.msgContent[i].endTime+'</p><p>最多提交次数：'+data.msgContent[i].maxSubmitTime+'</p>'+'<a href="on-line-edit.html"> <button class="btn btn-default btn-flat">新建</button></a>';
				}
			content.innerHTML=str;
		}
	});
	return false;
}

function listrep(){
	$.ajax({
		type:"post",
		url:"/iSulphur/user/user.do?action=agenda_report",
		success:function(data)
		{			
			var content1=document.getElementById("reviewed-rep");
			content1.empty;
			var str="";
			for(var i=0;i<data.msgContent.length;i++)
				{
				str+='<a href="#!" class="collection-item">'+data.msgContent[i].report_id+'</a>';
				}
			content1.innerHTML=str;
			
			var content2=document.getElementById("reviewing-rep");
			content2.empty;
			var str="";
			for(var i=0;i<data.msgContent.length;i++)
				{
				str+='<a href="#!" class="collection-item">'+data.msgContent[i].report_id+'</a>';
				}
			content2.innerHTML=str;
		}
	});
	return false;
}

function teaminfo(){
	$.ajax({
		type:"post",
		url:"/iSulphur/user/user.do?action=getTeam",
		success:function(data)
		{			
			var content=document.getElementById("basicinfo");
			content.empty;
			var str="";
			for(var i=0;i<data.msgContent.length;i++)
				{
				str+='<p>团队名称:'+data.msgContent[i].teamName+'</p><p style="margin: 15px 0 0 ;">项目名称:'+data.msgContent[i].project
				+'</p><p style="margin: 15px 0 0 ;">团队负责人:'+data.msgContent[i].teamLeader+'</p><p style="margin: 15px 0 0 ;">联系电话:'
				+data.msgContent[i].leaderPhone+'</p><p style="margin: 15px 0 0 ;">联系邮箱:'+data.msgContent[i].leaderMail+'</p>';
				}
			content.innerHTML=str;
		}
	});
	return false;
}