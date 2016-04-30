package webmail.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




import org.springframework.web.servlet.ModelAndView;

import webmail.bean.NPCompare;
import webmail.bean.TaskCompare;
import webmail.webservice.client.CalendarClient;
import webmail.webservice.client.FileClient;
import webmail.webservice.client.WebmailClient;
import webmail.wsdl.CreateCalendarResponse;
import webmail.wsdl.CreateFileResponse;
import webmail.wsdl.DeleteSelectedTaskResponse;
import webmail.wsdl.DeleteTaskResponse;
import webmail.wsdl.EditFileResponse;
import webmail.wsdl.GetAddTaskResponse;
import webmail.wsdl.GetFileByPathResponse;
import webmail.wsdl.GetTaskDetailResponse;
import webmail.wsdl.GetTasksResponse;
import webmail.wsdl.GetUpdateTaskResponse;
import webmail.wsdl.GetWebmailImapquotaResponse;
import webmail.wsdl.Imapquota;
import webmail.wsdl.TaskArray;
import webmail.wsdl.TaskBean;


@Controller
public class TaskController {

	
	@Autowired
	WebmailClient webmailClient;
	
	@Autowired
	private FileClient fileClient;
	
	@Autowired
	private CalendarClient calendarclient;
	
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public ModelAndView mycalendar(ModelMap map, HttpServletRequest request) 
	{
		HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		
		
		System.out.println("*************ses="+id);
		if(id==null)
		{
			//ses="SesExp";
			return new ModelAndView("redirect:/login?stat=ses", map);
		}
		
		String pass=(String)hs.getAttribute("pass");
		String port=(String)hs.getAttribute("port");
		String fdrnm=(String)hs.getAttribute("active_folder");
		GetWebmailImapquotaResponse wfresponse=webmailClient.getWebmailImapquotaRequest(host, id, pass);
		Imapquota iqt  =wfresponse.getImapquota();
		System.out.println("*********iqt********Qlota="+ iqt.getQuotalimit());
		long ql= iqt.getQuotalimit();
		long qu=iqt.getQuotauses();
		long qper=0l;
		if(ql>0)
		qper=(qu*100)/ql;
		//System.out.println("********************Quotalimi="+ per
		//System.out.println("********************Quota per="+qper);
		//GetWebmailAllMailCountResponse wfre=folderClient.getWebmailAllMailCountRequest(host, port, id, pass,fdrnm);
		//map.addAttribute("MailCount", ""+(wfre.getAllemailcount()));
		map.addAttribute("QuotaPer", ""+qper);
		map.addAttribute("QuotaLimit", ""+ql);
		map.addAttribute("QuotaUses", ""+qu); 
		map.addAttribute("createTask", new TaskBean());
		 /*String path="/"+id+"/Contacts";
	 		GetFolderResponse folderResponse = folderClient.getFolderRequest(path,id);
	 		List<Folder> folderList = folderResponse.getGetFoldersByParentFolder().getFolderListResult().getFolderList();*/
	 	
		GetFileByPathResponse fileByPath=fileClient.getFileByPath("/"+id+"/Task/"+id+"_task.ics",id,pass);
	    webmail.wsdl.File fileNode=fileByPath.getFile();
	    if(fileNode.getFileContent() != null)
	    {
	    	GetTasksResponse taskres = webmailClient.buildTask(fileNode.getFileContent(), id+"_task.ics");
	    	List<TaskBean> taskarray  = taskres.getTaskList().getTaskList();
	    	Collections.sort(taskarray,new TaskCompare());
	    	map.addAttribute("taskList", taskarray);
	    }
	    else
	    {
	    	CreateCalendarResponse res = calendarclient.createCalendar(id,"","PUBLISH");
	    	String filename=id+"_task.ics";
			System.out.println(filename);

			CreateFileResponse cr = fileClient.createFile(filename,"/"+id+"/Task", id,pass, "", "",res.getResponse(),0);
			System.out.println(cr.isSuccess());
			map.addAttribute("taskList", new ArrayList<TaskBean>());
	    }
	    	
		return new ModelAndView("tasks", map);
		
	}
	
