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
            throw new Error("'You don't have account.Please signin first");
        }
        return response.json();
    })
    .then(data => {
        // Compare retrieved data with input data

        if (data && data.customerPassword === password) {
            alert("Login successful");
            console.log('Login successful');

            const signup = document.getElementById("singupcontent");
            const username = `Hi, ${data.customerfirstName}`;
            signup.innerText = username;

            // Store user information in localStorage
            localStorage.setItem('user', JSON.stringify({
                customerId: data.customerId,
                username: data.customerfirstName,
            }));

            // Create a cart for the customer
            createCart(data.customerId);

            // Redirect or perform other actions here
            window.location.href="../landing_page/index.html";
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
// Add this code to your script
document.addEventListener('DOMContentLoaded', () => {
    const user = JSON.parse(localStorage.getItem('user'));

    if (user) {
        const signup = document.getElementById("singupcontent");
        signup.innerText = `Hi, ${user.username}`;
    }
});

function createCart(customerId) {
    const cartData = {}; 

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
      
    })
    .catch(error => {
       
        console.error('Error creating cart:', error);
    });
}