
function showModelPop(){
    document.querySelector('.cartMainDiv').classList.add('showPopform')
    // document.querySelector('.cartPopShow').classList.add('')
    console.log("Hello")
}

function closeModelPop(){
    document.querySelector('.cartMainDiv').classList.remove('showPopform')
}


let cartMainDiv = document.getElementById("cartMainDiv");

const userString = localStorage.getItem('user');
if (userString) {
    // Parse the JSON string to get the object
    const user = JSON.parse(userString);
    customerId = user.customerId;
}
// console.log("line3");
fetch(`http://localhost:9090/cart/getCartIdByCustomerId/${customerId}`, {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
    },
})            
.then(response =>{
    console.log("line4");
    if(!response.ok){
        throw new Error("Network response was not ok");
    }
    return response.json();
})
.then(data =>{
    // console.log("line5");
    // console.log("Data is:"+data+" "+typeof(data));
    
    let cart_id = data;
    fetch(`http://localhost:9090/cart/getcartItems/${cart_id}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
    
    })
    .then(response =>{
        return response.json();
    })
    .then(data =>{
        showMedicine(data);
    })

    function showMedicine(items){
        if(items == null){
            data = [];
        }else{
            items.forEach(element => {
                // console.log("Name is "+ element.medicineName);
                let container = document.getElementById("container")
                let itemContainer = document.createElement("div");
                itemContainer.setAttribute("id","itemContainer")
                // container.setAttribute("id","container");
                let firstDiv = document.createElement("div");
                firstDiv.setAttribute("id","firstDiv");
                firstDiv.innerHTML = `
                <img id="medicineImage" src="${element.image}" alt="">
                `;

                let secondDiv = document.createElement("div");
                secondDiv.setAttribute("id","secondDiv");
                secondDiv.innerHTML = `
                <h3 id="medicineName" class = "productCombineDivClass"> ${element.medicineName}</h3>
                <p id = "medicinePrice"><span class="combinedDivParameter">Price: $</span>${element.price}</p>
                 <p id = "numberOfPills"><span class="combinedDivParameter">Pills:</span> ${element.numberOfTablets}</p>
                `;

                let thirdDiv = document.createElement("div");
                thirdDiv.setAttribute("id","thirdDiv");
                thirdDiv.innerHTML = `
                <p id="productMFG"><span class = "productCombineSpanClass">MFG:</span> ${element.medicineManufacturingDate}</p>
                <p><span class="productCombineSpanClass">EXP:</span> ${element.medicineExpiryDate}</p>
                <p><span class="productCombineSpanClass">Company:</span> ${element.companyName}</p>
                `;

                // <div class="quantity-container">
                    // <button class="quantity-button" onclick="decrement()">-</button>
                    // <span class="quantity">1</span>
                    // <button class="quantity-button" onclick="increment()">+</button>
                // </div>

                let forthDiv = document.createElement("div");
                forthDiv.setAttribute("id","forthDiv");
                forthDiv.setAttribute("class","quantity-container");
                forthDiv.innerHTML = `
                <button class="quantity-button" onclick="decrement()">-</button>
                <span class="quantity">1</span>
                <button class="quantity-button" onclick="increment()">+</button>
                `;
 
                let fifthDiv = document.createElement("div");
                fifthDiv.setAttribute("id","fifthDiv");
                fifthDiv.setAttribute("class","remove-container");
                fifthDiv.innerHTML = `
                <button class="remove-button" onclick="removefromCart()">Remove</button>
                `;


                itemContainer.append(firstDiv,secondDiv,thirdDiv,forthDiv,fifthDiv)
                container.append(itemContainer);
                cartMainDiv.append(container);
                
            });
        }
        
    }
})
 
                    
                    