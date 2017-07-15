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

