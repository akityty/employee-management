package konkon.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthDate;
  private String address;
  private String avatar;
  private float salary;
  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department department;

  public Employee() {
  }

  public Employee(String name, Date birthDate, String address, String avatar, float salary, Department department) {
    this.name = name;
    this.birthDate = birthDate;
    this.address = address;
    this.avatar = avatar;
    this.salary = salary;
    this.department = department;
  }
  public Employee(Long id,String name, Date birthDate, String address, String avatar, float salary, Department department) {
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

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
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
