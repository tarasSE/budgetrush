function register() {
    var nameElement1 = document.getElementById("name");
    var name = nameElement1.value;

    var nameElement2 = document.getElementById("password");
    var password = nameElement2.value;

    var json = {name: name, password: password};

    sendPost("/v1/users/", json);

}

