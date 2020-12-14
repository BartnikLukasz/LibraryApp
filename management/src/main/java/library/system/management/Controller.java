package library.system.management;

import library.system.management.models.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/library")
public class Controller {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    RestTemplate restTemplate;



}
