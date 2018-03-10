package in.co.mss.rmshib.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.mss.rmshib.dto.MarksheetDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.MarksheetModelHibImpl;

public class MarksheetModelTest {

	public static MarksheetModelHibImpl model = new MarksheetModelHibImpl();
	public static MarksheetDTO dto = new MarksheetDTO();
	public static SimpleDateFormat sdf = new SimpleDateFormat();

	public static void main(String[] args) {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByRollNo();
		// testFindByPK();
		// testList();
		// testSearch();
		testMeritList();
	}

	private static void testMeritList() {
		List list = new ArrayList();
		try {
			list = model.getMeritList(0, 10);
			if (list.size() <= 0) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tSTUDENT_ID \tROLL_NO  \tNAME \t\tPHYSICS  CHEMISTRY \tMATHS \tTOTAL");

				Iterator it = list.iterator();
				while (it.hasNext()) {

					dto = (MarksheetDTO) it.next();
					int total = dto.getPhysics() + dto.getMaths()
							+ dto.getChemistry();
					System.out.print("\n" + dto.getId());
					System.out.print("\t" + dto.getStudentId());
					System.out.print("\t\t" + dto.getRollNo());
					System.out.print("\t" + dto.getName());
					System.out.print("\t\t" + dto.getPhysics());
					System.out.print("\t" + dto.getChemistry());
					System.out.print("\t" + dto.getMaths());
					System.out.print("\t" + total);
				}
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void testSearch() {
		List list = new ArrayList();
		dto.setRollNo("0818cs10");
		try {
			list = model.search(dto, 0, 10);
			if (list.size() <= 0) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tSTUDENT_ID \tROLL_NO  \tNAME \t\tPHYSICS  CHEMISTRY \tMATHS");

				Iterator it = list.iterator();
				while (it.hasNext()) {
					dto = (MarksheetDTO) it.next();
					System.out.print("\n" + dto.getId());
					System.out.print("\t" + dto.getStudentId());
					System.out.print("\t\t" + dto.getRollNo());
					System.out.print("\t" + dto.getName());
					System.out.print("\t\t" + dto.getPhysics());
					System.out.print("\t" + dto.getChemistry());
					System.out.print("\t" + dto.getMaths());
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
			list = model.list(0, 10);
			if (list.size() <= 0) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tSTUDENT_ID \tROLL_NO  \tNAME \t\tPHYSICS  CHEMISTRY \tMATHS");

				Iterator it = list.iterator();
				while (it.hasNext()) {
					dto = (MarksheetDTO) it.next();
					System.out.print("\n" + dto.getId());
					System.out.print("\t" + dto.getStudentId());
					System.out.print("\t\t" + dto.getRollNo());
					System.out.print("\t" + dto.getName());
					System.out.print("\t\t" + dto.getPhysics());
					System.out.print("\t" + dto.getChemistry());
					System.out.print("\t" + dto.getMaths());
				}
			}

		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void testFindByPK() {
		long pk = 4L;
		try {
			dto = model.findByPK(pk);
			if (dto == null) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tSTUDENT_ID \tROLL_NO  \tNAME \t\tPHYSICS  CHEMISTRY \tMATHS");
				System.out.print("\n" + dto.getId());
				System.out.print("\t" + dto.getStudentId());
				System.out.print("\t\t" + dto.getRollNo());
				System.out.print("\t" + dto.getName());
				System.out.print("\t\t" + dto.getPhysics());
				System.out.print("\t" + dto.getChemistry());
				System.out.print("\t" + dto.getMaths());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void testFindByRollNo() {
		try {
			dto = model.findByRollNo("0818cs10");
			if (dto == null) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tSTUDENT_ID \tROLL_NO  \tNAME \t\tPHYSICS  CHEMISTRY \tMATHS");
				System.out.print("\n" + dto.getId());
				System.out.print("\t" + dto.getStudentId());
				System.out.print("\t\t" + dto.getRollNo());
				System.out.print("\t" + dto.getName());
				System.out.print("\t\t" + dto.getPhysics());
				System.out.print("\t" + dto.getChemistry());
				System.out.print("\t" + dto.getMaths());
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
			MarksheetDTO deleted_DTO = model.findByPK(pk);
			if (deleted_DTO != null) {
				System.out.println("DELETE METHOD FAILS");
			} else {
				System.out.println("RECORD DELETED AT " + dto.getId());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private static void testUpdate() {

		try {
			dto = model.findByPK(1L);
			dto.setName("Robin Shergill");

			dto.setChemistry(88);
			dto.setMaths(88);
			dto.setPhysics(88);
			model.update(dto);

			MarksheetDTO updated_DTO = model.findByPK(1L);
			if (!"rk choudhary".equals(updated_DTO.getName())) {
				System.out.println("Test Update fail");
			} else {
				System.out.println("RECORD UPDATED AT " + dto.getId());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

		/*
		 * try { dto = model.findByPK(1L);
		 * 
		 * dto.setName("Robin Shergill"); dto.setMaths(55); dto.setPhysics(55);
		 * dto.setChemistry(55); dto.setCreatedBy("super_admin@gmail.com");
		 * dto.setModifiedBy("super_admin@gmail.com");
		 * dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		 * dto.setModifiedDatetime(new Timestamp(new Date().getTime())); try {
		 * model.update(dto); } catch (DuplicateRecordException e) {
		 * e.printStackTrace(); } MarksheetDTO updated_DTO = model.findByPK(1L);
		 * if (!"Tesla".equals(updated_DTO.getName())) {
		 * System.out.println("UPDATE METHOD FAILS"); } else {
		 * System.out.println("RECORD UPDATED AT " + dto.getId()); } } catch
		 * (ApplicationException e) { e.printStackTrace(); }
		 */

	}

	private static void testAdd() {

		dto.setRollNo("0818cs15");
		dto.setStudentId(6L);
		dto.setPhysics(80);
		dto.setMaths(90);
		dto.setChemistry(95);
		dto.setCreatedBy("super_admin@gmail.com");
		dto.setModifiedBy("super_admin@gmail.com");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		try {
			long pk = model.add(dto);
			MarksheetDTO added_DTO = model.findByPK(pk);
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
