package librarymanagement.librarysetup;

import java.util.Scanner;

import librarymanagement.LibraryManagementMain;
import librarymanagement.ManageUser.ManageUserView;
import librarymanagement.booksetup.BookSetupView;
import librarymanagement.login.LoginView;
import librarymanagement.model.Library;

public class LibrarySetupView {
    
    private LibrarySetupModel libraraySetupModel;

    Scanner sc=new Scanner(System.in);

    public LibrarySetupView() {
        libraraySetupModel=new LibrarySetupModel(this);
    }

    public void init() {
        libraraySetupModel.startSetup();
    }

    public void onFirstSetup() {
      
        System.out.println("\nEnter library details");

        Library library=new Library();
        System.out.println("\nEnter a Library Name");
        library.setLibraryName(sc.next());
        library.setLibraryId();
        System.out.println("\nEnter a Email");
        library.setEmail(sc.next());
        System.out.println("\nEnter a Phone number");
        library.setPhoneNo(sc.next());
        System.out.println("\nEnter a address");
        library.setAddress(sc.next());
        libraraySetupModel.validateLibrary(library);
    }

    public void onCompleteSetup(Library library) {
       System.out.println("\nLibrary Setup is Completed :)");
       System.out.println("\nCurrent Library Name - " + library.getLibraryName());
       Scanner sc = new Scanner(System.in);
       while(true) {
        System.out.println("\n------------------------------------------");
        System.out.println(" 1-Add User\n 2-Add Book\n 3-Search Book\n 4-List Books\n 5-List Users\n 6-Remove Book\n 7-Overdue books List\n 8-Logout\n 0-Exit");
        System.out.println("\n------------------------------------------");
        System.out.println("\nEnter your choice:");
        int choice=sc.nextInt();
         switch(choice) {
            case 1:new ManageUserView().addNewUser();
                   break;
            case 2:new BookSetupView().initAddBooks();
                   break;
            case 3:new BookSetupView().searchBookUser();
                   break;
            case 4:new BookSetupView().listAllBook();
                   break;
            case 5:new ManageUserView().listAllUser();
                   break;
            case 6:new BookSetupView().removeBookFromList();
                   break;
            case 7:new BookSetupView().ListDuebooks();
                   break;
            case 8:System.out.println("\n-- You are logged out successfully -- \n\n");
				new LoginView().init();
				return;
           case 0:System.out.println("\n-- Thanks for using " + LibraryManagementMain.getInstance().getAppName() + " --");
				  return; 
	    default:
		   System.out.println("\nPlease Enter valid choice\n");
         }
       }
    }

    public void showAlert(String message) {
        System.out.println("\n"+message);
        onFirstSetup();
    }

    public void initUser(String userName) {
        Scanner sc = new Scanner(System.in);
        while(true) {
         System.out.println("\n------------------------------------------");
         System.out.println(" 1-Search Book\n 2-List Books\n 3-Request Book\n 4-Logout\n 0-Exit\n");
         System.out.println("\n------------------------------------------");
         System.out.println("\nEnter your choice:");
         int choice=sc.nextInt();
          switch(choice) {
             case 1:new BookSetupView().searchBookUser();
                    break;
             case 2:new BookSetupView().listAllBook();
                    break;
             case 3:new BookSetupView().requestBook(userName);
                    break;
             case 4:System.out.println("\n-- You are logged out successfully -- \n\n");
                    new LoginView().init();
                    return;
            case 0:System.out.println("\n-- Thanks for using " + LibraryManagementMain.getInstance().getAppName() + " --");
                   return; 
             default:
                 System.out.println("\nPlease Enter valid choice\n");
          }
        }
    }


}
