package in.co.mss.rmshib.model;

import java.util.List;


import in.co.mss.rmshib.dto.CollegeDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;

/**
 * Data Access Object of College
 *
 * @author Session Facade
 * @version 1.0
 * 
 */


public interface CollegeModelInt {
	public long add(CollegeDTO dto) throws ApplicationException,
			DuplicateRecordException;

	public void update(CollegeDTO dto) throws ApplicationException,
			DuplicateRecordException;

	public void delete(CollegeDTO dto) throws ApplicationException;

	public CollegeDTO findByName(String name) throws ApplicationException;

	public CollegeDTO findByPK(long pk) throws ApplicationException;

	public List search(CollegeDTO dto, int pageNo, int pageSize)
			throws ApplicationException;

	public List search(CollegeDTO dto) throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

}
