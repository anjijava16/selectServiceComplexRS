package com.iwinner.wats.rs.dao;

import java.util.List;

import com.iwinner.wats.rs.dto.NdmnsDTO;
import com.iwinner.wats.rs.dto.SpotifyCodesDTO;

public interface SelectionDaoIF {
	public void insertSpotifyProcessRecord(final List<NdmnsDTO> ndmnsList);
	public void insertSpotifyCodes(List<SpotifyCodesDTO> listSpotifyCodes);
	//public   void listOfUUPR();
	public  void insertUUPR();
	public  void insertUUPRInformation();
	public List<NdmnsDTO> spotifyDetails(String msisdn);
}
