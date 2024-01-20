$(document).ready(() => {
    //init ckeditor
    CKEDITOR.plugins.addExternal('video', '/static/ckeditor/plugins/video/', 'plugin.js');
    CKEDITOR.replace('text', {
        extraPlugins: 'video'
    });
    NiceSelect.bind(document.getElementById("categoryCode"));
    $('#featured').bootstrapSwitch();
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