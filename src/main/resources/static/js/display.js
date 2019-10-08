"use strict";

function _typeof(s) {
    return (_typeof = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(s) {
        return typeof s
    } : function(s) {
        return s && "function" == typeof Symbol && s.constructor === Symbol && s !== Symbol.prototype ? "symbol" : typeof s
    })(s)
}

function _classCallCheck(s, o) {
    if (!(s instanceof o)) throw new TypeError("Cannot call a class as a function")
}

function _defineProperties(s, o) {
    for (var t = 0; t < o.length; t++) {
        var i = o[t];
        i.enumerable = i.enumerable || !1, i.configurable = !0, "value" in i && (i.writable = !0), Object.defineProperty(s, i.key, i)
    }
}

function _createClass(s, o, t) {
    return o && _defineProperties(s.prototype, o), t && _defineProperties(s, t), s
}

function yanchi(f) {
    (new(function() {
        function s() {
            _classCallCheck(this, s)
        }
        return _createClass(s, [{
            key: "init",
            value: function() {
                this.shops = f(".shops"), this.leftBox = f(".left-bar"), this.clipBox = f(".clip-box"), this.imgBox = f(".img-box"), this.rightBox = f(".right-bar"), this.rightImg = this.rightBox.children().eq(0), this.bindEvent()
            }
        }, {
            key: "bindEvent",
            value: function() {
                this.shops.on("mouseenter", ".left-bar", this.showEle.bind(this)), this.shops.on("mouseleave", ".left-bar", this.hideEle.bind(this)), this.shops.on("mousemove", ".left-bar", this.follow.bind(this))
            }
        }, {
            key: "showEle",
            value: function() {
                this.clipBox.css({
                    display: "block"
                }), this.imgBox.css({
                    opacity: .4
                }), this.rightBox.css({
                    display: "block"
                })
            }
        }, {
            key: "hideEle",
            value: function() {
                this.clipBox.css({
                    display: "none"
                }), this.imgBox.css({
                    opacity: 1
                }), this.rightBox.css({
                    display: "none"
                })
            }
        }, {
            key: "follow",
            value: function(s) {
                var o = s || window.event,
                    t = o.clientX,
                    i = o.clientY,
                    e = f(document).scrollTop(),
                    n = t - this.leftBox[0].offsetLeft - this.clipBox[0].offsetWidth / 2,
                    c = i - this.leftBox[0].offsetTop - this.clipBox[0].offsetHeight / 2 + e,
                    r = this.leftBox[0].offsetWidth - this.clipBox[0].offsetWidth,
                    a = this.leftBox[0].offsetHeight - this.clipBox[0].offsetHeight,
                    l = (this.rightBox[0].offsetWidth, this.clipBox[0].offsetWidth, this.rightBox[0].offsetWidth - this.rightImg[0].offsetWidth);
                n = r < (n = n < 0 ? 0 : n) ? r : n, c = a < (c = c < 0 ? 0 : c) ? a : c, this.clipBox[0].style.cssText += ";left:".concat(n, "px;top:").concat(c, "px"), this.clipBox[0].style.backgroundPosition = "-".concat(n, "px -").concat(c, "px"), this.rightImg[0].style.cssText = "left:".concat(n / r * l, "px;top:").concat(c / a * l, "px")
            }
        }]), s
    }())).init()
}! function(a) {
    var s = a.Callbacks(),
        o = a.Callbacks(),
        t = a.Callbacks(),
        i = a.Callbacks(),
        l = {
            pxxList: {},
            carts: localStorage.getItem("carts") ? JSON.parse(localStorage.getItem("carts")) : {},
            shopAttr: "pxx",
            display: localStorage.getItem("display")
        };

    function e(s) {
        var o = s.goods_list;
        a.each(o, function(s, o) {
            l.pxxList[s] = o
        })
    }

    function n() {
        var o = a(this).attr("data-id");
        l.carts[l.shopAttr] instanceof Array || (l.carts[l.shopAttr] = []), l.carts[l.shopAttr].some(function(s) {
            if (s.id == o) return s.count++, !0
        }) || l.carts[l.shopAttr].push({
            id: o,
            count: 1,
            price: parseFloat(a(this).siblings(".price").children("strong").html())
        }), r("carts", l.carts)
    }

    function c() {
        var t = a(this).parent().attr("data-id");
        l.carts[l.shopAttr].some(function(s, o) {
            if (s.id == t) return function(s, o) {
                l.carts[l.shopAttr].splice(o, 1)
            }(0, o), !0
        }), r("carts", l.carts)
    }

    function r(s, o) {
        localStorage.setItem(s, JSON.stringify(o))
    }

    function f() {
        var s = l.carts[l.shopAttr],
            o = 0;
        (s = s || []).forEach(function(s) {
            o += s.count
        }), a(".cartslogo-counts").html(o)
    }

    function d() {
        var o = l.display,
            s = [];
        for (var t in l.pxxList) s.push(l.pxxList[t]);
        var i = "";
        s.forEach(function(s) {
            s.goods_id == o && (i += '<div class="left-bar">\n                <div class="img-box">\n                    <img src="'.concat(s.hd_url, '" alt="">\n                </div>\n                <div class="clip-box" style="background-image: url(').concat(s.hd_url, ');"></div>\n            </div>\n            <div class="shops-message">\n                <div class="name">').concat(s.goods_name, '</div>\n                <div class="mes">').concat(s.short_name, '</div>\n                <p class="company">pxx自营</p>\n                <div class="price"><strong>').concat((s.normal_price / 100).toFixed(2), '</strong>元</div>\n                <div class="song">\n                    <span class="ka">赠品</span>\n                    <span class="feng">赠米粉卡</span>\n                </div>\n                <div class="city">\n                    <div class="chengshi">\n                        <i class="iconfont">&#xe684;</i>\n                        <div>\n                            <div class="address-info">\n                                <span>北京</span>\n                                <span>北京市</span>\n                                <span>昌平区</span>\n                                <span>沙阳路</span>\n                            </div>\n                            <span class="item">修改</span>\n                        </div>\n                    </div>\n                </div>\n                <div class="addCart options" data-id="').concat(s.goods_id, '">\n                    <div class="biglarge" data-id="').concat(s.goods_id, '">加入购物车</div>\n                </div>\n            </div>\n            <div class="right-bar">\n                <img src="').concat(s.hd_url, '" alt="">\n            </div>'))
        }), a(".shops").html(i)
    }

    function h() {
        var s = l.carts[l.shopAttr],
            o = [];
        if (!s) return !1;
        for (var t in l.pxxList) o.push(l.pxxList[t]);
        var i = o,
            e = [];
        s.forEach(function(o) {
            i.some(function(s) {
                if (o.id == s.goods_id) return s.count = o.count, e.push(s), !0
            })
        });
        var n = "",
            c = 0,
            r = 0;
        e.forEach(function(s) {
            var o = (s.normal_price / 100).toFixed(2) * s.count,
                t = s.count;
            r += t, c += o, n += '<div class="padding-cart">\n            <div class="cart-item clearfix first options smallcart" data-id="'.concat(s.goods_id, '">\n                <a class="thumb" href="javascript:void(0)">\n                    <img src="').concat(s.hd_thumb_url, '" alt="">\n                </a>\n                <a class="name" href="javascript:void(0)">').concat(s.goods_name, '</a>\n                <span class="price">').concat((s.normal_price / 100).toFixed(2), "元x").concat(s.count, '</span>\n                <a class="btn-del" href="javascript:void(0)">X</a>\n            </div>\n        </div>')
        }), a("#smallCarts").html(n), a(".total>em").html(r), a(".total>.price>em").html(c.toFixed(2))
    }

    function u(s) {
        s.stopPropagation()
    }

}
    (jQuery), jQuery, setTimeout(function() {
    yanchi($)
}, 200), $(".nav1 li").mouseover(function() {
    $(this).find("a").css("color", "#ff6700")
}).mouseout(function() {
    $(this).find("a").css("color", "#616161")
}), $(".nav2 a").mouseover(function() {
    $(this).css("color", "#ff6700")
}).mouseout(function() {
    $(this).css("color", "#757575")
}), $("#serv").mouseover(function() {
    $(this).css({
        background: "#ff6700",
        color: "#fff"
    })
}).mouseout(function() {
    $(this).css({
        background: "#fff",
        color: "#ff6700"
    })
}), $(".staTxt a").mouseover(function() {
    $(this).css("color", "#ff6700")
}).mouseout(function() {
    $(this).css("color", "#757575")
}), $(".staLink a").mouseover(function() {
    $(this).css("color", "#ff6700")
}).mouseout(function() {
    $(this).css("color", "#b0b0b0")
}), $(".top li").mouseover(function() {
    $(this).children("a").css("color", "#fff")
}).mouseout(function() {
    $(this).children("a").css("color", "#b0b0b0")
}), $("#shopCar").on("mouseover", function() {
    $(this).css({
        color: "#FF6700",
        background: "#fff"
    }), $(this).children("a").css("color", "#FF6700"), $(".goods").stop(!0, !1).slideDown()
}).mouseout(function() {
    $(this).css({
        color: "#b0b0b0",
        background: "#424242"
    }), $(this).children("a").css("color", "#b0b0b0"), $(".goods").stop(!0, !1).slideUp()
});
var flagSer = 1;
$(".ser1").mouseover(function() {
        flagSer && ($(".ser1 input").css({
            border: "1px solid #c0c0c0",
            borderRight: "none"
        }), $(".ser2").css("border", "1px solid #c0c0c0"))
    }).mouseout(function() {
        flagSer && ($(".ser1 input").css({
            border: "1px solid #e0e0e0",
            borderRight: "none"
        }), $(".ser2").css("border", "1px solid #e0e0e0"))
    }), $(".a a").mouseover(function() {
        $(this).css({
            color: "#fff",
            background: "#FF6700"
        })
    }).mouseout(function() {
        $(this).css({
            color: "#757575",
            background: "#eee"
        })
    }), $(".ser2").mouseover(function() {
        flagSer && $(".ser1 input").css({
            border: "1px solid #c0c0c0",
            borderRight: "none"
        }), $(this).css({
            color: "#fff",
            width: "50px",
            height: "50px",
            border: "none",
            background: "#ff6700",
            cursor: "pointer"
        })
    }).mouseout(function() {
        flagSer ? ($(this).css({
            color: "#616161",
            width: "48px",
            height: "48px",
            background: "#fff",
            border: "1px solid #e0e0e0"
        }), $(".ser1 input").css({
            border: "1px solid #e0e0e0",
            borderRight: "none"
        })) : $(this).css({
            color: "#616161",
            width: "48px",
            height: "48px",
            background: "#fff",
            border: "1px solid #ff6700"
        })
    }), $(".ser1 input").focus(function() {
        flagSer = 0, $(".a").addClass("hide"), $(".keywordList").removeClass("hide"), $(this).css("border", "1px solid #ff6700"), $(".ser2").css({
            border: "1px solid #ff6700",
            borderLeft: "none"
        }), $(".keywordList").css("border", "1px solid #ff6700")
    }).blur(function() {
        flagSer = 1, $(".a").removeClass("hide"), $(".keywordList").addClass("hide"), $(this).css("border", "1px solid #e0e0e0"), $(".ser2").css("border", "1px solid #e0e0e0")
    }), $(".nav li").mouseover(function() {
        $(this).children("a").css("color", "#FF6700"), $(this).index() < 8 && ($(".select").removeClass("hide"), $(".select").slideDown().finish(), $(".select").find("ul").addClass("hide"), $(".select").find("ul").eq($(this).index()).removeClass("hide"))
    }).mouseout(function() {
        $(this).children("a").css("color", "#333333")
    }), $(".nav").mouseout(function() {
        $(".select").slideUp()
    }), $(".select").find("ul").mouseover(function() {
        $(".select").slideDown().finish()
    }).mouseout(function() {
        $(".select").slideUp()
    }),
    function() {
        function s(s, o) {
            var t = {
                expires: -1
            };
            ! function(s, o, t) {
                "object" === _typeof(t) ? Object.assign({}, t) : t = {};
                var i = "";
                if (i += s + "=" + encodeURI(o), "number" == typeof t.expires) {
                    var e = new Date;
                    e.setDate(e.getDate() + t.expires), i += ";expires=" + e
                }
                i += t.path ? ";path=" + t.path : "", i += t.domain ? ";domain=" + t.domain : "", document.cookie = i
            }(s, null, o = "object" == _typeof(o) ? Object.assign(t, o) : t)
        }
        document.cookie.split("=").some(function(s) {
            if ("username" == s) {
                var o = function(s) {
                    var o = document.cookie.split("; "),
                        t = [],
                        i = o.map(function(s) {
                            var o = s.split("=");
                            return t.push(o[0]), o[1]
                        }),
                        e = t.indexOf(s);
                    return -1 !== e ? i[e] : ""
                }("username");
                return $(".top .right li").eq(0).children().html("您好，" + o), $(".top .right li").eq(0).children().attr("href", "javascript:void(0)"), $(".top .right li").eq(1).children().html("注销"), $(".top .right li").eq(2).children().html(""), !0
            }
        }), "注销" == $(".top .right li").eq(1).children().html() ? ($(".top .right li").eq(1).children().attr("href", "javascript:void(0)"), $(".top .right li").eq(1).children().on("click", function() {
            s("username", {
                path: "/"
            }), history.go(0)
        })) : $(".top .right li").eq(1).children().attr("href", "./register.html")
    }(), $(function() {
        var s = $(".box").innerWidth(),
            o = $(window).width(),
            e = Math.floor(o / s);

        function i() {
            for (var s = [], o = 0; o < $(".box").length; o++)
                if (o < e) s[o] = $(".box").eq(o).innerHeight();
                else {
                    var t = Math.min.apply(null, s),
                        i = n(s, t);
                    $(".box").eq(o).css({
                        position: "absolute",
                        top: t + "px",
                        left: $(".box").eq(i).offset().left + "px"
                    }), s[i] += $(".box").eq(o).innerHeight()
                }
        }

        function n(s, o) {
            for (var t in s)
                if (s[t] == o) return t
        }
        $("#container").css({
            width: e * s + "px",
            margin: "0 auto"
        }), window.onload = function() {
            i();
            var t = [{
                src: "./images/1.jpg"
            }, {
                src: "./images/2.jpg"
            }, {
                src: "./images/3.jpg"
            }, {
                src: "./images/4.jpg"
            }, {
                src: "./images/5.jpg"
            }, {
                src: "./images/6.jpg"
            }, {
                src: "./images/xmad_15519291062065_hikIJ.jpg"
            }, {
                src: "./images/8.jpg"
            }, {
                src: "./images/9.jpg"
            }, {
                src: "./images/10.gif"
            }, {
                src: "./images/11.jpg"
            }, {
                src: "./images/12.jpg"
            }];
            window.onscroll = function() {
                if (function() {
                        var s = 0;
                        return $(document).scrollTop() + $(window).height() >= $(document).height() && (s = 1), s
                    }()) {
                    for (var s = 0; s < t.length; s++) {
                        var o = "<div class='box'><div class='boximg'><img src=" + t[s].src + "></div></div>";
                        $("#container").append(o)
                    }
                    i()
                }
            }
        }
    });