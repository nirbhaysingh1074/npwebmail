package webmail.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import webmail.webservice.client.FolderClient;
import webmail.wsdl.Attachment;
import webmail.wsdl.GetMailDisplayResponse;
import webmail.wsdl.Inbox;
import webmail.wsdl.InboxDisplay;

@Controller
public class DisplayMailContentController {

	@Autowired
	FolderClient folderClient;

	
	@RequestMapping(value = "/getMailContentByUid", method = RequestMethod.GET)
	@ResponseBody
	public String listWebmailInbox(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		String res="";
		
		String uid= request.getParameter("uid");
		String folder= request.getParameter("folder");
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String port=(String)hs.getAttribute("port");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@folder"+folder);
		GetMailDisplayResponse gdres=folderClient.displayMailContentRequest(host, port, id, pass, uid, folder);
		InboxDisplay inbd= gdres.getGetInboxByUid();
		List<Attachment> latt=inbd.getAttachment();
		
		
       
		
		if(latt!=null)
		{
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@list"+latt.size());

		String uids="";
		String fromid="";
		String disp_from="";
		String ovr_from1="";
		String ovr_from2="";
		String sub="";
		String attch="";
		String date="";
		String dt_title="";
		String mailcnt="";
		String to="";
		String disp_to="";
		String disp_to_tit="";
		
		//attch=inbd.getAttachment();
		uids=inbd.getUid();
		sub=inbd.getSubject();
		fromid=inbd.getFromId();
		date=inbd.getSendDtae();
		dt_title=inbd.getSendDtaeTitle();
		mailcnt=inbd.getMailContent();
		to=inbd.getToId();
		String from_img_id="";
		disp_from=fromid;
		disp_from=disp_from.replace("<", "&lt;");
		disp_from=disp_from.replace("<", "&lt;");
		System.out.println("@@@@@@@@@@@@@fromid="+fromid);
		String fromarr[]=fromid.split("<");
		if(fromarr!=null && fromarr.length>1)
		{
			ovr_from1=fromarr[0];
			ovr_from2=fromarr[1].replace(">", "");
			from_img_id=ovr_from2;
		}
		else
		{
			ovr_from1=fromid;
			from_img_id=ovr_from1;
		}
		
		
		String img_myurl=hs.getAttribute("img_myurl").toString();
		String path_img=img_myurl+from_img_id+".jpg";
        String path_img_id=from_img_id+"nomyimage_cnt";
        String path_img_full_id=from_img_id+"nomyimage_cnt_full";
		
		disp_to=to;
		disp_to=disp_to.replace("<", "&lt;");
		disp_to=disp_to.replace("<", "&lt;");
		disp_to=disp_to.replace(",", ",<br>");
		
		String toarr[]=to.split(",");
		for(int i=0;i<toarr.length;i++)
		{
			String tit="";
			
			if(toarr[i].contains(id))
			{
				tit="Me";
			}
			else
			{
			String toarr1[]=toarr[i].split("<");
			if(toarr1!=null && toarr1.length>0)
			{
				String att=toarr1[0].trim();
				String toarr2[]=att.split(" ");
				if(toarr2!=null && toarr2.length>0)
				{
					tit=toarr2[0];
				}
				else
				{
					tit=att;
				}
			}
			else
			{
			tit=toarr[i];
			}
			}
			
			if(disp_to_tit== null || disp_to_tit.equals(""))
			{
				disp_to_tit=tit;
			}
			else
			{
				disp_to_tit=disp_to_tit+", "+tit;
			}
			
		}
		
		System.out.println("title="+sub);
		
		res+="<div class='top_bottom_1'> <h1>"+sub+"</h1>";
		res+="<div class='mail_right_1'> <a href='#'><div class='printer'></div></a> ";
		res+="<a href='#'><div class='fullscreen'></div> </a> </div>";
		res+="<div class='clear'></div> <div class='clear'></div> </div>";
		res+="  <div class='mail_header mail_header_top' style='padding-top: 0px;'>  <div class='mail_left'>  ";
		res+="  <div class='images_hover'>  <img src='"+path_img+"' onerror='getAltImageDisp(this.id)' id='"+path_img_id+"' />   <div class='images_details'> ";
		res+="  <div class='mail_left_1'>  <span>"+ovr_from1+"</span> <div class='clear'></div>"+ovr_from2+"  </div> ";
		res+="<img src='"+path_img+"' onerror='getAltImageDisp(this.id)' id='"+path_img_full_id+"' /> <div class='send_mail_deatils'> <a href='#'>Add to contacts</a> <a href='#'>Emails</a> ";
		res+=" <div class='right_forw'>  <a href='#'> <div class='mail_for'> <img src='images/plus_option.gif'> </div> </a> ";
		res+=" <a href='#'> <div class='mail_for_1'> <img src='images/mail_fow.png'> </div> </a>  </div> </div> </div>  </div> ";
		
		res+="<p class='first_p'> <a href='#'>"+disp_from+"</a></p>";
		res+=" <div class='mail_row'> <p class='last_p new_paragraph'>to "+disp_to_tit+" </p> <a href='#' onClick='mail_to(event);'> <div class='mail_deatil'></div> </a>";
		res+="<div class='to_me' style='display: none;'>";
		res+="<ul> <li class='comm_width'><span>from: </span></li><li> "+disp_from+" </li>";
		res+="<div class='clear'></div> ";
		res+="<li class='comm_width'><span>to:</span></li><li> "+disp_to+"</li>  <div class='clear'></div>";
		res+=" <li class='comm_width'><span>date:</span></li><li> "+dt_title+"</li>  <div class='clear'></div>";
		res+=" <li class='comm_width'><span>subject:</span></li><li>"+sub+"</li><div class='clear'></div>";
		res+="<li class='comm_width'><span>mailed-by:</span></li><li>Silvereye.in</li> <div class='clear'></div>";
		res+=" <li class='comm_width'><span>signed-by:</span></li><li>Silvereye.in</li> <div class='clear'></div>";
		res+="<li class='imag_upload'> <span>Images from this sender are always displayed.</span><a href='#'>Don't display from now on.</a>";
		res+=" <div class='clear'></div> </li>";
		res+=" </ul> </div>  </div> </div>";
		
		res+="<div class='mail_right'><div style='float:left;margin-top: 7px;'>"+date+" </div> <div class='bottom_flag'></div>";
		res+="  <div class='mail_option'> <a title='Reply' href='#'>  <div class='back'> <img src='images/back_one.png'></div>  </a>";
		res+="  <a title='More' href='#' onClick='option_here_1(event);'>   <div class='option'> <img src='images/open_sub_menu.png'></div>  </a> </div>  </div>";
		res+="  <div class='clear'></div> </div>";
		res+=" <div class='mail_content_1 mail_content_p1'><div class='mail_down_option_1'>";
		res+=" <ul><li><a href='#'>Reply</a></li><div class='clear'></div>";
		res+="  <li><a href='#'>Reply to all</a></li><div class='clear'></div>";
		res+="<li><a href='#'>Forward</a></li><div class='clear'></div>";
		res+=" <li><a href='#'>Open chat</a></li>  <div class='clear'></div>";
	    res+=" <li><a href='#'>Filter messages like this</a></li> <div class='clear'></div>";
	    res+="  <li><a href='#'>Print</a></li> <div class='clear'></div>";
	    res+=" <li><a href='#'>Add Techgig to Contacts list</a></li> <div class='clear'></div>";
	    res+=" <li><a href='#'>Delete this message</a></li><div class='clear'></div>";
	    res+=" <li><a href='#'>Report spam</a></li> </ul>";
	    res+=" <div class='clear'></div> </div>";
	    res+="<p>"+mailcnt+"</p>";
	   
	    
	    if(latt!=null && latt.size()>0)
	    {
	    	
	    	res+=" <div class='main_attachment'> <div class='attachment_file attachment_top'> <div class='left_attachment'>";
	    	res+=" <p><span>"+latt.size()+"</span> Attachments</p><div class='clear'></div></div> <div class='left_attachment'></div></div>";
	    	res+="  <div class='attachment_content'>";
	    	res+=" <ul> ";
	    	for(Attachment at : latt)
			{
	    	String nm=at.getAttachmentName();
	    	String sz=at.getAttachmentSize();
	    	if(((Integer.parseInt(sz))/(1024*1024))>0)
	    	{
	    		sz=""+((Integer.parseInt(sz))/(1024*1024))+" MB";
	    	}
	    	else if(((Integer.parseInt(sz))/(1024))>0)
	    	{
	    		sz=""+((Integer.parseInt(sz))/(1024))+" KB";
	    	}
	    	else
	    	{
	    		sz=sz+" bytes";
	    	}
	    	String nam=nm.replace('.', ';');
	    	String arr_ext[]=nam.split(";");
	    	/*System.out.println("$$$$$$$$$$$$$$$$$$$$$="+arr_ext.length);
	    	for(int j=0;j<arr_ext.length;j++)
	    	{
	    		System.out.println("**"+arr_ext[j]);
	    	}
	    	
	    	System.out.println("$$$$$$$$$$$$$$$$$$$$$");*/
	    	String ext=arr_ext[arr_ext.length-1];
	    	ext=ext.trim();
	    	String att_sml_icon="images/att_sml_file.png";
	    	String att_big_icon="images/att_big_file.png";
	    	if(ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("gif"))
	    	{
	    		att_sml_icon="images/att_sml_image.png";
	    		att_big_icon="images/att_big_image.png";
	    	}
	    	else if(ext.equalsIgnoreCase("doc") || ext.equalsIgnoreCase("docx") )
	    	{
	    		att_sml_icon="images/att_sml_word.png";
	    		att_big_icon="images/att_big_word.png";
	    	}
	    	else if(ext.equalsIgnoreCase("ppt") || ext.equalsIgnoreCase("pptx") )
	    	{
	    		att_sml_icon="images/att_sml_ppt.png";
	    		att_big_icon="images/att_big_ppt.png";
	    	}
	    	else if(ext.equalsIgnoreCase("xls") || ext.equalsIgnoreCase("xlsx") || ext.equalsIgnoreCase("csv") )
	    	{
	    		att_sml_icon="images/att_sml_excel.png";
	    		att_big_icon="images/att_big_excel.png";
	    	}
	    	else if(ext.equalsIgnoreCase("pdf")  )
	    	{
	    		att_sml_icon="images/att_sml_pdf.png";
	    		att_big_icon="images/att_big_pdf.png";
	    	}
	    	if(ext.equalsIgnoreCase("zip") || ext.equalsIgnoreCase("7z") || ext.equalsIgnoreCase("rar"))
	    	{
	    		att_sml_icon="images/att_sml_zip.png";
	    		att_big_icon="images/att_big_zip.png";
	    	}
	    	
	    	
	    	res+="<li><div class='attachment_con_box'> <div class='attachment_images'> <img src='"+att_big_icon+"' /> </div>";
	    	res+=" <div class='attach_con_bottom'><img src='"+att_sml_icon+"' /> <span>"+nm+"</span> </div>";
	    	res+=" <div class='attach_mousehover'> <div class='attach_row'><img src='"+att_sml_icon+"' /> <span>"+nm+"</span>";
	    	res+=" <div class='clear'></div> <div class='attachment_size'>"+sz+"</div> </div> <div class='clear'></div>";
	    	res+="  <div class='attachment_option' ><a href='downloadMailAttachByName?uid="+uid+"&name="+nm+"'> <img src='images/download.png'  /> </a></div>   </div> </div> </li>";
	    	}
	    	res+=" </ul> </div> </div>";
	    }
	    
		}
		return res;
	}
	
	
	
	

