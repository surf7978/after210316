<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>자료실</h3>
	<form action="insertBoard" method="post"
		encType="multipart/form-data">
		작성자<input type="text" name="writer"><br /> 
		제목<input	type="text" name="title"><br /> 
		내용	<textarea name="content"></textarea><br /> 
		첨부파일<input type="file" name="uploadFile" multiple="multiple" /><br />
		<input	type="submit" value="저장">
	</form>
</body>
<!-- 
SEQ	NUMBER	No
TITLE	VARCHAR2(100 BYTE)	Yes
WRITER	VARCHAR2(20 BYTE)	Yes
CONTENT	VARCHAR2(4000 BYTE)	Yes
FILENAME	VARCHAR2(100 BYTE)	Yes
 -->
</html>