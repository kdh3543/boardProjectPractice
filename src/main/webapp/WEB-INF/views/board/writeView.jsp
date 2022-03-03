<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>

    <head>
      <script src="${pageContext.request.contextPath}/resources/js/summernote/summernote-lite.js"></script>
      <script src="${pageContext.request.contextPath}/resources/js/summernote/lang/summernote-ko-KR.js"></script>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/summernote/summernote-lite.css">
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="X-UA-Compatible" content="ie=edge">

      <title>게시판 글 작성</title>
      <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    </head>

    <body>
      <c:choose>
        <c:when test="${id==dto.writer}">
          <form id="frm" action="/board/modSuccess" method="post">
            <table border=1 align=center>
              <tr>
                <th colspan=2>글 작성<input type="hidden" value="${dto.seq}" name="seq">
              </tr>
              <tr>
                <td>제목 :
                <td><input type=text value=${dto.title } readonly id="title" name="title">
              </tr>
              <tr>
                <td>글 작성 :
                <td><textarea name="contents" class="summernote" id="contents" cols="100" rows="20"
                    readonly>${dto.contents } </textarea>
              </tr>
              <tr>
                <td colspan=2 align=center>
                  <input type="button" value="수정" id="modBtn">
                  <button id="modSuccessBtn" style="display: none;">수정완료</button>
                  <input type="button" value="취소" id="modCancel" style="display: none;">
                  <input type="button" value="삭제" id="delBtn">
                  <input type="button" value="뒤로 가기" id="backBtn">
                </td>
              </tr>
            </table>
          </form>
        </c:when>
        <c:otherwise>
          <table border=1 align=center>
            <tr>
              <th colspan=2>게시글
            </tr>
            <tr>
              <td>제목 :
              <td><input type=text value=${dto.title } readonly>
            </tr>
            <tr>
              <td>글 작성 :
              <td><textarea name="contents" class="summernote" id="contents" cols="100" rows="20"
                  readonly>${dto.contents } </textarea>
            </tr>
            <tr>
              <td colspan=2 align=center>
                <input type="button" value="뒤로 가기" id="backBtn">
              </td>
            </tr>
          </table>
        </c:otherwise>
      </c:choose>
      <script>
        $(document).ready(function () {
          $(".summernote").summernote({
            height: 300,
            lang: "ko-KR"
          })
        });

        let title = $("#title");
        let contents = $("#contents");

        $("#backBtn").on("click", function () {
          history.back();
        });
        $("#modBtn").on("click", function () {
          $("#delBtn").hide();
          $("#modBtn").hide();
          $("#backBtn").hide();
          $("#modSuccessBtn").show();
          $("#modCancel").show();
          title.attr("readonly", false);
          contents.attr("readonly", false);
        });

        $("#modCancel").on("click", function () {
          $("#delBtn").show();
          $("#modBtn").show();
          $("#backBtn").show();
          $("#modSuccessBtn").hide();
          $("#modCancel").hide();
          title.val("${dto.title}");
          title.attr("readonly", true);

          contents. val("${dto.contents}");
          contents.attr("readonly", true);
      		
        });

        $("#delBtn").on("click", function () {
          if (confirm("게시글을 삭제하시겠습니까?")) {
            location.href = "/board/deleteBoard?seq=${dto.seq}";
          }
        });

        $("#frm").on("submit", function () {
          if (title.val() == "") {
            alert("제목을 입력하세요.");
            return false;
          } else if (contents.val() == "") {
            alert("글을 입력하세요.")
            return false;
          }
        });
      </script>
    </body>

    </html>