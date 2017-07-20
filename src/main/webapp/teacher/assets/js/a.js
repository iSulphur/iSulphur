
function showreport(){
    $.ajax({
        type:'post',
        url: '/iSulphur/teacher/report_manager.do?action=show',
        data:'report_task_id='+$("#report_task_id").val(),
        success: function(data) 
        {
        	
        	var str="";
        	var i=0;
        	for(i=0;i<data.msgContent.length;i++)
        	str+="<div class='col-md-4 col-sm-4'>"+"<div class='card teal'>"+"<div class='card-content white-text'>"+
					"<span class='card-title'>报告"+(i+1)+"</span>"+"</div>"+"<div><span>团队名  "+data.msgContent[i].team_name+"</span></div>"+"<div class='card-action'>"+"<a href='review.html'>详情</a>"+
					"<a href='#'>删除</a>"+"</div>"+"</div>"+"</div>"
					document.getElementById("hhh").innerHTML = str;
        }
    });
    return false;
}

function showreview0() {
	 $.ajax({
        type: 'get',
        url: '/iSulphur/teacher/review_manager.do?action=findall0',
        success: function(data) 
        { 	
        	var str="";
        	var i=0;
        	for(i=0;i<data.msgContent.length;i++)
        	str+="<tr>" + "<td>" + data.msgContent[i].review_id + "</td>" + "<td>" +
        	data.msgContent[i].report_id + "</td>" + "<td>" + data.msgContent[i].ranking + "</td><td>"+data.msgContent[i].suggest+"</td>" +
			"<td><a href='update.html'><input type='button' value='修改'  name='new'/></a></td>"+"</tr>";
			document.getElementById("hello").innerHTML = str;
        }
    });
    return false;
}

function show(){
	var t = getCookie("report_id") ;
	if(t != null){
		$.ajax({
			type:"post",
			url:"/iSulphur/teacher/report_manager.do?action=choose",
			data:'report_id='+t,
			success:function(data)
			{
				if(data.msgContent)				
					{
					document.getElementById("tea-pro").innerHTML="sr";
					document.getElementById("tea-teamname").innerHTML=data.msgContent.team_name;
					document.getElementById("tea-teamleader").innerHTML=data.msgContent.team_leader;
					document.getElementById("tea-teamphone").innerHTML=data.msgContent.leader_phone;
					document.getElementById("tea-teammail").innerHTML=data.msgContent.leader_mail;
					document.getElementById("tea-proprogress").innerHTML=data.msgContent.progress;
					document.getElementById("tea-teamachieve").innerHTML=data.msgContent.harvest;
					document.getElementById("tea-teamplan").innerHTML=data.msgContent.next_aim;
					}
					else
					{
						alert("该报告已不存在。！");
					}
			
			}
		});
		return false;
	}
	
}

function showreview1() {
	 $.ajax({
       type: 'get',
       url: '/iSulphur/teacher/review_manager.do?action=findall1',
       success: function(data) 
       { 	
       	var str="";
       	var i=0;
       	for(i=0;i<data.msgContent.length;i++)
       	str+="<tr>" + "<td>" + data.msgContent[i].review_id + "</td>" + "<td>" +
       	data.msgContent[i].report_id + "</td>" + "<td>" + data.msgContent[i].ranking + "</td><td>"+data.msgContent[i].suggest+"</td>";
			document.getElementById("world").innerHTML = str;
       }
   });
   return false;
}


function review(){
	$.ajax({
		type:"post",
		url:"/iSulphur/teacher/report_manager.do?action=review",
		data:'report_id='+$("#report_id").val()+'&ranking='+$("#ranking").val()+'&suggest='+$("#suggest").val(),
		success:function(data)
		{
			alert("评审已保存！");
		}
	});
	return false;
}

function review1(){
	$.ajax({
		type:"post",
		url:"/iSulphur/teacher/report_manager.do?action=review1",
		data:'report_id='+$("#report_id").val()+'&ranking='+$("#ranking").val()+'&suggest='+$("#suggest").val(),
		success:function(data)
		{
			alert("评审已上传！");
		}
	});
	return false;
}

function update(){
	$.ajax({
		type:"post",
		url:"/iSulphur/teacher/review_manager.do?action=update",
		data:'report_id='+$("#report_id").val()+'&ranking='+$("#ranking").val()+'&suggest='+$("#suggest").val(),
		success:function(data)
		{
			alert("更改成功！");
		}
	});
	return false;
}

function upload(){
	$.ajax({
		type:"post",
		url:"/iSulphur/teacher/review_manager.do?action=upload",
		data:'report_id='+$("#report_id").val()+'&ranking='+$("#ranking").val()+'&suggest='+$("#suggest").val(),
		success:function(data)
		{
			alert("上传成功啦！");
		}
	});
	return false;
}

