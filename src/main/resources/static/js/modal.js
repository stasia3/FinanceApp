const modal = document.getElementById("modal");
const openBtn = document.getElementById("openModalBtn");
const closeBtn = document.getElementById("closeModalBtn");

openBtn.onclick = () => {
    modal.style.display = "flex";
    document.body.style.overflow = "hidden";
};

closeBtn.onclick = closeModal;

modal.onclick = e => {
    if (e.target === modal) closeModal();
};

function closeModal() {
    modal.style.display = "none";
    document.body.style.overflow = "";
}
