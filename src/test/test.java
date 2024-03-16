package test;

import main.classes.Book;
import main.classes.Library;
import main.classes.Student;
import org.junit.jupiter.api.Test;

import main.*;

import static org.junit.jupiter.api.Assertions.*;

class LendTest {

    @Test
    void lendSucceed() {
        Library library = new Library();

        Book book = new Book("registered-book", "Author", 1);
        Student student = new Student("registered-student", 8);

        library.addBook(book);
        library.addStudent(student);

        assertTrue(library.lendBook(book, student));
    }

    @Test
    void unregisteredBook() {
        Library library = new Library();

        Book book = new Book("unregistered-book", "Author", 4);
        Student student = new Student("Not-registered-student", 2);

        library.addStudent(student);

        assertFalse(library.lendBook(book, student));
    }

    @Test
    void unregisteredStudent() {
        Library library = new Library();

        Book book = new Book("registered-book", "Author", 3);
        Student student = new Student("unregistered-student", 5);

        library.addBook(book);

        assertFalse(library.lendBook(book, student));
    }

    @Test
    void alreadyLent() {
        Library library = new Library();

        Book book = new Book("registered-book", "Author", 10);
        Student student = new Student("registered-student", 13);

        library.addBook(book);
        library.addBook(book);
        library.addStudent(student);
        library.lendBook(book, student);

        assertFalse(library.lendBook(book, student));
    }
}



class returnbook {
    @Test
    void studentnotregister(){
        Library library = new Library();
        Book book1 = new Book("Book-1", "Author-1", 10);
        Student student2 = new Student("Not-registered-student", 13);
        Student student1= new Student("registered-student", 13);
        library.addBook(book1);
        library.addStudent(student1);



        assertFalse(library.returnBook(book1, student2), "the student doesn't registered");
    }

    @Test
    void studentreturnbook(){
        Library library = new Library();
        Book book1 = new Book("Book-1", "Author-1", 10);
        Student student1= new Student("registered-student", 13);
        library.addBook(book1);
        library.addStudent(student1);
        library.lendBook(book1, student1);
        assertTrue(library.returnBook(book1, student1), "sucessfull to return book");
    }
    @Test
    void studentdoesnothavebook(){
        Library library = new Library();
        Book book1 = new Book("Book-1", "Author-1", 10);
        Book book2 = new Book("Book-2", "Author-2", 10);
        Student student1= new Student("registered-student", 13);
        library.addBook(book1);
        library.addBook(book2);
        library.addStudent(student1);
        library.lendBook(book2, student1);
        assertFalse(library.returnBook(book1, student1), "student doesn't have this book");
    }
    @Test
    void bookNotRegistered(){
        Library library = new Library();
        Book book1 = new Book("Book-1", "Author-1", 10);
        Book book2 = new Book("Book-2", "Author-2", 10);
        Student student1= new Student("registered-student", 13);
        library.addBook(book1);
        library.addStudent(student1);
        assertFalse(library.returnBook(book2, student1), "the book doesn't registered");
    }
}
