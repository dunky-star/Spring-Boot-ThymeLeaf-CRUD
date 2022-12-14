package springcrudthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springcrudthymeleaf.entity.Employee;
import springcrudthymeleaf.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class MainController {

    private EmployeeService employeeService;
    public MainController(EmployeeService theEmployeeService) {
        this.employeeService = theEmployeeService;
    }

    /**
    // Load employee data
    private List<Employee> theEmployees;

    @PostConstruct
    private void loadData(){
        // Create employee
        Employee emp1 = new Employee(1, "Leslie", "Duncan", "dunky@mail.com");
        Employee emp2 = new Employee(2, "Anjana", "Jane", "anaa@mail.com");
        Employee emp3 = new Employee(3, "Deus", "Kan", "kan@mail.com");
        Employee emp4 = new Employee(4, "Hector", "Viz", "viz@mail.com");

        // Create the list
        theEmployees = new ArrayList<>();

        // Add to the list
        theEmployees.add(emp1);
        theEmployees.add(emp2);
        theEmployees.add(emp3);
        theEmployees.add(emp4);
    }
    */

    // Add a mapping for "/list
    @GetMapping("/list")
    public String listEmployees(Model theModel){

        // get employees from db
        List<Employee> theEmployees = employeeService.findAll();

        // Add to the Spring model.
        theModel.addAttribute("employees", theEmployees);
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId,
                                    Model theModel) {

        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);

        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee", theEmployee);

        // send over to our form
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // save the employee
        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submissions incase a user hits reloads on the browser.
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {

        // delete the employee
        employeeService.deleteById(theId);

        // redirect to /employees/list
        return "redirect:/employees/list";

    }
}
