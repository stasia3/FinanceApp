// Replace with your backend endpoint
const urlItemPrice = 'http://localhost:8080/api/itemprice';
const shopMap = {};

loadShops();
loadData();

function loadData() {
    fetch(urlItemPrice)
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector('#data-table tbody');
            tbody.innerHTML = "";-

            data.forEach(item => {
                const tr = document.createElement('tr');
                console.log("TEST: ", item);
                tr.innerHTML = `
                    <td>${item.idItemPrice}</td>
                    <td>${item.createdAt}</td>
                    <td>${shopMap[item.idShop] ?? 'Shop'}</td>
                    <td>${item.price}</td>
                    <td>${item.TVAType}</td>
                    <td>${item.actual}</td>
                    <td>${item.idItem}</td>
                    <td>${item.idPreviousPrice}</td>
                `;
                tbody.appendChild(tr);
            });
        })
        .catch(err => console.error('Error fetching data:', err));

}

// Initial load


document.getElementById('add-item-form').addEventListener('submit', function(e) {
    e.preventDefault(); // prevent page reload

    const createdAt = document.getElementById('createdAt').value; // yyyy-mm-dd
    const idShop = document.getElementById('shopSelect').value;
    const price = parseFloat(
        document.getElementById('price').value.replace(',', '.')
    );
    const TVAType = document.getElementById('TVAType').value;
    const actual = document.getElementById('isActual').value;
    const idItem = parseInt(document.getElementById('idItem').value, 10);
    const idPreviousPrice = parseInt(document.getElementById('idPreviousPrice').value, 10);


    fetch(urlItemPrice, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ createdAt, idShop, price, TVAType, actual, idItem, idPreviousPrice })
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





