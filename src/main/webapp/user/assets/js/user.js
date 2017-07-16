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
			
			alert('ok');
		}
	});
	return false;
}

function online(){
	$.ajax({
		type:"post",
		url:"/iSulphur/user/user.do?action=edit",
		data:'team_name='+$("#team-name").val()+'&project='+$("#projection").val()+'&team_leader='+$("#principal").val()+'&leader_phone='+$("#phone").val()+'&leader_mail='+$("#email").val()+'&progress='+$("#progress").val()+'&harvest='+$("#cur-achieve").val()+'&next_aim='+$("#plan").val(),
		success:function(data)
		{			
			alert('ok');
		}
	});
	return false;
}
