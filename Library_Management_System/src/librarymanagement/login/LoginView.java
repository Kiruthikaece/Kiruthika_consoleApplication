package librarymanagement.login;

import java.util.Scanner;

import librarymanagement.LibraryManagementMain;
import librarymanagement.datalayer.LibraryDatabase;
import librarymanagement.librarysetup.LibrarySetupView;
import librarymanagement.model.Credentials;

public class LoginView {

   private LoginModel loginmodel;

   public LoginView() {
    loginmodel=new LoginModel(this);
   }
   
    public void init() {

        System.out.println("-----------------------------------------------------------------------\n" +
                    "          "+ LibraryManagementMain.getInstance().getAppName() + "  \n\n          version " +
                   LibraryManagementMain.getInstance().getAppVersion());
                   System.out.println("-----------------------------------------------------------------------");
                   System.out.println("fetch data...");
                   loginmodel.retrieveJsonAll();
                   System.out.println("\n\nPlease login to proceed.");
                  proceedLogin();  
    }
    
    public void proceedLogin() {
      Credentials credential=new Credentials();
      Scanner sc=new Scanner(System.in);
      System.out.println("\nEnter a username");
      String userName= sc.next();
      credential.setUserName(userName);
      System.out.println("\nEnter a password");
      String password= sc.next();
      credential.setPassword(password);
      loginmodel.validateLogin(credential);
    }
    public void onSuccess(String userName) {
       LibrarySetupView librarySetupView = new LibrarySetupView();
        if(userName.equals("admin")) {
        System.out.println("\n\n****** Admin login successfull! ******");
        librarySetupView.init();
        }
        
        else {
          System.out.println("\n\n****** User login successfull! ******");
          librarySetupView.initUser(userName);
        }
       
        
        
    }
    public void showAlert(String alertMessage) {
        System.out.println("\n"+alertMessage);
        checkForLogin();
    }

    private void checkForLogin() {
		System.out.println("\nDo you try again? \nType Yes/No");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.next();
		if (choice.equalsIgnoreCase("yes")) {
			proceedLogin();
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println("\n ---- Thank You ----");
		} else {
			System.out.println("\nInvalid choice, Please enter valid choice.\n");
			checkForLogin();
		}
	}
}
