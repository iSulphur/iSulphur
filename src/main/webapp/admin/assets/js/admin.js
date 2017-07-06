// check admin Login
function login() {
    $.ajax({
        type: 'post',
        url: '/iSulphur/admin/login.do',
        data: $('form').serialize(),
        success: function (data) {
            if(data == 1){
            	window.location.href="current-task.html";
            }
            else {
            	alert("登陆失败！");
            }

        }
    });
    return false;
}
<<<<<<< HEAD
function adduser(){
	$.ajax({
		type:"post",
		url:"",
		data:"passwprd="+pwd.val(),
		success:function(data)
		{
			
			if(data==1)
			alert("添加成功");
			else
			{
				alert("添加失败");
=======
function updatapwd(){
	$.ajax({
		type:"post",
		url:"/iSulphur/admin/update_pwd.do",
		data:"password="+pwd.val(),
		success:function(data)
		{
			if(data==1)
			alert("修改成功");
			else
			{
				alert("修改失败");
>>>>>>> 091252817ca49130075ef020e8b0043c4858d4e6
			}
		}
	});

}
<<<<<<< HEAD
function updatapwd(){
	$.ajax({
		type:"post",
		url:"",
		data="passwprd="+pwd.val(),
		success:function(data)
		{
			
			if(data==1)
			alert("添加成功");
			else
			{
				alert("添加失败");
			}
		}
=======
function adduser(){
	$.ajax({
		type:"post",
		url:"",
		data:$('form').serialize(),
        success: function (data) {
            if(data == 1){
            alert("添加成功");
            }
            else {
            	alert("添加失败！");
            }

        }
>>>>>>> 091252817ca49130075ef020e8b0043c4858d4e6
	});

}