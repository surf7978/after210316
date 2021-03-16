<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <!--Load the AJAX API-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', '부서');
        data.addColumn('number', '인원수');
        /*
        ajax이용해서 이렇게 만들기
        data.addRows([
          ['개발', 3],
          ['인사', 1],
          ['기획', 2]
        ]);
        */
        var arr = [];
        $.ajax({
        	url : 'getChartData',
        	//근데 ajax 디폴트가 비동기라 이거먼저 실행될수도있으니까 ajax는 동기식으로 해줘야함
        	async : false,
        	dataType : 'json',
        	success : function(result){
        		//서버에서 받아온 데이터[{},{}] >>> [[],[]]로 바꿈
        			console.log(result)
        		for(o of result){ // result 갯수만큼 for문 돌리고 {}를 []바꾸기
        			//arr.push([o.name, parseInt(o.cnt)]);
        			arr.push([o.saleDate, o.totalPrice]);
        		}
        	}
        });
        data.addRows(arr);

        // Set chart options
        var options = {'title':'부서별 인원수',
                       'width':400,
                       'height':300,
                       //옵션 포맷 추가가능 구글차트api >>> Axis options 에서 검색
                       vAxis : {format:"#,###"},
        			   gridlines:{count:10}               
        };

        // ColumnChart 이거 바꾸면 그래프 종류 바뀜
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
  </head>

  <body>
    <!--Div that will hold the pie chart-->
    <div id="chart_div"></div>
  </body>
</html>