<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- 제이쿼리 ui -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui-touch-punch/0.2.3/jquery.ui.touch-punch.min.js"></script>
    <title>Document</title>
    <style>
        * {
            margin: 0px;
            padding: 0px;
            text-decoration: none;
            font-family: sans-serif;

        }

        body {
            background: linear-gradient(125deg, #050000, #313164, #3e7c7c);
            background-color: black;
            color: white;
        }

        .loginForm {
            position: absolute;
            width: 500px;
            height: 400px;
            padding: 30px, 20px;
            text-align: center;
            top: 60%;
            left: 50%;
            transform: translate(-50%, -70%);
            border-radius: 15px;
        }

        .loginForm h2 {
            text-align: center;
            margin: 30px;
        }

        .join-input {
            border: 2px solid #adadad;
            margin: 50px;
            padding: 10px 10px;
        }

        .input-style {
            width: 100%;
            border: none;
            outline: none;
            color: #636e72;
            font-size: 16px;
            height: 25px;
            background: none;
            display: inline-block;
        }

        .btn-row {
            position: relative;
            left: -20%;
        }

        .btn {
            position: relative;
            left: 40%;
            transform: translateX(-50%);
            margin-bottom: 40px;
            width: 40%;
            height: 40px;
            background: linear-gradient(125deg, #3a6464, #5d5885, #181a1a);
            background-position: left;
            background-size: 200%;
            color: white;
            font-weight: bold;
            border: none;
            cursor: pointer;
            transition: 0.4s;
            display: inline;
        }

        .btn:hover {
            background-position: right;
        }

        .bottomText {
            text-align: center;
        }
    </style>
</head>

<body>
<form action="${pageContext.request.contextPath}/login_proc" method="post" class="loginForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <h2>Login</h2>
    <div class="join-input">
        <input type="text" name="userId" class="id input-style" placeholder="아이디를 입력해주세요">
    </div>
    <div class="join-input">
        <input type="password" name="password" class="password input-style" placeholder="패스워드를 입력해주세요">
    </div>
    <div>
        <c:if test="${exceptionMessage != null}">
            <p> Error : <c:out value="${exceptionMessage}"/></p>
        </c:if>
    </div>
    <div class="btn-row">
        <button type="button" class="btn" onclick="login()">
            로그인
        </button>
        <button type="button" class="btn" onclick="join()">
            회원가입
        </button>
    </div>
</form>

<script>
    function login() {
        document.querySelector("form").submit();
    }

    function join() {
        location.href = "/join";
    }
</script>
</body>

</html>