<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring Break % Counter</title>
    <style>
        body {
            background: white;
            font-family: 'Dubai', sans-serif;
            text-align: center;
            padding: 10%;
            margin: 0 auto;
            transition: 0.6s ease-out;
        }
        .main {
            background: aliceblue;
            padding: 10%;
            transition: 0.6s ease-out;
        }
        .main:hover {
            background: #c3e4ff;
        }
        strong {
            color: orange;
        }

        .bottom {
            padding: 1%;
            display: flex;
            width:100%;
            height: 100%;
            justify-content: center;
            align-items: center;
        }

        p {
            padding: 5px;
            width: auto;
            font-size: 20px;
            height: 50%;
            transition: 0.6s ease-out;
            background: white;
        }
        p:hover {
            background: #c3e4ff;
        }
        button {
            padding: 5px;
            font-family: 'Dubai', sans-serif;
            text-align: center;
            font-size: 20px;
            height: 50%;
            background: white;
            border: white solid;
            transition: 0.6s ease-out;
            width: auto;
        }

        button:hover {
            background: #c3e4ff;
            color: aliceblue;
        }
    </style>
</head>
<body>
<div class="main">
    <h1>
        Unfortunately, <strong>${value}</strong> of Spring Break has already passed.
    </h1>
</div>
<div class="bottom">
    <p>
        Created by Kason Gu in 2022.
    </p>
    <button onclick="github()">
        View source on Github
    </button>
</div>
<script>
    function github() {
        open("https://github.com/croissant676/BreakTracker")
    }
</script>
</body>
</html>