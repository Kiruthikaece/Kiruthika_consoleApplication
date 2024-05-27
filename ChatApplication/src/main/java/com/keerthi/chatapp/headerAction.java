package com.keerthi.chatapp;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.keerthi.datalayer.DatabaseManager;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

public class headerAction extends ActionSupport {
    
    private String search;
   static private List<SignUpAction> searchResults;

    public String execute() {
    	
        if (search != null) {
            searchResults = DatabaseManager.getInstance().searchFriends(search);
            System.out.println(searchResults.size()+".....");

            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<SignUpAction> getSearchResults() {
        return searchResults;
    }
}
