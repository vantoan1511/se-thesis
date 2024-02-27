$(function () {
    const $deleteAccountBtn = $('#delete-my-account-btn');
    const $uploadBtn = $('#upload-btn');

    $deleteAccountBtn.click(e => handleDeleteMyAccountButton(e));
    $uploadBtn.click(e => handleUploadButton(e));

    validateUserProfile();
    imageOptionsFunc();
    loadAvatar();
});

function imageOptionsFunc() {
    const $options = $('.image-options');

    $(".gallery a").click(function (e) {
        e.preventDefault();
        $options.css({
            top: e.clientY,
            left: e.clientX
        }).show();
        $(this).closest('li').append($options);
    })

    $(document).click(function (e) {
        if (!$(e.target).closest(".gallery").length) {
            $options.hide();
        }
    });

    $('#inspect-btn').click(function (e) {
        e.preventDefault();
        let imageUrl = $(this).closest("li").find("a").attr("href");
        handleImageInspect(e, imageUrl);
    });

    $('#set-avatar-btn').click(function (e) {
        e.preventDefault();
        let newAvatarUrl = $(this).closest('li').find('a').attr('href');
        $('input[name=avatarUrl]').val(newAvatarUrl);
        $('.avatar').attr('src', newAvatarUrl);
    });

    $('#delete-image-btn').click(function (e) {
        e.preventDefault();
        let imageId = $(this).closest('li').find('a').data("image-id");
        let data = [imageId];
        showWarningAlert('Xóa mục này?', result => {
            if (result.isConfirmed) {
                handleDeleteRequest('/api/v1/files', data, () => {
                    location.reload()
                }, (xhr) => errorCallback(xhr));
            }
        });
    });
}

function validateUserProfile() {
    $('#user-profile-form').validate({
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
        errorClass: 'help-block',
        highlight: function (element, errorClass, validClass) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).closest('.form-group').removeClass('has-error');
        }
    });
}

function handleDeleteMyAccountButton(event) {
    event.preventDefault();
    showWarningAlert('Tài khoản của bạn sẽ bị xóa vĩnh viễn và không thể khôi phục', (result) => {
        if (result.isConfirmed) {
            location.replace(location.href + '/delete');
        }
    });
}