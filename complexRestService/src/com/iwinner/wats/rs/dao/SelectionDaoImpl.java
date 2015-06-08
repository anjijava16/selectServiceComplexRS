package com.iwinner.wats.rs.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.iwinner.wats.rs.dto.NdmnsDTO;
import com.iwinner.wats.rs.dto.SpotifyCodesDTO;
import com.iwinner.wats.rs.utils.BeanFactory;
import com.iwinner.wats.rs.utils.WapConstants;

public class SelectionDaoImpl implements SelectionDaoIF {
	public static String INSERT_SPOTIFY_CODES="INSERT INTO spotify_codes(ID,MESSAGE,ISSUE_SYSTEM,RETURN_MESSAGE) VALUES (?,?,?,?)";
	public static String UUPR_INSERTION_QUERY="INSERT INTO UUPR (MSISDN,GENDER,NATIOANLITY,ETHNIC_RACE,DOB,LANG,MARKET_SEGMENT,MARKET_CODE,TIMESTAMP_REG,PREPAID_POSTPAID) VALUES(?,?,?,?,?,?,?,?,?,?)";
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Integer insertDaoImpl(NdmnsDTO ndmnsDTO) {
		JdbcTemplate jdbcTemp = new JdbcTemplate(dataSource);
		Integer id = jdbcTemp.update(
				WapConstants.INSERT_SPOTIFY_RECORD,
				new Object[] { ndmnsDTO.getMsisdn(), ndmnsDTO.getProcessDate(),
						ndmnsDTO.getProcessTime(), ndmnsDTO.getOpId(),
						ndmnsDTO.getMethodId(), ndmnsDTO.getReturnMessage(),
						ndmnsDTO.getClientId(), ndmnsDTO.getEntireLine(),
						ndmnsDTO.getUpdateTime(), ndmnsDTO.getUpdateDate(),
						ndmnsDTO.getReturnCode() });
		if (id == 1) {
			return id;
		}
		return null;
	}
	//insert batch example
	public void insertSpotifyProcessRecord(final List<NdmnsDTO> ndmnsList){
		JdbcTemplate jdbcTemp = new JdbcTemplate(dataSource);
	   jdbcTemp.batchUpdate(WapConstants.INSERT_SPOTIFY_RECORD, new BatchPreparedStatementSetter() {

		   public void setValues(PreparedStatement ps, int i) throws SQLException {
			NdmnsDTO ndmns = ndmnsList.get(i);
			ps.setString(1, ndmns.getMsisdn());
			ps.setDate(2, new java.sql.Date(ndmns.getProcessDate().getTime()));
			ps.setTimestamp(3, new java.sql.Timestamp(ndmns.getProcessTime().getTime()));
			ps.setString(4, ndmns.getOpId());
			ps.setString(5, ndmns.getMethodId());
			ps.setString(6, ndmns.getReturnCode());
			ps.setString(7, ndmns.getClientId());
			ps.setString(8, ndmns.getEntireLine());
			ps.setString(9, ndmns.getReturnMessage());
		}
	 
		public int getBatchSize() {
			return ndmnsList.size();
		}
	  
	   });
	   
	}
	public static void main1(String[] args) {
		
		int array[]={10,20,30,10,40,20};
		int size=array.length;
		for(int i=0;i<size;i++){
			
			for(int j=i+1;j<size;j++){
			  if(array[i]==array[j]){
				  //System.out.println("Only Repeated"+array[i]);
			  }else{
				  System.out.println(array[i]);
			  }
			}
		}
	}
	
