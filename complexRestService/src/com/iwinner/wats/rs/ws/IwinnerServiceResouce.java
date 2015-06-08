package com.iwinner.wats.rs.ws;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.iwinner.wats.rs.dao.SelectionDaoIF;
import com.iwinner.wats.rs.dto.NdmnsDTO;
import com.iwinner.wats.rs.utils.BeanFactory;

@Path("iwinnerService")
public class IwinnerServiceResouce {

	@Path("{msisnd}")
	@GET
	@Produces("application/xml")
	public NdmnsDTO getSpotifyMSISDNDetails(@PathParam("msisdn") String msisdn) {
		NdmnsDTO ndmnsDTO = new NdmnsDTO();
		ndmnsDTO.setClientId("100");
		ndmnsDTO.setMsisdn("889999");
		ndmnsDTO.setEntireLine("NEw");
		ndmnsDTO.setOpId("1");
		ndmnsDTO.setMethodId("5");
		ndmnsDTO.setProcessDate(new Date());
		ndmnsDTO.setProcessTime(new Timestamp(new Date().getTime()));
		ndmnsDTO.setReturnMessage("SUccess");
		ndmnsDTO.setSpotifyID(0);
		return ndmnsDTO;
	}
	
	@Path("/json/{msisnd}")
	@GET
	@Produces("application/json")
	public NdmnsDTO getSpotifyMSISDNDetailsJSON(@PathParam("msisdn") String msisdn) {
		NdmnsDTO ndmnsDTO = new NdmnsDTO();
		ndmnsDTO.setClientId("100");
		ndmnsDTO.setMsisdn("889999");
		ndmnsDTO.setEntireLine("NEw");
		ndmnsDTO.setOpId("1");
		ndmnsDTO.setMethodId("5");
		ndmnsDTO.setProcessDate(new Date());
		ndmnsDTO.setProcessTime(new Timestamp(new Date().getTime()));
		ndmnsDTO.setReturnMessage("SUccess");
		ndmnsDTO.setSpotifyID(0);
		return ndmnsDTO;
	}
	
	@Path("/spotify/{msisdn}")
	@GET
	@Produces("application/json")
	public NdmnsDTO  getSpotifyMSISDNDetailsMSISDN(@PathParam("msisdn") String msisdn) {
		SelectionDaoIF spotifyDaoIF=(SelectionDaoIF)BeanFactory.getBean("spotifyIntegration");
		List<NdmnsDTO> listOfValues=spotifyDaoIF.spotifyDetails(msisdn);
		NdmnsDTO ndmnsDTO = new NdmnsDTO();
		for(NdmnsDTO nd:listOfValues){
			ndmnsDTO=nd;
		}
		return ndmnsDTO;
	}
	@Path("/spotifyList/{msisdn}")
	@GET
	@Produces("application/xml")
	public List<NdmnsDTO>  getSpotifyListValeus(@PathParam("msisdn") String msisdn) {
		SelectionDaoIF spotifyDaoIF=(SelectionDaoIF)BeanFactory.getBean("spotifyIntegration");
		List<NdmnsDTO> listOfValues=spotifyDaoIF.spotifyDetails(msisdn);
		return listOfValues;
	}
}
