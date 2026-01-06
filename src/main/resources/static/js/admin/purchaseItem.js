// Replace with your backend endpoint
const url = 'http://localhost:8080/api/purchaseitem';

function loadData() {
    fetch(url)
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector('#data-table tbody');
            tbody.innerHTML = "";-

            data.forEach(item => {
                const tr = document.createElement('tr');
                console.log("TEST: ", item);
                tr.innerHTML = `
                    <td>${item.idPurchaseItem}</td>
                    <td>${item.quantity}</td>
                    <td>${item.quantityUnit}</td>
                    <td>${item.discountProcent}</td>
                    <td>${item.discountPrice}</td>
                    <td>${item.total}</td>
                    <td>${item.idItemPrice}</td>
                    <td>${item.idPurchase}</td>
                    <td>${item.currency}</td>
                `;
                tbody.appendChild(tr);
            });
        })
        .catch(err => console.error('Error fetching data:', err));

}

// Initial load
loadData();

document.getElementById('add-item-form').addEventListener('submit', function(e) {
    e.preventDefault(); // prevent page reload

    const quantity = document.getElementById('quantity').value;
    const quantityUnit = document.getElementById('quantityUnit').value;
    const discountProcent = document.getElementById('discountProcent').value;
    const discountPrice = document.getElementById('discountPrice').value;
    const total = document.getElementById('total').value;
    const idItemPrice = document.getElementById('idItemPrice').value;
    const idPurchase = document.getElementById('idPurchase').value;
    const currency = document.getElementById('currency').value;

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ quantity, quantityUnit, discountProcent, discountPrice, total, idItemPrice, idPurchase, currency })
    })
    .then(response => {
        if (!response.ok) throw new Error('Failed to add item');
        return response.json();
    })
    .then(newItem => {
        console.log('Item added:', newItem);
        loadData(); // reload table
        document.getElementById('add-item-form').reset(); // clear form
    })
    .catch(err => console.error(err));
});

