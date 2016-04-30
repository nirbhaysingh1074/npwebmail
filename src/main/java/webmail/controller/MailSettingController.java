package webmail.controller;


import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Connections;
import com.sieve.manage.ManageSieveClient;
import com.sieve.manage.ManageSieveResponse;
import com.sieve.manage.ParseException;
import com.sieve.manage.SieveScript;
import com.sieve.manage.examples.ConnectAndListScripts;
import com.sun.mail.imap.IMAPFolder;

import webmail.webservice.client.WebmailClient;
import webmail.wsdl.GetLdapFNameResponse;
import webmail.wsdl.GetLdapModifyListAttResponse;
import webmail.wsdl.GetLdapModifyOneAttResponse;
import webmail.wsdl.GetWebmailCreateFolderResponse;
import webmail.wsdl.GetWebmailDeleteFolderResponse;
import webmail.wsdl.GetWebmailFolderOtherResponse;
import webmail.wsdl.GetWebmailFolderResponse;
import webmail.wsdl.GetWebmailSubscriptionFolderResponse;
import webmail.wsdl.MailImapFolders;

import javax.mail.*;
import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;

@Controller
public class MailSettingController {

	
	@Autowired
	private WebmailClient webmailClient;
	

	
	

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
	
	
	
	public boolean modyfyAddAttr(DirContext ctx, String MY_ENTRY, String attr, String val)
	{
		boolean status=true;
		try
	    {
			 ModificationItem[] mods = new ModificationItem[1];
				//Attribute mod1 = new BasicAttribute("FTP", "TRUE");
				  // Attribute mod1 = new BasicAttribute("phone", "555-555-5555");
			  javax.naming.directory.Attribute mod0 = new javax.naming.directory.BasicAttribute(attr, val);


				    mods[0] = new ModificationItem(DirContext.ADD_ATTRIBUTE, mod0);
				    ctx.modifyAttributes(MY_ENTRY, mods);
	    }
	    catch(Exception e)
	    {
	    	status=false;
	    	e.printStackTrace();
	    	//System.out.print(e.toString());
	    	
	    }
		return status;
	}
	
	public boolean modyfyRemAttr(DirContext ctx, String MY_ENTRY, String attr)
	{
		boolean status=true;
		try
	    {
			 ModificationItem[] mods = new ModificationItem[1];
				//Attribute mod1 = new BasicAttribute("FTP", "TRUE");
				  // Attribute mod1 = new BasicAttribute("phone", "555-555-5555");
			  javax.naming.directory.Attribute mod0 = new javax.naming.directory.BasicAttribute(attr);


				    mods[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, mod0);
				   ctx.modifyAttributes(MY_ENTRY, mods);
	    }
	    catch(Exception e)
	    {
	    	status=false;
	    	e.printStackTrace();
	    	//System.out.print(e.toString());
	    	
	    }
		return status;
	}
	
	public boolean modyfyRepAttr(DirContext ctx, String MY_ENTRY, String attr, String val)
	{
		boolean status=true;
		try
	    {
			 ModificationItem[] mods = new ModificationItem[1];
				//Attribute mod1 = new BasicAttribute("FTP", "TRUE");
				  // Attribute mod1 = new BasicAttribute("phone", "555-555-5555");
			  javax.naming.directory.Attribute mod0 = new javax.naming.directory.BasicAttribute(attr, val);


				    mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod0);
				    ctx.modifyAttributes(MY_ENTRY, mods);

	    }
	    catch(Exception e)
	    {
	    	status=false;
	    	e.printStackTrace();
	    	//System.out.print(e.toString());
	    	
	    }
		return status;
	}
	
	
	@RequestMapping(value = "/settingOpen", method = RequestMethod.GET)
	
	public String composeView(ModelMap map, Principal principal, HttpServletRequest request) throws IOException, com.sieve.manage.ParseException 
	{
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		
		GetWebmailFolderResponse wfresponse=webmailClient.getWebmailFolderRequest(host, id, pass);
		String myfdr=wfresponse.getGetWebmailFolder();
		map.addAttribute("webmailClient", webmailClient);
		map.addAttribute("fldr_lst", myfdr);
		
	
		
		return "setting";
	}
	
