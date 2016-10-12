package me.itache.service;

import me.itache.entity.Note;
import me.itache.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class NoteService {
    @Autowired
    private NoteRepository taskRepository;

    public Set<Note> getAll() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return taskRepository.getByUsername(user.getUsername());
    }

    public Note add(Note task){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        task.setUsername(user.getUsername());
        return taskRepository.saveAndFlush(task);
    }

    public void delete(Long id){
        taskRepository.delete(id);
    }

    public Note update(Note task){
        if(taskRepository.getOne(task.getId()) == null){
            throw new IllegalArgumentException("Task "+task+" not exists");
        }
        return taskRepository.saveAndFlush(task);
    }

    public Note get(Long id) {
        return taskRepository.findOne(id);
    }
}
