
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Feed ${user.firstName} ${user.lastName}</title>
</head>
<body>
<%@include file="../index/header.jsp" %>



<div id="content" class="col-lg-8 col-lg-offset-2">

    <%--<div>--%>
        <%--<form action="/feed/post/view" method="POST">--%>
            <%--<div class="form-group">--%>
                <%--<textarea id="input-tweet" placeholder="What's new ?" name="tweetContent" class="form-control" style="height: 100px " rows="5" ></textarea>--%>
            <%--</div>--%>
            <%--<div class="col-lg-12 " id="tweet-post">--%>
                <%--<button type="submit" id="btn-tweet-post" style="width: 20%" class="btn btn-info pull-right">--%>
                    <%--Post <span class="glyphicon glyphicon-ok"/>--%>
                <%--</button>--%>
            <%--</div>--%>
        <%--</form>--%>
    <%--</div>--%>

    <%--<br/><br/><br/>--%>

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
                                <c:if test="${a.feedData.content.length() > 1000}">
                                    <p onclick="ajaxTweetModal(this)" class="curs" id="${a.id}" href="#modal-id" data-toggle="modal">
                                        ${fn:substring(a.feedData.content, 0, 1000)}...
                                    </p>
                                </c:if>
                                <c:if test="${a.feedData.content.length() <= 1000}">
                                    <p>
                                        ${a.feedData.content}
                                    </p>
                                </c:if>
                            </div>
                            <p>
                                <c:forEach var="t" items="${a.feedData.listTags()}">
                                    <a class="tweet-tags" href="/feed/tag/${t}">
                                        #${t}
                                    </a>
                                </c:forEach>
                            </p>
                            <br/>
                            <div class="btn-group">
                                <button class="btn btn-default"><span class="glyphicon glyphicon-heart text-danger"></span> Like</button>
                                <button name="${a.id}" type="submit" data-toggle="modal" data-target="#modal-id-repost" class="btn btn-default btn-repost-info"><span class="glyphicon glyphicon-retweet"></span>
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

<script>

    //        1 можно репостить .. этот пост нужно проверить мб я его уже репостнул
    //        2. это мой репост, можно удалить репост
    //        3. не мой репост. Но я могу сделать тоже репост этой записи  .. этот пост нужно проверить мб я его уже репостнул
    //        4. это мой пост.
    //        5. это мой пост который репостнули
    //        6. Этот пост можно репостить, но после проверки оказалось что я его уже репостил. Можно удалить репост
    //        7. не мой репост. Но я могу сделать тоже репост этой записи, после проверки оказалось что я уже его репостил. можно удалить

    $(document).ready(function () {
        $(".tweet-tags").css("color", "#84cbff");

        $(".btn-repost-info").click(function () {
            var div = $("#modal-id-repost-content");
            $.ajax({
                url:"/feed/" + this.name + "/info-post",
                type:"GET",
                success:function (data) {
                    if (data === 1) {

                    } if (data === 2){

                    } if (data === 3) {

                    } if (data === 4) {

                    } if (data === 5) {

                    } if (data === 6) {

                    } if (data === 7) {

                    }

                    div.html(data);
                },
                timeout:30000
            })
        })
//        $("#btn-tweet-post").click(function postTweet() {
//            var content = $("#input-tweet").val();
//            var maxTweetLength = 10000;
//            if (content.length <=0 || content.length > maxTweetLength)
//                return false;
//
//            $("#tweet-post").hide();
//
//
//            var json = JSON.stringify({"feedData":{"content":content}});
//            console.log(json);
//            $.ajax({
//                url:"/feed/",
//                type:"POST",
//                data:json,
//                contentType:"application/json; charset=UTF-8",
//                success:function (data) {
//                    if (data) {
//
//                    }
//                    $("#input-tweet").val("");
//                    $("#tweet-post").fadeToggle("slow");
//                },
//                error:function () {
//                    alert('Sorry, server is not responded');
//                },
//                timeout:30000
//            })
//            return false;
//        });
    })











    function ajaxTweetModal(a) {
        setTimeout(function () {
            $.ajax({
                url:"/feed/" + a.id,
                type:"GET",
                success:function (data) {
                    $("#modal-tweet-content").html(data.feedData.content);
                    $("#tweet-modal-title").html(data.sender.firstName + ' ' + data.sender.lastName + ' <br/>' + convertDate(data.date));
                },
                timeout:30000
            })
        }, 1000)
    }

    function clearModal() {
        $("#modal-tweet-content").html('<h1 class="text-center"><img class="preloader-modal-tweet" src="/resources/page-preloader.gif"></h1>');
        $("#tweet-modal-title").html("");
    }

    function convertDate(dateTime) {
        var date = new Date(dateTime);
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var hours = date.getHours();
        var minutes = date.getMinutes();
        var seconds = date.getSeconds();

        if (month >= 1 && month <= 9)
            month = '0' + month;
        if (hours >= 1 && hours <= 9)
            hours = '0' + hours;
        if (minutes >= 1 && minutes <= 9)
            minutes = '0' + minutes;
        if (seconds >= 1 && seconds <= 9)
            seconds = '0' + seconds;

        return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
    }





    function generateTweet() {

    }

    function templateTweet(data) {

    }



</script>
</body>
</html>
