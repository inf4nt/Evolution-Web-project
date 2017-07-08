<%--
  Created by IntelliJ IDEA.
  User: Infant
  Date: 08.07.2017
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All ${role}</title>
</head>
<body>
<%@include file="../index/header.jsp" %>
<c:set var="count" value="0"/>


<div class="col-md-9 col-lg-offset-2">

    <div>
        <ul class="pagination block-background">
            <c:set var="size" value="${list.size() - 1}"/>
            <c:forEach var="a" begin="0" end="${size/pageSize}">
                <li <c:if test="${a == 0}">class="active"</c:if> name="${a + 1}" ><a class="pagination-btn curs" name="${a + 1}" >${a + 1}</a></li>
            </c:forEach>
        </ul>
    </div>

    <br/>

    <div class="row block-background">
        <div class="col-md-14 ">

            <div id="pagination-content">
                <c:forEach var="a" varStatus="loop" items="${list}">
                    <c:if test="${loop.index%pageSize == 0}">
                        <c:set var="count" value="${count = count + 1}"/>
                        <ul class="pagination-content <c:if test="${loop.index >= pageSize}">hidden</c:if>" data-page="${count}">
                    </c:if>
                            <li class="pagination-content-item" style="width: 100%;height: 100px">
                                <img data-src="holder.js/140x140" class="img-circle center-block pull-left" style="width: 80px; height: 100%;"
                                     src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">
                                <div style="position: absolute;left:200px">
                                    <a href="/user/id${a.id}" style="font-size:large; color:white">
                                        ${a.firstName} ${a.lastName}
                                    </a>
                                </div>
                            </li>
                            <hr/>
                    <c:if test="${loop.index == pageSize * count - 1 || (loop.index == list.size() - 1 && list.size() - 1 <= pageSize * count) }">
                        </ul>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>

</div>
</body>

<script>
    $(document).ready(function () {
        $(".pagination-btn").click(function () {
            $(".pagination li").removeClass("active");
            var id = this.name;
            var ul = $("#pagination-content ul[data-page=" + id + "]");
            $(".pagination-content").addClass("hidden");
            ul.removeClass("hidden");
            $(".pagination li[name=" + id + "]").addClass("active");
        })
    })
</script>


</html>






<%--<c:if test="${loop.index == pageSize * count - 1}">--%>
    <%--</ul>--%>
<%--</c:if>--%>
<%--<c:if test="${loop.index == list.size() - 1 && list.size() - 1 <= pageSize * count}">--%>
    <%--</ul>--%>
<%--</c:if>--%>