@RequestMapping(value = "/settingOpenTemp", method = RequestMethod.GET)
	
	public String composeViewtemp(ModelMap map, Principal principal, HttpServletRequest request) throws IOException, ParseException 
	{
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
 		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		int sieveport=Integer.parseInt( hs.getAttribute("sieveport").toString());
		//ConnectAndListScripts.USERNAME=id;
		//ConnectAndListScripts.PASSWORD=pass;
		ManageSieveResponse    resp=       ConnectAndListScripts.getConnect(host,sieveport,id,pass);
		 ManageSieveClient client = ConnectAndListScripts.client;
		
		// Create a list to hold the result of the next command
		List<SieveScript> scripts = new ArrayList<SieveScript>();

		// Get the list of this users scripts. The current contents of
		// the list will be deleted first.
		resp = client.listscripts(scripts);
		if (!resp.isOk()) {
		    throw new IOException("Could not get list of scripts: " + resp.getMessage());
		}
		map.addAttribute("sieveclient",client);
		map.addAttribute("sieveManageResponse",resp);
		map.addAttribute("scriptList",scripts);
		
		hs.setAttribute("sieveclient",client);
		hs.setAttribute("sieveManageResponse",resp);
		/*GetWebmailFolderResponse wfresponse=webmailClient.getWebmailFolderRequest(host, id, pass);
		String myfdr=wfresponse.getGetWebmailFolder();
		map.addAttribute("webmailClient", webmailClient);
		map.addAttribute("fldr_lst", myfdr);*/
		GetWebmailFolderOtherResponse sfres=webmailClient.getWebmailFolderOtherRequest(host, id, pass, "");
	 	List<MailImapFolders> sflst= sfres.getGetSubFolder().getMailFolderListReturn().getMailFolderList();
	 	/*for(MailImapFolders fd: sflst)
	 	{
	 		System.out.println("^^^^^^^^^^^^^^^^^^name="+fd.getFolderFullName()+"----mode="+fd.getFolderMode()+"----sub="+fd.isIsSubs()+"---child="+fd.isHasChild());
	 	}*/
	 	map.addAttribute("webmailClient", webmailClient);
		map.addAttribute("imapfldrlst", sflst);
		
		return "settingtemp";
	}



@RequestMapping(value = "/deleteSettingTag", method = RequestMethod.GET)
@ResponseBody
public String deleteSettingTag(ModelMap map, Principal principal, HttpServletRequest request) {
	
	boolean st=true;
	HttpSession hs=request.getSession();
	String host=(String)hs.getAttribute("host");
	String id=(String)hs.getAttribute("id");
	String pass=(String)hs.getAttribute("pass");
	String ldapurl=(String)hs.getAttribute("ldapurl");
	String ldapbase=(String)hs.getAttribute("ldapbase");
	String tagnm=request.getParameter("tagnm");
	tagnm=tagnm.trim();
	tagnm=tagnm.replaceAll(" ", "_");
	
	GetLdapFNameResponse ldapres=webmailClient.getLdapFNmae(ldapurl, id, pass, ldapbase, "labelSetting");
	String labset=ldapres.getGetFName();
	String newtagnm="";
	String arr[]=labset.split("~");
	for(int i=0; i< arr.length; i++)
	{
		int len=arr[i].indexOf(tagnm+"#");
		if(len==0)
		{
			continue;
		}
		else
		{
			if(newtagnm.length()==0)
			{
				newtagnm=arr[i];
			}
			else
			{
				newtagnm+="~"+arr[i];
			}
		}
	}
	
	GetLdapModifyOneAttResponse modattone=webmailClient.getLdapModifyOneAtt(ldapurl, id, pass, ldapbase, "labelSetting", newtagnm);
	st= modattone.isGetStatus();
if(!st)
{
	return "Label is not deleted.";
}
return ""+st;
	
	
}



