<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.io.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Login</title>
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"  src="jquery-3.1.1.min.js" ></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
  <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
  <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
  <link rel="stylesheet" href="css/style.css"> 
</head>

<body>  
<div class="pen-title">
  <h1>iSulphur</h1>
</div>
<div class="container">
  <div class="card"></div>
  <div class="card">
    <h1 class="title">用户登录</h1>
    <form action ="login.do" method="post">
      <div class="input-container">
        <input  id="yonghu" name="username"/>
        <label for="{label}">用户名</label>
        <div class="bar"></div>
      </div>
      <div class="input-container">
        <input  id="pass" name="password"/>
        <label for="{label}">密码</label>
        <div class="bar"></div>
      </div>
      <div class="button-container">
        <button><span>Go</span></button>
      </div>
      <div class="footer"><a href="#">忘记密码</a></div>
    </form>
  </div>
</div>
</body>
</html>