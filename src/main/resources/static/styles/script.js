function checkPasswordValidity() {
    var input = document.getElementById("floatingPassword");
    var password = input.value;

    var hasNumber = /\d/.test(password);
    var hasUpper = /[A-Z]/.test(password);
    var hasLower = /[a-z]/.test(password);

    if (!hasNumber || !hasUpper || !hasLower) {
        input.setCustomValidity("Пароль должен содержать цифры, Заглавную и прописную буквы!");
    } else {
        input.setCustomValidity("");
    }
}

function validateForm() {
    var files = document.getElementById("images").files;
    if (files.length == 0) {
        alert("Выберите хотя бы одну фотографию!");
        return false;
    }
    if (files.length > 4) {
        alert("Максимальное количество загружаемых файлов: 4!");
        return false;
    }
    return true;
}

function likeMsg() {
    alert('Добавлено в Избранное');
}

