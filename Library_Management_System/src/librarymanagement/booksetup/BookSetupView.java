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
        sc.nextLine();
        System.out.println("\nEnter book name");
        book.setName(sc.nextLine());
        book.setId(bookSetupModel.setCount()+1);
        System.out.println("\nEnter book author");
        book.setAuthor(sc.nextLine());
        System.out.println("\nEnter book journer");
        book.setJourner(sc.nextLine());
        System.out.println("\nEnter book avilable count");
        book.setAvailableCount(sc.nextInt());
        sc.nextLine(); 
        System.out.println("\nEnter book publication");
        book.setPublication(sc.nextLine());
        bookSetupModel.validateBookAndAdd(book);
   }

   public void onSuccessAddBook(Books book) {
     System.out.println("---  "+book.getName()+" book added successfully ----");
     onCheckAddNewBook();
   }

  public void onExistBook(Books book) {
   System.out.println("---  "+book.getName()+" book already exist ----");
   onCheckAddNewBook();
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
    bookSetupModel.searchMethod(sc.nextLine());
}

public void listOfBooks(List<Books> list) {
  if(list.size() > 0) {
      System.out.println("-----------------------------------------------------------------------------------------");
      System.out.printf("%-8s | %-15s | %-15s | %-15s | %-15s | %-8s%n",
                     "Book Id","Book Name", "Author", "Journer","Publication","Available Count");
      System.out.println("-----------------------------------------------------------------------------------------");
      for(Books book : list) {
          System.out.printf("%-8d | %-15s | %-15s |  %-15s | %-15s | %-8d%n",
                           book.getId(),book.getName(), book.getAuthor(),
                            book.getJourner(),  book.getPublication(),book.getAvailableCount());
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
  System.out.println("Enter book Id for deletion:");
  bookSetupModel.removeBooks(sc.nextInt());
}

public void showAlert(String message) {
 System.out.println(message);
}

public void requestBook(String userName) {
  System.out.println("\nEnter book name");
  if(bookSetupModel.validateRequestBook(sc.nextLine(),userName)) {
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
 else
 showAlert("No data avilable");
}

public void updateBook() {
  System.out.println("Enter book id for update:");
  int id=sc.nextInt();
  System.out.println("Update avilable count:");
  int count=sc.nextInt();
  bookSetupModel.updateCount(id,count);
  
}




}