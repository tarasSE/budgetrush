function signup() {
    var nameElement1 = document.getElementById("name");
    var name = nameElement1.value;

    if (!validateName(name)) {

        return;
    }

    var nameElement2 = document.getElementById("password");
    var password = nameElement2.value;

    if (!validatePassword(password)) {

        return;
    }

    var jsonUser = {name: name, password: password, role: 0};

    createUser("/v1/users/", jsonUser);


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

    $('#login').append("<div id='description'> <p><font color='black' size='4'>Login</font></p> </div>");

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

    $('#signup').append("<div id='description'> <p><font color='black' size='4'>Signup</font></p> </div>");

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
        .done(function (result) {
            if (typeof (result.exception) != 'undefined') {
                alert(result.exception)
            }
            else alert('Registration complete. Now you can log in.');
        })

}


function validatePassword(value) {
    if (value.length < 4 || value.length > 20) {
        alert('Password length must be beetween 4 and 20 characters!');
        return false;
    }

    var reg = /^[a-zA-Z0-9-~!@#$%^&*()_+=]+$/

    if (value.search(reg) == 0) {
        return true;
    }
    else{
        alert("Password can includes only latin characters, numbers and \" ~!@#$%^&*()_+= \" symbols!");
    } return false;
}

function validateName(value) {

    if (value.length < 4 || value.length > 20) {
        alert('Name length must be beetween 4 and 20 characters!');
        return false;
    }
    var reg = /^[a-zA-Z0-9-_]+$/

    if (value.search(reg) == 0) {
        return true;
    }
    else{
        alert("Name can includes only latin characters, numbers and \" _ \" symbol!");
        return false;
    }
}
