package librarymanagement.model;

import java.time.LocalDate;


public class DueBook {
    
    private User  user;
    private Books  book;
    private LocalDate takenDate;
    private LocalDate dueDate;


    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Books getBook() {
        return book;
    }
    public void setBook(Books book) {
        this.book = book;
    }
    
    
    
    public LocalDate getTakenDate() {
        return takenDate;
    }
    public void setTakenDate(LocalDate takenDate) {
        this.takenDate = takenDate;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
