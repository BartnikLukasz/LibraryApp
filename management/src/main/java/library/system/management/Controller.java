package library.system.management;

import library.system.management.models.Book;
import library.system.management.models.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

//controller responsible for handling HTTP methods
@RestController
@RequestMapping(path="/library")
public class Controller {

    @Autowired
    BookRepository bookRepository;

    //function adding book to database
    @PostMapping(path="/add")
    public @ResponseBody void addBook(@RequestParam String title, @RequestParam String author, @RequestParam String genre){

        Book tempBook = new Book();
        tempBook.setTitle(title);
        tempBook.setAuthor(author);
        tempBook.setGenre(genre);

        bookRepository.save(tempBook);
    }

    //function getting all books from database
    @GetMapping(path="/getAll")
    public @ResponseBody Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    //function getting specific book from database
    @GetMapping(path="/getBook/{bookId}")
    public @ResponseBody Book getBook(@PathVariable(name = "bookId") String bookId){

        Optional<Book> tempBook = bookRepository.findById(Integer.parseInt(bookId));
        return tempBook.orElse(null);

    }

    //function updating book in database, parameters are optional, so one or more parameters can be updated
    @PutMapping(path="/edit/{bookId}")
    public @ResponseBody void editBookById(@PathVariable(name = "bookId") String bookId,
                                           @PathVariable(name = "title", required = false)  String title,
                                           @PathVariable(name = "author", required = false) String author,
                                           @PathVariable(name = "genre", required = false) String genre){

        Book tempBook = bookRepository.findById(Integer.parseInt(bookId)).get();

        if(title != null){
            tempBook.setTitle(title);
        }
        if(author != null){
            tempBook.setAuthor(author);
        }
        if(genre != null){
            tempBook.setGenre(genre);
        }

        bookRepository.save(tempBook);

    }

    //function deleting book from database
    @DeleteMapping(path="/delete/{deleteId}")
    public @ResponseBody void deleteBookById(@PathVariable(name = "bookId") String bookId){
        bookRepository.deleteById(Integer.parseInt(bookId));
    }

}
