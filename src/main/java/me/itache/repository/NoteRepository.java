package me.itache.repository;

import me.itache.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
@Transactional
public interface NoteRepository extends JpaRepository<Note, Long> {
    Set<Note> getByUsername(String username);
}
