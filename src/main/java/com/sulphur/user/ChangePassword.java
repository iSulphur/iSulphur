package com.sulphur.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sulphur.connectmysql.ConnectMysql;

@Controller

@RequestMapping("/user")
public class ChangePassword {

	Connection conn;

	@RequestMapping("/password.do")
	public @ResponseBody String queren(@RequestParam("currentpassword") String currentpassword, @RequestParam("newpassword") String newpassword, @RequestParam("confirmpassword") String confirmpassword, HttpServletRequest req)
			throws SQLException {
		conn = new ConnectMysql().getConnection();

		/* 查询当前用户密码 */
		String sql = "select password from password where username=\"" + req.getSession().getAttribute("username") + "\"";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		
		if (newpassword == confirmpassword && currentpassword == rs.getString("password")) {
			PreparedStatement psql;
			psql = conn.prepareStatement("update password set password = ? where username= ? ");
			psql.setString(1, currentpassword);
			psql.setString(2, (String)req.getSession().getAttribute("username"));
			psql.executeUpdate();
			
			psql.close();
			conn.close();
			
			return "1";
		} else
			return "0";
	}

}