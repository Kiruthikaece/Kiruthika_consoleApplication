package librarymanagement.booksetup;

import java.time.LocalDate;


import librarymanagement.datalayer.LibraryDatabase;
import librarymanagement.model.*;

public class BookSetupModel {

    private BookSetupView bookSetupView;

    public BookSetupModel(BookSetupView bookSetupView) {
       this.bookSetupView=bookSetupView;
    }

    public void validateBookAndAdd(Books book) {
       if(LibraryDatabase.getInstance().insertBooks(book))
       bookSetupView.onSuccessAddBook(book);
       else
       bookSetupView.onExistBook(book);

    }

    public void searchMethod(String bookName) {
        bookSetupView.listOfBooks(LibraryDatabase.getInstance().searchBooks(bookName));
    }

    public void getBooks() {
        bookSetupView.listOfBooks(LibraryDatabase.getInstance().getAllBooks());
    }

    public void removeBooks(String bookName) {
          if(LibraryDatabase.getInstance().removeBook(bookName))
          bookSetupView.showAlert("Book removed successfully");
          else
          bookSetupView.showAlert("No Books avilable");
    }

    public boolean validateRequestBook(String bookName,String userName) {
        if(LibraryDatabase.getInstance().isAssignBook(bookName,userName))
            return true;
        return false;
    }

    public void getAllDuebooks() {
        bookSetupView.printDueBooks(LibraryDatabase.getInstance().getAllDueBooks());
    }

}
