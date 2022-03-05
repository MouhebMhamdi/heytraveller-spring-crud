package com.voyageAffaires.Controller;
import com.voyageAffaires.Repositories.UserRepository;
import com.voyageAffaires.Services.EmailService;
import com.voyageAffaires.Services.ReclamationService;
import com.voyageAffaires.entities.Reclamation;
import com.voyageAffaires.entities.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.MessagingException;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/reclamation")
public class ReclamationController {
    @Autowired
    private ReclamationService reclamationService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/all")
    public List<Reclamation> getAllReclamations(){
        return reclamationService.getAllReclamations();
    }

    @GetMapping("/{id}")
    public Reclamation getReclamationById(@PathVariable Long id){
        return reclamationService.getReclamationById(id);
    }

    @GetMapping("/user/{idUser}")
    public List<Reclamation> getReclamationByUser(@PathVariable Long idUser){
        return reclamationService.getReclamationByUser(idUser);
    }

    @GetMapping("/user/nbrReclamation/{idUser}")
    public int nbrReclamationByUser(@PathVariable Long idUser){
        return reclamationService.nbrReclamationByUser(idUser);
    }
    @PostMapping( "/user/addReclamation/{idUser}")
    @ApiOperation(value = "Add a list of claims and assign it to a user through the id")
    public List<Reclamation> addReclamationAndAffecterToUser(@RequestBody List<Reclamation> reclamations, @PathVariable Long idUser/* @RequestPart("file") MultipartFile image*/) {
        User us=userRepository.findById(idUser).get();
        emailService.send(us.getEmail(),"Hey Traveller reclamations","GoodMorning, <b>"+us.getUsername()+"</b><br /> " +
                "All your reclamations has been registred successfuly, thanks !! <br /> cordially");
        return reclamationService.addReclamationAndAffecterToUser(reclamations,idUser);
    }

    @RequestMapping(value = "/addImgRec/{idRec}",method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Reclamation addImgReclamation(@RequestPart("file") MultipartFile image,@PathVariable Long idRec)throws IOException{
        return reclamationService.addImgReclamation(image,idRec);
    }



    @RequestMapping(value = "/addImgRecList",method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Reclamation> addImgToReclamationsList(@RequestPart("file") MultipartFile image,@RequestParam List<Long> idRec)throws IOException{
        return reclamationService.addImgToReclamationsList(image,idRec);
    }
    @PutMapping("/update/{idReclamation}")
    public Reclamation updateReclamation(@RequestBody Reclamation reclamation,@PathVariable  Long idReclamation){
        return reclamationService.updateReclamation(reclamation,idReclamation);
    }

    @DeleteMapping("/delete")
    public String deleteAllReclamations(){
        reclamationService.deleteAllReclamations();
        return "All deleted !!";
    }

    @DeleteMapping("/delete/{id}")
    public List<Reclamation> deleteReclamationById(@PathVariable Long id){
        reclamationService.deleteReclamationByid(id);
        return this.getAllReclamations();
    }

    @DeleteMapping("/delete/user/{id}")
    public List<Reclamation> deleteReclamationByUser(@PathVariable Long id){
        reclamationService.deleteReclamationByUser(id);
        return this.getAllReclamations();
    }

    @GetMapping("/affectUser/{idRec}/{idUser}")
    @ApiOperation(value = "Assign User to a reclamation by id reclamation and id user")
    public List<Reclamation> affecterUserToReclamation(@PathVariable Long idRec,@PathVariable Long idUser){
        return reclamationService.affecterUserToReclamation(idRec,idUser);
    }


}