	@RequestMapping(value = "/getMailContentFullPageByUid", method = RequestMethod.GET)
	@ResponseBody
	public String listWebmailInboxfullpage(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		String res="";
		
		
		String uid= request.getParameter("uid");
		String folder= request.getParameter("folder");
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String port=(String)hs.getAttribute("port");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@folder"+folder);
		GetMailDisplayResponse gdres=folderClient.displayMailContentRequest(host, port, id, pass, uid, folder);
		InboxDisplay inbd= gdres.getGetInboxByUid();
		List<Attachment> latt=inbd.getAttachment();
		
		
       
		
		if(latt!=null)
		{
		
		String uids="";
		String fromid="";
		String disp_from="";
		String ovr_from1="";
		String ovr_from2="";
		String sub="";
		String attch="";
		String date="";
		String dt_title="";
		String mailcnt="";
		String to="";
		String disp_to="";
		String disp_to_tit="";
		
		//attch=inbd.getAttachment();
		uids=inbd.getUid();
		sub=inbd.getSubject();
		fromid=inbd.getFromId();
		date=inbd.getSendDtae();
		dt_title=inbd.getSendDtaeTitle();
		mailcnt=inbd.getMailContent();
		System.out.println("^^^^^^^^^^^^^^^="+mailcnt);
		to=inbd.getToId();
		
		disp_from=fromid;
		disp_from=disp_from.replace("<", "&lt;");
		disp_from=disp_from.replace("<", "&lt;");
		String from_img_id="";
		String fromarr[]=fromid.split("<");
		if(fromarr!=null && fromarr.length>1)
		{
			ovr_from1=fromarr[0];
			ovr_from2=fromarr[1].replace(">", "");
			from_img_id=ovr_from2;
		}
		else
		{
			ovr_from1=fromid;
			from_img_id=ovr_from1;
		}
		
		
		String img_myurl=hs.getAttribute("img_myurl").toString();
		String path_img=img_myurl+from_img_id+".jpg";
        String path_img_id=from_img_id+"nomyimage_cnt";
        String path_img_full_id=from_img_id+"nomyimage_cnt_full";
		
		disp_to=to;
		disp_to=disp_to.replace("<", "&lt;");
		disp_to=disp_to.replace("<", "&lt;");
		disp_to=disp_to.replace(",", ",<br>");
		
		String toarr[]=to.split(",");
		for(int i=0;i<toarr.length;i++)
		{
			String tit="";
			
			if(toarr[i].contains(id))
			{
				tit="Me";
			}
			else
			{
			String toarr1[]=toarr[i].split("<");
			if(toarr1!=null && toarr1.length>0)
			{
				String att=toarr1[0].trim();
				String toarr2[]=att.split(" ");
				if(toarr2!=null && toarr2.length>0)
				{
					tit=toarr2[0];
				}
				else
				{
					tit=att;
				}
			}
			else
			{
			tit=toarr[i];
			}
			}
			
			if(disp_to_tit== null || disp_to_tit.equals(""))
			{
				disp_to_tit=tit;
			}
			else
			{
				disp_to_tit=disp_to_tit+", "+tit;
			}
			
		}
		
		//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@uid"+uids);
		
		res+="<div class='right_top_pannel'><div class='for_tool'>";
		res+="<ul><li><div class='tool_inner_box'><ul id='menu'><li class='menu_space'><a onclick='backToMailList()'>";
		res+="<img src='images/back_option.png'></a></li></ul></div></li>";
		res+="<li><div class='large_tool search_form_tool' style='display: block;'>";
		res+="<ul><li class='hidden_option'><a href='#'><img src='images/back_one.png'></a></li>";
        res+="<div class='right_border'></div> <li class='hidden_option'><a href='#'><img src='images/back_doble.png'></a></li>";
        res+="<div class='right_border'></div> <ul id='menu' class='next_mail'>";
        res+="<li> <a href='#' style='padding:0px;'><img src='images/next.png'></a> <a href='#' class='sub_menu_link' style='padding:0px;'><img src='images/open_sub_menu.png'></a>";
        res+="<ul><li> <a href='#'>Forward Inline</a></li><li><a href='#'>Forward As Attachment</a></li></ul> </li></ul>";
        res+="<div class='right_border'></div> <li><a href='#'><img src='images/tool_delete.png'></a></li>";
        res+="<div class='right_border'></div> <li><a href='#'><img src='images/restriction.png'></a></li> <div class='right_border'></div>";
        res+="<ul id='menu' class='next_mail more_arrow'> <li class='more'> More </li>";
        res+="<li><a href='#' class='sub_menu_link'><img src='images/open_sub_menu.png'></a> <ul style='margin-left: -27px !important;'>";
        res+=" <li> <a href='#'>Mark as unread</a></li> <li><a href='#'>Add Star</a></li></ul>";
        res+="</li></ul></ul></div></li></ul><div class='right_tool_part'><div class='right_tool'> <a href='#'> <img src='images/reload.png'> </a> </div>";
        res+="<div class='right_tool_1'><ul id='menu'> <li> <img src='images/setting_toll.png' class='four_margin'></li>";
        res+="<li class='right_menu_1'><a href='#'> <img src='images/open_sub_menu.png' style='margin-left: 8px !important;'></a>";
        res+="</div></div> </div></div>";
        
        
        
        res+="<div id='widget'  > <div class='full_width_mail'><div class='mail_content'><div class='mail_content_1 full_view_content' style='width: 48.5%; float: right; display: block; min-height: 75%; max-height: 95%;'> ";
        res+="<div class='mail_left_content'><div class='top_bottom_1'><h1>"+sub+" </h1> ";
        res+="<div class='mail_right_1'> <a href='#'><div class='printer'></div></a> <a href='#'><div class='fullscreen'></div> </a>";
        res+="</div><div class='clear'></div><div class='clear'></div> </div>";
        res+="<div class='mail_header mail_header_top' style='padding-top: 0px;'> <div class='mail_left'>"; 
        res+="<div class='images_hover'><img src='"+path_img+"' onerror='getAltImageDisp(this.id)' id='"+path_img_id+"' /><div class='images_details'><div class='mail_left_1'>";
        res+="<span>"+ovr_from1+"</span><div class='clear'></div>"+ovr_from2+"</div>";
        res+="<img src='"+path_img+"' onerror='getAltImageDisp(this.id)' id='"+path_img_full_id+"' /><div class='send_mail_deatils'><a href='#'>Add to contacts</a><a href='#'>Emails</a>";
        res+="<div class='right_forw'><a href='#'><div class='mail_for'><img src='images/plus_option.gif'></div></a><a href='#'>";
        res+="<div class='mail_for_1'><img src='images/mail_fow.png'></div></a></div></div></div> </div>";
              
		
        res+="<p class='first_p'><a href='#'>"+disp_from+"</a></p>";
        res+="<div class='mail_row'><p class='last_p new_paragraph'>to "+disp_to_tit+" </p><a href='#' onClick='mail_to(event);'> <div class='mail_deatil'></div> </a>";
        res+="<div class='to_me' style='display: none;'><ul><li class='comm_width'><span>from: </span></li><li> "+disp_from+"</li>";
       res+="<div class='clear'></div><li class='comm_width'><span>to:</span></li><li>"+disp_to+"</li>";
        res+="<div class='clear'></div><li class='comm_width'><span>date:</span></li><li> "+dt_title+"</li>";
        res+="<div class='clear'></div><li class='comm_width'><span>subject:</span></li><li>"+sub+"</li>";
        res+="<div class='clear'></div><li class='comm_width'><span>mailed-by:</span></li><li>Silvereye.in</li>";
        res+=" <div class='clear'></div><li class='comm_width'><span>signed-by:</span></li><li>Silvereye.in</li>";
        res+="<div class='clear'></div><li class='imag_upload'><span>Images from this sender are always displayed.</span><a href='#'>Don't display from now on.</a>";
        res+="<div class='clear'></div></li></ul></div> </div></div>";
		
        
        res+="<div class='mail_right'><div style='float:left;margin-top: 7px;'>"+date+" </div>";
        res+="<div class='bottom_flag'></div><div class='mail_option'><a title='Reply' href='#'><div class='back'> <img src='images/back_one.png'></div></a>";
        res+="<a title='More' href='#' onClick='option_here_1(event);'> <div class='option'> <img src='images/open_sub_menu.png'></div></a>";
        res+="</div></div><div class='clear'></div></div>";
        res+="<div class='mail_content_1 mail_content_p'> <div class='mail_down_option_1'><ul>";
        res+="<li><a href='#'>Reply</a></li><div class='clear'></div>";
        res+="<li><a href='#'>Reply to all</a></li><div class='clear'></div>";
        res+="<li><a href='#'>Forward</a></li><div class='clear'></div>";
        res+="<li><a href='#'>Open chat</a></li><div class='clear'></div>";
        res+="<li><a href='#'>Filter messages like this</a></li><div class='clear'></div>";
        res+="<li><a href='#'>Print</a></li><div class='clear'></div>";
        res+="<li><a href='#'>Add Techgig to Contacts list</a></li>";
        res+=" <div class='clear'></div><li><a href='#'>Delete this message</a></li>";
        res+="<div class='clear'></div> <li><a href='#'>Report spam</a></li></ul>";
        res+="<div class='clear'></div></div>";
        res+="<p>"+mailcnt+"</p>";
        
        
        
        
        if(latt!=null && latt.size()>0)
	    {
	    	
        
        res+="<div class='main_attachment'><div class='attachment_file attachment_top'><div class='left_attachment'>";
        res+="<p><span>"+latt.size()+"</span> Attachments</p><div class='clear'></div> </div> <div class='left_attachment'></div></div>";
        res+="<div class='attachment_content'> <ul>";
        
        for(Attachment at : latt)
		{
    	String nm=at.getAttachmentName();
    	String sz=at.getAttachmentSize();
    	if(((Integer.parseInt(sz))/(1024*1024))>0)
    	{
    		sz=""+((Integer.parseInt(sz))/(1024*1024))+" MB";
    	}
    	else if(((Integer.parseInt(sz))/(1024))>0)
    	{
    		sz=""+((Integer.parseInt(sz))/(1024))+" KB";
    	}
    	else
    	{
    		sz=sz+" bytes";
    	}
    	String nam=nm.replace('.', ';');
    	String arr_ext[]=nam.split(";");
    	/*System.out.println("$$$$$$$$$$$$$$$$$$$$$="+arr_ext.length);
    	for(int j=0;j<arr_ext.length;j++)
    	{
    		System.out.println("**"+arr_ext[j]);
    	}
    	
    	System.out.println("$$$$$$$$$$$$$$$$$$$$$");*/
    	String ext=arr_ext[arr_ext.length-1];
    	ext=ext.trim();
    	String att_sml_icon="images/att_sml_file.png";
    	String att_big_icon="images/att_big_file.png";
    	if(ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("gif"))
    	{
    		att_sml_icon="images/att_sml_image.png";
    		att_big_icon="images/att_big_image.png";
    	}
    	else if(ext.equalsIgnoreCase("doc") || ext.equalsIgnoreCase("docx") )
    	{
    		att_sml_icon="images/att_sml_word.png";
    		att_big_icon="images/att_big_word.png";
    	}
    	else if(ext.equalsIgnoreCase("ppt") || ext.equalsIgnoreCase("pptx") )
    	{
    		att_sml_icon="images/att_sml_ppt.png";
    		att_big_icon="images/att_big_ppt.png";
    	}
    	else if(ext.equalsIgnoreCase("xls") || ext.equalsIgnoreCase("xlsx") || ext.equalsIgnoreCase("csv") )
    	{
    		att_sml_icon="images/att_sml_excel.png";
    		att_big_icon="images/att_big_excel.png";
    	}
    	else if(ext.equalsIgnoreCase("pdf")  )
    	{
    		att_sml_icon="images/att_sml_pdf.png";
    		att_big_icon="images/att_big_pdf.png";
    	}
    	if(ext.equalsIgnoreCase("zip") || ext.equalsIgnoreCase("7z") || ext.equalsIgnoreCase("rar"))
    	{
    		att_sml_icon="images/att_sml_zip.png";
    		att_big_icon="images/att_big_zip.png";
    	}
        
        res+="<li><div class='attachment_con_box'><div class='attachment_images'> <img src='"+att_big_icon+"' /></div>";
        res+="<div class='attach_con_bottom'><img src='"+att_sml_icon+"' /> <span>"+nm+"</span> </div>";
        res+="<div class='attach_mousehover'> <div class='attach_row'><img src='"+att_sml_icon+"' /> <span>"+nm+"</span>";
        res+="<div class='clear'></div><div class='attachment_size'>"+sz+"</div> </div>";
        res+="<div class='clear'></div><div class='attachment_option'><a href='downloadMailAttachByName?uid="+uid+"&name="+nm+"'> <img src='images/download.png'  /> </a> </div> </div></div> </li>";
       
		}
        
        res+="</ul></div></div>";
        
	    }
		}
		return res;
	}
}
