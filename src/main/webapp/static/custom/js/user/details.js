$(function () {
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
