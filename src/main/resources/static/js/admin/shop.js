console.log("Opened shop.js");

// Replace with your backend endpoint
const urlShop = 'http://localhost:8080/api/shop';


function loadData() {
    fetch(urlShop)
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector('#data-table tbody');
            tbody.innerHTML = "";-

            data.forEach(item => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${item.idShop}</td>
                    <td>${item.name}</td>
                    <td>${item.description}</td>
                    <td>${item.country}</td>
                    <td>${item.city}</td>
                    <td>${item.street}</td>
                    <td>${item.isFizic}</td>
                    <td>${item.site}</td>
                    <td><img src="/uploads/shop/${item.img}" alt="item image" style="width:80px; height:auto;"></td>
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

    const formData = new FormData();
    formData.append('name', document.getElementById('name').value);
    formData.append('description', document.getElementById('description').value);
    formData.append('country', document.getElementById('country').value);
    formData.append('city', document.getElementById('city').value);
    formData.append('street', document.getElementById('street').value);
    formData.append('isFizic', document.getElementById('isFizic').value);
    formData.append('site', document.getElementById('site').value);
    const imageInput = document.getElementById('img');
    if (imageInput.files.length > 0) {
        formData.append('img', imageInput.files[0]);
    }

    fetch(urlShop, {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (!response.ok) throw new Error('Failed to add item or Upload failsed');
        return response.json();
    })
    .then(data => {
        console.log('Saved:', data);
        loadData(); // reload table
        document.getElementById('add-item-form').reset(); // clear form
    })
    .catch(err => console.error(err));
});

function loadShops() {
    fetch(urlShop + "/select")
        .then(res=> res.json())
        .then(data => {
            const select = document.getElementById("shopSelect");
            select.innerHTML = '<option value="">Select shop</option>';

            data.forEach(shop => {
                // store mapping
                shopMap[shop.id] = shop.label;

                const opt = document.createElement("option");
                opt.value = shop.id;
                opt.textContent = shop.label;
                select.appendChild(opt);
            });
        })
        .catch(err => console.error(err));
}
