package in.co.mss.rmshib.model;

import in.co.mss.rmshib.dto.CourseDTO;
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
 * JDBC Implementation of Course Model
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */

public class CourseModelJDBCImpl implements CourseModelInt {
	private static Logger log = Logger.getLogger(CourseModelJDBCImpl.class);

	/***************************** NEXT PRIMARY KEY OF ROLE **********************************************************/

	/**
	 * Find next PK of Role
	 * 
	 * @throws DatabaseException
	 */

	public Integer nextPK() throws DatabaseException { // FIND NEXT PK OF ROLE
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT MAX(ID) FROM COURSE");
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

	/*********************************** ADD COURSE *******************************************************/
	public long add(CourseDTO dto) throws ApplicationException,
			DuplicateRecordException { // ADD A ROLE
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		CourseDTO duplicataRole = findByName(dto.getName());
		// Check if create Course already exist
		if (duplicataRole != null) {
			throw new DuplicateRecordException("Course already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO COURSE VALUES(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getDescription());
			pstmt.setInt(4, dto.getDuration());
			pstmt.setString(5, dto.getCreatedBy());
			pstmt.setString(6, dto.getModifiedBy());
			pstmt.setTimestamp(7, dto.getCreatedDatetime());
			pstmt.setTimestamp(8, dto.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in add Course");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/********************************** DELETE COURSE ***********************************************************/
	public void delete(CourseDTO dto) throws ApplicationException { // DELETE
																	// A
																	// COURSE
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM COURSE WHERE ID=?");
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
					"Exception : Exception in delete Course");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/***************************** (FIND BY NAME) **************************************************/

	public CourseDTO findByName(String name) throws ApplicationException {
		log.debug("Model findBy EmailId Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM COURSE WHERE NAME=?");
		CourseDTO dto = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new CourseDTO();
				dto.setId(rs.getLong(1));
				dto.setName(rs.getString(2));
				dto.setDescription(rs.getString(3));
				dto.setDuration(rs.getInt(4));
				dto.setCreatedBy(rs.getString(5));
				dto.setModifiedBy(rs.getString(6));
				dto.setCreatedDatetime(rs.getTimestamp(7));
				dto.setModifiedDatetime(rs.getTimestamp(8));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy EmailId End");
		return dto;
	}

	/************************************ FIND COURSE BY PK (FIND BY PK) **********************************************/

	public CourseDTO findByPK(long pk) throws ApplicationException { // FIND
																		// COURSE
																		// BY PK
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM COURSE WHERE ID=?");
		CourseDTO dto = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new CourseDTO();
				dto.setId(rs.getLong(1));
				dto.setName(rs.getString(2));
				dto.setDescription(rs.getString(3));
				dto.setDuration(rs.getInt(4));
				dto.setCreatedBy(rs.getString(5));
				dto.setModifiedBy(rs.getString(6));
				dto.setCreatedDatetime(rs.getTimestamp(7));
				dto.setModifiedDatetime(rs.getTimestamp(8));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return dto;
	}

	/*********************************** UPDATE ROLE *****************************************************************/

	public void update(CourseDTO dto) throws ApplicationException, // UPDATE A
																	// COURSE
			DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		CourseDTO duplicataRole = findByName(dto.getName());

		// Check if updated Role already exist
		if (duplicataRole != null && duplicataRole.getId() != dto.getId()) {
			throw new DuplicateRecordException("Course already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE COURSE SET NAME=?,DESCRIPTION=?,DURATION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getDescription());
			pstmt.setInt(3, dto.getDuration());
			pstmt.setString(4, dto.getCreatedBy());
			pstmt.setString(5, dto.getModifiedBy());
			pstmt.setTimestamp(6, dto.getCreatedDatetime());
			pstmt.setTimestamp(7, dto.getModifiedDatetime());
			pstmt.setLong(8, dto.getId());
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
			throw new ApplicationException("Exception in updating Course ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	public List search(CourseDTO dto) throws ApplicationException { // SEARCH
																	// A
																	// ROLE
		return search(dto, 0, 0);

	}

	/*********************************** SEARCH ROLE *****************************************************************/

	/**
	 * Search Role with pagination
	 * 
	 * @return list : List of Roles
	 * @param bean
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
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM COURSE WHERE 1=1");
		if (dto != null) {
			if (dto.getId() > 0) {
				sql.append(" AND id = " + dto.getId());
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				sql.append(" AND NAME LIKE '" + dto.getName() + "%'");
			}
			if (dto.getDescription() != null
					&& dto.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION LIKE '" + dto.getDescription()
						+ "%'");
			}
			if (dto.getDuration() != null && dto.getDuration() > 0) {
				sql.append(" AND DURATION LIKE '" + dto.getDuration() + "%'");
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
				dto = new CourseDTO();
				dto.setId(rs.getLong(1));
				dto.setName(rs.getString(2));
				dto.setDescription(rs.getString(3));
				dto.setDuration(rs.getInt(4));
				dto.setCreatedBy(rs.getString(5));
				dto.setModifiedBy(rs.getString(6));
				dto.setCreatedDatetime(rs.getTimestamp(7));
				dto.setModifiedDatetime(rs.getTimestamp(8));
				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Course");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model search End");
		return list;
	}

	/*************************** GET LIST OF ROLE ************************************************************/

	public List list() throws ApplicationException { // GET LIST OF ROLE
		return list(0, 0);
	}

	/************************************* LIST OF ALL ROLE *******************************************************/

	/**
	 * Get List of Role with pagination
	 * 
	 * @return list : List of Role
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("SELECT * FROM COURSE");
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
				CourseDTO dto = new CourseDTO();
				dto.setId(rs.getLong(1));
				dto.setName(rs.getString(2));
				dto.setDescription(rs.getString(3));
				dto.setDuration(rs.getInt(4));
				dto.setCreatedBy(rs.getString(5));
				dto.setModifiedBy(rs.getString(6));
				dto.setCreatedDatetime(rs.getTimestamp(7));
				dto.setModifiedDatetime(rs.getTimestamp(8));
				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting list of Course");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list End");
		return list;

	}

}
