 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Login Page</title>
	
	<!-- Bootstrap -->
	<link href="login_css/bootstrap.css" rel="stylesheet" media="screen">
	<link href="login_css/custom.css" rel="stylesheet" media="screen">

	<!-- Animo css-->
	<link href="login_css/animate+animo.css" rel="stylesheet" media="screen">
	
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	  <script src="assets/js/html5shiv.js"></script>
	  <script src="assets/js/respond.min.js"></script>
	<![endif]-->
	
	
	<!-- Load jQuery -->
	<script src="login_js/jquery.v2.0.3.js"></script>

<script type="text/javascript">
function openMsg()
{
	
//alert('qqqqqqqqqqqqq');
 window.setTimeout(function bye(){errorMessage();document.getElementById("err_div").style.visibility='hidden';}, 2000);
 
}

function openPro()
{
	document.getElementById("load_div").style.display='block';	
}
</script>
  </head>
  <body>
  		 <script type="text/javascript">
function getWebmailfldr(folderPath){
	alert("meeeeeeeeeeeeeeeeeee");
	 $.ajax({
         type: "GET",
         url: "${pageContext.request.contextPath}/getWbmailfolder",
        // data: {'path':folderPath},
         contentType: "application/json",
         async: false,
         success: function (data) {
            // $("#fileSystem").html(data);
            alert(data);
         }
     });
	
}

function getWebmailsubfldr(folderPath){
	alert("meeeeeeeeeeeeeeeeeeeSub="+folderPath);
	 $.ajax({
         type: "GET",
         url: "${pageContext.request.contextPath}/getAllMailCountInfolder",
         data: {'path':folderPath},
         contentType: "application/json",
         async: false,
         success: function (data) {
            // $("#fileSystem").html(data);
            alert(data);
         }
     });
	
}
</script>
<!--<a onclick="getWebmailsubfldr('inbox')">npsubbbbbbbbbbbbbbbbbbb </a><br>
 <a onclick="getWebmailfldr()">npppppppppppppppppppppp </a><br>
<a onclick="getWebmailsubfldr()">npsubbbbbbbbbbbbbbbbbbb </a><br> -->
	<!-- 100% Width & Height container  -->
	<div class="login-fullwidith">

		<!-- error msg  -->
		<%
		String err=request.getParameter("stat");
		if(err!=null && !(err.equals("")))
		{
			String msg="Login Failed";
			if(err.equals("auth"))
			{
				msg="Authentication Failed";
			}
			else if(err.equals("conn"))
			{
				msg="Connection Refused";
			}
		%>
		 <script type="text/javascript">
		
		 
		// alert('hiii');
		 openMsg();
		 
		 </script>
		<div id="err_div" style="margin-top: 50px ! important; width: 354px; text-align:center;" class="login-c3">
				
				<div style="margin-top: 15px; color: white;" ><%=msg %></div>
		</div>
		
		<%} %>
		<!-- Login Wrap  -->
		<form:form method="post" action="dologin"  name="login-form" >
		<div class="login-wrap">
            <div class="logo_new">
                <img height="60px" src="images/logo.png" />
            </div>
			<div class="login-c1">
			
				<div class="cpadding50">
					<div class="username"></div><input type="text" name="id" class="form-control logpadding username" placeholder="Username" >
					<br/>
					<div class="password"></div><input type="text" name="pass" class="form-control logpadding password" placeholder="Password" >
				</div>
			
			</div>
			<div class="login-c2">
				<div class="logmargfix">
					<div class="chpadding50">
							<div class="alignbottom">
								<input class="btn-search4" onclick="openPro()"  type="submit" value="Submit" >					
							</div>
							<div class="alignbottom2">
							  <div class="checkbox">
								<label>
								  <input type="checkbox">Remember
								</label>
							  </div>
							</div>
							<div id="load_div" style="bottom: 6px;margin-right: 3%; position: absolute; right: 57px; display:none;"><img width="80px" src="images/ajax-login.gif" /></div>	
					</div>
				</div>
			</div>
			<div class="login-c3">
				
				<div class="right"><a href="forgot.html" class="whitelink">Lost password?</a></div>
			</div>			
		</div>
		</form:form>
		<!-- End of Login Wrap  -->
	
	</div>	
	<!-- End of Container  -->

	<!-- Javascript  -->
	<!--<script src="login_js/initialize-loginpage.js"></script>-->
	<script src="login_js/jquery.easing.js"></script>
	<!-- Load Animo -->
	<script src="login_js/animo.js"></script>
	<script>
	function errorMessage(){
		$('.login-wrap').animo( { animation: 'tada' } );
	}
	</script>
	
  </body>
</html>