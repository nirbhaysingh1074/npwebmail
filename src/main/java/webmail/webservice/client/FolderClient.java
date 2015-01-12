package webmail.webservice.client;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import webmail.wsdl.GetComposeMailRequest;
import webmail.wsdl.GetComposeMailResponse;
import webmail.wsdl.GetInboxMailDescRequest;
import webmail.wsdl.GetInboxMailRequest;
import webmail.wsdl.GetLdapFNameRequest;
import webmail.wsdl.GetLdapFNameResponse;
import webmail.wsdl.GetMailAttachDownloadRequest;
import webmail.wsdl.GetMailAttachDownloadResponse;
import webmail.wsdl.GetMailDisplayRequest;
import webmail.wsdl.GetMailDisplayResponse;
import webmail.wsdl.GetMailInboxDescResponse;
import webmail.wsdl.GetMailInboxResponse;
import webmail.wsdl.GetWebmailAllMailCountRequest;
import webmail.wsdl.GetWebmailAllMailCountResponse;
import webmail.wsdl.GetWebmailAuthRequest;
import webmail.wsdl.GetWebmailAuthResponse;
import webmail.wsdl.GetWebmailFolderRequest;
import webmail.wsdl.GetWebmailFolderResponse;
import webmail.wsdl.GetWebmailImapquotaRequest;
import webmail.wsdl.GetWebmailImapquotaResponse;
import webmail.wsdl.GetWebmailMoveTrashRequest;
import webmail.wsdl.GetWebmailMoveTrashResponse;
import webmail.wsdl.GetWebmailQuotaRequest;
import webmail.wsdl.GetWebmailQuotaResponse;
import webmail.wsdl.GetWebmailSubFolderRequest;
import webmail.wsdl.GetWebmailSubFolderResponse;
import webmail.wsdl.GetWebmailUnreadMailCountRequest;
import webmail.wsdl.GetWebmailUnreadMailCountResponse;
import webmail.wsdl.RemoveWebmailFlagRequest;
import webmail.wsdl.RemoveWebmailFlagResponse;
import webmail.wsdl.SetWebmailFlagResponse;
import webmail.wsdl.SetWebmailFlageRequest;
import webmail.wsdl.SetWebmailSeenRequest;
import webmail.wsdl.SetWebmailSeenResponse;
import webmail.wsdl.SetWebmailUnSeenRequest;
import webmail.wsdl.SetWebmailUnSeenResponse;

public class FolderClient extends WebServiceGatewaySupport {
	
	
	
