package com.voyageAffaires.Controller;


import com.voyageAffaires.Services.ReponseReclamationService;
import com.voyageAffaires.entities.ReponseReclamation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ReponseReclamation")
public class ReponseReclamationController {
    @Autowired
    private ReponseReclamationService reclamationService;
    /*
    ReponseReclamation getReponseReclamationById(Long id);
    List<ReponseReclamation> getAllReponseReclamations();
    List<ReponseReclamation> addReponseReclamationandAffectToReclamation(List<ReponseReclamation> reponseReclamations,Long idReclamation );
    ReponseReclamation updateReponseReclamation(ReponseReclamation reclamation,Long respRecId);
    void deleteReclamationById(Long idRespReclamation);
    void deleteAllReclamation();
     */


    @GetMapping("/all")
    public List<ReponseReclamation> getAllReponseReclamations(){
       return reclamationService.getAllReponseReclamations();
    }

    @GetMapping("/{id}")
    public ReponseReclamation getReponseReclamationById(@PathVariable Long id){
        return reclamationService.getReponseReclamationById(id);
    }

    @PostMapping("/add/{idReclamation}")
    @ApiOperation(value = "add Reponse Reclamation and Affect To Reclamation")
    public List<ReponseReclamation> addReponseReclamationandAffectToReclamation(@PathVariable Long idReclamation,@RequestBody List<ReponseReclamation> reponseReclamations){
        return reclamationService.addReponseReclamationandAffectToReclamation(reponseReclamations,idReclamation);
    }

    @PutMapping("/update/{id}")
    public ReponseReclamation updateReponseReclamation(@PathVariable Long id,@RequestBody ReponseReclamation reclamation){
        return reclamationService.updateReponseReclamation(reclamation,id);
    }

    @DeleteMapping("/{id}")
    public List<ReponseReclamation> deleteReclamationById(@PathVariable Long id){
        reclamationService.deleteReclamationById(id);
        return this.getAllReponseReclamations();
    }
    @DeleteMapping("/all")
    public String deleteReclamations(){
        reclamationService.deleteAllReclamation();
        return "all reponse reclamations deleted !!";
    }
}
