package webmail.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import webmail.webservice.client.FolderClient;
import webmail.wsdl.GetMailInboxDescResponse;
import webmail.wsdl.GetMailInboxResponse;
import webmail.wsdl.GetWebmailFolderResponse;
import webmail.wsdl.GetWebmailSubFolderResponse;
import webmail.wsdl.GetWebmailUnreadMailCountResponse;
import webmail.wsdl.Inbox;
import webmail.wsdl.InboxDesc;
import webmail.wsdl.InboxListReturn;

import java.util.List;

@Controller
public class InboxController {

	@Autowired
	private FolderClient folderClient;
	
	
	
	
	@RequestMapping(value = "/getMailInbox", method = RequestMethod.GET)
	@ResponseBody
	public String listWebmailInbox(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		String res=" <script src='js/event.js' type='text/javascript' language='javascript' ></script> ";
		String start= request.getParameter("start");
		String end= request.getParameter("end");
		String folder= request.getParameter("folder");
		String pview=request.getParameter("pview");
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String port=(String)hs.getAttribute("port");
		hs.setAttribute("active_folder", folder);
		//System.out.println("inbox***********************folder="+folder);
		GetMailInboxResponse wfre=folderClient.getInboxMailRequest(host, port, id, pass, start, end, folder);
		List<Inbox>  inblist=wfre.getGetInboxByMailLimit().getInboxListReturn().getInboxMailList();
		
		for(Inbox inb : inblist)
		{
			String uid="";
			String fromid="";
			String sub="";
			String attch="";
			String date="";
			String dt_title="";
			String seen_class=""; 
			String ckboxnm="";
			if(inb.isMailSeen()== false)
			{
				seen_class="unread_message";
				ckboxnm="unseen";
			}
			else
			{
				ckboxnm="seen";
			}
			
			String flag_class="small_image";
			String flg_img="images/star_gray.png";
			if(inb.isMailFlage()== true)
			{
				flag_class="small_image_flag";
				flg_img="images/star-flag.png";
				ckboxnm=ckboxnm+"-stared";
			}
			else
			{
				ckboxnm=ckboxnm+"-unstared";
			}
			
			String css_class1="row_content";
			String css_class2="row_first";
			String css_class3="row_left";
			if(pview.equalsIgnoreCase("leftview"))
			{
				css_class1="row_content left_view_mess";
				css_class2="row_first mail_de_op";
				css_class3="row_left left_view_con";
			}
			
			
			uid=inb.getUid();
			fromid=inb.getFromId();
			sub=inb.getSubject();
			attch=inb.getAttachment();
			date=inb.getSendDtae();
			dt_title=inb.getSendDtaeTitle();
			String mailcnt=inb.getMailContent();
			mailcnt = mailcnt.replaceAll("\\<.*?\\>", "");
			res+="<div id='div_"+uid+"' class='"+css_class1+"' style='cursor: pointer;'    ><div class='"+css_class2+"'><div id='div_unread_"+uid+"' class='"+css_class3+" "+seen_class+" '>";
			res+="<div class='flag_first'><div class='row_check_box'>";
			res+=" <input type='checkbox' id='chk_id_"+uid+"' name='"+ckboxnm+"' value='"+uid+"' onchange='selMailCountInPanel()'   class='mail_checked'>";
			res+=" </div> <a style='cursor: pointer;'><div id='div_flag_"+uid+"' class='"+flag_class+"' onclick='flagAction("+uid+")'></div> </a>";
			res+=" </div>";
			res+=" <div onclick='getMailCnt("+uid+")' class='form_first'> "+fromid+" </div>";
			res+="  <div onclick='getMailCnt("+uid+")' class='subject_first'><div class='forword_icon'></div>";
			res+=   sub;
			res+=" </div></div>";
			res+="<div onclick='getMailCnt("+uid+")' class='right_row_first'>";
			res+="<div class='row_date' style='cursor: pointer;' title='"+dt_title+"'>"+date+"</div>";
			res+="<div class='row_attach'>";
			if(attch.equalsIgnoreCase("yes"))
			{
				res+="	<img src='images/attachment.png'>";
			}
				
			res+="	</div> <a style='cursor: pointer;' onclick='moveTrash("+uid+")'><div class='row_delete'></div></a>";
			res+="   </div> </div>";
			res+="   <div onclick='getMailCnt("+uid+")' class='message'> "+mailcnt+" </div><div class='new_flag'><img src='"+flg_img+"' /></div>";
			res+=" <div class='clear'></div>  </div>";
		}
		//System.out.println("return inbox**********************="+res);
		
		return res;
		
	}
	
	
	
	
	@RequestMapping(value = "/getMailInboxDesc", method = RequestMethod.GET)
	@ResponseBody
	public String listWebmailInboxDesc(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		String res=" <script src='js/event.js' type='text/javascript' language='javascript' ></script> ";
		String start= request.getParameter("start");
		String end= request.getParameter("end");
		String folder= request.getParameter("folder");
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String port=(String)hs.getAttribute("port");
		hs.setAttribute("active_folder", folder);
		System.out.println("inbox***********************folder="+folder);
		GetMailInboxDescResponse wfre=folderClient.getInboxMailRequestDesc(host, port, id, pass, start, end, folder);
		List<InboxDesc>  inblist=wfre.getGetInboxByMailLimitDesc().getInboxListDescReturn().getInboxMailListDesc();
		
		for(InboxDesc inb : inblist)
		{
			String uid="";
			String fromid="";
			String sub="";
			String attch="";
			String date="";
			String dt_title="";
			String seen_class="";
			if(inb.isMailSeenDesc()== false)
			{
				seen_class="unread_message";
			}
			
			String flag_class="small_image";
			if(inb.isMailFlageDesc()== true)
			{
				flag_class="small_image_flag";
			}
			
			uid=inb.getUidDesc();
			fromid=inb.getFromIdDesc();
			sub=inb.getSubjectDesc();
			attch=inb.getAttachmentDesc();
			date=inb.getSendDtaeDesc();
			dt_title=inb.getSendDtaeTitleDesc();
			String mailcnt=inb.getMailContentDesc();
			mailcnt = mailcnt.replaceAll("\\<.*?\\>", "");
			res+="<div id='div_"+uid+"' class='row_content' ><div class='row_first'><div class='row_left "+seen_class+" '>";
			res+="<div class='flag_first'><div class='row_check_box'>";
			res+=" <input type='checkbox' class='mail_checked'>";
			res+=" </div> <a href='#'><div id='div_flag_"+uid+"' class='"+flag_class+"' onclick='falgAction("+uid+")'></div> </a>";
			res+=" </div>";
			res+=" <div class='form_first'> "+fromid+" </div>";
			res+="  <div class='subject_first'><div class='forword_icon'></div>";
			res+=   sub;
			res+=" </div></div>";
			res+="<div class='right_row_first'>";
			res+="<div class='row_date' style='cursor: pointer;' title='"+dt_title+"'>"+date+"</div>";
			res+="<div class='row_attach'>";
			if(attch.equalsIgnoreCase("yes"))
			{
				res+="	<img src='images/attachment.png'>";
			}
				
			res+="	</div> <a style='cursor: pointer;' onclick='moveTrash("+uid+")'><div class='row_delete'></div></a>";
			res+="   </div> </div>";
			res+="   <div class='message'> "+mailcnt+" </div>";
			res+=" <div class='clear'></div>  </div>";
		}
		System.out.println("return inbox**********************="+res);
		
		return res;
		
	}
	
}
