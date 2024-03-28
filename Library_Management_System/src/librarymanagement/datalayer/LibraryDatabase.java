package librarymanagement.datalayer;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import librarymanagement.model.*;


public class LibraryDatabase {
    

    private static LibraryDatabase libraryDatabase;

     private Library library;

     private String bookPath="E:\\ConsoleApplication\\Library_Management_System\\src\\librarymanagement\\JsonFile\\book.json";
     private String libraryPath="E:\\ConsoleApplication\\Library_Management_System\\src\\librarymanagement\\JsonFile\\library.json";
     private String userPath="E:\\ConsoleApplication\\Library_Management_System\\src\\librarymanagement\\JsonFile\\user.json";

     List<User> userList=new ArrayList<>();
     List<Books> bookList=new ArrayList<>();
     List<DueBook> dueBookList=new ArrayList<>();
     List<Library> libraryList=new ArrayList<>();

     ObjectMapper mapper = new ObjectMapper();

    public static LibraryDatabase getInstance() {
        if(libraryDatabase==null)
        libraryDatabase =new LibraryDatabase();
        return libraryDatabase;
    }

    public Library getLibraray() {
        return library;
    }
		
    
    public boolean insertLibrary(Library library) {
        boolean isNewLibrary=true;
        for(Library existLibrary:libraryList)
        if(existLibrary.getEmail().equals(library.getEmail())) {
        isNewLibrary=false;
         break;
        }
 
        if(isNewLibrary)
        {
        libraryList.add(library);
        addLibraryJson(library);
        return true;
        }
        else 
        return false;     
        
    }

    public boolean inserUserinDb(User user) {
       boolean isNewUser=true;
       for(User existUser:userList)
       if(existUser.getEmailId().equals(user.getEmailId())) {
        isNewUser=false;
        break;
       }

       if(isNewUser)
       {
        userList.add(user);
        addUserJson(user);
        return true;
       }
       else
       return false;
       
    }



    public List<Books> searchBooks(String bookdetail) {
        List<Books> searchResult = new ArrayList<>();
        if(bookList.size()>0) {
            for(Books book:bookList)
            if(bookdetail.equalsIgnoreCase(book.getName()))
            searchResult.add(book);
        }
        
        return searchResult;

    }

    public boolean insertBooks(Books book) {
        boolean isNewBook = true;
        for (Books existingBook : bookList) {
            if (book.getName().equals(existingBook.getName()) && 
                book.getAuthor().equals(existingBook.getAuthor())) {
                isNewBook = false;
                break;
            }
        }
        if (isNewBook) {
            bookList.add(book);
            addBookJson(book);
            return true;   
        } 
        else 
        return false;
        
    }
    


    public List<Books> getAllBooks() {
        return bookList;
    }

