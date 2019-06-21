<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../common/common.jsp" %>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
	<link rel="stylesheet" href="${basePath}/resource/css/login.css">    
    <title>注册界面</title>

  </head>
  
  <body>
    <form action="${basePath}/login/resig" method="post">
    	<div class="maindiv">
	    	<div class = "topdiv">
	    		<div class="leftdiv">
		    		<dl>
		    			<dt>用&nbsp;&nbsp;户&nbsp;名：</dt>
		    			<dt>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</dt>
		    			<dt>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</dt>
		    			<dt>账户类型：</dt>
		    		</dl>
		    	</div>
		    	<div class="rightdiv">
		    		<dl>
		    			<dt><input type="text" name="username"/></dt>
		    			<dt><input type="password" name="password"/></dt>
		    			<dt><input type="text" name="email"/></dt>
		    			<dt>
		    				<select name="userType">
				  				<option label="管理员" value="a">
				  				<option label="老&nbsp;&nbsp;&nbsp;师" value="t">
				  				<option label="学&nbsp;&nbsp;&nbsp;生" value="s">
				  			</select>
				  		</dt>
		    		</dl>
		    	</div>
		    	<input id="submit" type="submit"  value="注册"/>
		    	<input id="btn" type="button"  value="取消" onclick="location.href='javascript:history.go(-1);'"/>
	    	</div>	
    	</div>
  		
  	</form>
  </body>
</html>
