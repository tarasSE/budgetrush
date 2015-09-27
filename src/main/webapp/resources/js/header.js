function createHeader(placeholderId) {
    $('#' + placeholderId).empty();
    var isAuthorise = $.cookie('is_authorised');

    if (isAuthorise == '1') {
        if ($.cookie('role') == 'ROLE_ADMIN') {
            $('#' + placeholderId).append('<th>' +
                '<input id="users" class="btn" type="button" onclick="getContent(\'/v1/users\', \'content\')" value="Users">' +
                '</th>');


        }
        $('#' + placeholderId).append(
            '<th>' +
            '<input id="accounts" class="btn" type="button"  onclick="getContent(\'/v1/accounts\', \'content\')" value="Accounts"/>' +
            '</th>');
        $('#' + placeholderId).append(
            '<th>' +
            '<input id="currencies" class="btn" type="button"  onclick="getContent(\'/v1/currencies\', \'content\')" value="Currencies"/>' +
            '</th>');
        $('#' + placeholderId).append(
            '<th>' +
            '<input id="contractors" class="btn" type="button"  onclick="getContent(\'/v1/contractors\', \'content\')" value="Contractors"/>' +
            '</th>');
        $('#' + placeholderId).append(
            '<th>' +
            '<input id="categories" class="btn" type="button"  onclick="getContent(\'/v1/categories\', \'content\')" value="Categories"/>' +
            '</th>');
        $('#' + placeholderId).append(
            '<th>' +
            '<input id="orders" class="btn" type="button"  onclick="getContent(\'/v1/orders\', \'content\')" value="Orders"/>' +
            '</th>');
        if ($.cookie('role') == 'ROLE_ADMIN') {
            $('#' + placeholderId).append('<th>Your admin room can be here.</th>');
        }
        $('#' + placeholderId).append('<th>' + '<font color="#6495ed" size="4">' + $.cookie('name') + '  </font>' +
            '<input id="logout" class="btn" type="button" onclick="logout()" value="Logout" />' +
            '</th>');

    }
    else {
        $('#' + placeholderId).append(
            '<th>' +
            '<input id="login" class="btn" type="button" onclick="chooseLogin(\'content\')" value="LogIn">' +
            '</th>');
        $('#' + placeholderId).append(
            '<th>' +
            '<input id="signup" class="btn" type="button" onclick="chooseSignup(\'content\')" value="SignUp">' +
            '</th>');
    }
}