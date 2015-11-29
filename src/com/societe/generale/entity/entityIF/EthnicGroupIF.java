package com.societe.generale.entity.entityIF;

import java.util.List;

import com.societe.generale.entity.EthnicGroup;
import com.societe.generale.exception.SocieteGeneraleException;

public interface EthnicGroupIF {
	
	public String getEthnicGroup(Integer ethnicGroupID) throws SocieteGeneraleException;
	public List<EthnicGroup> getAllEthnicGroup() throws SocieteGeneraleException;
}
