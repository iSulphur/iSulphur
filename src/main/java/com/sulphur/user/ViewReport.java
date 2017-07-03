package com.sulphur.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sulphur.connectmysql.ConnectMysql;

@Controller
@RequestMapping("/user")
public class ViewReport {

	public Connection conn;
	@RequestMapping("/view.do")
	public @ResponseBody HashMap<String, String> getReport(HttpServletRequest req) throws SQLException{
		
		HashMap<String, String> result = new HashMap<String, String>();//最终返回的结果
		conn = new ConnectMysql().getConnection();

		/*查询当前团队的项目名称,根据项目名称查询上传报告表，获得上传报告表编号，再去评审表中查询评审表的记录条数*/
		/* 查询当前用户id*/
		String sql = "select id from admin where username=\"" + req.getSession().getAttribute("username") + "\"";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);// 返回的结果集
		String currentId = rs.getString("id");
		
		/*查询当前团队的项目名称*/
		sql = "select projectName from team where Tid = "+ currentId;
		rs = statement.executeQuery(sql);
		String projectName = rs.getString("projectName");
		
		/*查询上传报告表编号*/
		sql = "select uid from uploadreport where projectName = "+ projectName;
		rs = statement.executeQuery(sql);
		
		/*遍历报告表记录*/
		while(rs.next()){
			String uploadReportId = rs.getString("uid");
			/*查询该报告的评审条数*/
			sql = "select rid from review where uid = "+ uploadReportId;
			ResultSet reviewRecord = statement.executeQuery(sql);
			reviewRecord.last();
			int count = rs.getRow();
			//如果已经有3个老师评审了，就是已评审
			if(count == 3){
				result.put(uploadReportId, "1");
			}
			else{
				result.put(uploadReportId, "0");
			}
		}
		return result;
	}
}
