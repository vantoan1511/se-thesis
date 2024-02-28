$(function () {
    const $previewThumbnailBtn = $('#preview-thumbnail-btn');

    $previewThumbnailBtn.click(e => previewThumbnail(e));

    ckeditor();
    niceSelectFunc();
    uploadImageFunc();
    bootstrapSwitchFunc();
    adminImageOptionsFunc();
})

function previewThumbnail(e) {
    e.preventDefault();
    let thumbnailUrl = $(e.target).closest('div').siblings().find('input').val();
    handleImageInspect(e, thumbnailUrl);
}

function uploadImageFunc() {
    let $uploadBtn = $('#upload-btn');
    $uploadBtn.click(e => handleUploadButtonClick(e));
}

function adminImageOptionsFunc() {
    const $options = $('.image-options');

    $(".gallery img").click(function (e) {
        e.preventDefault();
        $options.css({
            top: e.clientY,
            left: e.clientX
        }).show();
        $(this).closest('div').append($options);
    });

    $(document).click(function (e) {
        let $img = $('.image-item img');
        let clickedOnImg = false;
        $img.each(function () {
            if ($(this).is(e.target)) {
                clickedOnImg = true;
            }
        });
        if (!clickedOnImg) {
            $options.hide();
        }
    });

    $('#inspect-btn').click(function (e) {
        e.preventDefault();
        let imageUrl = $(this).closest(".image-item").find("img").attr("src");
        handleImageInspect(e, imageUrl);
    });

    $('#set-thumbnail-btn').click(function (e) {
        e.preventDefault();
        let thumbnailUrl = $(this).closest(".image-item").find("img").attr("src");
        $('input[name=thumbnailUrl]').val(thumbnailUrl);
    });

    $('#delete-image-btn').click(function (e) {
        e.preventDefault();
        let imageId = $(this).closest(".image-item").find("img").data("image-id");
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

function bootstrapSwitchFunc() {
    $('#featured').bootstrapSwitch();
}

function niceSelectFunc() {
    NiceSelect.bind(document.getElementById("categoryCode"));
}

function ckeditor() {
    CKEDITOR.plugins.addExternal('video', '/static/ckeditor/plugins/video/', 'plugin.js');
    CKEDITOR.replace('text', {
        extraPlugins: 'video'
    });
}

const handlePreviewButtonClick = (event, alias) => {
    event.preventDefault();
    $('.preview-frame').attr('src', `/${alias}?previewMode=true`)
    $('#iframe-container').removeClass("hidden");
    $('body').css('overflow', 'hidden');
}

const handlePreviewCloseButtonClick = (event) => {
    event.preventDefault();
    $('#iframe-container').addClass("hidden");
    $("body").css("overflow", "auto");
}