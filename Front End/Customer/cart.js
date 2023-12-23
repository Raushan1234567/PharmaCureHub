
function showModelPop(){
    document.querySelector('.cartMainDiv').classList.add('showPopform')
    // document.querySelector('.cartPopShow').classList.add('')
    console.log("Hello")
}

function closeModelPop(){
    document.querySelector('.cartMainDiv').classList.remove('showPopform')
}




let customerId;
function fetchCartId() {
    const userString = localStorage.getItem('user');
    if (userString) {
        const user = JSON.parse(userString);
        customerId = user.customerId;
    }

    return fetch(`http://localhost:9090/cart/getCartIdByCustomerId/${customerId}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Network response was not ok");
        }
        return response.json();
    });
}

function fetchCartItems(cartId) {
    return fetch(`http://localhost:9090/cartItem/getMedicinesByCartId/${cartId}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Error fetching cart items");
        }
        return response.json();
    });
}

function removeCartItem(cartId, medicineId) {
    return fetch(`http://localhost:9090/cartItem/deleteCartItem/${cartId}/${medicineId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
    });
}
 let totalSum=0;
// payment start
function payment() {
    let amount=totalSum;
    $.ajax(
    {
    url:`http://localhost:9090/cart/payment/${amount}`,
    data:JSON.stringify({amount:amount,info:"information"}),
    contentType:'appliation/json',
    type:'Post',
    dataType:'json',
    success:function(response) {
    
    if(response.status == "created") {
        let options = {
            key: 'rzp_test_QHOBobX1qODW9l',
            amount: response.amount,
            currency: 'INR',
            name: "Demo",
            description: "Test Transaction",
            image: "",
            order_id: response.id,
            handler:function(response){
    console.log(response.razorpay_payement_id);
    console.log(response.razorpay_order_id);
    console.log(response.razorpay_signature);
    console.log("payment done");
    
            },
            prefill:{
                name:'',
                email:'',
                contact:''
            },
            notes: {
                address: "Razorpay Corporate Office Test"
            },
            
        theme: {
            color: "#3399cc"
        }
         }
         var rzp1 = new Razorpay(options);
         rzp1.on("payment.failed",function(response){
            console.log(response);
            alert("failed");
         });
    
    let s=rzp1.open();
        console.log(s);
    }
    
    },
    error:function(error){
        console.log(error); 
    }
    
    })
    }
// payment end
var container = document.getElementById("container");
 
async function showMedicineContainer(element) {

    let itemContainer = document.createElement("div");
    itemContainer.setAttribute("id", "itemContainer");

    let firstDiv = document.createElement("div");
    firstDiv.setAttribute("id", "firstDiv");
    firstDiv.innerHTML = `<img id="medicineImage" src="${element.image}" alt="">`;

    let secondDiv = document.createElement("div");
    secondDiv.setAttribute("id", "secondDiv");
    secondDiv.innerHTML = `
        <h3 id="medicineName" class="cartCombineDivClass">${element.medicineName}</h3>
        <p id="medicinePrice" class="cartCombineDivClass"><span class="combinedDivParameter">Price: $</span>${element.price}</p>
        <p id="numberOfPills" class="cartCombineDivClass"><span class="combinedDivParameter">Pills:</span> ${element.numberOfTablets}</p>
    `;
let id=document.getElementById("totalAmount") ;
    let thirdDiv = document.createElement("div");
    thirdDiv.setAttribute("id", "thirdDiv");
    thirdDiv.innerHTML = `
        <p id="productMFG" class = "cartCombingClass"><span class="cartCombineSpanClass">MFG:</span> ${element.medicineManufacturingDate}</p>
        <p class = "cartCombingClass"><span class="cartCombineSpanClass">EXP:</span> ${element.medicineExpiryDate}</p>
        <p class = "cartCombingClass"><span class="cartCombineSpanClass">Company:</span> ${element.companyName}</p>
    `;

    let forthDiv = document.createElement("div");
    forthDiv.setAttribute("id", "forthDiv");
    forthDiv.setAttribute("class", "quantity-container");

    let decrement_button = document.createElement("button")
    decrement_button.setAttribute("id","decrement_button");
    decrement_button.innerText = "-";

    let quantity = document.createElement("span");
    quantity.setAttribute("id","quantity");
    try {
        const quantityValue = await getQuantity(element.medicineId);
        quantity.innerText = quantityValue;
        totalSum+= (element.price * quantityValue);
        
        id.innerHTML=totalSum;
        console.log(totalSum);
    } catch (error) {
        console.error("Error fetching quantity:", error);
    }
    
    
    let increment_button = document.createElement("button")
    increment_button.setAttribute("id","increment_button");
    increment_button.innerText = "+";

    decrement_button.addEventListener('click', () =>{

        decrementQuantity(element.medicineId);
    })

    increment_button.addEventListener('click', () =>{
        incrementQuantity(element.medicineId);
    })
   forthDiv.append(decrement_button,quantity,increment_button);

    let fifthDiv = document.createElement("div");
    fifthDiv.setAttribute("id", "fifthDiv");
    fifthDiv.setAttribute("class", "remove-container");
    let removebutton = document.createElement("button");
    removebutton.setAttribute("id", "remove_button");
    removebutton.innerText = "Remove";

    removebutton.addEventListener('click', () => {
        removeItemFromCart(element.medicineId);
    });

    fifthDiv.append(removebutton);
    itemContainer.append(firstDiv, secondDiv, thirdDiv, forthDiv, fifthDiv);
    container.append(itemContainer);
    cartMainDiv.append(container);
}

