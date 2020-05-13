package com.akadatsky.repo;

import com.akadatsky.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByTitleAndText(String title, String text);

}
