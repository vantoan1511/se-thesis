$(function () {
    const $deleteAccountBtn = $('#delete-account-btn');
    const $disableAccountBtn = $('#disable-account-btn');
    const $enableAccountBtn = $('#enable-account-btn');

    $deleteAccountBtn.click((e) => handleDeleteAccountButton(e));
    $disableAccountBtn.click((e) => handleDisableAccountButton(e));
    $enableAccountBtn.click((e) => handleEnableAccountButton(e));

    $('#user-details-form').validate({
        rules: {
            firstName: {
                required: true
            },
            lastName: {
                required: true
            },
            email: {
                email: true,
                required: true
            },
        },
        messages: {
            firstName: {
                required: "Vui lòng nhập họ"
            },
            lastName: {
                required: "Vui lòng nhập tên"
            },
            email: {
                required: "Vui lòng nhập địa chỉ email",
                email: "Vui lòng nhập một địa chỉ email hợp lệ"
            }
        },
        errorElement: 'span',
        errorClass: 'error invalid-feedback',
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        }
    });
    $('#user-account-form').validate({
        rules: {
            password: {
                required: true
            },
            newPW: {
                required: true
            },
            rePW: {
                required: true
            }
        },
        messages: {
            password: {
                required: "Vui lòng nhập mật khẩu cũ"
            },
            newPW: {
                required: "Vui lòng nhập mật khẩu mới"
            },
            rePW: {
                required: "Vui lòng nhập lại mật khẩu mới"
            }
        },
        errorElement: 'span',
        errorClass: 'error invalid-feedback',
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        }
    });
})

function handleDeleteAccountButton(e) {
    e.preventDefault();
    showWarningAlert('Tài khoản này sẽ bị xóa vĩnh viễn và không thể khôi phục', result => {
        if (result.isConfirmed) {
            location.replace(location.href + '/delete');
        }
    })
}

function handleDisableAccountButton(e) {
    e.preventDefault();
    showWarningAlert('Vô hiệu hóa tài khoản?', result => {
        if (result.isConfirmed) {
            location.replace(location.href + '/disable');
        }
    })
}

function handleEnableAccountButton(e) {
    e.preventDefault();
    showWarningAlert('Kích hoạt tài khoản?', result => {
        if (result.isConfirmed) {
            location.replace(location.href + '/enable');
        }
    })
}