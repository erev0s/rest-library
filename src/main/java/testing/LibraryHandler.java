package testing;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryHandler {
    private static Map<Integer, Book> bookLibrary = new HashMap<>(); //to store ids and books
    private static int counter = 0;


    // method to add a book to the library
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<Object> addBook(@RequestBody Book tmpBook) {
        Book toBeAdded = new Book();
        int tmpId = counter++;
        toBeAdded.setId(tmpId);
        toBeAdded.setAuthor(tmpBook.getAuthor());
        toBeAdded.setTitle(tmpBook.getTitle());
        toBeAdded.setPages(tmpBook.getPages());
        bookLibrary.put(tmpId, toBeAdded);
        return new ResponseEntity<>("Book has been added successfully", HttpStatus.CREATED);
    }

    //method to retrieve all books
    @RequestMapping(value = "/book")
    public ResponseEntity<Object> allBooks() {
        return new ResponseEntity<>(bookLibrary.values(), HttpStatus.OK);
    }

    //method to retrieve information about a book by id
    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> bookDetails(@PathVariable("id") int id){
        return new ResponseEntity<>(bookLibrary.get(id), HttpStatus.OK);
    }

    //method to delete a book by providing the id
    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> bookDelete(@PathVariable("id") int id) {
        bookLibrary.remove(id);
        return new ResponseEntity<>("Book is deleted successsfully", HttpStatus.OK);
    }

    /*
    Difference with the above delete method is that we made the update to check
    first if the id exists which is not necassary as the hashmap removes only if it exists
     */
    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateBook(@PathVariable("id") int id, @RequestBody Book tmpBook) {
        if (bookLibrary.containsKey(id)) {
            bookLibrary.remove(id);
            tmpBook.setId(id);
            bookLibrary.put(id, tmpBook);
            return new ResponseEntity<>("Book updated successsfully", HttpStatus.OK);
        }else return new ResponseEntity<>("Book was not found", HttpStatus.OK);

    }

}