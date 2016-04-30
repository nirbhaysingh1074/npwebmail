package webmail.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import webmail.model.ChatArchive;

@Repository("chatArchiveDao")
@Transactional
public class ChatArchiveDaoImpl implements ChatArchiveDao{

	@Autowired  
	 private SessionFactory sessionFactory1;  
	
	 @SuppressWarnings("unchecked")
	public List<ChatArchive> getOldChatList(String email)
	{
		 
		 Session session = null;
		 session = sessionFactory1.openSession();
   		 List<ChatArchive> list = session.createCriteria(ChatArchive.class)
				 .add(Restrictions.eq("bare_peer", email))
				 .list();
		session.close();
		return list;
	}
}
