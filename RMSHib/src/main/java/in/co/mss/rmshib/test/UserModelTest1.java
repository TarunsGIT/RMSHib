package in.co.mss.rmshib.test;

import static org.junit.Assert.fail;
import in.co.mss.rmshib.dto.UserDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.UserModelHibImpl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class UserModelTest1 {

	public static UserDTO dto=new UserDTO();
	public static UserModelHibImpl model=new UserModelHibImpl();
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Test
	public void testAdd() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dto.setFirstName("Namratha");
		dto.setLastName("Samrath");
		dto.setLogin("Namratha@gmail.com");
		dto.setPassword("789456");
		dto.setDob(sdf.parse("08/12/1993"));
		dto.setMobileNo("7860808080");
		dto.setRoleId(6L);
		dto.setUnSuccessfulLogin(2);
		dto.setGender("Male");
		dto.setLastLogin(new Timestamp(new Date().getTime()));
		dto.setLock("EXCLUSIVE");
		dto.setRegisteredIP("192.168.121.11");
		dto.setLastLoginIP("192.168.121.11");
		dto.setCreatedBy("super_admin@gmail.com");
		dto.setModifiedBy("super_admin@gmail.com");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		try {
			long pk = model.add(dto);
			UserDTO added_record = model.findByPK(pk);
			if (added_record == null) {
				fail("ADD METHOD FAILED");
				/*System.out.println("ADD METHOD FAILED");*/
			} else {
				System.out.println("RECORD ADDED AT " + dto.getId());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}
	
public void testUpdate(){
	
}



}	




