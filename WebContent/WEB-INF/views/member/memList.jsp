<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>MEMBER LIST</title>
</head>

<center><a>ȸ�� LIST</a></center>

<script language="javaScript">
function Secrhcheck(){ 
		
	if(!document.SearchForm.keyWord)
		alert("�˻��ɼ��� �������ּ���");
		return false;
	}
</script>


<form name="SearchForm" method="post" onsubmit="Searchcheck()" action="memList.mw">


<select name="keyField">
<option value="name">
<option value="id">
</select>


<input type="text" name="keyWord">
<input type="submit" value="Ȯ��">
</form>

<body>
<table border="1" cellpadding="0" cellspacing="0" align="center">
<tr>
	<td align="center" width="50">ID</td>
	<td align="center" width="50">�̸�</td>
	<td align="center" width="50">����</td>
	<td align="center" width="50">�������</td>	
	<td align="center" width="50">�޴�����ȣ</td>
</tr>

<c:forEach var="list" items="${list}">
<tr height="30" align="center">
	<td align="center" width="40">${list.id}</td>
	<td align="center" width="40">${list.name}</td>
	<td align="center" width="40">${list.gender}</td>
	<td align="center" width="50">${list.birth_y}-${list.birth_m}-${list.birth_d}</td>
	<td align="center" width="50">${list.phone1}-${list.phone2}-${list.phone3}</td>
</tr>
	
</c:forEach>
</body>
</table>
</html>