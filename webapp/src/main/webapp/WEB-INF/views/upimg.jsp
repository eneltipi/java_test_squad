<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <title>Document</title>
    </head>

    <body>
    <body>
        <input type="file" id="asd" multiple>

        <script>
            const formData = new FormData();

            var fileInput = document.querySelector("#asd");
            fileInput.addEventListener('change', e => {

                for (var i = 0; i < fileInput.files.length; i++) {
                    formData.append("file", fileInput.files[i]);
                }
            });












//                  $.ajax({
//                    type: "POST",
//                    enctype: 'multipart/form-data',
//                    url: "/SpringMVC/upimg",
//                    data: fd,
////                    processData: false,
////                        contentType: false,
////                    cache: false,
//                    success: (data) => {
//                        alert("yes");
//                    }
//                });

//            fetch("/SpringMVC/upimg", {
//            method: 'POST',
//                    body: formData
//            });
//            let result = await response.json();
//            alert(result.message);
//            });

        </script>
    </body>

</html>