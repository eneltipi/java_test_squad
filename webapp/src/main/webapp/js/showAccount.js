const searchField = document.getElementById("searchInput") 

function loadAccountByFilter(inputValue) {
    table.innerHTML = "";
    array.filter((account) => {
        let email = account.email.toLowerCase();
        
        if (email.includes(inputValue)) {
            document.querySelector("tbody").innerHTML +=
                    `
                <tr>
                    <td></td>
                    <td>${account.email}</td>
                    <td>${account.password}</td>
                    <td>${account.fullname}</td>
                    <td>${account.phonenumber}</td>
                    <td>${account.role ? 'Admin':'Empolyee'}</td>
                    <td>${account.dateCreated}</td>
                </tr >`;
        }
    });        

}
searchField.addEventListener("keyup", (e) => {
    const inputValue = e.target.value.trim().toLowerCase();
    
    if(e.code == "Space" && inputValue.length == 0){
    	return
    }
    if (inputValue.length > 0) {
        loadAccountByFilter(inputValue)
        document.getElementById('clearSearching').innerText = "x";
    }
    if(inputValue.length == 0) {
        loadAccountByFilter(inputValue)
        document.getElementById('clearSearching').innerText = "";       
    }
});


document.getElementById('clearSearching').addEventListener('mouseup', ()=>{
    searchField.value = "";
    loadAccountByFilter("")      
    document.getElementById('clearSearching').innerText = "";  
})

