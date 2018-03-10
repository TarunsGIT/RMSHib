package in.co.mss.rmshib.model;

import java.util.List;


import in.co.mss.rmshib.dto.MarksheetDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DuplicateRecordException;

/**
 * Data Access Object of Marksheet
 *
 * @author Session Facade
 * @version 1.0
 * 
 */


public interface MarksheetModelInt {
	public long add(MarksheetDTO dto) throws ApplicationException,
			DuplicateRecordException;

	public void delete(MarksheetDTO dto) throws ApplicationException;

	public MarksheetDTO findByRollNo(String rollNo) throws ApplicationException;

	public MarksheetDTO findByPK(long pk) throws ApplicationException;

	public void update(MarksheetDTO dto) throws ApplicationException,
			DuplicateRecordException;

	public List search(MarksheetDTO dto) throws ApplicationException;

	public List search(MarksheetDTO dto, int pageNo, int pageSize)
			throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List getMeritList(int pageNo, int pageSize)
			throws ApplicationException;

}
