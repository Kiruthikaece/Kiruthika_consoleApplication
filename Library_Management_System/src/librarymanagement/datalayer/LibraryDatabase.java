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
             try {
                 File file = new File("E:\\ConsoleApplication\\Library_Management_System\\src\\librarymanagement\\JsonFile\\library.json");
                 if (!file.exists()) {
                     file.createNewFile();
                     mapper.writeValue(file, new ArrayList<>());
                 }
                 List<Library> existingLibrary = mapper.readValue(file,
                         new TypeReference<List<Library>>() {});
                         existingLibrary.add(library);
                 mapper.writeValue(file, existingLibrary);
                 return true;
                 
             } catch (IOException e) {
                 e.printStackTrace();
                 return false;
             }
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
            try {
                File file = new File("E:\\ConsoleApplication\\Library_Management_System\\src\\librarymanagement\\JsonFile\\user.json");
                if (!file.exists()) {
                    file.createNewFile();
                    mapper.writeValue(file, new ArrayList<>());
                }
                List<User> existingUser = mapper.readValue(file,
                        new TypeReference<List<User>>() {});
                        existingUser.add(user);
                mapper.writeValue(file, existingUser);
    
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
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
            try {
                File file = new File("E:\\ConsoleApplication\\Library_Management_System\\src\\librarymanagement\\JsonFile\\book.json");
                if (!file.exists()) {
                    file.createNewFile();
                    mapper.writeValue(file, new ArrayList<>());
                }
                List<Books> existingBooks = mapper.readValue(file,
                        new TypeReference<List<Books>>() {});
                existingBooks.add(book);
                mapper.writeValue(file, existingBooks);
    
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
    


    public List<Books> getAllBooks() {
       
            try {
                bookList = mapper.readValue(new File("E:\\ConsoleApplication\\Library_Management_System\\src\\librarymanagement\\JsonFile\\book.json"),
                        new TypeReference<List<Books>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        
        return bookList;
    }

    public boolean removeBook(String bookName) {
        Iterator<Books> iterator = bookList.iterator();
        boolean isRemoved = false;
    
        while(iterator.hasNext()) {
            Books book = iterator.next();
            if(book.getName().equalsIgnoreCase(bookName)) {
               int count=book.getAvailableCount();
               book.setAvailableCount(count-1);
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
       
        try {
            userList = mapper.readValue(new File("E:\\ConsoleApplication\\Library_Management_System\\src\\librarymanagement\\JsonFile\\user.json"),
                    new TypeReference<List<User>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    return userList;
    }

    public List<DueBook> getAllDueBooks() {
        try {
            dueBookList = mapper.readValue(new File("E:\\ConsoleApplication\\Library_Management_System\\src\\librarymanagement\\JsonFile\\duebook.json"),
                    new TypeReference<List<DueBook>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dueBookList;
    }
    
}
