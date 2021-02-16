document.getElementById("searchInput").addEventListener("keyup", (e) => {
    let inputValue = e.target.value.trim().toLowerCase();
    table.innerHTML = "";

    array.filter((account) => {
        let username = account.username.toLowerCase();
        let result = username.includes(inputValue);
        if (result) {
            document.querySelector("tbody").innerHTML +=
                    `
                <tr>
                    <td></td>
                    <td>${account.username}</td>
                    <td>${account.password}</td>
                    <td>${account.fullname}</td>
                    <td>${account.address}</td>
                    <td>${account.phonenumber}</td>
                    <td>${account.role ? 'Admin':'Empolyee'}</td>
                    <td>${account.dateCreated}</td>
                </tr >`;
        }
    });
    if (inputValue.length > 0) {
        document.getElementById('clearSearching').innerText = "x";
    } else {
        document.getElementById('clearSearching').innerText = "";
    }
});