	@RequestMapping(value = "/createTask", method = RequestMethod.GET)
	@ResponseBody
	public String createTask(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		HttpSession hs=request.getSession();
		String id=(String)hs.getAttribute("id");
//		System.out.println("User id : " + id);
		String pass=(String)hs.getAttribute("pass");
		String tsk_detail = request.getParameter("tsk_detail");
		String startDate = request.getParameter("startDate");
		String startTime = request.getParameter("startTime");
		String endDate = request.getParameter("endDate");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		String tsk_priority = request.getParameter("tsk_priority");
		String taskDesc = request.getParameter("taskDesc");
		String tsk_progress = request.getParameter("tsk_progress");
//		-------------hidden time --------------
		startTime="00:00am";
		endTime="00:00am";
		
//		System.out.println("AQQQQQQQQQQQQQQQQQQQQQQQQQQQQtsk_detail"+tsk_detail+"startDate"+startDate+"startTime"+startTime+"endDate"+endDate+"endTime"+endTime+"status"+status+"tsk_priority"+tsk_priority+"taskDesc"+taskDesc);
		JSONObject obj = new JSONObject();
		if(tsk_detail != null && tsk_detail.length() != 0 && status != null &&  status.length() != 0 && tsk_priority != null && tsk_priority.length() != 0)
		{
//			System.out.println("file node : " );
					
			
			
			
		    GetFileByPathResponse fileByPath=fileClient.getFileByPath("/"+id+"/Task/"+id+"_task.ics",id,pass);
		    webmail.wsdl.File fileNode=fileByPath.getFile();
		    
		    TaskBean taskBean = new TaskBean();
		    taskBean.setDetail(tsk_detail);
		    taskBean.setTaskdesc(taskDesc);
		    taskBean.setStatus(status);
		    
		    try
		    {
//		    	tsk_progress = tsk_progress.trim().replace("%", "");
		    	taskBean.setProgress(Integer.parseInt(tsk_progress));
		    	taskBean.setPriority(Integer.parseInt(tsk_priority));
		    }
		    catch(NumberFormatException e)
		    {
		    	e.printStackTrace();
		    }
		    try 
		    {
		    	if(startTime.length() == 6)
		    	{
		    		startTime = "0"+startTime;
		    	}
		    	if(endTime.length() == 6)
		    	{
		    		endTime = "0"+endTime;
		    	}
		    	
		    	if(startTime.contains("am"))
		    	{
		    		startTime = startTime.replace("am", "");
		    		startTime = startTime +":00+05:30";
		    		
		    	}
		    	else
		    	{
		    		startTime = startTime.replace("pm", "");
		    		String[] tm = startTime.split(":");
		    		int hour = Integer.parseInt(tm[0]);
		    		startTime = String.valueOf(hour+12)+":"+tm[1]+":00+05:30";
		    	}
		    	if(endTime.contains("am"))
		    	{
		    		endTime = endTime.replace("am", "");
//		    		endTime = endTime.replace(":", "");
		    		endTime = endTime +":00+05:30";
		    		
		    	}
		    	else
		    	{
		    		endTime = endTime.replace("pm", "");
		    		String[] tm = endTime.split(":");
		    		int hour = Integer.parseInt(tm[0]);
		    		endTime = String.valueOf(hour+12)+":"+tm[1]+":00+05:30";
		    	}
		    	startDate = startDate+"T"+startTime;
		    	endDate = endDate+"T"+endTime;
		    	XMLGregorianCalendar stcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(startDate);
		    	XMLGregorianCalendar endcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(endDate);
		    	if(stcal.toGregorianCalendar().getTime().getTime() < endcal.toGregorianCalendar().getTime().getTime())
		    	{
		    		taskBean.setStartdate(stcal);
		    		taskBean.setEnddate(endcal);
		    		GetAddTaskResponse response = webmailClient.addTaskRequest(taskBean, fileNode.getFileContent());
					if(response.getUid() != null && response.getUid().length() != 0 )
					{
						EditFileResponse res2 = fileClient.editEvent(fileNode.getFilePath(),response.getResponse(),id,pass);
						obj.put("success", "true");
						return obj.toJSONString();
					}
		    	}
		    	else
		    	{
		    		obj.put("enddateerror", "true");
		    		return obj.toJSONString();
		    	}
				
				
			}
		    catch (DatatypeConfigurationException e1) 
		    {
				e1.printStackTrace();
				obj.put("success", "false");
				return obj.toJSONString();
			}
		    catch (Exception e) 
		    {
				e.printStackTrace();
				obj.put("success", "false");
				return obj.toJSONString();
			}
		    
			
		    obj.put("success", "false");
		}
		return obj.toJSONString();
	}
	
