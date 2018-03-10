package in.co.mss.rmshib.model;

import in.co.mss.rmshib.dto.CourseDTO;
import in.co.mss.rmshib.dto.RoleDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;

import java.util.List;

/**
 * Data Access Object of Course
 * 
 * @author Session Facade
 * @version 1.0
 *
 */

public interface CourseModelInt {

	/**
	 * Add a Course
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when Role already exists
	 */
	public long add(CourseDTO dto) throws ApplicationException,
			DuplicateRecordException;

	/**
	 * Update a Course
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : if updated user record is already exist
	 */
	public void update(CourseDTO dto) throws ApplicationException,
			DuplicateRecordException;

	/**
	 * Delete a Courses
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(CourseDTO dto) throws ApplicationException;

	/**
	 * Find Course by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public CourseDTO findByName(String name) throws ApplicationException;

	/**
	 * Find Course by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public CourseDTO findByPK(long pk) throws ApplicationException;

	/**
	 * Search Role with pagination
	 * 
	 * @return list : List of Role
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */
	public List search(CourseDTO dto, int pageNo, int pageSize)
			throws ApplicationException;

	/**
	 * Search Course
	 * 
	 * @return list : List of Course
	 * @param dto
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(CourseDTO dto) throws ApplicationException;

	/**
	 * Gets List of Course
	 * 
	 * @return list : List of Course
	 * @throws DatabaseException
	 */
	public List list() throws ApplicationException;

	/**
	 * get List of Course with pagination
	 * 
	 * @return list : List of Course
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException;

}
