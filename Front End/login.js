function submitForm(event) {
    event.preventDefault(); // Prevent the default form submission

    // Get form values
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;


    fetch(`http://localhost:9090/findbyemail/${email}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json', 
        },
    })
    .then(response => {
        if (!response.ok) {
           // console.log('Server response:', data);
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        // Compare retrieved data with input data
        if (data && data.customerPassword  === password) {
            alert("Login successful")
            console.log('Login successful');
            // Redirect or perform other actions here
        } else {
            //console.log('Server response:', data);
            console.log('Invalid email or password');
            // Handle incorrect credentials here
        }
    })
    .catch(error => {
        // Handle errors
        console.error('Error during login:', error);
    });
}
