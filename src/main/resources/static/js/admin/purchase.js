// Replace with your backend endpoint
const urlPurchase = 'http://localhost:8080/api/purchase';
const shopMap = {};

loadShops();
loadData();

function loadData() {
    fetch(urlPurchase)
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector('#data-table tbody');
            tbody.innerHTML = "";-

            data.forEach(item => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${item.idPurchase}</td>
                    <td>${shopMap[item.idShop] ?? 'Shop'}</td>
                    <td>${item.date}</td>
                    <td>${item.discountTotal}</td>
                    <td>${item.paymentType}</td>
                    <td>${item.priceTotal}</td>
                    <td>${item.TVATotal}</td>
                `;
                tbody.appendChild(tr);
            });
        })
        .catch(err => console.error('Error fetching data:', err));

}

document.getElementById('add-item-form').addEventListener('submit', function(e) {
    e.preventDefault(); // prevent page reload

    const idShop = document.getElementById('shopSelect').value;
    const date = document.getElementById('date').value;
    const discountTotal = document.getElementById('discountTotal').value;
    const paymentType = document.getElementById('paymentType').value;
    const priceTotal = document.getElementById('priceTotal').value;
    const TVATotal = document.getElementById('TVATotal').value;

    fetch(urlPurchase, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ idShop, date, discountTotal, paymentType, priceTotal, TVATotal })
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

