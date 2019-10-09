package konkon.formartter;

import konkon.service.DepartmentService;
import konkon.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

public class StringToLocalDateFormatter implements Formatter<LocalDate> {
  private EmployeeService employeeService;
  @Autowired
  public StringToLocalDateFormatter(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }
  @Override
  public LocalDate parse(String text, Locale locale) throws ParseException {
    return null;
  }

  @Override
  public String print(LocalDate object, Locale locale) {
    return null;
  }
}
