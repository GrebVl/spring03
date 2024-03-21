package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.controllers.BookRequest;
import ru.gb.springbootlesson3.entity.Book;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

    private final CommonService commonService;

    public  Book createBook(BookRequest request){
        if (commonService.getBookRepository().findByName(request.getBookName()) != null){
            log.info("Книга с name существует " + request.getBookName());
            throw new NoSuchElementException("Книга с name существует " + request.getBookName());
        }

        Book book = new Book(request.getBookName());
        commonService.getBookRepository().createBook(book);
        return book;
    }

    public List<Book> getAllBooks(){
        return commonService.getBookRepository().getAllBooks();
    }

    public Book getIdBook(Long id){
        if (commonService.getBookRepository().findById(id) == null){
            log.info("Книга не найдена с id " + id);
            throw new NoSuchElementException("Книга не найдена с id " + id);
        }

        return commonService.getBookRepository().findById(id);
    }

    public void deleteIdBook(Long id){
        if (commonService.getBookRepository().findById(id) == null){
            log.info("Книга не найдена с id " + id);
            throw new NoSuchElementException("Книга не найдена с id " + id);
        }
        commonService.getBookRepository().deleteBook(id);
    }


}