function showMedicine(items) {
    container.innerHTML = null;
    if (items == null) {
        data = [];
    } else {
        totalSum=0;
        items.forEach(element => {
            showMedicineContainer(element);
        });
    }
}

function removeItemFromCart(medicineId) {
   
    fetchCartId()
        .then(data => {
            let cart_id = data;
            return removeCartItem(cart_id, medicineId);
        })
        .then(response => {
            if (!response.ok) {
                alert("Medicine doesn't exist");
            } else {
                alert("Medicine Deleted");
            }
        })
        .then(() => {
            // Fetch and display updated cart items
            fetchCartId()
                .then(cartId => fetchCartItems(cartId))
                .then(data => showMedicine(data));
        })
        .catch(error => {
            console.error("Error:", error);
        });
}

function getCartItemId(cart_id, medicineId) {
    return fetch(`http://localhost:9090/cartItem/getCartItemId/${cart_id}/${medicineId}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Error fetching Medicine");
        }
        return response.json();
    });
}


async function updateQuantity(itemId) {
    const quantityElement = document.querySelector(`[data-item-id="${itemId}"] .quantity`);
    if (quantityElement) {
        try {
            const cartId = await fetchCartId();
            const cartItemId = await getCartItemId(cartId, itemId);
            const response = await fetch(`http://localhost:9090/cartItem/getQuantity/${cartItemId}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error("Error fetching quantity");
            }

            const quantityData = await response.json();
            quantityElement.innerText = quantityData.quantity; // Update the quantity directly
        } catch (error) {
            console.error("Error updating quantity:", error);
        }
    }
}


async function decrementQuantity(itemId) {
    try {
        const cartId = await fetchCartId();
        const cartItemId = await getCartItemId(cartId, itemId);
        const response = await fetch(`http://localhost:9090/cartItem/decrement/${cartItemId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            },
        });

        if (response.ok) {
            // alert("Successfully decremented");
            // Fetch and display updated cart items
            fetchCartId()
                .then(cartId => fetchCartItems(cartId))
                .then(data => showMedicine(data));
        } else {
            throw new Error("Error fetching decrement");
        }
    } catch (error) {
        console.error("Error:", error);
    }
}

async function incrementQuantity(itemId) {
    try {
        const cartId = await fetchCartId();
        const cartItemId = await getCartItemId(cartId, itemId);
        const response = await fetch(`http://localhost:9090/cartItem/increment/${cartItemId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            },
        });

        if (response.ok) {
            // alert("Successfully incremented");
            // Fetch and display updated cart items
            fetchCartId()
                .then(cartId => fetchCartItems(cartId))
                .then(data => showMedicine(data));
        } else {
            throw new Error("Error fetching increment");
        }
    } catch (error) {
        console.error("Error:", error);
    }
}


async function getQuantity(medicineId) {
    try {
        let cart_id = await fetchCartId();
        let cartItemId = await getCartItemId(cart_id, medicineId);
        let response = await fetch(`http://localhost:9090/cartItem/getQuantity/${cartItemId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            },
        });
        if (!response.ok) {
            throw new Error("Error fetching quantity");
        }
        let quantityData = await response.json();
        return quantityData;
    } catch (error) {
        console.error("Error fetching quantity:", error);
        throw error;
    }
}

// Fetch and show medicine initially
fetchCartId()
    .then(cartId => fetchCartItems(cartId))
    .then(data => showMedicine(data))
    .catch(error => {
        console.error("Error:", error);
    });
