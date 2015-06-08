package com.iwinner.wats.rs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.iwinner.wats.rs.dto.NdmnsDTO;

public class NdmnsSelectMapper implements RowMapper {
@Override
public Object mapRow(ResultSet rs, int arg1) throws SQLException {
 NdmnsDTO ndmnsDTO=new NdmnsDTO();
 ndmnsDTO.setClientId(rs.getString("RETURNCLIENTID"));
 ndmnsDTO.setEntireLine(rs.getString("ENTIRELINE"));
 ndmnsDTO.setMethodId(rs.getString("METHODID"));
 ndmnsDTO.setOpId(rs.getString("OPID"));
 ndmnsDTO.setProcessDate(rs.getDate("PROCESS_DATE"));
 ndmnsDTO.setProcessTime(rs.getTimestamp("PROCESS_TIME"));
 ndmnsDTO.setReturnCode(rs.getString("RETURN_CODE"));
 ndmnsDTO.setReturnMessage(rs.getString("RETURNMESSAGE"));
 ndmnsDTO.setSpotifyID(rs.getInt("SpotifyID"));
 ndmnsDTO.setUpdateDate(rs.getDate("UPDATED_DATE"));
 ndmnsDTO.setMsisdn(rs.getString("MSISDN"));
 ndmnsDTO.setUpdateTime(rs.getTimestamp("UPDATEDTIME"));
	return ndmnsDTO;
}
}
