package in.co.mss.rmshib.model;

import in.co.mss.rmshib.dto.TimeTableDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DatabaseException;
import in.co.mss.rmshib.exception.DuplicateRecordException;

import java.util.Date;
import java.util.List;

/**
 * Data Access Object of Time Table
 * 
 * @author Session Facade
 * @version 1.0
 *
 */

public interface TimeTableModelInt {

	/**
	 * Add a Time Table
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when Role already exists
	 */
	public long add(TimeTableDTO dto) throws ApplicationException,
			DuplicateRecordException, DatabaseException;

	/**
	 * Update a Time Table
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : if updated user record is already exist
	 */
	public void update(TimeTableDTO dto) throws ApplicationException,
			DuplicateRecordException, DatabaseException;

	/**
	 * Delete a Time Table
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(TimeTableDTO dto) throws ApplicationException;

	/**
	 * Find Time Table by Course,Subject
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public TimeTableDTO findByCourse(String course, String subject)
			throws ApplicationException;

	/**
	 * Find Time Table by Examination Date,Course
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public TimeTableDTO findByExaminationDate(Date examinationDate, String course)
			throws ApplicationException;

	/**
	 * Find Time Table by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public TimeTableDTO findByPK(long pk) throws ApplicationException;

	/**
	 * Search Role with pagination
	 * 
	 * @return list : List of Time Table
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */
	public List search(TimeTableDTO dto, int pageNo, int pageSize)
			throws ApplicationException;

	/**
	 * Search Time Table
	 * 
	 * @return list : List of Time Table
	 * @param dto
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(TimeTableDTO dto) throws ApplicationException;

	/**
	 * Gets List of Time Table
	 * 
	 * @return list : List of Time Table
	 * @throws DatabaseException
	 */
	public List list() throws ApplicationException;

	/**
	 * get List of Time Table with pagination
	 * 
	 * @return list : List of Time Table
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException;

}
