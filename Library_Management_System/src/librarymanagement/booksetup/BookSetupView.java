package librarymanagement.booksetup;

import java.util.List;
import java.util.Scanner;

import librarymanagement.model.Books;
import librarymanagement.model.DueBook;

public class BookSetupView {

   Scanner sc=new Scanner(System.in);
   private BookSetupModel bookSetupModel;

   public BookSetupView() {
    bookSetupModel=new BookSetupModel(this);
   }

   public void initAddBooks() {
        System.out.println("\nEnter the Books details here");
        Books book=new Books();
        System.out.println("\nEnter book name");
        book.setName(sc.next());
        book.setBookId();
        System.out.println("\nEnter book author");
        book.setAuthor(sc.next());
        System.out.println("\nEnter book journer");
        book.setJourner(sc.next());
        System.out.println("\nEnter book avilable count");
        book.setAvailableCount(sc.nextInt());
        System.out.println("\nEnter book volume");
        book.setVolume(sc.nextInt());
        bookSetupModel.validateBookAndAdd(book);
   }

   public void onSuccessAddBook(Books book) {
     System.out.println("---  "+book.getName()+" book added successfully ----");
   }

  public void onExistBook(Books book) {
   System.out.println("---  "+book.getName()+" book already exist ----");
  }

  public void onCheckAddNewBook() {
   System.out.println("Do you want to add more books? \nType Yes/No");
   String choice=sc.next();
   if(choice.equalsIgnoreCase("yes"))
   initAddBooks();
   else if(choice.equalsIgnoreCase("no"))
   System.out.println("\n Thanks for adding books");
   else {
      System.out.println("\n Invalid choice , please enter correct choice");
      onCheckAddNewBook();
   }
  }

public void searchBookUser() {
    System.out.println("Enter book name for search");
    bookSetupModel.searchMethod(sc.next());
}

public void listOfBooks(List<Books> list) {
  if(list.size() > 0) {
      System.out.println("-----------------------------------------------------------------------------------------");
      System.out.printf("%-15s | %-8s | %-15s | %-15s | %-15s | %-8s%n",
                        "Book Name", "Book Id", "Author", "Journer", "Available Count", "Volume");
      System.out.println("-----------------------------------------------------------------------------------------");
      for(Books book : list) {
          System.out.printf("%-15s | %-8d | %-15s |  %-15s | %-15d | %-8d%n",
                            book.getName(), book.getId(), book.getAuthor(),
                            book.getJourner(), book.getAvailableCount(), book.getVolume());
      }
      System.out.println("-----------------------------------------------------------------------------------------");
  } else {
      System.out.println("No books available");
  }
}

public void listAllBook() {
   bookSetupModel.getBooks();
}

public void removeBookFromList() {
  System.out.println("Enter book name for deletion:");
  bookSetupModel.removeBooks(sc.next());
}

public void showAlert(String message) {
 System.out.println(message);
}

public void requestBook(String userName) {
  System.out.println("\nEnter book name");
  if(bookSetupModel.validateRequestBook(sc.next(),userName)) {
     showAlert("Book assigned successfully.");
  }
  else 
    System.out.println("Sorry, the requested book is not available.");
}

public void ListDuebooks() {
  bookSetupModel.getAllDuebooks();
}

public void printDueBooks(List<DueBook> allDueBooks) {
 if(allDueBooks.size()>0) {
  System.out.printf("\n%-15s %-15s %-15s %-15s","Name","Book name","Borrow date","Due date");
  for(DueBook list:allDueBooks)
  System.out.printf("\n%-15s %-15s %-15s %-15s",list.getUser().getUserName(),list.getBook().getName(),list.getTakenDate(),list.getDueDate());
 }
}
}