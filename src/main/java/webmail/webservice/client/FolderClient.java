package webmail.webservice.client;

import java.text.SimpleDateFormat;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import webmail.wsdl.AssignSinglePermissionDAVRequest;
import webmail.wsdl.AssignSinglePermissionRequest;
import webmail.wsdl.AssignSinglePermissionResponse;
import webmail.wsdl.CreateEssentialFoldersRequest;
import webmail.wsdl.CreateEssentialFoldersResponse;
import webmail.wsdl.CreateFolderDAVRequest;
import webmail.wsdl.CreateFolderRequest;
import webmail.wsdl.CreateFolderResponse;
import webmail.wsdl.DeleteFolderRequest;
import webmail.wsdl.DeleteFolderResponse;
import webmail.wsdl.FolderListReturn;
import webmail.wsdl.GetFolderByPathDAVRequest;
import webmail.wsdl.GetFolderByPathRequest;
import webmail.wsdl.GetFolderByPathResponse;
import webmail.wsdl.GetFolderDAVRequest;
import webmail.wsdl.GetFolderRequest;
import webmail.wsdl.GetFolderResponse;
import webmail.wsdl.GetRecycledDocsRequest;
import webmail.wsdl.GetRecycledDocsResponse;
import webmail.wsdl.GetSharedFoldersByPathDAVRequest;
import webmail.wsdl.GetSharedFoldersByPathRequest;
import webmail.wsdl.GetSharedFoldersByPathResponse;
import webmail.wsdl.GetSharedFoldersRequest;
import webmail.wsdl.GetSharedFoldersResponse;
import webmail.wsdl.HasChildRequest;
import webmail.wsdl.HasChildResponse;
import webmail.wsdl.RecycleFolderDAVRequest;
import webmail.wsdl.RecycleFolderRequest;
import webmail.wsdl.RecycleFolderResponse;
import webmail.wsdl.RenameFolderRequest;
import webmail.wsdl.RenameFolderResponse;
import webmail.wsdl.RestoreFolderRequest;
import webmail.wsdl.RestoreFolderResponse;
import webmail.wsdl.RestoreVersionRequest;
import webmail.wsdl.RestoreVersionResponse;
import webmail.wsdl.ShareFolderByPathRequest;
import webmail.wsdl.ShareFolderByPathResponse;

public class FolderClient extends WebServiceGatewaySupport {


