<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>

    <head>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
      <!-- include summernote css/js -->
      <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
      <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
      <!-- include summernote-ko-KR -->
      <script src="lang/summernote-ko-KR.js"></script>

      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
      <meta http-equiv="X-UA-Compatible" content="ie=edge">
      <title>게시판 글 작성</title>
      <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    </head>

    <body>
      <form id="frm" method="post" action="/board/writeSuccess">
        <table border=1 align=center>
          <tr>
            <th colspan=2>글 작성
          </tr>
          <tr>
            <td>제목 :
            <td><input type="text" placeholder="제목 입력" name="title" id="title">
          </tr>
          <tr>
            <td>글 작성 :
            <td><textarea name="contents" placeholder="글을 입력하세요." id="summernote" class="contents" cols="100"
                rows="20"></textarea>
          </tr>
          <tr>
            <td colspan=2 align=center>
              <button id="submit">글쓰기</button>
              <input type="reset" value="다시 작성">
              <input type="button" value="뒤로 가기" id="backBtn">
            </td>
          </tr>
        </table>
      </form>

      <script>
        $(document).ready(function () {
          $('#summernote').summernote({
            lang: 'ko-KR', 				 // default: 'en-US'
            height: 500,                 // set editor height
            minHeight: null,             // set minimum height of editor
            maxHeight: null,             // set maximum height of editor
            focus: true                  // set focus to editable area after initializing summernote
          });

        });


        let title = $("#title");
        let contents = $(".contents");

        $("#backBtn").on("click", function () {
          history.back();
        });
        $("#frm").on("submit", function () {
          if (title.val() == "") {
            alert("제목을 입력하세요.");
            return false;
          } else if (contents.val() == "") {
            alert("글을 입력하세요.")
            return false;
          }
        })
      </script>
    </body>

    </html>