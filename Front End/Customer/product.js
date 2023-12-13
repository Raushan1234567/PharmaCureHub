// Function to fetch data from the API
function fetchData() {
    fetch("http://localhost:9090/medicine/findAll")
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            // Check if data is an array
            if (Array.isArray(data)) {
                // Display the products
                displayProducts(data);
            } else {
                console.error("Invalid data format");
            }
        })
        .catch(error => {
            console.error("Error fetching data:", error);
        });
}

// Function to display products in the productContainer
function displayProducts(products) {
    const productContainerDiv = document.getElementById("productContainerDiv");

    // Iterate through the products and append to productContainer
    products.forEach(item => {
        const productDiv = document.createElement("div");
        productDiv.classList.add("product-item");
        productDiv.setAttribute("id","productContainer");
        
        const imageCombinedDiv = document.createElement("div");
        imageCombinedDiv.setAttribute("id","imageCombinedDiv");       
        imageCombinedDiv.innerHTML = `
           <img id="productImage" class="productDetails" src="${item.image}" alt="${item.medicineName}">

        `;

        const combinedDiv = document.createElement("div");
        combinedDiv.setAttribute("id","combineDiv");  
        combinedDiv.innerHTML = `
            <p id="productPrice" >Price: $${item.price}</p>
            <p id="productTableteNumber">Pills: ${item.numberOfTablets}</p>
            <p id="productManufacturingDate">MFG: ${item.medicineManufacturingDate}</p>
            <p id="productExpiryDate">EXP: ${item.medicineExpiryDate}</p>
        `;

        imageCombinedDiv.append(combinedDiv);
        
        const productCombine = document.createElement("div");
        productCombine.setAttribute("id","productCombine");
        productCombine.innerHTML = `
        
            <h4 id="productName" class = "productCombineDivClass">${item.medicineName}</h4>
            <p id="productDescription" class = "productCombineDivClass">Desc: ${item.medicineDescription}</p>
            <p id="productCompanyName" class = "productCombineDivClass">Company: ${item.companyName}</p>
            <!-- <p id="productCategory" class = "productCombineDivClass">Category: ${item.category}</p> -->
            <!-- Add other fields as needed -->
        `;
        const addToCart = document.createElement("button");
        addToCart.setAttribute("id","addToCart");
        addToCart.innerText = "Add To Cart"
        productDiv.append(imageCombinedDiv,productCombine,addToCart);
        productContainerDiv.append(productDiv);
    });
}

// Call the fetchData function when the page loads
window.onload = fetchData;