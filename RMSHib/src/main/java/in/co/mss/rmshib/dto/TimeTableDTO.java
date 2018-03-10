package in.co.mss.rmshib.dto;

import java.util.Date;

/**
 * Time Table DTO class encapsulates Time Table attributes
 * 
 * @author Session Facade
 * @version 1.0
 * * 
 */


public class TimeTableDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	/**
	 * Course ID
	 */
	private long courseId;

	/**
	 * Course Name
	 */
	private String course;
	/**
	 * Subject Name
	 */
	private String subject;
	/**
	 * Date of Exam
	 */
	private Date examinationDate;
	/**
	 * Time of Exam
	 */
	private String time;
	/**
	 * Day of Exam
	 */
	private String day;

	/**
	 * accessor
	 */
	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getExaminationDate() {
		return examinationDate;
	}

	public void setExaminationDate(Date examinationDate) {
		this.examinationDate = examinationDate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return course;
	}

}
