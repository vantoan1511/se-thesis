$(document).ready(() => {
    //init ckeditor
    CKEDITOR.plugins.addExternal('video', '/static/ckeditor/plugins/video/', 'plugin.js');
    CKEDITOR.replace('content', {
        extraPlugins: 'video'
    });
    NiceSelect.bind(document.getElementById("categoryCode"));
    NiceSelect.bind(document.getElementById("accessCode"));
})

const handlePreviewButtonClick = (event) => {
    event.preventDefault();
    $('#iframe-container').removeClass("hidden");
    $('body').css('overflow', 'hidden');
}

const handlePreviewCloseButtonClick = (event) => {
    event.preventDefault();
    $('#iframe-container').addClass("hidden");
    $("body").css("overflow", "auto");
}