package konkon.service;

import konkon.model.Employee;
import konkon.model.EmployeeForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
  void save(EmployeeForm employeeForm);
  void delete(Long id);
  Employee getEmployee(EmployeeForm employeeForm);
  Employee findById(Long id);
  Page<Employee> findAll(Pageable pageable);
  Page<Employee> findAllByNameContaining(String name,Pageable pageable);

}
