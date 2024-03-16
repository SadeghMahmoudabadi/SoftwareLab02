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



class ReturnBookTest {
    @Test
    void studentNotRegistered() {
        Library library = new Library();
        Book book1 = new Book("Book-1", "Author-1", 10);
        Student student2 = new Student("Not-registered-student", 13);
        Student student1 = new Student("registered-student", 13);
        library.addBook(book1);
        library.addStudent(student1);


        assertFalse(library.returnBook(book1, student2), "the student doesn't registered");
    }

    @Test
    void studentReturnBook() {
        Library library = new Library();
        Book book1 = new Book("Book-1", "Author-1", 10);
        Student student1 = new Student("registered-student", 13);
        library.addBook(book1);
        library.addStudent(student1);
        library.lendBook(book1, student1);
        assertTrue(library.returnBook(book1, student1), "sucessfull to return book");
    }

    @Test
    void studentDoesBotHaveBook() {
        Library library = new Library();
        Book book1 = new Book("Book-1", "Author-1", 10);
        Book book2 = new Book("Book-2", "Author-2", 10);
        Student student1 = new Student("registered-student", 13);
        library.addBook(book1);
        library.addBook(book2);
        library.addStudent(student1);
        library.lendBook(book2, student1);
        assertFalse(library.returnBook(book1, student1), "student doesn't have this book");
    }

    @Test
    void bookNotRegistered() {
        Library library = new Library();
        Book book1 = new Book("Book-1", "Author-1", 10);
        Book book2 = new Book("Book-2", "Author-2", 10);
        Student student1 = new Student("registered-student", 13);
        library.addBook(book1);
        library.addStudent(student1);
        assertFalse(library.returnBook(book2, student1), "the book doesn't registered");
    }

    @Test
    void returnAgain() {
        Library library = new Library();
        Book book1 = new Book("Book-1", "Author-1", 10);

        Student student1 = new Student("registered-student", 13);
        library.addBook(book1);
        library.addStudent(student1);
        library.lendBook(book1, student1);
        library.returnBook(book1, student1);
        assertFalse(library.returnBook(book1, student1), "The student has returned the book twice");
    }
}


class BookSearchTest {

    @Test
    void searchByName() {
        Library library = new Library();

        Book book1 = new Book("Book-1", "Author-1", 10);
        Book book2 = new Book("Book-2", "Author-2", 11);
        Book book3 = new Book("Book-3", "Author-3", 12);
        Book book4 = new Book("Book-4", "Author-1", 13);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);

        var keys = new ArrayList<Object>(Arrays.asList("Book-3", "Book-2"));
        assertNull(library.searchBooks(SearchByType.NAME, keys)); // Should return null
    }

    @Test
    void searchByID() {
        Library library = new Library();

        Book book1 = new Book("Book-1", "Author-1", 10);
        Book book2 = new Book("Book-2", "Author-2", 11);
        Book book3 = new Book("Book-3", "Author-3", 12);
        Book book4 = new Book("Book-4", "Author-1", 13);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);

        var keys = new ArrayList<Object>(Arrays.asList(10, 11));
        ArrayList<Book> search_results = library.searchBooks(SearchByType.ID, keys); // Should return [book1, book2]

        ArrayList<Book> correct_results = new ArrayList<>();
        correct_results.add(book1);
        correct_results.add(book2);

        boolean isEqual = true;

        if (search_results != null && search_results.size() == correct_results.size()) {
            for (Book correct_result : correct_results) {
                if (!search_results.contains(correct_result)) {
                    isEqual = false;
                    break;
                }
            }
        } else {
            isEqual = false;
        }

        assertTrue(isEqual);

    }

    @Test
    void searchByTitle() {
        Library library = new Library();

        Book book1 = new Book("Book-1", "Author-1", 10);
        Book book2 = new Book("Book-2", "Author-2", 11);
        Book book3 = new Book("Book-3", "Author-3", 12);
        Book book4 = new Book("Book-4", "Author-1", 13);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);

        var keys = new ArrayList<Object>(Arrays.asList("Book-1", "Book-3"));
        ArrayList<Book> search_results = library.searchBooks(SearchByType.TITLE, keys); // Should return [book1, book3]

        ArrayList<Book> correct_results = new ArrayList<>();
        correct_results.add(book1);
        correct_results.add(book3);

        boolean isEqual = true;

        if (search_results != null && search_results.size() == correct_results.size()) {
            for (Book correct_result : correct_results) {
                if (!search_results.contains(correct_result)) {
                    isEqual = false;
                    break;
                }
            }
        } else {
            isEqual = false;
        }
        assertTrue(isEqual);

    }

    @Test
    void searchByAuthor() {
        Library library = new Library();

        Book book1 = new Book("Book-1", "Author-1", 10);
        Book book2 = new Book("Book-2", "Author-2", 11);
        Book book3 = new Book("Book-3", "Author-3", 12);
        Book book4 = new Book("Book-4", "Author-1", 13);
        Book book5 = new Book("Not-owned-book", "Author-1", 15);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);

        var keys = new ArrayList<Object>(Arrays.asList("Author-1", "Author-x"));
        ArrayList<Book> search_results = library.searchBooks(SearchByType.AUTHOR, keys); // Should return [book1, book3]

        ArrayList<Book> correct_results = new ArrayList<>();
        correct_results.add(book1);
        correct_results.add(book4);

        boolean isEqual = true;
        if (search_results != null && search_results.size() == correct_results.size()) {
            for (Book correct_result : correct_results) {
                if (!search_results.contains(correct_result)) {
                    isEqual = false;
                    break;
                }
            }
        } else {
            isEqual = false;
        }
        assertTrue(isEqual);
    }
}

