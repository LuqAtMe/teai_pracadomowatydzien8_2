package home.application.teai_pracadomowatydzien8_2.repo;

import home.application.teai_pracadomowatydzien8_2.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {


//    @Query(value = "UPDATE notes SET notes.header = :header, notes.text = :text WHERE notes.id = :id", nativeQuery = true)
//    void updateNote(@Param("header") String header, @Param("text") String text, @Param("id") Long id);


}
