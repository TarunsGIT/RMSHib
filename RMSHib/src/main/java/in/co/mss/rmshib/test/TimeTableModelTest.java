package in.co.mss.rmshib.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.mss.rmshib.dto.TimeTableDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DatabaseException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.TimeTableModelHibImpl;

public class TimeTableModelTest {

	public static TimeTableModelHibImpl model = new TimeTableModelHibImpl();
	public static TimeTableDTO dto = new TimeTableDTO();
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) throws ParseException {
		testAdd();
		
	}

	private static void testAdd() throws ParseException {
		dto.setCourseId(1L);
		dto.setSubject("CHEMISTRY");
		dto.setExaminationDate(sdf.parse("15/5/2016"));
		dto.setTime("10:00 AM-1:00 AM");
		dto.setDay("WENSDAY");
		dto.setCreatedBy("super_admin@gmail.com");
		dto.setModifiedBy("super_admin@gmail.com");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		try {
			long pk = model.add(dto);
			TimeTableDTO added_DTO = model.findByPK(pk);
			if (added_DTO == null) {
				System.out.println("METHOD ADD FAILS");
			} else {
				System.out.println("RECORD ADDED AT " + dto.getId());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
