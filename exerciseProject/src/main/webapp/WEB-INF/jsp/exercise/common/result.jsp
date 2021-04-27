<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>About exercise</title>
	<script src="/js/jquery-3.3.1.min.js"></script>
</head>
<body>

<body>
<%
String message = (String)request.getAttribute("message");	
%>
<script type="text/javascript">
	var  type = "${type}";	
	//팝업창일때
	if(type == '1'){
		alert("<%=message%>");
		opener.location.href="${url}";
		self.close();
	}else if(type == '2'){
		alert("<%=message%>");
		top.location.href="${url}";	
	}else if(type == '3'){
		alert("<%=message%>");
		history.back();
	}else if(type == '4'){		
		if (confirm("<%=message%>")) {			
			top.location.href="${trueUrl}";
		}else{			
			top.location.href="${falseUrl}";
		}
	}else if(type == '5'){		
		alert("<%=message%>");
		window.close();
	}else if(type == '6'){
		top.location.href="${url}";	
	}else if(type == '7'){//팝업 오프너 새로고침
		alert("<%=message%>");
		opener.location.reload();
		self.close();
	}else if(type == '8'){//팝업 이동 및 오프너 새로고침
		alert("<%=message%>");
		top.location.href="${url}";	
		opener.location.reload();
	}
</script>
</body>
</html>