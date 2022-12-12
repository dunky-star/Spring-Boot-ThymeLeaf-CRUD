package springcrudthymeleaf.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springcrudthymeleaf.model.Employee;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class MainController {

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

    // Add a mapping for "/list
    @GetMapping("/list")
    public String listEmployees(Model theModel){

        // Add to the Spring model.
        theModel.addAttribute("employees", theEmployees);
        return "list-employees";
    }
}
