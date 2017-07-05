package com.sulphur.user;

import java.sql.Connection;
import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Calendar;  
import java.util.Date; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sulphur.connectmysql.ConnectMysql;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class Editonline {
	Connection conn;
	
	@RequestMapping(value="/upload",method=RequestMethod.GET)
	
	public @ResponseBody String upload( HttpServletRequest req,@RequestParam("团 队 名 称 ")String t_name,@RequestParam("项 目 名 称 ") String p_name,
			@RequestParam("团队负责人：") String t_leader,@RequestParam("联 系 电 话 ：") String telephone,@RequestParam("联 系 邮 箱 ：") String mail,
			@RequestParam("项 目 进 展 ：") String p_progress,@RequestParam("已取得的阶段性成果：") String harvest,@RequestParam("下一阶段项目计划及时间安排：") String nextaim)
	throws SQLException
	{
		conn = new ConnectMysql().getConnection();
	    PreparedStatement cnm=conn.prepareStatement("insert into upload values(?,?,?,?,?,?,?,?,?,?)");
	    Date d = new Date();  //get time and date now
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d-HH-mm-ss");//format 
        String dateNowStr = sdf.format(d);  //convert to a string
        cnm.setString(1, (String)req.getSession().getAttribute("ID")+dateNowStr);//use id and date&&time produce a report_id
	    cnm.setString(2, dateNowStr);//date&&time as a string 
	    cnm.setString(3, t_name);
		cnm.setString(4, p_name);
		cnm.setString(5, t_leader);
		cnm.setString(6, telephone);
		cnm.setString(7, mail);
		cnm.setString(8, p_progress);
		cnm.setString(9, harvest);
		cnm.setString(10, nextaim);
		cnm.executeQuery();
		cnm.close();
		
		return "success";
		
	
	
}
	
	
	
	
	
	
}
