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

import com.sulphur.connectmysql.ConnectMysql;

@Controller

@RequestMapping("/password")
public class ChangePassword {

	Connection conn;

	@RequestMapping("/update")
	public @ResponseBody String queren(HttpSession HttpSession, String currentpassword, String newpassword, String confirmpassword)
			throws SQLException {
		conn = new ConnectMysql().getConnection();

		/* 查询当前用户密码 */
		String sql = "select password from admin where username=\"" + HttpSession.getAttribute("username") + "\"";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);// 返回的结果集

		/*如果新密码和确认密码相等，且当前密码输入正确，密码修改成功*/
		if (newpassword == confirmpassword && currentpassword == rs.getString("password")) {
			PreparedStatement psql;
			psql = conn.prepareStatement("update user set password = ? where username= ? ");
			psql.setString(1, currentpassword);
			psql.setString(2, (String)HttpSession.getAttribute("username"));
			psql.executeUpdate();
			
			/*关闭连接*/
			psql.close();
			conn.close();
			
			return "1";
		} else
			return "0";
	}

}