
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Feed ${user.firstName} ${user.lastName}</title>
</head>
<body>
<%@include file="../index/header.jsp" %>

<style>
    .curs {
        cursor: pointer;
    }
</style>



<div style="display: none;" id="content" class="col-lg-8 col-lg-offset-2">
    <br/><br/>


    <table style="width: 100%">
        <thead>
        <tr>
            <td></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="a" items="${list}">
            <tr>
                <td>
                    <c:if test="${a.reposted.id != null}">
                        <a href="/user/id${a.reposted.id}">
                                ${a.reposted.firstName} ${a.reposted.lastName}
                        </a>
                        <span style="color: white"> <span class="glyphicon glyphicon-share-alt"></span> pinned post</span>
                        <br/><br/>
                    </c:if>

                    <a href="/user/id${a.sender.id}">
                            ${a.sender.firstName} ${a.sender.lastName}
                    </a>
                    <p>
                        <span class="glyphicon glyphicon-hourglass"></span>
                        ${a.date}
                    </p>
                    <p id="feed-content" class="text-center">
                        ${fn:substring(a.feedData.content, 0, 3000)}
                    </p>

                    <div class="btn-group">
                        <span class="glyphicon glyphicon-heart text-danger curs"></span>
                        <span class="glyphicon glyphicon-share text-muted curs"></span>
                    </div>

                    <hr/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
    $(document).ready(function () {
        $("#content").fadeToggle("slow");
    })

</script>
</body>
</html>
