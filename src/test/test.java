package test;

import main.classes.Book;
import main.classes.Library;
import main.classes.Student;
import org.junit.jupiter.api.Test;

import main.*;

import static org.junit.jupiter.api.Assertions.*;

class LendTest {
    @Test
    void lendFailUnregisteredStudent() {
        Library library = new Library();

        Book book1 = new Book("Book-1", "Author-1", 10);

        Student student1 = new Student("Not-registered-student", 13);

        library.addBook(book1);
        library.addStudent(student1);


        boolean status = library.lendBook(book1, student1);
        
    }
}