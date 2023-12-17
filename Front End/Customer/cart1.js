
function showModelPop(){
    document.querySelector('.cartMainDiv').classList.add('showPopform')
    // document.querySelector('.cartPopShow').classList.add('')
    console.log("Hello")
}

function closeModelPop(){
    document.querySelector('.cartMainDiv').classList.remove('showPopform')
}


let quantities = {};
let customerId;

function increment(itemId) {
    if (!quantities[itemId]) {
        quantities[itemId] = 1;
    } else {
        quantities[itemId]++;
    }
    updateQuantity(itemId);
}

function decrement(itemId) {
    if (quantities[itemId] && quantities[itemId] > 1) {
        quantities[itemId]--;
        updateQuantity(itemId);
    }
}

function updateQuantity(itemId) {
    const quantityElement = document.querySelector(`[data-item-id="${itemId}"] .quantity`);
    if (quantityElement) {
        quantityElement.innerText = quantities[itemId];
    }
}

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
    return fetch(`http://localhost:9090/cart/getcartItems/${cartId}`, {
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
    return fetch(`http://localhost:9090/cart/removeCartItems/${cartId}/${medicineId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
    });
}

var container = document.getElementById("container");

function showMedicineContainer(element) {

    let itemContainer = document.createElement("div");
    itemContainer.setAttribute("id", "itemContainer");

    let firstDiv = document.createElement("div");
    firstDiv.setAttribute("id", "firstDiv");
    firstDiv.innerHTML = `<img id="medicineImage" src="${element.image}" alt="">`;

    let secondDiv = document.createElement("div");
    secondDiv.setAttribute("id", "secondDiv");
    secondDiv.innerHTML = `
        <h3 id="medicineName" class="productCombineDivClass">${element.medicineName}</h3>
        <p id="medicinePrice"><span class="combinedDivParameter">Price: $</span>${element.price}</p>
        <p id="numberOfPills"><span class="combinedDivParameter">Pills:</span> ${element.numberOfTablets}</p>
    `;

    let thirdDiv = document.createElement("div");
    thirdDiv.setAttribute("id", "thirdDiv");
    thirdDiv.innerHTML = `
        <p id="productMFG"><span class="productCombineSpanClass">MFG:</span> ${element.medicineManufacturingDate}</p>
        <p><span class="productCombineSpanClass">EXP:</span> ${element.medicineExpiryDate}</p>
        <p><span class="productCombineSpanClass">Company:</span> ${element.companyName}</p>
    `;

    let forthDiv = document.createElement("div");
    forthDiv.setAttribute("id", "forthDiv");
    forthDiv.setAttribute("class", "quantity-container");
    forthDiv.innerHTML = `
        <button class="quantity-button" onclick="decrement(${element.medicineId})">-</button>
        <span class="quantity">${quantities[element.medicineId] || 1}</span>
        <button class="quantity-button" onclick="increment(${element.medicineId})">+</button>
    `;

    let fifthDiv = document.createElement("div");
    fifthDiv.setAttribute("id", "fifthDiv");
    fifthDiv.setAttribute("class", "remove-container");
    let removebutton = document.createElement("button");
    removebutton.setAttribute("class", "remove-button");
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

// Fetch and show medicine initially
fetchCartId()
    .then(cartId => fetchCartItems(cartId))
    .then(data => showMedicine(data))
    .catch(error => {
        console.error("Error:", error);
    });
