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
            <p><span class="combinedDivParameter">Price:</span> $${item.price}</p>
            <p><span class="combinedDivParameter">Pills:</span> ${item.numberOfTablets}</p>
            <p><span class="combinedDivParameter">MFG:</span> ${item.medicineManufacturingDate}</p>
            <p><span class="combinedDivParameter">EXP:</span> ${item.medicineExpiryDate}</p>
        `;

        imageCombinedDiv.append(combinedDiv);
        
        const productCombine = document.createElement("div");
        productCombine.setAttribute("id","productCombine");
        productCombine.innerHTML = `
        
            <h3 id="productName" class = "productCombineDivClass">${item.medicineName}</h3>
            <p id="productDescription" class = "productCombineDivClass"><span class="productComibineParameter"> Desc:</span> ${item.medicineDescription}</p>
            <p id="productCompanyName" class = "productCombineDivClass"><span class="productComibineParameter"> Company:</span>Company: ${item.companyName}</p>
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
document.addEventListener('DOMContentLoaded', () => {
    const user = JSON.parse(localStorage.getItem('user'));

    if (user) {
        const signup = document.getElementById("singupcontent");
        signup.innerText = `Hi, ${user.username}`;
    }
});