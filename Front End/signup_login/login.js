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
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        // Compare retrieved data with input data

        if (data && data.customerPassword === password) {
            alert("Login successful");
            console.log('Login successful');

            // Create a cart for the customer
            createCart(data.customerId);
            

            window.location.href = "/landing_page/index.html";

            // Redirect or perform other actions here
        } else {
            console.log('Invalid email or password');
            // Handle incorrect credentials here
        }
    })
    .catch(error => {
        // Handle errors
        console.error('Error during login:', error);
    });
}
function createCart(customerId) {
    const cartData = {}; // No need to include cartId in the request body

    fetch(`http://localhost:9090/cart/createCart/${customerId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
        body: JSON.stringify(cartData),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error creating cart');
        }
        return response.json();
    })
    .then(data => {
        console.log('Cart created successfully:', data);
        // Perform any additional actions after cart creation if needed
    })
    .catch(error => {
        // Handle errors during cart creation
        console.error('Error creating cart:', error);
    });
}