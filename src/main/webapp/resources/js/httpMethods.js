function sendGet(url) {

    $.ajax({
        url: url,
        type: 'GET'
    })
        .done(function(result){
            return result;
        })
        .fail(function(){
            return "Failed to GET data!";
        });
}

function sendPost(url, json) {

    $.ajax({
        url: url,
        dataType: 'json',
        accept: 'application/json',
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(json) //JSON.stringify(["Яблоко", "Апельсин", "Слива"])
    })
        .done(function(result){
            return result;
        })
        .fail(function(){
            return "Failed to POST data!";
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
    })
        .done(function(result){
            return result;
        })
        .fail(function(){
            return "Failed to PUT data!";
        });

}


function sendDelete(url) {
    $.ajax({
        url: url,
        type: 'DELETE'
    })
        .done(function(){
            return "Successfully deleted."
        })
        .fail(function(){
            return "Failed to DELETE data!"
        });
}