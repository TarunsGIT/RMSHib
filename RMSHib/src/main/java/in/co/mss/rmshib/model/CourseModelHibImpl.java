package in.co.mss.rmshib.model;

import in.co.mss.rmshib.dto.CourseDTO;

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
import org.hibernate.criterion.Restrictions;

/**
 * Hibernate Implementation of CourseModel
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */

public class CourseModelHibImpl implements CourseModelInt {

	private static Logger log = Logger.getLogger(CourseModelHibImpl.class);

	/**
	 * Add a Course
	 * 
	 * @param dto
	 * @throws DatabaseException
	 * 
	 */
	public long add(CourseDTO dto) throws ApplicationException,
			DuplicateRecordException {

		log.debug("Model add Started");
		long pk = 0;

		CourseDTO duplicateCourse = findByName(dto.getName()); // Check if
																// updated
																// Role already
																// exist
		if (duplicateCourse != null) {
			throw new DuplicateRecordException("Course already exists");

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
			throw new ApplicationException("Exception in Course Add "
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
	 * Delete a Course
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void delete(CourseDTO dto) throws ApplicationException {
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
			throw new ApplicationException("Exception in Course Delete"
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
	public CourseDTO findByPK(long pk) throws ApplicationException {

		log.debug("Model findBypk Started");
		CourseDTO dto = null;
		Session session = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(CourseDTO.class);
			criteria.add(Restrictions.eq("id", pk));

			List list = criteria.list();
			if (list.size() == 1) {
				dto = (CourseDTO) list.get(0);
			} else {
				dto = null;
			}

		} catch (Exception e) {
			log.error(e);
			throw new ApplicationException("Exception in getting Course by pk "
					+ e.getMessage());

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
	public void update(CourseDTO dto) throws ApplicationException,
			DuplicateRecordException {
		log.debug("Model update Started");
		Session session = null;
		Transaction transaction = null;

		CourseDTO duplicateCourse = findByName(dto.getName());
		// Check if updated Role already exist
		if (duplicateCourse != null) {
			throw new DuplicateRecordException("Course already exists");
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
				throw new ApplicationException("Exception in Course Update"
						+ e.getMessage());
			}
		} finally {
			session.close();
		}
		log.debug("Model update End");
	}

	/**
	 * Searches Course
	 * 
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(CourseDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/**
	 * Searches Courses with pagination
	 * 
	 * @return list : List of Courses
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */
	public List search(CourseDTO dto, int pageNo, int pageSize)
			throws ApplicationException {

		System.out.println("in method search 1-->" + dto.getName());

		log.debug("Model search Started");
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(CourseDTO.class);

			/*
			 * if (dto.getId() > 0) { criteria.add(Restrictions.eq("id",
			 * dto.getId())); }
			 */

			if (dto.getName() != null && dto.getName().length() > 0) {
				criteria.add(Restrictions.like("name", dto.getName() + "%"));
			}
			if (dto.getDescription() != null
					&& dto.getDescription().length() > 0) {
				criteria.add(Restrictions.like("description",
						dto.getDescription() + "%"));
			}
			if (dto.getDuration() != null && dto.getDuration() > 0) {

				criteria.add(Restrictions.eq("duration", dto.getDuration()));
			}

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
	 * Gets List of Course
	 * 
	 * @return list : List of Courses
	 * @throws DatabaseException
	 */
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * get List of Course with pagination
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
			Criteria criteria = session.createCriteria(CourseDTO.class);

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
					"Exception : Exception in  Courses list");
		} finally {
			session.close();
		}

		log.debug("Model list End");
		return list;
	}

	/**
	 * Find Course by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public CourseDTO findByName(String name) throws ApplicationException {

		log.debug("Model findByName Started");
		Session session = null;
		CourseDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(CourseDTO.class);
			criteria.add(Restrictions.eq("name", name));
			List list = criteria.list();

			if (list.size() == 1) {
				dto = (CourseDTO) list.get(0);
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
