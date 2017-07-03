package com.sulphur.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sulphur.connectmysql.ConnectMysql;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class Editonline {
	Connection conn;

	@RequestMapping("/edit.do")

	public @ResponseBody String upload(HttpServletRequest req, @RequestParam("team_name") String t_name,
			@RequestParam("project_name") String p_name, @RequestParam("team_leader") String t_leader,
			@RequestParam("leader_phone") String telephone, @RequestParam("leader_email") String mail,
			@RequestParam("project_progress") String p_progress, @RequestParam("harvest") String harvest,
			@RequestParam("nextaim") String nextaim) throws SQLException {
		conn = new ConnectMysql().getConnection();
		PreparedStatement cnm = conn.prepareStatement("insert into upload values(?,?,?,?,?,?,?,?)");
		cnm.setString(1, t_name);
		cnm.setString(2, p_name);
		cnm.setString(3, t_leader);
		cnm.setString(4, telephone);
		cnm.setString(5, mail);
		cnm.setString(6, p_progress);
		cnm.setString(7, harvest);
		cnm.setString(8, nextaim);
		cnm.executeQuery();
		cnm.close();

		return "1";

	}

}