    public boolean removeBook(int bookId) {
        Iterator<Books> iterator = bookList.iterator();
        boolean isRemoved = false;
    
        while(iterator.hasNext()) {
            Books book = iterator.next();
            if(book.getId()==bookId) {
               int count=book.getAvailableCount();
               book.setAvailableCount(count-1);
            //    if(book.getAvailableCount()==0)
            //    {
            //     iterator.remove();
            //    }
                isRemoved = true;
                break;
            }
        }
    
        if (isRemoved) {
            try { 
                mapper.writeValue(new File("E:\\ConsoleApplication\\Library_Management_System\\src\\librarymanagement\\JsonFile\\book.json"), bookList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        return isRemoved;
    }



    public boolean isAssignBook(String bookName,String userName) {
        boolean isBookPresent=false,isUserPresent=false;

        DueBook duebook=new DueBook();
        for (Books book : bookList) {
            if (book.getName().equals(bookName) && book.getAvailableCount() > 0) 
            duebook.setBook(book);
            isBookPresent=true;
        }
        for(User user:userList) {
            if (user.getName().equals(userName)) 
            duebook.setUser(user);
            isUserPresent=true;
        }
        LocalDate td = LocalDate.now(); 
        LocalDate dueDate=td.plusDays(15);
        duebook.setTakenDate(td);
        duebook.setDueDate(dueDate);
        if(isBookPresent && isUserPresent) {
            dueBookList.add(duebook);
            duebook.getBook().setAvailableCount(duebook.getBook().getAvailableCount()-1);
            return true; 
        }
        return false; 
    }
    public boolean CheckValidLogin(Credentials credential) {
       
        if(credential.getUserName().equals("admin") && credential.getPassword().equals("admin123"))
        return true;
     

     for(User user:userList) 
        if(credential.getUserName().equals(user.getUserName()) && credential.getPassword().equals(user.getPassword())) 
         return true;

         return false;
    }

    public List<User> getallUsers() {
    
    return userList;
    }

    public List<DueBook> getAllDueBooks() {
        return dueBookList;
    }
   
    public void addBookJson(Books book) {
        try {
            File file = new File(bookPath);
            if (!file.exists()) {
                file.createNewFile();
                mapper.writeValue(file, new ArrayList<>());
            }
            List<Books> existingBooks = mapper.readValue(file,
                    new TypeReference<List<Books>>() {});
            existingBooks.add(book);
            mapper.writeValue(file, existingBooks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addLibraryJson(Library library) {
        try {
            File file = new File(libraryPath);
            if (!file.exists()) {
                file.createNewFile();
                mapper.writeValue(file, new ArrayList<>());
            }
            List<Library> existingLibrary = mapper.readValue(file,
                    new TypeReference<List<Library>>() {});
                    existingLibrary.add(library);
            mapper.writeValue(file, existingLibrary);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUserJson(User user) {
        try {
            File file = new File(userPath);
            if (!file.exists()) {
                file.createNewFile();
                mapper.writeValue(file, new ArrayList<>());
            }
            List<User> existingUser = mapper.readValue(file,
                    new TypeReference<List<User>>() {});
                    existingUser.add(user);
            mapper.writeValue(file, existingUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Books> retrieveBook() {
            try {
                File file = new File(bookPath);
                if(!file.exists()) {
                    file.createNewFile();
                    mapper.writeValue(file, new ArrayList<>());
                }
                bookList = mapper.readValue(new File(bookPath),
                        new TypeReference<List<Books>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        return bookList;

    }

    public List<User> retrieveUser() {
        try {
            File file = new File(userPath);
            if(!file.exists()) {
                file.createNewFile();
                mapper.writeValue(file, new ArrayList<>());
            }
            userList = mapper.readValue(new File(userPath),
                    new TypeReference<List<User>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<Library> retrieveLibrary() {

        try {
            File file = new File(libraryPath);
            if(!file.exists()) {
                file.createNewFile();
                mapper.writeValue(file, new ArrayList<>());
            }
            libraryList = mapper.readValue(new File(libraryPath),
                    new TypeReference<List<Library>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libraryList;
    }
    
    public void retrieveAll() {
        retrieveLibrary();
        retrieveUser();
        retrieveBook();
    }

    public int counterBook() {
        int size=0;
        try {
            File jsonFile = new File(bookPath);
            if(jsonFile.exists()) {
            List<Object> jsonList = mapper.readValue(jsonFile, new TypeReference<List<Object>>() {});
             size = jsonList.size();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    public int counterUser() {
        int size=0;
        try {
            File jsonFile = new File(userPath);
            if(jsonFile.exists()) {
            List<Object> jsonList = mapper.readValue(jsonFile, new TypeReference<List<Object>>() {});
             size = jsonList.size();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    public int counterLibrary() {
        int size=0;
        try {
            File jsonFile = new File(libraryPath);
            if(jsonFile.exists()) {
            List<Object> jsonList = mapper.readValue(jsonFile, new TypeReference<List<Object>>() {});
             size = jsonList.size();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    public List<Library> getAllLibrary() {
        return libraryList;
      
    }

    public void updateBooksToJson() {
        try {
            mapper.writeValue(new File(bookPath), bookList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean UpdateQueryBook(int id, int count) {
        boolean ispresent=false;
       for(Books book:bookList) {
        if(book.getId()==id)
        book.setAvailableCount(count);
        updateBooksToJson();  
        ispresent=true;
       } 
       if(ispresent)
       return true;
       else
       return false;
    }
}
