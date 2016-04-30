package webmail.controller;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Connections;

import redis.clients.jedis.JedisPool;
import webmail.bean.MailAccSetting;
import webmail.chatdwr.ScriptSessList;
import webmail.chatdwr.XmppChatClass;
import webmail.idle.IdleMail;
import webmail.webservice.client.CalendarClient;
import webmail.webservice.client.FileClient;
import webmail.webservice.client.FolderClient;
import webmail.webservice.client.WebmailClient;
import webmail.wsdl.CreateCalendarResponse;
import webmail.wsdl.CreateEssentialFoldersResponse;
import webmail.wsdl.CreateFileResponse;
import webmail.wsdl.DeleteFileResponse;
import webmail.wsdl.GetFileByPathResponse;
import webmail.wsdl.GetLdapFNameResponse;
import webmail.wsdl.GetLdapModifyOneAttResponse;
import webmail.wsdl.GetWebmailAuthResponse;
import org.apache.log4j.Logger;

@Controller
public class LoginAuthenticationController {


	@Autowired private ScriptSessList scriptSessList;
	@Value ("${xmppDomain}") private String xmppDomain;
	@Value ("${packetReplyTimeout}") private int packetReplyTimeout; // millis
	@Value ("${chatImageFolder}") private String chatImageFolder;
	@Value ("${onlineStatus}") private String onlineStatus;
	
	 static Logger log = Logger.getLogger(LoginAuthenticationController.class.getName());
	
	@Autowired
	private WebmailClient webmailClient;
	@Autowired
	private FolderClient folderClient;
	
	@Autowired
	private FileClient fileClient;
	
	@Autowired
	private CalendarClient calendarclient;
	
	public  DirContext getConnection(String url,String uid,String password, String base)
	{
		    DirContext ctx=null;
		    try
		    {
		    	String username="";
		    	/*String arr[]=uid.split("@");
		    	String dom="";
		    	if(arr!=null && arr.length==2)
		    	{
		    		dom=arr[1];
		    	}*/
		   // String base="ou=Users,domainName="+dom+",o=domains,"+mbase;
		    username="mail="+uid+","+base;
		    //System.out.println("@@@@@@@@@@@@@@@@@@ ldap user="+username);
		    Hashtable env = new Hashtable();
		    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		    env.put(Context.PROVIDER_URL, url); // LDAP host and base
		    env.put("java.naming.ldap.attributes.binary", "jpegPhoto");
		    // LDAP authentication options
		    env.put(Context.SECURITY_AUTHENTICATION, "simple");
		    env.put(Context.SECURITY_PRINCIPAL, username);
		    env.put(Context.SECURITY_CREDENTIALS, password);

		     ctx = new InitialDirContext(env);
		
		    }
		    catch(Exception e)
		    {
		    	System.out.print(e.toString());
		    	e.printStackTrace();
		    	
		    }
		    return ctx;
	}
	
