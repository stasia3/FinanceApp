// Replace with your backend endpoint
const url = 'http://localhost:8080/api/item';

function loadData() {
    fetch(url)
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector('#data-table tbody');
            tbody.innerHTML = "";-

            data.forEach(item => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${item.idItem}</td>
                    <td>${item.name}</td>
                    <td>${item.description}</td>
                    <td>${item.type}</td>
                    <td><img src="/uploads/${item.image}" alt="item image" style="width:80px; height:auto;"></td>
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

//    const name = document.getElementById('name').value;
//    const description = document.getElementById('description').value;
//    const type = document.getElementById('type').value;
//    const image = document.getElementById('image').value;

    const formData = new FormData();
    formData.append('name', document.getElementById('name').value);
    formData.append('description', document.getElementById('description').value);
    formData.append('type', document.getElementById('type').value);
    const imageInput = document.getElementById('image');
    if (imageInput.files.length > 0) {
        formData.append('image', imageInput.files[0]);
    }

    fetch(url, {
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

