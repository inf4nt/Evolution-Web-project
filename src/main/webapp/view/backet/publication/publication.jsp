<%--
  Created by IntelliJ IDEA.
  User: Infant
  Date: 09.07.2017
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Publication ${publication.subject}</title>
</head>
<body>
<%@include file="../../index/header.jsp" %>


    <div id="content" class="col-lg-10 col-lg-offset-2 block-background">
        <div>
            <a class="white" href="/user/id${publication.sender.id}">
                ${publication.sender.firstName} ${publication.sender.lastName}
            </a>
            <a class="white" href="#/">
                ${publication.date}
            </a>
            <c:if test="${publication.sender.id == authUser.id}">
                <div class="pull-right">
                    <button class="btn btn-lg btn-default">
                        <span class="glyphicon glyphicon-edit"></span>
                        Edit
                    </button>
                    <button data-toggle="modal" data-target="#modal-id" class="btn btn-lg btn-danger">
                        <span class="glyphicon glyphicon-remove"></span>
                        Delete
                    </button>
                </div>
            </c:if>
        </div>
        <div>
            <br/>
            <p style="font-size:x-large;" class="text-center">
                ${publication.subject}
            </p>
            <p>
                ${publication.categoryName}
            </p>
        </div>
        <div style="font-size:large">
            <br/><hr/>
            ${publication.content}
        </div>
    </div>


<div class="modal fade" id="modal-id">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <p class="modal-title" style="color: black">Are you sure?</p>
            </div>
            <div class="modal-footer">
                <button id="btn-delete-publication" type="button" class="btn btn-danger" data-dismiss="modal">Delete</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


</body>

<script>
    $(document).ready(function () {
        $(".white").css("color", "white");
        $("#btn-delete-publication").click(function () {
            $.ajax({
                url:"/publication/${publication.id}",
                type:"DELETE",
                success:function () {
                    $("#content").html("");
                },
                error:function () {
                    alert('Error');
                },
                timeout:30000
            })
        })



    })

</script>

</html>
