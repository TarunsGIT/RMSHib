package in.co.mss.rmshib.model;

import in.co.mss.rmshib.dto.CollegeDTO;
import in.co.mss.rmshib.dto.CourseDTO;
import in.co.mss.rmshib.dto.FacultyDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DatabaseException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.util.HibDataSource;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Hibernate Implementation of FacultyModel
 *
 * @author Session Facade
 * @version 1.0
 *
 */

public class FacultyModelHibImpl implements FacultyModelInt {

	private static Logger log = Logger.getLogger(FacultyModelHibImpl.class);

	/**
	 * Add a Faculty
	 * 
	 * @param dto
	 * @throws DatabaseException
	 * 
	 */
	public long add(FacultyDTO dto) throws ApplicationException,
			DuplicateRecordException, DatabaseException {

		log.debug("Model add Started");
		long pk = 0;
		System.out
				.println("**********IN MODEL ADD METHOD FACULTY MODEL********");
		CollegeModelInt college = ModelFactory.getInstance().getCollegeModel();
		CollegeDTO collegeDTO = college.findByPK(dto.getCollegeId());
		dto.setCollegeName(collegeDTO.getName());

		CourseModelInt course = ModelFactory.getInstance().getCourseModel();
		CourseDTO courseDTO = course.findByPK(dto.getCourseId());
		dto.setCourseName(courseDTO.getName());

		FacultyDTO duplicateEmail = findByEmailId(dto.getEmail()); // Check if
																	// updated
																	// Role
																	// already
																	// exist
		if (duplicateEmail != null && duplicateEmail.getId() != dto.getId()) {
			throw new DuplicateRecordException(
					"Faculty's Email  already exists");

		}

		if (dto.getPrimarySubject().equals(dto.getSecondarySubject())) {
			throw new DatabaseException(
					"Primary and secondary subject cannot be same");

		}

		Session session = HibDataSource.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(dto);
			pk = dto.getId();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ApplicationException("Exception in Faculty Add "
					+ e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		log.debug("Model add End");
		return dto.getId();
	}

	/**
	 * Delete a Faculty
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void delete(FacultyDTO dto) throws ApplicationException {
		log.debug("Model delete Started");
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibDataSource.getSession();
			transaction = session.beginTransaction();
			session.delete(dto);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			if (transaction != null) {
				transaction.rollback();
			}
			throw new ApplicationException("Exception in Faculty Delete"
					+ e.getMessage());
		} finally {
			session.close();
		}
		log.debug("Model delete End");
	}

	/**
	 * Find Role by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public FacultyDTO findByPK(long pk) throws ApplicationException {

		log.debug("Model findBypk Started");
		FacultyDTO dto = null;
		Session session = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(FacultyDTO.class);
			criteria.add(Restrictions.eq("id", pk));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (FacultyDTO) list.get(0);
			} else {
				dto = null;
			}

		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException(
					"Exception in getting Faculty by pk " + e.getMessage());

		} finally {
			session.close();
		}
		log.debug("Model findBypk End");
		return dto;
	}

	/**
	 * Update a Course
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void update(FacultyDTO dto) throws ApplicationException,
			DuplicateRecordException, DatabaseException {
		log.debug("Model update Started");
		Session session = null;
		Transaction transaction = null;

		CollegeModelInt college = ModelFactory.getInstance().getCollegeModel();
		CollegeDTO collegeDTO = college.findByPK(dto.getCollegeId());
		dto.setCollegeName(collegeDTO.getName());

		CourseModelInt course = ModelFactory.getInstance().getCourseModel();
		CourseDTO courseDTO = course.findByPK(dto.getCourseId());
		dto.setCourseName(courseDTO.getName());

		FacultyDTO duplicateFaculty = findByEmailId(dto.getEmail());
		// Check if updated Role already exist
		if (duplicateFaculty != null && duplicateFaculty.getId() != dto.getId()) {
			throw new DuplicateRecordException(
					"Faculty's Email Id already exists");
		}
		if (dto.getPrimarySubject().equals(dto.getSecondarySubject())) {
			throw new DatabaseException(
					"Primary and secondary subject cannot be same");

		}

		try {
			session = HibDataSource.getSession();
			transaction = session.beginTransaction();
			session.update(dto);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			if (transaction != null) {
				transaction.rollback();
				throw new ApplicationException("Exception in Faculty Update"
						+ e.getMessage());
			}
		} finally {
			session.close();
		}
		log.debug("Model update End");
	}

	/**
	 * Searches Faculty
	 * 
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(FacultyDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/**
	 * Searches Faculty with pagination
	 * 
	 * @return list : List of Faculty
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */
	public List search(FacultyDTO dto, int pageNo, int pageSize)
			throws ApplicationException {

		log.debug("Model search Started");
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(FacultyDTO.class);

			/*
			 * if (dto.getId() > 0) { criteria.add(Restrictions.eq("id",
			 * dto.getId())); }
			 */
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				criteria.add(Restrictions.like("firstName", dto.getFirstName()
						+ "%"));
			}

			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
				criteria.add(Restrictions.like("lastName", dto.getLastName()
						+ "%"));
			}
			if (dto.getCourseName() != null && dto.getCourseName().length() > 0) {
				criteria.add(Restrictions.like("course_name",
						dto.getCourseName() + "%"));
			}
			if (dto.getCourseId() > 0) {
				criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
			}
			criteria.addOrder(Order.desc("id"));

			// if page size is greater than zero the apply pagination
			if (pageSize > 0) {
				criteria.setFirstResult(((pageNo - 1) * pageSize));
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception in Course search");
		} finally {
			session.close();
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Gets List of Faculty
	 * 
	 * @return list : List of Courses
	 * @throws DatabaseException
	 */
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * get List of Faculty with pagination
	 * 
	 * @return list : List of Courses
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(FacultyDTO.class);

			// if page size is greater than zero then apply pagination
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize) + 1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();
		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in  Faculty list");
		} finally {
			session.close();
		}

		log.debug("Model list End");
		return list;
	}

	/**
	 * Find Faculty by Email
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public FacultyDTO findByEmailId(String email) throws ApplicationException {

		log.debug("Model findByName Started");
		Session session = null;
		FacultyDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(FacultyDTO.class);
			criteria.add(Restrictions.eq("email", email));
			List list = criteria.list();

			if (list.size() == 1) {
				dto = (FacultyDTO) list.get(0);
			}

		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception in getting Course by name " + e.getMessage());

		} finally {
			session.close();
		}

		log.debug("Model findByName End");
		return dto;
	}

}
