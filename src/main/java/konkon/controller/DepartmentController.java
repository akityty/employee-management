package konkon.controller;

import konkon.model.Department;
import konkon.model.Employee;
import konkon.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DepartmentController {
  @Autowired
  DepartmentService departmentService;

  @GetMapping("/department/create")
  public ModelAndView showCreateForm() {
    ModelAndView modelAndView = new ModelAndView("/department/create");
    modelAndView.addObject("department", new Department());
    return modelAndView;
  }

  @PostMapping("/department/create")
  public ModelAndView saveDepartment(@ModelAttribute Department department) {
    departmentService.save(department);
    ModelAndView modelAndView = new ModelAndView("/department/list");
    modelAndView.addObject(department);
    return modelAndView;
  }
  @GetMapping("/department/list")
  public ModelAndView departmentList(){
    Iterable<Department> departments = departmentService.findAll();
    ModelAndView modelAndView = new ModelAndView("/department/list");
    modelAndView.addObject("departments", departments);
    return modelAndView;
  }
  @GetMapping("/department/edit/{id}")
  public ModelAndView showDeleteDepartment(@PathVariable Long id){
    Department department =  departmentService.findById(id);
    if(department == null){
      return new ModelAndView("/error");
    }
    ModelAndView modelAndView = new ModelAndView("/department/edit");
    modelAndView.addObject(department);
    return modelAndView;
  }
  @PostMapping("/department/edit")
  public ModelAndView saveDepartment(@ModelAttribute Department department, BindingResult result){
    departmentService.save(department);
    return new ModelAndView("redirect:/department/list");
  }
  @GetMapping("/department/delete/{id}")
  public ModelAndView showDelete(@PathVariable Long id){
    Department department = departmentService.findById(id);
    if(department == null){
      return new ModelAndView("/error");
    }
    ModelAndView modelAndView = new ModelAndView("/department/delete");
    modelAndView.addObject(department);
    return modelAndView;
  }
  @PostMapping("/department/delete")
  public ModelAndView deleteDepartment(@ModelAttribute Department department){
    Long i = department.getId();
    departmentService.delete(department.getId());
    return new ModelAndView("redirect:/department/list");
  }
  @GetMapping("/department/view/{id}")
  public ModelAndView showEmployeesByDepartment(Department department){
   List<Employee> employees = (List<Employee>) departmentService.findAllByDepartment(department);
   ModelAndView modelAndView = new ModelAndView("/department/view");
   modelAndView.addObject("employees",employees);
   modelAndView.addObject("department",department);
   return modelAndView;
  }
  @PostMapping("/department/view")
  public ModelAndView modelAndView(@ModelAttribute Department department){
    ModelAndView modelAndView = new ModelAndView("/department/view");
    modelAndView.addObject(department);
    return modelAndView;
  }
}
