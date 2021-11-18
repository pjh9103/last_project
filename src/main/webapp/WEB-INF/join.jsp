<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
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
            <input type="text" id="userId" name="userId" class="input-style" placeholder="아이디를 입력해주세요">
        </div>
        <div class="join-input">
            <input type="password" id="password" name="password" class="input-style" placeholder="패스워드를 입력해주세요">
        </div>
        <div class="join-input">
            <input type="email" id="birth" name ="birth" class="input-style" placeholder="생일을입력해주세요">
        </div>
        <div class="join-input">
            <input type="phone" id="phone" name = phone class="input-style" placeholder="휴대폰번호를 입력해주세요">
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
            validation();
        }

        function validation() {
            let userId = document.querySelector('#userId').value;
            if(!userId) {
                alert("아이디를 입력해주세요")
                return
            }
            let password = document.querySelector('#password').value;
            if(!password) {
                alert("비밀번호를 입력해주세요")
                return
            }
            let passwordLength = password.length;
            if( passwordLength < 8 || passwordLength > 50 ) {
                alert("비밀번호는 8자리 이상 50자리 이하이어야합니다.")
                return
            }

            let birth = document.querySelector('#birth').value;
            if(!birth){
                alert("생일을 입력해주세요")
                return
            }

            let birthFormat = /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
            let replaceBirth = birth.replaceAll(" ","");
            if(!birthFormat.test(replaceBirth)){
                alert("생년월일은 ex 2021-09-21 이어야합니다.")
                return
            }
            let phone = document.querySelector('#phone').value;
            if(!phone) {
                alert("휴대폰 번호를 입력해주세요")
                return
            }

            let phoneFormat = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
            if(!phoneFormat.test(phone)){
                alert("휴대폰 번호는 010-xxxx-xxxx 이어야합니다.")
                return
            }

            document.querySelector(".join-form").submit();
        }

        function cancel() {
            let referrer = document.referrer;
            location.href=referrer;
        }
        </script>
    </form>
</body>