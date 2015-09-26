function requestToken(name, password) {
    $.ajax({
        url: '/oauth/token?client_id=rest_id&client_secret=rest_key&grant_type=password&' +
        'username=' + name +
        '&password=' + password,
        dataType: 'json',
        type: 'POST'
    })
        .done(function (result) {
            var bestBefore = new Date();
            bestBefore.setMinutes(bestBefore.getMinutes() + 29);
            $.cookie('best_before', bestBefore);
            $.cookie('access_token', result.access_token);
            $.cookie('refresh_token', result.refresh_token);
            $.cookie('is_authorised', 1);
            $.cookie('name', name);

            window.location.reload()
        })
        .fail(function () {
            alert("Incorrect name or password!");
        });

}


function refreshToken() {
    $.ajax({
        url: '/oauth/token?grant_type=refresh_token&client_id=rest_id&client_secret=rest_key&' +
        'refresh_token=' + $.cookie('refresh_token'),
        dataType: 'json',
        type: 'POST'
    })
        .done(function (result) {
            var bestBefore = new Date();
            bestBefore.setMinutes(bestBefore.getMinutes() + 29);
            $.cookie('best_before', bestBefore);
            $.cookie('access_token', result.access_token);
            $.cookie('refresh_token', result.refresh_token);

        })
        .fail(function () {
            var cookies = document.cookie.split(";");
            for (var i = 0; i < cookies.length; i++) {
                var equals = cookies[i].indexOf("=");
                var name = equals > -1 ? cookies[i].substr(0, equals) : cookies[i];
                document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
            }
            window.location.reload();
        });

}
function getToken() {
    var newDate = new Date();
    var oldDate = Date.parse($.cookie('best_before'));

    if (newDate >= oldDate) {
        refreshToken();
    }

    return $.cookie('access_token');
}

function getRole(name, password){
    $.ajax({
        url: '/v1/users/role/' + name +
        '&'+
        password,
        dataType: 'json',
        type: 'GET'
    })
        .done(function (data) {
            $.cookie('role', data.role);
        });
}

