package librarymanagement.ManageUser;

import java.util.List;
import java.util.Scanner;

import librarymanagement.model.User;

public class ManageUserView {

    Scanner sc=new Scanner(System.in);
    private ManageUserModel manageUserModel;
    
    public ManageUserView() {
        manageUserModel=new ManageUserModel(this);
    }

    public void addNewUser() {
       System.out.println("\nEnter the user details here");
       User user=new User();

       System.out.println("\nEnter name");
       user.setName(sc.next());

       user.setId(manageUserModel.setCount()+1);
       System.out.println("\nEnter User Email Id");
       user.setEmailId(sc.next());

       System.out.println("\nEnter Username");
       user.setUserName(sc.next());
       System.out.println("\nEnter password");
       user.setPassword(sc.next());

       System.out.println("\nEnter User Phone Number");
       user.setPhoneNo(sc.next());
      
     
       manageUserModel.validateAndAddUser(user);
    }

    public void onSuccessAddUser(User user) {
        System.out.println("---- user "+user.getName()+" added successfully ----");
        checkForAddNewUser();
    }

    public void onExistUser(User user) {
        System.out.println("---- user "+user.getName()+" already exist ----");
        checkForAddNewUser();
    }

    private void checkForAddNewUser() {
		System.out.println("Do you want to add more users? \nType Yes/No");
		
		String choice = sc.next();
		if (choice.equalsIgnoreCase("yes")) {
			addNewUser();
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println("\n Thanks for adding users");
		} else {
			System.out.println("\nInvalid choice, Please enter valid choice.\n");
			checkForAddNewUser();
		}
	}

    public void listAllUser() {
        manageUserModel.getAll();
    }

    public void printUser(List<User> getallUsers) {
        if(getallUsers.size()>0) {
            System.out.printf("\n%-10s %-15s %-25s %-15s %-15s","Id","Name","Email","Username","Phone Number");
           for(User user:getallUsers)
           System.out.printf("\n%-10d %-15s %-25s %-15s %-15s",user.getId(),user.getName(),user.getEmailId(),user.getUserName(),user.getPhoneNo());
        }
        else
        System.out.println("no data avilable");
       

    }

}
