function createHeader(placeholderId) {
    $('#' + placeholderId).empty();
    var isAuthorise = $.cookie('is_authorised');

    if (isAuthorise == '1') {
        if ($.cookie('role') == 'ROLE_ADMIN') {
            $('#' + placeholderId).append('<th>' +
                '<input id="logout" type="button" onclick="getContent(\'/v1/users\', \'content\')" value="Users">' +
                '</th>');


        }
        $('#' + placeholderId).append(
            '<th>' +
            '<input id="logout" type="button"  onclick="getContent(\'/v1/accounts\', \'content\')" value="Accounts"/>' +
            '</th>');
        $('#' + placeholderId).append(
            '<th>' +
            '<input id="logout" type="button"  onclick="getContent(\'/v1/currencies\', \'content\')"value="Currencies"/>' +
            '</th>');
        $('#' + placeholderId).append(
            '<th>' +
            '<input id="logout" type="button"  onclick="getContent(\'/v1/contractors\', \'content\')" value="Contractors"/>' +
            '</th>');
        $('#' + placeholderId).append(
            '<th>' +
            '<input id="logout" type="button"  onclick="getContent(\'/v1/categories\', \'content\')" value="Categories"/>' +
            '</th>');
        $('#' + placeholderId).append(
            '<th>' +
            '<input id="logout" type="button"  onclick="getContent(\'/v1/orders\', \'content\')" value="Orders"/>' +
            '</th>');
        if ($.cookie('role') == 'ROLE_ADMIN') {
            $('#' + placeholderId).append('<th>Your admin room can be here.</th>');
        }
        $('#' + placeholderId).append('<th>' +
            '<input id="logout" type="button" onclick="logout()" value="' + $.cookie('name') + ' Logout" />' +
            '</th>');

    }
    else {
        $('#' + placeholderId).append(
            '<th>' +
            '<input id="logout" type="button" onclick="chooseLogin(\'content\')" value="LogIn">' +
            '</th>');
        $('#' + placeholderId).append(
            '<th>' +
            '<input id="logout" type="button" onclick="chooseSignup(\'content\')" value="SignUp">' +
            '</th>');
    }
}