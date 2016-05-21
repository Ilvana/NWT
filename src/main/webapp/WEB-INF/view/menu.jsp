<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header id="header"><!--header-->
    <div class="header_top"><!--header_top-->
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="contactinfo">
                        <div ng-controller="LanguageController" style="padding-top: 10px;">
                            <a href="" ng-click="changeLanguage('en')"><img width="18px" src="resources/images/home/uk_flag.ico">EN</a>
                            &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                            <a href="" ng-click="changeLanguage('bh')"><img width="18px" src="resources/images/home/bih_flag.png">BH</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="social-icons pull-right">
                        <ul class="nav navbar-nav">
                            <li><a href="http://www.fcebook.com"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="http://twitter.com/"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="http://www.linkedin.com/"><i class="fa fa-linkedin"></i></a></li>
                            <li><a href="http://plus.google.com"><i class="fa fa-google-plus"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/header_top-->

    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a href="/#/"><img width="180px" src="resources/images/home/logo.png" alt=""/></a>
                    </div>
                </div>
                <div class="col-sm-8">
                    <div class="shop-menu pull-right">
                        <ul class="nav navbar-nav">
                            <li><a href="/#/contact"><i class="fa fa-user"></i><span translate>menu.contact.title.label</span></a></li>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <li><a href="/#/admin"><i class="fa fa-lock"></i><span translate>menu.admin.title.label</span></a></li>
                            </sec:authorize>
                            <sec:authorize access="!hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                                <li><a href="/#/login"><i class="fa fa-lock"></i><span translate>menu.login.title.label</span></a></li>
                            </sec:authorize>
                            <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                                <li><a href="#" onclick="document.getElementById('logoutForm').submit();"
                                       role="button"><i class="fa fa-lock"></i>Logout</a></li>
                            </sec:authorize>
                            <sec:authorize access="!hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                                <li><a href="/#/registration"><i class="fa fa-plus-circle"></i> <span translate>menu.registration.title.label</span></a></li>
                            </sec:authorize>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/header-middle-->

    <div class="header-bottom"><!--header-bottom-->
        <div class="container">
            <div class="row">
                <div class="col-sm-9">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse"
                                data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <div class="mainmenu pull-left">
                        <ul style="padding-top: 8px" class="nav navbar-nav collapse navbar-collapse">
                            <li><a href="/#/" translate>menu.home.title.label</a></li>
                            <li><a href="/#/announcements" translate>menu.announcements.title.label</a></li>
                            <li><a href="/#/special_offers" translate>menu.specialOffers.title.label</a></li>
                            <li><a href="/#/about_us" translate>menu.aboutUs.title.label</a></li>
                            <li><a href="/#/contact" translate>menu.contact.title.label</a></li>
                            <li class="dropdown"><a href="#">Blog<i class="fa fa-angle-down"></i></a>
                                <ul role="menu" class="sub-menu">
                                    <li><a href="blog.html">Blog List</a></li>
                                    <li><a href="blog-single.html">Blog Single</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="search_box pull-right">
                        <input type="text" placeholder="{{ 'main.nav.search' | translate }}"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/header-bottom-->
</header>
<!--/header-->