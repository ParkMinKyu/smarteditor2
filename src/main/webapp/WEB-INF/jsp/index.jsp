<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>통합 홈페이지 버그잡는 페이지</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
	<c:if test="${empty sessionScope.writer }">
		<ul class="nav nav-tabs">
		  <li role="presentation" class="active"><a href="#">Login</a></li>
		</ul>
		<form method="post">
		  <div class="form-group">
		    <label for="writer">Name</label>
		    <input type="text" class="form-control" id="writer" name="writer" placeholder="Name">
		  </div>
		  <div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong id="alertMsg"></strong> 
			</div>
		  <button type="button" id="saveBtn" class="btn btn-default">저장</button>
		</form>
	</c:if>
	<c:if test="${!empty sessionScope.writer }">
		<ul class="nav nav-tabs">
		  <li role="presentation" class="active"><a href="#">Bug 등록</a></li>
		</ul>
		<div class="alert alert-info" role="alert">
			  <strong>${writer }</strong> Login 
		</div>
		<div id="menus">
		<select class="form-control" id="parentMenu">
			<c:forEach items="${menus }" var="menu">
				<option value="${menu.id }">${menu.menuName }
			</c:forEach>
		</select>
		</div>
		<button type="button" class="btn btn-default" id="searchMenu">하위 메뉴 검색</button>
		<textarea name="ir1" id="ir1" rows="20" cols="130"></textarea>
		<button type="button" id="writeBtn" class="btn btn-default">저장</button>
	</c:if>
</div>

<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/se2/js/HuskyEZCreator.js"/>" charset="utf-8"></script>
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "ir1",
    sSkinURI: "<c:url value="/resources/se2/SmartEditor2Skin.html"/>",
    fCreator: "createSEditor2"
});
$(function(){
	$('.alert-warning').hide();

	$('#searchMenu').on('click',function(){
		$.ajax({
			url : '<c:url value="/getMenus"/>'
		});
	});
	
	$('#saveBtn').on('click',function(){
		if($('#writer').val() == ''){
			$('#alertMsg').text('이름을 입력하세요.');
			$('#writer').focus();
			$('.alert-warning').show();
			return;
		}

		$('form').submit();
	});
	
	$('#writeBtn').on('click',function(){
		oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
		// 에디터의 내용에 대한 값 검증은 이곳에서
		// document.getElementById("ir1").value를 이용해서 처리한다.
		try {
			console.log($(("#ir1")).val());
			elClickedObj.form.submit();
		} catch(e) {}
	});
});
</script>
</body>
</html>