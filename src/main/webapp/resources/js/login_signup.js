function signup() {
    var nameElement1 = document.getElementById("name");
    var name = nameElement1.value;

    var nameElement2 = document.getElementById("password");
    var password = nameElement2.value;

    var json = {name: name, password: password};

    sendPost("/v1/users/", json);

    login(name, password);

    window.location.replace("/content");

}

function login() {
    var nameElement1 = document.getElementById("name");
    var name = nameElement1.value;

    var nameElement2 = document.getElementById("password");
    var password = nameElement2.value;

    requestToken(name, password);

    console.log('Login successful');
}

function chooseLogin(placeholderId) {

    var pleceholderClass = $('#' + placeholderId).attr('class');
    $('div.' + pleceholderClass).empty();

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
    $('div.' + pleceholderClass).empty();
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

