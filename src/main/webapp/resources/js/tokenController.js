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
            bestBefore.setMinutes(bestBefore.getMinutes() + 1);
            $.cookie('best_before', bestBefore);
            $.cookie('access_token', result.access_token);
            $.cookie('refresh_token', result.refresh_token);
            window.location.reload()
        })
        .fail(function () {
            alert("Failed to get token!");
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
            bestBefore.setMinutes(bestBefore.getMinutes() + 1);
            $.cookie('best_before', bestBefore);
            $.cookie('access_token', result.access_token);
            $.cookie('refresh_token', result.refresh_token);

        })
        .fail(function () {
            alert("Failed to get token!");
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

