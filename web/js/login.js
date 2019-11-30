/* global fetch */

function login (action) {
    console.log("action:", action);

    const email = encodeURIComponent(document.querySelector("#email").value);
    const password = hash(encodeURIComponent(document.querySelector("#password").value));

    fetch("/expense-tracker/LoginController?action=" + action, {
        method: "POST",
        body: "email=" + email + "&password=" + password,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }).then(res => { return res.json(); })
        .then(res => {
        if (res.success) {
            document.querySelector("#alert").innerHTML = `
            <div class="notification is-success">` + res.success + `</div>`;
        } else if (res.error) {
            document.querySelector("#alert").innerHTML = `
            <div class="notification is-danger">` + res.error + `</div>`;
        }
    });
}



