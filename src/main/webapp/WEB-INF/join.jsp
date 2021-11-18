<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        * {
            margin: 0px;
            padding: 0px;
            text-decoration: none;
            font-family: sans-serif;

        }

        body {
            background: linear-gradient(125deg, #976b6b, #9a9ab1, #96dbdb);
            background-color: black;
            color: white;
        }

        .join-form {
            position: absolute;
            width: 500px;
            height: 400px;
            padding: 30px, 20px;
            text-align: center;
            top: 48%;
            left: 50%;
            transform: translate(-50%, -70%);
            border-radius: 15px;
        }

        .join-form h2 {
            text-align: center;
            margin: 30px;
        }

        .join-input {
            border: 2px solid #adadad;
            margin-left: 50px;
            margin-right: 50px;
            margin-bottom: 30px;
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
    <form action="/join" method="post" class="join-form">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <h2>회원가입</h2>
        <div class="join-input">
            <input type="text" name="userId" class="input-style" placeholder="아이디를 입력해주세요">
        </div>
        <div class="join-input">
            <input type="password"  name="password" class="input-style" placeholder="패스워드를 입력해주세요">
        </div>
        <div class="join-input">
            <input type="password"  name="twopassword"class="input-style" placeholder="패스워드를 재 입력해주세요">
        </div>
        <div class="join-input">
            <input type="email" name ="birth" class="input-style" placeholder="생일을입력해주세요">
        </div>
        <div class="join-input">
            <input type="phone" name = phone class="input-style" placeholder="휴대폰번호를 입력해주세요">
        </div>
        <div class="btn-row">
            <button type="button" class="btn" onclick="button()">
                가입하기
            </button>
            <button type="button" class="btn" onclick="cancel()">
                취소하기
            </button>
        </div>
        <script>
        function button() {
        	document.querySelector(".join-form").submit();
        }
        function cancel() {
            let referrer = document.referrer;
            location.href=referrer;
        }
        </script>
    </form>
</body>