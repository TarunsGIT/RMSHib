package in.co.mss.rmshib.model;

import in.co.mss.rmshib.dto.CollegeDTO;
import in.co.mss.rmshib.dto.CourseDTO;
import in.co.mss.rmshib.dto.FacultyDTO;
import in.co.mss.rmshib.exception.ApplicationException;
import in.co.mss.rmshib.exception.DatabaseException;
import in.co.mss.rmshib.exception.DuplicateRecordException;
import in.co.mss.rmshib.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * JDBC Implementation of Faculty Model
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */

public class FacultyModelJDBCImpl implements FacultyModelInt {

	private static Logger log = Logger.getLogger(FacultyModelJDBCImpl.class);

	/************************************* NEXT PRIMARY KEY OF STUDENT *************************/

	/**
	 * 
	 * Find next PK of Faculty
	 * 
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT MAX(ID) FROM FACULTY");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	/******************************** FIND FACULTY BY PK *****************************************/

	/**
	 * Find Faculty by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public FacultyDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM FACULTY WHERE ID=?");
		FacultyDTO dto = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new FacultyDTO();
				dto.setId(rs.getLong(1));
				dto.setCollegeId(rs.getLong(2));
				dto.setCollegeName(rs.getString(3));
				dto.setCourseId(rs.getLong(4));
				dto.setCourseName(rs.getString(5));
				dto.setPrimarySubject(rs.getString(6));
				dto.setSecondarySubject(rs.getString(7));
				dto.setFirstName(rs.getString(8));
				dto.setLastName(rs.getString(9));
				dto.setQualification(rs.getString(10));
				dto.setDob(rs.getDate(11));
				dto.setMobileNo(rs.getString(12));
				dto.setEmail(rs.getString(13));
				dto.setAddress(rs.getString(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Faculty by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return dto;
	}

	/*********************************** FIND BY EMAIL ***************************************/

	/**
	 * Find User by Faculty
	 * 
	 * @param Email
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public FacultyDTO findByEmailId(String Email) throws ApplicationException {
		log.debug("Model findBy Email Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM FACULTY WHERE EMAIL=?");
		FacultyDTO dto = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, Email);
			ResultSet rs = pstmt.executeQuery();// FOR SELECT STATMENT WE USE
												// executeQuery();
			while (rs.next()) {
				dto = new FacultyDTO();
				dto.setId(rs.getLong(1));
				dto.setCollegeId(rs.getLong(2));
				dto.setCollegeName(rs.getString(3));
				dto.setCourseId(rs.getLong(4));
				dto.setCourseName(rs.getString(5));
				dto.setPrimarySubject(rs.getString(6));
				dto.setSecondarySubject(rs.getString(7));
				dto.setFirstName(rs.getString(8));
				dto.setLastName(rs.getString(9));
				dto.setQualification(rs.getString(10));
				dto.setDob(rs.getDate(11));
				dto.setMobileNo(rs.getString(12));
				dto.setEmail(rs.getString(13));
				dto.setAddress(rs.getString(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Faculty by Email");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Email End");
		return dto;
	}

	/*********************************
	 * ADD FACULTY
	 * 
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 * @throws DatabaseException
	 * 
	 * 
	 * 
	 * 
	 *********************************************/

