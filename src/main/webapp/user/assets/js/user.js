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
		data:'report_task_id='+$("#report_task_id").val()+'&team_name='+$("#team-name").val()+'&project='+$("#projection").val()+'&team_leader='+$("#principal").val()+'&leader_phone='+$("#phone").val()+'&leader_mail='+$("#email").val()+'&progress='+$("#progress").val()+'&harvest='+$("#cur-achieve").val()+'&next_aim='+$("#plan").val(),
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
			var str="";
		    
			for(var i=0;i<data.msgContent.length;i++)
				{
				str+='<div class="card-action">'+data.msgContent[i].reportTaskId+'</div><div class="card-content"><p>任务要求：'+data.msgContent[i].taskProperty+
				'</p><p>开始时间：'+data.msgContent[i].beginTime+'</p><p>结束时间：'+data.msgContent[i].endTime+'</p><p>最多提交次数：'+data.msgContent[i].maxSubmitTime+'</p>'+'<a href="on-line-edit.html"> <button class="btn btn-default btn-flat">新建</button></a></div>';
				}
			content.innerHTML=str;
		}
	});
	return false;
}

function listrep(){
	$.ajax({
		type:"post",
		url:"/iSulphur/user/user.do?action=review_report",
		success:function(data)
		{
			for(var i=0;i<data.msgContent.length;i++){
			if(data.msgContent[i].review_status==1)
			{var content1=document.getElementById("reviewed-rep");
			content1.empty;
			var str="";
			str+='<a href="#!" class="collection-item">'+data.msgContent[i].project+'</a>';
			content1.innerHTML=str;}
			
			else{
			var content2=document.getElementById("reviewing-rep");
			content2.empty;
			var str="";
			str+='<a href="#!" class="collection-item">'+data.msgContent[i].project+'<button style="float:right;"  name="report_id" value='+data.msgContent[i].report_id+' onclick="choose(this);">查看</button>'+'</a>';
			content2.innerHTML=str;
			    }
			}
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
				str+='<p>团队名称:'+data.msgContent.teamName+'</p><p style="margin: 15px 0 0 ;">项目名称:'+data.msgContent.project
				+'</p><p style="margin: 15px 0 0 ;">团队负责人:'+data.msgContent.teamLeader+'</p><p style="margin: 15px 0 0 ;">联系电话:'
				+data.msgContent.leaderPhone+'</p><p style="margin: 15px 0 0 ;">联系邮箱:'+data.msgContent.leaderMail+'</p>';
			content.innerHTML=str;
		}
	});
	return false;
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

function listrew(){
	var t = getCookie("report_id") ;
	if(t != null){
	$.ajax({
		type:"post",
		url:"/iSulphur/user/user.do?action=get_review",
		data:'report_id='+t,
		success:function(data)
		{			
			document.getElementById("add_report_id").innerHTML=data.msgContent.report_id;
			document.getElementById("add_ranking").innerHTML=data.msgContent.ranking;
			document.getElementById("add_suggest").innerHTML=data.msgContent.suggest;
			
		}
	});
	return false;
	}
}

function choose(obj){
	var report_id = obj.value;
	setCookie('report_id',report_id);
	window.location.href = "add.html";
}

function result(){
	var t = getCookie("report_id") ;
	if(t != null){
	$.ajax({
	type:"post",
	url:"iSulphur/user/user.do?action=find_result",
	data:'report_id='+t,
	success:function(data)
	{			
		document.getElementById("result").innerHTML=data.msgContent.finalResult;
	}
	});
return false;
	}
}


function a(){
	result();
	listrew();
}