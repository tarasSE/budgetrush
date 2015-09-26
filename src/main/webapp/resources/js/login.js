function signup() {
    var nameElement1 = document.getElementById("name");
    var name = nameElement1.value;
    if(~name.indexOf(' ') ){
        alert('Field "name" can\'t includes spaces!');
        return
    }
    var nameElement2 = document.getElementById("password");
    var password = nameElement2.value;

    var json = {name: name, password: password, role: 0};

    createUser("/v1/users/", json);



}

function login() {

    var nameElement1 = document.getElementById("name");
    var name = nameElement1.value;

    var nameElement2 = document.getElementById("password");
    var password = nameElement2.value;

    requestToken(name, password);

    getRole(name, password);

    console.log('Login successful');
}

function logout() {
    var cookies = document.cookie.split(";");
    for (var i = 0; i < cookies.length; i++) {
        var equals = cookies[i].indexOf("=");
        var name = equals > -1 ? cookies[i].substr(0, equals) : cookies[i];
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
    window.location.reload()
}

function chooseLogin(placeholderId) {

    var pleceholderClass = $('#' + placeholderId).attr('class');
    $('#' + placeholderId).empty();

    $('#' + placeholderId).append("<div class='login' id='login'/>");

    $('#login').append("<div class='field' id='name-field'/>");
    $('#name-field').append("<label for='name'>Name:</label>");
    $('#name-field').append("<input type='text' id='name'/>");

    $('#login').append("<div class='field' id='pass-field'/>");
    $('#pass-field').append("<label for='password'>Password:</label>");
    $('#pass-field').append("<input type='password' id='password'/>");

    $('#login').append("<div class='button' id='button'/>");
    $('#button').append("<input type='button' value='Login' onclick='login()'/>");

}

function chooseSignup(placeholderId) {
    var pleceholderClass = $('#' + placeholderId).attr('class');
    $('#' + placeholderId).empty();
    $('#' + placeholderId).append("<div class='main' id='signup'/>");

    $('#signup').append("<div class='field' id='name-field'/>");
    $('#name-field').append("<label for='name'>Name:</label>");
    $('#name-field').append("<input type='text' id='name'/>");

    $('#signup').append("<div class='field' id='pass-field'/>");
    $('#pass-field').append("<label for='password'>Password:</label>");
    $('#pass-field').append("<input type='password' id='password'/>");

    $('#signup').append("<div class='button' id='button'/>");
    $('#button').append("<input type='button' value='Signup' onclick='signup()'/>");
}

function createUser(url, json) {

    $.ajax({
        url: url,
        dataType: 'json',
        accept: 'application/json',
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(json) //JSON.stringify(["Яблоко", "Апельсин", "Слива"])
    })
        .done(function(result){
            if(result.exception != 'undefined'){
                alert(result.exception)
            }
            return result;
        })

}
