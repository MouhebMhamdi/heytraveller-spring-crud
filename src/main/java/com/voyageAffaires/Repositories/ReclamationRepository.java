package com.voyageAffaires.Repositories;

import com.voyageAffaires.entities.Reclamation;
import com.voyageAffaires.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {

    @Query("SELECT R FROM Reclamation R WHERE R.user.id=:id")
    List<Reclamation> getRecBYId(Long id);


    Reclamation deleteByUser(User user);

    @Query("SELECT count(R) FROM  Reclamation R,User U WHERE  U.id=R.user.id and U.id=:id")
    int nbrReclamationByUser(Long id);
}
