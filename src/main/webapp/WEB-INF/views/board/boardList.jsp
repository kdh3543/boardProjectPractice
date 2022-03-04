<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="X-UA-Compatible" content="ie=edge">
      <title>게시판 리스트</title>
      <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
      <style>
        * {
          box-sizing: border-box;
        }

        

        #board {
          width: 700px;
          height: 1000px;
          margin: auto;
        }

        #boardHeader {
          width: 100%;
          height: 10%;
        }

        #headerTitle{
          width: 100%;
          height: 70%;
          text-align: center;
          font-size: 3rem;
        }

        #searchBar{
          width: 100%;
          height: 30%;
        }

        #searchBar>div{
          float: left;
          height: 100%;
        }

        #searchIndex{
          width: 10%;
        }

        #searchText{
          width: 70%;
        }

        #searchBtn{
          width: 20%;
        }

        #textSize, #btnSize{
          width: 100%;
          height: 100%;
        }

        #boardMiddle {
          width: 100%;
          height: 80%;
        }

        #boardFooter {
          width: 100%;
          height: 10%;
        }

        #middleHeader {
          width: 100%;
          height: 5%;
          text-align: center;
          line-height: 40px;
          border-bottom: 3px solid black;
        }

        #middleHeader>div {
          float: left;
          height: 100%;
        }

        #middleMain {
          width: 100%;
          height: 95%;
          text-align: center;
          overflow-y: auto;
          
        }

        #middleNo {
          width: 10%;
        }

        #middleTitle {
          width: 30%;
        }

        #middleWriter {
          width: 20%;
        }

        #middleDate {
          width: 30%;
        }

        #middleCount {
          width: 10%;
        }

        #middleMainContainer{
          width: 100%;
          height: 70px;
          line-height: 70px;
          border-bottom: 1px solid black;
        }

        #middleMainContainer>div{
          float: left;
          height: 100%;
        }

        #pageNavi{
          width: 100%;
          height: 30%;
          text-align: center;
          line-height: 30px;
        }

        #footerButton {
          width: 100%;
          height: 70%;
          display: flex;
          align-items: center;
          justify-content: center;
        }

        #footerButton>input[type=button] {
          width: 50%;
          height: 100%;
        }

        #footerButton>input[type=button]:hover {
          cursor: pointer;
        }

        a, a:hover{
          color: black; 
          text-decoration: none;
        }
      </style>
    </head>

    <body>
      <div id="board">
        <div id=boardHeader>
          <div id="headerTitle">게시판</div>
          <div id="searchBar">
            <div id="searchIndex">
              <select name="name" id="select">
                <option value="검색" id="option">검색</option>
                <option value="제목" id="option">제목</option>
                <option value="글쓴이" id="option">글쓴이</option>
              </select>
            </div>
            <div id="searchText">
              <input type="text" placeholder="검색하실 단어를 입력하세요." id="textSize">
            </div>
            <div id="searchBtn">
              <input type="button" value="검색" id="btnSize" class="search">
            </div>
          </div>
        </div>
        <div id=boardMiddle>
          <div id="middleHeader">
            <div id="middleNo">No.</div>
            <div id="middleTitle">제목</div>
            <div id="middleWriter">글쓴이</div>
            <div id="middleDate">날짜</div>
            <div id="middleCount">조회수</div>
          </div>
          <div id="middleMain">
            <c:forEach var="i" items="${list}">
              <div id="middleMainContainer">
                <div id="middleNo">${i.seq}</div>
                <div id="middleTitle"><a href="/board/boardContents?seq=${i.seq}">${i.title}</a></div>
                <div id="middleWriter">${i.writer}</div>
                <div id="middleDate">${i.write_date}</div>
                <div id="middleCount">${i.view_count}</div>
              </div>
            </c:forEach>
          </div>
        </div>
        <div id=boardFooter>
          <div id="pageNavi">
            ${navi}
          </div>
          <div id="footerButton">
            <input type="button" value="글쓰기" id="writeBtn">
            <input type="button" value="뒤로가기" id="backBtn">
          </div>
        </div>
      </div>

      <script>
        $("#writeBtn").on("click", function () {
          location.href = "/board/writeForm";
        });
        $("#backBtn").on("click", function () {
          location.href = "/member/mainPage";
        })

        $(".search").on("click",function(){      
          if($("#textSize").val()=="") {
            alert("검색어를 입력해주세요.");
            location.href = "/board/boardList?cpage=1";
            return false;
          }else{
            location.href = "/board/searchResult?cpage=1&result="+$("#textSize").val()+"&option="+$("#select option:selected").val();
          }
          
        })
        $("#textSize").on("keyup",function(){
          if(window.event.keyCode==13){  
            if($("#textSize").val()=="") {
              alert("검색어를 입력해주세요.");
              location.href = "/board/boardList?cpage=1";
              return false;
            }else{
              location.href = "/board/searchResult?cpage=1&result="+$("#textSize").val()+"&option="+$("#select option:selected").val();
          }
          }
          
        })
      </script>
    </body>

    </html>