package webmail.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart; 
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.UIDFolder;
import javax.mail.Flags.Flag;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import webmail.webservice.client.FolderClient;
import webmail.wsdl.GetMailAttachDownloadResponse;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

@Controller
public class DownloadAttachmentController {

	@Autowired
	FolderClient folderClient;

	
	@RequestMapping(value = "/downloadMailAttachByName", method = RequestMethod.GET)
	public String listWebmailInbox(ModelMap map, Principal principal, HttpServletRequest request,HttpServletResponse response) 
	{
		String res="";
		boolean status=true;
		String uid= request.getParameter("uid");
		//String foldernm= request.getParameter("folder");
		String name= request.getParameter("name");
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String port=(String)hs.getAttribute("port");
		String foldername=(String)hs.getAttribute("active_folder");
		System.out.println("***********************hiiiiiiiiiiiiiiiiiiii");
		/*GetMailAttachDownloadResponse attres=folderClient.downloadMailAttachRequest(host, port, id, pass, uid, foldername, name);
		
		InputStream input =(InputStream) attres.getWebamilInputStream();
		
		System.out.println("^^^^^^^^^^^^"+input);
		
		 String headerKey = "Content-Disposition";
         String headerValue = String.format("attachment; filename=\"%s\"",  name);
         response.setHeader(headerKey, headerValue);
         String destFilePath = "/" + name;
         try
         {
         //OutputStream output = response.getOutputStream();
         FileOutputStream output = new FileOutputStream(destFilePath);
         byte[] buffer = new byte[4096];

         int byteRead;

         while ((byteRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, byteRead);
         }
         output.close();
         }
         catch(IOException e)
         {
        	 System.out.println(e);
         }
		*/
		
	//	boolean status=true;
		//	GetMailDisplayResponse inboxlist= new GetMailDisplayResponse();
			
			IMAPFolder folder = null;
	        Store store = null;
	        Flag flag = null;
	        try 
	        {
	            Properties properties = new Properties(); 
				properties.put("mail.store.protocol", "imaps"); 
				properties.put("mail.imap.port", port); 
				properties.put("mail.imap.starttls.enable", "true"); 
				Session emailSession = Session.getDefaultInstance(properties); 
				store = emailSession.getStore("imaps"); 
				store.connect(host, id, pass); 
				IMAPStore imapStore = (IMAPStore) store; 
	            folder = (IMAPFolder) store.getFolder(foldername); //This works for both email account
			
	      UIDFolder uf = (UIDFolder)folder;
	if (!folder.exists()) {
		// inboxlist.setGetInboxByUid(inb);
	    // return   inboxlist;        
	  	}
	  folder.open(Folder.READ_WRITE);
	  long [] arr={Long.parseLong(uid)};
	 Message[] msg =folder.getMessagesByUID(arr);
	int umsg= folder.getUnreadMessageCount();
	 for (int i = 0; i< msg.length; i++)
	  {
		 
		/* inb.setUid(""+uid);
		 Message message = msg[i];
		
		 boolean chkst=	message.isSet(Flags.Flag.FLAGGED);
		 inb.setMailFlage(chkst);
		 
		 boolean chkseen=	message.isSet(Flags.Flag.SEEN);
		 inb.setMailSeen(chkseen);
		 
		 String from = InternetAddress.toString(msg[i].getFrom());
	  inb.setFromId(from);
	 

	  String replyTo = InternetAddress.toString(msg[i].getReplyTo());
	  inb.setReplyId(replyTo);

	  String to = InternetAddress.toString( msg[i].getRecipients(Message.RecipientType.TO));
	  inb.setToId(to);

	  String subject = msg[i].getSubject();
	  inb.setSubject(subject);

	  Date sent = msg[i].getSentDate();
	  SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd");
	  SimpleDateFormat format1 = new SimpleDateFormat("E, MMM dd, yyyy");
	  SimpleDateFormat format2 = new SimpleDateFormat("hh:mm a"); 
	  SimpleDateFormat format3 = new SimpleDateFormat("MMM dd"); 
	  String dip_dt="";
	  Date cdt=new Date();
	  String dt1=format0.format(cdt);
	  String dt2=format0.format(sent);
	  if(dt1.equalsIgnoreCase(dt2))
	  {
		  dip_dt=format2.format(sent);
	  }
	  else
	  {
		  dip_dt=format3.format(sent);
	  }*/
	  
	  //String ttl_dt=format1.format(sent)+" at "+format2.format(sent);
	  
	  //inb.setSendDtae(dip_dt);
	 // inb.setSendDtaeTitle(ttl_dt);
	 
	  Object content = msg[i].getContent();  

	 // String attch="";
	 // String mail_cont="";
	  
	  if(msg[i].getContentType().contains("text/html;") || msg[i].getContentType().contains("text/plain;"))
	  {
		 /* String cnt=msg[i].getContent().toString();
	  	  
	  		mail_cont=cnt;
	  	  */
	  }
	  else
	  {
		  /*if (content instanceof String)  
	{
			  
	    String body = (String)content;  
	  
	     attch="";
	}  
	else */
		if (content instanceof Multipart)  
	{  
	    Multipart multipart = (Multipart)content;  
	    
	 

	    for (int j = 0; j < multipart.getCount(); j++) {

	        BodyPart bodyPart = multipart.getBodyPart(j);
	       // Attachment at=new Attachment();
	        /*mail_cont=getText(bodyPart);
	        if(mail_cont.length()>100)
	    	  {
	    	    mail_cont=mail_cont.substring(0, 99);
	    	  }
	    	 */
	        
	        String disposition = bodyPart.getDisposition();

	          if (disposition != null && (disposition.equalsIgnoreCase("ATTACHMENT"))) 
	          	{ 
	        	 
	              DataHandler handler = bodyPart.getDataHandler();
	              if(handler.getName().equalsIgnoreCase(name))
	              {
	            	 // HttpServletResponse response=null;
	            	//  MockHttpServletResponse response = new MockHttpServletResponse(); 
	              String headerKey = "Content-Disposition";
	              String headerValue = String.format("attachment; filename=\"%s\"",  handler.getName());
	              response.setHeader(headerKey, headerValue);
	              InputStream input = bodyPart.getInputStream();
	              String destFilePath = "/" +  handler.getName();
	              //OutputStream output = response.getOutputStream();
	              FileOutputStream output = new FileOutputStream(destFilePath);
	              byte[] buffer = new byte[4096];

	              int byteRead;

	              while ((byteRead = input.read(buffer)) != -1) {
	                 output.write(buffer, 0, byteRead);
	              }
	              output.close();
	              }
	              
	              
	              
	            //  at.setAttachmentName(handler.getName());
	              
	              /*
	              at.setAttachmentSize(""+bodyPart.getSize());
	              if(attch.equals(""))
	              {
	            	  attch=""+handler.getName(); 
	              }
	              else
	              {
	            	  attch=attch+"-"+handler.getName();             	  
	              }
	              
	              inb.getAttachment().add(at);*/
	            }
	          else 
	          	{
	        	  
	        	  /*if(bodyPart.getContentType().contains("text/html;"))
	    		  {
	        	  String cnt=bodyPart.getContent().toString();
	              if(mail_cont==null || mail_cont.equals(""))
	              {
	          	     	mail_cont=cnt;
	          	  }
	    		  }
	        	  else  if(bodyPart.getContentType().contains("text/plain;"))
	    		  {
	            	  continue;
	    		  }
	        	  else
	        	  {
	        		  String cnt=bodyPart.getContent().toString();
	                  if(mail_cont==null || mail_cont.equals(""))
	                  {
	              	     	mail_cont=cnt;
	              	  }
	        	  }*/
	            }
	   
	  }
	  }
	  }
	 // String arr[]=attch.split(",");
	  //inb.setMailContent(mail_cont);
	  
	  
	  }
	 
	  folder.close(true);
	  store.close();
	  }
	  catch(MessagingException e)
	        {
		  	status=false;
	        }
	  catch(IOException e)
	        {
			status=false;
	        }    
	  catch(Exception e)
	        {
			status=false;
	        }
	        
		return "downloadMailAttachByName";
	}
	
	
}
