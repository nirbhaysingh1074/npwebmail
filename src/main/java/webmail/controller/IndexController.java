package webmail.controller;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import webmail.chatdwr.ScriptSessList;
import webmail.chatdwr.XmppChatClass;
import webmail.webservice.client.FolderClient;
import webmail.wsdl.GetWebmailAllMailCountResponse;
import webmail.wsdl.GetWebmailImapquotaResponse;
import webmail.wsdl.Imapquota;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.ReferralException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
	@Autowired private ScriptSessList scriptSessList;
	//@Autowired private Runner runner;
	
	@Value ("${xmppDomain}") private String xmppDomain;
	@Value ("${packetReplyTimeout}") private int packetReplyTimeout; // millis
	@Value ("${chatImageFolder}") private String chatImageFolder;
	@Value ("${onlineStatus}") private String onlineStatus;
	
	@Autowired
	FolderClient folderClient;

	/*@RequestMapping(value = "/userDashboard", method = RequestMethod.GET)
	public String getUserDashboard(ModelMap map) {
		return "userDashboard";
	}*/

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin(ModelMap map) 
	{
		return "login";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getLoginindex(ModelMap map) 
	{
		return "redirect:/login";
	}
	
	

	@RequestMapping(value = "/compose", method = RequestMethod.GET)
	public String getcompose(ModelMap map) 
	{
		return "compose";
	}
	
	
	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public ModelAndView getcalendar(ModelMap map, HttpServletRequest request) 
	{
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String port=(String)hs.getAttribute("port");
		String fdrnm=(String)hs.getAttribute("active_folder");
		GetWebmailImapquotaResponse wfresponse=folderClient.getWebmailImapquotaRequest(host, id, pass);
		Imapquota iqt  =wfresponse.getImapquota();
		System.out.println("*********iqt********Qlota="+ iqt.getQuotalimit());
		long ql= iqt.getQuotalimit();
		long qu=iqt.getQuotauses();
		long qper=(qu*100)/ql;
		//System.out.println("********************Quotalimi="+ per
		//System.out.println("********************Quota per="+qper);
		//GetWebmailAllMailCountResponse wfre=folderClient.getWebmailAllMailCountRequest(host, port, id, pass,fdrnm);
		//map.addAttribute("MailCount", ""+(wfre.getAllemailcount()));
		map.addAttribute("QuotaPer", ""+qper);
		map.addAttribute("QuotaLimit", ""+ql);
		map.addAttribute("QuotaUses", ""+qu);
		XmppChatClass xmppChatClass=new XmppChatClass();
		//TODO: SEPERATE THE CONFIGURATION
		xmppChatClass.createConnection(xmppDomain, packetReplyTimeout, request);
		xmppChatClass.registerListeners(chatImageFolder);
		//xmppChatClass.performLogin(loginUser.getUserid(), loginUser.getPassword(), onlineStatus);
		xmppChatClass.performLogin(id, pass, onlineStatus);
		scriptSessList.listenScriptSession();
		request.getSession().setAttribute("xmppChatClass", xmppChatClass);
		map.addAttribute("imageurl", chatImageFolder);
		return new ModelAndView("calendar", map);
		
	}
	
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public ModelAndView getcontacts(ModelMap map, HttpServletRequest request) 
	{
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String port=(String)hs.getAttribute("port");
		String fdrnm=(String)hs.getAttribute("active_folder");
		GetWebmailImapquotaResponse wfresponse=folderClient.getWebmailImapquotaRequest(host, id, pass);
		Imapquota iqt  =wfresponse.getImapquota();
		System.out.println("*********iqt********Qlota="+ iqt.getQuotalimit());
		long ql= iqt.getQuotalimit();
		long qu=iqt.getQuotauses();
		long qper=(qu*100)/ql;
		//System.out.println("********************Quotalimi="+ per
		//System.out.println("********************Quota per="+qper);
		//GetWebmailAllMailCountResponse wfre=folderClient.getWebmailAllMailCountRequest(host, port, id, pass,fdrnm);
		//map.addAttribute("MailCount", ""+(wfre.getAllemailcount()));
		map.addAttribute("QuotaPer", ""+qper);
		map.addAttribute("QuotaLimit", ""+ql);
		map.addAttribute("QuotaUses", ""+qu);
		XmppChatClass xmppChatClass=new XmppChatClass();
		//TODO: SEPERATE THE CONFIGURATION
		xmppChatClass.createConnection(xmppDomain, packetReplyTimeout, request);
		xmppChatClass.registerListeners(chatImageFolder);
		//xmppChatClass.performLogin(loginUser.getUserid(), loginUser.getPassword(), onlineStatus);
		xmppChatClass.performLogin(id, pass, onlineStatus);
		scriptSessList.listenScriptSession();
		request.getSession().setAttribute("xmppChatClass", xmppChatClass);
		map.addAttribute("imageurl", chatImageFolder);
		return new ModelAndView("contacts", map);
		
	}
	
	@RequestMapping(value = "/mycalendar", method = RequestMethod.GET)
	public String mycalendar(ModelMap map) 
	{
		//System.out.println("**************contactpeople");
		return "mycalendar";
	}
	
	@RequestMapping(value = "/contactpeople", method = RequestMethod.GET)
	public String contactpeople(ModelMap map) 
	{
		//System.out.println("**************contactpeople");
		return "contactpeople";
	}
	
	
	@RequestMapping(value = "/contactdetail", method = RequestMethod.GET)
	public String contactdetail(ModelMap map) 
	{
		//System.out.println("**************contactdetail");
		return "contactdetail";
	}
	
	@RequestMapping(value = "/inbox", method = RequestMethod.GET)
	public ModelAndView getinbox(ModelMap map, HttpServletRequest request) 
	{
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String port=(String)hs.getAttribute("port");
		String fdrnm=(String)hs.getAttribute("active_folder");
		GetWebmailImapquotaResponse wfresponse=folderClient.getWebmailImapquotaRequest(host, id, pass);
		Imapquota iqt  =wfresponse.getImapquota();
		System.out.println("*********iqt********Qlota="+ iqt.getQuotalimit());
		long ql= iqt.getQuotalimit();
		long qu=iqt.getQuotauses();
		long qper=(qu*100)/ql;
		//System.out.println("********************Quotalimi="+ per
		//System.out.println("********************Quota per="+qper);
		GetWebmailAllMailCountResponse wfre=folderClient.getWebmailAllMailCountRequest(host, port, id, pass,fdrnm);
		map.addAttribute("MailCount", ""+(wfre.getAllemailcount()));
		map.addAttribute("QuotaPer", ""+qper);
		map.addAttribute("QuotaLimit", ""+ql);
		map.addAttribute("QuotaUses", ""+qu);
		XmppChatClass xmppChatClass=new XmppChatClass();
		//TODO: SEPERATE THE CONFIGURATION
		xmppChatClass.createConnection(xmppDomain, packetReplyTimeout, request);
		xmppChatClass.registerListeners(chatImageFolder);
		//xmppChatClass.performLogin(loginUser.getUserid(), loginUser.getPassword(), onlineStatus);
		xmppChatClass.performLogin(id, pass, onlineStatus);
		scriptSessList.listenScriptSession();
		request.getSession().setAttribute("xmppChatClass", xmppChatClass);
		map.addAttribute("imageurl", chatImageFolder);
		return new ModelAndView("inbox", map);
	
	}
	
	/*
	 * @Autowired private PersonRepo personRepo;
	 */
	
}
