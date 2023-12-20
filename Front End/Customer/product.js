
var cartId = null ;
function fetchData() {
    fetch("http://localhost:9090/medicine/findAll")
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
        
            if (Array.isArray(data)) {
            
                displayProducts(data);
            } else {
                console.error("Invalid data format");
            }
        })
        .catch(error => {
            console.error("Error fetching data:", error);
        });
}


function displayProducts(products) {
    const productContainerDiv = document.getElementById("productContainerDiv");

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
            <p id = "productPrice"><span class="combinedDivParameter">Price:</span> $${item.price}</p>
            <p id = "productNoPills"><span class="combinedDivParameter">Pills:</span> ${item.numberOfTablets}</p>
            <p id = "productMFG"><span class="combinedDivParameter">MFG:</span> ${item.medicineManufacturingDate}</p>
            <p id = "productPrice"><span class="combinedDivParameter">EXP:</span> ${item.medicineExpiryDate}</p>
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
         
        addToCart.addEventListener('click',()=>{
          
            let customerId = null;
         

            const userString = localStorage.getItem('user');
                if (userString) {
           
                    const user = JSON.parse(userString);
                    customerId = user.customerId;
                }
                console.log("customerId is: "+customerId);
              
            fetch(`http://localhost:9090/cart/getCartIdByCustomerId/${customerId}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                },
            })            
                .then(response =>{
                  
                    if(!response.ok){
                        alert("login first!");
                        throw new Error("Network response was not ok");
                    }
                    return response.json();
                })
                .then(data =>{                    
                    let cart_id = data;
                    console.log("Cart_id is : "+cart_id);

                    fetch(`http://localhost:9090/cartItem/addMedicineToCart/${cart_id}/${item.medicineId}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
                    },
                    
                }) 
                .then(response =>{
                      
                    if(!response.ok){   

                        alert("Medicine already exist");
                    }else {
                        alert("item added successfully");
                        
                    }                                          
                }) 
                
            })
                            
        })
      
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