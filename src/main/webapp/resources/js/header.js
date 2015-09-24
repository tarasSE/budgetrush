function createHeader(placeholderId){
    $('#' + placeholderId).empty();

    var isAuthorise = $.cookie('is_authorised');
    var role = $.cookie('role');
    if (isAuthorise == '1'){
        if(role == 'ROLE_ADMIN'){
        $('#'+placeholderId).append('<th><a onclick="getContent(\'/v1/users\', \'content\')">Users<a/></th>');}
        $('#'+placeholderId).append('<th><a onclick="getContent(\'/v1/accounts\', \'content\')">Accounts<a/></th>');
        $('#'+placeholderId).append('<th><a onclick="getContent(\'/v1/currencies\', \'content\')">Currencies<a/></th>');
        $('#'+placeholderId).append('<th><a onclick="getContent(\'/v1/contractors\', \'content\')">Contractors<a/></th>');
        $('#'+placeholderId).append('<th><a onclick="getContent(\'/v1/categories\', \'content\')">Categories<a/></th>');
        $('#'+placeholderId).append('<th><a onclick="getContent(\'/v1/orders\', \'content\')">Orders<a/></th>');
        $('#'+placeholderId).append('<th><a onclick="logout()">Logout<a/></th>');

    }
    else{
        $('#'+placeholderId).append('<th><a onclick="chooseLogin(\'content\')">LogIn</a></th>');
        $('#'+placeholderId).append('<th><a onclick="chooseSignup(\'content\')">SignUp</a></th>');
    }
}