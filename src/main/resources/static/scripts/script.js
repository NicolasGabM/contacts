
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

function displayData(){
    const contactTableBody = document.getElementById("contact_table_body");
    contactTableBody.innerHTML = ""; 
    const startIndex = (currentPage - 1) * rowsPerPage;
    const endIndex = startIndex + rowsPerPage;
    const pageData = data.slice(startIndex, endIndex);

    pageData.forEach(item => {
        const contactRow = document.createElement("tr");
        contactRow.className = "contactRow";

        contactRow.innerHTML = `
            <td>${item.id}</td>
            <td>${item.firstName}</td>
            <td>${item.lastName}</td>
            <td>${item.phone}</td>
            <td>${item.mail}</td>
            <td>${item.address}</td>
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
fetchData();
