<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
</head>
<body>

<textarea id="code_snippet" placeholder = "Enter your new code here"></textarea>
<br>
<span>Time restriction: </span>
<input id = "time_restriction" type = "text" value = "0"/>
<br>
<span>Views restriction: </span>
<input id = "views_restriction" type = "text" value = "0"/>
</br>
<button id="send_snippet" type="submit" onclick="send()">Submit</button>

<script type="text/javascript">
    function send() {
        let object = {
            "code": document.getElementById("code_snippet").value,
            "time": document.getElementById("time_restriction").value,
            "views": document.getElementById("views_restriction").value
        };

        let json = JSON.stringify(object);

        let xhr = new XMLHttpRequest();
        xhr.open("POST", '/api/code/new', false)
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.send(json);

        if (xhr.status === 200) {
            alert("Success!");
        }
    }
</script>

</body>
</html>