@RequestMapping(value = "/addNewLabel", method = RequestMethod.GET)
@ResponseBody
public String addNewLabel(ModelMap map, Principal principal, HttpServletRequest request) {
	
	boolean st=true;
	HttpSession hs=request.getSession();
	String host=(String)hs.getAttribute("host");
	String id=(String)hs.getAttribute("id");
	String pass=(String)hs.getAttribute("pass");
	String ldapurl=(String)hs.getAttribute("ldapurl");
	String ldapbase=(String)hs.getAttribute("ldapbase");
	String lname=request.getParameter("lname");
	String lcolor=request.getParameter("lcolor");
	
	int len=lname.length();
	
	int ind1=lname.indexOf("$");
	int ind2=lname.indexOf("#");
	int ind3=lname.indexOf("~");
	int ind4=lname.indexOf("_");
	
	
	if(lname==null || lcolor==null || lname.equalsIgnoreCase("")  || lcolor.equalsIgnoreCase(""))
	{
		return "Label name or color is empty.";
	}
	else if(len>50)
	{
		return "Label name is too long.";
	}
	else if(ind1>=0 || ind2>=0 || ind3>=0 || ind4>=0)
	{
		return "Label contains a forbidden character. (~ or _ or $ or #)" ;
	}
	else if(lname.equalsIgnoreCase("Important") || lname.equalsIgnoreCase("Work") || lname.equalsIgnoreCase("Personal") || lname.equalsIgnoreCase("To Do") || lname.equalsIgnoreCase("Later"))
	{
		return "Label is already exist.";
	}
	else
	{
	
		lname=lname.replace(" ", "_");
		
		GetLdapFNameResponse ldapres=webmailClient.getLdapFNmae(ldapurl, id, pass, ldapbase, "labelSetting");
		String labset=ldapres.getGetFName();
		
	
		int opt1=labset.indexOf("~"+lname+"#");
		int opt2=labset.indexOf(lname+"#");
		if(opt1<0 && opt2!=0)
		{
		
		String labset_res="";
		if(labset==null || labset.length()==0)
		{
			labset_res=lname+lcolor;
		}
		else
		{
			labset_res=labset+"~"+lname+lcolor;
		}
		
		//labset_res=labset_res.replace(" ", "_");
		
		GetLdapModifyOneAttResponse modattone=webmailClient.getLdapModifyOneAtt(ldapurl, id, pass, ldapbase, "labelSetting", labset_res);
		st= modattone.isGetStatus();
	if(!st)
	{
		return "Label is not created.";
	}
	return ""+st;
		}
		else
		{
			return "Label is already exist.";
		}
	}
}



