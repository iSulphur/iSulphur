<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title></title>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="assets/materialize/css/materialize.min.css"
	media="screen,projection" />
<!-- Bootstrap Styles-->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- Morris Chart Styles-->
<link href="assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
<!-- Custom Styles-->
<link href="assets/css/custom-styles.css" rel="stylesheet" />
<!-- Google Fonts-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="assets/js/Lightweight-Chart/cssCharts.css" />
<link rel="stylesheet" href="assets/css/new.css" />
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand waves-effect waves-dark" href="index.html"><i
				class="large material-icons">insert_chart</i> <strong>iSulphur</strong></a>

			<div id="sideNav" href="">
				<i class="material-icons dp48">toc</i>
			</div>
		</div>

		<ul class="nav navbar-top-links navbar-right">
			<li><a class="dropdown-button waves-effect waves-dark" href="#!"
				data-activates="dropdown1"><i class="fa fa-user fa-fw"></i> <b>注销用户</b>
					<i class="material-icons right">arrow_drop_down</i></a></li>
		</ul>
		</nav>
		<!-- Dropdown Structure -->
		<ul id="dropdown1" class="dropdown-content">
			<li><a href="#"><i class="fa fa-exchange fa-fw"></i> 切换账号</a></li>
			<li><a href="#"><i class="fa fa-sign-out fa-fw"></i> 退 出</a></li>
		</ul>
		<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">

				<li><a href="on-line editing.html"
					class="waves-effect waves-dark"><i class="fa fa-dashboard"></i>
						新建报告</a>

					<ul class="nav nav-second-level">
						<li><a href="on-line editing.html">在线编辑</a></li>
						<li><a href="uploading.html">上传报告</a></li>
					</ul></li>
				<li><a href="view report.html" class="waves-effect waves-dark"><i
						class="fa fa-bar-chart-o"></i> 查看报告</a>

					<ul class="nav nav-second-level">
						<li><a href="view report.html#yishenhe">已审核</a></li>
						<li><a href="view report.html#daishenhe">待审核</a></li>
					</ul></li>
				<li><a href="agenda.html" class="waves-effect waves-dark"><i
						class="fa fa-qrcode"></i> 待办报告</a></li>

				<li><a href="basic information.html"
					class="waves-effect waves-dark"><i class="fa fa-table"></i>
						基本信息</a></li>
				<li><a class="active-menu waves-effect waves-dark"
					href="change password.html"><i class="fa fa-fw fa-file"></i>修改密码</a>
				</li>
			</ul>

		</div>

		</nav>
		<!-- /. NAV SIDE  -->
		</a>
		</li>
		</ul>

	</div>

	</nav>
	<form action="changePassword.do" method="post">
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">密码修改</h1>
			</div>
			<div id="page-inner">
				<div class="col-md-12">
					<div class="card">
						<div class="card-content">
							<div>

								<!-- Text input-->
								<p>
									<span style="color: red">*</span>当前密码:
								</p>
								<div>
									<input name="oldPassword" type="text" placeholder="输入当前密码">
									<a href="#"><p class="help-block">忘记当前密码？</p></a>
								</div>
							</div>
							<div>

								<!-- Text input-->
								<p>
									<span style="color: red">*</span>新密码:
								</p>
								<div>
									<input name="newPassword" type="text" placeholder="输入新密码">
								</div>
							</div>
							<div>

								<!-- Text input-->
								<p>
									<span style="color: red">*</span>确认密码:
								</p>
								<div>
									<input name="confirm" type="text" placeholder="重新确认密码">
								</div>
							</div>
							<div>
								<!-- Text input-->
								<p>
									<span style="color: red">*</span>验证码:
								</p>
								<div>
									<input type="text" placeholder="输入验证码">
								</div>
							</div>
							<a class="waves-effect waves-light btn">确认</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- /. PAGE INNER  -->
	<!-- /. WRAPPER  -->
	<!-- JS Scripts-->
	<!-- jQuery Js -->
	<script src="assets/js/jquery-1.10.2.js"></script>

	<!-- Bootstrap Js -->
	<script src="assets/js/bootstrap.min.js"></script>

	<script src="assets/materialize/js/materialize.min.js"></script>

	<!-- Metis Menu Js -->
	<script src="assets/js/jquery.metisMenu.js"></script>
	<!-- Custom Js -->
	<script src="assets/js/custom-scripts.js"></script>



</body>

</html>
