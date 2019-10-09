package konkon.service.impl;

import konkon.model.Employee;
import konkon.model.EmployeeForm;
import konkon.repository.DepartmentRepository;
import konkon.repository.EmployeeRepository;
import konkon.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@PropertySource("classpath:global_config_app.properties")
public class EmployeeServiceImpl implements EmployeeService {
  @Autowired
  Environment env;
  @Autowired
  private EmployeeRepository employeeRepository;
  private DepartmentRepository departmentRepository;

  @Override
  public void save(EmployeeForm employeeForm) {
    Employee employee = getEmployee(employeeForm);
    employeeRepository.save(employee);
  }

  @Override
  public void delete(Long id) {
    employeeRepository.delete(id);
  }

  @Override
  public Employee getEmployee(EmployeeForm employeeForm) {
    MultipartFile multipartFile = employeeForm.getAvatar();
    String fileName = multipartFile.getOriginalFilename();
    String fileUpload = env.getProperty("file_upload").toString();

    try {
      //multipartFile.transferTo(imageFile);
      FileCopyUtils.copy(employeeForm.getAvatar().getBytes(), new File(fileUpload + fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (fileName.equals("") && employeeForm.getId() != null) {
      Employee employee = findById(employeeForm.getId());
      fileName = employee.getAvatar();
    }
    if (employeeForm.getId() == null) {
      return new Employee(employeeForm.getName(), employeeForm.getBirthDate(), employeeForm.getAddress(), fileName, employeeForm.getSalary(), employeeForm.getDepartment());
    } else
      return new Employee(employeeForm.getId(), employeeForm.getName(), employeeForm.getBirthDate(), employeeForm.getAddress(), fileName, employeeForm.getSalary(), employeeForm.getDepartment());
  }

  @Override
  public Employee findById(Long id) {
    return employeeRepository.findOne(id);
  }

  @Override
  public Page<Employee> findAll(Pageable pageable) {
    return employeeRepository.sort(pageable) ;
  }

  @Override
  public Page<Employee> findAllByNameContaining(String name, Pageable pageable) {
    return employeeRepository.findAllByNameContaining(name,pageable);
  }
}
