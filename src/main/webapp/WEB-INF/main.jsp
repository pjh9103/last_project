<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <!-- lodash 플러그인 -->
    <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.20/lodash.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <title>Document</title>

    <style>
        div.stop-dragging
        {
        -ms-user-select: none; 
        -moz-user-select: -moz-none;
        -khtml-user-select: none;
        -webkit-user-select: none;
        user-select: none;
        }
                /* noramlize */
                body,
        ol,
        ul,
        li,
        a,
        h1,
        h2,
        h3,
        h4,
        h5,
        h6,
        button {
            padding: 0;
            margin: 0;
            border: 0;
            outline: none;
            list-style: none;
            text-decoration: none;
            color: inherit;
            background-color: transparent;
        }

        html,
        body {
            height: 100%;
            width: 100%;
            background-color: black;
            color: white;
        }

        .slide-area {
            background-color: rgb(90, 63, 63);
            width: 100%;
            height: 500px;
            margin-bottom: 50px;
        }

        .slide-area div {
            height: 500px;
        }

        .slide-area img {
            width: 100%;
            height: 500px;
            object-fit: cover;
        }

        .movie-area h2 {

            font-weight: bold;
            font-size: 25px;
        }

        .movie-area-t1 {
            display: flex;
            overflow-y: hidden;
            overflow-x: scroll;
            padding: 20px
        }

        .poster > .poster-img  {
            width: 100%;
            height: 100%;
            object-fit: cover;
            transition : transform 450ms;
        }

        .poster > .poster-img:hover {
            transform: scale(1.08);
            opacity: 1;
        }

        button {
            cursor: pointer;
        }

        /*custom*/
        .flex {
            display: flex;
        }

        .wrap {
            width: 100%;
            height: 50vh;
            border: 10px solid black;
            box-sizing: border-box;
        }

        .box {
            width: calc(100%/ 5);
            height: calc(100%/ 2);
        }

        /* slide css */
        .slide-wrap {
            overflow: hidden;
        }

        .slide-draggable {
            height: 100%;
        }

        .slide-draggable > .poster {
            position: relative;
            flex-shrink: 0;
            width:  300px;
            height: 400px;
            margin: 20px;
            object-fit:cover;
            overflow: hidden;
        }

        .slide-draggable > .poster > span {
            position: absolute;
            width: 50px;
            height: 50px;
            background-color: red;
            text-align: center;
            font-size: 3.2vh;
            right: 0;
        }
    </style>
</head>

<body class="stop-dragging">
    <div class="slide-area">
        <div><img src="https://img.newspim.com/news/2021/02/23/2102231353019420_w.jpg"/></div>
        <div><img src="https://img.khan.co.kr/news/2021/05/08/l_2021050701000790000061461.jpg"/></div>
        <div><img src="https://img.newspim.com/news/2021/02/23/2102231353019420_w.jpg"/></div>
    </div>
    <div class="movie-area">
        <div class='row'>
            <c:forEach  var="movie" varStatus="status" items="${movie}">
                <h2>${movie.key}</h2>
                <div class="slide1 wrap slide-wrap" data-slide-duration="800">
                    <div class="slide-draggable flex">
                        <c:forEach  var="movies" varStatus="status" items="${movie.value}" begin="1">
                            <div class="poster"><span>${status.index}</span><img class="poster-img" src="https://image.tmdb.org/t/p/w500/${movies.posterImg}"></div>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <script>
        function dragSlide__init() {
            var $slideDrag = $('.slide1').find(' > .slide-draggable');
            var slideDuration = parseInt($slideDrag.parent().attr('data-slide-duration'));

            $slideDrag.data("data-slide-index", 0)
            var slideIndex = $slideDrag.data("data-slide-index");
            var dragAmount = 0;

            // slide setting
            function slideSet() {
                var $this = $slideDrag;
                var $parent = $this.parent();
                var slideWidth = $parent.width();

                var duration = slideDuration / 1000;

                $this.css('transition', 'transform ' + duration + 's');
                $this.css({ transform: "translateX(" + slideIndex * -slideWidth + "px)" });
            }

            //drag slide
            function dragSlide() {
                var $this = $(this);

                // drag prevent
                if ($this.hasClass('prevent')) {
                    return;
                }
                $this.addClass('prevent');

                // slide
                var $slide = $this.parent();

                var slideWidth = $slide.width();
                var minDistance = (slideWidth / 2) / 2;

                var dragPosition = parseInt($this.css("left"));
                slideDuration = parseInt($slide.attr('data-slide-duration'));
                var slideLimit = $this.find(" > .poster").length - 1;

                //drag reset
                $this.stop().animate({ left: 0 }, {
                    duration: slideDuration, complete: function () {
                        $this.removeClass('prevent');
                    }
                });


                // drag direction
                if (dragPosition < 0) {
                    console.log(dragPosition);
                    if (dragPosition > -minDistance || slideIndex >= slideLimit) return;

                    $this.data("data-slide-index", ++slideIndex);
                    dragAmount += -slideWidth;
                    console.log(dragAmount);
                }
                else {
                    if (dragPosition < minDistance || slideIndex <= 0) return;
                    $this.data("data-slide-index", --slideIndex);
                    dragAmount += slideWidth;
                }
                $this.css({ transform: "translateX(" + dragAmount + "px)" });
            }

            // draggable
            slideSet();
            $(".slide1 .slide-draggable").draggable({ axis: "x", stop: dragSlide });
            $(window).resize(_.throttle(slideSet, 100));
        }

        $(function () {
            dragSlide__init();
            $('.slide-area').bxSlider({
                mode: 'fade',
                captions: true,
                speed: 1000
                
            });
        });

    </script>


</body>

</html>