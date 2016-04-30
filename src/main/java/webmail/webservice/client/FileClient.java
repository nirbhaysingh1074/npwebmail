package webmail.webservice.client;

import java.text.SimpleDateFormat;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import webmail.wsdl.AssignSinglePermissionRequest;
import webmail.wsdl.AssignSinglePermissionResponse;
import webmail.wsdl.CreateFileRequest;
import webmail.wsdl.CreateFileDAVRequest;
import webmail.wsdl.CreateFileResponse;
import webmail.wsdl.DeleteFileRequest;
import webmail.wsdl.DeleteFileDAVRequest;
import webmail.wsdl.DeleteFileResponse;
import webmail.wsdl.EditFileRequest;
import webmail.wsdl.EditFileDAVRequest;
import webmail.wsdl.EditFileResponse;
import webmail.wsdl.FileListReturn;
import webmail.wsdl.GetFileByPathRequest;
import webmail.wsdl.GetFileByPathDAVRequest;
import webmail.wsdl.GetFileByPathResponse;
import webmail.wsdl.GetFileRequest;
import webmail.wsdl.GetFileDAVRequest;
import webmail.wsdl.GetFileResponse;
import webmail.wsdl.GetFileWithOutStreamRequest;
import webmail.wsdl.GetFileWithOutStreamDAVRequest;
import webmail.wsdl.GetFileWithOutStreamResponse;
import webmail.wsdl.GetRecycledDocsRequest;
import webmail.wsdl.GetRecycledDocsResponse;
import webmail.wsdl.GetSharedFilesByPathRequest;
import webmail.wsdl.GetSharedFilesByPathResponse;
import webmail.wsdl.GetSharedFilesByPathWithContentRequest;
import webmail.wsdl.GetSharedFilesByPathWithContentDAVRequest;
import webmail.wsdl.GetSharedFilesByPathWithContentResponse;
import webmail.wsdl.GetSharedFilesRequest;
import webmail.wsdl.GetSharedFilesResponse;
import webmail.wsdl.GetVCFFileRequest;
import webmail.wsdl.GetVCFFileDAVRequest;
import webmail.wsdl.GetVCFFileResponse;
import webmail.wsdl.HasChildRequest;
import webmail.wsdl.HasChildResponse;
import webmail.wsdl.RecycleFileRequest;
import webmail.wsdl.RecycleFileResponse;
import webmail.wsdl.RemoveAssignedPermissionRequest;
import webmail.wsdl.RemoveAssignedPermissionDAVRequest;
import webmail.wsdl.RemoveAssignedPermissionResponse;
import webmail.wsdl.RenameFileRequest;
import webmail.wsdl.RenameFileResponse;
import webmail.wsdl.RestoreFileRequest;
import webmail.wsdl.RestoreFileResponse;
import webmail.wsdl.RestoreVersionRequest;
import webmail.wsdl.RestoreVersionResponse;
import webmail.wsdl.ShareFileByPathRequest;
import webmail.wsdl.ShareFileByPathResponse;

public class FileClient extends WebServiceGatewaySupport {

	public EditFileResponse editEvent(String filePath,byte[] filecontent,String userid,String password) 
	{
		EditFileDAVRequest request = new EditFileDAVRequest();
		request.setFileContent(filecontent);
		request.setFilePath(filePath);
		request.setUserid(userid);
		request.setPassword(password);
		EditFileResponse response =(EditFileResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback(
						"http://mail.silvereye.in:8080/ws/editFileDAVRequest"));
		return response;
	}
	
