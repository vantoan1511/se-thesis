<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<c:set var="pageTitle" value="Kho lưu trữ"/>
<c:set var="breadcrumbs" value="${['Trang chủ', pageTitle]}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
</head>
<body>
<div class="content-wrapper">
    <%@ include file="../components/admin/contentHeader.jsp" %>

    <section class="content">
        <!-- Functionality bar -->
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-3">
                        <button onclick="handleUploadButtonClick(event)"
                                class="btn btn-block bg-gradient-success"><i class="ri-upload-line"></i> Tải lên
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="card">
            <div class="card-header">
                <form id="page-request-form"
                      method="get">
                    <div class="row">
                        <div class="col-md-auto">
                            <select name="by"
                                    id="sort-by"
                                    class="col form-control custom-select">
                                <option value="title">Tiêu đề</option>
                                <option value="createdAt">Ngày tạo</option>
                                <option value="lastModifiedAt">Lần sửa đổi cuối</option>
                            </select>
                        </div>
                        <div class="col-md-auto">
                            <select name="order"
                                    id="sort-order"
                                    class="col form-control custom-select">
                                <option value="ASC">Tăng dần</option>
                                <option value="DESC">Giảm dần</option>
                            </select>
                        </div>
                        <div class="col-md-auto">
                            <select id="limit-select"
                                    name="size"
                                    class="col form-control custom-select">
                                <option value="2">2</option>
                                <option value="10">10</option>
                                <option value="20">20</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" name="page" value="1">
                </form>
            </div>
            <div class="card-body">
                <div class="row text-center">
                    <c:if test="${files.numberOfElements lt 1}">
                        <i>Trống</i>
                    </c:if>
                    <div class="gallery row">
                        <div class="image-options">
                            <a id="inspect-btn"
                               class="dropdown-item btn btn-default btn-sm">
                                <i class="ri-search-line text-success"></i> Xem trước
                            </a>
                            <a id="edit-btn"
                               class="btn btn-sm dropdown-item"
                               title="Click để xem chi tiết">
                                <i class="ri-edit-box-line text-success"></i> Chi tiết
                            </a>
                            <a id="copy-url-btn"
                               title="Click để copy url"
                               class="btn btn-sm btn-default dropdown-item">
                                <i class="ri-clipboard-line text-success"></i> Copy Url
                            </a>
                            <a id="delete-image-btn"
                               class="dropdown-item btn btn-default btn-sm">
                                <i class="ri-delete-bin-line text-danger"></i> Xóa
                            </a>
                        </div>
                        <c:forEach var="image"
                                   items="${files.content}">
                            <div class="image-item col-md-auto">
                                <img style="object-fit: cover"
                                     data-image-id="${image.id}"
                                     data-image-alias="${image.alias}"
                                     src="${image.url}"
                                     height="200"
                                     width="200">
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="card-footer clear-fix row">
                <div class="col-auto">
                    <span>Trang ${files.number + 1} - Hiển thị ${files.numberOfElements} trong số ${files.totalElements} mục.</span>
                </div>
                <div class="col-auto">
                    <ul id="pagination" class="pagination-sm"></ul>
                </div>
            </div>
        </div>
    </section>
</div>
<script src="<c:url value="/static/custom/js/gallery/gallery.js"/>"></script>
<script>
    $(function () {
        paginationFunc();
        adminImageOptionsFunc();
    })

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

        $('#edit-btn').click(function (e) {
            e.preventDefault();
            let imageAlias = $(this).closest(".image-item").find("img").data("image-alias");
            location.replace(location.href + '/' + imageAlias);
        })

        $('#copy-url-btn').click(function (e) {
            e.preventDefault();
            let imageUrl = $(this).closest(".image-item").find("img").attr("src");
            handleCopyToClipboard(e, imageUrl);
        })

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

    function paginationFunc() {
        const sortBy = `${sortBy}`;
        const sortOrder = `${sortOrder}`;
        const size = `${files.size}`;
        const $sortBySelect = $('#sort-by');
        const $sortOrderSelect = $('#sort-order');
        const $sizeSelect = $('#limit-select');
        const $pageRequest = $('#page-request-form');
        const $curPage = $('input[name=page]');
        const $curSize = $('input[name=size]');

        const totalItems = ${files.totalElements};
        const currentPage = ${files.number+1};
        const totalPages = ${files.totalPages};

        $sortBySelect.find('option').each(function () {
            if (sortBy === $(this).val()) {
                $(this).attr('selected', true);
            }
        })

        $sortOrderSelect.find('option').each(function () {
            if (sortOrder === $(this).val()) {
                $(this).attr('selected', true);
            }
        })

        $sizeSelect.find('option').each(function () {
            if (size === $(this).val()) {
                $(this).attr('selected', true);
            }
        })

        $sortBySelect.change(function () {
            $pageRequest.submit();
        })

        $sortOrderSelect.change(function () {
            $pageRequest.submit();
        })

        $sizeSelect.change(function () {
            $pageRequest.submit();
        })

        if (totalItems > 0) {
            $('#pagination').twbsPagination({
                startPage: currentPage,
                totalPages: totalPages,
                visiblePages: 7,
                first: 'Đầu',
                last: 'Cuối',
                next: 'Tiếp',
                prev: 'Lùi',
                onPageClick: (event, page) => {
                    if (currentPage !== page) {
                        $curPage.val(page);
                        $pageRequest.submit();
                    }
                }
            });
        }
    }
</script>
</body>
</html>
