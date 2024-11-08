package com.example.imployeeMS.Controller;

import com.example.imployeeMS.Dto.DepartmentDTO;
import com.example.imployeeMS.Dto.EmployeeDTO;
import com.example.imployeeMS.Dto.ResponseDTO;
import com.example.imployeeMS.Service.DepartmentService;
import com.example.imployeeMS.Util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("api/v1/departmentcontroll")
public class DepartmentController {
    @Autowired
    public DepartmentService departmentService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/saveDepartment")
    public ResponseEntity saveDepartment(@RequestBody DepartmentDTO departmentDTO){
        try {
            String res = departmentService.saveDepartment(departmentDTO);

            if (res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(String.valueOf(departmentDTO));
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else if (res.equals("06")){
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage(("Department Registered"));
                responseDTO.setContent(String.valueOf(departmentDTO));
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(String.valueOf(departmentDTO));
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateDepartment")
    public ResponseEntity updateDepartment (@RequestBody DepartmentDTO departmentDTO){
        try {
            String res = departmentService.updateDepartment(departmentDTO);

            if (res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(String.valueOf(departmentDTO));
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else if (res.equals("06")){
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage(("Department isnt registered"));
                responseDTO.setContent(String.valueOf(departmentDTO));
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(String.valueOf(departmentDTO));
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getAllDepartment")
    public ResponseEntity getAllDepartment(){
        try {
            List<DepartmentDTO> departmentDTOList = departmentService.getAllDepartment();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage(("Success"));
            responseDTO.setContent(String.valueOf(departmentDTOList));
            return new ResponseEntity (responseDTO,HttpStatus.ACCEPTED);

        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/searchDepartment/{departmentId}")
    public ResponseEntity searchDepartment(@PathVariable long departmentId){
        try {
            DepartmentDTO departmentDTO = departmentService.searchDepartment(departmentId);
            if (departmentDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("success");
                responseDTO.setContent(String.valueOf(departmentDTO));
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Department available for this departmentId");
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

    @DeleteMapping(value = "/deleteDepartment/{departmentId}")
    public ResponseEntity deleteDepartment(@PathVariable long departmentId){
        try {
            String res = departmentService.deleteDepartment(departmentId);
            if (res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Department Available For this departmentId");
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
