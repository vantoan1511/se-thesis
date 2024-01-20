$(document).ready(() => {
    const currentPath = location.pathname + location.search
    const navLinks = document.querySelectorAll('.nav-link');
    navLinks.forEach(link => {
        const href = link.getAttribute('href');
        if (href === currentPath) {
            link.classList.add('active');
        }
    });

    $(window).scroll(function () {
        if ($(this).scrollTop() > 20) {
            $('#back-to-top').fadeIn();
        } else {
            $('#back-to-top').fadeOut();
        }
    });
});

const scrollToTop = () => {
    if ($(window).scrollTop() > 20) {
        $('body, html').animate({
            scrollTop: 0
        }, 800)
    }
}

/**
 * Listing article page
 */

const handleTrashButtonClick = (event, id) => {
    event.preventDefault();
    showWarningAlert('Mục được chọn sẽ được chuyển vào thùng rác trước khi bị xóa hoàn toàn', (result) => {
        if (result.isConfirmed) {
            handlePutRequest('/api/v1/articles/' + id + '/trash', undefined, () => {
                showSuccessAlert('Đã chuyển 1 mục vào thùng rác', () => {
                    location.reload()
                })
            }, (xhr) => errorCallback(xhr));
        }
    })
}
const handleRestoreButtonClick = (event, id) => {
    event.preventDefault();
    showWarningAlert('Khôi phục mục này', (result) => {
        if (result.isConfirmed) {
            handlePutRequest('/api/v1/articles/' + id + '/restore', undefined, () => {
                showSuccessAlert('Đã khôi phục 1 mục', () => {
                    location.reload()
                })
            }, (xhr) => errorCallback(xhr));
        }
    })
}

const handleDeleteButtonClick = (event, id) => {
    event.preventDefault();
    showWarningAlert('Mục được chọn sẽ bị xóa vĩnh viễn', (result) => {
        if (result.isConfirmed) {
            handleDeleteRequest('/api/v1/articles/' + id, undefined, () => {
                showSuccessAlert('Đã xóa 1 mục', () => {
                    location.reload()
                })
            }, (xhr) => errorCallback(xhr))
        }
    })
}

const handleMultipleTrashButtonClick = (event, selector) => {
    event.preventDefault();
    let ids = getElementsID(selector);
    if (ids.length === 0) {
        showErrorToast('Không có mục nào được chọn', 2000)
        return;
    }
    showWarningAlert(ids.length + ' mục sẽ được chuyển vào thùng rác trước khi bị xóa hoàn toàn', (result) => {
        if (result.isConfirmed)
            handlePutRequest('/api/v1/articles/trash', ids,
                () => {
                    showSuccessAlert('Đã chuyển ' + ids.length + ' mục vào thùng rác', () => {
                        location.reload()
                    })
                }, (xhr) => errorCallback(xhr));
    })
}

const handleMultipleRestoreButton = (event, selector) => {
    event.preventDefault();
    let ids = getElementsID(selector);
    if (ids.length === 0) {
        showErrorToast('Không có mục nào được chọn', 2000)
        return;
    }
    showWarningAlert(ids.length + ' mục sẽ được khôi phục', (result) => {
        if (result.isConfirmed)
            handlePutRequest('/api/v1/articles/restore', ids,
                () => {
                    showSuccessAlert(ids.length + ' mục đã được khôi phục', () => {
                        location.reload()
                    })
                }, (xhr) => errorCallback(xhr));
    })
}
const handleMultipleDeleteButtonClick = (event, selector) => {
    event.preventDefault();
    let ids = getElementsID(selector);
    if (ids.length === 0) {
        showErrorToast('Không có mục nào được chọn', 2000)
        return;
    }
    showWarningAlert(ids.length + ' mục sẽ bị xóa vĩnh viễn', (result) => {
        if (result.isConfirmed)
            handleDeleteRequest('/api/v1/articles', ids,
                () => {
                    showSuccessAlert(ids.length + ' mục đã được xóa', () => {
                        location.reload()
                    })
                }, (xhr) => errorCallback(xhr));
    })
}

const handleRefuseButtonClick = (event, id) => {
    event.preventDefault();
    showWarningAlert('Từ chối yêu cầu đăng tải bài viết này?', result => {
        if (result.isConfirmed) {
            handlePutRequest('/api/v1/articles/' + id + '/refuse', undefined,
                () => {
                    showSuccessAlert('Đã từ chối yêu cầu', () => {
                        location.reload()
                    })
                }, (xhr) => errorCallback(xhr));
        }
    })
}

const handleMultipleApproveButtonClick = (event) => {
    event.preventDefault();
    showErrorToast('Chức năng đang bảo trì', 2000)
}


/**
 * Details article page
 */

