<%@page import="in.co.mss.rmshib.dto.UserDTO"%>
<%@page import="in.co.mss.rmshib.dto.RoleDTO"%>
<%@page import="in.co.mss.rmshib.controller.ORSView"%>
<html>
<body>
    <form action="<%=ORSView.WELCOME_CTL%>">
        <%@ include file="Home.jsp" %>
                    <h1 class="text-center">
                        <font size="10px" color="red">Welcome to ORS </font>
                    </h1>
        
                    <%
                    UserDTO dto = (UserDTO) session.getAttribute("user");
                        if (dto != null) {
                            if (dto.getRoleId() == RoleDTO.STUDENT) {
                    %>
        
                    <h2 class="text-center">
                        <a href="<%=ORSView.GET_MARKSHEET_CTL%>">Click here to see your
                            Marksheet </a>
                    </h2>
                     
                     <%
                            }
                        }
                     %>
                
                </form>
        
      <%--  <%@ include file="Footer.jsp"%> --%>
</body>
</html>
<%@ include file="Footer.jsp" %>