@RequestMapping(value = "/saveIdentities", method = RequestMethod.GET)
@ResponseBody
public String saveIdentities(ModelMap map, Principal principal,
		HttpServletRequest request) {
	
	

	
	 	
	
	HttpSession hs=request.getSession();
	String host=(String)hs.getAttribute("host");
	String id=(String)hs.getAttribute("id");
	String pass=(String)hs.getAttribute("pass");
	String ldapurl=(String)hs.getAttribute("ldapurl");
	String ldapbase=(String)hs.getAttribute("ldapbase");
	boolean status=true;
	String fn=request.getParameter("fn");
	String mob=request.getParameter("mob");
	String hmob=request.getParameter("hmob");
	String tel=request.getParameter("tel");
	String addr=request.getParameter("addr");
	String addrpcode=request.getParameter("addrpcode");
	String back_mail=request.getParameter("back_mail"); 
	String sig=request.getParameter("sig");
	String sig_st=request.getParameter("sig_st");
	String repto=request.getParameter("repto");
	String dob=request.getParameter("dob");
	String anni=request.getParameter("anni");
	String plang = request.getParameter("plang");
	List <String> attname=new ArrayList<String>();
	List <String> attval=new ArrayList<String>();
	
	attname.add("cn");
	attval.add(fn);
	
	attname.add("mobile");
	attval.add(mob);
	
	attname.add("homePhone" );
	attval.add(hmob);
	
	attname.add("telephoneNumber" );
	attval.add(tel);
	
	attname.add("postalAddress");
	attval.add(addr);
	
	attname.add("postalCode");
	attval.add(addrpcode);
	
	attname.add( "backupMailAddress");
	attval.add(back_mail);
	
	attname.add("signature");
	attval.add(sig);
	
	if(sig_st!=null && !(sig_st.equals("")))
	{
		sig_st=sig_st.toUpperCase();
	}
	attname.add("signatureStatus");
	attval.add(sig_st);
	   
	attname.add("replyTo");
	attval.add(repto);
	
	attname.add("preferredLanguage");
	attval.add(plang);
	
	attname.add("anniversary");
	attval.add(anni);
	
	attname.add( "dateOfBirth");
	attval.add(dob);
	
	//System.out.println("^^^^^^^^^^^^^^^^="+attname.size());
	//System.out.println("^^^^^^^^^^^^^^^^="+attval.size());
	
	GetLdapModifyListAttResponse modattlst=webmailClient.getLdapModifyListAtt(ldapurl, id, pass, ldapbase, attname, attval);
	status=modattlst.isGetStatus();
	if(status)
	{
		hs.setAttribute("fname", fn);
	}
	
	
	/*DirContext ctx=getConnection(ldapurl,id,pass,ldapbase);
		String entry="mail="+id+","+ldapbase;
		if(fn!=null && !(fn.equals("")))
		{
			status= modyfyRepAttr(ctx, entry, "cn", fn);
		}
		
		
		if(mob!=null && !(mob.equals("")))
		{
			status= modyfyRepAttr(ctx, entry, "mobile", mob);
		}
		else
		{
			status= modyfyRemAttr(ctx, entry, "mobile");	
		}
		
		if(hmob!=null && !(hmob.equals("")))
		{
			status= modyfyRepAttr(ctx, entry, "homePhone", hmob);
		}
		else
		{
			status= modyfyRemAttr(ctx, entry, "homePhone");	
		}
		
		if(tel!=null && !(tel.equals("")))
		{
			status= modyfyRepAttr(ctx, entry, "telephoneNumber", tel);
		}
		else
		{
			status= modyfyRemAttr(ctx, entry, "telephoneNumber");	
		}
		
		
		
		if(addr!=null && !(addr.equals("")))
		{
			status= modyfyRepAttr(ctx, entry, "postalAddress", addr);
		}
		else
		{
			status= modyfyRemAttr(ctx, entry, "postalAddress");	
		}
		
		if(addrpcode!=null && !(addrpcode.equals("")))
		{
			status= modyfyRepAttr(ctx, entry, "postalCode", addrpcode);
		}
		else
		{
			status= modyfyRemAttr(ctx, entry, "postalCode");	
		}
		
		if(back_mail!=null && !(back_mail.equals("")))
		{
			status= modyfyRepAttr(ctx, entry, "backupMailAddress", back_mail);
		}
		else
		{
			status= modyfyRemAttr(ctx, entry, "backupMailAddress");	
		}
		
		if(sig!=null && !(sig.equals("")))
		{
			status= modyfyRepAttr(ctx, entry, "signature", sig);
		}
		else
		{
			status= modyfyRemAttr(ctx, entry, "signature");	
		}
		
		if(sig_st!=null && !(sig_st.equals("")))
		{
			sig_st=sig_st.toUpperCase();
			status= modyfyRepAttr(ctx, entry, "signatureStatus", sig_st);
		}
		else
		{
			status= modyfyRemAttr(ctx, entry, "signatureStatus");	
		}
		
		if(repto!=null && !(repto.equals("")))
		{
			status= modyfyRepAttr(ctx, entry, "replyTo", repto);
		}
		else
		{
			status= modyfyRemAttr(ctx, entry, "replyTo");	
		}
		
		
		if(plang!=null && !(plang.equals("")))
		{
			status= modyfyRepAttr(ctx, entry, "preferredLanguage", plang);
		}
		else
		{
			status= modyfyRemAttr(ctx, entry, "preferredLanguage");	
		}
		
		if(anni!=null && !(anni.equals("")))
		{
			anni=anni.replace("-", "");
			status= modyfyRepAttr(ctx, entry, "anniversary", anni);
		}
		else
		{
			status= modyfyRemAttr(ctx, entry, "anniversary");	
		}
		
		
		
		if(dob!=null && !(dob.equals("")))
		{
			dob=dob.replace("-", "");
			status= modyfyRepAttr(ctx, entry, "dateOfBirth", dob);
		}
		else
		{
			status= modyfyRemAttr(ctx, entry, "dateOfBirth");	
		}
		
		closeConn(ctx);*/
	
	return "true";
	
	
}



