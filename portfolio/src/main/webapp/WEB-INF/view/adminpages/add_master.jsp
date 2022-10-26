<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    <script src="./jq/jquery.js"></script>
    <script src="./fns/signfns.js"></script>
</head>
<body>
    <header class="admin_title_add">
        <p><img src="./img/logo.png" class="logo_sm"> ADMINISTRATOR ADD</p>
    </header>

    <section class="admin_bgcolor_add">
    <form name="a_frm" id="a_frm" >
        <div class="admin_login_add">
            <ul>
                <li class="font_color1">아이디 및 패스워드 정보</li>
                <li>
                <input type="text" class="add_input1" name="a_id" id="a_id" placeholder="생성할 관리자 아이디를 입력하세요">
                <button type="button"  class="btn_button" id="c_overlap">중복체크</button>
                </li>
                <li>
                    <input type="password" class="add_input1" name="a_pw" id="pw1" placeholder="접속할 패스워드를 입력하세요">
                    <input type="password" class="add_input1" id="pw2" placeholder="동일한 패스워드를 입력하세요">
                </li>
                <li class="font_color1">관리자 기본정보 입력</li>
                <li>
                    <input type="text" class="add_input1" name="a_name" placeholder="담당자 이름을 입력하세요">
                </li>
                <li>
                <input type="text" class="add_input1 emails" name="a_mail" placeholder="담당자 이메일을 입력하세요">
                </li>
                <li class="font_color1">
                <input type="text" class="add_input1 hp1" name="a_tel" placeholder="HP" value="010" maxlength="3">
                -
                <input type="text" class="add_input1 hp2" name="a_tel" placeholder="1234" maxlength="4">
                -
                <input type="text" class="add_input1 hp2" name="a_tel" placeholder="5678" maxlength="4">
                </li>
                <li class="font_color1">관리자 담당부서 및 직책</li>
                <li>
                    <select class="add_select1" name="a_part">
                        <option value="-1">담당자 부서를 선택하세요</option>
                        <option value="0">임원</option>
                        <option value="1">전산팀</option>
                        <option value="2">디자인팀</option>
                        <option value="3">CS팀</option>
                        <option value="4">MD팀</option>
                    </select>
                    <select class="add_select1" name="a_grade">
                        <option value="-1">담당자 직책을 선택하세요</option>
                        <option value="0">대표</option>
                        <option value="1">부장</option>
                        <option value="2">팀장</option>
                        <option value="3">과장</option>
                        <option value="4">대리</option>
                        <option value="5">사원</option>
                    </select>
                </li>
                <li class="font_color1">※ 가입완료 후 전산 담당자가 확인 후 로그인 할 수 있습니다.</li>
            </ul>
            <span class="admin_addbtn" >
                <button type="button" class="btn_button btncolor1" title="관리자 등록" onclick="idsubmit()">관리자 등록</button>
                <button type="button" class="btn_button btncolor2" title="관리자 취소" onclick="backtomain()">등록 취소</button>
            </span>
        </div>
    </form>
    </section>
    <footer class="admin_copy">
        <div>
            Copyright ⓒ 2022 webshopping All rights reserved.
        </div>
    </footer>
</body>
</html>