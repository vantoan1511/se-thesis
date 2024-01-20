function showSuccessAlert(text, callback) {
    Swal.fire({
        icon: 'success',
        title: 'Thành công',
        text: text,
        allowOutsideClick: false,
    }).then(callback);
}

function showWarningAlert(text, callback) {
    Swal.fire({
        icon: 'warning',
        title: 'Lưu ý',
        text: text,
        allowOutsideClick: false,
        showCancelButton: true,
        cancelButtonText: 'Hủy'
    }).then(callback);
}

function showSuccessToast(text, timer) {
    Swal.fire({
        toast: true,
        position: 'top-end',
        icon: 'success',
        showConfirmButton: false,
        text: text,
        timer: timer
    })
}

function showErrorToast(text, timer) {
    Swal.fire({
        toast: true,
        position: 'top-end',
        icon: 'error',
        showConfirmButton: false,
        text: text,
        timer: timer
    })
}

function showBottomErrorToast(text, timer) {
    Swal.fire({
        toast: true,
        position: 'bottom-end',
        icon: 'error',
        showConfirmButton: false,
        text: text,
        timer: timer
    })
}

const getResponseTextAsJSON = (XmlHttpRequest) => {
    try {
        return JSON.parse(XmlHttpRequest.responseText);
    } catch (e) {
        return {message: 'Lỗi hệ thống không xác định'};
    }
}

const errorCallback = (xhr) => {
    showErrorToast(getResponseTextAsJSON(xhr).message, 3000)
}

//Http request utils
const handlePostRequest = (url, data, success, error) => {
    handleRestRequest({
        url: url,
        type: 'POST',
        data: data,
        success: success,
        error: error
    })
}
const handlePutRequest = (url, data, success, error) => {
    handleRestRequest({
        url: url,
        type: 'PUT',
        data: data,
        success: success,
        error: error
    })
}
const handleDeleteRequest = (url, data, success, error) => {
    handleRestRequest({
        url: url,
        type: 'DELETE',
        data: data,
        success: success,
        error: error
    })
}
const handleRestRequest = ({url, type, data, success, error}) => {
    $.ajax({
        url: url,
        type: type,
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: success,
        error: error
    })
}

//UI elements Utils
const handleSelectAllCheckboxClick = (self, selector) => {
    $(selector).prop('checked', self.checked);
}

const handleSingleCheckboxClick = (parentSelector, otherSelector) => {
    $(parentSelector).prop('checked', $(otherSelector + ':checked').length === $(otherSelector).length);
}

const getElementsID = (selector) => {
    let ids = [];
    ids = $(selector).map(function () {
        return this.id;
    }).get();
    return ids;
}

const getFormData = (formSelector) => {
    let formData = $(formSelector).serializeArray();
    let data = {};
    $.each(formData, (i, v) => data[v.name] = v.value);
    return data;
}
const getCKEditorContent = () => {
    return CKEDITOR.instances.text.getData();
}

const handleImageInspect = (event, url) => {
    event.preventDefault();
    Swal.fire({
        imageUrl: url,
        imageAlt: 'image'
    })
}

const handleCopyToClipboard = (event, url) => {
    event.preventDefault();
    navigator.clipboard.writeText(url).then(r => {
        showSuccessToast('Đã copy ' + url, 2000)
    });
}

const defaultDateFormatOptions = {
    weekday: 'long',    // EEEE
    day: '2-digit',     // dd
    month: '2-digit',   // MM
    year: 'numeric',    // yyyy
    hour: '2-digit',    // HH
    minute: '2-digit'   // mm
};