@RequestMapping(value = "/saveGeneralSetting", method = RequestMethod.GET)
@ResponseBody
public String saveGeneralSetting(ModelMap map, Principal principal,
		HttpServletRequest request) {
	String data =request.getParameter("data");
	
	HttpSession hs=request.getSession();
	String host=(String)hs.getAttribute("host");
	String id=(String)hs.getAttribute("id");
	String pass=(String)hs.getAttribute("pass");
	String ldapurl=(String)hs.getAttribute("ldapurl");
	String ldapbase=(String)hs.getAttribute("ldapbase");
	boolean status=true;
	
	GetLdapModifyOneAttResponse modattone=webmailClient.getLdapModifyOneAtt(ldapurl, id, pass, ldapbase, "accountSetting", data);
	status= modattone.isGetStatus();
	if(status)
	{
	 JSONParser parser=new JSONParser();
	 try{
         Object obj;
		
			obj = parser.parse(data);
		
         JSONArray array = (JSONArray)obj;
         for(int i=0;i<array.size();i++)
         {
        String jsonval=array.get(i).toString();
        if(jsonval!=null && !jsonval.equals(""))
        {
        String 	arr[]=jsonval.split(",");
        if(arr[0]!=null && arr[1]!=null)
        {
        String arr_nm[]=arr[0].split(":");
        String arr_val[]=arr[1].split(":");
        String arrnm=arr_nm[1].replace("\"", "");
        String arrval=arr_val[1].replace("\"", "");
        arrval=arrval.replace("}", "");
       
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
        // System.out.println("^^^^^^^^^^^^^^^^^^"+array.get(i));
        // System.out.println("^^^^^^^^^^^^^^^^^^"+MailAccSetting.limitMail);
         }

        
      }catch(org.json.simple.parser.ParseException pe){
        //System.out.println(pe);
    	  pe.printStackTrace();
      }
	}
	 
	 
	/* 
	DirContext ctx=getConnection(ldapurl,id,pass,ldapbase);
	String entry="mail="+id+","+ldapbase;
	if(data!=null && !(data.equals("")))
	{
		
		status= modyfyRepAttr(ctx, entry, "accountSetting", data);
	}
	else
	{
		status= modyfyRemAttr(ctx, entry, "accountSetting");	
	}*/
	
	
	return "";
}

@RequestMapping(value = "/changeAccPasswordFirst", method = RequestMethod.GET)
@ResponseBody
public String changeAccPasswordFirst(ModelMap map, Principal principal,
		HttpServletRequest request) {

	String new_pass =request.getParameter("new_pass");
	String con_new_pass =request.getParameter("con_new_pass");
	HttpSession hs=request.getSession();
	String host=(String)hs.getAttribute("host");
	String id=(String)hs.getAttribute("id");
	String pass=(String)hs.getAttribute("pass");
	String ldapurl=(String)hs.getAttribute("ldapurl");
	String ldapbase=(String)hs.getAttribute("ldapbase");
	boolean status=true;
	
	
	if( new_pass==null ||  new_pass.equalsIgnoreCase("") || con_new_pass==null ||  con_new_pass.equalsIgnoreCase(""))
	  {
		return "Please input all fields.";
	  }
	else if(!new_pass.equals(con_new_pass))
	{
		return "Password not matched.";
	}
	else
	{
		try
		{
			new_pass=com.example.Encryption.puttySSHA512(new_pass);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		GetLdapModifyOneAttResponse modattone=webmailClient.getLdapModifyOneAtt(ldapurl, id, pass, ldapbase, "userPassword", new_pass);
		status=modattone.isGetStatus();
		if(status)
		{
			modattone=webmailClient.getLdapModifyOneAtt(ldapurl, id, con_new_pass, ldapbase, "forcePasswordChange", "FALSE");
			//hs.setAttribute("forcePasswordChange", "false");
		}
		
		/*
		DirContext ctx=getConnection(ldapurl,id,pass,ldapbase);
		String entry="mail="+id+","+ldapbase;
		status= modyfyRepAttr(ctx, entry, "userPassword", new_pass);
		ctx=getConnection(ldapurl,id,new_pass,ldapbase);
		status= modyfyRepAttr(ctx, entry, "forcePasswordChange", "TRUE");
		
		closeConn(ctx);*/
	}
	return "Password has been changed. Please login again.";
	
}




	@RequestMapping(value = "/changeAccPassword", method = RequestMethod.GET)
	@ResponseBody
	public String changePassword(ModelMap map, Principal principal,
			HttpServletRequest request) {
		String cur_pass =request.getParameter("cur_pass");
		String new_pass =request.getParameter("new_pass");
		String con_new_pass =request.getParameter("con_new_pass");
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String ldapurl=(String)hs.getAttribute("ldapurl");
		String ldapbase=(String)hs.getAttribute("ldapbase");
		boolean status=true;
		
		if(!cur_pass.equalsIgnoreCase(pass))
		{
			return "Current Password is Wrong.";
		}
		if(cur_pass==null ||  cur_pass.equalsIgnoreCase("") || new_pass==null ||  new_pass.equalsIgnoreCase("") || con_new_pass==null ||  con_new_pass.equalsIgnoreCase(""))
		  {
			return "Please input all fields.";
		  }
		else if(!new_pass.equals(con_new_pass))
		{
			return "Password not matched.";
		}
		else
		{
			try
			{
				new_pass=com.example.Encryption.puttySSHA512(new_pass);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			GetLdapModifyOneAttResponse modattone=webmailClient.getLdapModifyOneAtt(ldapurl, id, pass, ldapbase, "userPassword", new_pass);
			status=modattone.isGetStatus();
			if(status)
			{
				hs.setAttribute("pass", con_new_pass);
			}
			
			/*
			DirContext ctx=getConnection(ldapurl,id,pass,ldapbase);
			String entry="mail="+id+","+ldapbase;
			status= modyfyRepAttr(ctx, entry, "userPassword", new_pass);
			if(status)
			{
				hs.setAttribute("pass", new_pass);
			}
			closeConn(ctx);*/
		}
		return "Password has been changed.";
		
	}
	
	
	@RequestMapping(value = "/createNewFolder", method = RequestMethod.GET)
	@ResponseBody
	public String createNewFolder(ModelMap map, Principal principal,
			HttpServletRequest request) {
		String path =request.getParameter("path");
		String name =request.getParameter("name");
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		boolean isCreated = true;  
		String newfldrnm=path+"."+name;
        if(path.equalsIgnoreCase("-"))
        {
        	newfldrnm=name; 
        }
        
        if(name.length()>100)
        {
        	return "Folder name is too long";
        }
        
        
        
        GetWebmailCreateFolderResponse cresub=webmailClient.getWebmailCreateFolder(host, id, pass, newfldrnm);
        isCreated=cresub.isGetcflderStatus();
        
		/*if( !newfldrnm.equalsIgnoreCase("Inbox") && !newfldrnm.equalsIgnoreCase("Drafts") && !newfldrnm.equalsIgnoreCase("Sent") && !newfldrnm.equalsIgnoreCase("Junk") && !newfldrnm.equalsIgnoreCase("Trash") && !newfldrnm.equalsIgnoreCase("Archive"))
		{
			Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        Store store=null;
            try {
                Session ses = Session.getDefaultInstance(props, null);
               store = ses.getStore("imaps");
                store.connect(host,id, pass);
                javax.mail.Folder parent = store.getDefaultFolder(); 
                
                javax.mail.Folder newFolder = parent.getFolder(newfldrnm); 
                isCreated = newFolder.create(javax.mail.Folder.HOLDS_MESSAGES);   
                newFolder.setSubscribed(true);
        
                store.close();
    } catch (Exception e)   
    {   
    	
        e.printStackTrace();   
        isCreated = false;   
    } 
            finally
            {
            	try {
					store.close();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
		}
		else
		{
			  isCreated = false; 	
		}*/
		return ""+isCreated;
		
	}
	
	
	
	
	@RequestMapping(value = "/deleteFolder", method = RequestMethod.GET)
	@ResponseBody
	public String deleteFolder(ModelMap map, Principal principal,
			HttpServletRequest request) {
		String fldnm =request.getParameter("fldnm");
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		boolean isDeleted = true; 
		
		GetWebmailDeleteFolderResponse delres=webmailClient.getWebmailDeleteFolder(host, id, pass, fldnm);
		isDeleted=delres.isGetdflderStatus();
		/*if( !fldnm.equalsIgnoreCase("Inbox") && !fldnm.equalsIgnoreCase("Drafts") && !fldnm.equalsIgnoreCase("Sent") && !fldnm.equalsIgnoreCase("Junk") && !fldnm.equalsIgnoreCase("Trash") && !fldnm.equalsIgnoreCase("Archive")) 
				{
		Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        Store store=null;
            try {
                Session ses = Session.getDefaultInstance(props, null);
                store = ses.getStore("imaps");
                store.connect(host,id, pass);
                javax.mail.Folder parent = store.getDefaultFolder(); 
                javax.mail.Folder newFolder = parent.getFolder(fldnm); 
                if(newFolder.isSubscribed())
                {
                newFolder.setSubscribed(false);
                }
                isDeleted = newFolder.delete(true);
               
                store.close();
    } catch (Exception e)   
    {   
      
        e.printStackTrace();   
        isDeleted = false;   
    }  
            finally
            {
            	try {
					store.close();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
				}
		else
		{
			isDeleted = false; 
		}*/
		return ""+isDeleted;
		
	}
	
	
	@RequestMapping(value = "/renameFolder", method = RequestMethod.GET)
	@ResponseBody
	public String renameFolder(ModelMap map, Principal principal,
			HttpServletRequest request) {
		
			String oldnm =request.getParameter("oldnm");
			String newnm =request.getParameter("newnm");
			HttpSession hs=request.getSession();
			String host=(String)hs.getAttribute("host");
			String id=(String)hs.getAttribute("id");
			String pass=(String)hs.getAttribute("pass");
			boolean stat = true; 
	        Store store=null;
	            try {
	            	store=Connections.imapConnectionSmallNP(host, id, pass);
		
		IMAPFolder folder = (IMAPFolder) store.getFolder(oldnm);
        
		boolean subst = true; 
        if ( folder.exists()) {
        	subst=folder.isSubscribed();
        	if(subst)
        	{
        		folder.setSubscribed(false);
        	}
            if ( folder.isOpen()) folder.close(false);
            javax.mail.Folder newFolder =  store.getFolder(newnm);
           
            stat =  folder.renameTo(newFolder);
            
            
            if(subst)
        	{
            	newFolder.setSubscribed(true);
        	}
            
            
       }
            store.close();
	               
	            }
	            catch (Exception e)   
	            {   
	              
	                e.printStackTrace();   
	                stat = false;   
	            }  
	                    finally
	                    {
	                    	try {
	        					store.close();
	        				} catch (MessagingException e) {
	        					// TODO Auto-generated catch block
	        					e.printStackTrace();
	        				}
	                    }
	        		
		return ""+stat;
		
	}
	
	

	@RequestMapping(value = "/folderSubscription", method = RequestMethod.GET)
	@ResponseBody
	public String folderSubscription(ModelMap map, Principal principal,
			HttpServletRequest request) {
		String fldnm =request.getParameter("fldnm");
		String stat =request.getParameter("stat");
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		boolean isSub = true; 
		boolean sub=true;
		if(stat!=null && !stat.equals("") && stat.equalsIgnoreCase("false"))
		{
			sub=false;
		}
		GetWebmailSubscriptionFolderResponse subres=webmailClient.getWebmailSubscriptionFolder(host, id, pass, fldnm, sub);
		isSub=subres.isGetsflderStatus();
		/*if(stat!=null && !stat.equals("") && !fldnm.equalsIgnoreCase("Inbox") && !fldnm.equalsIgnoreCase("Drafts") && !fldnm.equalsIgnoreCase("Sent") && !fldnm.equalsIgnoreCase("Junk") && !fldnm.equalsIgnoreCase("Trash") && !fldnm.equalsIgnoreCase("Archive") )
		{
			if(stat.equalsIgnoreCase("false"))
			{
				sub=false;
			}
		Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        Store store=null;
            try {
                Session ses = Session.getDefaultInstance(props, null);
                store = ses.getStore("imaps");
                store.connect(host,id, pass);
                javax.mail.Folder parent = store.getDefaultFolder(); 
                javax.mail.Folder newFolder = parent.getFolder(fldnm); 
                newFolder.setSubscribed(sub);
               
                store.close();
    } catch (Exception e)   
    {   
      
        e.printStackTrace();   
        isSub = false;   
    }  
            finally
            {
            	try {
					store.close();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
		}
		else
		{
			 isSub = false;
		}
*/
		return ""+isSub;
		
	}
	
}
