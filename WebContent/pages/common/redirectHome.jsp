<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	 .page-loader svg,.topbar,header nav{display:inline-block}.topbar,header div[class^=col-md]{padding:0}.tabs,.topbar,body{overflow:hidden}#menu-items>li>a,.submenu a,.tabs a,a,a:hover{text-decoration:none}.at-section__title,.at-user,.cd-nugget-info,.cd-popup-container,.download,.no-border .btn-primary,.page-loader,.read_more,.testimonial{text-align:center}body{font-family:Raleway,sans-serif;font-size:13px;background-color:transparent;overflow-x:hidden;background-image:url(../images/body_bg.png);background-position:center center;background-repeat:no-repeat}.page-loader{position:fixed;width:100%;height:100%;top:0;left:0;background-color:#e6edfc;z-index:9999999}.page-loader svg{width:100px;height:100px;margin:20% 20px 20px}header{position:relative}.logo{position:absolute;left:50%;top:50%;transform:translate(-140%,25%)}.slogan{color:#030387;font-size:15px;margin-left:2px;font-weight:700}.topbar{margin-top:2.5em;float:right}.horiz li{border-left:solid 1px #fe4f0a}.horiz li:first-child{border-left:none}.active,.horiz a,.horiz li{float:left;display:block;position:relative;outline:0;-webkit-tap-highlight-color:transparent;color:#6d6d6d}.active,.horiz a:link,.horiz a:visited{text-decoration:none;z-index:51;padding:.5em 1em;-webkit-transition:all .2s linear .1s;transition:all .2s linear .1s;-ms-touch-action:none;touch-action:none}.active,.horiz a:active,.horiz a:focus,.horiz a:hover{color:#fff}.horiz a:after{left:50%;left:-webkit-calc(50% - 2px);left:calc(50% - 2px);width:1px;height:.45em;-webkit-transition:all .3s ease;transition:all .3s ease}.horiz a:active:after,.horiz a:focus:after,.horiz a:hover:after{left:0;width:100%;height:100%;background:#020b5e;background:-moz-linear-gradient(top,rgba(2,11,94,1) 0,rgba(3,3,135,1) 100%);background:-webkit-linear-gradient(top,rgba(2,11,94,1) 0,rgba(3,3,135,1) 100%);background:linear-gradient(to bottom,rgba(2,11,94,1) 0,rgba(3,3,135,1) 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#020b5e', endColorstr='#030387', GradientType=0 )}.main-menu{width:100%;height:50px;background-color:#030387}.nav-wrap{margin:0;padding:0;width:100%;position:fixed}#menu-items,#menu-items li{display:inline-block;position:relative}#menu-items{margin:0 auto;list-style:none;width:auto;padding:0;z-index:99}#menu-items li{margin-right:1px}#menu-items li>ul>li{display:block;margin-right:0;min-width:220px}#menu-items>li>a{color:#fff;font-size:14px;padding:15px 10px;float:left;text-transform:capitalize;transition:all .4s ease}#menu-items a:hover{color:#fff;background:#fe4f0a}#magic-line{display:none;position:absolute;bottom:-2px;left:0;width:100px;height:2px;background:#fe4f0a}#menu-items li>ul{position:absolute;left:0;top:50px;display:none;animation:slide .2s linear 0s normal;z-index:99999999;padding:0}.subMenu li{display:block;background:#FE4F0A}#menu-items li>ul>li>a{display:block;padding:5px 12px}#menu-items li:hover>ul{display:block;animation:slide .2s linear 0s normal}#menu-items li:hover>ul li:hover>a{background:#32508f}@keyframes slide{from{transform:rotateY(95deg) skewX(0)}to{transform:rotateY(0) skewX(0)}}.current_page_item a{color:#fff!important;background-color:#fe4f0a}.ie6 #menu-items li,.ie7 #menu-items li{display:inline}.ie6 #magic-line{bottom:-3px}.call-us{height:50px;background-color:#fff;float:right;padding:0 9px;font-family:sans-serif;font-size:14px}.call-us i{margin-right:5px}.call-us a{display:block;line-height:50px;font-weight:600;color:#fe4f0a}.vs-slider{max-width:100%;padding:0;margin:0 auto;background:#030387;position:relative}.overlay-slide{position:absolute;right:0;top:0;z-index:99}.overlay-slide .panel{border:0;position:absolute;top:50%;left:50%;transform:translate(-5%,-50%);width:300px;border-radius:0}.overlay-slide .panel-heading{background-color:#fff;padding:15px;color:#fe4f0a;font-weight:600;font-size:15px}.login-form input,.login-form select{border-radius:0}.login-form button[type=submit]{background-color:#fe4f0a;border:none;border-radius:0;color:#fff;font-weight:600}.overlay-slide .panel i{margin-right:5px}.overlay-slide .panel-footer{background-color:#ff986f;border-radius:0}.overlay-slide .panel-footer a{color:#fff;font-weight:600;font-size:15px;text-transform:capitalize;transition:ease all .2s}.overlay-slide .panel-footer a:hover{color:#030387}.overlay-slide .form-group{width:75%}.overlay-slide .form-control{width:100%}.read_more{background-color:#ff9e77;border-bottom:solid 2px #fe4f0a;padding:10px;color:#fff;position:absolute;width:calc(100% - 30px);top:90%;transform:translate(-1px,-10px);transition:ease .3s all}.tab,.tabs,.tabs .current,.tabs li{position:relative}.read_more:hover{background-color:#0e56b3;border-bottom:solid 2px #023679}.products{background-color:#fff;-webkit-box-shadow:0 0 10px -3px rgba(0,0,0,.4);-moz-box-shadow:0 0 10px -3px rgba(0,0,0,.4);box-shadow:0 0 10px -3px rgba(0,0,0,.4);padding:0 0 25px;margin-top:60px}.tab{background:#fff;margin:0 auto;line-height:1.5;font-weight:300;color:#888;-webkit-font-smoothing:antialiased}.tabs{margin:-65px 0 0;width:100%}.tabs li{float:left;line-height:38px;padding:0}.tabs a{background-color:transparent;color:#e03f00;font-weight:500;display:block;letter-spacing:0;outline:0;padding:10px 20px;font-size:16px;-webkit-transition:all .2s ease-in-out;-moz-transition:all .2s ease-in-out;transition:all .2s ease-in-out}.tabs li:first-child a{color:#0047a2}.tabs a span{font-weight:700}.tabs a img{margin-right:5px;vertical-align:middle;margin-top:-4px}.tabs_item{display:none}.tabs_item:first-child{display:block}.tabs .current{-webkit-box-shadow:0 -10px 15px -1px rgba(0,0,0,.15);-moz-box-shadow:0 -10px 15px -1px rgba(0,0,0,.15);box-shadow:0 -10px 15px -1px rgba(0,0,0,.15);background-color:#f7f7f7}.tabs .current::after{content:'';position:absolute;bottom:-7px;left:50%;width:0;height:0;border-style:solid;border-width:10px 7.5px 0;border-color:#f7f7f7 transparent transparent;-webkit-transform:rotate(360deg)}.tab .tab_content{margin-top:15px}#content .container{background-color:#fff;-webkit-box-shadow:0 -5px 15px -1px rgba(0,0,0,.15);-moz-box-shadow:0 -5px 15px -1px rgba(0,0,0,.15);box-shadow:0 -5px 15px -1px rgba(0,0,0,.15)}.home .container{background-color:transparent;-webkit-box-shadow:unset!important;-moz-box-shadow:unset!important;box-shadow:unset!important}.accordion,.at-column,.at-grid{background:#FFF}#content section{padding-top:15px;padding-bottom:10px}.at-section__title{margin:20px;color:#FE4F0A;font-size:2.5rem;font-weight:300;line-height:2.625rem}.at-grid{display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;-webkit-flex-direction:row;-ms-flex-direction:row;flex-direction:row;-webkit-flex-wrap:wrap;-ms-flex-wrap:wrap;flex-wrap:wrap;border:1px solid #E2E9ED}.at-grid[data-column="1"] .at-column{width:100%;max-width:100%;min-width:100%;-webkit-flex-basis:100%;-ms-flex-preferred-size:100%;flex-basis:100%;max-height:300px}.at-grid[data-column="2"] .at-column{width:50%;max-width:50%;min-width:50%;-webkit-flex-basis:50%;-ms-flex-preferred-size:50%;flex-basis:50%;max-height:300px}.at-grid[data-column="3"] .at-column{width:33.33333%;max-width:33.33333%;min-width:33.33333%;-webkit-flex-basis:33.33333%;-ms-flex-preferred-size:33.33333%;flex-basis:33.33333%}.at-grid[data-column="4"] .at-column{width:25%;max-width:25%;min-width:25%;-webkit-flex-basis:25%;-ms-flex-preferred-size:25%;flex-basis:25%}.at-grid[data-column="5"] .at-column{width:20%;max-width:20%;min-width:20%;-webkit-flex-basis:20%;-ms-flex-preferred-size:20%;flex-basis:20%}.at-grid[data-column="6"] .at-column{width:16.66667%;max-width:16.66667%;min-width:16.66667%;-webkit-flex-basis:16.66667%;-ms-flex-preferred-size:16.66667%;flex-basis:16.66667%}.at-grid[data-column="7"] .at-column{width:14.28571%;max-width:14.28571%;min-width:14.28571%;-webkit-flex-basis:14.28571%;-ms-flex-preferred-size:14.28571%;flex-basis:14.28571%}.at-grid[data-column="8"] .at-column{width:12.5%;max-width:12.5%;min-width:12.5%;-webkit-flex-basis:12.5%;-ms-flex-preferred-size:12.5%;flex-basis:12.5%}.at-grid[data-column="9"] .at-column{width:11.11111%;max-width:11.11111%;min-width:11.11111%;-webkit-flex-basis:11.11111%;-ms-flex-preferred-size:11.11111%;flex-basis:11.11111%}.at-column{z-index:0;position:relative;box-shadow:0 0 0 1px #E2E9ED;padding:10px;box-sizing:border-box;-webkit-transition:box-shadow .2s ease,z-index 0s .2s ease,-webkit-transform .2s ease;transition:box-shadow .2s ease,z-index 0s .2s ease,-webkit-transform .2s ease;transition:box-shadow .2s ease,transform .2s ease,z-index 0s .2s ease;transition:box-shadow .2s ease,transform .2s ease,z-index 0s .2s ease,-webkit-transform .2s ease}.at-column:before{content:"";display:block;padding-top:100%}.at-column:hover{z-index:1;box-shadow:0 8px 50px rgba(0,0,0,.2);-webkit-transform:scale(1.05);transform:scale(1.05);-webkit-transition:box-shadow .2s ease,z-index 0s 0s ease,-webkit-transform .2s ease;transition:box-shadow .2s ease,z-index 0s 0s ease,-webkit-transform .2s ease;transition:box-shadow .2s ease,transform .2s ease,z-index 0s 0s ease;transition:box-shadow .2s ease,transform .2s ease,z-index 0s 0s ease,-webkit-transform .2s ease}.at-column:hover .at-social{margin:16px 0 0;opacity:1}.at-user{position:absolute;top:50%;left:0;width:100%;-webkit-transform:translate(0,-50%);transform:translate(0,-50%)}.at-user__avatar{width:105px;height:105px;border-radius:100%;margin:0 auto 20px;overflow:hidden}.at-user__avatar img{display:block;width:100%;max-width:100%}.at-user__name{color:#313435;font-size:1.5rem;font-weight:500;line-height:2.625rem}.news_date,.news_title,.testimonial h4{font-weight:700}.at-user__title{color:#030387;font-size:1.275rem}.at-social{margin:0 0 -18px;opacity:0;-webkit-transition:margin .2s ease,opacity .2s ease;transition:margin .2s ease,opacity .2s ease}.at-social__item{display:inline-block;margin:0 10px}.accordion .link,.accordion li.default .submenu,.at-social__item a{display:block}.at-social__item svg{fill:#515F65;display:block;height:18px;-webkit-transition:fill .2s ease;transition:fill .2s ease}.at-social__item svg:hover{fill:#788D96}.table-striped td{padding:5px}.news-items .isotopeContent{height:150px;border:0}.news-img{height:180px!important;overflow:hidden!important;border:none}.download__textWrapper,.img-replace{overflow:hidden}#content h1{padding:10px 0}.accordion{width:100%;max-width:760px;margin:20px auto 0;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;list-style-type:none!important;padding:0}.accordion .link{cursor:pointer;padding:15px 15px 15px 42px;color:#4D4D4D;font-size:14px;font-weight:700;border-bottom:1px solid #CCC;position:relative;-webkit-transition:all .4s ease;-o-transition:all .4s ease;transition:all .4s ease}.accordion li:last-child .link{border-bottom:0}.accordion li i{position:absolute;top:16px;left:12px;font-size:18px;color:#595959;-webkit-transition:all .4s ease;-o-transition:all .4s ease;transition:all .4s ease}.accordion li i.fa-chevron-down{right:12px;left:auto;font-size:16px}.accordion li.open .link{color:#030387}.accordion li.open i{color:#ee6d3a}.accordion li.open i.fa-chevron-down{-webkit-transform:rotate(180deg);-ms-transform:rotate(180deg);-o-transform:rotate(180deg);transform:rotate(180deg)}.submenu{display:none;background:#030387;font-size:14px;list-style-type:none;margin:0;padding:0}.submenu li{border-bottom:1px solid #4b4a5e}.submenu a{display:block;color:#d9d9d9;padding:12px 12px 12px 42px;-webkit-transition:all .25s ease;-o-transition:all .25s ease;transition:all .25s ease}.submenu a:hover{background:#FE8353;color:#FFF}.form_type{background-color:#F87744;color:#fff;text-align:center;padding:10px}.no-border .isotopeContent{border:none!important;height:190px}.no-border .btn-primary{background-color:transparent;border-color:transparent;border-radius:0;color:#333;position:absolute;bottom:0}.service-form{width:80%;margin:0 auto}.service-form input,.service-form textarea{border:1px solid #fe8353;border-radius:0}.page-slider{width:100%;background-size:cover!important;height:280px}.has_header{background-color:rgba(240,120,72,.89);color:#fff;border:none;margin-top:-70px}#searchWrapper,#searchWrapper .form-control,#searchWrapper button{height:50px}.testimonial{background-color:#f8f4f5;padding:20px 0;-webkit-box-shadow:inset 0 7px 10px -13px rgba(0,0,0,.75);-moz-box-shadow:inset 0 7px 10px -13px rgba(0,0,0,.75);box-shadow:inset 0 7px 10px -13px rgba(0,0,0,.75)}.testimonial i{display:block;font-size:35px;color:#464646;padding:10px 0}.testimonial h4{color:#fd510d}.testimonial p{font-size:15px;color:#343434}.no-padding{padding:0}.download__icon,.download__text,.download__textWrapper{position:absolute;width:100%;height:100%;left:0}.download__icon,.download__text{-webkit-transition:top .5s;transition:top .5s}.download{display:inline-block;position:relative;background-color:#214396;color:#fff;font-size:1.1rem;width:230px;height:40px;-webkit-transition:background-color .5s,-webkit-transform .1s;transition:background-color .5s,-webkit-transform .1s;transition:background-color .5s,transform .1s;transition:background-color .5s,transform .1s,-webkit-transform .1s}.download__text{line-height:40px;top:0}.download__icon{top:100%;background:url(../images/icon-download.svg) center center no-repeat}.download::before{content:attr(data-tooltip);width:140px;height:40px;background-color:#f36f21;color:#fff;font-size:1rem;border-radius:.25em;line-height:40px;bottom:90px;left:calc(50% - 70px)}.download::after{content:'';width:0;height:0;border:10px solid transparent;border-top-color:#f36f21;left:calc(50% - 10px);bottom:70px}.download::after,.download::before{position:absolute;opacity:0;-webkit-transition:all .5s;transition:all .5s;visibility:hidden}.download:hover{background-color:#f3813e}.download:hover .download__text{top:-100%}.download:hover .download__icon{top:0}.download:hover::after,.download:hover::before{opacity:1;visibility:visible}.download:hover::after{bottom:60px}.download:hover::before{bottom:80px}.download:active{-webkit-transform:translate(2px,2px);transform:translate(2px,2px)}footer .widget-title{position:relative}footer .widget-title:before{content:'';position:absolute;left:0;bottom:0;width:30px;height:2px;background-color:#0643a4}footer .widget:nth-child(1){border-right:solid 1px #dcdcdc}footer,footer a{color:#545454;background-color:#fff}.foo-links{background-color:#d1cfd0;padding:25px 0;margin-bottom:10px}.foo-links a{background-color:transparent}.foo-links span{line-height:30px}.foo-links h4{color:#034aa2}.foo-links h4.corporate{color:#e54d0c}.foo-links ul.nostyle li{padding:2px 0}.news_date{color:#4673b0;font-size:14px;display:block;padding-bottom:10px}.salvage{padding:10px}.salvage .user-details{background-color:#e6e6fa;padding:10px 0;color:#fe4f0a;line-height:35px;margin-bottom:25px}.salvage button{display:inline-block;padding-right:20px;padding-left:20px}.img-replace{display:inline-block;text-indent:100%;color:transparent;white-space:nowrap}.cd-nugget-info{position:absolute;width:100%;height:50px;line-height:50px;bottom:0;left:0}.cd-nugget-info a{position:relative;font-size:14px;color:#5e6e8d;-webkit-transition:all .2s;-moz-transition:all .2s;transition:all .2s}.no-touch .cd-nugget-info a:hover{opacity:.8}.cd-nugget-info span{vertical-align:middle;display:inline-block}.cd-nugget-info span svg{display:block}.cd-nugget-info .cd-nugget-info-arrow{fill:#5e6e8d}.cd-popup{position:fixed;left:0;top:0;height:100%;width:100%;background-color:rgba(98,98,98,.92);opacity:0;visibility:hidden;-webkit-transition:opacity .3s 0s,visibility 0s .3s;-moz-transition:opacity .3s 0s,visibility 0s .3s;transition:opacity .3s 0s,visibility 0s .3s;z-index:9999}.cd-popup.is-visible{opacity:1;visibility:visible;-webkit-transition:opacity .3s 0s,visibility 0s 0s;-moz-transition:opacity .3s 0s,visibility 0s 0s;transition:opacity .3s 0s,visibility 0s 0s}.cd-popup-container{position:relative;width:90%;max-width:750px;margin:1em auto;background:#FFF;border-radius:.25em .25em .4em .4em;box-shadow:0 0 20px rgba(0,0,0,.2);-webkit-transform:translateY(-40px);-moz-transform:translateY(-40px);-ms-transform:translateY(-40px);-o-transform:translateY(-40px);transform:translateY(-40px);-webkit-backface-visibility:hidden;-webkit-transition-property:-webkit-transform;-moz-transition-property:-moz-transform;transition-property:transform;-webkit-transition-duration:.3s;-moz-transition-duration:.3s;transition-duration:.3s}.cd-popup-container .cd-buttons:after{content:"";display:table;clear:both}.cd-popup-container .cd-buttons li{float:left;width:50%;list-style:none}.cd-popup-container .cd-buttons input{display:block;height:60px;line-height:60px;text-transform:uppercase;color:#FFF;-webkit-transition:background-color .2s;-moz-transition:background-color .2s;transition:background-color .2s}.dropdown div,.loading_form{display:inline-block}.cd-popup-container .cd-buttons li:first-child input{background:#fc7169;border-radius:0 0 0 .25em}.no-touch .cd-popup-container .cd-buttons li:first-child input:hover{background-color:#fc8982}.cd-popup-container .cd-buttons li:last-child input{background:#b6bece;border-radius:0 0 .25em}.no-touch .cd-popup-container .cd-buttons li:last-child input:hover{background-color:#c5ccd8}.cd-popup-container .cd-popup-close{position:absolute;top:8px;right:8px;width:30px;height:30px}.cd-popup-container .cd-popup-close::after,.cd-popup-container .cd-popup-close::before{content:'';position:absolute;top:12px;width:14px;height:3px;background-color:#8f9cb5}.cd-popup-container .cd-popup-close::before{-webkit-transform:rotate(45deg);-moz-transform:rotate(45deg);-ms-transform:rotate(45deg);-o-transform:rotate(45deg);transform:rotate(45deg);left:8px}.cd-popup-container .cd-popup-close::after{-webkit-transform:rotate(-45deg);-moz-transform:rotate(-45deg);-ms-transform:rotate(-45deg);-o-transform:rotate(-45deg);transform:rotate(-45deg);right:8px}.is-visible .cd-popup-container{-webkit-transform:translateY(0);-moz-transform:translateY(0);-ms-transform:translateY(0);-o-transform:translateY(0);transform:translateY(0)}.bid-form{margin:5px}.bid-form .help-block{background:#d9534f;color:#fff}.loading_form{visibility:hidden}.loading_form img{width:35px;height:auto}body[dir=rtl] .breadcrumb a{margin-left:20px;position:relative}body[dir=rtl] .breadcrumb a::before{content:"";font-family:FontAwesome;padding:0;left:-10px;position:absolute}.carousel-content p{color:#3b3b3b}@media only screen and (min-width:481px){.active:after,.horiz a:after{position:absolute;bottom:0;content:"";display:block;z-index:-1;background:#030387}}@media screen and (min-width:1170px){.cd-popup-container{margin:8em auto}}@media (max-width:800px){.at-column{width:50%!important;max-width:50%!important;min-width:50%!important;-webkit-flex-basis:50%!important;-ms-flex-preferred-size:50%!important;flex-basis:50%!important}}@media screen and (min-width:260px) and (max-width:900px){.call-us,.tabs li{float:none}.foo-links,.main-menu,.main-menu a,.slogan{text-align:center}.menuBtn{display:block;height:30px;width:30px;position:absolute;right:20px;top:20px;z-index:101}.menuBtn>span,.menuBtn>span:after,.menuBtn>span:before{background-color:#FE4F0A;border-radius:1px;height:2px;left:50%;position:absolute}.menuBtn>span{width:100%;top:50%;margin:-1px 0 0 -15px;-webkit-transition:height .1s;transition:height .1s}.menuBtn>span:after,.menuBtn>span:before{content:'';width:100%;margin-left:-15px;-webkit-transition:all .2s;transition:all .2s}.menuBtn>span:after{top:-7px}.menuBtn>span:before{bottom:-7px}.menuBtn.act>span{height:0}.menuBtn.act>span:after,.menuBtn.act>span:before{background-color:#030387;top:1px}.menuBtn.act>span:after{-webkit-transform:rotate(45deg);transform:rotate(45deg)}.menuBtn.act>span:before{-webkit-transform:rotate(-45deg);transform:rotate(-45deg)}.main-menu{background-color:#fff;position:absolute;left:0;top:0;z-index:100;height:100%;width:100%;display:table;opacity:0;-webkit-transition:all .5s cubic-bezier(.68,-.55,.265,1.55);transition:all .5s cubic-bezier(.68,-.55,.265,1.55);-webkit-transform:scale(0);transform:scale(0)}.current_page_item a,.subMenu li{background-color:transparent!important}.main-menu.act{opacity:1;-webkit-transform:scale(1);transform:scale(1)}.main-menu.act ul li{opacity:1;display:block!important;-webkit-transform:translateX(0);transform:translateX(0)}.main-menu.act ul li a{color:#030387!important}.main-menu>ul{vertical-align:middle;margin-top:20px;display:block!important}.main-menu li{padding:8px 0;-webkit-transition:all .4s 510ms;transition:all .4s 510ms;opacity:0;display:block!important;clear:both!important}.main-menu li:nth-child(odd){-webkit-transform:translateX(30%);transform:translateX(30%)}.main-menu li:nth-child(even){-webkit-transform:translateX(-30%);transform:translateX(-30%)}.main-menu li:last-child{-webkit-transform:none;transform:none}.main-menu a{color:#030387;display:block;width:100%}#magic-line,.subMenu li{display:none!important}.main-menu a.suBtn{color:#fff}.subMenu{position:relative!important}.dropdown div,.overlay-slide{display:none}#menu-items li>ul{top:0}body{padding-top:0!important}.slogan{margin-left:40px}.dropdown-menu{min-width:120px}.dropdown{position:absolute;right:-10px;top:-40px}#lang-btn{margin-top:0}.navbar-brand{margin-top:5px}.topbar{margin-top:0}.horiz li{border:none!important}.ms-skin-default .ms-nav-next,.ms-skin-default .ms-nav-prev{left:10px}.ms-skin-default .ms-nav-next{left:auto;right:10px;margin-top:0!important;top:33%!important}.ms-bullets.ms-dir-h{left:30%!important}.products{padding:35px 0 25px}.tabs{margin-top:-110px}.tabs li{width:100%;clear:both;background-color:#fff}footer p.text-right{text-align:center!important}#toTop{background:rgba(255,255,255,.6);border:1px solid #ddd}#content .widget-left{height:100%!important;width:100%}.at-column{width:100%!important;max-width:100%!important;min-width:100%!important;-webkit-flex-basis:100%!important;-ms-flex-preferred-size:100%!important;flex-basis:100%!important}:lang(ar) .menuBtn{left:20px;right:auto}:lang(ar) .dropdown{left:-10px;right:auto;top:-40px}; :lang(ar) .tabs li span{float:right;padding-right:5px}}
.spinner {
  height: 30px;
  width: 30px;
  border: 3px solid rgba(160, 160, 160, 0.4);
  border-right-color: #FE4F0A;
  border-radius: 50%;
  -webkit-animation: spin 1.1s infinite linear;
          animation: spin 1.1s infinite linear;
	position: absolute;
	margin: 0 auto;
	top: 50%;
	left:0;
	right: 0;
}
.page-loader{
	background:#e7e7e7;
}
@-webkit-keyframes spin {
  from {
    -webkit-transform: rotate(0deg);
            transform: rotate(0deg);
  }
  from {
    -webkit-transform: rotate(360deg);
            transform: rotate(360deg);
  }
}

@keyframes spin {
  from {
    -webkit-transform: rotate(0deg);
            transform: rotate(0deg);
  }
  from {
    -webkit-transform: rotate(360deg);
            transform: rotate(360deg);
  }
}
@media screen and (max-width: 900px) {
    .ten_logo{
        height: 77px !important;
        width: auto !important;
    }
    .overlay-slide{
    	display: block;
    }
    .overlay-slide .panel {
		border: 0;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-5%,-50%);
		width: 150px;
		border-radius: 0;
	}
	.overlay-slide img{
		width: 58%;
		left: 0;
		top: 0;
		float: right;
	}
}
</style>
</head>
<body>
<form method="post" action='<s:property value="forward"/>' id="form1" name="form1">
		<div class="page-loader">
   			 <div class="spinner"></div>  
		</div>
		<s:hidden name="e"/>		
		  	
	</form>
	<br/>
</body>
<script type="text/javascript">
	document.form1.submit();
</script>
</html>