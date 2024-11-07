
let currentPage = 1;      
const rowsPerPage = 10;         
let data = []

async function fetchData() {
    try {
        const response = await fetch("http://localhost:8080/api/v1/contacts/");
        if (!response.ok) throw new Error("error in data fetch");

        data = await response.json();
        displayData();
    } catch (error) {
        console.log(error.message);
    }
}

//get method
function displayData(){
    const contactTableBody = document.getElementById("contact_table_body");
    contactTableBody.innerHTML = ""; 
    const startIndex = (currentPage - 1) * rowsPerPage;
    const endIndex = startIndex + rowsPerPage;
    const pageData = data.slice(startIndex, endIndex);

    pageData.forEach(item => {
        const contactRow = document.createElement("tr");
        contactRow.id = `contactRow${item.id}`;

        contactRow.innerHTML = `
            <td>${item.id}</td>
            <td>${item.firstName}</td>
            <td>${item.lastName}</td>
            <td>${item.phone}</td>
            <td>${item.mail}</td>
            <td>${item.address}</td>
            <td><button id = "delete" class="acction_button" onclick="deleteContact(${item.id})">X</button>

        `
        contactTableBody.appendChild(contactRow);
    });
    
    const emptyRows = rowsPerPage - pageData.length;
    for (let i = 0; i < emptyRows; i++) {
        const emptyRow = document.createElement('tr');
        emptyRow.innerHTML = `
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            
        `;
        contactTableBody.appendChild(emptyRow);
    }
    

    document.getElementById("previous_button").disabled = currentPage === 1;
    document.getElementById("next_button").disabled = endIndex >= data.length;

}

function nextPage() {
    if ((currentPage * rowsPerPage) < data.length) {
        currentPage++;
        displayData();
    }
}
function previousPage() {
    if (currentPage > 1) {
        currentPage--;
        displayData();
    }
}

//post method
document.getElementById("contact_form").addEventListener("submit", async function(event) {
    event.preventDefault(); 

    const firstName = document.getElementById("first_name").value;
    const lastName = document.getElementById("last_name").value;
    const phone = document.getElementById("phone").value;
    const mail = document.getElementById("mail").value;
    const address = document.getElementById("address").value;

    const contact = {
        firstName: firstName,
        lastName:lastName,
        phone:phone,
        mail: mail,
        address:address
    };

    try {
        const response = await fetch("http://localhost:8080/api/v1/contacts/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(contact)
        });

        if (response.ok) {
            const result = await response.json();
            console.log("contact added:", result);
            document.getElementById("contact_form").reset(); 
            fetchData()
        
        } else {
            console.error("Error:", response.statusText, errorText);
                    }
    } catch (error) {
        console.error("Error", error);
    }
    
});

//delete method
async function deleteContact(id){
    console.log(id)
    const confirmation = confirm("delete this contact?");
        if (!confirmation) return; 
    
        try {
            const response = await fetch(`http://localhost:8080/api/v1/contacts/${id}`, {
                method: "DELETE"
            });
    
            if (response.ok) {
                console.log("deleted contact:", id);
                fetchData()
            } else {
                console.error("Error", response.statusText);

            }
        } catch (error) {
            console.error("Error in DELETE method:", error);
        }
    
}

//update method
document.getElementById("update_form").addEventListener("submit", async function(event) {
    event.preventDefault(); 

    const id = document.getElementById("contact_id").value;
    const firstName = document.getElementById("update_first_name").value;
    const lastName = document.getElementById("update_last_name").value;
    const phone = document.getElementById("update_phone").value;
    const mail = document.getElementById("update_mail").value;
    const address = document.getElementById("update_address").value;

    const updatedContact = {
        firstName: firstName,
        lastName:lastName,
        phone:phone,
        mail: mail,
        address:address
    };

    try {
        const response = await fetch(`http://localhost:8080/api/v1/contacts/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(updatedContact)
        });

        if (response.ok) {
            console.log("Contacto actualizado:", updatedContact);
            document.getElementById("update_form").reset()
            fetchData()
        } else {
            console.error("update error:", response.statusText);
        }
    } catch (error) {
        console.error("Put method error:", error);
    }
        
});
fetchData()


