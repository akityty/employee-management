package konkon.controller;

import konkon.model.Department;
import konkon.model.Employee;
import konkon.model.EmployeeForm;
import konkon.service.DepartmentService;
import konkon.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.Optional;

@Controller
public class EmployeeController {
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private DepartmentService departmentService;

  @ModelAttribute("departments")
  public Iterable<Department> getAllDepartment() {
    return departmentService.findAll();
  }

  @GetMapping("/employee/create")
  public ModelAndView showCreateForm() {
    ModelAndView modelAndView = new ModelAndView("/employee/create");
    modelAndView.addObject("employeeForm", new EmployeeForm());
    return modelAndView;
  }

  @PostMapping("/employee/create")
  public ModelAndView saveEmployee(@ModelAttribute EmployeeForm employeeForm) {
    employeeService.save(employeeForm);
    return new ModelAndView("redirect:/employee/list");
  }

  @GetMapping("/employee/list")
  public ModelAndView showListEmployee(@RequestParam("searchName") Optional<String> searchName, @PageableDefault(value = 3) Pageable pageable) {
    Page<Employee> employees;
    if (searchName.isPresent()) {
      employees = employeeService.findAllByNameContaining(searchName.get(), pageable);
    } else {
      employees = employeeService.findAll(pageable);
    }
    ModelAndView modelAndView = new ModelAndView("/employee/list");
    modelAndView.addObject("employees", employees);
    return modelAndView;
  }

  @GetMapping("/employee/edit/{id}")
  public ModelAndView showEditForm(@PathVariable Long id) {
    Employee employee = employeeService.findById(id);
    if (employee != null) {
      ModelAndView modelAndView = new ModelAndView("/employee/edit");
      modelAndView.addObject("employee", employee);
      return modelAndView;
    } else {
      return new ModelAndView("/error");
    }
  }

  @PostMapping("/employee/edit")
  public ModelAndView editEmployee(@ModelAttribute EmployeeForm employeeForm) {
    employeeService.save(employeeForm);
    return new ModelAndView("redirect:/employee/list");
  }

  @GetMapping("/employee/delete/{id}")
  public ModelAndView showDelete(@PathVariable Long id) {
    Employee employee = employeeService.findById(id);
    if (employee != null) {
      ModelAndView modelAndView = new ModelAndView("/employee/delete");
      modelAndView.addObject("employee", employee);
      return modelAndView;
    } else {
      return new ModelAndView("/error");
    }
  }
  @PostMapping("/employee/delete")
  public ModelAndView modelAndView(@ModelAttribute EmployeeForm employeeForm){
    employeeService.delete(employeeForm.getId());
    return new ModelAndView("redirect:/employee/list");
  }
}
