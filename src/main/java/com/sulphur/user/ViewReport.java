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
		
		HashMap<String, String> result = new HashMap<String, String>();//���շ��صĽ��
		conn = new ConnectMysql().getConnection();

		/*��ѯ��ǰ�Ŷӵ���Ŀ����,������Ŀ���Ʋ�ѯ�ϴ����������ϴ�������ţ���ȥ������в�ѯ�����ļ�¼����*/
		/* ��ѯ��ǰ�û�id*/
		String sql = "select id from admin where username=\"" + req.getSession().getAttribute("username") + "\"";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);// ���صĽ����
		String currentId = rs.getString("id");
		
		/*��ѯ��ǰ�Ŷӵ���Ŀ����*/
		sql = "select projectName from team where Tid = "+ currentId;
		rs = statement.executeQuery(sql);
		String projectName = rs.getString("projectName");
		
		/*��ѯ�ϴ��������*/
		sql = "select uid from uploadreport where projectName = "+ projectName;
		rs = statement.executeQuery(sql);
		
		/*����������¼*/
		while(rs.next()){
			String uploadReportId = rs.getString("uid");
			/*��ѯ�ñ������������*/
			sql = "select rid from review where uid = "+ uploadReportId;
			ResultSet reviewRecord = statement.executeQuery(sql);
			reviewRecord.last();
			int count = rs.getRow();
			//����Ѿ���3����ʦ�����ˣ�����������
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
