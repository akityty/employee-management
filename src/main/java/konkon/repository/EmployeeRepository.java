package konkon.repository;

import konkon.model.Department;
import konkon.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
  Page<Employee> findAllByNameContaining(String name, Pageable pageable);
  @Query("select  e from Employee  e order by  e.salary desc ")
  Page<Employee> sort(Pageable pageable);
  Iterable<Employee> findAllByDepartment(Department department);
}
