const apiUrl = '/public/api/v1/articles/';
const articleId = $('#id').data('article-id');
const limit = 2;
const loggedInUsername = $('#logged-user').data('username');
let page = 1;
let pageRequest = {};
let targetUrl;

$(document).ready(() => {
    loadReviews()
})

const loadReviews = () => {
    targetUrl = `${apiUrl}${articleId}/reviews?page=${page}&limit=${limit}`;
    getReviews(targetUrl)
    const $loadMore = $('#load-more-reviews-button');
    if (!pageRequest.last) {
        page += 1;
    } else {
        $loadMore.addClass('hidden')
    }
}

const getReviews = (target) => {
    $.get(target, (data) => {
        pageRequest = data;
        console.log('GET >>', pageRequest)
        createReviews(data.content)
    })
}

const createReviews = (reviews) => {
    const $commentList = $('.comment-list');
    reviews.forEach(review => {
        const $item = $('<div>').attr('id', review.id).addClass('item');
        const $user = $('<div>').addClass('user');
        const $figure = $('<figure>');
        const $img = $('<img>').attr('src', '/static/web/images/img01.jpg');
        const $details = $('<div>').addClass('details');
        const $username = $('<h5>').addClass('name').text(`${review.userFirstName} ${review.userLastName}`);
        const $time = $('<div>').addClass('time').text(new Date(review.createdAt).toLocaleDateString('vi-VN', defaultDateFormatOptions));
        const $description = $('<div>').addClass('description').html(review.text);
        const $footer = $('<footer>');
        const $reply = $('<a>').addClass('reply-button').attr('href', '#leave-review')
            .attr('onclick', 'handleReplyButton(this)').attr('data-item-id', review.id)
            .html(`Trả lời <i class="ion-reply"></i>`)
        const $edit = $('<a>').attr('href', '#leave-review')
            .attr('onclick', 'handleUpdateReviewButton(this)').attr('data-item-id', review.id)
            .html(`Sửa <i class="ion-edit"></i>`)
        const $delete = $('<a>').attr('href', '#leave-review')
            .attr('onclick', 'handleDeleteReviewButton(this)').attr('data-item-id', review.id)
            .html(`Xóa <i class="ion-android-delete"></i>`)

        let $parentText = $('<div>')
        if (review.parentId !== null) {
            $parentText.html(`<blockquote>${review.parentText}</blockquote>`);
        }

        $footer.append($reply)
        if (loggedInUsername === review.createdBy) {
            $footer.append($edit, $delete)
        }
        $details.append($username, $time, $parentText, $description, $footer);
        $figure.append($img);
        $user.append($figure, $details)
        $item.append($user)
        $commentList.append($item)
    })
}

const handleReplyButton = (self, isReply = true) => {
    if (isReply) {
        let id = $(self).data('item-id');
        let replyText = $('#' + id + ' .description').text()
        console.log('Reply to >> ', replyText)
        $('#reply-text').text(replyText)
        $('input[name=parentId]').attr('value', id);
        $('#reply-to-text').removeClass('hidden')
    } else {
        $('#reply-text').text(null)
        $('input[name=parentId]').attr('value', '');
        $('#reply-to-text').addClass('hidden')
    }
}

const handleDeleteReviewButton = (self) => {
    let id = $(self).data('item-id');
    let data = []
    data.push(id);
    console.log('Delete >>', data)
    showWarningAlert('Bình luận sẽ bị xóa', (result) => {
        if (result.isConfirmed) {
            handleDeleteRequest('/api/v1/reviews', data, () => {
                showSuccessAlert('Đã xóa', () => {
                    location.reload()
                })
            }, (xhr) => errorCallback(xhr))
        }
    })
}

const handleUpdateReviewButton = (self) => {
    let id = $(self).data('item-id');
    $('input[name=id]').val(id);
    let text = $('#' + id + ' .description').text();
    $('#text').text(text)
    console.log('Update on >> ', id + ': ' + text)
}

const handleReviewSubmitButton = (event, formSelector) => {
    event.preventDefault();
    let data = getFormData(formSelector);
    let reviewId = data["id"];
    console.log('Review >> ', data)
    let url = '/api/v1/reviews';
    if (reviewId === '') {
        handlePostRequest(url, data, () => {
            showSuccessAlert('Đã đăng bình luận', () => {
                location.reload()
            })
        }, (xhr) => {
            showBottomErrorToast(getResponseTextAsJSON(xhr).message, 2000)
        })
    } else {
        handlePutRequest(url, data, () => {
            showSuccessAlert('Đã sửa bình luận', () => {
                location.reload()
            })
        }, (xhr) => errorCallback(xhr))
    }
}