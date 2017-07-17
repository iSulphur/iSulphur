//teacher


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
        	data.msgContent[i].report_id + "</td>" + "<td>" + data.msgContent[i].ranking + "</td>" +
			"<td><input type='button' value='修改'  name='new'/></td>"+"</tr>";
			document.getElementById("hello").innerHTML = str;
        }
    });
    return false;
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
       	data.msgContent[i].report_id + "</td>" + "<td>" + data.msgContent[i].ranking + "</td>" +
			"<td><a href='view.html'><input type='button' value='查看'  name='new'/></a></td>"+"</tr>";
			document.getElementById("world").innerHTML = str;
       }
   });
   return false;
}

function choose(){
	$.ajax({
		type:"post",
		url:"/iSulphur/teacher/choose.do",
		data:'report_id='+$("#report_id").val(),
		success:function(data)
		{
			if(data.msgContent)				
				{alert("hh");}
				else
				{
					alert("该报告已不存在。！");
				}
		
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