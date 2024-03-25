package librarymanagement;

import librarymanagement.login.LoginView;

public class LibraryManagementMain {


    private static LibraryManagementMain libraryManagement;

	private String appName = "Library Management System";

	private String appVersion = "0.1.0";

	
	private LibraryManagementMain() {

	}

    public static LibraryManagementMain getInstance() {
		if (libraryManagement == null) {
			libraryManagement = new LibraryManagementMain();
		}
		return libraryManagement;
	}
    public String getAppName() {
		return appName;
	}

	public String getAppVersion() {
		return appVersion;
	}
    public static void main(String[] args) {
        
        LoginView loginview = new LoginView();
        loginview.init();
    }
}
