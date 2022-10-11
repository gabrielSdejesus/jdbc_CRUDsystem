package projectJDBC.entities;

public class Student {

	private Integer registration;
	private String name;
	private String phone;

	public Student() {
	}

	public Student(Integer registration, String name, String phone) {
		this.registration = registration;
		this.name = name;
		this.phone = phone;
	}

	public Integer getRegistration() {
		return registration;
	}

	public void setRegistration(Integer registration) {
		this.registration = registration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
