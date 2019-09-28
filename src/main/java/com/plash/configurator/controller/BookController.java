package com.plash.configurator.controller;


import com.plash.configurator.exception.ExceptionThrower;
import com.plash.configurator.pojo.JsonBook;
import com.plash.configurator.pojo.ResponseCodeJson;
import com.plash.configurator.service.interfaces.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/books/")
public class BookController {


    @Autowired
    private BookService bookService;


    final static Logger logger = LoggerFactory.getLogger(BookController.class);


    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<ResponseCodeJson> create(@RequestBody JsonBook jsonBook) throws Exception {
        logger.info("Request Json: " + jsonBook);
        Assert.notNull(jsonBook.getTitle(), " book Title Should not be empty");
        Assert.notNull(jsonBook.getAuthor(), " book author should not be empty");
        Assert.notNull(jsonBook.getVersion(), "book version should not be empty");
        ExceptionThrower exceptionThrower = new ExceptionThrower();
        ResponseCodeJson result = bookService.create(jsonBook);
        if (result.getErrorcode() != 200) {
            logger.info("create json is failed");
            exceptionThrower.throwCustomException(result.getErrorcode(), result.getMessage());
        }
        logger.info("inside create json :" + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "bookid/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseCodeJson> getBookById(@PathVariable("id") Long id) throws Exception {
        logger.info("Inside Get book by id :" + id);
        Assert.notNull(id, "id should not empty");
        ExceptionThrower exceptionThrower = new ExceptionThrower();
        ResponseCodeJson result = bookService.getBookById(id);
        if (result.getErrorcode() != 200) {
            exceptionThrower.throwCustomException(result.getErrorcode(), result.getMessage());
        }
        logger.info("data:");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "booktitle/{title}", method = RequestMethod.GET)
    public ResponseEntity<ResponseCodeJson> getBookByTitle(@PathVariable("title") String title) throws Exception {
        logger.info("Inside Get book by Title :" + title);
        Assert.notNull(title, "Title should not empty");
        ExceptionThrower exceptionThrower = new ExceptionThrower();
        ResponseCodeJson responseCodeJson = bookService.getBookByTitle(title);
        if (responseCodeJson.getErrorcode() != 200) {
            exceptionThrower.throwCustomException(responseCodeJson.getErrorcode(), responseCodeJson.getMessage());
        }
        logger.info("data ");
        return new ResponseEntity<>(responseCodeJson, HttpStatus.OK);
    }

    @RequestMapping(value = "getallbooks", method = RequestMethod.GET)
    public ResponseEntity<ResponseCodeJson> getAllBooks() throws Exception {
        logger.info("Inside get all books");
        ExceptionThrower exceptionThrower = new ExceptionThrower();
        ResponseCodeJson responseCodeJson = bookService.getAllBooks();
        if (responseCodeJson.getErrorcode() != 200) {
            exceptionThrower.throwCustomException(responseCodeJson.getErrorcode(), responseCodeJson.getMessage());
        }
        logger.info("data ");
        return new ResponseEntity<>(responseCodeJson, HttpStatus.OK);
    }

    @RequestMapping(value = "{bookId}/{updateVersion}", method = RequestMethod.POST)
    public ResponseEntity<ResponseCodeJson> updateVersionById(@PathVariable("updateVersion") String updateVersion, @PathVariable("bookId") Long bookId) throws Exception {
        logger.info("Inside update:");
        Assert.notNull(updateVersion, "updateversion should not empty");
        Assert.notNull(bookId, "bookid should not empty");
        ExceptionThrower exceptionThrower = new ExceptionThrower();
        ResponseCodeJson responseCodeJson = bookService.updateVersionById(updateVersion, bookId);
        if (responseCodeJson.getErrorcode() != 200) {
            exceptionThrower.throwCustomException(responseCodeJson.getErrorcode(), responseCodeJson.getMessage());
        }
        logger.info("data ");
        return new ResponseEntity<>(responseCodeJson, HttpStatus.OK);
    }

    @RequestMapping(value = "{id|", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseCodeJson> deleteBookById(@PathVariable("id") Long id) throws Exception {
        logger.info("Inside Get book by id :" + id);
        Assert.notNull(id, "id should not empty");
        ExceptionThrower exceptionThrower = new ExceptionThrower();
        ResponseCodeJson responseCodeJson = bookService.deleteBookById(id);
        if (responseCodeJson.getErrorcode() != 200) {
            exceptionThrower.throwCustomException(responseCodeJson.getErrorcode(), responseCodeJson.getMessage());
        }
        logger.info("data ");
        return new ResponseEntity<>(responseCodeJson, HttpStatus.OK);
    }
}
