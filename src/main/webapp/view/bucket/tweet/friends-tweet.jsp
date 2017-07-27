<%--
  Created by IntelliJ IDEA.
  User: Infant
  Date: 23.07.2017
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Feed ${user.firstName} ${user.lastName}</title>
</head>
<body>
<%@include file="../../index/header.jsp" %>


<div id="content">
    <div class="col-lg-8 col-lg-offset-2">

        <div id="table-tweet-content">
            <table style="width: 100%">
                <thead>
                <tr>
                    <td></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="a" items="${tweets}">
                    <tr id="tr-${a.id}">
                        <td>
                            <div class="block-background div-white">
                                <c:if test="${a.sender.id == authUser.id && a.reposted.id == null}">
                                    <form action="/feed/${a.id}/delete/view" method="GET">
                                        <button type="submit" class="btn btn-default btn-md pull-right">
                                            <span class="glyphicon glyphicon-remove text-danger"></span>
                                        </button>
                                    </form>
                                </c:if>
                                <c:if test="${a.reposted.id != null}">
                                    <a href="/user/id${a.reposted.id}">
                                            ${a.reposted.firstName} ${a.reposted.lastName}
                                    </a>
                                    <span style="color: white"> <span class="glyphicon glyphicon-share-alt"></span> reposted</span>
                                    <br/><br/>
                                </c:if>

                                <a href="/user/id${a.sender.id}">
                                        ${a.sender.firstName} ${a.sender.lastName}
                                </a>
                                <div class="feed-link">
                                    <br/>
                                    <c:if test="${a.content.length() > 1000}">
                                        <p class="curs" id="${a.id}" href="#modal-id" data-toggle="modal">
                                                ${fn:substring(a.content, 0, 1000)}...
                                        </p>
                                    </c:if>
                                    <c:if test="${a.content.length() <= 1000}">
                                        <p>
                                            ${a.content}
                                        </p>
                                    </c:if>
                                </div>
                                <p>
                                    <c:forEach var="t" items="${a.listTags()}">
                                        <a class="tweet-tags" href="/feed/tag/${t}">
                                            #${t}
                                        </a>
                                    </c:forEach>
                                </p>
                                <br/>
                                <div class="btn-group">
                                    <button class="btn btn-default"><span class="glyphicon glyphicon-heart text-danger"></span> Like</button>
                                    <button name="${a.id}" type="submit" data-toggle="modal" data-target="#modal-id-repost" class="btn
                                    <c:if test="${a.checkRepost}">
                                        btn-primary
                                    </c:if>
                                    <c:if test="${!a.checkRepost}">
                                       btn-default
                                    </c:if>
                                     btn-repost-info"><span class="glyphicon glyphicon-retweet"></span>
                                            ${a.countRepost}
                                        Repost
                                    </button>
                                </div>

                            </div>
                            <br/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>


        <div class="modal fade" id="modal-id-repost">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Modal title</h4>
                    </div>
                    <div id="modal-id-repost-content" class="modal-body">

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">Save changes</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->


        <div class="modal fade" id="modal-id">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" onclick="clearModal()" aria-hidden="true">&times;</button>
                        <h4 style="color: black" id="tweet-modal-title" class="modal-title"></h4>
                    </div>
                    <div id="modal-tweet-content" class="modal-body block-sms">
                        <h1 class="text-center"><img class="preloader-modal-tweet" src="/resources/page-preloader.gif"></h1>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" onclick="clearModal()" data-dismiss="modal">Close</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->


    </div>
</div>


</body>
</html>
