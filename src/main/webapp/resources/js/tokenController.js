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


        })
        .fail(function () {
            alert("Failed to get token!");
        });

}

function getToken() {
    if (new Date >= $.cookie('best_before')) {
        $.ajax({
            url: '/SpringRestSecurityOauth/oauth/token?grant_type=refresh_token&client_id=restapp&client_secret=restapp&' +
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
                alert("Failed to get token!");
            });

    }

    return $.cookie('access_token');
}