const handleSaveButtonClick = (event, formSelector, saveAndClose, saveAndNew) => {
    event.preventDefault();
    let data = getFormData(formSelector);
    data["text"] = getCKEditorContent();
    let id = data["id"];
    console.log(data);
    console.log("ID >> ", id)
    let successCallback = (result) => {
        showSuccessAlert('Các thay đổi đã được lưu', () => {
            if (saveAndClose) location.replace('/admin/articles')
            else if (saveAndNew) location.replace('/admin/articles/new')
            else location.replace('/admin/articles/' + result.id)
        })
    }
    if (id !== '') {
        handlePutRequest('/api/v1/articles', data, (result) => successCallback(result), (xhr) => errorCallback(xhr))
    } else {
        handlePostRequest('/api/v1/articles', data, (result) => successCallback(result), (xhr) => errorCallback(xhr))
    }
}

const handleArticleEditButton = (event, id) => {
    event.preventDefault();
    let url = `/api/v1/articles/${id}/edit`;
    let successText = 'Đã chuyển sang chế độ chỉnh sửa'
    showWarningAlert('Bài viết sẽ được lưu ở trạng thái Nháp và sẽ phải gửi xét duyệt lại', (result) => {
        if (result.isConfirmed) {
            handlePutRequest(url, undefined, () => {
                showSuccessAlert(successText, () => {
                    location.reload()
                })
            }, (xhr) => errorCallback(xhr))
        }
    })
}

const handleArticleSubmitButton = (event, id, publish = true) => {
    event.preventDefault();
    let url = publish ? '/api/v1/articles/' + id + '/submit' : '/api/v1/articles/' + id + '/reject'
    let successText = publish ? 'Đã gửi yêu cầu đăng tải bài viết' : 'Đã hủy đăng tải bài viết'
    console.log(url)
    handlePutRequest(url, undefined,
        () => {
            showSuccessAlert(successText, () => {
                location.reload()
            })
        }, (xhr) => errorCallback(xhr))
}
const handleArticlePublishButton = (event, id, publish = true) => {
    event.preventDefault();
    let url = publish ? '/api/v1/articles/' + id + '/publish' : '/api/v1/articles/' + id + '/unpublish'
    let successText = publish ? 'Đã đăng tải bài viết' : 'Đã hủy đăng tải bài viết'
    console.log(url)
    handlePutRequest(url, undefined,
        () => {
            showSuccessAlert(successText, () => {
                location.reload()
            })
        }, (xhr) => errorCallback(xhr))
}
const handleApproveButtonClick = (event, id) => {
    event.preventDefault();
    showWarningAlert('Chấp nhận yêu cầu đăng tải bài viết', result => {
        if (result.isConfirmed) {
            handlePutRequest('/api/v1/articles/' + id + '/approve', undefined, () => {
                location.reload()
            }, (xhr) => errorCallback(xhr))
        }
    })
}

/**
 * Storage page
 */
const handleUploadButtonClick = async (e) => {
    e.preventDefault();
    const {value: file} = await Swal.fire({
        title: "Chọn hình ảnh",
        input: "file",
        inputValidator: ((value) => {
            return new Promise((resolve) => {
                if (value !== null) {
                    resolve();
                } else {
                    resolve("Không có file nào được chọn");
                }
            });
        }),
        confirmButtonText: "Tiếp",
        inputAttributes: {
            "accept": "image/*",
            "aria-label": "Upload your profile picture"
        }
    });

    if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
            Swal.fire({
                input: 'text',
                inputPlaceholder: 'Tiêu đề',
                inputValue: file.name,
                allowOutsideClick: false,
                imageUrl: e.target.result,
                imageAlt: "An image will be uploaded",
                showCancelButton: true,
                confirmButtonText: 'Tải lên'
            }).then((result) => {
                if (result.isConfirmed) {
                    const titleInput = $('#swal2-input').val();
                    const formData = new FormData();
                    formData.append('file', file);
                    formData.append('title', titleInput);
                    $.ajax({
                        url: '/api/v1/media',
                        type: 'POST',
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: () => {
                            showSuccessAlert('Tải lên thành công', () => {
                                location.reload()
                            })
                        },
                        error: (xhr) => errorCallback(xhr)
                    })
                }
            });
        };
        reader.readAsDataURL(file);
    }
}

const handleImageTrashButtonClick = (event, elements) => {
    event.preventDefault();
    if (elements.length === 0) {
        showErrorToast('Không có mục nào được chọn', 1500)
        return
    }
    let ids = getElementsID(elements)
    handleDeleteRequest('/api/v1/media', ids,
        () => {
            showSuccessAlert('Đã chuyển ' + ids.length + ' vào thùng rác', () => {
                location.reload();
            })
        }, (xhr) => errorCallback(xhr))
}

/**
 * Web single article
 */

const handleLoginButton = (event, self) => {
    event.preventDefault();
    let nextUrl = location.href;
    location.replace(location.origin + '/login?nextUrl=' + nextUrl + '#comments-section')
}