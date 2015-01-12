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
import webmail.wsdl.GetWebmailAllMailCountResponse;
import webmail.wsdl.GetWebmailUnreadMailCountResponse;

@Controller
public class MailCountController {

	
	@Autowired
	private FolderClient folderClient;
	
	
	
	@RequestMapping(value = "/getAllMailCountInfolderDiv", method = RequestMethod.GET)
	@ResponseBody
	public String getAllMailCntDiv(ModelMap map, Principal principal,  HttpServletRequest request)
	{
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String port=(String)hs.getAttribute("port");
		String fdrenm=request.getParameter("path");
		String inboxcnt="";
		//System.out.println("********************folder="+fdrenm);
		GetWebmailAllMailCountResponse wfre=folderClient.getWebmailAllMailCountRequest(host, port, id, pass,fdrenm);
		long mcount=wfre.getAllemailcount();
		long end=10;
		if(end>mcount)
		{
			end=mcount;
		}
		if(mcount==0)
		{
			inboxcnt=fdrenm+" is Empty";	
		}
		else
		{
		inboxcnt="1&nbsp;-&nbsp;"+end+"&nbsp;of&nbsp;"+mcount;
		}
		return inboxcnt;
	}
	
	
	
	
	@RequestMapping(value = "/getUnreadMailCountInfolderDiv", method = RequestMethod.GET)
	@ResponseBody
	public String getUnreadMailCntDiv(ModelMap map, Principal principal,  HttpServletRequest request)
	{
	
		
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String port=(String)hs.getAttribute("port");
		String fdr=request.getParameter("fdr");
		String inboxcnt="";
		GetWebmailUnreadMailCountResponse wfre=folderClient.getWebmailUnreadMailCountRequest(host, port, id, pass,fdr);
		int mcnt  =wfre.getUnreademailcount();
		if(mcnt > 0)
		{
			inboxcnt="("+mcnt+")";
		}
		
		return inboxcnt;
		
	}
}
