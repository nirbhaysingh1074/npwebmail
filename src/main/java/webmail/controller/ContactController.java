package webmail.controller;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.stream.FileImageInputStream;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sieve.manage.examples.ConnectAndListScripts;

import ezvcard.*;
import ezvcard.io.text.VCardReader;
import ezvcard.property.*;
import webmail.bean.NPCompare;
import webmail.webservice.client.FileClient;
import webmail.webservice.client.FolderClient;
import webmail.webservice.client.WebmailClient;
import webmail.wsdl.AssignSinglePermissionResponse;
import webmail.wsdl.CreateContactResponse;
import webmail.wsdl.CreateFileResponse;
import webmail.wsdl.CreateFolderResponse;
import webmail.wsdl.DeleteFileResponse;
import webmail.wsdl.DeleteFolderResponse;
import webmail.wsdl.Folder;
import webmail.wsdl.GetContactDetailResponse;
import webmail.wsdl.GetFileByPathResponse;
import webmail.wsdl.GetFileWithOutStreamResponse;
import webmail.wsdl.GetFolderByPathResponse;
import webmail.wsdl.GetFolderResponse;
import webmail.wsdl.GetFullContactDetailResponse;
import webmail.wsdl.GetLdapUserDirectoryResponse;
import webmail.wsdl.GetSharedFilesByPathResponse;
import webmail.wsdl.GetSharedFoldersByPathResponse;
import webmail.wsdl.GetSharedFoldersResponse;
import webmail.wsdl.GetVCFFileResponse;
import webmail.wsdl.GetVCFLdapDirResponse;
import webmail.wsdl.GetWebmailFolderResponse;
import webmail.wsdl.LdapDirectory;
import webmail.wsdl.RecycleFolderResponse;
import webmail.wsdl.RemoveAssignedPermissionResponse;
import webmail.wsdl.VCFFileAtt;
import webmail.wsdl.VCFLdapDirAtt;

import java.io.*;

@Controller
public class ContactController {

	
	@Autowired
	private WebmailClient webmailClient;
	
	@Autowired
	private FolderClient folderClient;
	
	@Autowired
	private FileClient fileClient;
	
	public final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

