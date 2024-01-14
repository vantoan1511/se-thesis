<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../../../common/taglib.jsp" %>

<!-- Start nav -->
<nav class="menu">
    <div class="container">
        <div class="brand">
            <a href="#">
                <img src="/static/web/images/logo.png" alt="Magz Logo">
            </a>
        </div>
        <div class="mobile-toggle">
            <a href="#" data-toggle="menu" data-target="#menu-list"><i class="ion-navicon-round"></i></a>
        </div>
        <div class="mobile-toggle">
            <a href="#" data-toggle="sidebar" data-target="#sidebar"><i class="ion-ios-arrow-left"></i></a>
        </div>
        <div id="menu-list">
            <ul class="nav-list">
                <li class="for-tablet nav-title"><a>Menu</a></li>
                <sec:authorize access="isAnonymous()">
                    <li class="for-tablet"><a href="/login">Login</a></li>
                    <li class="for-tablet"><a href="/register">Register</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li class="for-tablet">
                        <a href="#">NGUYEN VAN TOAN
                        </a>
                    </li>
                    <li class="for-tablet"><a href="/logout">Logout</a></li>
                </sec:authorize>
                <li><a href="/home">Trang chủ</a></li>
                <li class="dropdown magz-dropdown magz-dropdown-megamenu">
                    <a href="#">Tất cả <i class="ion-ios-arrow-right"></i></a>
                    <div class="dropdown-menu megamenu">
                        <div class="megamenu-inner">
                            <div class="row">
                                <div class="col-md-3">
                                    <h2 class="megamenu-title">Column 1</h2>
                                    <ul class="vertical-menu">
                                        <li><a href="#">Example 1</a></li>
                                        <li><a href="#">Example 2</a></li>
                                        <li><a href="#">Example 3</a></li>
                                        <li><a href="#">Example 4</a></li>
                                        <li><a href="#">Example 5</a></li>
                                    </ul>
                                </div>
                                <div class="col-md-3">
                                    <h2 class="megamenu-title">Column 2</h2>
                                    <ul class="vertical-menu">
                                        <li><a href="#">Example 6</a></li>
                                        <li><a href="#">Example 7</a></li>
                                        <li><a href="#">Example 8</a></li>
                                        <li><a href="#">Example 9</a></li>
                                        <li><a href="#">Example 10</a></li>
                                    </ul>
                                </div>
                                <div class="col-md-3">
                                    <h2 class="megamenu-title">Column 3</h2>
                                    <ul class="vertical-menu">
                                        <li><a href="#">Example 11</a></li>
                                        <li><a href="#">Example 12</a></li>
                                        <li><a href="#">Example 13</a></li>
                                        <li><a href="#">Example 14</a></li>
                                        <li><a href="#">Example 15</a></li>
                                    </ul>
                                </div>
                                <div class="col-md-3">
                                    <h2 class="megamenu-title">Column 4</h2>
                                    <ul class="vertical-menu">
                                        <li><a href="#">Example 16</a></li>
                                        <li><a href="#">Example 17</a></li>
                                        <li><a href="#">Example 18</a></li>
                                        <li><a href="#">Example 19</a></li>
                                        <li><a href="#">Example 20</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- End nav -->