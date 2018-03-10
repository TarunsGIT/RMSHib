package in.co.mss.rmshib.test;

import in.co.mss.rmshib.dto.CollegeDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.model.CollegeModelHibImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CollegeModelTest {

	public static CollegeModelHibImpl model = new CollegeModelHibImpl();
	public static CollegeDTO dto = new CollegeDTO();

	public static void main(String[] args) {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByName();
		// testList();
		// testSearch();
		testFindByPk();
	}

	private static void testFindByPk() {
		long pk = 8L;
		try {
			dto = model.findByPK(pk);
			if (dto == null) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tNAME \tADDRESS \tSTATE \tCITY \tCONTACT");

				System.out.print("\n" + dto.getId());
				System.out.print("\t" + dto.getName());
				System.out.print("\t" + dto.getAddress());
				System.out.print("\t" + dto.getState());
				System.out.print("\t" + dto.getCity());
				System.out.print("\t" + dto.getPhoneNo());

			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testSearch() {
		List list = new ArrayList();
		dto.setId(1L);
		try {
			list = model.search(dto, 0, 0);
			if (list.size() < 0) {
				System.out.println("NO RECORD FOUND");

			} else {
				System.out
						.println("ID \tNAME \tADDRESS \tSTATE \tCITY \tCONTACT");
				Iterator it = list.iterator();
				while (it.hasNext()) {
					dto = (CollegeDTO) it.next();
					System.out.print("\n" + dto.getId());
					System.out.print("\t" + dto.getName());
					System.out.print("\t" + dto.getAddress());
					System.out.print("\t" + dto.getState());
					System.out.print("\t" + dto.getCity());
					System.out.print("\t" + dto.getPhoneNo());

				}
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testList() {
		try {
			List list = model.list();
			if (list.size() < 0) {
				System.out.println("NO RECORD FOUND");

			} else {
				System.out
						.println("ID \tNAME \tADDRESS \tSTATE \tCITY \tCONTACT");
				Iterator it = list.iterator();
				while (it.hasNext()) {
					dto = (CollegeDTO) it.next();
					System.out.print("\n" + dto.getId());
					System.out.print("\t" + dto.getName());
					System.out.print("\t" + dto.getAddress());
					System.out.print("\t" + dto.getState());
					System.out.print("\t" + dto.getCity());
					System.out.print("\t" + dto.getPhoneNo());
				}
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testFindByName() {
		try {
			dto = model.findByName("IIST");
			if (dto == null) {
				System.out.println("NO RECORD FOUND");
			} else {
				System.out
						.println("ID \tNAME \tADDRESS \tSTATE \tCITY \tCONTACT");
				System.out.print("\n" + dto.getId());
				System.out.print("\t" + dto.getName());
				System.out.print("\t" + dto.getAddress());
				System.out.print("\t" + dto.getState());
				System.out.print("\t" + dto.getCity());
				System.out.print("\t" + dto.getPhoneNo());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private static void testDelete() {
		long pk = 9L;
		try {
			dto = model.findByPK(pk);
			if (dto == null) {
				System.out.println("NO RECORD FOUND AT " + pk);
			} else {
				dto.setId(pk);
				model.delete(dto);
				CollegeDTO deleted_DTO = model.findByPK(pk);
				if (deleted_DTO != null) {
					System.out.println("METHOD DELETE FAILED");

				} else {
					System.out.println("RECORD DELETED AT " + dto.getId());
				}
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testUpdate() {
		long pk = 10L;
		try {
			dto = model.findByPK(pk);
			if (dto == null) {
				System.out.println("NO RECORD FOUND AT " + pk);
			} else {
				dto.setName("IIMR");
				dto.setAddress("Opposite IIM Indore");
				dto.setState("Madhya Pradesh");
				dto.setCity("Indore");
				dto.setPhoneNo("0731484248");
				dto.setCreatedBy("super_admin@gmail.com");
				dto.setModifiedBy("super_admin@gmail.com");
				dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
				dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
				try {
					model.update(dto);
					CollegeDTO updated_DTO = model.findByPK(pk);
					if (!" Tesla ".equals(updated_DTO)) {
						System.out.println("RECORD UPDATED AT INDEX "
								+ dto.getId());
					} else {
						System.out.println("RECORD UPDATE FAILED");
					}
				} catch (DuplicateRecordException e) {
					e.printStackTrace();
				}
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testAdd() {
		dto.setName("IICA");
		dto.setAddress("Opposite IIM Indore");
		dto.setState("Madhya Pradesh");
		dto.setCity("Indore");
		dto.setPhoneNo("0731484248");
		dto.setCreatedBy("super_admin@gmail.com");
		dto.setModifiedBy("super_admin@gmail.com");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		try {
			long pk = model.add(dto);
			CollegeDTO added_record = model.findByPK(pk);
			if (added_record == null) {
				System.out.println("ADD METHOD FAILS");

			} else {
				System.out
						.println("RECORD SUCESSFULLY ADDED AT " + dto.getId());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}
}
