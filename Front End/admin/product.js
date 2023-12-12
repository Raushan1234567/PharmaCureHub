function addMedicine() {
    const medicineData = {
        medicineName: document.getElementById('medicineName').value,
        price: parseFloat(document.getElementById('price').value),
        image: document.getElementById('image').value,
        numberOfTablets: parseInt(document.getElementById('numberOfTablets').value),
        medicineDescription: document.getElementById('medicineDescription').value,
        medicineManufacturingDate: document.getElementById('manufacturingDate').value,
        medicineExpiryDate: document.getElementById('expiryDate').value,
        companyName: document.getElementById('companyName').value,
        category: document.getElementById('category').value,
    };

    fetch('http://localhost:9090/medicine/adds', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(medicineData),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        console.log('Medicine added successfully:', data);
        alert("Medicine added successfully")
        fetchMedicineData();
        // Handle the response data as needed
    })
    .catch(error => {
        alert("Error")
        console.error('Error adding medicine:', error);
        // Handle errors
    });
}

function fetchMedicineData() {
    fetch(`http://localhost:9090/medicine/${medicineName}`)
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        console.log('Medicine data retrieved successfully:', data);

        // Append the medicine data to the 'getproduct' div
        displayMedicineData(data);
    })
    .catch(error => {
        console.error('Error fetching medicine data:', error);
        // Handle errors
    });
}

function displayMedicineData(data) {
    var getproductDiv = document.getElementById('getproduct');
    getproductDiv.innerHTML = ''; // Clear existing content

    data.forEach(medicine => {
        var medicineElement = document.createElement('div');
        medicineElement.innerHTML = `
            <p>Medicine Name: ${medicine.medicineName}</p>
            <p>Price: ${medicine.price}</p>
            <p>Image URL: ${medicine.image}</p>
            <p>Number of Tablets: ${medicine.numberOfTablets}</p>
            <p>Medicine Description: ${medicine.medicineDescription}</p>
            <p>Manufacturing Date: ${medicine.manufacturingDate}</p>
            <p>Expiry Date: ${medicine.expiryDate}</p>
            <p>Company Name: ${medicine.companyName}</p>
            <p>Category: ${medicine.category}</p>
        `;
        getproductDiv.appendChild(medicineElement);
    });
}