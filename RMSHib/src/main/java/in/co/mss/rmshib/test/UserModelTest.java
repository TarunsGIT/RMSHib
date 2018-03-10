package in.co.mss.rmshib.test;

import in.co.mss.rmshib.dto.UserDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.UserModelHibImpl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserModelTest {
	public static UserModelHibImpl model = new UserModelHibImpl();
	public static UserDTO dto = new UserDTO();

	public static void main(String[] args) throws ParseException {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testList();
		// testSearch();
		testFindByLogin();

	}

	private static void testFindByLogin() {
		try {
			dto = model.findByLogin("ronny@gmail.com");
			if (dto == null) {
				System.out.println("NO RECORD FOUND");
			} else {
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
				System.out
						.println("ID \tFIRST_NAME \tLAST_NAME \tLOGIN \tPASSWORD \tDOB \tMOBILE_NO \tROLE_ID \tROLE_NAME");

				System.out.print("\n" + dto.getId());
				System.out.print("\t" + dto.getFirstName());
				System.out.print("\t" + dto.getLastName());
				System.out.print("\t" + dto.getLogin());
				System.out.print("\t" + dto.getPassword());
				System.out.print("\t" + sdf1.format(dto.getDob()));
				System.out.print("\t" + dto.getMobileNo());
				System.out.print("\t" + dto.getRoleId());
				System.out.print("\t" + dto.getRoleName());

			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testSearch() {
		List list = new ArrayList();
		dto.setId(4L);
		/* dto.setLastName("SINGH"); */
		try {
			list = model.search(dto, 0, 0);
			if (list.size() < 0) {
				System.out.println("NO RECORD FOUND");

			} else {
				System.out
						.println("ID \tFIRST_NAME \tLAST_NAME \tLOGIN \tPASSWORD \tDOB \tMOBILE_NO \tROLE_ID \tROLE_NAME");
				Iterator it = list.iterator();
				while (it.hasNext()) {
					dto = (UserDTO) it.next();
					System.out.print("\n" + dto.getId());
					System.out.print("\t" + dto.getFirstName());
					System.out.print("\t" + dto.getLastName());
					System.out.print("\t" + dto.getLogin());
					System.out.print("\t" + dto.getPassword());
					System.out.print("\t" + dto.getDob());
					System.out.print("\t" + dto.getMobileNo());
					System.out.print("\t" + dto.getRoleId());
					System.out.print("\t" + dto.getRoleName());

				}
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testList() {
		List list = new ArrayList();
		try {
			list = model.list();
			if (list.size() < 0) {
				System.out.println("TEST LIST FAILS");
			} else {
				System.out
						.println("ID \tFIRST_NAME \tLAST_NAME \tLOGIN \tPASSWORD \tDOB \tMOBILE_NO \tROLE_ID \tROLE_NAME");
				Iterator it = list.iterator();

				while (it.hasNext()) {
					dto = (UserDTO) it.next();
					System.out.print("\n" + dto.getId());
					System.out.print("\t" + dto.getFirstName());
					System.out.print("\t" + dto.getLastName());
					System.out.print("\t" + dto.getLogin());
					System.out.print("\t" + dto.getPassword());
					System.out.print("\t" + dto.getDob());
					System.out.print("\t" + dto.getMobileNo());
					System.out.print("\t" + dto.getRoleId());
					System.out.print("\t" + dto.getRoleName());

				}
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testDelete() {
		long pk = 7L;
		dto.setId(pk);
		try {
			model.delete(dto);
			UserDTO deleted_DTO = model.findByPK(pk);
			if (deleted_DTO != null) {
				System.out.println("DELETE RECORD FAILED");
			} else {
				System.out.println("RECORD SUCESSFULLY DELETED AT "
						+ dto.getId());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private static void testUpdate() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		long pk = 6L;
		try {
			dto = model.findByPK(pk);
			dto.setFirstName("Namratha");
			dto.setLastName("Sangram");
			dto.setLogin("namratha@gmail.com");
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
				model.update(dto);
				UserDTO updated_DTO = model.findByPK(pk);
				if (!" Tesla ".equals(updated_DTO)) {
					System.out
							.println("RECORD UPDATED AT INDEX " + dto.getId());
				} else {
					System.out.println("RECORD UPDATE FAILED");
				}

			} catch (DuplicateRecordException e) {
				e.printStackTrace();
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testAdd() throws ParseException {
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
				System.out.println("ADD METHOD FAILED");
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
