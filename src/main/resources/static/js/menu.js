fetch("/menus/adminMenu.html")
    .then(res => res.text())
    .then(html => {
        document.getElementById("admin-menu-container").innerHTML = html;
    })
    .catch(err => console.error("Menu load error:", err));

fetch("/menus/userMenu.html")
    .then(res => res.text())
    .then(html => {
        document.getElementById("user-menu-container").innerHTML = html;
    })
    .catch(err => console.error("Menu load error:", err));
