var get = function(url,successCallback,errorCallback){
    $.ajax({
        method: 'GET',
        url: url,
        cache: false,
        success: successCallback,
        error:errorCallback
    });
};

var del = function(url,successCallback,errorCallback){
    $.ajax({
        method: 'DELETE',
        url: url,
        success: successCallback,
        error:errorCallback
    });
};

var post = function(url,contentType,data,successCallback,errorCallback){
    $.ajax({
        method: 'POST',
        contentType: contentType,
        data: data,
        url: url,
        success: successCallback,
        error: errorCallback
    });
};

var put = function(url,contentType,data,successCallback,errorCallback){
    $.ajax({
        method: 'PUT',
        contentType: contentType,
        data: data,
        url: url,
        success: successCallback,
        error: errorCallback
    });
};
