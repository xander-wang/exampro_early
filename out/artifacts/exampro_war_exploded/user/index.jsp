<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>在线答题</title>
	<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../css/paper.css">
	<script src="../js/jquery.js" type="text/javascript"></script>
	<script src="../js/bootstrap.js"></script>
</head>

<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">在线考试系统</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="">试题列表</a></li>
				<li><a href="">查看错题</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${user.getUserid() != null}">
						<li>
							<a>
									${user.getUsertruename()}
							</a>
						</li>
						<li>
							<a href="">注销</a>
						</li>
					</c:when>
					<c:otherwise>
						<li><a href="../login.jsp">登录</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>

	<main class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h3 class="panel-title">查看试题</h3>
			</div>
			<div class="panel-body">
				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>
									试题名称
								</th>
								<th>
									题目数量
								</th>

								<th style="width: 90px;">
									操作
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									java基础
								</td>
								<td>
									20
								</td>

								<td>
									<a href="">开始答题</a>
								</td>
							</tr>
							<tr>
								<td>
									大数据基础
								</td>
								<td>
									20
								</td>

								<td>
									<a href="">开始答题</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</main>
</body>
</html>