	public CreateEssentialFoldersResponse createEssentialFolders( String userid,String password) {
		CreateEssentialFoldersRequest request = new CreateEssentialFoldersRequest();
		request.setUserid(userid);request.setPassword(password);
		CreateEssentialFoldersResponse response = (CreateEssentialFoldersResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/CreateEssentialFoldersRequest"));
		return response;
	}

	
	public GetFolderResponse getFolderRequest(String path, String userid,String password) {
		GetFolderDAVRequest request = new GetFolderDAVRequest();
		request.setFolderPath(path);
		request.setUserid(userid);request.setPassword(password);
		GetFolderResponse response = (GetFolderResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/GetFolderDAVRequest"));
		return response;
	}

	public GetFolderResponse getFolderRequestBC(String path, String userid,String password) {
		GetFolderRequest request = new GetFolderRequest();
		request.setFolderPath(path);
		request.setUserid(userid);request.setPassword(password);
		GetFolderResponse response = (GetFolderResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/GetFolderRequest"));
		return response;
	}
	
	public GetSharedFoldersResponse getSharedFoldersRequest(String userid,String password) {
		GetSharedFoldersRequest request = new GetSharedFoldersRequest();
		request.setUserid(userid);
		request.setPassword(password);
		GetSharedFoldersResponse response = (GetSharedFoldersResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/GetSharedFoldersRequest"));

		return response;
	}

public GetSharedFoldersByPathResponse getSharedFoldersByPathRequest(
			String userid,String password, String path) {
		GetSharedFoldersByPathDAVRequest request = new GetSharedFoldersByPathDAVRequest();
		request.setUserid(userid);request.setPassword(password);
		request.setPath(path);
		GetSharedFoldersByPathResponse response = (GetSharedFoldersByPathResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/GetSharedFoldersByPathDAVRequest"));

		return response;
	}

public void printResponse(GetFolderResponse response) {
		FolderListReturn country = response.getGetFoldersByParentFolder();
		System.out.println();
		System.out.println("Forecast for " + ", "
				+ country.getFolderListResult().getFolderList().size());

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
		public HasChildResponse hasChild(String path, String userid,String password) {
		HasChildRequest request = new HasChildRequest();
		request.setFolderPath(path);
		request.setUserid(userid);request.setPassword(password);
		HasChildResponse response = (HasChildResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/HasChildRequest"));
		return response;
	}

public CreateFolderResponse createFolder(String folderName,
			String parentFolder, String userid,String password, String keywords, String notes) {
		CreateFolderDAVRequest request = new CreateFolderDAVRequest();
		request.setFolderName(folderName);
		request.setParentFolder(parentFolder);
		request.setUserid(userid);request.setPassword(password);
		request.setKeywords(keywords);
		request.setNotes(notes);
		CreateFolderResponse response = (CreateFolderResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/CreateFolderDAVRequest"));
		return response;
	}

public GetFolderByPathResponse getFolderByPath(String folderPath,
			String userid,String password) {
		GetFolderByPathDAVRequest request = new GetFolderByPathDAVRequest();
		request.setFolderPath(folderPath);
		request.setUserid(userid);request.setPassword(password);
		GetFolderByPathResponse response = (GetFolderByPathResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/GetFolderByPathDAVRequest"));
		return response;
	}

public GetFolderByPathResponse getFolderByPathBC(String folderPath,
		String userid,String password) {
	GetFolderByPathRequest request = new GetFolderByPathRequest();
	request.setFolderPath(folderPath);
	request.setUserid(userid);request.setPassword(password);
	GetFolderByPathResponse response = (GetFolderByPathResponse) getWebServiceTemplate()
			.marshalSendAndReceive(
					request,
					new SoapActionCallback(
							"http://mail.silvereye.in:8080/ws/GetFolderByPathRequest"));
	return response;
}

	public ShareFolderByPathResponse shareFolderByPath(String folderPath,
			String userid,String password, String users, String groups, String userpermissions,
			String grouppermissions) {
		ShareFolderByPathRequest request = new ShareFolderByPathRequest();
		request.setFolderPath(folderPath);
		request.setUserid(userid);request.setPassword(password);
		request.setUsers(users);
		request.setGroups(groups);
		request.setUserpermissions(userpermissions);
		request.setGrouppermissions(grouppermissions);

		ShareFolderByPathResponse response = (ShareFolderByPathResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/ShareFolderByPathRequest"));

		return response;
	}

		public AssignSinglePermissionResponse assignSinglePermission(
			String folderName, String userid,String password, String user, String value) {
		AssignSinglePermissionDAVRequest request = new AssignSinglePermissionDAVRequest();
		request.setFolderPath(folderName);
		request.setUser(user);
		request.setValue(value);
		request.setUserid(userid);request.setPassword(password);

		AssignSinglePermissionResponse response = (AssignSinglePermissionResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/AssignSinglePermissionDAVRequest"));

		return response;
	}

		public DeleteFolderResponse deleteFolder(String folderName, String userid,String password) {
		DeleteFolderRequest request = new DeleteFolderRequest();
		request.setUserid(userid);request.setPassword(password);
		request.setFolderPath(folderName);
		DeleteFolderResponse deleteResponse = (DeleteFolderResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/deleteFolderRequest"));
		return deleteResponse;
	}

	public RecycleFolderResponse recycleFolder(String folderName, String userid,String password) {
		RecycleFolderDAVRequest request = new RecycleFolderDAVRequest();
		request.setUserid(userid);request.setPassword(password);
		request.setFolderPath(folderName);
		RecycleFolderResponse deleteResponse = (RecycleFolderResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/recycleFolderDAVRequest"));
		return deleteResponse;
	}

	public RestoreFolderResponse restoreFolder(String folderName, String userid,String password) {
		RestoreFolderRequest request = new RestoreFolderRequest();
		request.setUserid(userid);request.setPassword(password);
		request.setFolderPath(folderName);
		RestoreFolderResponse deleteResponse = (RestoreFolderResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/restoreFolderRequest"));
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

	public RenameFolderResponse renameFolder(String oldFolderName,
			String newFolderName, String userid,String password) {
		RenameFolderRequest request = new RenameFolderRequest();
		request.setOldName(oldFolderName);
		request.setNewName(newFolderName);
		request.setUserid(userid);request.setPassword(password);
		RenameFolderResponse renameResponse = (RenameFolderResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/renameFolderRequest"));
		return renameResponse;
	}

	public RestoreVersionResponse restoreVersion(String folderPath,
			String versionName, String userid,String password) {
		RestoreVersionRequest request = new RestoreVersionRequest();
		request.setFolderPath(folderPath);
		request.setUserid(userid);request.setPassword(password);
		request.setVersionName(versionName);
		RestoreVersionResponse restoreResponse = (RestoreVersionResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://mail.silvereye.in:8080/ws/restoreVersionRequest"));
		return restoreResponse;
	}

}
