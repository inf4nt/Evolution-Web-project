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
                <label>Category list:  <p id="sending" style="font-size:xx-large; display:none;">Sending</p></label>
                <select class="form-control" id="category">
                    <c:forEach var="a" items="${category}">
                        <option value="${a.name()}">
                            ${a.name()}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div id="content-publication-theme" class="col-lg-12 ">
                <label>Subject: <span id="length-subject">${lengthSubject}</span></label>
                <textarea id="subject" style="width: 100%" class="form-control" rows="5"></textarea>
            </div>
            <div id="content-publication-textarea" class="col-lg-12 ">
                <label>Content: <span id="length-publication">${lengthContent}</span></label>
                <textarea style="width: 100%" id="publication-text" class="form-control" maxlength="${lengthContent}" rows="22"></textarea>
                <br/>
                <button data-toggle="modal" data-target="#modal-id" id="post-publication" class="btn btn-primary btn-lg pull-right" style="width: 20%">
                    Post
                    <span class="glyphicon glyphicon-pushpin"></span>
                </button>
                <p id="success-send" style="font-size:x-large; display:none;">Successful</p>
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
                        <button id="post-modal-publication" type="button" class="btn btn-success" data-dismiss="modal">Send</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

    </div>



<script>

    var lengthContent = ${lengthContent};
    var lengthSubject = ${lengthSubject};

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
        $("#subject").keyup(function () {
            var c = $("#subject").val().length;
            $("#length-subject").html(lengthSubject - c);
        })


        $("#post-publication").click(function () {
            var subject = $("#subject").val();
            var publication = $("#publication-text").val();
            var category = $("#category").val();
            if (publication.length > lengthContent || publication.length === 0 || subject.length > lengthSubject || subject.length === 0)
                return false;
            return true;
        })





        $("#post-modal-publication").click(function () {
            var subject = $("#subject").val();
            var publication = $("#publication-text").val();
            var category = $("#category").val();
            if (publication.length > lengthContent || publication.length === 0 || subject.length > lengthSubject || subject.length === 0)  {
                $("#modal-id").modal("hide");
                return false;
            }

            var json = JSON.stringify({"content":publication, "subject":subject});

            $("#sending").fadeToggle("slow");

            setTimeout(function () {
                $.ajax({
                    url:"/publication/?category=" + category,
                    type:"POST",
                    data: json,
                    contentType:"application/json; charset=UTF-8",
                    success:function (data) {
                        if (data !== null) {
                            $("#success-send").fadeToggle("slow").delay(3000).hide("slow");
                        }
                        $("#subject").val("");
                        $("#publication-text").val("");
                    },
                    error:function () {

                    },
                    timeout:30000
                })
                $("#sending").hide("slow");
            }, 2000);

        })
    })


</script>





</body>
</html>
