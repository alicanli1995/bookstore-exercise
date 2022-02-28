let get = (url, successCallback, errorCallback) => {
	$.ajax({
		method: 'GET',
		url: url,
		cache: false,
		success: successCallback,
		error: errorCallback
	});
};

let del = (url, successCallback, errorCallback) => {
	$.ajax({
		method: 'DELETE',
		url: url,
		success: successCallback,
		error: errorCallback
	});
};

let post = (url, contentType, data, successCallback, errorCallback) => {
	$.ajax({
		method: 'POST',
		contentType: contentType,
		data: data,
		url: url,
		success: successCallback,
		error: errorCallback
	});
};

let put = (url, contentType, data, successCallback, errorCallback) => {
	$.ajax({
		method: 'PUT',
		contentType: contentType,
		data: data,
		url: url,
		success: successCallback,
		error: errorCallback
	});
};