	@RequestMapping(value = "/loadTaskList", method = RequestMethod.GET)
	@ResponseBody
	public String loadTaskList(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		HttpSession hs=request.getSession();
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
//		System.out.println("User id : " + id);
		String pagecount  = request.getParameter("pagecount");
		System.out.println("in loadTaskList  pagecount : " + pagecount);
		GetFileByPathResponse fileByPath=fileClient.getFileByPath("/"+id+"/Task/"+id+"_task.ics",id,pass);
	    webmail.wsdl.File fileNode=fileByPath.getFile();
	    JSONObject res = new JSONObject();
	    if(fileNode.getFileContent() != null)
	    {
	    	GetTasksResponse taskres = webmailClient.buildTask(fileNode.getFileContent(), id+"_task.ics");
	    	List<TaskBean> taskarray  = taskres.getTaskList().getTaskList();
	    	Collections.sort(taskarray,new TaskCompare());
	    	int totalTask = taskarray.size();
	    	res.put("totaltask", totalTask);
	    	JSONArray jsonArray = new JSONArray();
	    	if(!taskarray.isEmpty() && pagecount != null && pagecount.length() != 0)
	    	{
	    		JSONObject obj = null;
	    		String startDate = "";
	    		String endDate = "";
	    		String modifyDate = "";
	    		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
	    		int pcount = 1;
	    		try
	    		{
	    			pcount = Integer.parseInt(pagecount);
	    		}
	    		catch(NumberFormatException e)
	    		{
	    			e.printStackTrace();
	    		}
	    		if(pcount > 0  && totalTask  > (pcount-1)*20 )
	    		{
	    			res.put("pagecount", pcount);
	    			
	    			int count =  0;
	    			for(int i = (pcount-1)*20 ; i < taskarray.size() && count < 20 ; i ++, count++ )
	    			{
	    				TaskBean task = taskarray.get(i);
	    				obj = new JSONObject();
	    				obj.put("uid", task.getUid());
	    				obj.put("detail", task.getDetail());
	    				
	    				startDate  = task.getStartdate().toString();
	    				startDate = startDate.substring(0, 10);
	    				
	    				try
	    				{
	    					Date st = dateFormat1.parse(startDate);
	    					startDate = dateFormat.format(st);
	    					
	    				}
	    				catch(ParseException e)
	    				{
	    					e.printStackTrace();
	    				}
	    				
	    				endDate  = task.getEnddate().toString();
	    				endDate = endDate.substring(0, 10);
	    				
	    				try
	    				{
	    					Date st = dateFormat1.parse(endDate);
	    					endDate = dateFormat.format(st);
	    					
	    				}
	    				catch(ParseException e)
	    				{
	    					e.printStackTrace();
	    				}
	    				if(task.getModifydate() != null)
	    				{
	    					modifyDate  = task.getModifydate().toString();
	    					modifyDate = modifyDate.substring(0, 10);
	    					try
	    					{
	    						
	    						Date md = dateFormat1.parse(modifyDate);
	    						modifyDate = dateFormat.format(md);
	    					}
	    					catch(ParseException e)
	    					{
	    						e.printStackTrace();
	    					}
	    				}
	    				else
	    				{
	    					modifyDate =  "Not Modified";
	    				}
	    				
	    				
	    				obj.put("startDate", startDate);
	    				obj.put("endDate", endDate);
	    				obj.put("modifyDate", modifyDate);
	    				obj.put("status", task.getStatus());
	    				
	    				jsonArray.add(obj);
	    			}
	    			
	    		}
	    	}
	    	res.put("tasklist", jsonArray);
	    	
	    }
		return res.toJSONString();
	}