	public void insertSpotifyCodes(List<SpotifyCodesDTO> listSpotifyCodes) {
		for(SpotifyCodesDTO spotifyCodes:listSpotifyCodes){
			System.out.println(spotifyCodes.toString());
			JdbcTemplate jdbcTemp = new JdbcTemplate(dataSource);
				Integer id = jdbcTemp.update(INSERT_SPOTIFY_CODES,
				new Object[] {spotifyCodes.getSpotifyCodeID(),spotifyCodes.getSpotifyMessage(),spotifyCodes.getSpotifyIssueSystem(),spotifyCodes.getSpotifyReturnMessage() });
		}
	}
	public  static List<SpotifyCodesDTO> listOfSpotifyCodes(){
		List<SpotifyCodesDTO> spotifyCodesList=new ArrayList<SpotifyCodesDTO>();
	      try {
	    		File fileRead = new File(WapConstants.SOURCE_SPOTIFYCODES_FILE);
				BufferedReader br=new BufferedReader(new FileReader(fileRead));
				String line=null;
				while((line=br.readLine())!=null){
					SpotifyCodesDTO spotifyCodes=new SpotifyCodesDTO();
					String sp[]=line.split(";");
					spotifyCodes.setSpotifyCodeID(sp[0]);
					spotifyCodes.setSpotifyMessage(sp[1]);
					spotifyCodes.setSpotifyIssueSystem(sp[2]);
					spotifyCodes.setSpotifyReturnMessage(sp[3]);
					spotifyCodesList.add(spotifyCodes);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	      return spotifyCodesList;
	}

	
	
	public  void insertUUPRInformation(){
		try {
    		File fileRead = new File("D:\\abc\\db\\ABC_backup_20130509.csv");
    		BufferedReader br=new BufferedReader(new FileReader(fileRead));
			String line=null;
			while((line=br.readLine())!=null){
				final String sp[]=line.split(",");
		  JdbcTemplate jdbcTemp = new JdbcTemplate(dataSource);
		  	String msisdn=sp[0].replaceAll("^\"|\"$", "");
			String gendor=sp[1].replaceAll("^\"|\"$", "");
			String nation=sp[2].replaceAll("^\"|\"$", "");
			String lan=sp[5].replaceAll("^\"|\"$", "");
			String marCode=sp[7].replaceAll("^\"|\"$", "");
			String prepost=sp[9].replaceAll("^\"|\"$", "");
			Integer id = jdbcTemp.update(UUPR_INSERTION_QUERY,new Object[] {msisdn,gendor,nation,sp[3], sp[4],lan,sp[6],marCode,sp[8],prepost });
			}
			}catch(Exception e){
				
			}
	}
	
	public  void insertUUPR(){
		final List<String> uuprRecord=listOfUUPR();
		for(final String str:uuprRecord){
			
		JdbcTemplate jdbcTemp = new JdbcTemplate(dataSource);
		   jdbcTemp.batchUpdate(WapConstants.INSERT_SPOTIFY_RECORD, new BatchPreparedStatementSetter() {
			   public void setValues(PreparedStatement ps, int i) throws SQLException {
				   String sp[]=str.split(";");
				   String msisdn=sp[0].replaceAll("\"", sp[0]);
					ps.setString(1, msisdn);//msisdn
					String gendor=sp[1].replaceAll("\"", sp[1]);
					ps.setString(2, gendor);//gendor
					String nation=sp[2].replaceAll("\"", sp[2]);
					ps.setString(3,nation );//nation
					ps.setString(4,sp[3]);//Ethink
					ps.setString(5, sp[4]);//dob
					String lan=sp[5].replaceAll("\"", sp[5]);
					ps.setString(6,lan);//lan
					ps.setString(7, sp[6]);//marSeg
					String marCode=sp[7].replaceAll("\"", sp[7]);
					ps.setString(8,marCode);//marCode
					ps.setString(9, sp[8]);//timestamp
					String prepost=sp[9].replaceAll("\"", sp[9]);
					ps.setString(10,prepost);//preorpost
			  
			   }
			   public int getBatchSize() {
					return uuprRecord.size();
				}
			  
			   });
		}
	}
	
	
	
	public List<NdmnsDTO> spotifyDetails(String msisdn){
		String SELECT_QUERY="SELECT * FROM spotifyhits HT WHERE HT.MSISDN=?";
		List<NdmnsDTO> ndmnsDto=new ArrayList<NdmnsDTO>();
		try {
			JdbcTemplate jdbcTemp = new JdbcTemplate(dataSource);
			ndmnsDto=jdbcTemp.query(SELECT_QUERY, new Object[]{msisdn},new NdmnsSelectMapper());
		} catch (Exception e) {
		}
		return ndmnsDto;
	}
	
	public   List<String> listOfUUPR(){
		List<String> list=new ArrayList<String>();
		  
		try {
	    		File fileRead = new File("D:\\dB\\db\\BAC_backup_20130509.csv");
	    		BufferedReader br=new BufferedReader(new FileReader(fileRead));
				String line=null;
				while((line=br.readLine())!=null){
					final String sp[]=line.split(",");
//					System.out.println(line);
					JdbcTemplate jdbcTemp = new JdbcTemplate(dataSource);
					list.add(line);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return list;
	}
	public static void main(String[] args) {

		/*String str="\""+"60122512258"+"\"";
		
		//System.out.println(str.replaceAll("\"", str));
		String string = str.replaceAll("^\"|\"$", "");
		 System.out.println(string);*/
		SelectionDaoIF spotifyDaoIF=BeanFactory.getSpotifyDaoImpl();
		//spotifyDaoIF.insertUUPR();
		spotifyDaoIF.insertUUPRInformation();
		
	}
	public static void main2(String[] args) {
		SelectionDaoIF spotifyDaoIF=BeanFactory.getSpotifyDaoImpl();
		List<SpotifyCodesDTO> spotifyCodesList=listOfSpotifyCodes();
		System.out.println(spotifyCodesList.size());
		spotifyDaoIF.insertSpotifyCodes(spotifyCodesList);
		for(SpotifyCodesDTO spCodes:spotifyCodesList){
			System.out.println(spCodes.toString());
		}
	}
	public static void test(){
		

		int array[] = { 10, 20, 30, 20, 40, 40, 50, 60, 70, 80 };// array of ten
		// elements
		int size = array.length;
		System.out.println("Size before deletion: " + size);

		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if (array[i] == array[j]) // checking one element with all the element
				{
					while (j < (size) - 1) {
						array[j] = array[j + 1];// shifting the values
						j++;
					}
					size--;
				}
			}
		}
		System.out.println("Size After deletion: " + size);
		for (int k = 0; k < size; k++) {
			System.out.println(array[k]); // printing the values
		}

	}
}
