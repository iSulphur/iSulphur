package com.sulphur.user.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sulphur.connectmysql.ConnectMysql;

@Controller

@RequestMapping("/password")
public class ChangePassword {

	Connection conn;

	@RequestMapping("/update")
	public @ResponseBody String queren(HttpSession HttpSession, String currentpassword, String newpassword, String confirmpassword)
			throws SQLException {
		conn = new ConnectMysql().getConnection();

		/* ��ѯ��ǰ�û����� */
		String sql = "select password from admin where username=\"" + HttpSession.getAttribute("username") + "\"";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);// ���صĽ����

		/*����������ȷ��������ȣ��ҵ�ǰ����������ȷ�������޸ĳɹ�*/
		if (newpassword == confirmpassword && currentpassword == rs.getString("password")) {
			PreparedStatement psql;
			psql = conn.prepareStatement("update user set password = ? where username= ? ");
			psql.setString(1, currentpassword);
			psql.setString(2, (String)HttpSession.getAttribute("username"));
			psql.executeUpdate();
			
			/*�ر�����*/
			psql.close();
			conn.close();
			
			return "1";
		} else
			return "0";
	}

}
