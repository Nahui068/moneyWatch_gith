<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <html>
     <head><title>로그인</title>
      <script language="JavaScript">
      
      function begin(){
          document.myform.id.focus();
         }
      
      function checkIt(){
         if(!document.myform.id.value){
        	alert("아이디를 입력하지 않으셨습니다.");
          	document.myform.id.focus();
          	return false;
          	}
         if(!document.myform.pw.value){
          	alert("비밀번호를 입력하지 않으셨습니다.");
          	document.myform.pw.focus();
         	return false;
          	}
         }
      
        
      </script>  
      
     </head>
    <body onload="begin()">
     <form name="myform" action="/moneyWatch/loginPro.mw" method="post" onSubmit="return checkIt()">
      <table cellSpacing="1" cellPadding="1" width="250" border="1" align="center">  
      
       <tr height="30">
        <td colspan="2" align="middle"><strong>회원로그인</strong></td></tr>
       
       <tr height="30">
        <td width="110" align="center">아이디</td>
        <td width="150" align=center>
         <input type="text" name="id" size="15" maxlength="12"></td></tr>
       
       <tr height="30">
        <td width="110" align="center">비밀번호</td>
        <td width="150" align="center">
         <input type="password" name="pw" size="15" maxlength="12"></td></tr>
       
       <tr height="30">
        <td colspan="2" align="middle">
         <input type="submit" value="로그인">
         <input type="reset" value="다시입력">
         <input type="button" value="회윈가입" onclick="javascript:window.location='/moneyWatch/registerForm.mw'"></td></tr>             
      </table>
     </form>
    </body>  
   </html>