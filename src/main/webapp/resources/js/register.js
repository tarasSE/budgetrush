function SendPost(url, json) {

    $.ajax({
        url:url,
        dataType: 'json',
        accept: 'application/json',
        type:'POST',
        contentType: 'application/json; charset=utf-8',
        data:JSON.stringify(json) //JSON.stringify(["Яблоко", "Апельсин", "Слива"])
    });

}

function register() {
    var nameElement1 = document.getElementById("name");
    var name = nameElement1.value;

    var nameElement2 = document.getElementById("password");
    var password = nameElement2.value;

    var json = {name:name, password:password};

    SendPost("/v1/users/", json);

}