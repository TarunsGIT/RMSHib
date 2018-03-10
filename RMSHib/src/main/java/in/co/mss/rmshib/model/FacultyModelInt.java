package in.co.mss.rmshib.model;

import in.co.mss.rmshib.dto.FacultyDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DatabaseException;
import in.co.mss.rmshib.exception.DuplicateRecordException;

import java.util.List;

/**
 * Data Access Object of Faculty
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */

public interface FacultyModelInt {
	/**
	 * Add a Faculty
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when Role already exists
	 */
	public long add(FacultyDTO dto) throws ApplicationException,
			DuplicateRecordException,DatabaseException;

	/**
	 * Update a Faculty
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : if updated user record is already exist
	 */
	public void update(FacultyDTO dto) throws ApplicationException,
			DuplicateRecordException,DatabaseException;

	/**
	 * Delete a Faculty
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(FacultyDTO dto) throws ApplicationException;

	/**
	 * Find Faculty by EmailId
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public FacultyDTO findByEmailId(String name) throws ApplicationException;

	/**
	 * Find Faculty by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public FacultyDTO findByPK(long pk) throws ApplicationException;

	/**
	 * Search Role with pagination
	 * 
	 * @return list : List of Faculty
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */
	public List search(FacultyDTO dto, int pageNo, int pageSize)
			throws ApplicationException;

	/**
	 * Search Faculty
	 * 
	 * @return list : List of Faculty
	 * @param dto
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(FacultyDTO dto) throws ApplicationException;

	/**
	 * Gets List of Faculty
	 * 
	 * @return list : List of Faculty
	 * @throws DatabaseException
	 */
	public List list() throws ApplicationException;

	/**
	 * get List of Faculty with pagination
	 * 
	 * @return list : List of Faculty
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException;

}
