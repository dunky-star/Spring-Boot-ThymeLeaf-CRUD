package springcrudthymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springcrudthymeleaf.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

        // that's it ... no need to write any code LOL!
}