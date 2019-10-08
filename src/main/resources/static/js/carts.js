"use strict";

function _typeof(t) {
    return (_typeof = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(t) {
        return typeof t
    } : function(t) {
        return t && "function" == typeof Symbol && t.constructor === Symbol && t !== Symbol.prototype ? "symbol" : typeof t
    })(t)
}! function(s) {
    var t = s.Callbacks(),
        o = s.Callbacks(),
        n = s.Callbacks(),
        c = s.Callbacks(),
        i = s.Callbacks(),
        a = s.Callbacks(),
        e = {
            pxxList: {},
            carts: localStorage.getItem("carts") ? JSON.parse(localStorage.getItem("carts")) : {},
            shopAttr: "pxx"
        };

    function r(t) {
        var o = t.goods_list;
        s.each(o, function(t, o) {
            e.pxxList[t] = o
        })
    }

    function l() {
        var o = s(this).parent().attr("data-id");
        e.carts[e.shopAttr] instanceof Array || (e.carts[e.shopAttr] = []), e.carts[e.shopAttr].some(function(t) {
            if (t.id == o) return t.count++, !0
        }) || e.carts[e.shopAttr].push({
            id: o,
            count: 1,
            price: parseFloat(s(this).prev(".price").children("strong").html())
        }), v("carts", e.carts)
    }

    function u() {
        1 == s(this)[0].checked ? s("input[type='checkbox']").each(function() {
            this.checked = !0
        }) : s("input[type='checkbox']").each(function() {
            this.checked = !1
        })
    }

    function f() {
        for (var t = s(".single"), o = e.carts[e.shopAttr], n = 0, c = 0, i = 0; i < t.length; i++) 1 == t[i].checked && (n += o[i].count, c += o[i].price * o[i].count);
        s("#J_selTotalNum").html(n), s("#J_cartTotalPrice").html(c.toFixed(2))
    }

    function d() {
        var o = s(".single"),
            n = 0;
        o.each(function(t) {
            if (1 == o[t].checked) return n++;
            n--
        }), o.length == n ? s(".allsel")[0].checked = !0 : s(".allsel")[0].checked = !1
    }

    function h() {
        var n = s(this).parent().attr("data-id");
        e.carts[e.shopAttr].some(function(t, o) {
            if (t.id == n) return t.count <= 1 || t.count--, !0
        }), v("carts", e.carts)
    }

    function p() {
        var n = s(this).parent(".col-action").attr("data-id");
        e.carts[e.shopAttr].some(function(t, o) {
            if (t.id == n) return function(t, o) {
                e.carts[e.shopAttr].splice(o, 1)
            }(0, o), !0
        }), v("carts", e.carts)
    }

    function v(t, o) {
        localStorage.setItem(t, JSON.stringify(o))
    }

    function m() {
        var t = e.carts[e.shopAttr],
            o = 0;
        (t = t || []).forEach(function(t) {
            o += t.count
        }), s("#J_cartTotalNum").html(o)
    }

    function b() {
        var t = e.carts[e.shopAttr],
            o = [];
        if (!t) return !1;
        for (var n in e.pxxList) o.push(e.pxxList[n]);
        var c = o,
            i = [];
        t.forEach(function(o) {
            c.some(function(t) {
                if (o.id == t.goods_id) return t.count = o.count, i.push(t), !0
            })
        });
        var a = "";
        i.forEach(function(t) {
            a += '<div class="item-box">\n            <div class="item-table">\n                <div class="col col-check">\n                    <div class="ipt" data-id="'.concat(t.goods_id, '">\n                        <input type="checkbox"  class="single">\n                    </div>\n                </div>\n                <div class="col col-img">\n                    <a href="javascript:void(0)">\n                        <img src="').concat(t.hd_thumb_url, '" alt="">\n                    </a>\n                </div>\n                <div class="col col-name">').concat(t.goods_name, '</div>\n                <div class="col col-price">').concat((t.normal_price / 100).toFixed(2), '元</div>\n                <div class="col col-num">\n                    <div class="change-goods-num options" data-id="').concat(t.goods_id, '">\n                        <a href="javascript:void(0)" class="quantity-down">-</a>\n                        <input type="text" value="').concat(t.count, '">\n                        <a href="javascript:void(0)" class="quantity-add">+</a>\n                    </div>\n                </div>\n                <div class="col col-total">').concat((t.count * (t.normal_price / 100)).toFixed(2), '元</div>\n                <div class="col col-action options" data-id="').concat(t.goods_id, '">\n                    <a href="javascript:void(0)" class="del-all">X</a>\n                </div>\n            </div>\n        </div>')
        }), s(".list-body").html(a)
    }

    function y() {
        var t = e.carts,
            o = e.shopAttr;
        if (!(t[o] instanceof Array)) return !1;
        t[o].forEach(function(t) {
            s(".change-goods-num[data-id=".concat(t.id, "]")).children().eq(1).val(t.count)
        })
    }

    function g() {
        s(this).parent().parent().parent().remove()
    }
    s(function() {
        fetch("./json/pxx.json").then(function(t) {
            return t.json()
        }).then(t.fire), t.add([r, b, m, f]), o.add([l, m, y, f]), n.add([h, m, d, y, f]), c.add([p, m, d, g, f]), i.add([u, f]), a.add([d, f]), s(".list-body").on("click", ".quantity-add", o.fire), s(".list-body").on("click", ".quantity-down", n.fire), s(".list-body").on("click", ".del-all", c.fire), s(".list-body").on("click", ".single", a.fire), s(".list-head").on("click", ".allsel", i.fire)
    })
}(jQuery), $(".nav1 li").mouseover(function() {
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
    }),
    function() {
        function t(t, o) {
            var n = {
                expires: -1
            };
            ! function(t, o, n) {
                "object" === _typeof(n) ? Object.assign({}, n) : n = {};
                var c = "";
                if (c += t + "=" + encodeURI(o), "number" == typeof n.expires) {
                    var i = new Date;
                    i.setDate(i.getDate() + n.expires), c += ";expires=" + i
                }
                c += n.path ? ";path=" + n.path : "", c += n.domain ? ";domain=" + n.domain : "", document.cookie = c
            }(t, null, o = "object" == _typeof(o) ? Object.assign(n, o) : n)
        }
        document.cookie.split("=").some(function(t) {
            if ("username" == t) {
                var o = function(t) {
                    var o = document.cookie.split("; "),
                        n = [],
                        c = o.map(function(t) {
                            var o = t.split("=");
                            return n.push(o[0]), o[1]
                        }),
                        i = n.indexOf(t);
                    return -1 !== i ? c[i] : ""
                }("username");
                return $(".topbar-info a").eq(0).html("您好，" + o), $(".topbar-info a").eq(0).attr("href", "javascript:void(0)"), $(".topbar-info a").eq(1).html("注销"), !0
            }
        }), "注销" == $(".topbar-info a").eq(1).html() ? ($(".topbar-info a").eq(1).attr("href", "javascript:void(0)"), $(".topbar-info a").eq(1).on("click", function() {
            t("username", {
                path: "/"
            }), history.go(0)
        })) : $(".topbar-info a").eq(1).children().attr("href", "./register.html")
    }();