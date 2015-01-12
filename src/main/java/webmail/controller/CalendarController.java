package webmail.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import webmail.webservice.client.FolderClient;


@Controller
public class CalendarController {

	
	@Autowired
	private FolderClient folderClient;
	
	
}