		public boolean validate(String emailStr) {
		        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		        return matcher.find();
		}
	
	
	@RequestMapping(value = "/uploadImageContactEdit",  produces = "text/html; charset=UTF-8", method = RequestMethod.POST)
	   public @ResponseBody String uploadImageContactEdit(MultipartHttpServletRequest request, HttpServletResponse response) {                 
	 
		 
		 
		 String st="false";
	     Iterator<String> itr =  request.getFileNames();
	 	HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String ldapurl=(String)hs.getAttribute("ldapurl");
		String ldapbase=(String)hs.getAttribute("ldapbase");
	   
	     while (itr.hasNext()){
	     MultipartFile mpf = request.getFile(itr.next());
	     try {
	    	 
	     int idx = mpf.getOriginalFilename().lastIndexOf('.');
    String fileExtension = idx > 0 ? mpf.getOriginalFilename().substring(idx) : ".tmp";
    if(fileExtension.equalsIgnoreCase(".png") || fileExtension.equalsIgnoreCase(".jpg") || fileExtension.equalsIgnoreCase(".jpeg"))
    {
    File fil = File.createTempFile(mpf.getOriginalFilename(), ".jpg");
  // System.out.println("!!!!!!!!!!!!!!!!!!!!!!="+fil.getPath()+"--"+fil.getName());
    mpf.transferTo(fil);
  
    byte[] bFile = new byte[(int) fil.length()];
    
    //convert file into array of bytes
    FileInputStream   fileInputStream = new FileInputStream(fil);
	    fileInputStream.read(bFile);
    fileInputStream.close(); 
    
    
 
		String entry="mail="+id+","+ldapbase;
		 st = new sun.misc.BASE64Encoder().encode(bFile);
	  hs.setAttribute("ContactImgEdit", bFile);
		/* InputStream stream = new ByteArrayInputStream(st.getBytes(StandardCharsets.UTF_8));
		 byte [] bt0=IOUtils.toByteArray(stream);
    byte [] bt=st.getBytes();
    byte[] bt1 = st.getBytes("UTF-8");
    byte[] bt2 = st.getBytes(Charset.forName("UTF-8"));
    byte[] bt3= IOUtils.toByteArray(st);
    
    InputStream in = IOUtils.toInputStream(st, "UTF-8");
    byte [] bt4=IOUtils.toByteArray(in);
    
    
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    int reads = in.read(); 
    while(reads != -1)
    {
 	   baos.write(reads); 
 	   reads = in.read();
    }
    byte [] bt5=baos.toByteArray();*/
    
    }
    
		} catch (IOException e)
	     {
			st="false";
			e.printStackTrace();
	     }
	   
	  break;
	    
	     }
	  

	    
	     
	     return st;
	 
	  }
	
	
	
	
	 @RequestMapping(value = "/uploadImageContact", produces = "text/html; charset=UTF-8", method = RequestMethod.POST)
	   public @ResponseBody String uploadImage(MultipartHttpServletRequest request, HttpServletResponse response) {                 
	 
		 
		 
		 String st="false";
	     Iterator<String> itr =  request.getFileNames();
	    // request.getp
	 	HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String ldapurl=(String)hs.getAttribute("ldapurl");
		String ldapbase=(String)hs.getAttribute("ldapbase");
	   
	     while (itr.hasNext()){
	     MultipartFile mpf = request.getFile(itr.next());
	     try {
	    	 
	     int idx = mpf.getOriginalFilename().lastIndexOf('.');
       String fileExtension = idx > 0 ? mpf.getOriginalFilename().substring(idx) : ".tmp";
       if(fileExtension.equalsIgnoreCase(".png") || fileExtension.equalsIgnoreCase(".jpg") || fileExtension.equalsIgnoreCase(".jpeg"))
       {
       File fil = File.createTempFile(mpf.getOriginalFilename(), ".jpg");
     // System.out.println("!!!!!!!!!!!!!!!!!!!!!!="+fil.getPath()+"--"+fil.getName());
       mpf.transferTo(fil);
     
       byte[] bFile = new byte[(int) fil.length()];
       
       //convert file into array of bytes
       FileInputStream   fileInputStream = new FileInputStream(fil);
	    fileInputStream.read(bFile);
       fileInputStream.close(); 
       
       
    
		String entry="mail="+id+","+ldapbase;
		 st = new sun.misc.BASE64Encoder().encode(bFile);
	  hs.setAttribute("ContactImg", bFile);
		/* InputStream stream = new ByteArrayInputStream(st.getBytes(StandardCharsets.UTF_8));
		 byte [] bt0=IOUtils.toByteArray(stream);
       byte [] bt=st.getBytes();
       byte[] bt1 = st.getBytes("UTF-8");
       byte[] bt2 = st.getBytes(Charset.forName("UTF-8"));
       byte[] bt3= IOUtils.toByteArray(st);
       
       InputStream in = IOUtils.toInputStream(st, "UTF-8");
       byte [] bt4=IOUtils.toByteArray(in);
       
       
       
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       int reads = in.read(); 
       while(reads != -1)
       {
    	   baos.write(reads); 
    	   reads = in.read();
       }
       byte [] bt5=baos.toByteArray();*/
       
       }
       
		} catch (IOException e)
	     {
			st="false";
			e.printStackTrace();
	     }
	   
	  break;
	    
	     }
	  

	    
	     
	     return st;
	 
	  }
	
	 
	 @RequestMapping(value = "/importContacts", produces = "text/html; charset=UTF-8", method = RequestMethod.POST)
	   public @ResponseBody String importContacts(MultipartHttpServletRequest request, HttpServletResponse response) 
	 {
		 
		 String st="true";
	     Iterator<String> itr =  request.getFileNames();
	    // request.getp
	 	HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String cfolder=(String)hs.getAttribute("active_contact");
	   if(cfolder.equals("Directory") || cfolder.equals("/Contacts/Collected Contacts"))
	   {
		   return "Contacts can not export into Directory or Collected Contacts";
	   }
	     while (itr.hasNext())
	     {
		     MultipartFile mpf = request.getFile(itr.next());
		     try
		     {
		    	 
				 int idx = mpf.getOriginalFilename().lastIndexOf('.');
			     String fileExtension = idx > 0 ? mpf.getOriginalFilename().substring(idx) : ".tmp";
			     if(fileExtension.equalsIgnoreCase(".vcf") || fileExtension.equalsIgnoreCase(".VCF") )
			     {
			     File fil = File.createTempFile(mpf.getOriginalFilename(), ".vcf");
			     System.out.println("!!!!!!!!!!!!!!!!!!!!!!="+fil.getPath()+"--"+fil.getName());
			     mpf.transferTo(fil);
   
   	   
				 //  String text = "D:\\contacts.vcf";
			     // put destination directory here - 
			     
			     // this line would have to be changed for java File.
			     //String[] strArrays = Files.readAllLines(text);
			     
			    List<String> lines=null;
				try 
				{
					lines = IOUtils.readLines(new FileInputStream(fil));
				}
				catch (FileNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(lines!=null)
				{
			     String[] strArrays =lines.toArray(new String[lines.size()]); 
			  		   
			     String empty = "";
			     String empty1 = "";
			     boolean flag = false;
			     boolean flag1 = false;
			     int num = 0;
			     
			     String[] strArrays1 = strArrays;
			     String name =""; // String.Empty;
			     for (int i = 0; i < (int)strArrays1.length; i++)
			     {
			         String str1 = strArrays1[i];
			         //this can be made case insensitive comparison
			         if (str1.equals("BEGIN:VCARD"))
			         {
			             flag = true;
			         }
			         if (str1.equals("END:VCARD"))
			         {
			             flag1 = true;
			         }
			         if (str1.startsWith("FN:"))
			         {
			             // put java equivalent of concat
			            // name = String.Concat(str1.substring(3), ".vcf");
			      	   name = str1.substring(3);
			         }
			         if (str1.toLowerCase().startsWith("EMAIL;".toLowerCase()))
			         {
			             // make java specific changes. need to split emailSplits variable on 2 chars ';' and ':'
			          //   String[] emailSplits = str1.split(new char[] { ';', ':' });
			      	   String[] emailSplits = str1.split(":");
			             empty1 = (emailSplits != null && emailSplits.length >= 2) ? emailSplits[emailSplits.length-1] : "";
			         }
			         if (flag)
			         {
			             // make java specific changes. Environment.NewLine is equivalent to '\n'
			             //empty = String.Concat(empty, Environment.NewLine, str1);
			      	   empty = empty+"\n"+ str1;
			         }
			         if (flag1)
			         {
			            if(empty1!=null && !empty1.equalsIgnoreCase("") && validate(empty1))
			            {
				            // empty1 = String.IsNullOrEmpty(empty1) ? name : empty1 + "_stork.vcf";
			            	
			            	UUID uuid = UUID.randomUUID();
			       	     	String randomUUIDString = uuid.toString();
			       	     	empty1=empty1+"$"+name+"_"+randomUUIDString+".vcf";
//				             empty1 = (empty1==null || empty1.equalsIgnoreCase("")) ? name : empty1 + "_id.vcf";
				             // make java specific changes - writes all lines to file
				             //    File.WriteAllText(Path.Combine(str, empty1), empty);
				             InputStream is = new ByteArrayInputStream(empty.getBytes());
				             
				             
				         	String notes="";
				    		String keywords="";
				    		String parentFolder = "/"+id+cfolder;
				    		String fileuserid=id;
				    	     
				    	     try 
				    	     {
				    	    	 byte[]	 iostream=org.apache.commons.codec.binary.Base64.encodeBase64(IOUtils.toByteArray(is));
				    		     CreateFileResponse cfileres=fileClient.createFile(empty1, parentFolder, fileuserid,pass,  keywords, notes,iostream,0);
				    		     boolean res=cfileres.isSuccess();
				    		     is.close();
				    		 }
				    	     catch (IOException e)
				    	     {
				    			// TODO Auto-generated catch block
				    			e.printStackTrace();
				    	     }
			             
			            }
			             
			             
			             flag = false;
			             flag1 = false;
			             empty = "";
			             empty1 = "";
			             name = "";
			             num++;
			         }
			         
			     }
				}
     
     
     
     
     
     }
     
		} catch (IOException e)
	     {
			st="false";
			e.printStackTrace();
	     }
	   
	  break;
	    
	     }
	  

	    
	     
	     return st;
	 
	  }
	
	@RequestMapping(value = "/getAllValueContactLdap",  produces = "text/html; charset=UTF-8", method = RequestMethod.GET)

	public String getAllValueContactLdap(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		String searchid=request.getParameter("searchid");
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String ldapurl=(String)hs.getAttribute("ldapurl");
		String ldpabase=(String)hs.getAttribute("ldapbase");
		System.out.println("!!!!!!!!!!!! ldap dn="+ldpabase);
		
		GetContactDetailResponse conres= webmailClient.getLdapContactFullDetail(ldapurl, id, pass, ldpabase, searchid);
		map.addAttribute("GetFullConRes", conres);
		
		
		return "contactalldispldap";
	}
@RequestMapping(value = "/getAllValueContact", method = RequestMethod.GET)

	public String getAllValueContact(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		HttpSession hs=request.getSession();
		String cfolder=(String)hs.getAttribute("active_contact");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String flname=request.getParameter("filename");
		System.out.println("openContactForm controller"+request.getParameter("filename"));
		String iostrm="";
		String parentFolder = "/"+id;
		String fileuserid=id;
		parentFolder=parentFolder+"/"+cfolder+"/"+flname;
		GetFileByPathResponse fileres= fileClient.getFileByPath(flname, fileuserid,pass);
		webmail.wsdl.File fileNode=fileres.getFile();
		//iostrm=fileNode.getFileContent();
		byte[] imageBytes = org.apache.commons.codec.binary.Base64.decodeBase64(fileNode.getFileContent());
		try {
			iostrm = IOUtils.toString(imageBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//InputStream inStream = IOUtils.toInputStream(iss);
		//System.out.println("^^^^^^^^^^^^^^^^="+iostrm);
		GetFullContactDetailResponse conres= webmailClient.getFullDetailContact(iostrm, flname);
		map.addAttribute("GetFullConRes", conres);
		//System.out.println("^^^^^^^^^^^^="+conres.getGetFullContactDetail().getWebamilFullName());
		
		return "contactalldisp";
	}
	

		@RequestMapping("/getSelContactUserList")
		public String getSelContactUserList(ModelMap map, Principal principal,RedirectAttributes attributes,
		HttpServletRequest request, @RequestParam String cname, @RequestParam(value="pn") int pn)
		  {
			System.out.println("^^^^^^^cname="+cname);
			System.out.println("^^^^^^^pns="+pn);
			
			HttpSession hs=request.getSession();
			hs.setAttribute("active_contact",cname);
			attributes.addAttribute("pn", pn);
//			attributes.addAttribute("rpp", rpp);
			
			return "redirect:/getContactList";
		  }

/*		@RequestMapping("/getSharedSelContactUserList")
		public String getSharedSelContactUserList(ModelMap map, Principal principal,
		HttpServletRequest request, @RequestParam String cname)
		  {
			System.out.println("^^^^^^^cname="+cname);
			HttpSession hs=request.getSession();
			hs.setAttribute("active_contact",cname);
			return "redirect:/getContactList";
		  }
*/
	
	
	
/*		@RequestMapping("/getAllValueContact")
		@ResponseBody
		public String getAllValueContact(ModelMap map, Principal principal,
		HttpServletRequest request)
		  {
			String res="<link type='text/css' rel='stylesheet' href='css/contact_css.css' />";
			
			res+="<table> <tr><td colspan='3' class='right_info_heading_edit'>Personal Information</td> </tr>";
			res+="<tr> <td class='right_name_first_pop_edit'>Full Name </td><td class='right_text_pop_edit'><input type='text' value='Hariom Srivastava' />";
	        res+="<div class='edite_name_edit'><div class='name_edit_edit'></div> Edit Name</div></td><td rowspan='3'><img src='images/photo_1.jpg'/>";
	        res+="<div class='save_chnage_pop_edit'>Change Image</div></td></tr>";
	        res+="<tr><td class='right_name_first_pop_edit'>Company</td><td><input type='text'  value='Silvereye IT solution Pvt.Ltd'/></td>";
	        res+="</tr><tr><td class='right_name_first_pop_edit'>Job Title</td>";
	        res+="  <td><input type='text' value='Web Designer'/></td></tr> </table>";
	
			return res;
		  }*/
	
	
	
	
    @RequestMapping(value = "/saveContactForm", produces = "text/html; charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
	public String saveContactForm(ModelMap map, Principal principal,
			HttpServletRequest request)
			  {
		HttpSession hs=request.getSession();
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String res="false";
		String cfolder= request.getParameter("selectedGp");
		String fullname= request.getParameter("fullname");
		String company= request.getParameter("company");
		String job= request.getParameter("job"); 
		String email= request.getParameter("email");
		String web_page= request.getParameter("web_page");
		String note= request.getParameter("note");
		String phone_work= request.getParameter("phone_work");
		String phone_home= request.getParameter("phone_home"); 
		String phone_fax= request.getParameter("phone_fax");
		String phone_mob= request.getParameter("phone_mob");
		String addr_work= request.getParameter("addr_work");
		String addr_home= request.getParameter("addr_home");
		String pre= request.getParameter("pre");
		String fnm= request.getParameter("fnm");
		String mnm= request.getParameter("mnm");
		String lnm= request.getParameter("lmn");
		String suf= request.getParameter("suf");
		String con_img=request.getParameter("con_img");
		byte bt[]=null;
		List <Byte> Bt_img=new ArrayList<Byte>();
		if(con_img!=null && !(con_img.equals("")) && !(con_img.equalsIgnoreCase("null")))
		{
			bt=(byte[])hs.getAttribute("ContactImg");
			hs.removeAttribute("ContactImg");
			if(bt!=null)
			{
			Byte[] byteObjects = new Byte[bt.length];

			int i=0;    
			// Associating Byte array values with bytes. (byte[] to Byte[])
			for(byte b: bt)
			byteObjects[i++] = b; 
			
			Bt_img = Arrays.asList(byteObjects);
			}
		}
		
		String arr[]=fullname.split(" ");
		if(arr.length==1)
		{
			if(arr[0]!=null && !arr[0].equals(""))
			{
				 pre= "";
				fnm= arr[0];
				mnm= "";
				lnm= "";
				suf="";
			}
			else
			{
				pre= "";
				fnm= "";
				mnm= "";
				lnm= "";
				suf="";
			}
		}
		else if(arr.length==2)
		{
			pre= "";
			fnm= arr[0];
			mnm= "";
			lnm= arr[1];
			suf="";
		}
		else if(arr.length>2)
		{
			int i=0;
			int l=1;
			if(arr[i].equalsIgnoreCase(pre))
				{
				i++;
				fnm=arr[i];
				}
			else
				{
				pre="";
				fnm=arr[i];
				}
			
			
			if(arr[arr.length-l].equalsIgnoreCase(suf))
				{
				
				l++;
				lnm=arr[arr.length-l];
				
				}
			else
				{
				suf="";
				lnm=arr[arr.length-l];
				}
			
			int j=1;
			String mid="";
			for(i++;i< arr.length-l; i++,j++)
				{
				if(arr[i]!=" ")
					{
				if(j==1)
					{
					mid=arr[i];
					}
				else
					{
					mid=mid+" "+arr[i];
					}
					}
				}
			mnm=mid;
		}
		
		
		if(fullname == null)
		{
			fullname = "";
		}
		else
		{
			fullname = fullname.trim();
		}
		
		
		String notes="";
		String keywords="";
		String parentFolder = "/"+id+"/Contacts";
		if(cfolder.contains("/sharedContacts/"))
		{
			parentFolder="/"+id+cfolder;
		}
		else
		{
			parentFolder=parentFolder+"/"+cfolder;
		}
		String fileuserid=id;
		
		String vcffilename="";
		 UUID uuid = UUID.randomUUID();
	     String randomUUIDString = uuid.toString();
	     vcffilename=email+"$"+fullname+"_"+randomUUIDString+".vcf";
	     CreateContactResponse resp= webmailClient.getVCFIOStream(vcffilename,note, fullname, company, job, email, web_page, phone_work, phone_home, phone_fax, phone_mob, addr_work, addr_home, pre, fnm, mnm, lnm, suf,Bt_img);
	     String iostrm=resp.getGetVFCIOStream();
	     InputStream is=IOUtils.toInputStream(iostrm);
	     
	     try {
	    byte[]	 iostream=org.apache.commons.codec.binary.Base64.encodeBase64(IOUtils.toByteArray(is));
	    	 System.out.println("^^^^^^^^^^^^^^^^^^"+resp.getGetVFCIOStream());
		     CreateFileResponse cfileres=fileClient.createFile(vcffilename, parentFolder, fileuserid,pass,  keywords, notes,iostream,0);
		    res=""+ cfileres.isSuccess();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	     
	     
	     
		 return res;
			  }
	
	
    
    @RequestMapping(value = "/saveToContactFromInbox", produces = "text/html; charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String saveToContactFromInbox(ModelMap map, Principal principal,
			HttpServletRequest request)
			  {
		HttpSession hs=request.getSession();
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String res="false";
		String cfolder= "Personal Contacts";
		String con_id= request.getParameter("con_id");
		String email= "";
		String fullname= "";
		String pre= "";
		String fnm= "";
		String mnm= "";
		String lnm= "";
		String suf= "";
	
		List <Byte> Bt_img=new ArrayList<Byte>();
		

		String notes="";
		String keywords="";
		String parentFolder = "/"+id+"/Contacts";
		String fileuserid=id;
		parentFolder=parentFolder+"/"+cfolder;
	
		if(con_id!=null  && con_id!="")
		{
		
		boolean st=	con_id.contains("<");
		if(st)
		{
			con_id=con_id.replace(">", "");
			String arr[]=con_id.split("<");
			if(arr.length>=2)
			{
			fullname=arr[0].trim();
			email=arr[1].trim();
			}
		}
		else
		{
			email=con_id;
			String arr[]=con_id.split("@");
			if(arr.length>=1)
			{
			fullname=arr[0];
			}
		}
		
		String []arr1=id.split("@");
		String []arr2=email.split("@");
		if(!arr1[1].equalsIgnoreCase(arr2[1]))
		{
		
			GetVCFFileResponse fileResponse =fileClient.getVCFFileRequest(parentFolder, id, pass);
			List<VCFFileAtt> vcffileList=fileResponse.getGetVCFFilesByParentFile().getVCFFileListResult().getVCFFileList();
			int flg=0;
			for(VCFFileAtt vfclst: vcffileList )
			{
				if(vfclst.getContactEmail().equalsIgnoreCase(email))
				{
					flg=1;
					break;
				}
			}
			if(flg==0)
			{
		
		String arr[]=fullname.split(" ");
		if(arr.length==1)
		{
			if(arr[0]!=null && !arr[0].equals(""))
			{
				 pre= "";
				fnm= arr[0];
				mnm= "";
				lnm= "";
				suf="";
			}
			else
			{
				pre= "";
				fnm= "";
				mnm= "";
				lnm= "";
				suf="";
			}
		}
		else if(arr.length==2)
		{
			pre= "";
			fnm= arr[0];
			mnm= "";
			lnm= arr[1];
			suf="";
		}
		else if(arr.length>2)
		{
			int i=0;
			int l=1;
			if(arr[i].equalsIgnoreCase(pre))
				{
				i++;
				fnm=arr[i];
				}
			else
				{
				pre="";
				fnm=arr[i];
				}
			
			
			if(arr[arr.length-l].equalsIgnoreCase(suf))
				{
				
				l++;
				lnm=arr[arr.length-l];
				
				}
			else
				{
				suf="";
				lnm=arr[arr.length-l];
				}
			
			int j=1;
			String mid="";
			for(i++;i< arr.length-l; i++,j++)
				{
				if(arr[i]!=" ")
					{
				if(j==1)
					{
					mid=arr[i];
					}
				else
					{
					mid=mid+" "+arr[i];
					}
					}
				}
			mnm=mid;
		}
		
		
		if(fullname == null)
		{
			fullname = "";
		}
		else
		{
			fullname = fullname.trim();
		}
		
		
		String vcffilename="";
		 UUID uuid = UUID.randomUUID();
	     String randomUUIDString = uuid.toString();
	     vcffilename=email+"$"+fullname+"_"+randomUUIDString+".vcf";
	     CreateContactResponse resp= webmailClient.getVCFIOStream(vcffilename,"", fullname, "", "", email, "", "", "", "", "", "", "", pre, fnm, mnm, lnm, suf,Bt_img);
	     String iostrm=resp.getGetVFCIOStream();
	     InputStream is=IOUtils.toInputStream(iostrm);
	     
	     try {
	    byte[]	 iostream=org.apache.commons.codec.binary.Base64.encodeBase64(IOUtils.toByteArray(is));
	    	 System.out.println("^^^^^^^^^^^^^^^^^^"+resp.getGetVFCIOStream());
		     CreateFileResponse cfileres=fileClient.createFile(vcffilename, parentFolder, fileuserid, pass,keywords, notes,iostream,0);
		    res=""+ cfileres.isSuccess();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     res="true";
			}
			else
			{
				res="already";	
			}
		}
		else
		{
			res="already";
		}
		  }
	     
		 return res;
			
	
			  }
    
    
    @RequestMapping(value = "/editContactForm",  produces = "text/html; charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
	public String editContactForm(ModelMap map, Principal principal,
			HttpServletRequest request)
	  {
		HttpSession hs=request.getSession();
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String res="false";
		String fullname= request.getParameter("fullname");
		String company= request.getParameter("company");
		String job= request.getParameter("job"); 
		String email= request.getParameter("email");
		String web_page= request.getParameter("web_page");
		String note= request.getParameter("note");
		String phone_work= request.getParameter("phone_work");
		String phone_home= request.getParameter("phone_home"); 
		String phone_fax= request.getParameter("phone_fax");
		String phone_mob= request.getParameter("phone_mob");
		String addr_work= request.getParameter("addr_work");
		String addr_home= request.getParameter("addr_home");
		String pre= request.getParameter("pre");
		String fnm= request.getParameter("fnm");
		String mnm= request.getParameter("mnm");
		String lnm= request.getParameter("lmn");
		String suf= request.getParameter("suf");
		String con_img=request.getParameter("con_img");
		String flnm=request.getParameter("flnm");
		byte bt[]=null;
		List <Byte> Bt_img=new ArrayList<Byte>();
		if(con_img!=null && !(con_img.equals("")) && !(con_img.equalsIgnoreCase("null")))
		{
			bt=(byte[])hs.getAttribute("ContactImgEdit");
			hs.removeAttribute("ContactImgEdit");
			if(bt!=null)
			{
			Byte[] byteObjects = new Byte[bt.length];

			int i=0;    
			// Associating Byte array values with bytes. (byte[] to Byte[])
			for(byte b: bt)
			byteObjects[i++] = b; 
			
			Bt_img = Arrays.asList(byteObjects);
			}
		}
		else
		{
			List <Byte> Bt_imgcpy=(List)hs.getAttribute("ContactPhotoByte");
			//hs.removeAttribute("ContactPhotoByte");
			if(Bt_imgcpy!=null)
			{
				Bt_img=Bt_imgcpy;
			}
		}
		
		String arr[]=fullname.split(" ");
		if(arr.length==1)
		{
			if(arr[0]!=null && !arr[0].equals(""))
			{
				 pre= "";
				fnm= arr[0];
				mnm= "";
				lnm= "";
				suf="";
			}
			else
			{
				pre= "";
				fnm= "";
				mnm= "";
				lnm= "";
				suf="";
			}
		}
		else if(arr.length==2)
		{
			pre= "";
			fnm= arr[0];
			mnm= "";
			lnm= arr[1];
			suf="";
		}
		else if(arr.length>2)
		{
			int i=0;
			int l=1;
			if(arr[i].equalsIgnoreCase(pre))
				{
				i++;
				fnm=arr[i];
				}
			else
				{
				pre="";
				fnm=arr[i];
				}
			
			
			if(arr[arr.length-l].equalsIgnoreCase(suf))
				{
				
				l++;
				lnm=arr[arr.length-l];
				
				}
			else
				{
				suf="";
				lnm=arr[arr.length-l];
				}
			
			int j=1;
			String mid="";
			for(i++;i< arr.length-l; i++,j++)
				{
				if(arr[i]!=" ")
					{
				if(j==1)
					{
					mid=arr[i];
					}
				else
					{
					mid=mid+" "+arr[i];
					}
					}
				}
			mnm=mid;
		}
		
		String notes="";
		String keywords="";
		String parentFolder ="/"+id+"/Contacts";
		if(flnm.contains("/sharedContacts/"))
		{
			 parentFolder ="/"+id+"/sharedContacts";
		}
		String fileuserid=id;
		String arrfl[]=flnm.split("/");
	
		
		if(arrfl.length>3)
		{
		parentFolder=parentFolder+"/"+arrfl[3];
		}
	
		String vcffilename="";
		 UUID uuid = UUID.randomUUID();
	     String randomUUIDString = uuid.toString();
	     if(fullname == null)
	     {
	    	 fullname = "";
	     }
	     else
	     {
	    	 fullname = fullname.trim();
	     }
	     vcffilename=email+"$"+fullname+"_"+randomUUIDString+".vcf";
	     CreateContactResponse resp= webmailClient.getVCFIOStream(vcffilename,note, fullname, company, job, email, web_page, phone_work, phone_home, phone_fax, phone_mob, addr_work, addr_home, pre, fnm, mnm, lnm, suf,Bt_img);
	     String iostrm=resp.getGetVFCIOStream();
	     InputStream is=IOUtils.toInputStream(iostrm);
	     
	     try {
	    byte[]	 iostream=org.apache.commons.codec.binary.Base64.encodeBase64(IOUtils.toByteArray(is));
	    	 System.out.println("^^^^^^^^^^^^^^^^^^"+resp.getGetVFCIOStream());
		     CreateFileResponse cfileres=fileClient.createFile(vcffilename, parentFolder, fileuserid,pass, keywords, notes,iostream,0);
		    res=""+ cfileres.isSuccess();
		    

		    fileClient.deleteFile(flnm, id,pass);
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     
	     

	     
	     
	     
		 return res;
			  }
    
    
	
	
	
	@RequestMapping("/createContactGroup")
	@ResponseBody
	public String createContactGroup(ModelMap map, Principal principal,
			HttpServletRequest request, @RequestParam String folderName)
			  {
		HttpSession hs=request.getSession();
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		System.out.println("^^^^^^^fname="+folderName);
		folderName=folderName.trim();
		String res="false";
				//HttpSession hs = request.getSession(false);
				String notes="";
				String keywords="";
				String parentFolder = "/"+id+"/Contacts";
				CreateFolderResponse createFolderResponse = folderClient.createFolder(
						folderName , parentFolder,id,pass ,keywords,notes);
				
				Folder folder = createFolderResponse.getFolder();
				String newFolder="";
				if(folder!=null ){
				res="true";
				}else{
					//return "access denied"; 
				}
				return res;
	}
	
	@RequestMapping(value = "/getDirectoryUserList",  produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String getDirectoryUserList(ModelMap map, Principal principal,
		HttpServletRequest request) {
		String res=" <table class='con_he_content'>  ";
		//System.out.println("!!!!!!!!!!! directory");
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String ldapurl=(String)hs.getAttribute("ldapurl");
		String ldpabase=(String)hs.getAttribute("ldapbase");
		//System.out.println("!!!!!!!!!!!! ldap dn="+ldpabase);
		GetVCFLdapDirResponse getdirres=webmailClient.getLdapDirectory(ldapurl, id, pass, ldpabase,"*");
		
		List<VCFLdapDirAtt> ldapDirList=getdirres.getGetVCFLdapDirByParentFile().getVCFLdapDirListResult().getVCFLdapDirList();
		
		Collections.sort(ldapDirList,new NPCompare());
		
		for(VCFLdapDirAtt ulst : ldapDirList)
		{
			//System.out.println("!!!!!!!!!!!!!!! name="+ulst.getContactName()+"  email="+ulst.getContactEmail()+" phone="+ulst.getContactPhone()+" addr="+ulst.getContactAddress()+" photo="+ulst.getContactPhoto()); 
			 	  res+="<tr >";
				  res+="  <td class='con_box contact_input'><input class='contact_input_dir' type='checkbox'/></td>";
				  res+="<td class='con_td_img'><img src='images/blank_man.png' /></td>";
		          res+="<td class='con_name'><div class='inner_text'>"+ulst.getContactName()+"</div></td>";
		          res+="<td class='con_email'><div class='inner_text'>"+ulst.getContactEmail()+"</div></td>";
		          res+=" <td class='con_email'><div class='inner_text'>"+ulst.getContactPhone()+"</div></td>";
		          res+="<td class='con_department'><div class='inner_text'>"+ulst.getContactDept()+"</div></td>";
		          res+=" <td class='con_address'><div class='inner_text address_div'>"+ulst.getContactAddress()+"</div></td>";
		          res+="</tr>";
		}
		 res+="  </table>";
		return res;
	}
	
	
	
	@RequestMapping(value = "/getconPopupListContacts",  produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String getconPopupListContacts(ModelMap map, Principal principal, HttpServletRequest request ) 
	{
		
		String res="<table class='con_he_list' style='    width: 100%;    position: relative;    top: 0px;'>     <tbody><tr> <td style='padding-left: 5px;' class='left_head'><input  class='contact_check_all' type='checkbox'></td>";
		 res+="<td class='con_imag_head'><img src='images/white_man.png'></td>   <td class='con_name_head'>Name</td> <td class='con_email'>Email</td>";
		 res+="</tr>  </tbody></table>";
		
		 res+="<table class='con_he_left_list'>  ";
		//String start= request.getParameter("start");
		///String end= request.getParameter("end");
		HttpSession hs=request.getSession();
		String uid=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
//		String limitContact = hsbd.getAttribute("limitContact").toString();

		/*String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String port=(String)hs.getAttribute("port");*/
		String cfolder=request.getParameter("fpath");
		
		
		if(cfolder.equalsIgnoreCase("Directory"))
		{
//			String host=(String)hs.getAttribute("host");
			String id=(String)hs.getAttribute("id");
			String ldapurl=(String)hs.getAttribute("ldapurl");
			String ldpabase=(String)hs.getAttribute("ldapbase");
			//System.out.println("!!!!!!!!!!!! ldap dn="+ldpabase);
			GetVCFLdapDirResponse getdirres=webmailClient.getLdapDirectory(ldapurl, id, pass, ldpabase, "*");
			
			List<VCFLdapDirAtt> ldapDirList=getdirres.getGetVCFLdapDirByParentFile().getVCFLdapDirListResult().getVCFLdapDirList();
			
			Collections.sort(ldapDirList,new NPCompare());
			
			if(ldapDirList != null && !ldapDirList.isEmpty())
			{
				int total =  ldapDirList.size();
				
				for(int i = 0; i < total; i++)
				{
					VCFLdapDirAtt ulst = ldapDirList.get(i);
					String photo=ulst.getContactPhoto();
					String tit="";
							tit=ulst.getContactName().replace(",", "");
							tit=tit.replace("'", "");
						tit=tit.replace("\"", "");
					if(tit.length()>0)
					{
						tit=tit+" <"+ulst.getContactEmail()+">";
					}
					else
					{
						tit=ulst.getContactEmail();
					}
					//System.out.println("!!!!!!!!!!!!!!! name="+ulst.getContactName()+"  email="+ulst.getContactEmail()+" phone="+ulst.getContactPhone()+" addr="+ulst.getContactAddress()+" photo="+ulst.getContactPhoto()); 
					res+="<tr name='Directory' id='"+ulst.getContactEmail()+"'>";
					res+="  <td class='con_box_left' style='padding-left: 5px;'><input class='contact_input_dir' name='chk_con' value='"+tit+"' type='checkbox'/></td>";
					if(photo!= null && !(photo.equals("")  ))
					{
						res+="<td class='con_imag_left'><img src='data:image/jpg;base64,"+photo+"' /></td>"; 
					}
					else
					{
						res+="<td class='con_imag_left'><img src='images/blank_man.png' /></td>";
					}
					res+="<td class='con_name_left'><div class='inner_text_left'>"+ulst.getContactName()+"</div></td>";
					res+="<td class='con_email_left'><div class='inner_text_left'>"+ulst.getContactEmail()+"</div></td>";
					/* res+=" <td class='con_email'><div class='inner_text'>"+ulst.getContactPhone()+"</div></td>";
			          res+="<td class='con_department'><div class='inner_text'>"+ulst.getContactDept()+"</div></td>";
			          res+=" <td class='con_address'><div class='inner_text address_div'>"+ulst.getContactAddress()+"</div></td>";
					 */
					res+="  </tr>";
				
				}
				
//				FOR(VCFLDAPDIRATT ULST : LDAPDIRLIST)
//				{
//				}	
				
			}
			
		}
		else
		{
			try
			{
				
				GetVCFFileResponse fileResponse =fileClient.getVCFFileRequest("/"+uid+cfolder, uid,pass);
				List<VCFFileAtt> vcffileList=fileResponse.getGetVCFFilesByParentFile().getVCFFileListResult().getVCFFileList();
				
				System.out.println("path is "+"/"+uid+cfolder);
				 //Collections.sort(vcffileList, new VCFFileAtt());
				
				int total =  vcffileList.size();
				
				
				if(vcffileList != null && !vcffileList.isEmpty())
				{
					
					for(int i = 0; i < total; i++)
					{
						VCFFileAtt vfclst = vcffileList.get(i);
						
						String tit="";
						tit=vfclst.getContactName().replace(",", "");
						tit=tit.replace("'", "");
						tit=tit.replace("\"", "");
						if(tit.length()>0)
						{
							tit=tit+" <"+vfclst.getContactEmail()+">";
						}
						else
						{
							tit=vfclst.getContactEmail();
						}
						
						res+="<tr name='othercontact' class='del_row'  id='/"+uid+cfolder+"/"+vfclst.getContactFileName()+"'>";
						res+="<td class='con_box_left' style='padding-left: 5px;'>";
						
							res+= "<input name='chk_con' value='"+tit+"' class='contact_input' type='checkbox'/>";
					
						res+= "</td>";
						res+="<td class='con_imag_left'><img src='images/blank_man.png' /></td>";
						res+="<td class='con_name_left'><div class='inner_text_left'>"+vfclst.getContactName()+"</div></td>";
						res+="<td class='con_email_left'><div class='inner_text_left'>"+vfclst.getContactEmail()+"</div></td>";
						/*   res+=" <td class='con_email'><div class='inner_text'>"+vfclst.getContactPhone()+"</div></td>";
       		          res+="<td class='con_department'><div class='inner_text'>"+vfclst.getContactDept()+"</div></td>";
       		          res+=" <td class='con_address'><div class='inner_text address_div'>"+vfclst.getContactAddress()+"</div></td>";
						 */
						res+="</tr>";
						
					}
					
				}
				
    		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        		 
	        	 
		}  
		
		
		
		res+="  </table> ";
		
		
		
		return res;
		
	}
	
	
	
	
	@RequestMapping(value = "/getconPopupListContactsSearch",  produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String getconPopupListContactsSearch(ModelMap map, Principal principal, HttpServletRequest request ) 
	{
		
		String res="<table class='con_he_list' style='    width: 100%;    position: relative;    top: 0px;'>     <tbody><tr> <td style='padding-left: 5px;' class='left_head'><input  class='contact_check_all' type='checkbox'></td>";
		 res+="<td class='con_imag_head'><img src='images/white_man.png'></td>   <td class='con_name_head'>Name</td> <td class='con_email'>Email</td>";
		 res+="</tr>  </tbody></table>";
		
		 res+="<table class='con_he_left_list'>  ";
		String alpha= request.getParameter("search");
		///String end= request.getParameter("end");
		HttpSession hs=request.getSession();
		String uid=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
//		String limitContact = hsbd.getAttribute("limitContact").toString();

		/*String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String port=(String)hs.getAttribute("port");*/
		String cfolder=request.getParameter("fpath");
		
		
		if(cfolder.equalsIgnoreCase("Directory"))
		{
//			String host=(String)hs.getAttribute("host");
			String id=(String)hs.getAttribute("id");
			String ldapurl=(String)hs.getAttribute("ldapurl");
			String ldpabase=(String)hs.getAttribute("ldapbase");
			//System.out.println("!!!!!!!!!!!! ldap dn="+ldpabase);
			GetVCFLdapDirResponse getdirres=webmailClient.getLdapDirectory(ldapurl, id, pass, ldpabase, alpha+"*");
			
			List<VCFLdapDirAtt> ldapDirList=getdirres.getGetVCFLdapDirByParentFile().getVCFLdapDirListResult().getVCFLdapDirList();
			
			Collections.sort(ldapDirList,new NPCompare());
			
			if(ldapDirList != null && !ldapDirList.isEmpty())
			{
				int total =  ldapDirList.size();
				
				for(int i = 0; i < total; i++)
				{
					VCFLdapDirAtt ulst = ldapDirList.get(i);
					String photo=ulst.getContactPhoto();
					String tit="";
							tit=ulst.getContactName().replace(",", "");
							tit=tit.replace("'", "");
						tit=tit.replace("\"", "");
					if(tit.length()>0)
					{
						tit=tit+" <"+ulst.getContactEmail()+">";
					}
					else
					{
						tit=ulst.getContactEmail();
					}
					//System.out.println("!!!!!!!!!!!!!!! name="+ulst.getContactName()+"  email="+ulst.getContactEmail()+" phone="+ulst.getContactPhone()+" addr="+ulst.getContactAddress()+" photo="+ulst.getContactPhoto()); 
					res+="<tr name='Directory' id='"+ulst.getContactEmail()+"'>";
					res+="  <td class='con_box_left' style='padding-left: 5px;'><input class='contact_input_dir' name='chk_con' value='"+tit+"' type='checkbox'/></td>";
					if(photo!= null && !(photo.equals("")  ))
					{
						res+="<td class='con_imag_left'><img src='data:image/jpg;base64,"+photo+"' /></td>"; 
					}
					else
					{
						res+="<td class='con_imag_left'><img src='images/blank_man.png' /></td>";
					}
					res+="<td class='con_name_left'><div class='inner_text_left'>"+ulst.getContactName()+"</div></td>";
					res+="<td class='con_email_left'><div class='inner_text_left'>"+ulst.getContactEmail()+"</div></td>";
					/* res+=" <td class='con_email'><div class='inner_text'>"+ulst.getContactPhone()+"</div></td>";
			          res+="<td class='con_department'><div class='inner_text'>"+ulst.getContactDept()+"</div></td>";
			          res+=" <td class='con_address'><div class='inner_text address_div'>"+ulst.getContactAddress()+"</div></td>";
					 */
					res+="  </tr>";
				
				}
				
//				FOR(VCFLDAPDIRATT ULST : LDAPDIRLIST)
//				{
//				}	
				
			}
			
		}
		else
		{
			try
			{
				
				GetVCFFileResponse fileResponse =fileClient.getVCFFileRequest("/"+uid+cfolder, uid,pass);
				List<VCFFileAtt> vcffileList=fileResponse.getGetVCFFilesByParentFile().getVCFFileListResult().getVCFFileList();
				
				System.out.println("path is "+"/"+uid+cfolder);
				 //Collections.sort(vcffileList, new VCFFileAtt());
				
				int total =  vcffileList.size();
				
				
				if(vcffileList != null && !vcffileList.isEmpty())
				{
					
					for(int i = 0; i < total; i++)
					{
						VCFFileAtt vfclst = vcffileList.get(i);
						if(vfclst.getContactEmail().startsWith(alpha) || vfclst.getContactEmail().startsWith(alpha.toLowerCase()) || vfclst.getContactEmail().startsWith(alpha.toUpperCase()) || (vfclst.getContactEmail().toUpperCase()).startsWith(alpha.toUpperCase())  || (vfclst.getContactEmail().toLowerCase()).startsWith(alpha.toLowerCase()) )
						{
							String tit="";
						tit=vfclst.getContactName().replace(",", "");
						tit=tit.replace("'", "");
						tit=tit.replace("\"", "");
						if(tit.length()>0)
						{
							tit=tit+" <"+vfclst.getContactEmail()+">";
						}
						else
						{
							tit=vfclst.getContactEmail();
						}
						
						res+="<tr name='othercontact' class='del_row'  id='/"+uid+cfolder+"/"+vfclst.getContactFileName()+"'>";
						res+="<td class='con_box_left' style='padding-left: 5px;'>";
						
							res+= "<input name='chk_con' value='"+tit+"' class='contact_input' type='checkbox'/>";
					
						res+= "</td>";
						res+="<td class='con_imag_left'><img src='images/blank_man.png' /></td>";
						res+="<td class='con_name_left'><div class='inner_text_left'>"+vfclst.getContactName()+"</div></td>";
						res+="<td class='con_email_left'><div class='inner_text_left'>"+vfclst.getContactEmail()+"</div></td>";
						/*   res+=" <td class='con_email'><div class='inner_text'>"+vfclst.getContactPhone()+"</div></td>";
       		          res+="<td class='con_department'><div class='inner_text'>"+vfclst.getContactDept()+"</div></td>";
       		          res+=" <td class='con_address'><div class='inner_text address_div'>"+vfclst.getContactAddress()+"</div></td>";
						 */
						res+="</tr>";
						}
					}
					
				}
				
    		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        		 
	        	 
		}  
		
		
		
		res+="  </table> ";
		
		
		
		return res;
		
	}
	
	
	@RequestMapping(value = "/getContactList",  produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String getContactList(ModelMap map, Principal principal, HttpServletRequest request , @RequestParam(value="pn") int pn) 
	{
		String res="<table class='con_he_left_list'>  ";
		//String start= request.getParameter("start");
		///String end= request.getParameter("end");
		HttpSession hs=request.getSession();
		String uid=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String limitContact=(String)hs.getAttribute("limitContact");
//		String limitContact = hsbd.getAttribute("limitContact").toString();

		/*String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String port=(String)hs.getAttribute("port");*/
		String cfolder=(String)hs.getAttribute("active_contact");
		System.out.println("inbox***********************contact folder="+cfolder);
		System.out.println("inbox***********************contact folder="+pn);
		
		int rpp = Integer.parseInt(limitContact);
		
		JSONObject obj = new JSONObject();
		if(cfolder.equalsIgnoreCase("Directory"))
		{
//			String host=(String)hs.getAttribute("host");
			String id=(String)hs.getAttribute("id");
			String ldapurl=(String)hs.getAttribute("ldapurl");
			String ldpabase=(String)hs.getAttribute("ldapbase");
			//System.out.println("!!!!!!!!!!!! ldap dn="+ldpabase);
			GetVCFLdapDirResponse getdirres=webmailClient.getLdapDirectory(ldapurl, id, pass, ldpabase, "*");
			
			List<VCFLdapDirAtt> ldapDirList=getdirres.getGetVCFLdapDirByParentFile().getVCFLdapDirListResult().getVCFLdapDirList();
			
			Collections.sort(ldapDirList,new NPCompare());
			
			if(ldapDirList != null && !ldapDirList.isEmpty())
			{
				int total =  ldapDirList.size();
				int start = (pn-1)*rpp;
				int  end = start;
				obj.put("total", total);
				obj.put("start", start+1);
				
				for(int i = start; i < start+rpp && i < total; i++)
				{
					VCFLdapDirAtt ulst = ldapDirList.get(i);
					String photo=ulst.getContactPhoto();
					//System.out.println("!!!!!!!!!!!!!!! name="+ulst.getContactName()+"  email="+ulst.getContactEmail()+" phone="+ulst.getContactPhone()+" addr="+ulst.getContactAddress()+" photo="+ulst.getContactPhoto()); 
					res+="<tr name='Directory' id='"+ulst.getContactEmail()+"'>";
					res+="  <td class='con_box_left'><input class='contact_input_dir' type='checkbox'/></td>";
					if(photo!= null && !(photo.equals("")  ))
					{
						res+="<td class='con_imag_left'><img src='data:image/jpg;base64,"+photo+"' /></td>"; 
					}
					else
					{
						res+="<td class='con_imag_left'><img src='images/blank_man.png' /></td>";
					}
					res+="<td class='con_name_left'><div class='inner_text_left'>"+ulst.getContactName()+"</div></td>";
					res+="<td class='con_email_left'><div class='inner_text_left'>"+ulst.getContactEmail()+"</div></td>";
					/* res+=" <td class='con_email'><div class='inner_text'>"+ulst.getContactPhone()+"</div></td>";
			          res+="<td class='con_department'><div class='inner_text'>"+ulst.getContactDept()+"</div></td>";
			          res+=" <td class='con_address'><div class='inner_text address_div'>"+ulst.getContactAddress()+"</div></td>";
					 */
					res+="  </tr>";
					end++;
				}
				
//				FOR(VCFLDAPDIRATT ULST : LDAPDIRLIST)
//				{
//				}	
				obj.put("end", end);
			}
			else
			{
				obj.put("start", 0);
				obj.put("end", 0);
			}
		}
		else
		{
			try
			{
				
				GetVCFFileResponse fileResponse =fileClient.getVCFFileRequest("/"+uid+cfolder, uid,pass);
				List<VCFFileAtt> vcffileList=fileResponse.getGetVCFFilesByParentFile().getVCFFileListResult().getVCFFileList();
				
				System.out.println("path is "+"/"+uid+cfolder);
				 //Collections.sort(vcffileList, new VCFFileAtt());
				
				int total =  vcffileList.size();
				int start = (pn-1)*rpp;

				obj.put("total", total);
				obj.put("start", start+1);
				
				if(vcffileList != null && !vcffileList.isEmpty())
				{
					int  end = start;
					for(int i = start; i < start+rpp && i < total; i++)
					{
						VCFFileAtt vfclst = vcffileList.get(i);
						res+="<tr name='othercontact' class='del_row'  id='/"+uid+cfolder+"/"+vfclst.getContactFileName()+"'>";
						res+="<td class='con_box_left'>";
						if(!cfolder.startsWith("/sharedContacts/"))
						{
							res+= "<input name='chk_con' value='/"+uid+cfolder+"/"+vfclst.getContactFileName()+"' class='contact_input' type='checkbox'/>";
						}
						res+= "</td>";
						res+="<td class='con_imag_left'><img src='images/blank_man.png' /></td>";
						res+="<td class='con_name_left'><div class='inner_text_left'>"+vfclst.getContactName()+"</div></td>";
						res+="<td class='con_email_left'><div class='inner_text_left'>"+vfclst.getContactEmail()+"</div></td>";
						/*   res+=" <td class='con_email'><div class='inner_text'>"+vfclst.getContactPhone()+"</div></td>";
       		          res+="<td class='con_department'><div class='inner_text'>"+vfclst.getContactDept()+"</div></td>";
       		          res+=" <td class='con_address'><div class='inner_text address_div'>"+vfclst.getContactAddress()+"</div></td>";
						 */
						res+="</tr>";
						end++;
					}
					obj.put("end", end);
				}
				else
				{
					obj.put("start", 0);
					obj.put("end", 0);
				}
    		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        		 
	        	 
		}  
		
		
		
		res+="  </table> ";
		obj.put("contacts", res);
		
		
		return obj.toJSONString();
		
	}
	
	
	
	
	@RequestMapping(value = "/getContactListAlpha", produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String getContactListAlpha(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		String res="<table class='con_he_left_list'>  ";
		HttpSession hs=request.getSession();
		String uid=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		
		String cfolder=(String)hs.getAttribute("active_contact");
		String alpha=request.getParameter("alpha");
		//System.out.println("inbox***********************contact folder="+cfolder);
		
		JSONObject obj = new JSONObject();
		
		if(cfolder.equalsIgnoreCase("Directory"))
		{
			String host=(String)hs.getAttribute("host");
			String id=(String)hs.getAttribute("id");
			String ldapurl=(String)hs.getAttribute("ldapurl");
			String ldpabase=(String)hs.getAttribute("ldapbase");
			//System.out.println("!!!!!!!!!!!! ldap dn="+ldpabase);
			
			
			
			GetVCFLdapDirResponse getdirres=webmailClient.getLdapDirectory(ldapurl, id, pass, ldpabase, alpha+"*");
			
			List<VCFLdapDirAtt> ldapDirList=getdirres.getGetVCFLdapDirByParentFile().getVCFLdapDirListResult().getVCFLdapDirList();
			
			
			for(VCFLdapDirAtt ulst : ldapDirList)
			{
				String photo=ulst.getContactPhoto();
				res+="<tr name='Directory' id='"+ulst.getContactEmail()+"'>";
				res+="  <td class='con_box_left'><input class='contact_input_dir' type='checkbox'/></td>";
				if(photo!= null && !(photo.equals("")  ))
				{
					res+="<td class='con_imag_left'><img src='data:image/jpg;base64,"+photo+"' /></td>"; 
				}
				else
				{
					res+="<td class='con_imag_left'><img src='images/blank_man.png' /></td>";
				}
				res+="<td class='con_name_left'><div class='inner_text_left'>"+ulst.getContactName()+"</div></td>";
				res+="<td class='con_email_left'><div class='inner_text_left'>"+ulst.getContactEmail()+"</div></td>";
				/* res+=" <td class='con_email'><div class='inner_text'>"+ulst.getContactPhone()+"</div></td>";
		          res+="<td class='con_department'><div class='inner_text'>"+ulst.getContactDept()+"</div></td>";
		          res+=" <td class='con_address'><div class='inner_text address_div'>"+ulst.getContactAddress()+"</div></td>";
				 */
				res+="  </tr>";
			}
				
				
			
		}
		else
		{
			try
			{
			
				GetVCFFileResponse fileResponse =fileClient.getVCFFileRequest("/"+uid+cfolder, uid,pass);
				List<VCFFileAtt> vcffileList=fileResponse.getGetVCFFilesByParentFile().getVCFFileListResult().getVCFFileList();
				
				System.out.println("path is "+"/"+uid+cfolder);
				

					if(alpha.equals("123"))
					{
						for(VCFFileAtt vfclst: vcffileList )
						{
							if(vfclst.getContactEmail() != null && vfclst.getContactEmail().length() > 0 && Character.isDigit(vfclst.getContactEmail().charAt(0)))
							{
								res+=" <tr name='othercontact' class='del_row'  id='/"+uid+cfolder+"/"+vfclst.getContactFileName()+"'>";
								res+="<td class='con_box_left'>";
								if(!cfolder.startsWith("/sharedContacts/"))
								{
									res+= "<input name='chk_con' value='/"+uid+cfolder+"/"+vfclst.getContactFileName()+"' class='contact_input' type='checkbox'/>";
								}
								res+= "</td>";
								res+="<td class='con_imag_left'><img src='images/blank_man.png' /></td>";
								res+="<td class='con_name_left'><div class='inner_text_left'>"+vfclst.getContactName()+"</div></td>";
								res+="<td class='con_email_left'><div class='inner_text_left'>"+vfclst.getContactEmail()+"</div></td>";
								/*   res+=" <td class='con_email'><div class='inner_text'>"+vfclst.getContactPhone()+"</div></td>";
			       		          res+="<td class='con_department'><div class='inner_text'>"+vfclst.getContactDept()+"</div></td>";
			       		          res+=" <td class='con_address'><div class='inner_text address_div'>"+vfclst.getContactAddress()+"</div></td>";
								 */
								res+="  </tr>";
							}
						}
					}
					else
					{
						for(VCFFileAtt vfclst: vcffileList )
						{
							if(vfclst.getContactEmail().startsWith(alpha) || vfclst.getContactEmail().startsWith(alpha.toLowerCase()) || vfclst.getContactEmail().startsWith(alpha.toUpperCase()) || (vfclst.getContactEmail().toUpperCase()).startsWith(alpha.toUpperCase())  || (vfclst.getContactEmail().toLowerCase()).startsWith(alpha.toLowerCase()) )
							{
								
								res+=" <tr name='othercontact' class='del_row'  id='/"+uid+cfolder+"/"+vfclst.getContactFileName()+"'>";
								res+="<td class='con_box_left'>";
								if(!cfolder.startsWith("/sharedContacts/"))
								{
									res+= "<input name='chk_con' value='/"+uid+cfolder+"/"+vfclst.getContactFileName()+"' class='contact_input' type='checkbox'/>";
								}
								res+= "</td>";
								res+="<td class='con_imag_left'><img src='images/blank_man.png' /></td>";
								res+="<td class='con_name_left'><div class='inner_text_left'>"+vfclst.getContactName()+"</div></td>";
								res+="<td class='con_email_left'><div class='inner_text_left'>"+vfclst.getContactEmail()+"</div></td>";
								/*   res+=" <td class='con_email'><div class='inner_text'>"+vfclst.getContactPhone()+"</div></td>";
		       		          res+="<td class='con_department'><div class='inner_text'>"+vfclst.getContactDept()+"</div></td>";
		       		          res+=" <td class='con_address'><div class='inner_text address_div'>"+vfclst.getContactAddress()+"</div></td>";
								 */
								res+="  </tr>";
							}
							
						}
						
					}
				
			}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
		}  
		
		
		
		res+="  </table> ";
		
		obj.put("contacts", res);
		return obj.toJSONString();
		
		
	}
	
	
	
	@RequestMapping(value = "/setGroupSelVal",  produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String getSetGroupSelVal(ModelMap map, Principal principal,
			HttpServletRequest request) {
		HttpSession hs=request.getSession();
		String uid=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String res="<select id='gp_select'>";
		res+="<option value='Personal Contacts'>Personal Contacts</option>";
		 String path="/"+uid+"/Contacts";
	 		GetFolderResponse folderResponse = folderClient.getFolderRequest(path,uid,pass);
	 		List<Folder> folderList = folderResponse.getGetFoldersByParentFolder().getFolderListResult().getFolderList();
	 		for ( Folder fEntry : folderList) {
	        	// System.out.println(fileEntry.getName());
	        	 if(!(fEntry.getFolderName().equalsIgnoreCase("Personal Contacts") || fEntry.getFolderName().equalsIgnoreCase("Collected Contacts") || fEntry.getFolderName().equalsIgnoreCase("Directory")))
	        	 {
	        		 res+="<option value='"+fEntry.getFolderName()+"'>"+fEntry.getFolderName()+"</option>";
	        	 }
	         
	    }
		
		
		 res+="</select>";
		return res;
	}
	
	
	@RequestMapping(value = "/conAllFolderList",  produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String conAllFolderList(ModelMap map, Principal principal,
			HttpServletRequest request) {
		String res1="";
		String res2="";
	/*	
		HttpSession hs=request.getSession();
		String uid=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
	
        String path="/"+uid+"/Contacts";
  		GetFolderResponse folderResponse = folderClient.getFolderRequest(path,uid,pass);
  		List<Folder> folderList = folderResponse.getGetFoldersByParentFolder().getFolderListResult().getFolderList();
 		for ( Folder fEntry : folderList) {
 	        	 if(!(fEntry.getFolderName().equalsIgnoreCase("Personal Contacts") || fEntry.getFolderName().equalsIgnoreCase("Collected Contacts") || fEntry.getFolderName().equalsIgnoreCase("Directory")))
 	        	 {
 	        		if(res2.equals(""))
 	        		{
 	        		 res1+=","+fEntry.getFolderName();
 	        	 }
 	         
 	    }
 		
 		

         path="/"+uid+"/sharedContacts";
         GetSharedFoldersByPathResponse sharedfolderResponse = folderClient.getSharedFoldersByPathRequest(uid, pass, path);//   getSharedFoldersRequest(path.substring(1),pass);
		 if(sharedfolderResponse.getGetSharedFoldersByPath().getFolderListResult()!=null){
	 			List<Folder> sharedfolderList = sharedfolderResponse.getGetSharedFoldersByPath().getFolderListResult().getFolderList();
			for ( Folder fEntry : sharedfolderList) {
			if(res2.equals(""))
			{
				res2=fEntry.getFolderName()	;
			}
			else
			{
				res2+=","+fEntry.getFolderName();
			}
		         
		    }}
		*/
		return res1+"<$nirbhay$>"+res2;
	}
	
	
	
	
	@RequestMapping(value = "/getconPopupList",  produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String getconPopupList(ModelMap map, Principal principal,
			HttpServletRequest request) {
		//System.out.println("!!!!!!!!!!!!!!!!!contacts");
		HttpSession hs=request.getSession();
		String uid=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String res="<select id='conPopupList' onchange='getAllPopupContacts(this.value)'>";
		res+="<option value='/Contacts/Personal Contacts'>Personal Contacts</option>";
		res+="<option value='/Contacts/Collected Contacts'>Collected Contacts</option>";
		res+="<option value='Directory'>Directory</option>";
		
		
		
		

         String path="/"+uid+"/Contacts";
  		GetFolderResponse folderResponse = folderClient.getFolderRequest(path,uid,pass);
  		List<Folder> folderList = folderResponse.getGetFoldersByParentFolder().getFolderListResult().getFolderList();
  		
  		
 		for ( Folder fEntry : folderList) {
 	        	// System.out.println(fileEntry.getName());
 	        	 if(!(fEntry.getFolderName().equalsIgnoreCase("Personal Contacts") || fEntry.getFolderName().equalsIgnoreCase("Collected Contacts") || fEntry.getFolderName().equalsIgnoreCase("Directory")))
 	        	 {
 	        		res+="<option value='/Contacts/"+fEntry.getFolderName()+"'>"+fEntry.getFolderName()+"</option>";
 	        	 }
 	         
 	    }
 		
 		

         path="/"+uid+"/sharedContacts";
 		
         GetSharedFoldersByPathResponse sharedfolderResponse = folderClient.getSharedFoldersByPathRequest(uid, pass, path);//   getSharedFoldersRequest(path.substring(1),pass);
 		
 			
		 if(sharedfolderResponse.getGetSharedFoldersByPath().getFolderListResult()!=null){
	 			List<Folder> sharedfolderList = sharedfolderResponse.getGetSharedFoldersByPath().getFolderListResult().getFolderList();
			for ( Folder fEntry : sharedfolderList) {
			 	
				res+="<option value='/sharedContacts/"+fEntry.getFolderName()+"'>"+fEntry.getFolderName()+"("+fEntry.getCreatedBy()+")</option>";
		         
		    }}
		
    
			res+="</select>";
		return res;
		
	}
	
	
	
	@RequestMapping(value = "/getWbmailContactFolder",  produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String getWbmailContactFolder(ModelMap map, Principal principal,
			HttpServletRequest request) {
		System.out.println("!!!!!!!!!!!!!!!!!contacts");
		HttpSession hs=request.getSession();
		String uid=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String res="<ul>";
		res+=" <li style='cursor: pointer;' onclick='getSelContactUserList(this.id)' id='/Contacts/Personal Contacts' ><img src='images/blank_man.png' class='icon_con' />";
		res+=" <span>Personal Contacts</span> <div class='clear'></div></li>";
         res+=" <li style='cursor: pointer;'  onclick='getSelContactUserList(this.id)' id='/Contacts/Collected Contacts' >    <img src='images/colt_con.png' class='collect_con col_con' />";
         res+=" <span>Collected Contacts</span>   <div class='clear'></div>    </li>";
         res+=" <li style='cursor: pointer;'  onclick='getSelContactUserList(this.id)' id='Directory' onclick='getDirectoryUserList()'> <img src='images/contact_dir.png' class='icon_con col_con' />";
         res+="<span>Directory</span>    <div class='clear'></div>  </li>";
		
		
		

         String path="/"+uid+"/Contacts";
  		GetFolderResponse folderResponse = folderClient.getFolderRequest(path,uid,pass);
  		List<Folder> folderList = folderResponse.getGetFoldersByParentFolder().getFolderListResult().getFolderList();
  		
  		
 		for ( Folder fEntry : folderList) {
 	        	// System.out.println(fileEntry.getName());
 	        	 if(!(fEntry.getFolderName().equalsIgnoreCase("Personal Contacts") || fEntry.getFolderName().equalsIgnoreCase("Collected Contacts") || fEntry.getFolderName().equalsIgnoreCase("Directory")))
 	        	 {
 	        		/* res+="<li style='cursor: pointer;'  onclick='getSelContactUserList(this.id)' id='/Contacts/"+fEntry.getFolderName()+"'> <img src='images/group_con.png' class='icon_con col_con group_img'> <span>"+fEntry.getFolderName()+"</span><img id='hidcon_/Contacts/"+fEntry.getFolderName()+"' onclick='delConFldr(this.id)' src='images/tool.png' class='delete_contact_row'><div class='clear'></div></li>";*/
 	        		 res+="<li style='cursor: pointer;'  onclick='getSelContactUserList(this.id)' id='/Contacts/"+fEntry.getFolderName()+"'> <img src='images/group_con.png' class='icon_con col_con group_img'> <span>"+fEntry.getFolderName()+"</span><div class='clear'></div></li>";
 	        	 }
 	         
 	    }
 		
 		

         path="/"+uid+"/sharedContacts";
 		// folderResponse = folderClient.getFolderRequest(path,uid,pass);
 		// folderList = folderResponse.getGetFoldersByParentFolder().getFolderListResult().getFolderList();
 		
 		
         GetSharedFoldersByPathResponse sharedfolderResponse = folderClient.getSharedFoldersByPathRequest(uid, pass, path);//   getSharedFoldersRequest(path.substring(1),pass);
 		
 		res+="</ul>";
		 res+="<$nirbhay$><ul>";
		
		 if(sharedfolderResponse.getGetSharedFoldersByPath().getFolderListResult()!=null){
	 			List<Folder> sharedfolderList = sharedfolderResponse.getGetSharedFoldersByPath().getFolderListResult().getFolderList();
			for ( Folder fEntry : sharedfolderList) {
			String nm="notmanage";
				if(fEntry.getUserSecurity().toString().contains(uid))
				{
					 nm="manage";
				}
		        	// System.out.println(fileEntry.getName());
		        //	 if(!(fEntry.getFolderName().equalsIgnoreCase("Personal Contacts") || fEntry.getFolderName().equalsIgnoreCase("Collected Contacts") || fEntry.getFolderName().equalsIgnoreCase("Directory")))
		        //	 {
		        		 res+="<li style='cursor: pointer;' name='"+nm+"'  onclick='getSelContactUserList(this.id)' id='/sharedContacts/"+fEntry.getFolderName()+"'> <img src='images/group_con.png' class='icon_con col_con group_img'> <span title='"+fEntry.getFolderName()+"("+fEntry.getCreatedBy()+")'>"+fEntry.getFolderName()+"("+fEntry.getCreatedBy()+")</span><div class='clear'></div></li>";
		        	// }
		         
		    }}
		/* for ( Folder fEntry : folderList) {
	        	// System.out.println(fileEntry.getName());
	        	
	        		 res+="<li style='cursor: pointer;'  onclick='getSelContactUserList(this.id)' id='/Contacts/"+fEntry.getFolderName()+"'> <img src='images/group_con.png' class='icon_con col_con group_img'> <span>"+fEntry.getFolderName()+"</span><img id='hidcon_/Contacts/"+fEntry.getFolderName()+"' onclick='delConFldr(this.id)' src='images/tool.png' class='delete_contact_row'><div class='clear'></div></li>";
	        		 res+="<li style='cursor: pointer;'  onclick='getSelContactUserList(this.id)' id='/sharedContacts/"+fEntry.getFolderName()+"'> <img src='images/group_con.png' class='icon_con col_con group_img'> <span>"+fEntry.getFolderName()+"</span><div class='clear'></div></li>";
	        	
	         
	    }*/
    
			res+="</ul>";
		return res;
		
	}
	
	@RequestMapping(value = "/shareConFldr",  produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String shareConFldr(ModelMap map, Principal principal,
			HttpServletRequest request) {
		
		String shareid=request.getParameter("shareid") ;
		String confldr=request.getParameter("confldr") ;
		
		HttpSession hs=request.getSession();
		String uid=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		System.out.println("current folder or file value is : "+confldr);
	    AssignSinglePermissionResponse response = folderClient
	            .assignSinglePermission("/"+uid+confldr, uid,pass, shareid+"/sharedContacts", "uw");
	    String resp = response.getAssignSinglePermissionResponse();
		return resp;
		
	}
	
	
	@RequestMapping(value = "/delConFldr",  produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String delConFldr(ModelMap map, Principal principal,
			HttpServletRequest request) {
		String resp="";
		String confldr=request.getParameter("conid") ;
		HttpSession hs=request.getSession();
		String uid=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		DeleteFileResponse resF=fileClient.deleteFile("/"+uid+confldr, uid,pass);
		
		hs.setAttribute("active_contact", "/Contacts/Personal Contacts");
		return resp;
		
	}
	
	
	@RequestMapping(value = "/deleteSelContacts",  produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String deleteSelContacts(ModelMap map, Principal principal,
			HttpServletRequest request) {
		String resp="";
		
		String confile=request.getParameter("con_file") ;
		//System.out.println(confile);
		if(confile!=null && !confile.equals(""))
		{
		String array_del[]=confile.split(",");
		HttpSession hs=request.getSession();
		String uid=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		for(int i=0;i< array_del.length;i++)
		{
		fileClient.deleteFile(array_del[i], uid, pass);
		}
		}
		
		return resp;
		
	}
	
	
	
	@RequestMapping(value = "/exportVCFFile", produces = "text/html; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String exportVCFFile(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		String res="";
		// 'group_name': fldr, 'export_type': selectedVal, 'con_arr' : JSON.stringify(con_arr)
		String exp_type=request.getParameter("export_type");
		String group_name=request.getParameter("group_name");
		//String con_arr[]=request.getParameterValues("con_arr");
		String con_str=request.getParameter("con_arr");
		HttpSession hs=request.getSession();
		String uid=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		if(exp_type.equalsIgnoreCase("contact"))
		{
			if(con_str!=null && con_str.length()>4)
			{
				try
				{
					con_str=con_str.replace("[\"", "");
					con_str=con_str.replace("\"]", "");
					String con_arr[]=con_str.split("\",\"");
					
					 FileOutputStream fileOuputStream = new FileOutputStream("D:\\new3.txt"); 
					
					for(int i=0;i<con_arr.length;i++)
					{
						GetFileByPathResponse fpr= fileClient.getFileByPath(con_arr[i], uid, pass);
						byte[] fl= fpr.getFile().getFileContent();
						fileOuputStream.write(fl);
					}
				
					fileOuputStream.close();
			
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		else if(exp_type.equalsIgnoreCase("group"))
		{
			try
			{
			GetVCFFileResponse fileResponse =fileClient.getVCFFileRequest("/"+uid+"/Contacts/"+group_name, uid,pass);
			List<VCFFileAtt> vcffileList=fileResponse.getGetVCFFilesByParentFile().getVCFFileListResult().getVCFFileList();
			FileOutputStream fileOuputStream = new FileOutputStream("D:\\new3.txt"); 
			for(VCFFileAtt vfclst: vcffileList )
			{
				GetFileByPathResponse fpr= fileClient.getFileByPath("/"+uid+"/Contacts/"+group_name+"/"+vfclst.getContactFileName(), uid,pass);
				byte[] fl= fpr.getFile().getFileContent();
				fileOuputStream.write(fl);
			}
			fileOuputStream.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(exp_type.equalsIgnoreCase("all"))
		{
			try
			{
			GetFolderResponse folderResponse = folderClient.getFolderRequest("/"+uid+"/Contacts",uid,pass);
    		List<Folder> folderList = folderResponse.getGetFoldersByParentFolder().getFolderListResult().getFolderList();
    		FileOutputStream fileOuputStream = new FileOutputStream("D:\\new3.txt"); 
    		for(Folder fEntry : folderList)
				{
    			GetVCFFileResponse fileResponse =fileClient.getVCFFileRequest("/"+uid+"/Contacts/"+fEntry.getFolderName(), uid,pass);
    			List<VCFFileAtt> vcffileList=fileResponse.getGetVCFFilesByParentFile().getVCFFileListResult().getVCFFileList();
    			for(VCFFileAtt vfclst: vcffileList )
    			{
    				GetFileByPathResponse fpr= fileClient.getFileByPath("/"+uid+"/Contacts/"+fEntry.getFolderName()+"/"+vfclst.getContactFileName(), uid,pass);
    				byte[] fl= fpr.getFile().getFileContent();
    				fileOuputStream.write(fl);
    			}
				}
    		fileOuputStream.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		return res;
	}
	
	
	@RequestMapping(value = "/exportVCFDownload", produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	public void exportVCFDownload(ModelMap map, Principal principal, HttpServletRequest request,HttpServletResponse response) 
	{
		String res="";
		// 'group_name': fldr, 'export_type': selectedVal, 'con_arr' : JSON.stringify(con_arr)
		String group_name=request.getParameter("fldr");
		
		HttpSession hs=request.getSession();
		String uid=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		
		
			try
			{
				GetVCFFileResponse fileResponse =fileClient.getVCFFileRequest("/"+uid+group_name, uid,pass);
				List<VCFFileAtt> vcffileList=fileResponse.getGetVCFFilesByParentFile().getVCFFileListResult().getVCFFileList();
				// UUID uuid = UUID.randomUUID();
			   //  String randomUUIDString = uuid.toString();
			     String nm="contacts.vcf";
				//String outPath="C:\\Users\\nirbhay\\AppData\\Local\\Temp\\"+nm;
				//String outPath="/tmp/tomcat7-tomcat7-tmp/"+nm;
			     String realPath = request.getServletContext().getRealPath("/");
			     String outPath  = realPath+"WEB-INF/view/jsp/temp/"+nm;
	
				FileOutputStream fileOuputStream = new FileOutputStream(outPath); 
				for(VCFFileAtt vfclst: vcffileList )
				{
					GetFileByPathResponse fpr= fileClient.getFileByPath("/"+uid+group_name+"/"+vfclst.getContactFileName(), uid,pass);
					byte[] fl=org.apache.commons.codec.binary.Base64.decodeBase64(fpr.getFile().getFileContent());
					fileOuputStream.write(fl);
				}
				fileOuputStream.close();
				
				 String headerKey = "Content-Disposition";
	             String headerValue = String.format("attachment; filename=\"%s\"",  nm);
	             response.setHeader(headerKey, headerValue);
	             InputStream input = new FileInputStream(outPath);
	             OutputStream output = response.getOutputStream();
	             byte[] buffer = new byte[4096];
	
	             int byteRead;
	
	             while ((byteRead = input.read(buffer)) != -1) {
	                output.write(buffer, 0, byteRead);
	             }
	             input.close();
	             output.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	//	return "exportVCFDownload";
	}
	
@RequestMapping(value = "/sharingPopupCon",  produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	
	public String sharingPopupCon(ModelMap map, HttpServletRequest request)
	{
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String act_fldr=request.getParameter("path");
		String folderName="/"+id+act_fldr;
		
		       
        GetFolderByPathResponse folderByPath = folderClient.getFolderByPath(folderName, id,pass);
        map.addAttribute("currentFolder",folderByPath.getFolder());
		        
//        GetUsersListResponse response = fileClient.getUsersListRequest(id,pass);
//        map.addAttribute("userlist", (Object)response.getUsersList());   
	
		return "contactshare";
	}

@RequestMapping("/assignSinglePermission")
@ResponseBody
public String assignSinglePermission(ModelMap map, Principal principal,
        @RequestParam String user, @RequestParam String value,@RequestParam String doctype,@RequestParam String multipath,
        HttpServletRequest request) {
    try
    {
    	HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String userid=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String resp="";
   
		//String[] str=multipath.split(",");
		//  for (int i = 0; i < str.length; i++) {
	    if(!multipath.equals(""))
	    {
	    	multipath="/"+userid+multipath;
	        AssignSinglePermissionResponse response = folderClient.assignSinglePermission(multipath, userid,pass, user, value);
	         resp = response.getAssignSinglePermissionResponse();
	         //MailUtils.sendComposeMail("192.168.0.53", "25", "edms@avi-oil.com", "google@2009", userid, "Shared with me", "dsdd");
	       // }
	    }    
	    //MailUtils.sendComposeMail("192.168.0.53", "25", "edms@avi-oil.com", "google@2009", userid, "Shared with me", "This is mail to inform you that someone have shared document with you.");
	    //MailUtils.sendComposeMail("mail.silvereye.in", "25", "rohit@silvereye.in", "yahoo@2009", "nirbhay@silvereye.in", "Shared with me", "This is mail to inform you that someone have shared document with you.");
	    return resp;
   
	}catch(Exception e)
    {
	
	    return "ajaxTrue";
	}
}


@RequestMapping("/removeAssignedPermission")
@ResponseBody
public String removeAssignedPermission( HttpServletRequest request, ModelMap map, Principal principal,@RequestParam String folderPath,@RequestParam String value,@RequestParam String user)
{
	HttpSession hs=request.getSession();
	String host=(String)hs.getAttribute("host");
	String userid=(String)hs.getAttribute("id");
	String pass=(String)hs.getAttribute("pass");
    try
    {
    	String resp="";
    
    	// if(principal==null){return "ajaxTrue";}else{
    	/*if(principal.getName().contains("@")){
        userid=principal.getName();
        }else{
            userid=principal.getName()+Config.EDMS_DOMAIN;
        }*/
        RemoveAssignedPermissionResponse response = fileClient.removeAssignedPermission(folderPath, userid,pass, user, value);
        resp = response.getAssignSinglePermissionResponse();
        //MailUtils.sendComposeMail("192.168.0.53", "25", "edms@avi-oil.com", "google@2009", userid, "Shared with me", "This is mail to inform you that someone have shared document with you.");
        return resp;
    //}
	}
    catch(Exception e){
	
	    return "ajaxTrue";
	}
}

	
}



