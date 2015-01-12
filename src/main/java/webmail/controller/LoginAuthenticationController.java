package webmail.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webmail.chatdwr.ScriptSessList;
import webmail.chatdwr.XmppChatClass;
import webmail.webservice.client.FolderClient;
import webmail.wsdl.GetLdapFNameResponse;
import webmail.wsdl.GetWebmailAuthResponse;


@Controller
public class LoginAuthenticationController {

	
	
	
	@Autowired
	private FolderClient folderClient;
	
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public String dologin(ModelMap map,  HttpServletRequest request) 
	{
		
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		String host="mail.silvereye.in";
		String port="993";
		String smtpport="25";
		String ldapurl ="ldap://49.50.64.100:389";
		String base1 ="dc=silvereye,dc=in";
	    String base="";
	    String dom="silvereye.in";
		if(!id.contains("@"))
		{
		id=id+"@"+dom;	
		}
		System.out.println("********************login="+id +"   pass="+pass);
		HttpSession hs=request.getSession();
		GetWebmailAuthResponse wauthres= folderClient.getWebmailAuthRequest(host, port, id, pass);
		int flag=wauthres.getGetWebmailAuth();
		System.out.println("********************auth="+flag);
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
			hs.setAttribute("host",host);
			hs.setAttribute("port", port);
			hs.setAttribute("smtpport", smtpport);
			hs.setAttribute("active_folder", "inbox");
			hs.setAttribute("active_contact", "Personal Contacts");
			//GetLdapFNameResponse ldapres=folderClient.getLdapFNmae(ldapurl, id, pass, base, "cn");
			//System.out.println("**************ldapfname="+ldapres.getGetFName());
			hs.setAttribute("fname", "Nirbhay Pratap Singh");
			hs.setAttribute("img_myurl", "https://mail.silvereye.in/photo/");
			
			return "redirect:/inbox";
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
