package com.societe.generale.service.serviceIMPL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.societe.generale.entity.EthnicGroup;
import com.societe.generale.entity.MemberDetail;
import com.societe.generale.entity.entityIF.EthnicGroupIF;
import com.societe.generale.entity.entityIF.MemberDetailIF;
import com.societe.generale.exception.SocieteGeneraleException;
import com.societe.generale.model.formbean.MemberDetailBean;
import com.societe.generale.service.serviceIF.MemberDetailServiceIF;


@Service
public class MemberDetailServiceIMPL implements MemberDetailServiceIF{

	@Autowired
	private MemberDetailIF memberDetailIF;
	@Autowired
	private EthnicGroupIF ethnicGroupIF;
	@Override
	public List<MemberDetailBean> searchDetail(String searchText) throws SocieteGeneraleException {
		List<MemberDetail> memberDetailList = memberDetailIF.searchDetail(searchText);
		List<MemberDetailBean> memberDetailBeanList = convertEntityIntoFormBean(memberDetailList);
		return memberDetailBeanList;
	}

	private List<MemberDetailBean> convertEntityIntoFormBean(List<MemberDetail> memberDetailList) {
		List<MemberDetailBean> memberDetailBeanList = new ArrayList<MemberDetailBean>();
		MemberDetailBean memberDetailBean = null;
		for (MemberDetail memberDetail:memberDetailList) {
			memberDetailBean = new MemberDetailBean();
			memberDetailBean.setMemberID(memberDetail.getMemberID());
			memberDetailBean.setStatus(memberDetail.getStatus());
			memberDetailBean.setHeightInCM(memberDetail.getHeight());
			memberDetailBean.setWeightInKG(memberDetail.getWeight()/1000);
			memberDetailBean.setRace(getEthnicGroup(Integer.parseInt(memberDetail.getRace())));
			if (memberDetail.getIsVeg() == "0")
				memberDetailBean.setIsVeg("FALSE");
			else
				memberDetailBean.setIsVeg("TRUE");
			memberDetailBeanList.add(memberDetailBean);
		}
		return memberDetailBeanList;
	}

	private String getEthnicGroup(Integer ethnicID) {
		String race = null;
		try {
			List<EthnicGroup> ethnicGroupList = null;
			if (ethnicGroupList == null) {
				ethnicGroupList = ethnicGroupIF.getAllEthnicGroup();
			}
			race = ethnicGroupList.get(ethnicID).getGroup_Name();
		} catch (SocieteGeneraleException e) {
			e.printStackTrace();
		}
		
		return race;
	}
}
