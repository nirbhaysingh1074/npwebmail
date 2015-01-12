<%@ page language="java" import="java.net.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String ipAddress=null;
String getWay = request.getHeader("VIA");   // Gateway
ipAddress = request.getHeader("X-FORWARDED-FOR");   // proxy
if(ipAddress==null)
{
    ipAddress = request.getRemoteAddr();
}
out.println("<br>getWay: "+getWay);
out.println("<br>IP Address: "+ipAddress);
InetAddress ipAddr = InetAddress.getLocalHost();
out.println("<br>"+ipAddr.getHostAddress());

%>
</body>
</html>