	@RequestMapping(value = "/deleteTask", method = RequestMethod.GET)
	@ResponseBody
	public String deleteTask(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		HttpSession hs=request.getSession();
		String id=(String)hs.getAttribute("id");
		
		String uid = request.getParameter("uid");
		uid = uid.substring(4);
//		System.out.println("Uid : " + uid);
		String pass=(String)hs.getAttribute("pass");
		GetFileByPathResponse fileByPath=fileClient.getFileByPath("/"+id+"/Task/"+id+"_task.ics",id,pass);
	    webmail.wsdl.File fileNode=fileByPath.getFile();
		DeleteTaskResponse response = webmailClient.deleteTask(fileNode.getFileContent(), uid);
		EditFileResponse res2 = fileClient.editEvent(fileNode.getFilePath(),response.getResponse(),id,pass);
		JSONObject obj = new JSONObject();
		if(response.isTaskdeleted())
		{
			obj.put("success", "true");
		}
		
		return obj.toJSONString();
	}
	
	@RequestMapping(value = "/deleteSelectedTask", method = RequestMethod.GET)
	@ResponseBody
	public String deleteSelectedTask(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		HttpSession hs=request.getSession();
		String id=(String)hs.getAttribute("id");
		
		String uid = request.getParameter("uid");
//		System.out.println("Uid : " + uid);
		String pass=(String)hs.getAttribute("pass");
		GetFileByPathResponse fileByPath=fileClient.getFileByPath("/"+id+"/Task/"+id+"_task.ics",id,pass);
	    webmail.wsdl.File fileNode=fileByPath.getFile();
	    DeleteSelectedTaskResponse response = webmailClient.deleteSelectedTask(fileNode.getFileContent(), uid);
		EditFileResponse res2 = fileClient.editEvent(fileNode.getFilePath(),response.getResponse(),id,pass);
		JSONObject obj = new JSONObject();
		if(response.isTaskdeleted())
		{
			obj.put("success", "true");
		}
		
		
		return obj.toJSONString();
	}
	
