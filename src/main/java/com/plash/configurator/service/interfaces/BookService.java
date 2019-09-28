package com.plash.configurator.service.interfaces;


import com.plash.configurator.pojo.JsonBook;
import com.plash.configurator.pojo.ResponseCodeJson;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    public ResponseCodeJson create(JsonBook jsonBook);
    public ResponseCodeJson getBookById(Long id);
    public ResponseCodeJson getBookByTitle(String title);
    public ResponseCodeJson getAllBooks();
    public ResponseCodeJson updateVersionById(String version, Long id);
    public ResponseCodeJson deleteBookById(Long id);

}
