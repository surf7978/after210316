<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getBoard</title>
</head>
<body>
<h3>게시글 상세보기</h3>
번호 : ${board.seq }<br>
제목 : ${board.title }<br>
작성자 : ${board.writer }<br>
내용 : ${board.content }<br>
첨부파일 단건 : <a href="fileDown?seq=${board.seq }">${board.filename }</a><br>
첨부파일 다건 :
<c:forTokens items="${board.filename}" delims="," var="file"><!-- delims="," = 구분기호 어떤건지 입력한거임 -->
	<a href="fileNameDown?filename=${file}">${file}</a><br>
</c:forTokens> 
<a href="../temp">홈으로</a><br>
<!-- 1.일괄다운받기 기능 만들기
	 2.JSON String 변환 유틸파일 만들기
	 3.게시판 전체조회 수정 만들기 +페이징, 제목 or 내용검색 
	 4. 일별 년도별 월별 판매 합계 쿼리 : to_char +emp-mapper에 추가하기
		 SALE_SEQ	NUMBER	No
		 SALE_DATE	DATE	Yes
		 PRODUCT_ID	NUMBER	Yes
		 SALE_QTY	NUMBER	Yes
		 SALE_PRICE	NUMBER	Yes
	 	 MEMBER_ID	VARCHAR2(12 BYTE)	Yes
	 	 	3001	18/09/04	1004	4	12000	홍길동
			30002	18/09/05	1003	2	5000	김유신
			30003	18/09/05	1001	1	4000	이기자
			30004	18/09/06	1002	1	35000	이순신
			30005	18/09/06	1001	4	12000	홍길동
			30006	18/09/06	1002	3	10500	김유신
			30007	18/09/07	1003	2	5000	이순신
			30008	18/09/07	1001	5	20000	이기자
			30009	18/09/07	1002	4	14000	김유신
			30010	18/09/07	1003	4	10000	홍길동
			
			select sum(sale_price), sale_date
			from sale_tbl_10
			group by sale_date;

			select sum(sale_price), to_char(sale_date, 'yyyy')
			from sale_tbl_10
			group by to_char(sale_date, 'yyyy');
			
			select sum(sale_price), to_char(sale_date, 'yyyy-MM')
			from sale_tbl_10
			group by to_char(sale_date, 'yyyy-MM');
 -->
<a href="">일괄다운받기(zip)</a>
</body>
</html>