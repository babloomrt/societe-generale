package com.societe.generale.entity.entityIF;

import java.util.List;

import com.societe.generale.entity.MemberDetail;
import com.societe.generale.exception.SocieteGeneraleException;


public interface MemberDetailIF {

	public List<MemberDetail> searchDetail(String searchText) throws SocieteGeneraleException;
}
