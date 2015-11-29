package com.societe.generale.service.serviceIF;

import java.util.List;

import com.societe.generale.exception.SocieteGeneraleException;
import com.societe.generale.model.formbean.MemberDetailBean;

public interface MemberDetailServiceIF {

	public List<MemberDetailBean> searchDetail(String searchText) throws SocieteGeneraleException;
}
