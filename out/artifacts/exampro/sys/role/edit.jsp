<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>编辑角色管理</title>
	<link rel="stylesheet" type="text/css" href="../../css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../../css/theme.css">
	<script src="../../js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
	<script>
   		$('#a_leader_txt', parent.document).html('编辑角色管理');
	</script>

	<div class="container-fluid">
		<div class="row-fluid">
			<form method="post" action="">
				<div class="btn-toolbar">
					<input type="submit" class="btn btn-primary" value="保存 ">
					<a href="" class="btn">取消</a>

				</div>

				<div class="well">
					<div class="tab-pane active in">
						<input type="hidden" name="roleid" value=""/>
						<label>
							角色名称：
						</label>
						<input type="text" name="rolename" maxlength="10"
							value="">
						<label>
							角色状态：
						</label>
						<select name="rolestate">
							<option value="1" selected="selected">正常</option>
							<option value="0">锁定</option>		
						</select>
						<div style="color: red">
							
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>