class StudentSearchTest {

    @Test
    void searchByTitle() {
        Library library = new Library();

        Student student1 = new Student("Alice", 10);
        Student student2 = new Student("Bob", 11);
        Student student3 = new Student("John", 12);

        library.addStudent(student1);
        library.addStudent(student2);
        library.addStudent(student3);

        var keys = new ArrayList<Object>(Arrays.asList("Alice", "Bob"));
        assertNull(library.searchStudents(SearchByType.TITLE, keys)); // Should return null
    }

    @Test
    void searchByAuthor() {
        Library library = new Library();

        Student student1 = new Student("Alice", 10);
        Student student2 = new Student("Bob", 11);
        Student student3 = new Student("John", 12);

        library.addStudent(student1);
        library.addStudent(student2);
        library.addStudent(student3);

        var keys = new ArrayList<Object>(Arrays.asList("Alice", "Bob"));
        assertNull(library.searchStudents(SearchByType.AUTHOR, keys)); // Should return null
    }

    @Test
    void searchByName() {
        Library library = new Library();

        Student student1 = new Student("Alice", 10);
        Student student2 = new Student("Bob", 11);
        Student student3 = new Student("John", 12);

        library.addStudent(student1);
        library.addStudent(student2);
        library.addStudent(student3);

        var keys = new ArrayList<Object>(Arrays.asList("Alice", "Bob"));
        ArrayList<Student> search_results = library.searchStudents(SearchByType.NAME, keys); // Should return [student1, student2]

        ArrayList<Student> correct_results = new ArrayList<>();
        correct_results.add(student1);
        correct_results.add(student2);

        boolean isEqual = true;
        if (search_results != null && search_results.size() == correct_results.size()) {
            for (Student correct_result : correct_results) {
                if (!search_results.contains(correct_result)) {
                    isEqual = false;
                    break;
                }
            }
        } else {
            isEqual = false;
        }
        assertTrue(isEqual);
    }

    @Test
    void searchByID() {
        Library library = new Library();

        Student student1 = new Student("Alice", 10);
        Student student2 = new Student("Bob", 11);
        Student student3 = new Student("John", 12);

        library.addStudent(student1);
        library.addStudent(student2);
        library.addStudent(student3);

        var keys = new ArrayList<Object>(Arrays.asList(12, 13));
        ArrayList<Student> search_results = library.searchStudents(SearchByType.ID, keys); // Should return [student3]

        ArrayList<Student> correct_results = new ArrayList<>();
        correct_results.add(student3);

        boolean isEqual = true;
        if (search_results != null && search_results.size() == correct_results.size()) {
            for (Student correct_result : correct_results) {
                if (!search_results.contains(correct_result)) {
                    isEqual = false;
                    break;
                }
            }
        } else {
            isEqual = false;
        }
        assertTrue(isEqual);
    }
}