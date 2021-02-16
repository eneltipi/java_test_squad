<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="css/showAccount.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script defer src="js/showAccount.js"></script>
<script src="js/aaa.js"></script>
<title>Document</title>
</head>

<body>
	<script>
            var notice = ${ notice }; //Insert notice from the controller

            // Check if any notice from the controller and alert it
            if (notice != null) {
                alert(notice);
            }

            var array = ${ userListJSON }; // AccountList            
            
        	const columnName = ${ columnNameJSON };// ColumnNameList
        </script>

	<div class="container">
		<div class="middle content">
			<form id="searchBar" action="search.htm" method="post">
				<input type="text" id="searchInput" />
				<div id='clearSearching'></div>
				<input type="submit" value="find" />
			</form>

			<table cellpadding="10">
				<thead>
					<tr>
						<th></th>
						<c:forEach var="column" items="${columnNameJAVA}">
							<th>${column}</th>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="a" items="${accs}">
						<tr>
							<td></td>
							<td>${a.getUsername()}</td>
							<td>${a.getPassword()}</td>
							<td>${a.getFullname()}</td>
							<td>${a.getAddress()}</td>
							<td>${a.getPhonenumber()}</td>
							<td>${a.getRole()?"Admin":"Employee"}</td>
							<td>${a.getDateCreated()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div id="consoleLog">
				<div id="consoleLogButton"></div>
				<textarea id="actionLog"></textarea>
				<div>
					<div>Previous</div>
					<div>Next</div>
				</div>
			</div>

		</div>

		<div class="right content">
			<div id="rightButton"></div>
			<div>
				<form action="insert" method="post" id="lol">
					<input type="text" name="username" placeholder="Username" /> <input
						type="text" name="password" placeholder="Password" /> <input
						type="text" name="fullname" placeholder="Full name" /> <input
						type="text" name="address" placeholder="Address" /> <input
						type="text" name="phonenumber" placeholder="Phone number" /> <input
						type="hidden" name="role" id="roleInput" /> <select
						id="roleSelection">
						<option>Empolyee</option>
						<option>Admin</option>
					</select> <input type="submit" value="Insert" />
				</form>
			</div>
		</div>

	</div>

	<div id="customMenu">
		<a>Delete</a> <a>Copy</a> <a>Paste</a> <a>Select this row</a>
	</div>


	<style>
table input {
	background-color: transparent;
	outline: none;
	border: none;
	width: 100%;
	height: 100%;
	pointer-events: none;
}
</style>

	<script>
            const table = document.querySelector('tbody');
            const customMenu = document.querySelector("#customMenu");

            var tableClick = 0;
            var selectedRow = table.rows[0];
            var selectedCell = selectedRow.cells[0];

            // Disable contextmenu on table
            table.addEventListener('contextmenu', e => {
                e.preventDefault();
                customMenu.style.top = e.clientY + "px";
                customMenu.style.left = e.clientX + "px";
                customMenu.style.zIndex = "1";
            });

            // Disable contextmenu on custome menu
            customMenu.addEventListener('contextmenu', e => {
                e.preventDefault();
            });

            table.addEventListener("mouseup", click => {
                if (click.button == 0) {

                    setTimeout(() => {
                        tableClick = 0;
                    }, 200);

                    customMenu.style.zIndex = "-1";

                    tableClick++;
                    selectedCell.style.backgroundColor = "transparent";
                    selectedRow.style.backgroundColor = "transparent";
                    selectedCell = click.target;
                    selectedRow = selectedCell.parentNode;

                    if (selectedCell.cellIndex == 0) {
                        selectedRow.style.backgroundColor = "green";
                    } else {
                        selectedCell.style.backgroundColor = "green";
                    }

                    if (tableClick == 2) {
                        let oldValue = selectedCell.innerText;
                        selectedCell.innerHTML = `<input type='text'>`;
                        let inputField = selectedCell.firstChild;

                        inputField.focus();
                        inputField.addEventListener('focusout', () => {
                            selectedCell.innerHTML = oldValue;
                        });

                        inputField.addEventListener('keyup', (e) => {
                            let newValue = inputField.value;

                            if (e.keyCode == 13) {
                                if (newValue.trim().length > 0) {
                                    let selectedRowIndex = selectedRow.rowIndex;
                                    let selectedCellIndex = selectedCell.cellIndex;

                                    let username = array[selectedRowIndex - 1].username;

                                    let content = {
                                        username: username,
                                        columnName: columnName[selectedCellIndex - 1],
                                        newValue: newValue
                                    };

                                    $.ajax({
                                        method: "post",
                                        data : content,
                                        url: "/webapp/updateCellValue",
                                        async: false,
                                        dataType: "json",
                                        success: function (response) {
                                            array = response;
                                            selectedCell.innerHTML = "";
                                            selectedCell.innerText = newValue;
                                        }
                                    });
                                } else {
                                    selectedCell.innerHTML = "";
                                    selectedCell.innerText = oldValue;
                                }
                            } else if (e.keyCode == 27) {
                                selectedCell.innerHTML = "";
                                selectedCell.innerText = oldValue;
                            }
                        });
                    }
                }
            });

            document.getElementById("roleSelection").addEventListener("change", e => {
                let selectedValue = e.target.options[e.target.selectedIndex].text;
                let roleInput = document.getElementById("roleInput");
                roleInput.value = false;
                if (selectedValue == "Admin") {
                    roleInput.value = true;
                }
            });

            // Menu option handler
            customMenu.addEventListener('click', e => {
            	console.log("lolll")
                let option = e.target.innerText;
                if (option == "Delete") {
                    let username = selectedRow.children[1].innerHTML;
                    console.log(username);

                    $.ajax({
                        method: "post",
                        data: {username: username},
                        url: "/webapp/deleteAccount",
                        async: false,	
                        dataType: "json",
                        success: function (response) {
                            if (response == 1) {
                                selectedRow.remove();
                                alert("Account successfully deleted");
                            } else {
                                alert("Failed to delete account");
                            }
                        }
                    });
                }
                customMenu.style.zIndex = "-1";
            });

            // open-close right panel onclick
            var isRightPanelOpen = true;

            document.querySelector("#rightButton").addEventListener("click", () => {
                if (isRightPanelOpen) {
                    document.querySelector(".container").style.width = "calc(100% + 230px)";
                    isRightPanelOpen = false;
                } else {
                    document.querySelector(".container").style.width = "100%";
                    isRightPanelOpen = true;
                }
            });

            // open-close console-log panel onclick
            var isConsoleLogOpen = true;

            document.querySelector("#consoleLogButton").addEventListener("click", () => {
                if (isConsoleLogOpen) {
                    document.querySelector(".middle").style.height = "calc(100% + 180px)";
                    isConsoleLogOpen = false;
                } else {
                    document.querySelector(".middle").style.height = "100%";
                    isConsoleLogOpen = true;
                }
            });


        </script>

</body>

</html>