<%--
  Created by IntelliJ IDEA.
  User: Infant
  Date: 08.07.2017
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Publication</title>
</head>
<body>
<%@include file="../index/header.jsp" %>



    <div id="content">

        <div class="col-lg-10 col-lg-offset-2  block-background ">
            <div id="category-publication" class="col-lg-5">
                <label>Category list:</label>
                <select class="form-control" id="category">
                    <c:forEach var="a" items="${category}">
                        <option value="${a.name()}">${a.name()}</option>
                    </c:forEach>
                </select>
            </div>

            <div id="content-publication-theme" class="col-lg-12 ">
                <label>Subject:</label>
                <textarea style="width: 100%" class="form-control" rows="5"></textarea>
            </div>
            <div id="content-publication-textarea" class="col-lg-12 ">
                <label>Content: <span id="length-publication">${lengthContent}</span></label>
                <textarea style="width: 100%" id="publication-text" class="form-control" maxlength="${lengthContent}" rows="22"></textarea>
                <br/>
                <button data-toggle="modal" data-target="#modal-id" class="btn btn-primary btn-lg pull-right" style="width: 20%">
                    Post
                    <span class="glyphicon glyphicon-pushpin"></span>
                </button>
            </div>

        </div>

        <div class="modal fade" id="modal-id">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" style="color: black">Are you sure ?</h4>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-success">Post</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

    </div>



<script>

    var lengthContent = ${lengthContent};


    $(document).ready(function () {
        $(".pagination-btn").click(function () {
            var id = this.name;
            var ul = $("#pagination-content ul[data-page=" + id + "]");
            $(".pagination-content").addClass("hidden");
            ul.removeClass("hidden");
        })
        $("#publication-text").keyup(function () {
            var c = $("#publication-text").val().length;
            $("#length-publication").html(lengthContent - c);
        })


    })


</script>





</body>
</html>
