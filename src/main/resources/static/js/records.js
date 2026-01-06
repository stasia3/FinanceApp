// Replace with your backend endpoint
const url = 'http://localhost:8080/api/purchase';

function loadData() {
    fetch(url)
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector('#data-table tbody');
            tbody.innerHTML = "";-

            data.forEach(item => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${item.idPurchase}</td>
                    <td>${item.shop}</td>
                    <td>${item.date}</td>
                    <td>${item.country}</td>
                    <td>${item.city}</td>
                    <td>${item.discountTotal}</td>
                    <td>${item.paymentType}</td>
                    <td>${item.priceTotal}</td>
                    <td>${item.TVATotal}</td>
                    <td><button>Details</button></td>
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

    const shop = document.getElementById('shop').value;
    const date = document.getElementById('date').value;
    const country = document.getElementById('country').value;
    const city = document.getElementById('city').value;
    const discountTotal = document.getElementById('discountTotal').value;
    const paymentType = document.getElementById('paymentType').value;
    const priceTotal = document.getElementById('priceTotal').value;
    const TVATotal = document.getElementById('TVATotal').value;

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ shop, date, country, city, discountTotal, paymentType, priceTotal, TVATotal })
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

