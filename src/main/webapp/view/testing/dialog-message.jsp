<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 22.05.2017
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Title</title>
    <style>
        .block-sms {
            height: 500px;
            width: 750px;
            overflow: scroll;
            word-break:break-all;
            overflow-x:hidden;
        }
    </style>


</head>
<body>
<%@include file="../index/header.jsp" %>

<div id="div-message-dialog" class="col-lg-6 col-lg-offset-1 col-md-6 col-md-offset-1">

    <h4 class="text-primary">
        <span class="glyphicon glyphicon-th-list"></span>
        Dialog
        <a href="/user/id${authUser.getId()}">${authUser.getFirstName()} ${authUser.getLastName()}</a>
    </h4>

    <div class="col-md-12 col-sm-10 block-sms">



        <table class="table table-hover">
            <thead>
            <tr>
                <td></td>
                <td></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="a" items="${list}">
                <tr>
                    <td style="width: 2%">
                        <img data-src="holder.js/140x140" class="img-circle center-block" style="width: 100px; height: 100px;"
                             src="http://www.isu.edu.tw/upload/276e/9/coming-soon.jpg" data-holder-rendered="true">
                    </td>
                    <td style="width: 10%">
                        <li class="link">
                            <a href="#" onclick="getMessage('${a.dialog.second.id}',
                                    '${a.dialog.second.firstName}', '${a.dialog.second.lastName}')">
                                <p>${a.dialog.second.firstName} ${a.dialog.second.lastName}</p>
                                <hr/>
                                <p id="${a.sender.id}" class="text-muted">${a.sender.firstName} ${a.sender.lastName} send:
                                    <br/> ${a.message}</p>
                                <hr/>
                            </a>
                        </li>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div style="display: none;" id="div-message" class="col-lg-7 col-lg-offset-1">
    <div id="user-second">

    </div>

    <div id="div-tbody-message" class="form-group block-sms scroll-down">

        <table class="table table-hover">
            <thead>
            <tr>
                <td></td>
                <td></td>
            </tr>
            </thead>
            <tbody id="tbodyMessage">

            </tbody>
        </table>
    </div>

    <form style="width: 750px" id="formMessage">
        <div class="form-group">
            <textarea id="inputMessage" placeholder="write message............" name="message" class="form-control" style="height: 100px; width: 100%;" rows="5" ></textarea>
        </div>

        <div style="width: 100%;">
            <button style="width: 100%;" form="formMessage" class="btn btn-info">
                Send <span class="glyphicon glyphicon-ok"/>
            </button>
        </div>
    </form>
</div>


<script>





    function getMessage(sel, selFirstName, selLastName) {
        $("#div-message-dialog").hide();
        $.ajax({
            url: "/im/im/${authUser.getId()}/"+sel,
            type:"GET",
            contentType:"application/json; charset=UTF-8",
            dataType: "json",
            success:function (data) {
                createTableMessage(data, sel, selFirstName, selLastName);
                $("#div-message").fadeToggle("slow");
            },
            complete: function () {
                scrollDown();
            }
        });
    }

    function returnToDialog() {
        $("#div-message").hide();
        $("#div-message-dialog").fadeToggle("slow");
    }

    function createTableMessage(data, sel, selFirstName, selLastName) {
        if (data) {
            var returnToDialog = ' <a href="#" onclick="returnToDialog()" id="return-dialog" class="glyphicon glyphicon-circle-arrow-left"> Return to dialog</a>' +
                '<br/><br/>';

            var hrefSel = returnToDialog +  '<a href="/user/id' + sel + '">' +
                    '<h1 class="text-center text-primary">' + selFirstName + ' ' + selLastName + '</h1></a><hr/>'
            $("#user-second").html(hrefSel);

            var jsonData = data;
            var result;
            for (var i = jsonData.length - 1; i >= 0; i--) {
                var element = jsonData[i];
                var user = element.sender;
                var table = ' <tr><td><p><a href="/user/id' + user.userId + '">'
                    + user.firstName +
                    ' ' + user.lastName +
                    '</a>' +
                    '</p> ' +
                    '<p>' + element.message + '</p> ' +
                    '</td> </tr>';
                result = result + table;
            }
//            $("#scroll #tbodyMessage")
            $("#div-tbody-message #tbodyMessage")
                .html(result);
        }
    }

    function scrollDown() {
        var div = $(".scroll-down");
        div.scrollTop(div.prop('scrollHeight'));
    }


</script>



</body>
</html>
