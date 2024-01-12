<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div class="card">
    <div class="card-body row">
        <div class="col-md-auto col-sm-3">
            <button onclick="handleMultipleApproveButtonClick(event)"
                    class="btn btn-block bg-gradient-success" title="Chấp nhận">
                <i class="fas fa-check"></i> Chấp nhận
            </button>
        </div>
        <div class="col-md-auto col-sm-3">
            <button onclick="handleMultipleApproveButtonClick(event)"
                    class="btn btn-block btn-default" title="Từ chối">
                <i class="fas fa-times text-danger"></i> Từ chối
            </button>
        </div>
    </div>
</div>