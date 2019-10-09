package konkon.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;

public class EmployeeForm {
  private Long id;
  private String name;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthDate;
  private String address;
  private MultipartFile avatar;
  private float salary;
  private Department department;

  public EmployeeForm() {
  }

  public EmployeeForm(Long id, String name, Date birthDate, String address, MultipartFile avatar, float salary, Department department) {
    this.id = id;
    this.name = name;
    this.birthDate = birthDate;
    this.address = address;
    this.avatar = avatar;
    this.salary = salary;
    this.department = department;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public MultipartFile getAvatar() {
    return avatar;
  }

  public void setAvatar(MultipartFile avatar) {
    this.avatar = avatar;
  }

  public float getSalary() {
    return salary;
  }

  public void setSalary(float salary) {
    this.salary = salary;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }
}
