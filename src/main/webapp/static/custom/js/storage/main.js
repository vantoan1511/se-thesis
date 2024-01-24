function handleImageDeleteButton(event, id) {
    event.preventDefault();
    let data = [id];
    console.log(data)
    showWarningAlert('Mục đã chọn sẽ bị xóa vĩnh viễn', result => {
        if (result.isConfirmed) {
            handleDeleteRequest('/api/v1/files', data, () => {
                showSuccessAlert('Đã xóa thành công', () => {
                    location.reload()
                }, (xhr) => errorCallback(xhr))
            })
        }
    })
}

const handleImageTrashButtonClick = (event, elements) => {
    event.preventDefault();
    if (elements.length === 0) {
        showErrorToast('Không có mục nào được chọn', 1500)
        return
    }
    let ids = getElementsID(elements)
    showWarningAlert('Mục đã chọn sẽ bị xóa vĩnh viễn', result => {
        if (result.isConfirmed) {
            handleDeleteRequest('/api/v1/files', ids,
                () => {
                    showSuccessAlert('Đã xóa ' + ids.length + ' mục', () => {
                        location.reload();
                    })
                }, (xhr) => errorCallback(xhr))
        }
    })
}

function handleImageDetailsSaveButton(event, selector, saveAndClose, saveAndNew) {
    event.preventDefault();
    let data = getFormData(selector);
    console.log('Form data >> ', data);
    let id = data.id;
    console.log('ID >> ', parseInt(id));
    let successCallback = (result) => {
        showSuccessAlert('Đã lưu thay đổi', () => {
            if (saveAndClose) location.replace('/admin/my-storage')
            else if (saveAndNew) location.replace('/admin/my-storage/new')
            else location.replace('/admin/my-storage/' + result.alias)
        })
    }
    if (id !== '') {
        handlePutRequest('/api/v1/files', data, (result) => successCallback(result), (xhr) => errorCallback(xhr))
    } else {
        handlePostRequest('/api/v1/files', data, (result) => successCallback(result), (xhr) => errorCallback(xhr))
    }
}
