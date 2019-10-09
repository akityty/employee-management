package konkon.service.impl;

import konkon.model.Department;
import konkon.model.Employee;
import konkon.repository.DepartmentRepository;
import konkon.repository.EmployeeRepository;
import konkon.service.DepartmentService;
import konkon.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private DepartmentRepository departmentRepository;
  @Override
  public Iterable<Department> findAll() {
    return departmentRepository.findAll();
  }

  @Override
  public Department findById(Long id) {
    return departmentRepository.findOne(id);
  }

  @Override
  public void save(Department department) {
    departmentRepository.save(department);
  }

  @Override
  public void delete(Long id) {
    Department department = findById(id);
    List<Employee> employees = (List<Employee>) findAllByDepartment(department);
    for (Employee employee : employees) {
      employeeRepository.delete(employee.getId());
    }
    departmentRepository.delete(id);
  }

  @Override
  public Iterable<Employee> findAllByDepartment(Department department) {
    return  employeeRepository.findAllByDepartment(department);
  }

}
