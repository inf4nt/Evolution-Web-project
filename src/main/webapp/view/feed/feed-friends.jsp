<%--
  Created by IntelliJ IDEA.
  User: Infant
  Date: 26.07.2017
  Time: 0:11
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>News</title>
</head>
<body>
<%@include file="../index/header.jsp" %>

<div id="content" class="col-lg-8 col-lg-offset-2">
    <div id="table-tweet-content">
        <table style="width: 100%">
            <thead>
            <tr>
                <td></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="a" items="${list}">
                <tr id="tr-${a.id}">
                    <td>
                        <div class="block-background div-white">
                            <a href="/user/id${a.sender.id}">
                                    ${a.sender.firstName} ${a.sender.lastName}
                            </a>
                            <div class="feed-link">
                                <br/>
                                <p>
                                    ${a.content}
                                </p>
                            </div>
                            <p>
                            <c:forEach var="t" items="${a.listTags()}">
                                <a class="tweet-tags" href="/feed/tag/${t}">
                                    #${t}
                                </a>
                            </c:forEach>
                            </p>
                            <br/>
                            <%--<div class="btn-group">--%>
                                <%--<button class="btn btn-default"><span class="glyphicon glyphicon-heart text-danger"></span> Like</button>--%>
                                <%--<button name="${a.id}" type="submit" data-toggle="modal" data-target="#modal-id-repost" class="btn btn-default btn-repost-info"><span class="glyphicon glyphicon-retweet"></span>--%>
                                        <%--${a.countRepost}--%>
                                    <%--Repost--%>
                                <%--</button>--%>
                            <%--</div>--%>

                        </div>
                        <br/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


</body>
</html>