	public void closeConn(DirContext ctx)
	{
		try
	    {
	   if(ctx!=null)
	   {
	     ctx.close();
	   }
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	//System.out.print(e.toString());
	    	
	    }
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public String dologin(ModelMap map,  HttpServletRequest request) 
	{
		String devtype=request.getHeader("User-Agent");
				System.out.println("ddevice>>>>>>>>>>"+devtype);
		
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		//String host="127.0.0.1"; 
		String host="mail.silvereye.in";
		String chathost="mail.silvereye.in";
		String redisHost = "localhost";  
		int redisPort = 6379;
		String XMailer="Sandesh Webmail 1.0";
		String port="993";
		//String port="143";
		String sieveport="2000";
		String smtpport="587";
		//String smtpport="25";
		String ldapurl ="ldap://mail.silvereye.in:389";
		//String ldapurl ="ldap://127.0.0.1:389";
		String base1 ="dc=silvereye,dc=in";
	    String base="";
	    String dom="silvereye.in";
		if(!id.contains("@"))
		{
		id=id+"@"+dom;	
		}
		
		//log.debug("Hello this is a debug message");
	    //log.info("Hello this is an info message");
	//	System.out.println("********************login="+id +"   pass="+pass);
		HttpSession hs=request.getSession();
		GetWebmailAuthResponse wauthres= webmailClient.getWebmailAuthRequest(host, port, id, pass);
		int flag=wauthres.getGetWebmailAuth();
		//System.out.println("********************auth="+flag);
		if(flag==1)
		{
			
			String mid=id;
			String arr[]=mid.split("@");
	    	if(arr!=null && arr.length==2)
	    	{
	    	dom=arr[1];
	    	}
	    	base="ou=Users,domainName="+dom+",o=domains,"+base1; 
			
			hs.setAttribute("id", id);
			hs.setAttribute("pass", pass);
			hs.setAttribute("chathost",chathost);
			hs.setAttribute("host",host);
			hs.setAttribute("port", port);
			hs.setAttribute("smtpport", smtpport);
			hs.setAttribute("sieveport", sieveport);
			hs.setAttribute("active_folder", "INBOX");
			hs.setAttribute("active_contact", "/Contacts/Personal Contacts");
			hs.setAttribute("ldapurl", ldapurl);
			hs.setAttribute("ldapbase", base);
			hs.setAttribute("XMailer", XMailer);
			hs.setAttribute("forcePasswordChange", "false");
			hs.setAttribute("redisHost", redisHost);
			hs.setAttribute("redisPort", redisPort);
			GetLdapFNameResponse ldapres=webmailClient.getLdapFNmae(ldapurl, id, pass, base, "cn");
			//System.out.println("**************ldapfname="+ldapres.getGetFName());
			String cn=ldapres.getGetFName();
			hs.setAttribute("fname", cn);
			
			  DirContext ctx=getConnection(ldapurl,id,pass,base);
		 	  String entry="mail="+id+","+base;
		 	 byte[] jpegBytes1=null;
		 	 try
		 	 {
		 	  Attributes testAttributes = ctx.getAttributes(entry);
		      Attribute jpegPhoto1 = testAttributes.get("jpegPhoto");
		      if(jpegPhoto1 != null)
		      {
		    	  jpegBytes1 = (byte[]) jpegPhoto1.get();
		      }
		      
		 	 }
		 	 catch(Exception e)
		 	 {
		 		 e.printStackTrace();
		 	 }
		 	closeConn(ctx);
		 	hs.setAttribute("img_myurl", jpegBytes1);
			//GetLdapFNameResponse ldapres0=webmailClient.getLdapFNmae(ldapurl, id, pass, base, "jpegphoto");
			//hs.setAttribute("img_myurl", ldapres0.getGetFName());
			//System.out.println("**************ldapfname="+ldapres1.getGetFName());
			//hs.setAttribute("img_myurl", "http://mail.silvereye.in/photo/");
			
			
			//IdleMail.inboxIdle(host, port, id, pass);
			
			
			GetLdapFNameResponse ldapres2=webmailClient.getLdapFNmae(ldapurl, id, pass, base, "accountSetting");
			String mailset=ldapres2.getGetFName();
			//System.out.println("**************accountSetting="+ldapres2.getGetFName());
			hs.setAttribute("previewPane", "Vertical view");
			 hs.setAttribute("limitMail", "15");
			 hs.setAttribute("limitContact", "20");
			 hs.setAttribute("generalSettingNotification", "New mail notifications on");
			 hs.setAttribute("chatService", "true");
			 if(mailset!=null && !mailset.equals(""))
		        {
				 mailset=mailset.replace("\"", "");
				 mailset=mailset.replace("[", "");
				 mailset=mailset.replace("{", "");
				 mailset=mailset.replace("}]", "");
		         String 	arr_set[]=mailset.split("},");
		       
		        	for(int i=0;i< arr_set.length;i++)
		        	{
		        String new_arr[]=arr_set[i].split(",");
		        String arr_nm[]=new_arr[0].split(":");
		        String arr_val[]=new_arr[1].split(":");
		        String arrnm=arr_nm[1];
		        String arrval=arr_val[1];
		       
		        if(arrnm.equalsIgnoreCase("limitMail"))
		        {
		        	hs.setAttribute("limitMail", arrval);
		        }
		        else if(arrnm.equalsIgnoreCase("limitContact"))
		        {
		        	hs.setAttribute("limitContact", arrval);
		        }
		        else if(arrnm.equalsIgnoreCase("generalSettingImages"))
		        {
		        	hs.setAttribute("generalSettingImages", arrval);
		        }
		        else if(arrnm.equalsIgnoreCase("generalSettingReply"))
		        {
		        	hs.setAttribute("generalSettingReply", arrval);
		        }
		        else if(arrnm.equalsIgnoreCase("generalSettingNotification"))
		        {
		        	hs.setAttribute("generalSettingNotification", arrval);
		        }
		        else if(arrnm.equalsIgnoreCase("generalSettingOutgoing"))
		        {
		        	hs.setAttribute("generalSettingOutgoing", arrval);
		        }
		        else if(arrnm.equalsIgnoreCase("previewPane"))
		        {
		        	hs.setAttribute("previewPane", arrval);
		        }
		        else if(arrnm.equalsIgnoreCase("chatService"))
		        {
		        	hs.setAttribute("chatService", arrval);
		        }
		        /*if(arrnm.equalsIgnoreCase("limitMail"))
		        {
		        	MailAccSetting.limitMail=Integer.parseInt(arrval);
		        }
		        else if(arrnm.equalsIgnoreCase("limitContact"))
		        {
		        	MailAccSetting.limitContact=Integer.parseInt(arrval);
		        }
		        else if(arrnm.equalsIgnoreCase("generalSettingImages"))
		        {
		        	MailAccSetting.generalSettingImages=arrval;
		        }
		        else if(arrnm.equalsIgnoreCase("generalSettingReply"))
		        {
		        	MailAccSetting.generalSettingReply=arrval;
		        }
		        else if(arrnm.equalsIgnoreCase("generalSettingNotification"))
		        {
		        	MailAccSetting.generalSettingNotification=arrval;
		        }
		        else if(arrnm.equalsIgnoreCase("generalSettingOutgoing"))
		        {
		        	MailAccSetting.generalSettingOutgoing=arrval;
		        }
		        else if(arrnm.equalsIgnoreCase("previewPane"))
		        {
		        	MailAccSetting.previewPane=arrval;
		        }*/
		        
		        	
		        }
		       
		       
		        
		        }
			 else
		        {
				 
		        	/*MailAccSetting.limitMail=15;
		        	MailAccSetting.limitContact=50;
		        	MailAccSetting.previewPane="Off view";*/
		        }
			 
			GetLdapFNameResponse ldapres3=webmailClient.getLdapFNmae(ldapurl, id, pass, base, "bgColor");
			String bgColor=ldapres3.getGetFName();
			
			if(bgColor==null || bgColor.length()==0)
			{
				bgColor="#03B3B2";
			}
			 
			 hs.setAttribute("bgColor", bgColor);
			
			 //Create Profile
			 GetLdapFNameResponse ldapres5=webmailClient.getLdapFNmae(ldapurl, id, pass, base, "createprofile");
			 if(ldapres5.getGetFName()!=null && ldapres5.getGetFName().equalsIgnoreCase("true"))
				{
				 try
					{
					
						CreateEssentialFoldersResponse res1=folderClient.createEssentialFolders(id, pass);
						//String srt=res.getResponseMessage();
						
						
						String calfile = "/" + id + "/calendar/default.ics";
					//	DeleteFileResponse dlr = fileClient.deleteFile(calfile, id, pass);
						
						GetFileByPathResponse fileByPath = fileClient.getFileByPath(calfile, id, pass);
						webmail.wsdl.File fileNode = fileByPath.getFile();
						if(fileNode.getFileContent() == null)
						{
							CreateCalendarResponse res = calendarclient.createCalendar(cn, "#6633FF","PUBLISH");
							
							CreateFileResponse cr = fileClient.createFile("default.ics", "/" + id+ "/calendar", id, pass, "", "", res.getResponse(), 0);
							System.out.println("file created : " + cr.getFile());
						}
						
						GetLdapModifyOneAttResponse	modattone=webmailClient.getLdapModifyOneAtt(ldapurl, id, pass, base, "createprofile", "FALSE");
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			 
			GetLdapFNameResponse ldapres1=webmailClient.getLdapFNmae(ldapurl, id, pass, base, "forcePasswordChange");
			
			
			//System.out.println("**************ldapfname="+ldapres1.getGetFName());
			if(ldapres1.getGetFName()!=null)
			{
				if(ldapres1.getGetFName().equalsIgnoreCase("false"))
				{
					try
					{
					XmppChatClass xmppChatClass=new XmppChatClass();
					
					//TODO: SEPERATE THE CONFIGURATION
					String		xmppDomain=id.substring(id.indexOf("@")+1);
					
					xmppChatClass.createConnection(chathost,xmppDomain, packetReplyTimeout, request);
					xmppChatClass.registerListeners(chatImageFolder);
					//xmppChatClass.performLogin(loginUser.getUserid(), loginUser.getPassword(), onlineStatus);
					xmppChatClass.performLogin(id, pass, onlineStatus);
					scriptSessList.listenScriptSession();
					request.getSession().setAttribute("xmppChatClass", xmppChatClass);
					hs.setAttribute("chatStatus",true);
					//map.addAttribute("imageurl", chatImageFolder);
					}
					catch(Exception ex)
					{
						hs.setAttribute("chatStatus",false);
					}
					
					try
					{
						/*JedisPool jedisPool=Connections.connectionJedis(redisHost, redisPort);
						hs.setAttribute("jedisPool", jedisPool);*/
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					IdleMail.inboxIdle(hs,host, port, id, pass);
					return "redirect:/inbox";
				}
				else
				{
					hs.setAttribute("forcePasswordChange", "true");
					return "redirect:/firstinbox";
				}
			}
			else
			{
				//hs.setAttribute("forcePasswordChange", "true");
				//return "redirect:/firstinbox";
				IdleMail.inboxIdle(hs,host, port, id, pass);
				return "redirect:/inbox";
			}
		}
		else if(flag==0)
		{
			return "redirect:/login?stat=auth";
		}
		else if(flag==2)
		{
			return "redirect:/login?stat=conn";
		}
		return "redirect:/login?stat=failed";
		 
	}
	
}
