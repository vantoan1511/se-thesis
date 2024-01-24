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