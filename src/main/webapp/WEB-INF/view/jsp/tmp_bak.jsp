<%@ page language="java" import="java.util.*,java.text.*, javax.activation.DataHandler,javax.mail.search.*, javax.mail.*,javax.mail.internet.*,com.sun.mail.imap.*,javax.mail.Flags.*;" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    
    <title>My JSP 'inbox.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <%
   IMAPFolder folder = null;
        Store store = null;
       // String subject = null;
        Flag flag = null;
       final String keyword="test";
        try 
        {
          Properties properties = new Properties(); 
			properties.put("mail.store.protocol", "imap"); 
			properties.put("mail.imap.port", "143"); 
			properties.put("mail.imap.starttls.enable", "true"); 
			Session emailSession = Session.getDefaultInstance(properties); 
			// emailSession.setDebug(true); 
			// create the IMAP3 store object and connect with the pop server 
			 store = emailSession.getStore("imap"); 
			//change the user and password accordingly 
			store.connect("127.0.0.1", "nirbhay@silvereye.in", "SIS@2009"); 
			IMAPStore imapStore = (IMAPStore) store; 
			out.println("<br>imapStore ---" + imapStore); // This doesn't work for other email account
         folder = (IMAPFolder) store.getFolder("inbox"); //This works for both email account



if (!folder.exists()) {
  out.println("<br><br><br>No INBOX...");
  System.exit(0);
  }
  folder.open(Folder.READ_ONLY);
  
  
  
  
  // creates a search criterion
  SearchTerm searchCondition = new SearchTerm() {
      @Override
      public boolean match(Message message) {
          try {
        	  /* String from="nirbhay@silvereye.co";
        	  if(from!=null && !(from.equals("")))
	        	 {
     		  Address[] fromAddress = message.getFrom();
	        	 boolean st_frm=false;
	              for (int i=0;i<fromAddress.length;i++ ) {
	                  if (fromAddress[i].toString().contains(from)  ) {
	                	  st_frm= true;
	                	  break;
	                  } 
	              }
	              if(!st_frm)
	              {
	            	  return st_frm;
	              }
	              else
	              {
	            	  return true;
	              }
	        	 }
     	 */
        	
     	 String to="nirbhay@silvereye.co";
     	 if(to!=null && !(to.equals("")))
    	 {
		  Address[] toAddress = message.getAllRecipients();
     	 boolean st_to=false;
          for (int i=0;i<toAddress.length;i++ ) {
              if (toAddress[i].toString().contains(to)  ) {
            	  st_to= true;
            	  break;
              } 
          }
          if(!st_to)
          {
        	  return st_to;
          }
          else
          {
        	  return true;
          }
    	 }
     	 
        	  /* Address[] fromAddress = message.getFrom();
        	 
              if (fromAddress != null && fromAddress.length > 0) {
                  if (fromAddress[0].toString().contains("nirbhay@silvereye.co") && message.getSubject().contains(keyword) ) {
                      return true;
                  } 
              }*/
             /*  SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
          	  String dateInString = "28-Jan-2015";
           	// String dateInString1 = "28-Jan-2015 23:59:59";
          	  
          	Date date = formatter.parse(dateInString);
         	Date date1 = formatter.parse(""+message.getReceivedDate());
         	//out.print("<br>dt="+date+" -- dt1="+date1);
              if (date.equals(date1)   ) {
                  return true;
              } */
          } catch (MessagingException ex) {
              ex.printStackTrace();
          }
          catch (Exception ex) {
              ex.printStackTrace();
          }
          return false;
      }
  };
  
  /*
  
   // creates a search criterion
  SearchTerm searchCondition = new SearchTerm() {
      @Override
      public boolean match(Message message) {
          try {
              if (message.getSubject().contains(keyword)) {
                  return true;
              }
          } catch (MessagingException ex) {
              ex.printStackTrace();
          }
          return false;
      }
  };
  
  */
  
  
  Message[] foundMessages = folder.search(searchCondition);
  
  for (int i = 0; i < foundMessages.length; i++) {
      Message message = foundMessages[i];
      String subject = message.getSubject();
      Address[] fromAddress = message.getFrom();
      String from = fromAddress[0].toString();
      Address[]toAdress=message.getAllRecipients();
     Date dt=message.getReceivedDate();
      String to=toAdress[0].toString();
      out.println("<br>To="+to);
      out.println("<br>From="+from);
      out.println("<br>date="+dt);
     out.println("<br>Found message #" + i + ": " + subject);
     out.println("<br>*******************************************");
  }

  // disconnect
  folder.close(false);
  store.close();
} catch (NoSuchProviderException ex) {
  System.out.println("No provider.");
  ex.printStackTrace();
} catch (MessagingException ex) {
  System.out.println("Could not connect to the message store.");
  ex.printStackTrace();
}
  
  
  
        
    %>
  </body>
</html>