function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

function load(){
	var task_id;
	if(getCookie('task_id')){
		task_id = getCookie('task_id');
	}
	else{
		
		task_id = GetQueryString("t");
		setCookie('task_id', task_id);
	}
	var base = new Base64();  
	  
	//2.解密  
	var d_task_id = base.decode(task_id);  
	
//	alert(d_task_id);
	
	if(d_task_id){
	$.ajax({
        type:'get',
        url: '/iSulphur/teacher/report_manager.do?action=show',
        data:'report_task_id='+d_task_id,
        success: function(data) 
        {
        	var str="";
        	var i=0;
        	for(var i=0;i<data.msgContent.length;i++)
        	str+="<div class='col-md-4 col-sm-4'>"+"<div class='card teal'>"+"<div class='card-content white-text'>"+
					"<span class='card-title'>"+data.msgContent[i].team_name+"</span></div>"+"<div class='card-action'>"+"<button class='btn btn-default btn-flat' name='report_id' value="+data.msgContent[i].report_id+" onclick='choose(this);'>详情</button>"+
					"</div>"+"</div>"+"</div>"
					document.getElementById("hhh").innerHTML = str;
        }
    });
	}
	else
	{alert("参数不存在！");}
}

function Base64() {  
	   
    // private property  
    _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";  
   
    // public method for encoding  
    this.encode = function (input) {  
        var output = "";  
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;  
        var i = 0;  
        input = _utf8_encode(input);  
        while (i < input.length) {  
            chr1 = input.charCodeAt(i++);  
            chr2 = input.charCodeAt(i++);  
            chr3 = input.charCodeAt(i++);  
            enc1 = chr1 >> 2;  
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);  
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);  
            enc4 = chr3 & 63;  
            if (isNaN(chr2)) {  
                enc3 = enc4 = 64;  
            } else if (isNaN(chr3)) {  
                enc4 = 64;  
            }  
            output = output +  
            _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +  
            _keyStr.charAt(enc3) + _keyStr.charAt(enc4);  
        }  
        return output;  
    }  
   
    // public method for decoding  
    this.decode = function (input) {  
        var output = "";  
        var chr1, chr2, chr3;  
        var enc1, enc2, enc3, enc4;  
        var i = 0;  
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");  
        while (i < input.length) {  
            enc1 = _keyStr.indexOf(input.charAt(i++));  
            enc2 = _keyStr.indexOf(input.charAt(i++));  
            enc3 = _keyStr.indexOf(input.charAt(i++));  
            enc4 = _keyStr.indexOf(input.charAt(i++));  
            chr1 = (enc1 << 2) | (enc2 >> 4);  
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);  
            chr3 = ((enc3 & 3) << 6) | enc4;  
            output = output + String.fromCharCode(chr1);  
            if (enc3 != 64) {  
                output = output + String.fromCharCode(chr2);  
            }  
            if (enc4 != 64) {  
                output = output + String.fromCharCode(chr3);  
            }  
        }  
        output = _utf8_decode(output);  
        return output;  
    }  
   
    // private method for UTF-8 encoding  
    _utf8_encode = function (string) {  
        string = string.replace(/\r\n/g,"\n");  
        var utftext = "";  
        for (var n = 0; n < string.length; n++) {  
            var c = string.charCodeAt(n);  
            if (c < 128) {  
                utftext += String.fromCharCode(c);  
            } else if((c > 127) && (c < 2048)) {  
                utftext += String.fromCharCode((c >> 6) | 192);  
                utftext += String.fromCharCode((c & 63) | 128);  
            } else {  
                utftext += String.fromCharCode((c >> 12) | 224);  
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);  
                utftext += String.fromCharCode((c & 63) | 128);  
            }  
   
        }  
        return utftext;  
    }  
   
    // private method for UTF-8 decoding  
    _utf8_decode = function (utftext) {  
        var string = "";  
        var i = 0;  
        var c = c1 = c2 = 0;  
        while ( i < utftext.length ) {  
            c = utftext.charCodeAt(i);  
            if (c < 128) {  
                string += String.fromCharCode(c);  
                i++;  
            } else if((c > 191) && (c < 224)) {  
                c2 = utftext.charCodeAt(i+1);  
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));  
                i += 2;  
            } else {  
                c2 = utftext.charCodeAt(i+1);  
                c3 = utftext.charCodeAt(i+2);  
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));  
                i += 3;  
            }  
        }  
        return string;  
    }  
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

function choose(obj){
	var report_id = obj.value;
	setCookie('report_id',report_id);
	window.location.href = "review.html";
}

