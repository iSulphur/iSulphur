// check admin Login
function login() {
    $.ajax({
        type: 'post',
        url: '/iSulphur/admin/login.do',
        data: $('form').serialize(),
        success: function (data) {
            if(data == 1){
            	window.location.href="admin-base-info.html";
            }
            else {
            	alert("登陆失败！");
            }

        }
    });
    return false;
}