<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<h3>게시글 상세보기</h3>
	제목 ${board.title}
	<p>
		내용 ${board.content}
		<!-- 컨트롤러에서 model의 board값을 받아왔다.  -->
	<hr>
	<!--hr 은 줄긋는거네.... -->
	<h3>댓글</h3>
	<div id="reply"></div>
	<form name="replyFrm">
		<input name="content">
		<button type="button" id="btnAdd">댓글등록</button>
	</form>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script>
	//댓글목록조회
	$.ajax({
		url: "getReplyList",
		data: {board_seq:${board.seq} }, //"board_seq=${board.seq}"
		dataType: "json",
		success: function(response){
			for(i=0; i<response.length; i++) {
				$("#reply").append(response[i].content+"<br>");
			}
		}
		
	});
	//댓글등록
	$("#btnAdd").on("click", function(){
		//등록 ajax처리하고 등록된 댓글을 div에 append
		$.ajax({
			url: "insertReply",
			data: {board_seq:${board.seq}, content :$("[name=content]").val() },
				// --> ?board_seq=1&content=dddd 이런식으로 나온다
			dataType: "json", 
			success : function(response){
				$("#reply").append(response.content+"<br>");
			}
		
		});
		
		
	});
	
</script>
</body>
</html>