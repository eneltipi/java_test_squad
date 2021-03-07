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

			 <div class="menu">
		        <div>
		            <div class="username">${userEmail}</div>
		            <div class="role">${userRole}</div>
		            <div class="logout">Logout</div>
		        </div>
		    </div>

            <div class="container">
                <div class="middle content">
                    <form id="searchBar">
                        <input type="text" id="searchInput" />
                        <div id='clearSearching'></div>
                        <div>Find</div>
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
                                <tr name="${a.getEmail()}">
									<td></td>
									<td>${a.getEmail()}</td>
									<td>${a.getPassword()}</td>
									<td>${a.getFullname()}</td>
									<td>${a.getPhonenumber()}</td>
									<td>${a.getRole()?"Admin":"Employee"}</td>
									<td>${a.getDateCreated()}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

		<div class="right content">
			<div id="rightButton"></div>
			<div>
				<form action="insert" method="post" id="insertForm">
					<input type="text" name="email" placeholder="Username" /> <input
						type="text" name="password" placeholder="Password" /> <input
						type="text" name="fullname" placeholder="Full name" /> <input
						type="text" name="phonenumber" placeholder="Phone number" /> <input
						type="hidden" name="role" id="roleInput" value="staff" /> <select
						id="roleSelection">
						<option>Empolyee</option>
						<option>Admin</option>
					</select> <input type="submit" name="insertSubmit" value="Insert" />
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
	
			var gaugau = {
					_default_account: "${userEmail}",
					mail : function Email(Mail){this.email = Mail;},
					tableSize(){
						return this.getTbody().children.length+"";
					},
					checkDeleteAccountID(email){
						return email === this._default_account ? this.deleteRow : email
					},
					deleteRow(){
						let tbody = gaugau.getTbody();
						if(tbody.children.length === 1){return alert("this is default account") };
						let selectedRow = tbody.children[Math.floor(Math.random() * tbody.children.length)]
				       let result =  gaugau.checkDeleteAccountID(selectedRow.children[1].innerText)				    		   
				    		   if(result instanceof Function)result();
				    		   else{
				    			   gaugau.selectedRow = selectedRow;
				    			   gaugau.ajax('/webapp/deleteAccount',new gaugau.mail(result));
				    		   }
				    },
					getTbody(setOrGet){
						 return document.querySelector('tbody');;
					},
					ajax(url,obj){
						
						if(!obj instanceof this.mail && url === '/webapp/deleteAccount') return;
						
						postData(url, obj)
                        .then(response => {
                            if (response == 1) {
                                gaugau.selectedRow.remove();
                                alert("Account successfully deleted");
                            } else {
                                alert("Failed to delete account");
                            }
                        }) 
						
					}
			};
	
                function getCookie(name) {
                    var cookieArr = document.cookie.split(";");
                    for (var i = 0; i < cookieArr.length; i++) {
                        var cookiePair = cookieArr[i].split("=");
                        if (name == cookiePair[0].trim()) {
                            return decodeURIComponent(cookiePair[1]);
                        }
                    }
                    return null;
                }

                async function postData(url = '', data = {}) {
                    const response = await fetch(url, {
                        method: 'POST', // *GET, POST, PUT, DELETE, etc.
                       
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': "Bearer " + getCookie('jwt')
                        },
                         body: JSON.stringify(data) // body data type must match "Content-Type" header
                    });
                    return response.json(); // parses JSON response into native JavaScript objects
                }

                async function aaa(url = '') {
                    const response = await fetch(url, {
                        method: 'POST', // *GET, POST, PUT, DELETE, etc.
                        headers: {
                            'Authorization': "Bearer " + getCookie('jwt')
                        },
                        body: new FormData(document.getElementById('insertForm'))
                    });
                    return response;
                }

                document.getElementById('insertForm').addEventListener('submit', e => {
                    e.preventDefault();
                    aaa('/webapp/insert').then((response) => {
                        console.log(response)
                        if(response.status == 403){
                            alert("Authentication deny!")
                        }else{
                            location.reload()
                        }
                    })
                });

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
                    selectedRow = e.target.parentNode;
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
                                        let email = array[selectedRowIndex - 1].email;

                                        let content = {
                                            email: email,
                                            columnName: columnName[selectedCellIndex - 1],
                                            newValue: newValue
                                        };

                                        postData('/webapp/updateCellValue', content)
                                            .then(response => {
                                                console.log(response)// JSON data parsed by `data.json()` call
                                                array = response;
                                                selectedCell.innerHTML = "";
                                                selectedCell.innerText = newValue;
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
                        roleInput.value = "staff,admin";
                    }
                });

                // Menu option handler
                customMenu.addEventListener('click', e => {
                    let option = e.target.innerText;
                    if (option == "Delete") {
                        let email = selectedRow.children[1].innerHTML;

                        postData('/webapp/deleteAccount', { email: email })
                            .then(response => {
                                if (response == 1) {
                                    selectedRow.remove();
                                    alert("Account successfully deleted");
                                } else {
                                    alert("Failed to delete account");
                                }
                            });
                    }
                    customMenu.style.zIndex = "-1";
                });


            </script>
</body>

</html>