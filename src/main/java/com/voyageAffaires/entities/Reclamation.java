package com.voyageAffaires.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reclamation")
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rec", nullable = false)
    private Long id;
    private String title;
    private String message;
    private String fileName;
    private String fileType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReclamation;
    @Lob
    private byte[] data;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "reclamation",cascade = CascadeType.ALL)
    private List<ReponseReclamation>  reponseReclamations;




}
