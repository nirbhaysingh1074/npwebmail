package webmail.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.server.UID;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.json.stream.JsonParser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import commons.JsonHelper;
import webmail.webservice.client.CalendarClient;
import webmail.webservice.client.FileClient;
import webmail.webservice.client.FolderClient;
import webmail.webservice.client.WebmailClient;
import webmail.wsdl.AssignSinglePermissionResponse;
import webmail.wsdl.CalendarBean;
import webmail.wsdl.ChangeColorResponse;
import webmail.wsdl.CreateCalendarRequest;
import webmail.wsdl.CreateCalendarResponse;
import webmail.wsdl.CreateEventResponse;
import webmail.wsdl.CreateFileResponse;
import webmail.wsdl.DeleteFileResponse;
import webmail.wsdl.DeleteTaskResponse;
import webmail.wsdl.EditFileRes;
import webmail.wsdl.EditFileResponse;
import webmail.wsdl.EventArray;
import webmail.wsdl.EventBean;
import webmail.wsdl.GetCalendarDetailResponse;
import webmail.wsdl.GetCalendarMailResponse;
import webmail.wsdl.GetComposeMailResponse;
import webmail.wsdl.GetDeleteEventResponse;
import webmail.wsdl.GetEventsResponse;
import webmail.wsdl.GetFileByPathResponse;
import webmail.wsdl.GetFileResponse;
import webmail.wsdl.GetFilterEventsResponse;
import webmail.wsdl.GetFolderByPathResponse;
import webmail.wsdl.GetImportCalendarResponse;
import webmail.wsdl.GetInviteEventResponse;
import webmail.wsdl.GetSharedFilesByPathResponse;
import webmail.wsdl.GetSharedFilesByPathWithContentResponse;
import webmail.wsdl.GetUpdateCalendarResponse;
import webmail.wsdl.GetVCFFileResponse;
import webmail.wsdl.RecycleFolderResponse;
import webmail.wsdl.RemoveAssignedPermissionResponse;
import webmail.wsdl.VCFFileAtt;

@Controller
public class CalendarController {

	@Autowired
	private CalendarClient calendarclient;
	@Autowired
	private FileClient fileClient;
	@Autowired
	private FolderClient folderClient;

	@Autowired
	private WebmailClient webmailClient;
	
	@RequestMapping("/createCalendar")
	@ResponseBody
	public String createCalendar(@RequestParam String calendarid,
			HttpServletRequest request, String calcolor) {
		HttpSession hs = request.getSession();
		String id = (String) hs.getAttribute("id");
		String pass = (String) hs.getAttribute("pass");
		CreateCalendarResponse res = calendarclient.createCalendar(calendarid,
				calcolor, "PUBLISH");
		calcolor = calcolor.replace("#", "");
		String filename = calendarid + "_"+ (UUID.randomUUID()) + ".ics";
		System.out.println(filename);

		CreateFileResponse cr = fileClient.createFile(filename, "/" + id+ "/calendar", id, pass, "", "", res.getResponse(), 0);
		System.out.println(cr.isSuccess());
		return cr.getFile().getFileName();
	}
	private static SimpleDateFormat iCalendarDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmm'00'");
	private static SimpleDateFormat iCalendarDateFormatHalf = new SimpleDateFormat("yyyyMMdd");
	 