	// onfile processing
	public GetFileWithOutStreamResponse getFileWithOutStreamRequest(String path, String userid,String password) {
		GetFileWithOutStreamDAVRequest request = new GetFileWithOutStreamDAVRequest();
		request.setFilePath(path);
		request.setUserid(userid);request.setPassword(password);
		GetFileWithOutStreamResponse response = (GetFileWithOutStreamResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/GetFileWithOutStreamDAVRequest"));

		return response;
	}
	
	public GetFileWithOutStreamResponse getFileWithOutStreamRequestBC(String path, String userid,String password) {
		GetFileWithOutStreamRequest request = new GetFileWithOutStreamRequest();
		request.setFilePath(path);
		request.setUserid(userid);request.setPassword(password);
		GetFileWithOutStreamResponse response = (GetFileWithOutStreamResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/GetFileWithOutStreamRequest"));

		return response;
	}
	
	public GetVCFFileResponse getVCFFileRequest(String path, String userid,String password) {
		GetVCFFileDAVRequest request = new GetVCFFileDAVRequest();
		request.setFilePath(path);
		request.setUserid(userid);
		request.setPassword(password);
		GetVCFFileResponse response = (GetVCFFileResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/GetVCFFileDAVRequest"));

		return response;
	}
	
	
		public GetFileResponse getFileRequest(String path, String userid,String password) {
		GetFileDAVRequest request = new GetFileDAVRequest();
		request.setFilePath(path);
		request.setUserid(userid);request.setPassword(password);
		GetFileResponse response = (GetFileResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/GetFileDAVRequest"));

		return response;
	}

	public GetSharedFilesResponse getSharedFilesRequest(String userid,String password) {
		GetSharedFilesRequest request = new GetSharedFilesRequest();
		request.setUserid(userid);request.setPassword(password);
		GetSharedFilesResponse response = (GetSharedFilesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/GetSharedFilesRequest"));

		return response;
	}

	public GetSharedFilesByPathWithContentResponse getSharedFilesByPathWithContentRequest(
			String userid,String password, String path) {
		GetSharedFilesByPathWithContentDAVRequest request = new GetSharedFilesByPathWithContentDAVRequest();
		request.setUserid(userid);request.setPassword(password);
		request.setPath(path);
		GetSharedFilesByPathWithContentResponse response = (GetSharedFilesByPathWithContentResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/GetSharedFilesByPathWithContentDAVRequest"));

		return response;
	}
	
	public RemoveAssignedPermissionResponse removeAssignedPermission(
            String folderName, String userid, String password, String user,
            String value) {
        RemoveAssignedPermissionDAVRequest request=new RemoveAssignedPermissionDAVRequest();
        request.setFolderPath(folderName);
        request.setUser(user);
        request.setValue(value);
        request.setUserid(userid);
        request.setPassword(password);
       
        RemoveAssignedPermissionResponse response=(RemoveAssignedPermissionResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        request,
                        new SoapActionCallback(
                                "http://mail.silvereye.in:8080/ws/removeAssignedPermissionDAVRequest"));
        return response;
    }

public void printResponse(GetFileResponse response) {
		FileListReturn country = response.getGetFilesByParentFile();

		System.out.println();
		System.out.println("Forecast for " + ", "
				+ country.getFileListResult().getFileList().size());

		/*
		 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 * System.out
		 * .print(format.format(forecast.getDate().toGregorianCalendar(
		 * ).getTime())); System.out.print(" ");
		 * System.out.print(forecast.getDesciption()); System.out.print(" ");
		 * Temp temperature = forecast.getTemperatures();
		 * System.out.print(temperature.getMorningLow() + "\u00b0-" +
		 * temperature.getDaytimeHigh() + "\u00b0 "); System.out.println(); } }
		 * else { System.out.println("No forecast received"); }
		 */
	}

	

	/*public CreateFileResponse createFile(String fileName, String parentFile,
            String userid, String keywords, String notes, String fileContent) {
        CreateFileRequest request = new CreateFileRequest();
        request.setFileName(fileName);
        request.setParentFile(parentFile);
        request.setUserid(userid);
        request.setKeywords(keywords);
        request.setNotes(notes);
       request.setFileContent(fileContent);
       
        System.out.println(fileName + " " + parentFile + " " + userid);
        CreateFileResponse response = (CreateFileResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        request,
                        new SoapActionCallback(
                                "http://mail.silvereye.in:8080/ws/CreateFileRequest"));
        return response;
    }*/
	
public CreateFileResponse createFile(String filename, String parentFolder,
			String userid,String password, String keywords, String notes, byte[] fileContent,long filesize) {

		CreateFileDAVRequest request = new CreateFileDAVRequest();
		request.setFileName(filename);
		request.setParentFile(parentFolder);
		request.setUserid(userid);request.setPassword(password);
		request.setKeywords(keywords);
		request.setFileSize(filesize);
//		String fileContent=IOUtils.toString(is);
		//System.out.println(fileContent);
		request.setNotes(notes);
		request.setFileContent(fileContent);
		CreateFileResponse response = (CreateFileResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/CreateFileDAVRequest"));
		return response;
	
	}


public CreateFileResponse createFileBC(String filename, String parentFolder,
		String userid,String password, String keywords, String notes, byte[] fileContent,long filesize) {

	CreateFileRequest request = new CreateFileRequest();
	request.setFileName(filename);
	request.setParentFile(parentFolder);
	request.setUserid(userid);request.setPassword(password);
	request.setKeywords(keywords);
	request.setFileSize(filesize);
//	String fileContent=IOUtils.toString(is);
	//System.out.println(fileContent);
	request.setNotes(notes);
	request.setFileContent(fileContent);
	CreateFileResponse response = (CreateFileResponse) getWebServiceTemplate()
			.marshalSendAndReceive(
					request,
					new SoapActionCallback(
							"http://mail.silvereye.in:8080/ws/CreateFileRequest"));
	return response;

}
	
	

	public GetFileByPathResponse getFileByPath(String filePath, String userid,String password) {
		GetFileByPathDAVRequest request = new GetFileByPathDAVRequest();
		request.setFilePath(filePath);
		request.setUserid(userid);
		request.setPassword(password);

		GetFileByPathResponse response = (GetFileByPathResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/GetFileByPathDAVRequest"));

		return response;
	}

	public GetFileByPathResponse getFileByPathBC(String filePath, String userid,String password) {
		GetFileByPathRequest request = new GetFileByPathRequest();
		request.setFilePath(filePath);
		request.setUserid(userid);
		request.setPassword(password);

		GetFileByPathResponse response = (GetFileByPathResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/GetFileByPathRequest"));

		return response;
	}
	
	public ShareFileByPathResponse shareFileByPath(String filePath,
			String userid,String password, String users, String groups, String userpermissions,
			String grouppermissions) {
		ShareFileByPathRequest request = new ShareFileByPathRequest();
		request.setFilePath(filePath);
		request.setUserid(userid);request.setPassword(password);
		request.setUsers(users);
		request.setGroups(groups);
		request.setUserpermissions(userpermissions);
		request.setGrouppermissions(grouppermissions);

		ShareFileByPathResponse response = (ShareFileByPathResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/ShareFileByPathRequest"));

		return response;
	}

	

	public DeleteFileResponse deleteFile(String fileName, String userid,String password) {
		DeleteFileDAVRequest request = new DeleteFileDAVRequest();
		request.setUserid(userid);request.setPassword(password);
		request.setFilePath(fileName);
		DeleteFileResponse deleteResponse = (DeleteFileResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/deleteFileDAVRequest"));
		return deleteResponse;
	}

	public RecycleFileResponse recycleFile(String fileName, String userid,String password) {
		RecycleFileRequest request = new RecycleFileRequest();
		request.setUserid(userid);request.setPassword(password);
		request.setFilePath(fileName);
		RecycleFileResponse deleteResponse = (RecycleFileResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/recycleFileRequest"));
		return deleteResponse;
	}

	public RestoreFileResponse restoreFile(String fileName, String userid,String password) {
		RestoreFileRequest request = new RestoreFileRequest();
		request.setUserid(userid);request.setPassword(password);
		request.setFilePath(fileName);
		RestoreFileResponse deleteResponse = (RestoreFileResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/restoreFileRequest"));
		return deleteResponse;
	}


public GetRecycledDocsResponse getRecycledDoc(String userid,String password,
			String folderName) {
		GetRecycledDocsRequest request = new GetRecycledDocsRequest();
		request.setUserid(userid);request.setPassword(password);
		request.setPath(folderName);
		GetRecycledDocsResponse deleteResponse = (GetRecycledDocsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/getRecycledDocsRequest"));
		return deleteResponse;
	}

	public RenameFileResponse renameFile(String oldFileName,
			String newFileName, String userid,String password) {
		RenameFileRequest request = new RenameFileRequest();
		request.setOldName(oldFileName);
		request.setNewName(newFileName);
		request.setUserid(userid);request.setPassword(password);
		RenameFileResponse renameResponse = (RenameFileResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/renameFileRequest"));
		return renameResponse;
	}

	

}
