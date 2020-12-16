package library.system.management.models;

import org.springframework.data.repository.CrudRepository;

//interface allowing easy use and creation of function for accessing database
public interface BookRepository extends CrudRepository<Book, Integer> {

}
