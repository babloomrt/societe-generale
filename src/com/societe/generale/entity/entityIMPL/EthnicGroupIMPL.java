package com.societe.generale.entity.entityIMPL;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.societe.generale.entity.EthnicGroup;
import com.societe.generale.entity.entityIF.EthnicGroupIF;
import com.societe.generale.exception.SocieteGeneraleException;
import com.societe.generale.utility.HibernateUtil;

@Repository
public class EthnicGroupIMPL implements EthnicGroupIF{

	private Logger log = Logger.getLogger(EthnicGroupIMPL.class.getName());
	private String CLASS_NAME = "EthnicGroupIMPL";
	@Override
	public String getEthnicGroup(Integer ethnicGroupID)
			throws SocieteGeneraleException {

		String METHOD_NAME="searchAdoptedTg";
		log.entering(CLASS_NAME, METHOD_NAME);
		EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
		String ethnicGroupName = null;
		try {
            Query query = entityManager.createNamedQuery("getEthnicGroup");
            query.setParameter(1, "ethnicGroupID");
            ethnicGroupName = (String) query.getSingleResult();
       }catch (HibernateException he) {
     		log.log(Level.SEVERE,"HibernateException in fetching Ethnic Group for :"+ethnicGroupID+": and original exception message is :"+he.getMessage(),he);
     	}catch(Exception e){
     		log.log(Level.SEVERE,"Exception in fetching Ethnic Group for :"+ethnicGroupID+": and original exception message  is :"+e.getMessage(),e);
     	}finally{
      		entityManager.clear();
      		entityManager.close();
      	}
		log.exiting(CLASS_NAME, METHOD_NAME,"getEthnicGroup");
		return ethnicGroupName;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EthnicGroup> getAllEthnicGroup() throws SocieteGeneraleException {
		String METHOD_NAME="getAllEthnicGroup";
		log.entering(CLASS_NAME, METHOD_NAME);
		EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
		List<EthnicGroup> ethnicGroupList = null;
		try {
            Query query = entityManager.createNamedQuery("getAllEthnicGroup");
            ethnicGroupList = (List<EthnicGroup>) query.getResultList();
       }catch (HibernateException he) {
     		log.log(Level.SEVERE,"HibernateException in fetching Ethnic Group and original exception message is :"+he.getMessage(),he);
     	}catch(Exception e){
     		log.log(Level.SEVERE,"Exception in fetching Ethnic Group and original exception message  is :"+e.getMessage(),e);
     	}finally{
      		entityManager.clear();
      		entityManager.close();
      	}
		log.exiting(CLASS_NAME, METHOD_NAME,"getAllEthnicGroup");
		return ethnicGroupList;
	}

}
