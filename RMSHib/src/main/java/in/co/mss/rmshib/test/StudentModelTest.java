package in.co.mss.rmshib.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.mss.rmshib.dto.StudentDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.StudentModelHibImpl;

public class StudentModelTest {

	public static StudentModelHibImpl model = new StudentModelHibImpl();
	public static StudentDTO dto = new StudentDTO();
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) throws ParseException {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		// testFindByEmail();
		// testList();
		testSearch();
	}

	private static void testSearch() {
		List list = new ArrayList();
		dto.setId(1L);
		dto.setFirstName("ANKIT");
		try {
			list = model.search(dto,0,0);
			if (list.size() <= 0) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tCOLLEGE_ID \tCOLLEGE_NAME \tFIRST_NAME \tLAST_NAME \tDOB \t\tMOBILE_NO \t EMAIL");

				Iterator it = list.iterator();
				while (it.hasNext()) {
					dto = (StudentDTO) it.next();
					System.out.print("\n" + dto.getId());
					System.out.print("\t" + dto.getCollegeId());
					System.out.print("\t\t" + dto.getCollegeName());
					System.out.print("\t\t" + dto.getFirstName());
					System.out.print("\t\t" + dto.getLastName());
					System.out.print("\t\t" + sdf.format(dto.getDob()));
					System.out.print("\t" + dto.getMobileNo());
					System.out.print("\t" + dto.getEmail());

				}
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testList() {
		List list = new ArrayList();
		try {
			list = model.list(1, 10);
			if (list.size() <= 0) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tCOLLEGE_ID \tCOLLEGE_NAME \tFIRST_NAME \tLAST_NAME \tDOB \t\tMOBILE_NO \t EMAIL");

				Iterator it = list.iterator();
				while (it.hasNext()) {
					dto = (StudentDTO) it.next();
					System.out.print("\n" + dto.getId());
					System.out.print("\t" + dto.getCollegeId());
					System.out.print("\t\t" + dto.getCollegeName());
					System.out.print("\t\t" + dto.getFirstName());
					System.out.print("\t\t" + dto.getLastName());
					System.out.print("\t\t" + sdf.format(dto.getDob()));
					System.out.print("\t" + dto.getMobileNo());
					System.out.print("\t" + dto.getEmail());
					System.out
							.print("\n"
									+ "--------------------------------------------------------------------------------------------------------------------------------");

				}
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testFindByEmail() {
		try {
			dto = model.findByEmailId("Kanungo@gmail.com");
			if (dto == null) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tCOLLEGE_ID \tCOLLEGE_NAME \tFIRST_NAME \tLAST_NAME \tDOB \t\tMOBILE_NO \t EMAIL");

				System.out.print("\n" + dto.getId());
				System.out.print("\t" + dto.getCollegeId());
				System.out.print("\t\t" + dto.getCollegeName());
				System.out.print("\t\t" + dto.getFirstName());
				System.out.print("\t\t" + dto.getLastName());
				System.out.print("\t\t" + sdf.format(dto.getDob()));
				System.out.print("\t" + dto.getMobileNo());
				System.out.print("\t" + dto.getEmail());
			}

		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testFindByPk() {
		long pk = 6L;
		try {
			dto = model.findByPK(pk);
			if (dto == null) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tCOLLEGE_ID \tCOLLEGE_NAME \tFIRST_NAME \tLAST_NAME \tDOB \t\tMOBILE_NO \t EMAIL");

				System.out.print("\n" + dto.getId());
				System.out.print("\t" + dto.getCollegeId());
				System.out.print("\t\t" + dto.getCollegeName());
				System.out.print("\t\t" + dto.getFirstName());
				System.out.print("\t\t" + dto.getLastName());
				System.out.print("\t\t" + sdf.format(dto.getDob()));
				System.out.print("\t" + dto.getMobileNo());
				System.out.print("\t" + dto.getEmail());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void testDelete() {

		long pk = 7L;
		dto.setId(pk);
		try {
			model.delete(dto);
			StudentDTO deleted_DTO = model.findByPK(pk);
			if (deleted_DTO != null) {
				System.out.println("METHOD DELETE FAILS");
			} else {
				System.out.println("RECORD DELETED AT " + dto.getId());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testUpdate() throws ParseException {
		try {
			dto = model.findByPK(1L);
			dto.setCollegeId(1L);
			dto.setFirstName("ROBIN");
			dto.setLastName("SHERGILL");
			dto.setDob(sdf.parse("14/09/1993"));
			dto.setMobileNo("9826784565");
			dto.setEmail("Gill@gmail.com");
			dto.setCreatedBy("super_admin@gmail.com");
			dto.setModifiedBy("super_admin@gmail.com");
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
			dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
			try {
				model.update(dto);
				StudentDTO updated_DTO = model.findByPK(1L);
				if (!"ROBIN".equals(updated_DTO.getFirstName())) {
					System.out.println("UPDATE METHOD FAILS");

				} else {
					System.out.println("RECORD UPDATED AT " + dto.getId());
				}
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testAdd() throws ParseException {

		dto.setCollegeId(8L);
		dto.setFirstName("GAURAV");
		dto.setLastName("KELKAR");
		dto.setDob(sdf.parse("14/09/1993"));
		dto.setMobileNo("9826784565");
		dto.setEmail("Kelkar@gmail.com");
		dto.setCreatedBy("super_admin@gmail.com");
		dto.setModifiedBy("super_admin@gmail.com");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		try {
			long pk = model.add(dto);
			StudentDTO added_DTO = model.findByPK(pk);
			if (added_DTO == null) {
				System.out.println("ADD METHOD FAILS");
			} else {
				System.out.println("RECORD ADDED AT " + dto.getId());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

}
