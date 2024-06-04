<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.keerthi.chatapp.LoginAction" %>
    <%@ page import="com.keerthi.chatapp.SignUpAction"%>
    <%@ page import="com.keerthi.chatapp.UserWithResponse"%>
    <%@ page import="com.keerthi.chatapp.headerAction"%>
    <%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatApp</title>
<style>
.container {
    width: 1500px;
    height: 650px;
    margin: -66px -138px;
    overflow: hidden;
    background: #fff;
    padding: 10px 10px;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

.form-container {
    max-width: 600px;
    padding: 10px;
    display: flex;
    background-color: white;
    position: absolute;
    bottom: 50px;
    right: 40px;
}

.form-container textarea {
   width: 500px;
    padding: 15px;
    margin: 15px 0 22px 0;
    border: none;
    background: LightGray;
    resize: none;
    min-height: 55px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

.form-container textarea:focus {
  background-color: #ddd;
  outline: none;
}
.form-container .btn {
    background-color: #04AA6D;
    color: white;
    padding: 10px 40px;
    border: none;
    cursor: pointer;
    opacity: 0.8;
    height: 50px;
    margin: 40px 20px;
}

.form-container .cancel {
  background-color: red;
}

.form-container .btn:hover, .open-button:hover {
  opacity: 1;
}

#chatDisplay {
    margin-top: 20px;
    overflow: auto;
    max-height: 400px;
}

/* For better visibility of the example */
body {
  margin: 80px 200px;
  background-color: #cccccc;
}

/* Message box starts here */
.container-msg {
  clear: both;
  position: relative;
  margin-bottom: 10px; /* Add some space between messages */
  max-width: 80%; /* Prevent the message from being too wide */
   white-space: inherit;
  word-break: break-word;/* Ensure long words break to the next line */
}
.container-msg .message-body {
  float: left;
  background-color: #ffffff;
  border: 1px solid #000000;
  padding: 6px 8px;
  border-radius: 5px;
}

.container-msg .message-body p {
  margin: 0;
}

.message-sent {
  float: right;
  right:50px;
}

.message-sent .message-body {
  background-color: #dcf8c6;
  border: 1px solid #dcf8c6;
}

.message-sent .arrow .outer {
  border-right-color: #dcf8c6;
}


.vl {
    border-right: 3px solid lightgray;
    height: 648px;
    position: absolute;
    left: 6%;
    top: 87px;
    overflow: auto;
}

.list-f {
   position: relative;
    right: 20px;
    align-content: center;
    text-align: center;
    height: 100px;
    margin: 29px;
    color: black;
    width: 300px;
    padding: 0.5rem;
    border-radius: 0.5rem;
    cursor: pointer;
    border-right: 4px solid #f50057;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
    }


</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<%
LoginAction l=new LoginAction();
SignUpAction signupAction1=l.getSignupAction();
int sender_id = signupAction1 != null ? signupAction1.getId() : 0;
headerAction h=new headerAction();
List<UserWithResponse> searchre=h.getSearchResults();
int receiver_id=0;

if(searchre!=null)
receiver_id=searchre.get(0).getId();
else
System.out.println("No search result");

List<SignUpAction> friendsList=l.getFriendsList();
%>
<div class="container">
<div class="chat-popup" id="myForm">
  <form class="form-container" id="chatForm">
    <textarea placeholder="Type message.." name="msg" id="message" required></textarea>
    <button type="submit" class="btn">Send</button>
  </form>
  
  <div id="chatDisplay"></div>
 
</div>
<div class="vl">
<% for(SignUpAction frd:friendsList) { %>
<div class="list-f"><%= frd.getName() %></div>
<% } %>
</div>
</div>
<script>
$(document).ready(function() {
    $('#chatForm').submit(function(event) {
        event.preventDefault(); 

        var message = $('#message').val();
        var sender_id = '<%= sender_id %>';
        var receiver_id = '<%= receiver_id %>';

        $.ajax({
            url: 'sendChat',
            method: 'POST',
            data: {
                sender_id: sender_id,
                receiver_id: receiver_id,
                chat: message
            },
            success: function(response) {
                var messageHtml = `
                <div class="container-msg message-sent">
        
                    <div class="message-body">
                        <p>` + message + `</p>
                    </div>
                </div>`;
                $('#chatDisplay').append(messageHtml);
                $('#message').val('');
            },
            error: function(xhr, status, error) {
                alert('Failed to send message: ' + error);
            }
        });
    });
});
</script>

</body>
</html>