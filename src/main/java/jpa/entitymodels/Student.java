package jpa.entitymodels;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "email")
	private String sEmail;
	
	@ManyToMany(targetEntity = Course.class)
	private List<Course> sCourses;
	
	
	@Column(name = "name")
	private String sName;

	@Column(name = "password")
	private String sPass;

	

	public Student() {
		this.sEmail = "";
		this.sName = "";
		this.sPass = "";
		this.sCourses = null;
	}

	public Student(String sEmail, String sName, String sPass, List<Course> sCourses) {
		super();
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPass = sPass;
		this.sCourses = sCourses;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPass() {
		return sPass;
	}

	public void setsPass(String sPass) {
		this.sPass = sPass;
	}

	public List<Course> getsCourses() {
		return sCourses;
	}

	public void setsCourses(List<Course> sCourses) {
		this.sCourses = sCourses;
	}

	@Override
	public String toString() {
		return "Student [sEmail=" + sEmail + ", sName=" + sName + ", sPass=" + sPass + ", sCourses=" + sCourses + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(sCourses, sEmail, sName, sPass);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(sCourses, other.sCourses) && Objects.equals(sEmail, other.sEmail)
				&& Objects.equals(sName, other.sName) && Objects.equals(sPass, other.sPass);
	}
	

}