	@RequestMapping(value = "createEvent", produces = "application/json")
	@ResponseBody
	public String createEvent(@RequestParam String eventdetails, HttpServletRequest request) 
	{
		JsonHelper helper = new JsonHelper();
		EventBean eventbean = null;
		try {
			eventbean = helper.parseJSON(eventdetails);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean valid = true;
		boolean ust=false;
		JSONObject obj = new JSONObject();
		if(eventbean.getEndeventdate().toGregorianCalendar().getTime().getTime() < eventbean.getStarteventdate().toGregorianCalendar().getTime().getTime())
		{
			valid = false;
			obj.put("error", "true");
			obj.put("endDateError", "true");
			
		}
		if(eventbean.getUntil() != null && eventbean.getUntil().toGregorianCalendar().getTime().getTime() < eventbean.getEndeventdate().toGregorianCalendar().getTime().getTime())
		{
			valid = false;
			obj.put("error", "true");
			obj.put("untilDateError", "true");
		}
		if(!valid)
		{
			return obj.toJSONString();
		}
		
		if(eventbean.getAllday()!=null && eventbean.getAllday().equalsIgnoreCase("on"))
		{
			/*java.util.Calendar npcal = java.util.Calendar.getInstance(); // creates calendar
			Date dt=eventbean.getUntil().toGregorianCalendar().getTime();
			Date dt1 = new Date(eventbean.getEndeventdate().toGregorianCalendar().getTime());
			npcal.setTime(dt);
			npcal.add(Calendar.DATE, 1);
			Date cDate = npcal.getTime();
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(cDate);*/
			XMLGregorianCalendar date2=eventbean.getEndeventdate();
			
			
			date2.setDay(date2.getDay()+1);
			
			eventbean.setEndeventdate(date2);
		}
		String send_invite_mail = request.getParameter("send_invite_mail");
		String deletedguest = request.getParameter("deletedguest");
		System.out.println("======================= "+send_invite_mail);
		
		HttpSession hs = request.getSession();
		String id = (String) hs.getAttribute("id");
		String pass = (String) hs.getAttribute("pass");
		String fname = (String) hs.getAttribute("fname");
		
		GetFileByPathResponse fileByPath = fileClient.getFileByPath("/" + id
				+ "/calendar/" + eventbean.getCalendar(), id, pass);
		boolean sharedcal = false;
		if(fileByPath.getFile().getFileContent() == null)
		{
			sharedcal = false;
			fileByPath = fileClient.getFileByPath("/" + id
					+ "/sharedCalendars/" + eventbean.getCalendar(), id, pass);
		}
		webmail.wsdl.File fileNode = fileByPath.getFile();
		CreateEventResponse res = calendarclient.createEvent(eventbean,
				fileNode.getFileContent(), fname, id);
	///	res.get
		ust=res.isUpdateStatus();
		EditFileResponse res1 = fileClient.editEvent(fileNode.getFilePath(),
				res.getResponse(), id, pass);
//		eventbean.setUid(res.getEventuid());
		
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		DateFormat dateformat1 = new SimpleDateFormat("yyyyMMdd'T'hhmmss");
		DateFormat dateformat2 = new SimpleDateFormat("yyyy-MM-dd");
		
		obj.put("summary", eventbean.getSummary());

		DateFormat df = new SimpleDateFormat("EEE MMM dd',' yyyy  hh:mm a");
		String eventtime = null;
		/*
		 * if (eventbean.getAllday().equals("on")) {
				obj.put("start",dateformat2.format(eventbean.getStarteventdate().toGregorianCalendar().getTime()));
				//eventbean.getEndeventdate().setDay(eventbean.getEndeventdate().getDay()+1);
				obj.put("end",dateformat2.format(eventbean.getEndeventdate().toGregorianCalendar().getTime()));
				
				//eventtime = df.format(eventbean.getStarteventdate().toGregorianCalendar().getTime());
			} else {
				obj.put("start",dateformat.format(eventbean.getStarteventdate()	.toGregorianCalendar().getTime()));
				obj.put("end",dateformat.format(eventbean.getEndeventdate()	.toGregorianCalendar().getTime()));
				//eventtime = df.format(eventbean.getStarteventdate().toGregorianCalendar().getTime());
			}
		 * 
		 */
		try 
		{
			if (eventbean.getAllday().equals("on")) {
				obj.put("start",dateformat2.format(eventbean.getStarteventdate().toGregorianCalendar().getTime()));
				obj.put("end",dateformat2.format(eventbean.getEndeventdate().toGregorianCalendar().getTime()));
				//eventbean.getEndeventdate().setDay(eventbean.getEndeventdate().getDay()-1);
				eventtime = df.format(eventbean.getStarteventdate().toGregorianCalendar().getTime());
			} else {
				obj.put("start",dateformat.format(eventbean.getStarteventdate()	.toGregorianCalendar().getTime()));
				obj.put("end",dateformat.format(eventbean.getEndeventdate()	.toGregorianCalendar().getTime()));
				eventtime = df.format(eventbean.getStarteventdate().toGregorianCalendar().getTime());
			}
			
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		
		String guestids = eventbean.getNewguest();
		String updateGuests = eventbean.getOldguest();
		
		if(send_invite_mail != null && send_invite_mail.equals("1") &&  ((guestids != null && guestids.length() > 0) || (updateGuests != null && updateGuests.length() > 0) || (deletedguest != null && deletedguest.length() > 0)  ))
		{
			String ipAddress=null;
			ipAddress = request.getHeader("X-FORWARDED-FOR");   // proxy
			if(ipAddress==null)
			{
				ipAddress = request.getRemoteAddr();
			}
			String host=(String)hs.getAttribute("host");
			String smtpport=(String)hs.getAttribute("smtpport");
			String xmailer=(String)hs.getAttribute("XMailer");
			String fromname=(String)hs.getAttribute("fname");
			String loc = "";
			if(eventbean.getLocation() != null && eventbean.getLocation().length() > 0 )
			{
				loc = "<tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>Location</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><a id='event_loc'>"+eventbean.getLocation()+"</a></td></tr>";
			}
			else
			{
				loc = "<span id='event_loc'></span>";
			}
			eventbean.setFrequency("no");
			eventbean.setReminderdata("");

			if(guestids != null && guestids.length() != 0)
			{
				if(guestids.charAt(0) == ',')
				{
					guestids = guestids.replaceFirst(",", "");
				}
				
				String sub = "Invitation: ("+eventbean.getSummary()+") @ "+eventtime+" ("+id+")";
				String cntt = "<table cellspacing='0' cellpadding='8' border='0' style='width:100%;font-family:Arial,Sans-serif;border:1px Solid #ccc;border-width:1px 2px 2px 1px;background-color:#fff' summary=''><tbody><tr><td><div style='padding:2px'><span></span><h3 style='padding:0 0 6px 0;margin:0;font-family:Arial,Sans-serif;font-size:16px;font-weight:bold;color:#222'><span id='event_title'>("+eventbean.getSummary()+")</span></h3><table cellspacing='0' cellpadding='0' border='0' summary='Event details'><tbody><tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>When</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><span id='event_time'>"+ eventtime+" </span><span style='color:#888'>India Standard Time</span></td></tr>"+loc+"<tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>Who</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><table cellspacing='0' cellpadding='0'><tbody><tr><td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><div style='margin:0 0 0.3em 0'><span><span><span><input type='hidden' id='sender_id' value='"+id+"'></span></span></span> <span id='event_by'>"+fromname+"</span><span style='font-size:11px;color:#888'> - organizer</span></div></td></tbody></table></td></tr></tbody></table></div><p style='color:#222;font-size:13px;margin:0'><span style='color:#888;padding:0 2em 10px 0;'>Going?</span><strong><span><span><a style='color:#20c;white-space:nowrap;text-decoration: none;cursor: pointer;' id='yes' onclick='sendresponse(this.id)'><span><span style='padding: 3px 10px;border: 1px solid gray'>Yes </span></span></a></span></span><span><span><a style='color:#20c;white-space:nowrap;text-decoration: none;cursor: pointer;' id='maybe' onclick='sendresponse(this.id)'><span style='padding: 3px 10px;border: 1px solid gray'>Maybe<span></a></span></span><span><span><a style='color:#20c;white-space:nowrap;text-decoration: none;cursor: pointer;' id='no' onclick='sendresponse(this.id)'><span style='padding: 3px 10px;border: 1px solid gray'>No</span></a></span></span></strong></p></td></tr></tbody></table>";
				
				String[] ids = guestids.split(",");
				
				for(String attende : ids)
				{
					StringBuilder attendis = new StringBuilder();
					String newguestids="";
					String arr[]=attende.split("`");
					if(arr.length>1)
						attende=arr[1];
					else
						attende=arr[0];
					
					
						newguestids=attende;
					
					
					attendis.append("ATTENDEE;ROLE=REQ-PARTICIPANT;PARTSTAT=NEEDS-ACTION;RSVP=TRUE:MAILTO:"+ attende+"\n");
				
				
				String calendarContent =
		                "BEGIN:VCALENDAR\n" +
		                        "METHOD:REQUEST\n" +
		                        "PRODID: Silvereye \\ Silvereye Calendar 11.57 \\ EN \n" +
		                        "VERSION:2.0\n" +
		                        "BEGIN:VEVENT\n" +
		                        "DTSTAMP:" + iCalendarDateFormat.format(eventbean.getStarteventdate().toGregorianCalendar().getTime()) + "\n" ;
		                       if(eventbean.getAllday()!=null && eventbean.getAllday().equalsIgnoreCase("on"))
		                       {
		                    	   calendarContent +="DTSTART;VALUE=DATE:" + iCalendarDateFormatHalf.format(eventbean.getStarteventdate().toGregorianCalendar().getTime())+ "\n" +
		   		                   "DTEND;VALUE=DATE:"  + iCalendarDateFormatHalf.format(eventbean.getEndeventdate().toGregorianCalendar().getTime())+ "\n" ;
		                       }
		                       else
		                       {
		                    	calendarContent +="DTSTART:" + iCalendarDateFormat.format(eventbean.getStarteventdate().toGregorianCalendar().getTime())+ "\n" +
		                        "DTEND:"  + iCalendarDateFormat.format(eventbean.getEndeventdate().toGregorianCalendar().getTime())+ "\n" ;
		                       }
		                       calendarContent +="SUMMARY:"+eventbean.getSummary()+"\n" +
		                        "UID:"+res.getEventuid()+"\n" +
		                        attendis.toString() +
		                        "ORGANIZER:MAILTO:"+id+"\n" +
		                        "LOCATION:"+eventbean.getLocation()+"\n" +
		                        "DESCRIPTION:"+eventbean.getDescription()+"\n" +
		                        "SEQUENCE:0\n" +
		                        "PRIORITY:5\n" +
		                        "CLASS:"+eventbean.getClazz()+"\n" +
		                        "STATUS:CONFIRMED\n" +
		                        "TRANSP:OPAQUE\n" +
		                       /* "BEGIN:VALARM\n" +
		                        "ACTION:DISPLAY\n" +
		                        "DESCRIPTION:REMINDER\n" +
		                        "TRIGGER;RELATED=START:-PT00H15M00S\n" +
		                        "END:VALARM\n" +*/
		                        "END:VEVENT\n" +
		                        "END:VCALENDAR";
				
				GetCalendarMailResponse res12 =  calendarclient.sendCalendarMailRequest(ipAddress, id, pass, fromname, host, smtpport, newguestids, sub, cntt,calendarContent, xmailer);
				System.out.println("mail response : " + res12.isSetMailStatus());
//				webmail.wsdlnew.GetComposeMailResponse gcmres =webmailClient.comoseMailRequest("","",ipAddress,xmailer,"", host, smtpport,imapport,saveSent, id, pass, fromname, guestids, null, null, sub, cntt, isatach,  attachflname, attachnewflname, null, null, "html", filePath);
				}
			}
			
			if(updateGuests != null && updateGuests.length() != 0 && ust)
			{
				if(updateGuests.charAt(0) == ',')
				{
					updateGuests = updateGuests.replaceFirst(",", "");
				}
				int seq=1;
				if(eventbean.getSequence()>0)
					seq=eventbean.getSequence();
				String sub = "Event Updated : ("+eventbean.getSummary()+") @ "+eventtime+" ("+id+")";
				String cntt = "<table cellspacing='0' cellpadding='8' border='0' style='width:100%;font-family:Arial,Sans-serif;border:1px Solid #ccc;border-width:1px 2px 2px 1px;background-color:#fff' summary=''><tbody><tr><td><div style='padding:2px'><span></span><h3 style='padding:0 0 6px 0;margin:0;font-family:Arial,Sans-serif;font-size:16px;font-weight:bold;color:#222'><span id='event_title'>("+eventbean.getSummary()+")</span></h3><table cellspacing='0' cellpadding='0' border='0' summary='Event details'><tbody><tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>When</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><span id='event_time'>"+ eventtime+" </span><span style='color:#888'>India Standard Time</span></td></tr>"+loc+"<tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>Who</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><table cellspacing='0' cellpadding='0'><tbody><tr><td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><div style='margin:0 0 0.3em 0'><span><span><span><input type='hidden' id='sender_id' value='"+id+"'></span></span></span> <span id='event_by'>"+fromname+"</span><span style='font-size:11px;color:#888'> - organizer</span></div></td></tbody></table></td></tr></tbody></table></div><p style='color:#222;font-size:13px;margin:0'><span style='color:#888;padding:0 2em 10px 0;'>Going?</span><strong><span><span><a style='color:#20c;white-space:nowrap;text-decoration: none;cursor: pointer;' id='yes' onclick='sendresponse(this.id)'><span><span style='padding: 3px 10px;border: 1px solid gray'>Yes </span></span></a></span></span><span><span><a style='color:#20c;white-space:nowrap;text-decoration: none;cursor: pointer;' id='maybe' onclick='sendresponse(this.id)'><span style='padding: 3px 10px;border: 1px solid gray'>Maybe<span></a></span></span><span><span><a style='color:#20c;white-space:nowrap;text-decoration: none;cursor: pointer;' id='no' onclick='sendresponse(this.id)'><span style='padding: 3px 10px;border: 1px solid gray'>No</span></a></span></span></strong></p></td></tr></tbody></table>";
				
				String[] ids = updateGuests.split(",");
				
				for(String attende : ids)
				{
					StringBuilder attendis = new StringBuilder();
					
					String newupdateGuests="";
					String arr[]=attende.split("`");
					if(arr.length>1)
						attende=arr[1];
					else
						attende=arr[0];
					
				
						newupdateGuests=attende;
					
					attendis.append("ATTENDEE;ROLE=REQ-PARTICIPANT;PARTSTAT=NEEDS-ACTION;RSVP=TRUE:MAILTO:"+ attende+"\n");
				
				
				String calendarContent =
		                "BEGIN:VCALENDAR\n" +
		                        "METHOD:REQUEST\n" +
		                        "PRODID: Silvereye \\ Silvereye Calendar 11.57 \\ EN \n" +
		                        "VERSION:2.0\n" +
		                        "BEGIN:VEVENT\n" +
		                        "DTSTAMP:" + iCalendarDateFormat.format(eventbean.getStarteventdate().toGregorianCalendar().getTime()) + "\n" ;
		                       if(eventbean.getAllday()!=null && eventbean.getAllday().equalsIgnoreCase("on"))
		                       {
		                    	   calendarContent +="DTSTART;VALUE=DATE:" + iCalendarDateFormatHalf.format(eventbean.getStarteventdate().toGregorianCalendar().getTime())+ "\n" +
				                   "DTEND;VALUE=DATE:"  + iCalendarDateFormatHalf.format(eventbean.getEndeventdate().toGregorianCalendar().getTime())+ "\n" ; 
		                       }
		                       else
		                       {
		                    	   calendarContent +="DTSTART:" + iCalendarDateFormat.format(eventbean.getStarteventdate().toGregorianCalendar().getTime())+ "\n" +
		                    	   "DTEND:"  + iCalendarDateFormat.format(eventbean.getEndeventdate().toGregorianCalendar().getTime())+ "\n" ;
		                       }
		                       calendarContent += "SUMMARY:"+eventbean.getSummary()+"\n" +
		                        "UID:"+res.getEventuid()+"\n" +
		                        attendis.toString() +
		                        "ORGANIZER:MAILTO:"+id+"\n" +
		                       // "LAST-MODIFIED:20160120T133639Z \n" +
		                        "LOCATION:"+eventbean.getLocation()+"\n" +
		                       // "CREATED:20160120T121242Z" +
		                        "DESCRIPTION:"+eventbean.getDescription()+"\n" +
		                        "SEQUENCE:"+seq+"\n" +
		                        "PRIORITY:5\n" +
		                        "CLASS:"+eventbean.getClazz()+"\n" +
		                        "STATUS:CONFIRMED\n" +
		                        "TRANSP:TRANSPARENT\n" +
		                        /*"BEGIN:VALARM\n" +
		                        "ACTION:DISPLAY\n" +
		                        "DESCRIPTION:REMINDER\n" +
		                        "TRIGGER;RELATED=START:-PT00H15M00S\n" +
		                        "END:VALARM\n" +*/
		                        "END:VEVENT\n" +
		                        "END:VCALENDAR";
				
				GetCalendarMailResponse res12 =  calendarclient.sendCalendarMailRequest(ipAddress, id, pass, fromname, host, smtpport, newupdateGuests, sub, cntt,calendarContent, xmailer);
				System.out.println("mail response : " + res12.isSetMailStatus());
//				webmail.wsdlnew.GetComposeMailResponse gcmres =webmailClient.comoseMailRequest("","",ipAddress,xmailer,"", host, smtpport,imapport,saveSent, id, pass, fromname, guestids, null, null, sub, cntt, isatach,  attachflname, attachnewflname, null, null, "html", filePath);
				}
			}
			
			if(deletedguest != null && deletedguest.length() > 0)
			{
				if(deletedguest.charAt(0) == ',')
				{
					deletedguest = deletedguest.replaceFirst(",", "");
				}
				
				int seq=0;
				if(eventbean.getSequence()>0)
				{
					seq=eventbean.getSequence();
				}
				else
				{
					seq=0;
				}
				
				String sub = "Canceled Event: ("+eventbean.getSummary()+") @ "+eventtime+" ("+id+")";
				String cntt = "<table cellspacing='0' cellpadding='8' border='0' style='width:100%;font-family:Arial,Sans-serif;border:1px Solid #ccc;border-width:1px 2px 2px 1px;background-color:#fff' summary=''><tbody><tr><td><h4 style='padding:6px 10px;margin:0 0 4px 0;font-family:Arial,Sans-serif;font-size:13px;line-height:1.4;border:1px Solid #fcc;background:#fcc;color:#222;font-weight:bold'>This event has been canceled.</h4><div style='padding:2px'><span></span><h3 style='padding:0 0 6px 0;margin:0;font-family:Arial,Sans-serif;font-size:16px;font-weight:bold;color:#222'><span>("+eventbean.getSummary()+")</span></h3><table cellspacing='0' cellpadding='0' border='0' summary='Event details'><tbody><tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>When</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'>"+ eventtime+" <span style='color:#888'>India Standard Time</span></td></tr>"+loc+"<tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>Who</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><table cellspacing='0' cellpadding='0'><tbody><tr><td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><div style='margin:0 0 0.3em 0'><span>"+fromname+"</span><span style='font-size:11px;color:#888'> - organizer</span></div></td></tbody></table></td></tr></tbody></table></td></tr></tbody></table>";


				String[] ids = deletedguest.split(",");
				
				for(String attende : ids)
				{
					StringBuilder attendis = new StringBuilder();
					String newdeletedguest="";
					String arr[]=attende.split("`");
					if(arr.length>1)
						attende=arr[1];
					else
						attende=arr[0];
					
					
						newdeletedguest=attende;
					
					attendis.append("ATTENDEE;ROLE=REQ-PARTICIPANT;PARTSTAT=DECLINED;RSVP=TRUE:MAILTO:"+ attende+"\n");
				
			
				String calendarContent =
		                "BEGIN:VCALENDAR\n" +
		                        "METHOD:CANCEL\n" +
		                        "PRODID: Silvereye \\ Silvereye Calendar 11.57 \\ EN \n" +
		                        "VERSION:2.0\n" +
		                        "BEGIN:VEVENT\n" +
		                        "DTSTAMP:" + iCalendarDateFormat.format(eventbean.getStarteventdate().toGregorianCalendar().getTime()) + "\n" ;
		                        if(eventbean.getAllday()!=null && eventbean.getAllday().equalsIgnoreCase("on"))
		                        {
		                        	calendarContent +="DTSTART;VALUE=DATE:" + iCalendarDateFormatHalf.format(eventbean.getStarteventdate().toGregorianCalendar().getTime())+ "\n" +
				                    "DTEND;VALUE=DATE:"  + iCalendarDateFormatHalf.format(eventbean.getEndeventdate().toGregorianCalendar().getTime())+ "\n" ;
		                        }
		                        else
		                        {
		                        	calendarContent +="DTSTART:" + iCalendarDateFormat.format(eventbean.getStarteventdate().toGregorianCalendar().getTime())+ "\n" +
		                        	"DTEND:"  + iCalendarDateFormat.format(eventbean.getEndeventdate().toGregorianCalendar().getTime())+ "\n" ;
		                        }
		                        calendarContent +="SUMMARY:"+eventbean.getSummary()+"\n" +
		                        "UID:"+res.getEventuid()+"\n" +
		                        attendis.toString() +
		                        "ORGANIZER:MAILTO:"+id+"\n" +
		                      //  "LAST-MODIFIED:20160120T133639Z \n" +
		                        "LOCATION:"+eventbean.getLocation()+"\n" +
		                      //  "CREATED:20160120T121242Z" +
		                        "DESCRIPTION:"+eventbean.getDescription()+"\n" +
		                        "SEQUENCE:"+seq+"\n" +
		                        "PRIORITY:5\n" +
		                        "CLASS:"+eventbean.getClazz()+"\n" +
		                        "STATUS:CONFIRMED\n" +
		                        "TRANSP:TRANSPARENT\n" +
		                      /*  "BEGIN:VALARM\n" +
		                        "ACTION:DISPLAY\n" +
		                        "DESCRIPTION:REMINDER\n" +
		                        "TRIGGER;RELATED=START:-PT00H15M00S\n" +
		                        "END:VALARM\n" +*/
		                        "END:VEVENT\n" +
		                        "END:VCALENDAR";
			    GetCalendarMailResponse res12 =  calendarclient.sendCalendarMailRequest(ipAddress, id, pass, fromname, host, smtpport, newdeletedguest, sub, cntt,calendarContent, xmailer);
				System.out.println("mail response : " + res12.isSetMailStatus());
//				webmail.wsdlnew.GetComposeMailResponse gcmres =webmailClient.comoseMailRequest("","",ipAddress,xmailer,"", host, smtpport,imapport,saveSent, id, pass, fromname, deletedguest, null, null, sub, cntt, isatach,  attachflname, attachnewflname, null, null, "html", filePath);
				
			}
			}
		}
		
		
		/*try 
		{
			if (eventbean.getAllday().equals("on")) {
				obj.put("start",dateformat2.format(eventbean.getStarteventdate().toGregorianCalendar().getTime()));
				//eventbean.getEndeventdate().setDay(eventbean.getEndeventdate().getDay()+1);
				obj.put("end",dateformat2.format(eventbean.getEndeventdate().toGregorianCalendar().getTime()));
				
				//eventtime = df.format(eventbean.getStarteventdate().toGregorianCalendar().getTime());
			} else {
				obj.put("start",dateformat.format(eventbean.getStarteventdate()	.toGregorianCalendar().getTime()));
				obj.put("end",dateformat.format(eventbean.getEndeventdate()	.toGregorianCalendar().getTime()));
				//eventtime = df.format(eventbean.getStarteventdate().toGregorianCalendar().getTime());
			}
			
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		*/
		
		if(!sharedcal)
		{
 			obj.put("name", "/calendar/"+eventbean.getCalendar());
		}
		else
		{
			obj.put("name", "/sharedCalendars/"+eventbean.getCalendar());
		}
		obj.put("id", res.getEventuid());
		obj.put("c", eventbean.getUid());
		obj.put("color", res.getEventcolor());
		
		JSONObject rdates = new JSONObject();
		JSONObject erdates = new JSONObject();
		if (res.getRepeatdates() != null) {
			int j = 0;
			for (String date : res.getRepeatdates().getDateTime())
			{
				if(j > 30)
				{
					break;
				}
				String[] splitdate = date.split("`");
				try {
					if (eventbean.getAllday().equals("on"))
					{
						rdates.put(j,dateformat2.format(dateformat1.parse(splitdate[0])));
						Date end=dateformat1.parse(splitdate[1]);
						//end.setDate(end.getDate()+1);
						erdates.put(j,dateformat2.format(end));
					}
					else
					{
						rdates.put(j,dateformat.format(dateformat1.parse(splitdate[0])));
						Date end=dateformat1.parse(splitdate[1]);
						//end.setDate(end.getDate()+1);
						erdates.put(j,end);
					}
					j++;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		obj.put("repeatdates", rdates);
		obj.put("erepeatdates", erdates);
		return obj.toJSONString();

	}

	
	@RequestMapping(value = "/sendInvitationResponse",produces = "text/html; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String sendInvitationResponse(@RequestParam String respond_to,String uuid,
			String reply_status, ModelMap map, HttpServletResponse response,
			HttpServletRequest request) 
	{
		
		HttpSession hs = request.getSession();
		String id = (String) hs.getAttribute("id");
		String pass = (String) hs.getAttribute("pass");
		String fname=(String) hs.getAttribute("fname");
		
		JSONObject obj = new JSONObject();
		
		String calfile = "/" + id + "/calendar/default.ics";
		GetFileByPathResponse fileByPath = fileClient.getFileByPath(calfile, id, pass);
		webmail.wsdl.File file = fileByPath.getFile();
		if(file.getFileContent() != null)
		{
			EventBean eventbean = calendarclient.geteventdetails(uuid, calfile,file.getFileContent()).getEventBean();
			
			String ipAddress=null;
			ipAddress = request.getHeader("X-FORWARDED-FOR");   // proxy
			if(ipAddress==null)
			{
				ipAddress = request.getRemoteAddr();
			}
			String host=(String)hs.getAttribute("host");
			String smtpport=(String)hs.getAttribute("smtpport");
			String xmailer=(String)hs.getAttribute("XMailer");
			String fromname=(String)hs.getAttribute("fname");
			String attendee = null;
			String sub = "";
			String loc = "";
			String msg = "";
			String atten_st="n`";
			int seq=0;
			
			
			DateFormat df = new SimpleDateFormat("EEE MMM dd',' yyyy  hh:mm a");
			String event_time = null;
			try 
			{
				if (eventbean.getAllday().equals("on")) {
					
					event_time = df.format(eventbean.getStarteventdate().toGregorianCalendar().getTime());
				} else {
						event_time = df.format(eventbean.getStarteventdate().toGregorianCalendar().getTime());
				}
				
				if(eventbean.getSequence()>0)
				{
					seq=eventbean.getSequence();
				}
				else
				{
					seq=0;
				}
					
			}
			catch (Exception e) 
			{
				// TODO: handle exception
			}
			
			String stat="";
			if(eventbean.getLocation() != null && eventbean.getLocation().length() != 0 )
			{
				loc = "<tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>Location</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><a>"+eventbean.getLocation()+"</a></td></tr>";
			}
			
			if(reply_status.equals("yes"))
			{
				attendee = "ATTENDEE;PARTSTAT=ACCEPTED:MAILTO:"+ id+"\n";
				sub = "Accepted : "+eventbean.getSummary();
				msg = "<h4 style='padding:6px 10px;margin:0 0 4px 0;font-family:Arial,Sans-serif;font-size:13px;line-height:1.4;border:1px Solid #fcc;background:#A9F5F2;color:#222;font-weight:bold'>This event has been accepted.</h4>";
				stat="Accepted";
				atten_st="a`";
			}
			else if(reply_status.equals("maybe"))
			{
				attendee = "ATTENDEE;PARTSTAT=TENTATIVE:MAILTO:"+ id+"\n";
				sub = "Tentatively Accepted : "+eventbean.getSummary();
//				sub = "Tentatively Accepted: "+event_title+" @ "+event_time+" ("+sender_id+")";
				msg = "<h4 style='padding:6px 10px;margin:0 0 4px 0;font-family:Arial,Sans-serif;font-size:13px;line-height:1.4;border:1px Solid #fcc;background:#BDBDBD;color:#222;font-weight:bold'>This event has been tentatively accepted.</h4>";
				stat="Tentatively Accepted";
				atten_st="t`";
			}
			else if(reply_status.equals("no"))
			{
				attendee = "ATTENDEE;PARTSTAT=DECLINED:MAILTO:"+ id+"\n";
				sub = "Rejected : "+eventbean.getSummary();
//				sub = "Rejected : "+event_title+" @ "+event_time+" ("+sender_id+")";
				msg = "<h4 style='padding:6px 10px;margin:0 0 4px 0;font-family:Arial,Sans-serif;font-size:13px;line-height:1.4;border:1px Solid #fcc;background:#fcc;color:#222;font-weight:bold'>This event has been rejected.</h4>";
				stat="Rejected";
				atten_st="d`";
			}
			
//		msg+="<table cellspacing='0' cellpadding='8' border='0' style='width:100%;font-family:Arial,Sans-serif;border:1px Solid #ccc;border-width:1px 2px 2px 1px;background-color:#fff' summary=''><tbody><tr><td>"+msg+"<div style='padding:2px'><span></span><h3 style='padding:0 0 6px 0;margin:0;font-family:Arial,Sans-serif;font-size:16px;font-weight:bold;color:#222'><span>"+eventbean.getSummary()+"</span></h3><table cellspacing='0' cellpadding='0' border='0' summary='Event details'><tbody><tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>When</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'>"+ event_time+" <span style='color:#888'>India Standard Time</span></td></tr>	 <tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>Where</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'>"+loc+"</td></tr>  	 <tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>Attendee</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><table cellspacing='0' cellpadding='0'><tbody><tr><td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><div style='margin:0 0 0.3em 0'><span>"+id+"</span><span style='font-size:11px;color:#888'> </span></div></td></tbody></table></td></tr></tbody></table></td></tr>	 <tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>Status</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'>"+stat+"</td></tr></tbody></table>";	
	msg+=" <table cellspacing='0' cellpadding='8' border='0' style='width:100%;font-family:Arial,Sans-serif;border:1px Solid #ccc;border-width:1px 2px 2px 1px;background-color:#fff' summary=''><tbody><tr><td><div style='padding:2px'><span></span><h3 style='padding:0 0 6px 0;margin:0;font-family:Arial,Sans-serif;font-size:16px;font-weight:bold;color:#222'><span>"+eventbean.getSummary()+"</span></h3><table cellspacing='0' cellpadding='0' border='0' summary='Event details'>	 <tbody>	 <tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>When</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'>"+ event_time+" <span style='color:#888'>India Standard Time</span></td></tr>	 "+loc+"<tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>Attendee</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'>"+ id+" </td></tr><tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>Status</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'>"+ stat+" </td></tr> </tbody>	</td></tr>	 </tbody></table>";
			SimpleDateFormat df1= new SimpleDateFormat("yyyyMMdd'T'hhmmss'Z'");
			Date mdt=new Date();
			java.util.Calendar npcal = java.util.Calendar.getInstance(); // creates calendar
			npcal.setTime(mdt); // sets calendar time/date=====> you can set your own date here
			npcal.add(java.util.Calendar.HOUR_OF_DAY, -5); // adds one hour
			npcal.add(java.util.Calendar.MINUTE, -30); // adds one Minute
			mdt= npcal.getTime();
			String calendarContent =
	                "BEGIN:VCALENDAR\n" +
	                        "PRODID: Silvereye \\ Silvereye Calendar 11.57 \\ EN \n" +
	                        "VERSION:2.0\n" +
	                        "METHOD:REPLY\n" +
	                        "BEGIN:VTIMEZONE\n" +
	                        "TZID:India Standard Time\n" +
	                        "BEGIN:STANDARD\n" +
	                        "DTSTART:16010101T000000\n" +
	                        "TZOFFSETFROM:+0530\n" +
	                        "TZOFFSETTO:+0530\n" +
	                        "END:STANDARD\n" +
	                        "END:VTIMEZONE\n" +
	                        "BEGIN:VEVENT\n" +
	                        "DTSTART;TZID=\"India Standard Time\":" + iCalendarDateFormat.format(eventbean.getStarteventdate().toGregorianCalendar().getTime())+ "\n" +
	                        "DTEND;TZID=\"India Standard Time\":"  + iCalendarDateFormat.format(eventbean.getEndeventdate().toGregorianCalendar().getTime())+ "\n" +
	                        "LAST-MODIFIED:"+df1.format(mdt)+"\n"+
	                        "DTSTAMP:"+df1.format(mdt)+"\n" +
	                        "SUMMARY:"+eventbean.getSummary()+"\n" +
	                        "UID:"+eventbean.getUid()+"\n" +
	                        attendee +
	                        "LOCATION:"+eventbean.getLocation()+"\n" +
	                        "DESCRIPTION:"+eventbean.getDescription()+"\n" +
	                        "SEQUENCE:0\n" +
	                        "PRIORITY:5\n" +
	                        "CLASS:"+eventbean.getClazz()+"\n" +
	                        "STATUS:CONFIRMED\n" +
	                        "TRANSP:OPAQUE\n" +
	                        "END:VEVENT\n" +
	                        "END:VCALENDAR";
			
			GetCalendarMailResponse res12 =  calendarclient.sendCalendarMailRequest(ipAddress, id, pass, fromname, host, smtpport, respond_to, sub, msg,calendarContent, xmailer);
			System.out.println("mail response : " + res12.isSetMailStatus());
			
			
			try
			{
				
				
				
				if(eventbean!=null)
		   		{
					
		   			
		   			String oldid=eventbean.getOldguest();
		   			String arrid[]=oldid.split(",");
		   			String attlst="";
		   			for(String aid: arrid)
		   			{
		   				if(aid==null)
		   				{
		   					aid="";
		   				}
		   				if(aid.endsWith("`"+id))
		   				{
		   					String arrid1[]=aid.split("`");
		   					if(arrid1.length>1)
		   					{
		   						aid=atten_st+arrid1[1];
		   					}
		   				}
		   				
		   					if(aid.length()>0)
		   						attlst+=","+aid;
		   					
		   				
		   			}
		   			
		   			if(attlst.length()>0)
		   			{
		   				eventbean.setOldguest(attlst);
		   				
		   				CreateEventResponse rescal = calendarclient.createEvent(eventbean,file.getFileContent(), fname, id);
		   				EditFileResponse res1 = fileClient.editEvent(file.getFilePath(),rescal.getResponse(), id, pass);
		   				EditFileRes efres= res1.getEditFileRes();
		   				
		   			}
		   		}
			}
			catch(Exception ee)
			{
				ee.printStackTrace();
			}
		}
			

		return obj.toJSONString();
	}
	
	@RequestMapping(value = "/addToDefaultCalender", produces = "application/json")
	@ResponseBody
	public String addToDefaultCalender(ModelMap map, HttpServletResponse response, HttpServletRequest request) 
	{
		HttpSession hs = request.getSession();
		String id = (String) hs.getAttribute("id");
		String pass = (String) hs.getAttribute("pass");
		
		File file=null;
		try 
		{
			FileInputStream fileStream=new FileInputStream(file=new File("D:/invite.ics"));

			byte []arr= new byte[(int)file.length()];
			
			fileStream.read(arr,0,arr.length);
			
			byte[] encodedImageFrom = org.apache.commons.codec.binary.Base64.decodeBase64(arr);
			
			System.out.println(arr);
			
			String calfile = "/" + id + "/calendar/"+ id+".ics";
			GetFileByPathResponse fileByPath = fileClient.getFileByPath(calfile, id, pass);
			webmail.wsdl.File fileNode = fileByPath.getFile();
			if(fileNode.getFileContent() != null)
			{
				CreateCalendarResponse res = calendarclient.importCalendarRequest(encodedImageFrom, fileNode.getFileContent());
				System.out.println(res.getResponse());
				EditFileResponse res1 = fileClient.editEvent(fileNode.getFilePath(),
						res.getResponse(), id, pass);
				
				System.out.println("success : " + res1.getEditFileRes().getResponse());
				
			}
			
			
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return null;
	}
	
	@RequestMapping(value = "/geteventdetails", produces = "application/json")
	@ResponseBody
	public String geteventdetails(@RequestParam String uid,
			String calendarname, ModelMap map, HttpServletResponse response,
			HttpServletRequest request) {
		HttpSession hs = request.getSession();
		String id = (String) hs.getAttribute("id");
		String pass = (String) hs.getAttribute("pass");
		GetFileByPathResponse fileByPath = fileClient.getFileByPath("/" + id
				+ calendarname, id, pass);
		webmail.wsdl.File fileNode = fileByPath.getFile();
		EventBean res = calendarclient.geteventdetails(uid, calendarname,
				fileNode.getFileContent()).getEventBean();
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timeformat = new SimpleDateFormat("h:mma");
		System.out.println(res.getStarteventdate().toGregorianCalendar()
				.getTime().toString());
		String startdate = dateformat.format(res.getStarteventdate()
				.toGregorianCalendar().getTime());
		if(res.getAllday()!=null && res.getAllday().equalsIgnoreCase("on"))
		{
			XMLGregorianCalendar date2=res.getEndeventdate();
			date2.setDay(date2.getDay()-1);
			res.setEndeventdate(date2);
		}
		String enddate = dateformat.format(res.getEndeventdate()
				.toGregorianCalendar().getTime());
		String starttime = timeformat.format(res.getStarteventdate()
				.toGregorianCalendar().getTime());
		String endtime = timeformat.format(res.getEndeventdate()
				.toGregorianCalendar().getTime());

		System.out.println(starttime);
		JSONObject obj = new JSONObject();
		if(fileNode.getCreatedBy().equals(id) || fileNode.getUserSecurity().toString().contains(id))
		{
			obj.put("permission", "manage");
			if (res.getCalendar() != null) {
				String[] aa = res.getCalendar().split("/");
				obj.put("calendar", aa[aa.length - 1]);
			}
		}
		else
		{
			obj.put("permission", "read");
			if (res.getCalendar() != null) {
				String[] aa = res.getCalendar().split("/");
				String arr[]=aa[aa.length - 1].split("_");
				String cal=arr[0];
				if(fileNode.getCreatedBy()!=null)
					cal+="("+fileNode.getCreatedBy()+")";
				obj.put("calendar", cal);
			}
		}
		obj.put("summary", res.getSummary());
		obj.put("start", startdate);
		obj.put("end", enddate);
		obj.put("location", res.getLocation());
		obj.put("description", res.getDescription());
		
		if(res.getOldguest() != null)
		{
			obj.put("oldguest", res.getOldguest());
		}
		else
		{
			obj.put("oldguest", "");
		}
		obj.put("reminderdata", res.getReminderdata());
		obj.put("eventuid", res.getUid());
		obj.put("clazz", res.getClazz());
		obj.put("frequency", res.getFrequency());
		obj.put("count", res.getCount());
		if(res.getUntil() != null)
		{
			obj.put("until", res.getUntil());
		}
		else
		{
			obj.put("until", "");
		}
		obj.put("allday", res.getAllday());
		obj.put("interval", res.getInterval());
		if (res.getUntil() != null) {
			obj.put("until",
					dateformat.format(res.getUntil().toGregorianCalendar()
							.getTime()));
		}
		if (res.getRepeatdatetimelist() != null)
			obj.put("daylist",
					res.getRepeatdatetimelist().getDateTime().toString()
							.replace("[", "").replace("]", "").replace(" ", ""));
		// System.out.println(res.getRepeatdatetimelist().getDateTime().toString().replace("[",
		// "").replace("]", "").replace(" ", ""));
		if (res.getAllday().equals("off")) {
			obj.put("starttime", starttime);
			obj.put("endtime", endtime);
		}
		return obj.toJSONString();
	}

	/*
	 * @RequestMapping(value = "/sharecalendar", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public String sharecalendar(ModelMap map,
	 * HttpServletRequest request) { String
	 * shareid=request.getParameter("shareid") ; String
	 * confldr=request.getParameter("confldr") ;
	 * 
	 * HttpSession hs=request.getSession(); String
	 * uid=(String)hs.getAttribute("id");
	 * System.out.println("current folder or file value is : "+confldr);
	 * AssignSinglePermissionResponse response = folderClient
	 * .assignSinglePermission("/"+uid+confldr, uid, shareid+"/sharedCalendars",
	 * "uw"); String resp = response.getAssignSinglePermissionResponse(); return
	 * resp;
	 * 
	 * }
	 */

	@RequestMapping(value = "/deleteCalendar", method = RequestMethod.GET)
	@ResponseBody
	public boolean deleteCalendar(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		String uid = (String) hs.getAttribute("id");
		String pass = (String) hs.getAttribute("pass");
		String calname = request.getParameter("calendar");
		// System.out.println(confile);
		if (calname != null && !calname.equals("") && !calname.equals("default.ics")) 
		{
			
			String calfile = "/" + uid + "/calendar/"+ request.getParameter("calendar");
			//DeleteFileResponse dlr = fileClient.deleteFile(calfile, uid, pass);
			DeleteFileResponse dlr = fileClient.deleteFile(calfile, uid, pass);
			
			return true;
		}
		else {
			return false;
		}

	}

	@RequestMapping(value = "/getCalendarDetail", method = RequestMethod.GET)
	@ResponseBody
	public String getCalendarDetail(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		String id = (String) hs.getAttribute("id");
		String pass = (String) hs.getAttribute("pass");
		String calfile = "/" + id + "/calendar/"+ request.getParameter("calendar");
		GetFileByPathResponse fileByPath = fileClient.getFileByPath(calfile, id, pass);
		webmail.wsdl.File file = fileByPath.getFile();
		
		JSONObject obj = new JSONObject();
		if(file.getFileContent() != null)
		{
			GetCalendarDetailResponse res = calendarclient.getCalendarDetail("", file.getFileContent());
			CalendarBean calBean = res.getCalendarBean();
			System.out.println("cal name : "+calBean.getCalendarname());
			System.out.println("getColor : "+calBean.getColor());
			System.out.println("getLocation : "+calBean.getLocation());
			System.out.println("getDescription : "+calBean.getDescription());
			obj.put("calname", calBean.getCalendarname());
			obj.put("calcolor", calBean.getColor());
			obj.put("ownerid", file.getCreatedBy());
			if(calBean.getLocation() != null)
			{
				obj.put("location", calBean.getLocation());
			}
			else
			{
				obj.put("location", "");
			}
			if(calBean.getDescription() != null)
			{
				obj.put("description", calBean.getDescription());
				
			}
			else
			{
				obj.put("description", "");
			}
			return obj.toJSONString();
		}
		System.out.println(calfile);
		obj.put("error", "true");
		return obj.toJSONString();

	}
	@RequestMapping(value = "/updateCalendarDetail", method = RequestMethod.GET)
	@ResponseBody
	public String updateCalendarDetail(HttpServletRequest request) 
	{
		HttpSession hs = request.getSession();
		String id = (String) hs.getAttribute("id");
		String pass = (String) hs.getAttribute("pass");
		String calfile = "/" + id + "/calendar/"+ request.getParameter("calendar");
		String cal_name = request.getParameter("cal_name");
		String cal_location = request.getParameter("cal_location");
		String cal_desc = request.getParameter("cal_desc");
		JSONObject obj = new JSONObject();
		if(cal_name != null && cal_name.trim().length() == 0)
		{
			obj.put("error", "true");
			return obj.toJSONString();
		}
		GetFileByPathResponse fileByPath = fileClient.getFileByPath(calfile, id, pass);
		webmail.wsdl.File file = fileByPath.getFile();
		if(file.getFileContent() != null)
		{
			CalendarBean calBean = new CalendarBean();
			calBean.setLocation(cal_location);
			calBean.setCalendarname(cal_name);
			cal_desc = cal_desc.replaceAll("[;:&,]", "-");
			calBean.setDescription(cal_desc);
			
			
			GetUpdateCalendarResponse res = calendarclient.getUpdateCalendarRequest(calBean, file.getFileContent());
			if(res.isSuccess())
			{
				EditFileResponse res1 = fileClient.editEvent(file.getFilePath(), res.getFilecontent(), id, pass);
				obj.put("success", "true");
				obj.put("cal_name", cal_name);
			}
			return obj.toJSONString();
		}
		System.out.println(calfile);
		obj.put("error", "true");
		return obj.toJSONString();
	}
	@RequestMapping(value = "/changecalendarcolor", method = RequestMethod.GET)
	@ResponseBody
	public String changecalendarcolor(HttpServletRequest request) {
		String color = request.getParameter("changedcolor");
		String filename = request.getParameter("calendar");
		HttpSession hs = request.getSession();
		String id = (String) hs.getAttribute("id");
		String pass = (String) hs.getAttribute("pass");
		GetFileByPathResponse fileByPath = fileClient.getFileByPath("/" + id
				+ "/calendar/" + filename, id, pass);
		webmail.wsdl.File file = fileByPath.getFile();
		JSONObject res = new JSONObject();
		ChangeColorResponse response = calendarclient.changecalendarcolor(color, file.getFileContent());
		if(response.isSuccess())
		{
			res.put("success", "true");
			EditFileResponse res1 = fileClient.editEvent(file.getFilePath(),response.getFilecontent(), id, pass);
//			JSONArray jsonArray = new JSONArray();
//			JSONObject obj = null;
//			if(res1.getEditFileRes().isSuccess())
//			{
//				for(EventBean ev : response.getEventList().getEventList())
//				{
//					obj = new JSONObject();
//					obj.put("uid", ev.getUid());
//					jsonArray.add(obj);
//				}
//				res.put("success", "true");
//				res.put("eventList", jsonArray);
//			}
			
		}
		
		return res.toJSONString();

	}

	/*
	 * @RequestMapping(value = "/calShareTemp", method = RequestMethod.GET)
	 * public String calShareTemp(ModelMap map, Principal principal,
	 * HttpServletRequest request) throws IOException, ParseException {
	 * HttpSession hs=request.getSession(); String
	 * host=(String)hs.getAttribute("host"); String
	 * id=(String)hs.getAttribute("id"); String
	 * pass=(String)hs.getAttribute("pass");
	 * //ConnectAndListScripts.USERNAME=id;
	 * //ConnectAndListScripts.PASSWORD=pass; //ManageSieveResponse resp=
	 * ConnectAndListScripts.getConnect(); //ManageSieveClient client =
	 * ConnectAndListScripts.client;
	 * 
	 * String cal_file_name = request.getParameter("cal_file_name");
	 * System.out.println("cal_file_name : " + cal_file_name);
	 * 
	 * 
	 * return "calsharetemp"; }
	 */
	@RequestMapping("/assignSinglePermissionCal")
	@ResponseBody
	public String assignSinglePermission(ModelMap map, Principal principal,
			@RequestParam String user, @RequestParam String value,
			@RequestParam String doctype, @RequestParam String multipath,
			HttpServletRequest request) {
		HttpSession hs = request.getSession();
		String userid = (String) hs.getAttribute("id");
		String pass = (String) hs.getAttribute("pass");
		try {
			String resp = "";

			if (userid == null) 
			{
				return "ajaxTrue";
			}
			else 
			{
				multipath = "/" + userid + "/calendar/" + multipath;
				String[] str = multipath.split(",");
				for (int i = 0; i < str.length; i++) 
				{
					if (!str.equals("")) 
					{

						AssignSinglePermissionResponse response = folderClient
								.assignSinglePermission(str[i], userid, pass,
										user, value);
						resp = response.getAssignSinglePermissionResponse();
					}
				}
				return resp;
			}
		} catch (Exception e) {

			return "ajaxTrue";
		}
	}

	@RequestMapping(value = "/sharingPopup", method = RequestMethod.GET)
	public String sharingPopup(ModelMap map, Principal principal,
			HttpServletRequest request, @RequestParam String cal_file_name) {
		HttpSession hs = request.getSession();
		String userid = (String) hs.getAttribute("id");
		String pass = (String) hs.getAttribute("pass");
		try {
			if (userid == null) {
				return "ajaxTrue";
			} else {
				String folderName = cal_file_name;

				if (folderName != "") {
					GetFolderByPathResponse folderByPath = folderClient
							.getFolderByPath("/" + userid + "/calendar/"
									+ folderName, userid, pass);
					map.addAttribute("currentFolder", folderByPath.getFolder());
				} else {
					folderName = (String) hs.getAttribute("currentFile");
					GetFolderByPathResponse folderByPath = folderClient
							.getFolderByPath(folderName, userid, pass);
					map.addAttribute("currentFolder", folderByPath.getFolder());
				}
				// GetUsersListResponse response =
				// this.userClient.getUsersListRequest(userid,principal.getPassword());
				// map.addAttribute("userlist",
				// (Object)response.getUsersList());
				return "sharingPopup";
			}
		} catch (Exception e) {

			return "ajaxTrue";
		}
	}

	@RequestMapping("/removeAssignedPermissionCal")
	@ResponseBody
	public String removeAssignedPermission(HttpServletRequest request,
			ModelMap map, Principal principal, @RequestParam String folderPath,
			@RequestParam String value, @RequestParam String user) {

		try {
			String resp = "";
			HttpSession hs = request.getSession();
			String userid = (String) hs.getAttribute("id");
			String pass = (String) hs.getAttribute("pass");

			RemoveAssignedPermissionResponse response = fileClient
					.removeAssignedPermission(folderPath, userid, pass, user,
							value);
			resp = response.getAssignSinglePermissionResponse();
			// MailUtils.sendComposeMail("192.168.0.53", "25",
			// "edms@avi-oil.com", "google@2009", userid, "Shared with me",
			// "This is mail to inform you that someone have shared document with you.");
			return resp;

		} catch (Exception e) {
			return "ajaxTrue";
		}
	}

	@RequestMapping(value = "/deleteCalendearEvent", method = RequestMethod.GET)
	@ResponseBody
	public String deleteCalendearEvent(ModelMap map, Principal principal,
			HttpServletRequest request) {
		HttpSession hs = request.getSession();
		String id = (String) hs.getAttribute("id");

		String uid = request.getParameter("uid");
		String calendarname = request.getParameter("calendarname");

		// System.out.println("Uid : " + uid);
		String pass = (String) hs.getAttribute("pass");
		GetFileByPathResponse fileByPath = fileClient.getFileByPath("/" + id
				+ calendarname, id, pass);
		webmail.wsdl.File fileNode = fileByPath.getFile();
		JSONObject obj = new JSONObject();
		if (fileNode.getFileContent() != null) 
		{
			EventBean eventbean = calendarclient.geteventdetails(uid, calendarname, fileNode.getFileContent()).getEventBean();
			GetDeleteEventResponse response = calendarclient.deleteEvent(uid,
					fileNode.getFileContent());
			EditFileResponse res2 = fileClient
					.editEvent(fileNode.getFilePath(),
							response.getFilecontent(), id, pass);
			if (response.isResponse()) 
			{
				
//				---------------------Send event cancelation mail ---------------------- 
				String guestList = eventbean.getOldguest();
				if(guestList != null && guestList.length() != 0)
				{
					if(guestList.charAt(0) == ',')
					{
						guestList = guestList.replaceFirst(",", "");
					}
					String ipAddress=null;
					ipAddress = request.getHeader("X-FORWARDED-FOR");   // proxy
					if(ipAddress==null)
					{
					    ipAddress = request.getRemoteAddr();
					}
					System.out.println(" deleted ids : " +guestList);
					String host=(String)hs.getAttribute("host");
					String smtpport=(String)hs.getAttribute("smtpport");
					String xmailer=(String)hs.getAttribute("XMailer");
					String fromname=(String)hs.getAttribute("fname");
					
					String[] ids = guestList.split(",");
					
					String loc = "";
					if(eventbean.getLocation() != null && eventbean.getLocation().length() != 0 )
					{
						loc = "<tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>Location</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><a>"+eventbean.getLocation()+"</a></td></tr>";
					}
					
					String sub = "Canceled Event: ("+eventbean.getSummary()+") @ "+eventbean.getStarteventdate().toGregorianCalendar().getTime()+" ("+id+")";
					String cntt = "<table cellspacing='0' cellpadding='8' border='0' style='width:100%;font-family:Arial,Sans-serif;border:1px Solid #ccc;border-width:1px 2px 2px 1px;background-color:#fff' summary=''><tbody><tr><td><h4 style='padding:6px 10px;margin:0 0 4px 0;font-family:Arial,Sans-serif;font-size:13px;line-height:1.4;border:1px Solid #fcc;background:#fcc;color:#222;font-weight:bold'>This event has been canceled.</h4><div style='padding:2px'><span></span><h3 style='padding:0 0 6px 0;margin:0;font-family:Arial,Sans-serif;font-size:16px;font-weight:bold;color:#222'><span>("+eventbean.getSummary()+")</span></h3><table cellspacing='0' cellpadding='0' border='0' summary='Event details'><tbody><tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>When</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'>"+ eventbean.getStarteventdate().toGregorianCalendar().getTime()+" <span style='color:#888'>India Standard Time</span></td></tr>"+loc+"<tr><td valign='top' style='padding:0 1em 10px 0;font-family:Arial,Sans-serif;font-size:13px;color:#888;white-space:nowrap'><div><i style='font-style:normal'>Who</i></div></td><td valign='top' style='padding-bottom:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><table cellspacing='0' cellpadding='0'><tbody><tr><td style='padding-right:10px;font-family:Arial,Sans-serif;font-size:13px;color:#222'><div style='margin:0 0 0.3em 0'><span>"+fromname+"</span><span style='font-size:11px;color:#888'> - organizer</span></div></td></tbody></table></td></tr></tbody></table></td></tr></tbody></table>";
					
					for(String attende : ids)
					{
						String newglist="";
						StringBuilder attendis = new StringBuilder();
						
						String arr[]=attende.split("`");
						if(arr.length>1)
							attende=arr[1];
						else
							attende=arr[0];
						
						
							newglist=attende;
						
						
						attendis.append("ATTENDEE;ROLE=REQ-PARTICIPANT;PARTSTAT=DECLINED;RSVP=TRUE:MAILTO:"+ attende+"\n");
					
					
					
					String calendarContent =
			                "BEGIN:VCALENDAR\n" +
			                        "METHOD:CANCEL\n" +
			                        "PRODID: Silvereye \\ Silvereye Calendar 11.57 \\ EN \n" +
			                        "VERSION:2.0\n" +
			                        "BEGIN:VEVENT\n" +
			                        "DTSTAMP:" + iCalendarDateFormat.format(eventbean.getStarteventdate().toGregorianCalendar().getTime()) + "\n" ;
			                       if(eventbean.getAllday()!=null && eventbean.getAllday().equalsIgnoreCase("on"))
			                       {
			                    	   calendarContent += "DTSTART;VALUE=DATE:" + iCalendarDateFormatHalf.format(eventbean.getStarteventdate().toGregorianCalendar().getTime())+ "\n" +
					                   "DTEND;VALUE=DATE:"  + iCalendarDateFormatHalf.format(eventbean.getEndeventdate().toGregorianCalendar().getTime())+ "\n" ; 
			                    	   
			                       }
			                       else
			                       {
			                    	   calendarContent += "DTSTART:" + iCalendarDateFormat.format(eventbean.getStarteventdate().toGregorianCalendar().getTime())+ "\n" +
			                    	   "DTEND:"  + iCalendarDateFormat.format(eventbean.getEndeventdate().toGregorianCalendar().getTime())+ "\n" ;
			                       }
			                       calendarContent += "SUMMARY:"+eventbean.getSummary()+"\n" +
			                        "UID:"+eventbean.getUid()+"\n" +
			                        attendis.toString() +
			                        "ORGANIZER:MAILTO:"+id+"\n" +
			                      //  "LAST-MODIFIED:20160120T133639Z \n" +
			                        "LOCATION:"+eventbean.getLocation()+"\n" +
			                       // "CREATED:20160120T121242Z" +
			                        "DESCRIPTION:"+eventbean.getDescription()+"\n" +
			                        "SEQUENCE:0\n" +
			                        "PRIORITY:5\n" +
			                        "CLASS:"+eventbean.getClazz()+"\n" +
			                        "STATUS:CONFIRMED\n" +
			                        "TRANSP:TRANSPARENT\n" +
			                        "END:VCALENDAR";
				    GetCalendarMailResponse res12 =  calendarclient.sendCalendarMailRequest(ipAddress, id, pass, fromname, host, smtpport, newglist, sub, cntt,calendarContent, xmailer);
					System.out.println("mail response : " + res12.isSetMailStatus());
				}
				}
//				---------------------Send event cancelation mail ---------------------- 
				obj.put("success", "true");
				return obj.toJSONString();
			}
		}
		obj.put("success", "false");
		return obj.toJSONString();
	}
	@RequestMapping(value = "/reloadFullCalendear", method = RequestMethod.GET)
	@ResponseBody
	public String reloadFullCalendear(ModelMap map, Principal principal,HttpServletRequest request) {
		HttpSession hs = request.getSession();
		String id = (String) hs.getAttribute("id");
		String pass = (String) hs.getAttribute("pass");
		
		JSONObject jsonobject = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		String dt = request.getParameter("mon");
		String year = request.getParameter("year");
		
		Date fsd = null;
		Date fed = null;
		XMLGregorianCalendar fd = null;
		if(dt != null && dt.length() != 0 && year != null && year.length() != 0)
		{
			int m = Integer.parseInt(dt);
			m++;
			int yr = Integer.parseInt(year);
			yr = 1900+yr;
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			try 
			{
				
				fsd = df.parse("20/"+m+"/"+yr);
				fed = df.parse("10/"+m+"/"+yr);
				fsd.setMonth(fsd.getMonth()-1);
				fed.setMonth(fed.getMonth()+1);
				if(m == 1)
				{
					dt = "12";
					yr--;
				}
				else if(m < 11)
				{
					
					dt = "0"+(m-1);
				}
				else
				{
					dt = ""+(m-1);
				}
				String filterdate = yr+"-"+dt+"-20T00:00:00+05:30";
				fd = DatatypeFactory.newInstance().newXMLGregorianCalendar(filterdate);
			}
			catch (DatatypeConfigurationException | ParseException e) 
			{
				e.printStackTrace();
				jsonobject.put("error", "true");
				return jsonobject.toJSONString();
			}
		}
		if(fd == null)
		{
			jsonobject.put("error", "true");
			return jsonobject.toJSONString();
		}
		try
		{
			GetFileResponse fileResponse = fileClient.getFileRequest("/"+id+"/calendar", id,pass);
			List<webmail.wsdl.File> fileList = fileResponse.getGetFilesByParentFile().getFileListResult().getFileList();
			List<CreateCalendarRequest> calendarfilelist=new ArrayList<CreateCalendarRequest>();
			
//			if(fileList.size() > 1)
//			{
//				GetInviteEventResponse res =  calendarclient.getInviteEventRequest(fileList.get(2).getFileContent(), fileList.get(1).getFileContent());
//				System.out.println("res : " + res.getEventList());
//			}
			
			GetFilterEventsResponse res = null;
			for (webmail.wsdl.File file : fileList) 
			{
				JSONObject obj = null;
				res = calendarclient.getFilterEvents(file.getFileContent(), file.getFileName(),fd);
				for(EventBean event:res.getEventList().getEventList())
				{
					
					
					 DateFormat dateformat=null;
		        	 if(event.getAllday().equals("on"))
		        		 {
			        		 event.getEndeventdate().setDay(event.getEndeventdate().getDay()+1);
		        		 	dateformat=new SimpleDateFormat("yyyy-MM-dd");
		        		 }
		        	 else
		        	 {
		        		 event.getEndeventdate().setDay(event.getEndeventdate().getDay());
			        	 dateformat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		        	 }
		        	 String startdate=dateformat.format(event.getStarteventdate().toGregorianCalendar().getTime());
		        	 String enddate=dateformat.format(event.getEndeventdate().toGregorianCalendar().getTime());
		        	 
		        	 if(event.getRepeatdatetimelist()!=null)
			        	{
			        		
			        	      for(String str:event.getRepeatdatetimelist().getDateTime())
			        	      {
			        	    	  	obj = new JSONObject();
			        	    	    String []dates=str.split("`");
			        	    	      	    	   DateFormat dateformat1=new SimpleDateFormat("yyyyMMdd'T'hhmmss");
			        	    	    Date datestart=dateformat1.parse(dates[0]);
			        	    	    Date dateend=dateformat1.parse(dates[1]);
			        	    	    dateend.setDate(dateend.getDate());
			        	    	    
			        	    	    if(datestart.after(fsd) && datestart.before(fed))
			        	    	    {
			        	    	    	obj.put("id", event.getUid());
			        	    	    	obj.put("c", event.getUid());
			        	    	    	obj.put("name", "/calendar/"+event.getCalendar());
			        	    	    	obj.put("title", event.getSummary());
			        	    	    	obj.put("color", res.getCalendarcolor());
			        	    	    	obj.put("start", dateformat.format(datestart));
			        	    	    	obj.put("end", dateformat.format(dateend));
			        	    	    	jsonarray.add(obj);
			        	    	    }
			        	    	    else if(datestart.after(fed))
			        	    	    {
			        	    	    	break;
			        	    	    }
			        	    		  
			        	    	 
			        	      }
			 		    }	
			        	else
			        	{
			        		obj = new JSONObject();
			        		obj.put("id", event.getUid());
	    					obj.put("c", event.getUid());
	    					obj.put("name", "/calendar/"+event.getCalendar());
	    					obj.put("title", event.getSummary());
	    					obj.put("color", res.getCalendarcolor());
	    					obj.put("start", startdate);
	    					obj.put("end", enddate);
	    					jsonarray.add(obj);
							
			        	}
		        	 
				}
				
				
			}
			
			
			//GetFileResponse fileResponse1 = fileClient.getFileRequest("/"+id+"/sharedCalendars",id,pass);
			//fileList = fileResponse1.getGetFilesByParentFile().getFileListResult().getFileList();
			
			GetSharedFilesByPathWithContentResponse fileResponse1 = fileClient.getSharedFilesByPathWithContentRequest(id,pass, "/"+id+"/sharedCalendars") ;
			fileList = fileResponse1.getGetSharedFilesByPath().getFileListResult().getFileList();
			
			List<CreateCalendarRequest> sharedcalendarfilelist=new ArrayList<CreateCalendarRequest>();
			List<String> sharedfilenames=new ArrayList<String>();
			
			for (webmail.wsdl.File file : fileList) 
			{
				JSONObject obj = null;
				
				res = calendarclient.getFilterEvents(file.getFileContent(), file.getFileName(),fd);
				for(EventBean event:res.getEventList().getEventList())
				{
					obj = new JSONObject();
					
					 DateFormat dateformat=null;
					 event.getEndeventdate().setDay(event.getEndeventdate().getDay()+1);
		        	 if(event.getAllday().equals("on"))
	        		 {
	        		 	dateformat=new SimpleDateFormat("yyyy-MM-dd");
	        		 }
		        	 else
		        	 {
			        	 dateformat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		        	 }
		        	 String startdate=dateformat.format(event.getStarteventdate().toGregorianCalendar().getTime());
		        	 String enddate=dateformat.format(event.getEndeventdate().toGregorianCalendar().getTime());
		        	 
		        	 if(event.getRepeatdatetimelist()!=null)
			        	{
			        		
			        	      for(String str:event.getRepeatdatetimelist().getDateTime())
			        	      {
			        	    	    String []dates=str.split("`");
    	    	      	    	    DateFormat dateformat1=new SimpleDateFormat("yyyyMMdd'T'hhmmss");
			        	    	    Date datestart=dateformat1.parse(dates[0]);
			        	    	    Date dateend=dateformat1.parse(dates[1]);
			        	    	    dateend.setDate(dateend.getDate());
			        	    	    
			        	    	    if(datestart.after(fsd) && datestart.before(fed))
			        	    	    {
										obj = new JSONObject();
										obj.put("id", event.getUid());
										obj.put("c", event.getUid());
										obj.put("name", "/sharedCalendars/" + event.getCalendar());
										obj.put("title", event.getSummary());
										obj.put("color", res.getCalendarcolor());
										obj.put("start", dateformat.format(datestart));
										obj.put("end", dateformat.format(dateend));
										jsonarray.add(obj);
			        	    	    }
			        	    	    else if(datestart.after(fed))
			        	    	    {
			        	    	    	break;
			        	    	    }
			        	    		
			        	    		  
			        	    	 
			        	      }
			 		    }	
			        	else
			        	{
			        		obj = new JSONObject();
			        		obj.put("id", event.getUid());
	    					obj.put("c", event.getUid());
	    					obj.put("name", "/sharedCalendars/"+event.getCalendar());
	    					obj.put("title", event.getSummary());
	    					obj.put("color", res.getCalendarcolor());
	    					obj.put("start", startdate);
	    					obj.put("end", enddate);
	    					jsonarray.add(obj);
							
			        	}
		        	 
				}
			}
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		jsonobject.put("eventList", jsonarray);
		
		
		
		return jsonobject.toJSONString();
	}
	
	
	 @RequestMapping(value = "/setActiveCal", produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	   public @ResponseBody String setActiveCal(HttpServletRequest request, HttpServletResponse response) 
	 {
		 String res="";
		 String calnm=request.getParameter("calnm");
		 HttpSession hs=request.getSession();
		 hs.setAttribute("active_calendar",calnm);
		 return res;
	 }
	
	 @RequestMapping(value = "/importCalendar", produces = "text/html; charset=UTF-8", method = RequestMethod.POST)
	   public @ResponseBody String importCalendar(MultipartHttpServletRequest request, HttpServletResponse response) 
	 {
		 
		 String st="true";
	     Iterator<String> itr =  request.getFileNames();
	    // request.getp
	 	HttpSession hs=request.getSession();
		String host=(String)hs.getAttribute("host");
		String id=(String)hs.getAttribute("id");
		String pass=(String)hs.getAttribute("pass");
		String cal=(String)hs.getAttribute("active_calendar");
	   
	     while (itr.hasNext())
	     {
		     MultipartFile mpf = request.getFile(itr.next());
		     try
		     {
		    	 
				 int idx = mpf.getOriginalFilename().lastIndexOf('.');
			     String fileExtension = idx > 0 ? mpf.getOriginalFilename().substring(idx) : ".tmp";
			     if(fileExtension.equalsIgnoreCase(".ics") || fileExtension.equalsIgnoreCase(".ICS") )
			     {
			     File fil = File.createTempFile(mpf.getOriginalFilename(), ".ics");
			     System.out.println("!!!!!!!!!!!!!!!!!!!!!!="+fil.getPath()+"--"+fil.getName());
			     mpf.transferTo(fil);
			     byte []newcal=org.apache.commons.codec.binary.Base64.encodeBase64(IOUtils.toByteArray((InputStream) new FileInputStream(fil)));
			     String calfile = "/" + id + "/calendar/"+cal;
			     GetFileByPathResponse fileByPath = fileClient.getFileByPath(calfile, id, pass);
			     webmail.wsdl.File file = fileByPath.getFile();
			     GetImportCalendarResponse calres=  calendarclient.getImportCalendar(newcal, file.getFileContent());
			 	EditFileResponse res1 = fileClient.editEvent(file.getFilePath(),calres.getFilecontent(), id, pass);
				
				System.out.println("success : " + res1.getEditFileRes().getResponse());
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
	 
	 
	 
	 @RequestMapping(value = "/exportICSDownload", produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
		public void exportICSDownload(ModelMap map, Principal principal, HttpServletRequest request,HttpServletResponse response) 
		{
			String res="";
			// 'group_name': fldr, 'export_type': selectedVal, 'con_arr' : JSON.stringify(con_arr)
			String calfile=request.getParameter("filenm");
			
			HttpSession hs=request.getSession();
			String uid=(String)hs.getAttribute("id");
			String pass=(String)hs.getAttribute("pass");
			
			
				try
				{
					GetVCFFileResponse fileResponse =fileClient.getVCFFileRequest("/"+uid+"/calendar/"+calfile, uid,pass);
					List<VCFFileAtt> vcffileList=fileResponse.getGetVCFFilesByParentFile().getVCFFileListResult().getVCFFileList();
					// UUID uuid = UUID.randomUUID();
				   //  String randomUUIDString = uuid.toString();
				     String nm="default.ics";
				
						GetFileByPathResponse fpr= fileClient.getFileByPath("/"+uid+"/calendar/"+calfile, uid,pass);
						byte[] fl=org.apache.commons.codec.binary.Base64.decodeBase64(fpr.getFile().getFileContent());
						
					
					
					 String headerKey = "Content-Disposition";
		             String headerValue = String.format("attachment; filename=\"%s\"",  nm);
		             response.setHeader(headerKey, headerValue);
		             InputStream input = new ByteArrayInputStream(fl);
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
		//	return "exportICSDownload";
		}
}
