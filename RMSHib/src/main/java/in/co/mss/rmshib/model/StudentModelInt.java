package in.co.mss.rmshib.model;

import java.util.List;


import in.co.mss.rmshib.dto.StudentDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;

/**
 * Data Access Object of Student
 *
 * @author Session Facade
 * @version 1.0
 *
 */


public interface StudentModelInt {
	public long add(StudentDTO dto) throws ApplicationException,
			DuplicateRecordException;

	public void update(StudentDTO dto) throws ApplicationException,
			DuplicateRecordException;

	public void delete(StudentDTO dto) throws ApplicationException;

	public StudentDTO findByEmailId(String emailId) throws ApplicationException;

	public StudentDTO findByPK(long pk) throws ApplicationException;

	public List search(StudentDTO dto, int pageNo, int pageSize)
			throws ApplicationException;

	public List search(StudentDTO dto) throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

}
