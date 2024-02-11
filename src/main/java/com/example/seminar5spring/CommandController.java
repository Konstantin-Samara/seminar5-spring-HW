package com.example.seminar5spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commands")
public class CommandController {
    private final CommandService service;

    @Autowired
    public CommandController(CommandService service1) {
        service = service1;
    }

    @GetMapping
    public List<String> getAllCommands() {
        return service.getAllCommands();
    }

    @GetMapping("/{id}")
    public String getCommandById(@PathVariable Long id) {
        return service.getCommandById(id).toString();
    }
    @GetMapping("/running")
    public List<String> getCmmandByStatus(){
        return service.getCommandByStatus();
    }

    @PostMapping
    public String createCommand(@RequestBody Command note) {
        return service.createCommand(note).toString();
    }

    @PutMapping("/{id}")
    public String changeStatus(@PathVariable Long id) {
        return service.changeStatus(id).toString();
    }

    @DeleteMapping("/{id}")
    public void deleteCommand(@PathVariable Long id) {
        service.deleteCommand(id);
    }


}
