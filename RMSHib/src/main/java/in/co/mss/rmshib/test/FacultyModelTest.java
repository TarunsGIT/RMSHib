package in.co.mss.rmshib.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.mss.rmshib.dto.FacultyDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DatabaseException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.FacultyModelHibImpl;

public class FacultyModelTest {

	public static FacultyModelHibImpl model = new FacultyModelHibImpl();
	public static FacultyDTO dto = new FacultyDTO();
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) throws ParseException {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByEmail();
		// testFindByPk();
		testList();
	}

	private static void testList() {
		List list = new ArrayList();
		try {
			list = model.list(0, 10);
			if (list.size() <= 0) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tCOLLEGE_ID \tCOLLEGE_NAME \tCOURSE_ID \tCOURSE_NAME \tFIRST_NAME \tLAST_NAME \tDOB \t\tQUALIFICATION \t\tEMAIL \t\t\tMOBILE_NO \tADDRESS");

				Iterator it = list.iterator();
				while (it.hasNext()) {
					dto = (FacultyDTO) it.next();
					System.out.print("\n" + dto.getId());
					System.out.print("\t" + dto.getCollegeId());
					System.out.print("\t\t" + dto.getCollegeName());
					System.out.print("\t\t" + dto.getCourseId());
					System.out.print("\t\t" + dto.getCourseName());
					System.out.print("\t\t" + dto.getFirstName());
					System.out.print("\t\t" + dto.getLastName());
					System.out.print("\t\t" + sdf.format((dto.getDob())));
					System.out.print("\t\t" + dto.getQualification());
					System.out.print("\t\t" + dto.getEmail());
					System.out.print("\t\t" + dto.getMobileNo());
					System.out.print("\t" + dto.getAddress());
				}
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testFindByPk() {
		try {
			dto = model.findByPK(2L);
			if (dto == null) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tCOLLEGE_ID \tCOLLEGE_NAME \tCOURSE_ID \tCOURSE_NAME \tFIRST_NAME \tLAST_NAME \tDOB \t\tQUALIFICATION \t\tEMAIL \t\t\tMOBILE_NO \tADDRESS");
				System.out.print("\n" + dto.getId());
				System.out.print("\t" + dto.getCollegeId());
				System.out.print("\t\t" + dto.getCollegeName());
				System.out.print("\t\t" + dto.getCourseId());
				System.out.print("\t\t" + dto.getCourseName());
				System.out.print("\t\t" + dto.getFirstName());
				System.out.print("\t\t" + dto.getLastName());
				System.out.print("\t\t" + sdf.format((dto.getDob())));
				System.out.print("\t\t" + dto.getQualification());
				System.out.print("\t\t" + dto.getEmail());
				System.out.print("\t\t" + dto.getMobileNo());
				System.out.print("\t" + dto.getAddress());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testFindByEmail() {
		try {
			dto = model.findByEmailId("sagrika@gmail.com");
			if (dto == null) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tCOLLEGE_ID \tCOLLEGE_NAME \tCOURSE_ID \tCOURSE_NAME \tFIRST_NAME \tLAST_NAME \tDOB \t\tQUALIFICATION \t\tEMAIL \t\t\tMOBILE_NO \tADDRESS");
				System.out.print("\n" + dto.getId());
				System.out.print("\t" + dto.getCollegeId());
				System.out.print("\t\t" + dto.getCollegeName());
				System.out.print("\t\t" + dto.getCourseId());
				System.out.print("\t\t" + dto.getCourseName());
				System.out.print("\t\t" + dto.getFirstName());
				System.out.print("\t\t" + dto.getLastName());
				System.out.print("\t\t" + sdf.format((dto.getDob())));
				System.out.print("\t\t" + dto.getQualification());
				System.out.print("\t\t" + dto.getEmail());
				System.out.print("\t\t" + dto.getMobileNo());
				System.out.print("\t" + dto.getAddress());
			}

		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void testDelete() {
		long pk = 6L;
		dto.setId(pk);
		try {
			model.delete(dto);
			FacultyDTO deleted_DTO = model.findByPK(pk);
			if (deleted_DTO != null) {
				System.out.println("DELETE METHOD FAILS");
			} else {
				System.out.println("RECORD DELETED AT " + dto.getId());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testUpdate() throws ParseException {
		long pk = 6L;
		try {
			dto = model.findByPK(pk);
			dto.setCollegeId(6L);
			dto.setCourseId(3L);
			dto.setFirstName("TANMAY");
			dto.setLastName("GANDHE");
			dto.setDob(sdf.parse("14/02/1993"));
			dto.setQualification("MS");
			dto.setEmail("gandhe@gmail.com");
			dto.setMobileNo("9826645698");
			dto.setAddress("INDORE");
			dto.setPrimarySubject("CHEMISTRY");
			dto.setSecondarySubject("MATHS");
			dto.setCreatedBy("super_admin@gmail.com");
			dto.setModifiedBy("super_admin@gmail.com");
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
			dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
			try {
				model.update(dto);
				FacultyDTO updated_DTO = model.findByPK(pk);
				if (!"ROBIN".equals(updated_DTO.getFirstName())) {
					System.out.println("RECORD UPDATED AT " + dto.getId());

				} else {
					System.out.println("UPDATE METHOD FAILS");
				}
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void testAdd() throws ParseException {

		dto.setCollegeId(6L);
		dto.setCourseId(3L);
		dto.setFirstName("TANMAY");
		dto.setLastName("GADKARI");
		dto.setDob(sdf.parse("14/02/1993"));
		dto.setQualification("MS");
		dto.setEmail("tanmay@gmail.com");
		dto.setMobileNo("9826645698");
		dto.setAddress("INDORE");
		dto.setPrimarySubject("CHEMISTRY");
		dto.setSecondarySubject("MATHS");
		dto.setCreatedBy("super_admin@gmail.com");
		dto.setModifiedBy("super_admin@gmail.com");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		try {
			long pk = model.add(dto);
			FacultyDTO added_DTO = model.findByPK(pk);
			if (added_DTO == null) {
				System.out.println("ADD METHOD FAILS");
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
