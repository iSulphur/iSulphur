<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Home</title>

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
<link rel="stylesheet" href="assets/js/Lightweight-Chart/cssCharts.css">
<link href="assets/css/new.css" rel="stylesheet" />
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
		<!--/. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">

				<li><a href="on-line editing.html"
					class="active-menu  waves-dark"><i class="fa fa-dashboard"></i>
						新建报告</a>

					<ul class="nav nav-second-level collapse in">
						<li><a href="on-line editing.html" style="color: white;">在线编辑</a>
						</li>
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
				<li><a class="waves-effect waves-dark"
					href="change password.html"><i class="fa fa-fw fa-file"></i>修改密码</a>
				</li>
			</ul>

		</div>

		</nav>
		<form action="onlineEditing.do" method="post">
			<!-- /. NAV SIDE  -->
			<div id="page-wrapper">
				<div class="header">
					<h1 class="page-header">在线编辑</h1>
				</div>
				<div id="page-inner">
					<div class="col-md-12">
						<div class="card">
							<div class="card-content">
								<form class="a">
									<p>团 队 名 称 ：</p>
									<input type="text" placeholder="输入项目名称"></input>
									<p>项 目 名 称 ：</p>
									<input type="text" placeholder="输入项目名称"></input>
									<p>团队负责人：</p>
									<input type="text" placeholder="输入项目名称"></input>
									<p>联 系 电 话 ：</p>
									<input type="text" placeholder="输入项目名称"></input>
									<p>联 系 邮 箱 ：</p>
									<input type="text" placeholder="输入项目名称"></input>
									<p>项 目 进 展 ：</p>
									<textarea class="b" cols="50" rows="10"
										placeholder="详细报告项目进展情况，是否按照计划进行、进度等"></textarea>
									<p>已取得的阶段性成果：</p>
									<textarea class="b" cols="50" rows="10"
										placeholder="成果为系统演示录像、项目报告、软件著作权、专利、论文、获奖情况等多种方式，可另附页或加附件。"></textarea>
									<p>下一阶段项目计划及时间安排：</p>
									<textarea class="b" cols="50" rows="10"
										placeholder="在这里输入内容..."></textarea>
									<input type="submit" value="确定" name="submit" /> <input
										type="reset" value="重置" name="reset" /> <input type="reset"
										value="预览" />

								</form>

							</div>
						</div>
					</div>
				</div>
		</form>
		<!-- /. PAGE INNER  -->
		<!-- /. WRAPPER  -->





	</div>
	</div>
	<!-- /. ROW  -->
	<div class="fixed-action-btn horizontal click-to-toggle">
		<a class="btn-floating btn-large red"> <i class="material-icons">menu</i>
		</a>
		<ul>
			<li><a class="btn-floating red"><i class="material-icons">insert_chart</i></a></li>
			<li><a class="btn-floating yellow darken-1"><i
					class="material-icons">format_quote</i></a></li>
			<li><a class="btn-floating green"><i class="material-icons">publish</i></a></li>
			<li><a class="btn-floating blue"><i class="material-icons">attach_file</i></a></li>
		</ul>
	</div>

	<footer>
	<p>
		Copyright &copy; 2016.Company name All rights reserved.<a
			target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>
	</p>


	</footer>
	</div>
	<!-- /. PAGE INNER  -->
	</div>
	<!-- /. PAGE WRAPPER  -->
	</div>
	<!-- /. WRAPPER  -->
	<!-- JS Scripts-->
	<!-- jQuery Js -->
	<script src="assets/js/jquery-1.10.2.js"></script>

	<!-- Bootstrap Js -->
	<script src="assets/js/bootstrap.min.js"></script>

	<script src="assets/materialize/js/materialize.min.js"></script>

	<!-- Metis Menu Js -->
	<script src="assets/js/jquery.metisMenu.js"></script>
	<!-- Morris Chart Js -->
	<script src="assets/js/morris/raphael-2.1.0.min.js"></script>
	<script src="assets/js/morris/morris.js"></script>


	<script src="assets/js/easypiechart.js"></script>
	<script src="assets/js/easypiechart-data.js"></script>

	<script src="assets/js/Lightweight-Chart/jquery.chart.js"></script>

	<!-- Custom Js -->
	<script src="assets/js/custom-scripts.js"></script>


</body>

</html>