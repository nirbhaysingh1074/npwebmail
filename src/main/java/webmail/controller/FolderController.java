package webmail.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

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
import webmail.wsdl.GetWebmailFolderResponse;
import webmail.wsdl.GetWebmailImapquotaResponse;
import webmail.wsdl.GetWebmailQuotaResponse;
import webmail.wsdl.GetWebmailSubFolderResponse;
import webmail.wsdl.GetWebmailUnreadMailCountResponse;
import webmail.wsdl.Imapquota;


@Controller
public class FolderController {

	@Autowired
	private FolderClient folderClient;
	
	
	
	@RequestMapping(value = "/getWbmailfolder", method = RequestMethod.GET)
	@ResponseBody
	public String listWebmailFolder(ModelMap map, Principal principal,
			HttpServletRequest request) {
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String port=(String)hs.getAttribute("port");
		String inboxcnt="";
		GetWebmailUnreadMailCountResponse wfre=folderClient.getWebmailUnreadMailCountRequest(host, port, id, pass,"inbox");
		int mcnt  =wfre.getUnreademailcount();
		if(mcnt > 0)
		{
			inboxcnt="("+mcnt+")";
		}
		
		GetWebmailFolderResponse wfresponse=folderClient.getWebmailFolderRequest(host, id, pass);
		String res="<ul class='left_margin' style='display: block;'>";
		String myfdr=wfresponse.getGetWebmailFolder();
		System.out.println("************************* webmail folder="+myfdr);
		String arr[]=myfdr.split(";");
		System.out.println("************************* webmail folder lenth="+arr.length);
		for(int i=0; i<arr.length; i++)
		{
			String prm="";
			if(arr[i].equalsIgnoreCase("Inbox"))
			{
				prm="inbox";
				res+="<li><a id='"+prm+"' style='cursor: pointer;' onclick='getWebmailInbox(this.id)' >Inbox &nbsp;<span id='unread_inbox'>"+inboxcnt+" </span></a></li>";
			}
			else if(arr[i].equalsIgnoreCase("Drafts") || arr[i].equalsIgnoreCase("Sent") || arr[i].equalsIgnoreCase("Junk") || arr[i].equalsIgnoreCase("Trash") || arr[i].equalsIgnoreCase("Archive")  )
			{
				prm=arr[i];
				res+="<li><a style='cursor: pointer;' id='"+prm+"' onclick='getWebmailInbox(this.id)'>"+arr[i]+"</a></li>";
			}
			else
			{
				prm=arr[i];
				int flg=Integer.parseInt(arr[i+1]);
				if(flg>0)
				{
					res+="<li class='bottom dcjq-parent-li'>";
					res+="<a style='cursor: pointer;'  id='"+prm+"'  onclick='getWebmailInbox(this.id)' class='dcjq-parent active' style='padding-left: 9px;'>"+arr[i]+"<span class='dcjq-icon'></span></a>";
					res+=" <ul style='display: block;' class='subfolder_onhover'>";
					GetWebmailSubFolderResponse wsfr=folderClient.getWebmailSubFolderRequest(host, id, pass, arr[i]);
					String mysubfdr=wsfr.getGetWebmailSubFolder();
					System.out.println("************************* webmail sub folder="+mysubfdr);
					String subarr[]=mysubfdr.split(";");
					String spath1=arr[i];
					for(int j=0;j < subarr.length;j++)
					{
						prm=arr[i]+"."+subarr[j];
						int flgsub=Integer.parseInt(subarr[j+1]);
						if(flgsub>0)
						{
							
							res+="<li class='dcjq-parent-li'><a style='cursor: pointer;' id='"+prm+"'  onclick='getWebmailInbox(this.id)' class='dcjq-parent active sub_folder_inner'>"+subarr[j]+"<span class='dcjq-icon'></span></a>";
							res+="<ul class='left_margin' style='display: block;'>";
							GetWebmailSubFolderResponse wsfr1=folderClient.getWebmailSubFolderRequest(host, id, pass, spath1+"."+subarr[j]);
							String mysubfdr1=wsfr1.getGetWebmailSubFolder();
							System.out.println("************************* webmail sub folder="+mysubfdr1);
							String subarr1[]=mysubfdr1.split(";");
							String spath2=subarr[j];
							for(int k=0;k < subarr1.length;k++)
							{
								prm=arr[i]+"."+subarr[j]+"."+subarr1[k];
								res+="<li><a style='cursor: pointer;' id='"+prm+"'  onclick='getWebmailInbox(this.id)'>"+subarr1[k]+"</a></li>";
								k++;
							}
							res+="</ul></li>";
						}
						else
						{
						res+="<li><a style='cursor: pointer;' id='"+prm+"'  onclick='getWebmailInbox(this.id)'>"+subarr[j]+"</a></li>";
						}
						j++;
					}
					res+="</ul></li>";
				}
				else
				{
					res+="<li><a style='cursor: pointer;' id='"+prm+"'  onclick='getWebmailInbox(this.id)'>"+arr[i]+"</a></li>";
				}
			}
			i++;
			
		}
		
		res+="</ul>";
		
		return res;
		
	}
	
	@RequestMapping(value = "/getWbmailsubfolder", method = RequestMethod.GET)
	@ResponseBody
	public String listWebmailQuota(ModelMap map, Principal principal,
			HttpServletRequest request, @RequestParam String path) {
		GetWebmailUnreadMailCountResponse wfresponse=folderClient.getWebmailUnreadMailCountRequest("mail.silvereye.in","993", "nirbhay@silvereye.in", "google@2009","inbox");
		int iqt  =wfresponse.getUnreademailcount();
		System.out.println("************************* unread="+iqt);
		return ""+iqt;
		
	}
	

}
