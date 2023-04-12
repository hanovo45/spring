<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <style>
        header {
            background-color: #e0e0e0;
            text-align: center;
            padding-top: 10px;
            height: 10%;
        }

        section {
            background-color: #d3d3d3;
            text-align: center;
            height: 40%;
        }

        header h2 {
            font-size: 36px;
        }

        menu {
            background-color: black;
            transform: scale(1.5);
        }

        menu ul {
            display: flex;
            justify-content: space-around;
            list-style-type: none;
            padding: 0;
        }

        menu ul li {
            color: white;
            padding: 10px;
        }

        section {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        section h4 {
            font-size: 24px;
        }

        section p {
            font-size: 18px;
        }

        footer {
            background-color: #e0e0e0;
            text-align: center;
            color: white;
            padding: 10px;
        }
        h4, p {
        display: block;
    }
    </style>
</head>

<body>
    <header>
        <h2>도서대여관리</h2>
    </header>
    <menu>
        <ul>
            <li>도서등록</li>
            <li>도서목록조회/수정</li>
            <li>대여현황조회</li>
            <li>홈으로</li>
        </ul>
    </menu>
    <section>        
        <p>국제 대여점의 도서등로그 대여정보 데이터베이스를 구축하고, 도서.DVD 대여관리를 위한 프로그램이다.</p>
    </section>
    <footer>
        <p>123123123123123</p>
    </footer>
</body>

</html>