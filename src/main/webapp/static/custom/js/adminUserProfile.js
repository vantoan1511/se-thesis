$(function () {
    const $deleteAccountBtn = $('#delete-account-btn');
    const $disableAccountBtn = $('#disable-account-btn');
    const $enableAccountBtn = $('#enable-account-btn');
    const $grantPrivileges = $('#grant-privileges-btn');

    $deleteAccountBtn.click((e) => handleDeleteAccountButton(e));
    $disableAccountBtn.click((e) => handleDisableAccountButton(e));
    $enableAccountBtn.click((e) => handleEnableAccountButton(e));
    $grantPrivileges.click(e => handleGrantPrivilegesButton(e));

    validateUserProfile();
    grantPrivilegesFunc();
    loadAvatar();
})

function grantPrivilegesFunc() {
    $('#available-roles').change(function () {
        let selectedRole = $(this).val();
        let selectedRoleText = $(this).find('option:selected').text();

        $('#user-roles').append($('<option>').val(selectedRole).text(selectedRoleText));
        $(this).find('option:selected').remove();
    })

    $('#user-roles').change(function () {
        if ($(this).children().length > 1) {
            let selectedRole = $(this).val();
            let selectedRoleText = $(this).find('option:selected').text();

            $('#available-roles').append($('<option>').val(selectedRole).text(selectedRoleText));
            $(this).find('option:selected').remove();
        } else {
            console.log('Need one role at least');
        }
    })
}

function validateUserProfile() {
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
}

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

function handleGrantPrivilegesButton(e) {
    e.preventDefault();
    let url = location.pathname;
    let parts = url.split('/');
    let username = parts[parts.length - 1];
    console.log(username)
    let grantedRole = [];
    let grantedRoleName = [];
    $('#user-roles option').each(function () {
        grantedRole.push($(this).val());
        grantedRoleName.push($(this).text());
    });
    console.log(grantedRoleName)
    showWarningAlert(`Xác nhận phân vai trò sau ${grantedRoleName}?`, result => {
        if (result.isConfirmed) {
            handlePutRequest(`/api/v1/users/${username}/grant`, grantedRole, () => {
                showSuccessAlert(`Đã phân vai trò ${grantedRoleName} thành công`, () => {
                    location.reload()
                })
            }, xhr => {
                errorCallback(xhr);
            });
        }
    });
}