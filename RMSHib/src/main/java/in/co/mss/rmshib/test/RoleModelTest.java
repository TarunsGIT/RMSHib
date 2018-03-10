package in.co.mss.rmshib.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.mss.rmshib.dto.RoleDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.RoleModelHibImpl;
import in.co.mss.rmshib.model.RoleModelInt;

public class RoleModelTest {

	public static RoleModelHibImpl model = new RoleModelHibImpl();
	public static RoleDTO dto = new RoleDTO();

	public static void main(String[] args) {
	      //testAdd();
		// testDelete();
		// testUpdate();
		// testFindByPk();
		// testFindByName();
		 testList();
		//testSearch();
	}

	private static void testSearch() {
		List list = new ArrayList();
		/* dto.setName("IMS"); */
		/*dto.setId(5L);*/
		dto.setDescription("Adminstrator");
		try {
			list = model.search(dto, 0, 0);
			if (list.size() < 0) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out.println("ID \tNAME \tDESCRIPTION");
				Iterator it = list.iterator();
				while (it.hasNext()) {
					dto = (RoleDTO) it.next();
					System.out.print("\n" + dto.getId());
					System.out.print("\t" + dto.getName());
					System.out.print("\t" + dto.getDescription());
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
				System.out.println("ID \tNAME \tDESCRIPTION");
				Iterator it = list.iterator();

				while (it.hasNext()) {
					dto = (RoleDTO) it.next();
					System.out.print("\n" + dto.getId());
					System.out.print("\t" + dto.getName());
					System.out.print("\t" + dto.getDescription());

				}
			}

		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void testFindByName() {

		String role_name = "finance";
		try {
			dto = model.findByName(role_name);
			if (dto == null) {
				System.out.println("FIND BY NAME FAILS");
			} else {
				System.out.println("RECORD FOUND BY NAME BY " + dto.getName());
			}
			System.out.println("ID \tNAME \tDESCRIPTION");
			System.out.print("\n" + dto.getId());
			System.out.print("\t" + dto.getName());
			System.out.print("\t" + dto.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testFindByPk() {
		long pk = 8L;
		try {
			dto = model.findByPK(pk);
			if (dto == null) {
				System.out.println("FIND BY PK FAILS");
			} else {
				System.out.println("RECORD FOUND AT " + dto.getId());
			}
			System.out.println("ID \tNAME \tDESCRIPTION");
			System.out.print("\n" + dto.getId());
			System.out.print("\t" + dto.getName());
			System.out.print("\t" + dto.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testUpdate() {
		long pk = 8L;
		try {
			dto = model.findByPK(pk);
			dto.setName("IMS");
			dto.setDescription("Information Managment System");
			dto.setCreatedBy("Super_admin@gmail.com");
			dto.setModifiedBy("Super_admin@gmail.com");
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
			dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
			try {
				model.update(dto);
				RoleDTO updated_record = model.findByPK(1L);
				if (!" Tesla ".equals(updated_record)) {
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

	private static void testDelete() {
		long pk = 7L;
		dto.setId(pk);
		try {
			model.delete(dto);
			RoleDTO deleted_DTO = model.findByPK(pk);
			if (deleted_DTO != null) {
				System.out.println("ROLE DELETE FAILED");

			} else {
				System.out
						.println("ROLE SUCESSFULLY DELETED AT " + dto.getId());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testAdd() {

		dto.setName("ETA");
		dto.setDescription("Education Training & Assesment");
		dto.setCreatedBy("Super_admin@gmail.com");
		dto.setModifiedBy("Super_admin@gmail.com");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		try {
			long pk = model.add(dto);
			RoleDTO addedBean = model.findByPK(pk);
			if (addedBean == null) {
				System.out.println("ADD METHOD FAILED");
			} else {
				System.out.println(dto.getId() + " th" + " RECORD ADDED");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

}
