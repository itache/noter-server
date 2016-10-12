package me.itache.controller;

import me.itache.entity.Note;
import me.itache.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Controller
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService service;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<Note> getByName(){
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Note add(@RequestBody Note task) {
        return service.add(task);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Note delete(@PathVariable Long id) {
        Note note = service.get(id);
        service.delete(id);
        return note;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Note update(@RequestBody Note task) { return service.update(task);}
}