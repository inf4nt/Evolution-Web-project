<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.04.2017
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${user.getFirstName()} ${user.getLastName()}</title>
</head>
<body>
<%@include file="../index/header.jsp" %>


<div class="col-lg-10">
    <div class="col-lg-4">
        <h1 class="text-center">PHOTO</h1>
        <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 250px; height: 300px;"
             src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">
    </div>

    <div class="col-lg-8 pull-right">
        <div class="text-center text-info">
            <h3>
                <c:if test="${user.getId() == authUser.getId()}">
                    <ins><span>Hello!</span></ins>
                </c:if>
                ${user.getFirstName()} ${user.getLastName()}
            </h3>
            <hr/>
            <ul class="nav">
                <li><a  id="showHideInfo" onclick="showHideAllInfo()" href="#" >Show full information</a></li>
            </ul>
            <div id="div-all-info" style="display: none">
                <h1>FRIEND COUNT</h1>
                <h1>FOLLOWER COUNT</h1>
                <h1>Offer to be friends</h1>
            </div>

        </div>
    </div>
</div>

<script>
    function showHideAllInfo() {
        var cl = document.getElementById("div-all-info").style.display;
        if (cl == "none") {
            document.getElementById("div-all-info").style.display = "block";
            document.getElementById("showHideInfo").innerHTML = "Hide full information";
        }

        if (cl == "block") {
            document.getElementById("div-all-info").style.display = "none";
            document.getElementById("showHideInfo").innerHTML = "Show full information";
        }
    }
</script>


</body>
</html>
