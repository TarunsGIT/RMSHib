package in.co.mss.rmshib.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.mss.rmshib.dto.CourseDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.CourseModelHibImpl;

public class CourseModelTest {

	public static CourseModelHibImpl model = new CourseModelHibImpl();
	public static CourseDTO dto = new CourseDTO();

	public static void main(String[] args) {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testList();
		// testFindByName();
		// testFindByPk();
		testSearch();
	}

	private static void testSearch() {
		List list = new ArrayList();
		dto.setId(1L);
		dto.setName("BE");
		try {
			list = model.search(dto, 0, 10);
			if (list.size() <= 0) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tCOURSE_NAME \tDESCRIPTION \t\t\tDURATION");
				Iterator it = list.iterator();
				while (it.hasNext()) {
					dto = (CourseDTO) it.next();
					System.out.print("\n" + dto.getId());
					System.out.print("\t" + dto.getName());
					System.out.print("\t\t" + dto.getDescription());
					System.out.print("\t\t" + dto.getDuration());
				}
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testFindByPk() {
		long pk = 5L;
		try {
			dto = model.findByPK(pk);
			if (dto == null) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tCOURSE_NAME \tDESCRIPTION \t\t\tDURATION");
				System.out.print("\n" + dto.getId());
				System.out.print("\t" + dto.getName());
				System.out.print("\t\t" + dto.getDescription());
				System.out.print("\t\t" + dto.getDuration());

			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void testFindByName() {
		try {
			dto = model.findByName("BE");
			if (dto == null) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tCOURSE_NAME \tDESCRIPTION \t\t\tDURATION");
				System.out.print("\n" + dto.getId());
				System.out.print("\t" + dto.getName());
				System.out.print("\t\t" + dto.getDescription());
				System.out.print("\t\t" + dto.getDuration());

			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testList() {
		List list = new ArrayList();
		try {
			list = model.list(0, 10);
			if (list.size() <= 0) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tCOURSE_NAME \tDESCRIPTION \t\t\tDURATION");
				Iterator it = list.iterator();
				while (it.hasNext()) {
					dto = (CourseDTO) it.next();
					System.out.print("\n" + dto.getId());
					System.out.print("\t" + dto.getName());
					System.out.print("\t\t" + dto.getDescription());
					System.out.print("\t\t" + dto.getDuration());
				}
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
			CourseDTO Deleted_Course = model.findByPK(pk);
			if (Deleted_Course != null) {
				System.out.println("DELETE METHOD FAILS");

			} else {
				System.out.println("RECORD DELETED AT " + dto.getId());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void testUpdate() {
		long pk = 6L;
		try {
			dto = model.findByPK(pk);
			dto.setName("MCA");
			dto.setDescription("Master Of Computer Application");
			dto.setDuration(4);
			dto.setCreatedBy("super_admin@gmail.com");
			dto.setModifiedBy("super_admin@gmail.com");
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
			dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
			try {
				model.update(dto);
				CourseDTO updated_Record = model.findByPK(pk);
				if (!" Tesla ".equals(updated_Record)) {
					System.out
							.println("RECORD UPDATED AT INDEX " + dto.getId());
				} else {
					System.out.println("RECORD UPDATE FAILED");
				}
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void testAdd() {
		dto.setName("MBA");
		dto.setDescription("Master Of Bussiness Adminstration");
		dto.setDuration(5);
		dto.setCreatedBy("super_admin@gmail.com");
		dto.setModifiedBy("super_admin@gmail.com");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		try {
			long pk = model.add(dto);
			CourseDTO added_DTO = model.findByPK(pk);
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
