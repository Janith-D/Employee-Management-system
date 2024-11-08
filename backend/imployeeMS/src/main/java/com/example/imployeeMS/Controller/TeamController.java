package com.example.imployeeMS.Controller;

import com.example.imployeeMS.Dto.EmployeeDTO;
import com.example.imployeeMS.Dto.ResponseDTO;
import com.example.imployeeMS.Dto.TeamDTO;
import com.example.imployeeMS.Service.EmployeeService;
import com.example.imployeeMS.Service.TeamService;
import com.example.imployeeMS.Util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("api/v1/teamControl")
public class TeamController {
    @Autowired
    public TeamService teamService;

    @Autowired
    private ResponseDTO responseDTO;


    @PostMapping(value = "/saveTeam")
    public ResponseEntity saveTeam(@RequestBody TeamDTO teamDTO) {
        try {
            String res = teamService.saveTeam(teamDTO);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(String.valueOf(teamDTO));
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Team Registered");
                responseDTO.setContent(String.valueOf(teamDTO));
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(String.valueOf(teamDTO));
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }

        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getAllTeam")
    public ResponseEntity getAllTeam(){
        try {
            List<TeamDTO> teamDTOList = teamService.getAllTeams();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage(("Success"));
            responseDTO.setContent(String.valueOf(teamDTOList));
            return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);

        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/searchTeam/{teamId}")
    public ResponseEntity searchTeam(@PathVariable long teamId){
        try {
            TeamDTO teamDTO = teamService.searchTeam(teamId);
            if (teamDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("success");
                responseDTO.setContent(String.valueOf(teamDTO));
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Teams at available ");
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
    @DeleteMapping(value = "/deleteTeam/{teamId}")
    public ResponseEntity deleteTeam(@PathVariable long teamId){
        try {
            String res = teamService.deleteTeam(teamId);
            if (res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Team at Available ");
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
