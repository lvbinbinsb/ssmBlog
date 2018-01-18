<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${requestScope.basePath }syntaxhighlighter/scripts/shCore.js"></script>  
<script type="text/javascript" src="${requestScope.basePath }syntaxhighlighter/scripts/shBrushJScript.js"></script>  
<script type="text/javascript" src="${requestScope.basePath }syntaxhighlighter/scripts/shBrushJava.js"></script>  
<link rel="stylesheet" type="text/css" href="${requestScope.basePath }syntaxhighlighter/styles/shCoreDefault.css">  
<link rel="stylesheet" type="text/css" href="${requestScope.basePath }syntaxhighlighter/styles/shCore.css">  
  

<script type="text/javascript">
     SyntaxHighlighter.all();
</script>
</head>
<body>
	<%
		request.setAttribute("blog_content", request.getParameter("blog_content"));
	%>
	
	<div>
		${requestScope.blog_content }
	</div>

</body>
</html>