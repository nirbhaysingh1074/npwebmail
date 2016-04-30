package webmail.idle;

import java.util.*;
import java.io.*;

import javax.mail.*;
import javax.mail.event.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;

import javax.activation.*;

import com.example.Connections;
import com.sun.mail.imap.*;

import webmail.chatdwr.ReverseClass;


public class IdleMail {

	private static ServletContext servletContext;
	
	 public static void inboxIdle(HttpSession hs,String host,String port,String id,String pass) {
		 try {
		
			final Store store = Connections.imapConnectionNP(host, port, id, pass);
		// IMAPStore imapStore = (IMAPStore) store;
			
			
	      //  store.connect(host, id, pass); 
	 final HttpSession ses=hs;  
	 final String uid=id;
	final IMAPFolder folder = (IMAPFolder) store.getFolder("Inbox");
	        folder.open(IMAPFolder.READ_ONLY);
	        hs.setAttribute("idleFolder", folder);
	        hs.setAttribute("idleStore", store);
	folder.addMessageCountListener(new MessageCountListener() {

	            public void messagesAdded(MessageCountEvent e) {
	            	ServerContext serverContext = ServerContextFactory
							.get(servletContext);
	                System.out.println("Message Count Event Fired---"+ e.getType());
	                Message[] msgs=e.getMessages();
	                String sub="";
	                String from="";
	                try
	                {
	                 sub=msgs[msgs.length-1].getSubject();
	                 try
	                 {
	                 sub=MimeUtility.decodeText(sub);
	                 }
	                 catch(Exception ex)
	                 {
	                	 ex.printStackTrace();
	                	 sub=msgs[msgs.length-1].getSubject();
	                 }
	                 from = InternetAddress.toString(msgs[msgs.length-1].getFrom());
	                 try
	                 {
	                	 from=MimeUtility.decodeText(from);
	                 }
	                 catch(Exception ee)
	                 {
	                	 ee.printStackTrace();
	                	 from = InternetAddress.toString(msgs[msgs.length-1].getFrom());
	                 }
	                 
	                 
	                }
	                catch(Exception eee)
	                {
	                	eee.printStackTrace();
	                }
	                System.out.println("Sn="+msgs.length);
	                (new ReverseClass()).refreshInboxDWR(serverContext, uid, from, sub);
	                
	             }

	            public void messagesRemoved(MessageCountEvent e) {
	                System.out.println("Message Removed Event fired---"+ e.getType());
	                Message[] msgs=e.getMessages();
	                System.out.println("Sn="+msgs.length);
	               
	            }
	        });

	/*folder.addMessageChangedListener(new MessageChangedListener() {

	            public void messageChanged(MessageChangedEvent e) {
	                System.out.println("Message Changed Event fired");
	            }
	        });*/
	
	
	Thread t = new Thread(new Runnable() {

        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                	
                	Store store=(Store)ses.getAttribute("idleStore");
                	if(store==null)
                	{
                		folder.close(true);
                		store.close();
                		Thread.currentThread().interrupt();
                	}
                	else
                	{
                	if(folder.isOpen() && ses!=null)
                	{
                    folder.idle();
                    }
                	else 
                	{
                		folder.close(true);
                		store.close();
                		Thread.currentThread().interrupt();
                	}
                	}
                }
            } catch (Exception ex) {
                //Handling exception goes here
            	Thread.currentThread().interrupt();
            	try
            	{
            		folder.close(true);
            		store.close();
            	}
            	catch(Exception e)
            	{
            		e.printStackTrace();
            	}
            }
        }
    });

    t.start();
	
	//t.sleep(360000);
	
		 } 
		    catch (MessagingException ex) {
		        //ex.printStackTrace();
		    	System.out.println(ex);
		    }
		    catch (Exception ex) {
		        //ex.printStackTrace();
		    	System.out.println(ex);
		    }
	 }
	
	
	

	 public static void myinbox(String host,String port,String id,String pass,String fdr,String fr) {
		  
		  Store store = null;
		    try {
		    	store=Connections.imapConnectionNP(host, port, id, pass);
		        // Open a Folder
				Folder folder = store.getFolder(fdr);
				folder.open(Folder.READ_ONLY);
			    System.out.println("folder open");
			    Message[] msg =folder.getMessages();
			    System.out.println("length="+msg.length);
		        // Add messageCountListener to listen for new messages
			    folder.addMessageCountListener(new MessageCountAdapter() {
		        public void messagesAdded(MessageCountEvent ev) {
		            Message[] msgs = ev.getMessages();
		            System.out.println("Got " + msgs.length + " new messages");

		            // Just dump out the new messages
		            for (int i = 0; i < msgs.length; i++) {
		         //  try {
		                System.out.println("-----");
		                System.out.println("Message " +msgs[i].getMessageNumber() + ":");
		              // msgs[i].writeTo(System.out);
		           /* } catch (IOException ioex) { 
		               // ioex.printStackTrace(); 
		            	System.out.println(ioex);
		            } catch (MessagingException mex) {
		              //  mex.printStackTrace();
		            	System.out.println(mex);
		            }*/
		            }
		        }
		        });

		        // Check mail once in "freq" MILLIseconds
		        int freq = Integer.parseInt(fr);
		        boolean supportsIdle = false;
		        try {
		        if (folder instanceof IMAPFolder) {
		            IMAPFolder f = (IMAPFolder)folder;
		            f.idle();
		            supportsIdle = true;
		        }
		        } catch (FolderClosedException fex) {
		        throw fex;
		        } catch (MessagingException mex) {
		        supportsIdle = false;
		        }
		        for (;;) {
		        if (supportsIdle && folder instanceof IMAPFolder) {
		            IMAPFolder f = (IMAPFolder)folder;
		            f.idle();
		            System.out.println("IDLE done");
		        } else {
		            Thread.sleep(freq); // sleep for freq milliseconds

		            // This is to force the IMAP server to send us
		            // EXISTS notifications. 
		            folder.getMessageCount();
		        }
		        }

		    } 
		    catch (MessagingException ex) {
		        //ex.printStackTrace();
		    	System.out.println(ex);
		    }
		    catch (Exception ex) {
		        //ex.printStackTrace();
		    	System.out.println(ex);
		    }
		    }
	
}
