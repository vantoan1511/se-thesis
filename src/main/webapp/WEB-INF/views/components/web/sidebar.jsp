<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../../../common/taglib.jsp" %>

<div class="col-xs-6 col-md-4 sidebar" id="sidebar">
    <div class="sidebar-title for-tablet">Sidebar</div>

    <%@include file="featuredAuthor.jsp" %>

    <%@ include file="popular.jsp" %>

    <aside>
        <div class="aside-body">
            <form class="newsletter">
                <div class="icon">
                    <i class="ion-ios-email-outline"></i>
                    <h1>Newsletter</h1>
                </div>
                <div class="input-group">
                    <input type="email" class="form-control email" placeholder="Your mail">
                    <div class="input-group-btn">
                        <button class="btn btn-primary"><i class="ion-paper-airplane"></i></button>
                    </div>
                </div>
                <p>By subscribing you will receive new articles in your email.</p>
            </form>
        </div>
    </aside>
    <aside>
        <ul class="nav nav-tabs nav-justified" role="tablist">
            <li class="active">
                <a href="#recomended" aria-controls="recomended" role="tab" data-toggle="tab">
                    <i class="ion-android-star-outline"></i> Recomended
                </a>
            </li>
            <li>
                <a href="#comments" aria-controls="comments" role="tab" data-toggle="tab">
                    <i class="ion-ios-chatboxes-outline"></i> Comments
                </a>
            </li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="recomended">
                <article class="article-fw">
                    <div class="inner">
                        <figure>
                            <a href="single.html">
                                <img src="/static/web/images/news/img16.jpg" alt="Sample Article">
                            </a>
                        </figure>
                        <div class="details">
                            <div class="detail">
                                <div class="time">December 31, 2016</div>
                                <div class="category"><a href="category.html">Sport</a></div>
                            </div>
                            <h1><a href="single.html">Donec congue turpis vitae mauris</a></h1>
                            <p>
                                Donec congue turpis vitae mauris condimentum luctus. Ut dictum neque at
                                egestas convallis.
                            </p>
                        </div>
                    </div>
                </article>
                <div class="line"></div>
                <article class="article-mini">
                    <div class="inner">
                        <figure>
                            <a href="single.html">
                                <img src="/static/web/images/news/img05.jpg" alt="Sample Article">
                            </a>
                        </figure>
                        <div class="padding">
                            <h1><a href="single.html">Duis aute irure dolor in reprehenderit in
                                voluptate velit</a></h1>
                            <div class="detail">
                                <div class="category"><a href="category.html">Lifestyle</a></div>
                                <div class="time">December 22, 2016</div>
                            </div>
                        </div>
                    </div>
                </article>
                <article class="article-mini">
                    <div class="inner">
                        <figure>
                            <a href="single.html">
                                <img src="/static/web/images/news/img02.jpg" alt="Sample Article">
                            </a>
                        </figure>
                        <div class="padding">
                            <h1><a href="single.html">Fusce ullamcorper elit at felis cursus
                                suscipit</a></h1>
                            <div class="detail">
                                <div class="category"><a href="category.html">Travel</a></div>
                                <div class="time">December 21, 2016</div>
                            </div>
                        </div>
                    </div>
                </article>
                <article class="article-mini">
                    <div class="inner">
                        <figure>
                            <a href="single.html">
                                <img src="/static/web/images/news/img10.jpg" alt="Sample Article">
                            </a>
                        </figure>
                        <div class="padding">
                            <h1><a href="single.html">Duis aute irure dolor in reprehenderit in
                                voluptate velit</a></h1>
                            <div class="detail">
                                <div class="category"><a href="category.html">Healthy</a></div>
                                <div class="time">December 20, 2016</div>
                            </div>
                        </div>
                    </div>
                </article>
            </div>
            <div class="tab-pane comments" id="comments">
                <div class="comment-list sm">
                    <div class="item">
                        <div class="user">
                            <figure>
                                <img src="/static/web/images/img01.jpg" alt="User Picture">
                            </figure>
                            <div class="details">
                                <h5 class="name">Mark Otto</h5>
                                <div class="time">24 Hours</div>
                                <div class="description">
                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="user">
                            <figure>
                                <img src="/static/web/images/img01.jpg" alt="User Picture">
                            </figure>
                            <div class="details">
                                <h5 class="name">Mark Otto</h5>
                                <div class="time">24 Hours</div>
                                <div class="description">
                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="user">
                            <figure>
                                <img src="/static/web/images/img01.jpg" alt="User Picture">
                            </figure>
                            <div class="details">
                                <h5 class="name">Mark Otto</h5>
                                <div class="time">24 Hours</div>
                                <div class="description">
                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </aside>
    <aside>
        <h1 class="aside-title">Videos
            <div class="carousel-nav" id="video-list-nav">
                <div class="prev"><i class="ion-ios-arrow-left"></i></div>
                <div class="next"><i class="ion-ios-arrow-right"></i></div>
            </div>
        </h1>
        <div class="aside-body">
            <ul class="video-list" data-youtube='"carousel":true, "nav": "#video-list-nav"'>
                <li><a data-youtube-id="SBjQ9tuuTJQ" data-action="magnific"></a></li>
                <li><a data-youtube-id="9cVJr3eQfXc" data-action="magnific"></a></li>
                <li><a data-youtube-id="DnGdoEa1tPg" data-action="magnific"></a></li>
            </ul>
        </div>
    </aside>
    <aside id="sponsored">
        <h1 class="aside-title">Sponsored</h1>
        <div class="aside-body">
            <ul class="sponsored">
                <li>
                    <a href="#">
                        <img src="/static/web/images/sponsored.png" alt="Sponsored">
                    </a>
                </li>
                <li>
                    <a href="#">
                        <img src="/static/web/images/sponsored.png" alt="Sponsored">
                    </a>
                </li>
                <li>
                    <a href="#">
                        <img src="/static/web/images/sponsored.png" alt="Sponsored">
                    </a>
                </li>
                <li>
                    <a href="#">
                        <img src="/static/web/images/sponsored.png" alt="Sponsored">
                    </a>
                </li>
            </ul>
        </div>
    </aside>
</div>