	public long add(FacultyDTO dto) throws ApplicationException,
			DuplicateRecordException, DatabaseException {
		log.debug("Model add Started");
		Connection conn = null;

		// get College Name According To College ID
		CollegeModelInt cModel = ModelFactory.getInstance().getCollegeModel();
		CollegeDTO collegeDTO = cModel.findByPK(dto.getCollegeId());
		dto.setCollegeName(collegeDTO.getName());

		// get Course Name According to Course ID
		CourseModelInt Model = ModelFactory.getInstance().getCourseModel();
		CourseDTO courseDTO = Model.findByPK(dto.getCourseId());
		dto.setCourseName(courseDTO.getName());

		FacultyDTO duplicateName = findByEmailId(dto.getEmail());
		/*
		 * FacultyBean dupicatePrimary = findByPrimarySubject(bean
		 * .getPrimarySubject()); FacultyBean dupicateSecondary =
		 * findBySecondarySubject(bean .getSecondarySubject());
		 */

		int pk = 0;
		if (duplicateName != null) {
			throw new DuplicateRecordException("Email already exists");
		}
		if (dto.getPrimarySubject().equals(dto.getSecondarySubject())) {
			throw new DatabaseException(
					"Primary and Secondary subject cannot be same");

		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(" RECORD SUCESSFULLY ADDED WITH PRIMARY KEY:"
					+ pk + " in ModelJDBC");

			conn.setAutoCommit(false); // Begin transaction
			System.out.println("BESIDE SET AUTO COMMIT");
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO FACULTY VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, dto.getCollegeId());
			pstmt.setString(3, dto.getCollegeName());
			pstmt.setLong(4, dto.getCourseId());
			pstmt.setString(5, dto.getCourseName());
			pstmt.setString(6, dto.getPrimarySubject());
			pstmt.setString(7, dto.getSecondarySubject());
			pstmt.setString(8, dto.getFirstName());
			pstmt.setString(9, dto.getLastName());
			pstmt.setString(10, dto.getQualification());
			pstmt.setDate(11, new java.sql.Date(dto.getDob().getTime()));
			pstmt.setString(12, dto.getMobileNo());
			pstmt.setString(13, dto.getEmail());
			pstmt.setString(14, dto.getAddress());
			pstmt.setString(15, dto.getCreatedBy());
			pstmt.setString(16, dto.getModifiedBy());
			pstmt.setTimestamp(17, dto.getCreatedDatetime());
			pstmt.setTimestamp(18, dto.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in Add Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/******************************* DELETE FACULTY ***************************************/

	public void delete(FacultyDTO dto) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM FACULTY WHERE ID=?");
			pstmt.setLong(1, dto.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : Delete rollback exception "
								+ ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in Delete Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/*****************************
	 * UPDATE FACULTY
	 * 
	 * @throws DatabaseException
	 **********************************************/

	public void update(FacultyDTO dto) throws ApplicationException,
			DuplicateRecordException, DatabaseException {
		log.debug("Model update Started");
		Connection conn = null;

		// get College Name According To College ID
		CollegeModelInt cModel = ModelFactory.getInstance().getCollegeModel();
		CollegeDTO collegeDTO = cModel.findByPK(dto.getCollegeId());
		dto.setCollegeName(collegeDTO.getName());

		// get Course Name According to Course ID
		CourseModelInt Model = ModelFactory.getInstance().getCourseModel();
		CourseDTO courseDTO = Model.findByPK(dto.getCourseId());
		dto.setCourseName(courseDTO.getName());

		FacultyDTO dtoExist = findByEmailId(dto.getEmail());
		 

		if (dtoExist != null && dtoExist.getId() != dto.getId()) {
			throw new DuplicateRecordException("Email Id Already Exists");
		}

		if (dto.getPrimarySubject().equals(dto.getSecondarySubject())) {
			throw new DatabaseException(
					"Primary and Secondary subject cannot be same");

		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE FACULTY SET COLLEGE_ID=?,COLLEGE_NAME=?,COURSE_ID=?,COURSE_NAME=?,PRIMARY_SUBJECT=?,SECONDARY_SUBJECT=?,FIRST_NAME=?,LAST_NAME=?,QUALIFICATION=?,DATE_OF_BIRTH=?,MOBILE_NO=?,EMAIL=?,ADDRESS=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setLong(1, dto.getCollegeId());
			pstmt.setString(2, dto.getCollegeName());
			pstmt.setLong(3, dto.getCourseId());
			pstmt.setString(4, dto.getCourseName());
			pstmt.setString(5, dto.getPrimarySubject());
			pstmt.setString(6, dto.getSecondarySubject());
			pstmt.setString(7, dto.getFirstName());
			pstmt.setString(8, dto.getLastName());
			pstmt.setString(9, dto.getQualification());
			pstmt.setDate(10, new java.sql.Date(dto.getDob().getTime()));
			pstmt.setString(11, dto.getMobileNo());
			pstmt.setString(12, dto.getEmail());
			pstmt.setString(13, dto.getAddress());
			pstmt.setString(14, dto.getCreatedBy());
			pstmt.setString(15, dto.getModifiedBy());
			pstmt.setTimestamp(16, dto.getCreatedDatetime());
			pstmt.setTimestamp(17, dto.getModifiedDatetime());
			pstmt.setLong(18, dto.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : Delete rollback exception "
								+ ex.getMessage());
			}
			throw new ApplicationException("Exception in Updating Faculty ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	public List search(FacultyDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/******************************** SEARCH FACULTY ******************************************/

	/**
	 * Search Faculty with pagination
	 * 
	 * @return list : List of Faculty
	 * @param bean
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

		StringBuffer sql = new StringBuffer("SELECT * FROM FACULTY WHERE 1=1");
		if (dto != null) {
			if (dto.getId() > 0) {
				sql.append(" AND id = " + dto.getId());
			}
			if (dto.getCourseName() != null && dto.getCourseName().length() > 0) {
				sql.append(" AND COURSE_NAME = " + dto.getCourseName());
			}
			if (dto.getCourseId() > 0) {
				sql.append(" AND COURSE_ID = " + dto.getCourseId());
			}
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME LIKE '" + dto.getFirstName() + "%'");
			}
			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME LIKE '" + dto.getLastName() + "%'");
			}
			if (dto.getQualification() != null
					&& dto.getQualification().length() > 0) {
				sql.append(" AND QUALIFICATION LIKE '" + dto.getQualification()
						+ "%'");
			}
			if (dto.getDob() != null && dto.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + dto.getDob());
			}
			if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
				sql.append(" AND MOBILE_NO LIKE '" + dto.getMobileNo() + "%'");
			}
			if (dto.getEmail() != null && dto.getEmail().length() > 0) {
				sql.append(" AND EMAIL LIKE '" + dto.getEmail() + "%'");
			}
			if (dto.getCollegeName() != null
					&& dto.getCollegeName().length() > 0) {
				sql.append(" AND COLLEGE_NAME = " + dto.getCollegeName());
			}
			sql.append(" ORDER BY id DESC ");
		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new FacultyDTO();
				dto.setId(rs.getLong(1));
				dto.setCollegeId(rs.getLong(2));
				dto.setCollegeName(rs.getString(3));
				dto.setCourseId(rs.getLong(4));
				dto.setCourseName(rs.getString(5));
				dto.setPrimarySubject(rs.getString(6));
				dto.setSecondarySubject(rs.getString(7));
				dto.setFirstName(rs.getString(8));
				dto.setLastName(rs.getString(9));
				dto.setQualification(rs.getString(10));
				dto.setDob(rs.getDate(11));
				dto.setMobileNo(rs.getString(12));
				dto.setEmail(rs.getString(13));
				dto.setAddress(rs.getString(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));
				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model search End");
		return list;
	}

	public List list() throws ApplicationException { // LIST OF STUDENT
		return list(0, 0);
	}

	/************************************** LIST FACULTY **************************************/

	/**
	 * Get List of Faculty with pagination
	 * 
	 * @return list : List of Student
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from FACULTY");

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FacultyDTO dto = new FacultyDTO();
				dto.setId(rs.getLong(1));
				dto.setCollegeId(rs.getLong(2));
				dto.setCollegeName(rs.getString(3));
				dto.setCourseId(rs.getLong(4));
				dto.setCourseName(rs.getString(5));
				dto.setPrimarySubject(rs.getString(6));
				dto.setSecondarySubject(rs.getString(7));
				dto.setFirstName(rs.getString(8));
				dto.setLastName(rs.getString(9));
				dto.setQualification(rs.getString(10));
				dto.setDob(rs.getDate(11));
				dto.setMobileNo(rs.getString(12));
				dto.setEmail(rs.getString(13));
				dto.setAddress(rs.getString(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));
				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting list of Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list End");
		return list;

	}

}