	public GetWebmailFolderResponse getWebmailFolderRequest(String host, String id, String pass)
	{
		GetWebmailFolderRequest request = new GetWebmailFolderRequest();
		request.setWebamilHost(host);
		
		request.setWebamilId(id);
		request.setWebamilPassword(pass);
		
		GetWebmailFolderResponse response =(GetWebmailFolderResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback(
						"http://localhost:8080/ws/GetWebmailFolderRequest"));
		
		return response;
		
	}
	
	
	public GetWebmailSubFolderResponse getWebmailSubFolderRequest(String host, String id, String pass, String path)
	{
		GetWebmailSubFolderRequest request=new GetWebmailSubFolderRequest();
		request.setWebamilFolderPath(path);
		request.setWebamilHost(host);
		request.setWebamilId(id);
		request.setWebamilPassword(pass);
		
		GetWebmailSubFolderResponse response=(GetWebmailSubFolderResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback("http://localhost:8080/ws/GetWebmailSubFolderRequest"));
		
		return response;
		
	}
	
	
	public GetWebmailAuthResponse getWebmailAuthRequest(String host, String port, String id, String pass)
	{
		GetWebmailAuthRequest request = new GetWebmailAuthRequest();
		request.setWebamilHost(host);
		request.setWebamilPort(port);
		request.setWebamilId(id);
		request.setWebamilPassword(pass);
		
		GetWebmailAuthResponse response =(GetWebmailAuthResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback("http://localhost:8080/ws/GetWebmailAuthRequest"));
		
		return response;
		
	}

	
	public GetWebmailImapquotaResponse getWebmailImapquotaRequest(String host, String id, String pass)
	{
		GetWebmailImapquotaRequest request = new GetWebmailImapquotaRequest();
		request.setWebamilHost(host);
		request.setWebamilId(id);
		request.setWebamilPassword(pass);
		
		GetWebmailImapquotaResponse response =(GetWebmailImapquotaResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback("http://localhost:8080/ws/GetWebmailImapquotaRequest"));
		
		return response;
		
	}
	
	
	public GetWebmailUnreadMailCountResponse getWebmailUnreadMailCountRequest(String host,String port, String id, String pass, String folder)
	{
		GetWebmailUnreadMailCountRequest request = new GetWebmailUnreadMailCountRequest();
		request.setWebamilHost(host);
		request.setWebamilPort(port);
		request.setWebamilId(id);
		request.setWebamilPassword(pass);
		request.setWebamilFolder(folder);
		GetWebmailUnreadMailCountResponse response =(GetWebmailUnreadMailCountResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback("http://localhost:8080/ws/GetWebmailUnreadMailCountRequest"));
		
		return response;
		
	}
	
	
	public GetWebmailAllMailCountResponse getWebmailAllMailCountRequest(String host,String port, String id, String pass, String folder)
	{
		GetWebmailAllMailCountRequest request = new GetWebmailAllMailCountRequest();
		request.setWebamilHost(host);
		request.setWebamilPort(port);
		request.setWebamilId(id);
		request.setWebamilPassword(pass);
		request.setWebamilFolder(folder);
		GetWebmailAllMailCountResponse response =(GetWebmailAllMailCountResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback("http://localhost:8080/ws/GetWebmailAllMailCountRequest"));
		
		return response;
		
	}
	
	
	
	
	public GetMailInboxResponse getInboxMailRequest(String host,String port, String id, String pass, String start, String end, String folder)
	{
		GetInboxMailRequest request = new GetInboxMailRequest();
		request.setWebamilHost(host);
		request.setWebamilPort(port);
		request.setWebamilId(id);
		request.setWebamilPassword(pass);
		request.setWebamilStart(start);
		request.setWebamilEnd(end);
		request.setWebamilFolder(folder);
		GetMailInboxResponse response =(GetMailInboxResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback("http://localhost:8080/ws/GetInboxMailRequest"));
		
		return response;
		
	}
	
	
	public GetMailInboxDescResponse getInboxMailRequestDesc(String host,String port, String id, String pass, String start, String end, String folder)
	{
		GetInboxMailDescRequest request = new GetInboxMailDescRequest();
		request.setWebamilHost(host);
		request.setWebamilPort(port);
		request.setWebamilId(id);
		request.setWebamilPassword(pass);
		request.setWebamilStart(start);
		request.setWebamilEnd(end);
		request.setWebamilFolder(folder);
		GetMailInboxDescResponse response =(GetMailInboxDescResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback("http://localhost:8080/ws/GetInboxMailDescRequest"));
		
		return response;
		
	}
	
	
	
	public SetWebmailFlagResponse setFlagActionRequest(String host,String port, String id, String pass, String folder, String uids)
	{
		SetWebmailFlageRequest request = new SetWebmailFlageRequest();
		request.setWebamilHost(host);
		request.setWebamilPort(port);
		request.setWebamilId(id);
		request.setWebamilPassword(pass);
		request.setWebmailFolder(folder);
		request.setWebamilUids(uids);
		
		SetWebmailFlagResponse response =(SetWebmailFlagResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback("http://localhost:8080/ws/SetWebmailFlageRequest"));
		
		return response;
		
	}
	
	
	public RemoveWebmailFlagResponse removeFlagActionRequest(String host,String port, String id, String pass, String folder, String uids)
	{
		RemoveWebmailFlagRequest request = new RemoveWebmailFlagRequest();
		request.setWebmailHost(host);
		request.setWebmailPost(port);
		request.setWebmailId(id);
		request.setWebmailPassword(pass);
		request.setWebmailFolder(folder);
		request.setWebmailUids(uids);
		
		RemoveWebmailFlagResponse response =(RemoveWebmailFlagResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback("http://localhost:8080/ws/RemoveWebmailFlagRequest"));
		
		return response;
		
	}
	
	
	
	public SetWebmailSeenResponse setSeenActionRequest(String host,String port, String id, String pass, String folder, String uids)
	{
		SetWebmailSeenRequest request = new SetWebmailSeenRequest();
		request.setWebamilHost(host);
		request.setWebamilPort(port);
		request.setWebamilId(id);
		request.setWebamilPassword(pass);
		request.setWebmailFolder(folder);
		request.setWebamilUids(uids);
		
		SetWebmailSeenResponse response =(SetWebmailSeenResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback("http://localhost:8080/ws/SetWebmailSeenRequest"));
		
		return response;
		
	}
	
	
	
