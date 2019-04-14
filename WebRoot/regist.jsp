<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>个人信息注册</title>
 	<link href="CSS/style.css" rel="stylesheet">
<script type="text/javascript">
	function Check(){
		if(document.getElementById("regist").realname.value==""){
			alert("请输入用户名！");
			document.getElementById("regist").realname.focus();
			return false;
		}
		if(document.getElementById("regist").age.value==""){
			alert("请输入合法年龄！");
			document.getElementById("regist").age.focus();
			return false;
		}
		if(document.getElementById("regist").password.value==""){
			alert("请输入密码！");
			document.getElementById("regist").password.focus();
			return false;
		}
	
		
		if(document.getElementById("regist").password.value != document.getElementById("regist").password1.value){
			alert("密码不一致！");
			document.getElementById("regist").password.focus();
			return false;
		}else{
			//向后端发起请求，注册信息
			document.getElementById("regist").submit();
		}	
	}
	
</script>
  </head>
  
  <body>
    <form id="regist" name="regist" action="servlet/RegisterServlet" method="post" onSubmit="return Check()">
        <h1>个人信息注册</h1>
		<strong>姓&nbsp;&nbsp;&nbsp;名：&nbsp;</strong><input type="text" name="realname" id="realname" style="width: 153px; ">
		<br><br>
		<strong>年&nbsp;&nbsp;&nbsp;龄：&nbsp;</strong><input type="text" name="age" id="age" style="width: 153px; ">
		<br><br>
		<strong>性&nbsp;&nbsp;&nbsp;别：&nbsp;</strong><input type="radio" id="male" name="sex" >男&nbsp;&nbsp; <input type="radio" id="female" name="sex"/>女
		<br><br/>
	
		<strong>输入密码:</strong><input type="password" name="password" id="password" style="width: 155px; ">
		<br><br/>
		<strong>确认密码:</strong><input type="password" name="password1" id="password1" style="width: 155px; ">
		<br><br>
		<input type="button" name="Submit" onclick="Check()" value="提交" >
		<input type="reset" value="重置" >
	</form>
  </body>
</html>
