<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.sun.mail.imap.IMAPFolder"%>
<%@ page language="java" import="java.util.*,
 javax.activation.DataHandler,
 javax.mail.*,
 javax.mail.internet.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'createfolder.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="js/jquery-1.8.3.min.js" type="text/javascript"></script> 
<script src="ckeditor/ckeditor.js"></script>
<script src="ckeditor/adapters/jquery.js"></script>
<script type="text/javascript">


	/* CKEDITOR.replace( 'editor1', {	  
		toolbar: [		// Defines toolbar group without name.																			
		{ name: 'document', groups: [ 'mode', 'document', 'doctools' ], items: [ 'Source' ] },
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ], items: [ 'Bold', 'Italic', 'Underline' ] },
		{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ], items: [  'NumberedList', 'BulletedList','JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ] },
		{ name: 'links', items: [ 'Link', 'Unlink', '' ] },
		{ name: 'insert', items: [ 'Image',  'Table',  'Smiley', 'SpecialChar',  ] },
		{ name: 'styles', items: [ 'Styles', 'Format', 'Font', 'FontSize' ] },
		{ name: 'colors', items: [ 'TextColor', 'BGColor' ] }
		],
		height: $(window).height()-343,
		enterMode: CKEDITOR.ENTER_BR,
		allowedContent : true
		
	}); */
	

</script>

  </head>

  <body>

 <%
	String host= "127.0.0.1"; //(String)hs.getAttribute('host');
	String id= "arangar@storkrubber.com"   ;  //(String)hs.getAttribute('id');
	String pass= "arjan@259";
	
	
	Properties props = System.getProperties();
    props.setProperty("mail.store.protocol", "imaps");
        try {
            Session ses = Session.getDefaultInstance(props, null);
            Store store = ses.getStore("imaps");
            store.connect(host, id, pass);
          //   out.println(store);


           Folder[] f = store.getDefaultFolder().list();
       		for(Folder fd:f)
       			{
       			String sub="no";
       			if(fd.isSubscribed())
       			{
       			sub="yes";
       			}
 				Folder t[]=fd.list();
 				int mode=fd.getType();
 				String mn="";
 				if(mode!=2)
 					{
						mn=""+fd.getMessageCount();
						}
				    out.println("<br><br>"+fd.getName()+" ("+mn+") &nbsp;&nbsp;&nbsp;&nbsp;          type:"+mode+"&nbsp;&nbsp;&nbsp;&nbsp; sub="+sub);
  				if(t.length>0)
  					{
  					out.print("    ---------->"+t.length);
  					}
 				for(Folder f1:t)
 					{
 					String sub1="no";
       			if(f1.isSubscribed())
       			{
       			sub1="yes";
       			}
						Folder t1[]=f1.list();
   				    out.println("<br> ---------->"+f1.getName()+"("+f1.getMessageCount()+")&nbsp;&nbsp;&nbsp;&nbsp;          type:"+f1.getType()+"&nbsp;&nbsp;&nbsp;&nbsp; sub="+sub1);
  					if(t1.length>0)
  						{
 						out.print("    ---------->"+t1.length);
  						}
  					for(Folder f2:t1)
 						{
 						String sub2="no";
       			if(f2.isSubscribed())
       			{
       			sub2="yes";
       			}
	    				    out.println("<br> -------------------->"+f2.getName()+"("+f2.getMessageCount()+")&nbsp;&nbsp;&nbsp;&nbsp;          type:"+f2.getType()+"&nbsp;&nbsp;&nbsp;&nbsp; sub="+sub2);
							}
  				    }
  				}
//folder.close(true);


      store.close();
/*
javax.mail.Folder[] folders = store.getDefaultFolder().list("*");

for (javax.mail.Folder folder : folders) {
     if ((folder.getType() & javax.mail.Folder.HOLDS_MESSAGES) != 0) {
         System.out.println(folder.getFullName() + " (" + folder.getMessageCount()+")");
     }
     }
     */
}
catch(Exception e)
{
out.print(e);
}  
 
  %>




  </body>
</html>
