package webmail.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import webmail.webservice.client.FolderClient;
import webmail.wsdl.GetComposeMailResponse;

@Controller
public class ComposeMailController {

	@Autowired
	private FolderClient folderClient;
	
	@RequestMapping(value = "/composeMailView", method = RequestMethod.GET)
	
	public String composeView(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		System.out.println("compose controller");
		return "compose";
	}
	
	
	@RequestMapping(value = "/sendComposeMail", method = RequestMethod.GET)
	@ResponseBody
	public String sendCompose(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		boolean status=true;
		
		String to=request.getParameter("to");
		String cc=request.getParameter("cc");
		String bcc=request.getParameter("bcc");
		String sub=request.getParameter("sub");
		String cntt=request.getParameter("cntt");
		String ipAddress=null;
		String getWay = request.getHeader("VIA");   // Gateway
		ipAddress = request.getHeader("X-FORWARDED-FOR");   // proxy
		if(ipAddress==null)
		{
		    ipAddress = request.getRemoteAddr();
		}
		System.out.println("getWay: "+getWay);
		System.out.println("IP Address: "+ipAddress);
		
		
		boolean saveSent=true;
		
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String smtpport=(String)hs.getAttribute("smtpport");
		String imapport=(String)hs.getAttribute("port");
		String fromname=(String)hs.getAttribute("fname");
		
		GetComposeMailResponse res=folderClient.comoseMailRequest(ipAddress, host, smtpport,imapport,saveSent, id, pass, fromname, to, cc, bcc, sub, cntt);
		status=res.isSetComposeStatus();
		System.out.println(res.isSetComposeStatus());
		return ""+status;
	}
}
