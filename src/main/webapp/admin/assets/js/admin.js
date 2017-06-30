// check admin Login
function login() {
    $.ajax({
        type: 'post',
        url: 'admin/login',
        data: $('form').serialize(),
        success: function (data) {
            if(data == 1){
            	alert("登陆成功！");
            }
            else {
            	alert("登陆失败！");
            }

        }
    });
    // return false;
}