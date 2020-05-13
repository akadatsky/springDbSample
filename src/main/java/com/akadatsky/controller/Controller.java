package com.akadatsky.controller;

import com.akadatsky.model.Message;
import com.akadatsky.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private MessageRepo messageRepo;

    @PostMapping
    public String addMessage(@RequestParam String title, @RequestParam String text, Map<String, Object> model) {
        Message message = new Message(title, text);
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @GetMapping
    public String showAll(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @GetMapping(value = "/exact")
    public String getMessage(@RequestParam(value = "title") String title,
                             @RequestParam(value = "text") String text,
                             Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findByTitleAndText(title, text);
        model.put("messages", messages);
        return "messages";
    }

    @GetMapping(value = "/{id}")
    public String getMessageById(@PathVariable("id") long id,
                             Map<String, Object> model) {
        Optional<Message> messages = messageRepo.findById(id);
        model.put("messages", messages.get());
        return "messages";
    }


}
