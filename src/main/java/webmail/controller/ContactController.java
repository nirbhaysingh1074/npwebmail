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

import ezvcard.*;
import ezvcard.io.text.VCardReader;
import ezvcard.parameter.*;
import ezvcard.property.*;
import webmail.webservice.client.FolderClient;
import webmail.wsdl.GetMailInboxResponse;
import webmail.wsdl.GetWebmailFolderResponse;
import webmail.wsdl.GetWebmailSubFolderResponse;
import webmail.wsdl.GetWebmailUnreadMailCountResponse;
import webmail.wsdl.Inbox;

import java.io.*;

@Controller
public class ContactController {

	
	@Autowired
	private FolderClient folderClient;
	
	@RequestMapping(value = "/getContactList", method = RequestMethod.GET)
	@ResponseBody
	public String getContactList(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		String res="<table class='con_he_content'>  ";
		//String start= request.getParameter("start");
		///String end= request.getParameter("end");
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String port=(String)hs.getAttribute("port");
		String cfolder=(String)hs.getAttribute("active_contact");
		System.out.println("inbox***********************contact folder="+cfolder);
		
		
		
		
		
		
		
		File folder = new File("D:/vcf/"+cfolder);
		
		
		for ( File fileEntry : folder.listFiles()) {
	        if (!fileEntry.isDirectory()) {
	        	 File file = new File("D:/vcf/"+cfolder+"/"+fileEntry.getName());
	        	 {
	        		 String name="";
	        		 String email="";
	        		 int email_cnt=0;
	        		 String phone="";
	        		 int phone_cnt=0;
	        		 String dept="Dept";
	        		 String addr="";
	        		 int addr_cnt=0;
	        		try
	        		{
	        		 VCardReader vcardReader = new VCardReader(file);
	        		 VCard vcard = Ezvcard.parse(file).first();
	        				FormattedName fn=vcard.getFormattedName();
	        				name=fn.getValue(); 
	        			 List<Email> elst=	vcard.getEmails();
	        			
	        			 for(Email em: elst)
	        			 {
	        				
	        				 if(email_cnt==0)
	        				 {
	        					 email =em.getValue();
	        				 }
	        				 email_cnt++;
	        				
	        				 System.out.println("**************email type="+em.getTypes()+": email="+em.getValue());
	        			 }
	        			 
	        			 List<Telephone> moblst=	vcard.getTelephoneNumbers();
	        			 for(Telephone tel: moblst)
	        			 {
	        				 if(phone_cnt==0)
	        				 {
	        					 phone=tel.getText(); 
	        				 }
	        				 phone_cnt++;
	        			 }
	        			 
	        			 
	        			 List<Address> addre= vcard.getAddresses();
	        			 for(Address ad: addre)
	        			 {
	        				 if(addr_cnt==0)
	        				 {
	        					if(ad.getStreetAddress()!=null && !(ad.getStreetAddress().equals("")))
	        					{
	        						if(addr.equals("") || addr==null)
	        						{
	        							addr=ad.getStreetAddress();
	        						}
	        						else
	        						{
	        							addr=addr+", "+ad.getStreetAddress();
	        						}
	        					}
	        					if(ad.getLocality()!=null && !(ad.getLocality().equals("")))
	        					{
	        						if(addr.equals("") || addr==null)
	        						{
	        							addr=ad.getLocality();
	        						}
	        						else
	        						{
	        							addr=addr+", "+ad.getLocality();
	        						}
	        					}
	        					if(ad.getRegion()!=null && !(ad.getRegion().equals("")))
	        					{
	        						if(addr.equals("") || addr==null)
	        						{
	        							addr=ad.getRegion();
	        						}
	        						else
	        						{
	        							addr=addr+", "+ad.getRegion();
	        						}
	        					}
	        					if(ad.getCountry()!=null && !(ad.getCountry().equals("")))
	        					{
	        						if(addr.equals("") || addr==null)
	        						{
	        							addr=ad.getCountry();
	        						}
	        						else
	        						{
	        							addr=addr+", "+ad.getCountry();
	        						}
	        					}
	        					if(ad.getPostalCode()!=null && !(ad.getPostalCode().equals("")))
	        					{
	        						if(addr.equals("") || addr==null)
	        						{
	        							addr=ad.getPostalCode();
	        						}
	        						else
	        						{
	        							addr=addr+", "+ad.getPostalCode();
	        						}
	        					}
	        					
	        				 }
	        				 addr_cnt++;
	        				 
	        			
	        				 
	        				 if(email_cnt>1)
	        				 {
	        					 email_cnt--;
	        					 email=email+"(+"+email_cnt+")";
	        				 }
	        				 if(phone_cnt>1)
	        				 {
	        					 System.out.println("*************name="+name+"--- contact="+phone_cnt);
	        					 phone_cnt--;
	        					 phone=phone+"(+"+phone_cnt+")";
	        				 }
	        				 if(addr_cnt>1)
	        				 {
	        					 addr_cnt--;
	        					 addr=addr+"(+"+addr_cnt+")";
	        				 }
	        				 
	        				 
	        				// System.out.println("Address:"+ad.getCountry()+" type:"+ad.getTypes());
	        				// System.out.println("Address:"+ad.getStreetAddress()+" type:"+ad.getTypes());
	        			 }
	        			 
	        			
	        		
	        			// System.out.println(vcard.getFormattedName().getValue());
	        			// System.out.println(vcard.getStructuredName().getFamily());
	        			// System.out.println("--------------------------------");
	        			
	        			vcardReader.close();
	        			 res+=" <tr>";
       				  res+="  <td class='con_box'><input type='checkbox'/></td>";
       				  res+="<td class='con_td_img'><img src='images/blank_man.png' /></td>";
       		          res+="<td class='con_name'><div class='inner_text'>"+name+"</div></td>";
       		          res+="<td class='con_email'><div class='inner_text'>"+email+"</div></td>";
       		          res+=" <td class='con_email'><div class='inner_text'>"+phone+"</div></td>";
       		          res+="<td class='con_department'><div class='inner_text'>"+dept+"</div></td>";
       		          res+=" <td class='con_address'><div class='inner_text address_div'>"+addr+"</div></td>";
       		          res+="  </tr>";
       				 
	        		}
	        		catch(Exception e)
	        		{
	        			System.out.println(e);
	        		}
	        		 
	        	 }
	        }
		}
	
		
		
		
		res+="  </table>";
		
		
		
		return res;
		
	}
	
	
	
	
	