	@RequestMapping(value = "/getTaskDetailByUid", method = RequestMethod.GET)
	@ResponseBody
	public String getTaskDetailByUid(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		HttpSession hs=request.getSession();
		String id=(String)hs.getAttribute("id");
//		System.out.println("User id : " + id);
		String pass=(String)hs.getAttribute("pass");
		String uid = request.getParameter("uid");
		JSONObject obj = new JSONObject();
		if(uid != null && uid.length() != 0)
		{
			GetFileByPathResponse fileByPath=fileClient.getFileByPath("/"+id+"/Task/"+id+"_task.ics",id,pass);
		    webmail.wsdl.File fileNode=fileByPath.getFile();
		    uid =uid.substring(4);
		    GetTaskDetailResponse response = webmailClient.getTaskDetail(fileNode.getFileContent(), uid);
		    List<TaskBean> taskList = response.getTaskList();
		    if(!taskList.isEmpty())
		    {
		    	TaskBean task = taskList.get(0);
		    	obj.put("uid", task.getUid());
				obj.put("detail", task.getDetail());
				obj.put("status", task.getStatus());
				obj.put("priority", String.valueOf(task.getPriority()));
				obj.put("progress", task.getProgress());
				obj.put("taskDesc", task.getTaskdesc());
				
				
 				XMLGregorianCalendar startcal = task.getStartdate();
 				if(startcal != null)
 				{
 					String mon = String.valueOf(startcal.getMonth());
 					String day = String.valueOf(startcal.getDay());
 					if(startcal.getMonth() <10)
 					{
 						mon = "0"+startcal.getMonth();
 					}
 					if(startcal.getDay() <10)
 					{
 						day = "0"+startcal.getDay();
 					}
 					obj.put("startDate", startcal.getYear()+"-"+mon+"-"+day);
 					
 					if(startcal.getHour() <12)
 					{
 						String hr = String.valueOf(startcal.getHour());
 						if(startcal.getHour() <10)
 						{
 							hr = ""+startcal.getHour();
 						}
 						String mn = String.valueOf(startcal.getMinute());
 						if(startcal.getMinute() <10)
 						{
 							mn = "0"+startcal.getMinute();
 						}
 						obj.put("startTime", hr + ":"+mn+"am");
 						
 					}
 					else
 					{
 						String hr = "";
 						if(startcal.getHour()-12 <10)
 						{
 							hr = ""+(startcal.getHour()-12);
 						}
 						else
 						{
 							hr = String.valueOf(startcal.getHour()-12);
 						}
 						String mn = String.valueOf(startcal.getMinute());
 						if(startcal.getMinute() <10)
 						{
 							mn = "0"+startcal.getMinute();
 						}
 						obj.put("startTime", hr + ":"+mn+"pm");
 						
 					}
 				}
 				XMLGregorianCalendar endcal = task.getEnddate();
 				if(endcal != null)
 				{
 					String mon = String.valueOf(endcal.getMonth());
 					String day = String.valueOf(endcal.getDay());
 					if(endcal.getMonth() <10)
 					{
 						mon = "0"+endcal.getMonth();
 					}
 					if(endcal.getDay() <10)
 					{
 						day = "0"+endcal.getDay();
 					}
 					obj.put("endDate", endcal.getYear()+"-"+mon+"-"+day);
 					
 					if(endcal.getHour() <12)
 					{
 						String hr = String.valueOf(endcal.getHour());
 						if(endcal.getHour() <10)
 						{
 							hr = ""+endcal.getHour();
 						}
 						String mn = String.valueOf(endcal.getMinute());
 						if(endcal.getMinute() <10)
 						{
 							mn = "0"+endcal.getMinute();
 						}
 						obj.put("endTime", hr + ":"+mn+"am");
 						
 					}
 					else
 					{
 						String hr = "";
 						if(endcal.getHour()-12 <10)
 						{
 							hr = ""+(endcal.getHour()-12);
 						}
 						else
 						{
 							hr = String.valueOf(endcal.getHour()-12);
 						}
 						String mn = String.valueOf(endcal.getMinute());
 						if(endcal.getMinute() <10)
 						{
 							mn = "0"+endcal.getMinute();
 						}
 						obj.put("endTime", hr + ":"+mn+"pm");
 						
 					}
 					
 				}
 				XMLGregorianCalendar modcal = task.getModifydate();
 				if(modcal != null)
 				{
 					obj.put("endDate", modcal.getYear()+"-"+modcal.getMonth()+"-"+modcal.getDay());
 					
 				}
 				
 				obj.put("success", "true");
				
				
		    }
		}
		return obj.toJSONString();
	}
	
	
	@RequestMapping(value = "/updateTask", method = RequestMethod.GET)
	@ResponseBody
	public String updateTask(ModelMap map, Principal principal, HttpServletRequest request) 
	{
		HttpSession hs=request.getSession();
		String id=(String)hs.getAttribute("id");
//		System.out.println("User id : " + id);
		String pass=(String)hs.getAttribute("pass");
		String tsk_detail = request.getParameter("tsk_detail");
		String startDate = request.getParameter("startDate");
		String startTime = request.getParameter("startTime");
		String endDate = request.getParameter("endDate");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		String tsk_priority = request.getParameter("tsk_priority");
		String taskDesc = request.getParameter("taskDesc");
		String tsk_progress = request.getParameter("tsk_progress");
		String uid = request.getParameter("uid");
//		System.out.println("AQQQQQQQQQQQQQQQQQQQQQQQQQQQQtsk_detail"+tsk_detail+"startDate"+startDate+"startTime"+startTime+"endDate"+endDate+"endTime"+endTime+"status"+status+"tsk_priority"+tsk_priority+"taskDesc"+taskDesc);
		JSONObject obj = new JSONObject();
		if(uid != null && uid.length() != 0 &&tsk_detail != null && tsk_detail.length() != 0 && status != null &&  status.length() != 0 && tsk_priority != null && tsk_priority.length() != 0)
		{
//			System.out.println("file node : " );
					
			
			
			
		    GetFileByPathResponse fileByPath=fileClient.getFileByPath("/"+id+"/Task/"+id+"_task.ics",id,pass);
		    webmail.wsdl.File fileNode=fileByPath.getFile();
		    
		    TaskBean taskBean = new TaskBean();
		    
		    taskBean.setUid(uid);
		    taskBean.setDetail(tsk_detail);
		    taskBean.setTaskdesc(taskDesc);
		    taskBean.setStatus(status);
		    
		    try
		    {
//		    	tsk_progress = tsk_progress.trim().replace("%", "");
		    	taskBean.setProgress(Integer.parseInt(tsk_progress));
		    	taskBean.setPriority(Integer.parseInt(tsk_priority));
		    }
		    catch(NumberFormatException e)
		    {
		    	e.printStackTrace();
		    }
		    try 
		    {
		    	if(startTime.length() == 6)
		    	{
		    		startTime = "0"+startTime;
		    	}
		    	if(endTime.length() == 6)
		    	{
		    		endTime = "0"+endTime;
		    	}
		    	
		    	if(startTime.contains("am"))
		    	{
		    		startTime = startTime.replace("am", "");
		    		startTime = startTime +":00+05:30";
		    		
		    	}
		    	else
		    	{
		    		startTime = startTime.replace("pm", "");
		    		String[] tm = startTime.split(":");
		    		int hour = Integer.parseInt(tm[0]);
		    		startTime = String.valueOf(hour+12)+":"+tm[1]+":00+05:30";
		    	}
		    	if(endTime.contains("am"))
		    	{
		    		endTime = endTime.replace("am", "");
		    		endTime = endTime +":00+05:30";
		    		
		    	}
		    	else
		    	{
		    		endTime = endTime.replace("pm", "");
		    		String[] tm = endTime.split(":");
		    		int hour = Integer.parseInt(tm[0]);
		    		endTime = String.valueOf(hour+12)+":"+tm[1]+":00+05:30";
		    	}
		    	startDate = startDate+"T"+startTime;
		    	endDate = endDate+"T"+endTime;
		    	XMLGregorianCalendar stcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(startDate);
		    	XMLGregorianCalendar endcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(endDate);
		    	if(stcal.toGregorianCalendar().getTime().getTime() < endcal.toGregorianCalendar().getTime().getTime())
		    	{
		    		taskBean.setStartdate(stcal);
		    		taskBean.setEnddate(endcal);
		    		
		    		GetUpdateTaskResponse response = webmailClient.getUpdateTask(taskBean, fileNode.getFileContent());
		    		if(response.isUpdateSuccess())
		    		{
		    			EditFileResponse res2 = fileClient.editEvent(fileNode.getFilePath(),response.getResponse(),id,pass);
		    			obj.put("success", "true");
		    			return obj.toJSONString();
		    		}
		    		
		    	}
		    	else
		    	{
		    		obj.put("enddateerror", "true");
		    		return obj.toJSONString();
		    	}
				
			}
		    catch (DatatypeConfigurationException e1) 
		    {
				e1.printStackTrace();
				obj.put("success", "false");
				return obj.toJSONString();
			}
		    catch (Exception e) 
		    {
				e.printStackTrace();
				obj.put("success", "false");
				return obj.toJSONString();
			}
		    
			
		    obj.put("success", "false");
//			System.out.println("JSON Object : " + obj.toJSONString());
		}
		return obj.toJSONString();
	}
	
	
}
