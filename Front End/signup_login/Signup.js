function submitForm(event) {
    event.preventDefault();

    var customerfirstName = document.getElementById("customerfirstName").value;
    var customerEmail = document.getElementById("email").value;
    var customerPassword = document.getElementById("password").value;

    console.log("customerfirstName:", customerfirstName);
    console.log("customerEmail:", customerEmail);
    console.log("customerPassword:", customerPassword);

    var formData = {
        customerfirstName: customerfirstName,
        customerEmail: customerEmail,
        customerPassword: customerPassword
    };

    fetch('http://localhost:9090/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
   
    .then(data => {
        if (!data.ok) {
           alert('User with this email already exists');
        //    window.location.href = "";
        window.location.href="login.html";
        }
       
        else{
            alert("Successfully created")
        }
       
    })
    .catch(error => {
        alert('Error during signup: ' + error.message);
    });
}
