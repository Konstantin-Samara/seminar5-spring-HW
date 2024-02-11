package com.example.seminar5spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
@Service
public class CommandService {
    @Autowired
    private CommandRepository commandRepository;

    public Command createCommand(Command note) {
        return commandRepository.save(note);
    }

    public List<String> getAllCommands() {
        return commandRepository.findAll().stream()
                .sorted(new Comparator<Command>() {
                    @Override
                    public int compare(Command o1, Command o2) {
                        return o1.getId().compareTo(o2.getId());
                    }
                })
                .map(Command::toString).toList();
    }

    public Command getCommandById(Long id) {
        return commandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Command not found"));
    }

    public Command changeStatus(Long id) {
        Command existingCommand = getCommandById(id);
        if (existingCommand.getStatus().equals(Command.Status.Running)){
            existingCommand.setStatus(Command.Status.Completed);
            existingCommand.setRunningDate("no Date");
        }
        else {
            existingCommand.setStatus(Command.Status.Running);
            existingCommand.setRunningDate(
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        return commandRepository.save(existingCommand);
    }

    public void deleteCommand(Long id) {
        getCommandById(id);
        commandRepository.deleteById(id);
    }

    public List<String> getCommandByStatus() {
        return commandRepository.findAll().stream()
                .filter(it->it.getStatus().equals(Command.Status.Running))
                .sorted(Comparator.comparing(Command::getId))
                .map(Command::toString).toList();
    }
}
