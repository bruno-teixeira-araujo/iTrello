<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

  <body>  
  
     <div class="row">     
        <div class="col-md-6"> 
          <h3>How to use iTrello App:</h3>
          <ol>
            <li>Go to "Login" </li>
            <li>Enter your Trello key and secret </li>
            <li>Allow iTrello to fetch data from Trello </li>
            <li>Go to "Boards" to list all your boards </li>
            <li>Select a "Board" to see the dashboard </li>
            <li>Go to "Logout" to logout </li>
          </ol>                                
        </div> 
        <div class="col-md-6">
          <h3>How to install iTrello:</h3>    
          <ol>
            <li>Clone: <br/> <code> git clone https://github.com/bruno-teixeira-araujo/iTrello.git </code> </li>
            <li>Build: <br/> <code> $ mvn clean install </code> </li>
            <li>Run: <br/>  <code> $ mvn jetty:run </code></li>
            <li>See: <br/>  <code>http://localhost:8080/itrello </code> </li>                                
          </ol> 
        </div> 
     </div>

 </body>
</html>