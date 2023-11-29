<%-- 
    Document   : Home
    Created on : 26. nov. 2023, 21.16.05
    Author     : Bokaj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!Doctype HTML>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CraveConnect</title>
    <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet">

  </head>
<body>
    
  <!-- Menu elements start -->
  <div id="square1" class="square">
    <nav>    

      <a href="${pageContext.request.contextPath}\home.jsp">Home</a>
      <a href="${pageContext.request.contextPath}\search.jsp">Search</a>
      <a href="${pageContext.request.contextPath}\profile.jsp">User profile</a> 

    </nav>
  </div>

  <div id="square2" class="square">

    <p>Søg på din ynglingsingredienser eller din livret og få resultater der passer præcist på din søgning, både hvis du vil finde noget nem aftensmad udefra eller selv vil igang med at kokkererer dit næste mesterværk</p>
  
  </div>

  <div id="square3" class="square">
  </div>
   
  <!-- Menu elements stop -->
  
  <div class="square">
    <p class="title">Hvad Craver du i dag?</p>
  </div>

  <div id="search-bar" class="square">
    <input type="text" placeholder="Skriv en ingrediens eller brug filtrene under til at finde din ret">
  </div>

</body>

</html>