package com.societe.generale.entity.entityIMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.societe.generale.entity.MemberDetail;
import com.societe.generale.entity.entityIF.MemberDetailIF;
import com.societe.generale.exception.SocieteGeneraleException;
import com.societe.generale.utility.HibernateUtil;

@Repository
public class MemberDetailIMPL implements MemberDetailIF {

	private Logger log = Logger.getLogger(EthnicGroupIMPL.class.getName());
	private String CLASS_NAME = "MemberDetailIMPL";
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberDetail> searchDetail(String searchText)
			throws SocieteGeneraleException {
		String METHOD_NAME="searchDetail";
		log.entering(CLASS_NAME, METHOD_NAME);
		List<MemberDetail> memberDetailList = new ArrayList<MemberDetail>();
		EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
		try {
            Query query = entityManager.createNamedQuery("getMemberDetails");
            query.setParameter(1, "%"+searchText+"%");
            query.setParameter(2, "%"+searchText+"%");
            memberDetailList = (List<MemberDetail>) query.getResultList();
       }catch (HibernateException he) {
     		log.log(Level.SEVERE,"HibernateException in fetching member for text :"+searchText+": and original exception message is :"+he.getMessage(),he);
     	}catch(Exception e){
     		log.log(Level.SEVERE,"Exception in fetching member for text :"+searchText+": and original exception message  is :"+e.getMessage(),e);
     	}finally{
      		entityManager.clear();
      		entityManager.close();
      	}
		log.exiting(CLASS_NAME, METHOD_NAME,"searchDetail");
		return memberDetailList;
	}

}
