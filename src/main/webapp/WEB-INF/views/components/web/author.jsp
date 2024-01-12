<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../../../common/taglib.jsp"%>

<div class="author">
    <figure>
        <img src="/static/web/images/img01.jpg">
    </figure>
    <div class="details">
        <div class="job">${article.createdBy}</div>
        <h3 class="name">John Doe</h3>
        <p>Nulla sagittis rhoncus nisi, vel gravida ante. Nunc lobortis condimentum elit, quis porta
            ipsum rhoncus vitae. Curabitur magna leo, porta vel fringilla gravida, consectetur in
            libero. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum.</p>
        <ul class="social trp sm">
            <li>
                <a href="#" class="facebook">
                    <svg>
                        <rect/>
                    </svg>
                    <i class="ion-social-facebook"></i>
                </a>
            </li>
            <li>
                <a href="#" class="twitter">
                    <svg>
                        <rect/>
                    </svg>
                    <i class="ion-social-twitter"></i>
                </a>
            </li>
            <li>
                <a href="#" class="youtube">
                    <svg>
                        <rect/>
                    </svg>
                    <i class="ion-social-youtube"></i>
                </a>
            </li>
            <li>
                <a href="#" class="googleplus">
                    <svg>
                        <rect/>
                    </svg>
                    <i class="ion-social-googleplus"></i>
                </a>
            </li>
        </ul>
    </div>
</div>

