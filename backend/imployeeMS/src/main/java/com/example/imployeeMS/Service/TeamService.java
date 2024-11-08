package com.example.imployeeMS.Service;

import com.example.imployeeMS.Dto.EmployeeDTO;
import com.example.imployeeMS.Dto.TeamDTO;
import com.example.imployeeMS.Entity.Employee;
import com.example.imployeeMS.Entity.Team;
import com.example.imployeeMS.Repo.TeamRepo;
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
public class TeamService {

        @Autowired
        private TeamRepo teamRepo;

        @Autowired
        private ModelMapper modelMapper;

        public String saveTeam(TeamDTO teamDTO) {
            if (teamRepo.existsById(teamDTO.getTeamId())) {
                return VarList.RSP_DUPLICATED;
            } else {
                teamRepo.save(modelMapper.map(teamDTO, Team.class));
                return VarList.RSP_SUCCESS;
            }
        }

        public List<TeamDTO> getAllTeams() {
            List<Team> teamList = teamRepo.findAll();
            return modelMapper.map(teamList,new TypeToken<ArrayList<TeamDTO>>(){}.getType());
        }

        public TeamDTO searchTeam(long teamId) {
            if (teamRepo.existsById(teamId)){
                Team team =teamRepo.findById(teamId).orElse(null);
                return modelMapper.map(team,TeamDTO.class);
            }else {
                return null;
            }
        }

        public String deleteTeam(long teamId) {
            if (teamRepo.existsById(teamId)){
                teamRepo.deleteById(teamId);
                return VarList.RSP_SUCCESS;
            }else {
                return VarList.RSP_NO_DATA_FOUND;
            }
        }
    }



