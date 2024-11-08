package com.example.imployeeMS.Controller;

import com.example.imployeeMS.Dto.EmployeeDTO;
import com.example.imployeeMS.Dto.ResponseDTO;
import com.example.imployeeMS.Entity.Department;
import com.example.imployeeMS.Entity.Employee;
import com.example.imployeeMS.Repo.DepartmentRepo;
import com.example.imployeeMS.Repo.EmployeeRepo;
import com.example.imployeeMS.Service.EmployeeService;
import com.example.imployeeMS.Util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("api/v1/empcontrol")
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;

    @Autowired
    private ResponseDTO responseDTO;



    @PostMapping(value = "/saveemployee")
    public ResponseEntity<ResponseDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO){

        try {
            String res = employeeService.saveEmployee(employeeDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(employeeDTO.toString());
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else if (res.equals("06")){
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Employee Registered");
                responseDTO.setContent(String.valueOf(employeeDTO));
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);

            }else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(String.valueOf(employeeDTO));
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);

            }

        }
        catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/updateemployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            String res = employeeService.updateEmployee(employeeDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage(("Success"));
                responseDTO.setContent(String.valueOf(employeeDTO));
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Not a Regiter Employee");
                responseDTO.setContent(String.valueOf(employeeDTO));
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }
            else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(String.valueOf(employeeDTO));
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }

        }
        catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }
    @GetMapping(value = "/getAllEmployees")
    public ResponseEntity getAllEmployees(){
        try {
            List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployee();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage(("Success"));
            responseDTO.setContent(String.valueOf(employeeDTOList));
            return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);

        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }
    @GetMapping(value = "/searchemployee/{empId}")
    public ResponseEntity searchEmployee(@PathVariable int empId){
        try {
            EmployeeDTO employeeDTO = employeeService.searchEmployee(empId);
            if (employeeDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("success");
                responseDTO.setContent(String.valueOf(employeeDTO));
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employees available for this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/deleteemployee/{empId}")
    public ResponseEntity deleteEmployee(@PathVariable int empId){
        try {
            String res = employeeService.deleteEmployee(empId);
            if (res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For this empId");
                responseDTO.setContent(null);
                return  new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    }


