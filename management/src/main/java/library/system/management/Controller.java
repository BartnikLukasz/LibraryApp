package library.system.management;

import library.system.management.models.Book;
import library.system.management.models.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping(path="/library")
public class Controller {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(path="/add")
    public @ResponseBody void addBook(@RequestParam String title, @RequestParam String author, @RequestParam String genre){
        Book tempBook = new Book();
        tempBook.setTitle(title);
        tempBook.setAuthor(author);
        tempBook.setGenre(genre);

        bookRepository.save(tempBook);
    }

    @GetMapping(path="/getAll")
    public @ResponseBody Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping(path="/getBook/{bookId}")
    public @ResponseBody Book getBook(@PathVariable("bookId") String bookId){
        Optional<Book> tempBook = bookRepository.findById(Integer.parseInt(bookId));
        return tempBook.orElse(null);
    }

}
