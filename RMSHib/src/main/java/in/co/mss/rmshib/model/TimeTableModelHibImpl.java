package in.co.mss.rmshib.model;

import in.co.mss.rmshib.dto.CourseDTO;
import in.co.mss.rmshib.dto.TimeTableDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DatabaseException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.util.HibDataSource;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Hibernate Implementation of Time Table Model
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
public class TimeTableModelHibImpl implements TimeTableModelInt {

	private static Logger log = Logger.getLogger(TimeTableModelHibImpl.class);

	/**
	 * Find Time Table by Course
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public TimeTableDTO findByCourse(String course, String subject)
			throws ApplicationException {

		log.debug("Model findByCourse Started");
		Session session = null;
		TimeTableDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TimeTableDTO.class);
			criteria.add(Restrictions.eq("course", course));
			criteria.add(Restrictions.eq("subject", subject));
			/* criteria.add(Restrictions.and("course_name", course)); */

			/*
			 * criteria.add(Restrictions.and(Restrictions.eq("course", course),
			 * Restrictions.eq("subject", subject)));
			 */
			List list = criteria.list();

			if (list.size() == 1) {
				dto = (TimeTableDTO) list.get(0);
			}

		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception in getting Time Table by Course "
							+ e.getMessage());

		} finally {
			session.close();
		}

		log.debug("Model findByCourse End");
		return dto;
	}

	/**
	 * Find Time Table by Examination Date
	 * 
	 * @param name
	 *            : get parameter
	 * @return list
	 * @throws DatabaseException
	 */
	public TimeTableDTO findByExaminationDate(Date examinationDate,
			String course) throws ApplicationException {

		log.debug("Model findByExaminatinDate Started");
		Session session = null;
		TimeTableDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TimeTableDTO.class);

			/*
			 * criteria.add(Restrictions.eq("course", course));
			 * criteria.add(Restrictions.eq("examinationDate",
			 * examinationDate));
			 */

			criteria.add(Restrictions.and(Restrictions.eq("examinationDate",
					new java.sql.Date(examinationDate.getTime())), Restrictions
					.eq("course", course)));

			List list = criteria.list();

			if (list.size() == 1) {
				dto = (TimeTableDTO) list.get(0);

			}

		} catch (HibernateException e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception in getting Time Table by Examination Date "
							+ e.getMessage());

		} finally {
			session.close();
		}

		log.debug("Model findByExaminationDate End");
		return dto;
	}

	/**
	 * Add a Time Table
	 * 
	 * @param dto
	 * @throws DatabaseException
	 * 
	 */
	public long add(TimeTableDTO dto) throws ApplicationException,
			DuplicateRecordException, DatabaseException {

		log.debug("Model add Started");
		long pk = 0;

		CourseModelInt course = ModelFactory.getInstance().getCourseModel();
		CourseDTO courseDTO = course.findByPK(dto.getCourseId());
		dto.setCourse(courseDTO.getName());

		TimeTableDTO duplicateCourse = findByCourse(dto.getCourse(),
				dto.getSubject());

		TimeTableDTO duplicateExaminationDate = findByExaminationDate(
				dto.getExaminationDate(), dto.getCourse());

		if (duplicateCourse != null) {
			throw new DuplicateRecordException(
					"Exam is already been scheduled for this subject in this course");

		} else if (duplicateExaminationDate != null) {
			throw new DatabaseException(
					"This date is already been occupied for this course");

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
			throw new ApplicationException("Exception in Add Time Table"
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
	 * Delete a Time Table
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void delete(TimeTableDTO dto) throws ApplicationException {
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
			throw new ApplicationException("Exception in Time Table Delete"
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
	public TimeTableDTO findByPK(long pk) throws ApplicationException {

		log.debug("Model findBypk Started");
		TimeTableDTO dto = null;
		Session session = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TimeTableDTO.class);
			criteria.add(Restrictions.eq("id", pk));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (TimeTableDTO) list.get(0);
			} else {
				dto = null;
			}

		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException(
					"Exception in getting Time Table by pk " + e.getMessage());

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
	public void update(TimeTableDTO dto) throws ApplicationException,
			DuplicateRecordException, DatabaseException {
		log.debug("Model update Started");
		Session session = null;
		Transaction transaction = null;
		
		CourseModelInt course = ModelFactory.getInstance().getCourseModel();
		CourseDTO courseDTO = course.findByPK(dto.getCourseId());
		dto.setCourse(courseDTO.getName());

		TimeTableDTO duplicateCourse = findByCourse(dto.getCourse(),
				dto.getSubject());

		TimeTableDTO duplicateExaminationDate = findByExaminationDate(
				dto.getExaminationDate(), dto.getCourse());
		

		if (duplicateCourse != null) {
			throw new DuplicateRecordException(
					"Exam is already been scheduled for this subject in this course");

		} else if (duplicateExaminationDate != null) {
			throw new DatabaseException(
					"This date is already been occupied for this course");

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
				throw new ApplicationException("Exception in Update Time Table"
						+ e.getMessage());
			}
		} finally {
			session.close();
		}
		log.debug("Model update End");
	}

	/**
	 * Searches Time Table
	 * 
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(TimeTableDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/**
	 * Searches Time Table with pagination
	 * 
	 * @return list : List of Time Table
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */
	public List search(TimeTableDTO dto, int pageNo, int pageSize)
			throws ApplicationException {

		log.debug("Model search Started");
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TimeTableDTO.class);

			/*
			 * if (dto.getId() > 0) { criteria.add(Restrictions.eq("id",
			 * dto.getId())); }
			 */
			if (dto.getCourse() != null && dto.getCourse().length() > 0) {
				criteria.add(Restrictions.like("course_name", dto.getCourse()
						+ "%"));
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
			throw new ApplicationException("Exception in Time Table search");
		} finally {
			session.close();
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Gets List of Time Table
	 * 
	 * @return list : List of Time Table
	 * @throws DatabaseException
	 */
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * get List of Time Table with pagination
	 * 
	 * @return list : List of Time Table
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
			Criteria criteria = session.createCriteria(TimeTableDTO.class);

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
					"Exception : Exception in  Time Table list");
		} finally {
			session.close();
		}

		log.debug("Model list End");
		return list;
	}

}
