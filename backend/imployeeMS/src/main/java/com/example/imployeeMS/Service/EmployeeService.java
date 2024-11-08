package com.example.imployeeMS.Service;

import com.example.imployeeMS.Dto.EmployeeDTO;
import com.example.imployeeMS.Entity.Department;
import com.example.imployeeMS.Entity.Employee;
import com.example.imployeeMS.Entity.Team;
import com.example.imployeeMS.Repo.DepartmentRepo;
import com.example.imployeeMS.Repo.EmployeeRepo;
import com.example.imployeeMS.Repo.TeamRepo;
import com.example.imployeeMS.Util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
     private ModelMapper modelMapper;


    public String saveEmployee(EmployeeDTO employeeDTO) {
        // Check if Employee already exists
        if (employeeRepo.existsById(employeeDTO.getEmpId())) {
            return VarList.RSP_DUPLICATED;
        }

        // Ensure department exists and is managed
        if (employeeDTO.getDepartmentId() != null) {
            Department department = departmentRepo.findById(employeeDTO.getDepartmentId()).orElse(null);
            if (department == null) {
                // Create and save the department if not found
                department = new Department();
                department.setId(employeeDTO.getDepartmentId());
                departmentRepo.save(department);
            }
        }

        // Ensure team exists and is managed
        if (employeeDTO.getTeamId() != null) {
            Team team = teamRepo.findById(employeeDTO.getTeamId()).orElse(null);
            if (team == null) {
                // Create and save the team if not found
                team = new Team();
                team.setId(employeeDTO.getTeamId());
                teamRepo.save(team);
            }
        }

        // Map DTO to Entity and save Employee
        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        // Make sure the department and team are set correctly
        if (employeeDTO.getDepartmentId() != null) {
            employee.setDepartment(departmentRepo.findById(employeeDTO.getDepartmentId()).orElse(null));
        }
        if (employeeDTO.getTeamId() != null) {
            employee.setTeam(teamRepo.findById(employeeDTO.getTeamId()).orElse(null));
        }

        // Save the employee entity
        employeeRepo.save(employee);
        return VarList.RSP_SUCCESS;
    }



    public String updateEmployee(EmployeeDTO employeeDTO){
        if(employeeRepo.existsById(employeeDTO.getEmpId())){
            employeeRepo.save(modelMapper.map(employeeDTO,Employee.class));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public List<EmployeeDTO> getAllEmployee(){
        List<Employee> employeeList = employeeRepo.findAll();
        return modelMapper.map(employeeList,new TypeToken<ArrayList<EmployeeDTO>>(){}.getType());
    }
    public EmployeeDTO searchEmployee(int empId){
        if (employeeRepo.existsById(empId)){
            Employee employee =employeeRepo.findById(empId).orElse(null);
            return modelMapper.map(employee,EmployeeDTO.class);
        }else {
            return null;
        }
    }
    public String deleteEmployee(int empId){
        if (employeeRepo.existsById(empId)){
            employeeRepo.deleteById(empId);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }



}
