//teacher
function showreport(){
    $.ajax({
        type:"get",
        url:"/iSulphur/teacher/report_manager.do?action=show",
        success: function(data) 
        {
			alert("OK啦");
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
        	
        	alert("ok");

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
        	alert("ok");
		

        }
    });
    return false;
}

function choose(){
	$.ajax({
		type:"post",
		url:"/iSulphur/teacher/choose.do",
		data:'report_task_id='+$("#report_task_id").val(),
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
		url:"/iSulphur/admin/team.do?action=review",
		data:'report_id='+$("#report_id").val()+'&ranking='+$("#rank").val()+'&suggest='+$("#suggest").val(),
		success:function(data)
		{
			alert("ok");
		}
	});
	return false;
}

function review1(){
	$.ajax({
		type:"post",
		url:"/iSulphur/admin/team.do?action=review1",
		data:'report_id='+$("#report_id").val()+'&ranking='+$("#rank").val()+'&suggest='+$("#suggest").val(),
		success:function(data)
		{
			if(data.msgContent == 1){
	            alert("上传成功");
	            }
	            else {
	            	alert("上传失败请重试！");
	            }
		}
	});
	return false;
}