	public SetWebmailUnSeenResponse setUnSeenActionRequest(String host,String port, String id, String pass, String folder, String uids)
	{
		SetWebmailUnSeenRequest request = new SetWebmailUnSeenRequest();
		request.setWebmailHost(host);
		request.setWebmailPort(port);
		request.setWebmailId(id);
		request.setWebmailPassword(pass);
		request.setWebmailFolder(folder);
		request.setWebmailUids(uids);
		
		SetWebmailUnSeenResponse response =(SetWebmailUnSeenResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback("http://localhost:8080/ws/SetWebmailUnSeenRequest"));
		
		return response;
		
	}
	
	
	
	
	
	
	public GetWebmailMoveTrashResponse  moveToTrashRequest(String host,String port, String id, String pass, String folder, String uids)
	{
		GetWebmailMoveTrashRequest request = new GetWebmailMoveTrashRequest();
		request.setWebamilHost(host);
		request.setWebamilPort(port);
		request.setWebamilId(id);
		request.setWebamilPassword(pass);
		request.setWebamilFolder(folder);
		request.setWebamilUids(uids);
		
		GetWebmailMoveTrashResponse response =(GetWebmailMoveTrashResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback("http://localhost:8080/ws/GetWebmailMoveTrashRequest"));
		
		return response;
		
	}
	
	public GetMailDisplayResponse  displayMailContentRequest(String host,String port, String id, String pass,String uid, String folder)
	{
		GetMailDisplayRequest request = new GetMailDisplayRequest();
		request.setWebamilHost(host);
		request.setWebamilPort(port);
		request.setWebamilId(id);
		request.setWebamilPassword(pass);
		request.setWebamilFolder(folder);
		request.setWebamilUid(uid);
		
		GetMailDisplayResponse response =(GetMailDisplayResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback("http://localhost:8080/ws/GetMailDisplayRequest"));
		
		return response;
		
	}
	
	
	public GetMailAttachDownloadResponse  downloadMailAttachRequest(String host,String port, String id, String pass,String uid, String folder, String name)
	{
		GetMailAttachDownloadRequest request = new GetMailAttachDownloadRequest();
		request.setWebamilHost(host);
		request.setWebamilPort(port);
		request.setWebamilId(id);
		request.setWebamilPassword(pass);
		request.setWebamilFolder(folder);
		request.setWebamilUid(uid);
		request.setWebamilFileName(name);
		//request.setWebamilResponse(resp);
		
		GetMailAttachDownloadResponse response =(GetMailAttachDownloadResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback("http://localhost:8080/ws/GetMailDisplayRequest"));
		
		return response;
		
	}
	
	
	public GetComposeMailResponse  comoseMailRequest(String ip, String host, String port,String imapport, boolean savesent, String id, String pass,String fromname, String to, String cc, String bcc, String sub ,String cnt)
	{
		GetComposeMailRequest request = new GetComposeMailRequest();
		request.setWebamilHost(host);
		request.setWebamilPort(port);
		request.setWebamilIMAPPort(imapport);
		request.setWebamilSaveSent(savesent);
		request.setWebamilId(id);
		request.setWebamilPassword(pass);
		request.setWebamilFromName(fromname);
		request.setWebamilTo(to);
		request.setWebamilCc(cc);
		request.setWebamilBcc(bcc);
		request.setWebamilSubject(sub);
		request.setWebamilBodyContent(cnt);
		
		//request.setWebamilResponse(resp);
		
		GetComposeMailResponse response =(GetComposeMailResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback("http://localhost:8080/ws/GetComposeMailRequest"));
		
		return response;
		
	}
	

	public GetLdapFNameResponse  getLdapFNmae(String ldapurl, String uid, String pass, String base, String attname)
	{
		GetLdapFNameRequest request = new GetLdapFNameRequest();
		request.setWebamilUrl(ldapurl);
		request.setWebamilId(uid);
		request.setWebamilPassword(pass);
		request.setWebmailBase(base);
		request.setWebamilAttName(attname);
		
		GetLdapFNameResponse response =(GetLdapFNameResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback("http://localhost:8080/ws/GetLdapFNameRequest"));
		
		return response;
		
	}
	


	
}
