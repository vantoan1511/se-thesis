function handleCategorySaveButton(event, selector, saveAndClose, saveAndNew) {
    event.preventDefault();
    let data = getFormData(selector);
    console.log('Form data >> ', data);
    let id = data.id;
    console.log('ID >> ', parseInt(id));
    let successCallback = (result) => {
        showSuccessAlert('Đã lưu thay đổi', () => {
            if (saveAndClose) location.replace('/admin/categories')
            else if (saveAndNew) location.replace('/admin/categories/new')
            else location.replace('/admin/categories/' + result.alias)
        })
    }
    if (id !== '') {
        handlePutRequest('/api/v1/categories', data, (result) => successCallback(result), (xhr) => errorCallback(xhr))
    } else {
        handlePostRequest('/api/v1/categories', data, (result) => successCallback(result), (xhr) => errorCallback(xhr))
    }
}

function handleCategoryDeleteButton(event, id) {
    event.preventDefault();
    let data = [id]
    console.log('IDS >> ', data);
    showWarningAlert('Chuyên mục và các bài viết liên quan sẽ bị xóa và không thể khôi phục', result => {
        if (result.isConfirmed) {
            handleDeleteRequest('/api/v1/categories', data, () => {
                showSuccessAlert('Đã xóa thành công', () => {
                    location.reload()
                })
            }, (xhr) => errorCallback(xhr))
        }
    })
}