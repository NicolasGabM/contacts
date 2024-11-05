
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
        } else {
            console.error("Error:", response.statusText);
                    }
    } catch (error) {
        console.error("Error", error);
    }
});