	@RequestMapping(value = "/getWbmailContactFolder", method = RequestMethod.GET)
	@ResponseBody
	public String getWbmailContactFolder(ModelMap map, Principal principal,
			HttpServletRequest request) {
		/*HttpSession hs=request.getSession();
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
		}*/
	
		String res="<ul>";
		
		res+=" <li id='Personal Contacts' ><img src='images/blank_man.png' class='icon_con' />";
		res+=" <span>Personal Contacts</span> <div class='clear'></div></li>";
         res+=" <li id='Collected Contacts' >    <img src='images/colt_con.png' class='collect_con col_con' />";
         res+=" <span>Collected Contacts</span>   <div class='clear'></div>    </li>";
         res+=" <li id='Directory'> <img src='images/contact_dir.png' class='icon_con col_con' />";
         res+="<span>Directory</span>    <div class='clear'></div>  </li>";
		
		
		
		
		
		File folder = new File("D:/vcf");
		for ( File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	// System.out.println(fileEntry.getName());
	        	 if(!(fileEntry.getName().equalsIgnoreCase("Personal Contacts") || fileEntry.getName().equalsIgnoreCase("Collected Contacts") || fileEntry.getName().equalsIgnoreCase("Directory")))
	        	 {
	        		 res+="<li id='"+fileEntry.getName()+"'> <img src='images/group_con.png' class='icon_con col_con group_img'> <span>"+fileEntry.getName()+"</span><div class='clear'></div></li>";
	        	 }
	        	 
	        } 
	    }
		
		
		
		
		
		
		
		
		
		
		res+="</ul>";
		
		return res;
		
	}
}
