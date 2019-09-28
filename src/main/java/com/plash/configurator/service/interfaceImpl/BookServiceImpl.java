package com.plash.configurator.service.interfaceImpl;

import com.plash.configurator.model.Book;
import com.plash.configurator.model.Language;
import com.plash.configurator.pojo.JsonBook;
import com.plash.configurator.pojo.ResponseCodeJson;
import com.plash.configurator.repository.BookRepository;
import com.plash.configurator.repository.LanguageRepository;
import com.plash.configurator.service.interfaces.BookService;
import com.plash.configurator.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public ResponseCodeJson create(JsonBook jsonBook) {
        ResponseCodeJson responseCodeJson = new ResponseCodeJson();
        String title = Utils.nonNull(jsonBook.getTitle());
        String author = Utils.nonNull(jsonBook.getAuthor());
        String version = Utils.nonNull(jsonBook.getVersion());
        String lName = Utils.nonNull(jsonBook.getLName());

        Book book = bookRepository.getBookByTitle(jsonBook.getTitle());
        Language language;

        if (book != null) {
            language = languageRepository.getLanguageByIdAndLang(jsonBook.getLid(), lName);
            if (language.getLanguageName() != null) {
                responseCodeJson.setErrorcode(400);
                responseCodeJson.setMessage("Already book is present with this language");
                return responseCodeJson;
            }
            language.setLanguageName(lName);
            language.setBid(jsonBook.getBid());
            languageRepository.save(language);
            book.setLName(lName);
        }
        book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGetUniqueId(Utils.getUniqueID());
        book.setVersion(version);
        book.setDate(new Date());
        book.setLName(lName);
        try {
            bookRepository.save(book);
            responseCodeJson.setErrorcode(200);
            responseCodeJson.setMessage("Success");
            return responseCodeJson;
        } catch (Exception e) {
            responseCodeJson.setErrorcode(400);
            responseCodeJson.setMessage(" unable to save book data");
            return responseCodeJson;
        }
    }

    public ResponseCodeJson getBookById(Long id) {
        ResponseCodeJson responseCodeJson = new ResponseCodeJson();
        Book book = bookRepository.getBookById(id);
        if (book == null) {
            book.setIsActive(1);
            responseCodeJson.setErrorcode(400);
            responseCodeJson.setMessage("No book find with that id");
            return responseCodeJson;
        }
        Language language = languageRepository.getLanguageById(book.getLid());
        if (language == null) {
            language.setBid(book.getBid());
            language.setLanguageName(book.getLName());
            languageRepository.save(language);
        }
        JsonBook jsonBook = new JsonBook();
        jsonBook.setTitle(book.getTitle());
        jsonBook.setAuthor(book.getAuthor());
        jsonBook.setBid(book.getBid());
        jsonBook.setVersion(book.getVersion());
        jsonBook.setLName(book.getLName());
        jsonBook.setLid(book.getLid());
        jsonBook.setDate(book.getDate());
        responseCodeJson.setMessage("Success");
        responseCodeJson.setErrorcode(200);
        return responseCodeJson;
    }

    public ResponseCodeJson getBookByTitle(String title) {
        ResponseCodeJson responseCodeJson = new ResponseCodeJson();
        Book book = bookRepository.getBookByTitle(title);
        if (book == null) {
            book.setIsActive(1);
            responseCodeJson.setErrorcode(400);
            responseCodeJson.setMessage("No book find with that title");
            return responseCodeJson;
        }
        Language language = languageRepository.getLanguageById(book.getLid());
        if (language == null) {
            language.setBid(book.getBid());
            language.setLanguageName(book.getLName());
            languageRepository.save(language);
        }
        JsonBook jsonBook = new JsonBook();
        jsonBook.setBid(book.getBid());
        jsonBook.setAuthor(book.getAuthor());
        jsonBook.setTitle(book.getTitle());
        jsonBook.setVersion(book.getVersion());
        jsonBook.setDate(book.getDate());
        jsonBook.setLid(book.getLid());
        jsonBook.setLName(book.getLName());
        responseCodeJson.setErrorcode(200);
        responseCodeJson.setMessage("success");
        return responseCodeJson;
    }

    public ResponseCodeJson getAllBooks() {
        ResponseCodeJson responseCodeJson = new ResponseCodeJson();
        List<Book> books = bookRepository.findAll();
        if (books == null) {
            responseCodeJson.setErrorcode(400);
            responseCodeJson.setMessage("No books found");
            return responseCodeJson;
        }
        for (Book book : books) {
            Long lid = book.getLid();
            Language language = languageRepository.getLanguageById(lid);
            Language language1;
            if (language == null) {
                language1 = new Language();
                language1.setBid(book.getBid());
                language1.setLanguageName(book.getLName());
                languageRepository.save(language1);
            }
        }
        List<JsonBook> jsonBooks = new ArrayList<JsonBook>();
        JsonBook jsonBook;
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            jsonBook = new JsonBook();
            jsonBook.setLid(book.getLid());
            jsonBook.setLName(book.getLName());
            jsonBook.setDate(book.getDate());
            jsonBook.setAuthor(book.getAuthor());
            jsonBook.setTitle(book.getTitle());
            jsonBook.setVersion(book.getVersion());
            jsonBooks.add(jsonBook);
        }
        responseCodeJson.setErrorcode(200);
        responseCodeJson.setMessage("success");
        return responseCodeJson;
    }

    public ResponseCodeJson updateVersionById(String version, Long id) {
        ResponseCodeJson responseCodeJson = new ResponseCodeJson();
        String versions = Utils.nonNull(version);
        Book book = bookRepository.getBookById(id);

        if (book == null) {
            responseCodeJson.setErrorcode(400);
            responseCodeJson.setMessage("No books found");
            return responseCodeJson;
        }
        Language language = languageRepository.getLanguageById(book.getLid());
        if (language == null) {
            language.setBid(book.getBid());
            language.setLanguageName(book.getLName());
            languageRepository.save(language);
        }
        book.setVersion(versions);
        bookRepository.save(book);
        JsonBook jsonBook = new JsonBook();
        jsonBook.setLid(book.getLid());
        jsonBook.setLName(book.getLName());
        jsonBook.setDate(book.getDate());
        jsonBook.setAuthor(book.getAuthor());
        jsonBook.setTitle(book.getTitle());
        jsonBook.setVersion(book.getVersion());
        responseCodeJson.setErrorcode(200);
        responseCodeJson.setMessage("success");
        return responseCodeJson;
    }

    public ResponseCodeJson deleteBookById(Long id) {
        ResponseCodeJson responseCodeJson = new ResponseCodeJson();
        Book book = bookRepository.getBookById(id);
        if (book == null) {
            responseCodeJson.setErrorcode(400);
            responseCodeJson.setMessage("No books found");
            return responseCodeJson;
        }
        if (book.getIsActive() == 1) {
            responseCodeJson.setErrorcode(200);
            responseCodeJson.setMessage("success");
            return responseCodeJson;
        }
        book.setIsActive(1);
        responseCodeJson.setErrorcode(200);
        responseCodeJson.setMessage("success");
        return responseCodeJson;
    }
}
