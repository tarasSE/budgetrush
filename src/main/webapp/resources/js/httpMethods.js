function sendPost(url, json) {

    $.ajax({
        url: url,
        dataType: 'json',
        accept: 'application/json',
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(json) //JSON.stringify(["Яблоко", "Апельсин", "Слива"])
    });

}

function sendDelete(url) {
    $.ajax({
        url: url,
        type: 'DELETE'
    });
}

function sendPut(url, json) {

    $.ajax({
        url: url,
        dataType: 'json',
        accept: 'application/json',
        type: 'PUT',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(json)
    });

}