package com.example.imployeeMS.Service;


import com.example.imployeeMS.Dto.DepartmentDTO;
import com.example.imployeeMS.Dto.EmployeeDTO;
import com.example.imployeeMS.Entity.Department;
import com.example.imployeeMS.Entity.Employee;
import com.example.imployeeMS.Repo.DepartmentRepo;
import com.example.imployeeMS.Util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private ModelMapper modelMapper;

    public String saveDepartment(DepartmentDTO departmentDTO){
        if (departmentRepo.existsById(departmentDTO.getDepartmentId())){
            return VarList.RSP_DUPLICATED;
        }else {
            departmentRepo.save(modelMapper.map(departmentDTO, Department.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateDepartment(DepartmentDTO departmentDTO){
        if (departmentRepo.existsById(departmentDTO.getDepartmentId())){
            departmentRepo.save(modelMapper.map(departmentDTO,Department.class));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public  List<DepartmentDTO> getAllDepartment(){
        List<Department> departmentList = departmentRepo.findAll();
        return modelMapper.map(departmentList,new TypeToken<ArrayList<EmployeeDTO>>(){}.getType());
    }
    public DepartmentDTO searchDepartment(long departmentId){
        if (departmentRepo.existsById(departmentId)){
            Department department =departmentRepo.findById(departmentId).orElse(null);
            return modelMapper.map(department,DepartmentDTO.class);
        }else {
            return null;
        }
    }
    public String deleteDepartment(long departmentId){
        if (departmentRepo.existsById(departmentId)){
            departmentRepo.deleteById(departmentId);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

}
