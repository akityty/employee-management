package konkon.service;

import konkon.model.Department;
import konkon.model.Employee;

public interface DepartmentService {
  Iterable<Department> findAll();
  Department findById(Long id);
  void save(Department department);
  void delete(Long id);
  Iterable<Employee> findAllByDepartment(Department department);
}
