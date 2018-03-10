package in.co.mss.rmshib.dto;

/**
 * Faculty DTO class encapsulates Faculty attributes
 * 
 * @author Session Facade
 * @version 1.0
 *
 */

import java.util.Date;

public class FacultyDTO extends BaseDTO {

	/**
	 * First Name of Faculty
	 */
	private String firstName;
	/**
	 * Last Name of Faculty
	 */
	private String lastName;
	/**
	 * Date of Birth of Faculty
	 */
	private Date dob;
	/**
	 * Qualification of Faculty
	 */
	private String qualification;
	/**
	 * Email of Faculty
	 */
	private String email;
	/**
	 * MobileNo of Faculty
	 */
	private String mobileNo;
	/**
	 * Address of Faculty
	 */
	private String address;
	/**
	 * Primary Subject of Faculty
	 */
	private String primarySubject;
	/**
	 * Secondary Subject of Faculty
	 */
	private String secondarySubject;
	/**
	 * CollegeId of Faculty
	 */
	private long collegeId;
	/**
	 * College Name of Faculty
	 */
	private String collegeName;
	/**
	 * CourseId of Faculty
	 */
	private long courseId;
	/**
	 * Course Name of Faculty
	 */
	private String courseName;

	/**
	 * accessor
	 */

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrimarySubject() {
		return primarySubject;
	}

	public void setPrimarySubject(String primarySubject) {
		this.primarySubject = primarySubject;
	}

	public String getSecondarySubject() {
		return secondarySubject;
	}

	public void setSecondarySubject(String secondarySubject) {
		this.secondarySubject = secondarySubject;
	}

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return firstName + " " + lastName;
	}

}
