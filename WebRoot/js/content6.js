
$(function ()
{
    var show_delay;
    var scroll_div_left = parseInt((document.body.offsetWidth - 990) / 2) + 618;
    if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style) {
        scroll_div_left = parseInt((document.body.offsetWidth - 990) / 2 + 617);
    }
    $('#goTopBox').css("left", scroll_div_left);
    $(window).resize(function ()
    {
        scroll_div_left = parseInt((document.body.offsetWidth - 990) / 2) + 618;
        if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style) {
            scroll_div_left = parseInt((document.body.offsetWidth - 990) / 2 + 617);
        }
        $("#goTopBox").css("left", scroll_div_left);
    });
    reshow(scroll_div_left, show_delay);
    function reshow(margin_l, show_d)
    {
        $("#goTopBox").css("left", margin_l);
        if (show_d) {
            window.clearTimeout(show_d);
        }
        scroll_div_left = parseInt((document.body.offsetWidth - 990) / 2) + 618;
        if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style) {
            scroll_div_left = parseInt((document.body.offsetWidth - 990) / 2 + 617);
        }
        show_d = setTimeout(function ()
        {
            reshow(scroll_div_left);
        }, 100);
    }
    $(window).scroll(function ()
    {
        var h1 = $(document).scrollTop();
        var h2 = $("body").height() - document.documentElement.clientHeight - h1;
        var h3 = $(".footer").height() + $(".copyright").height();
        if (h1 > 100) {
            $('#goTopBox').show();
        }
        if (h1 < 100) {
            $('#goTopBox').hide();
        }
        if (h2 < h3 + 35)
        {
            if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style)
            {
                $('#goTopBox').css("position", "absolute");
                document.getElementById("goTopBox").style.removeExpression('top');
                $('#goTopBox').css("top", ($("body").height() - 55 - h3 - 35) + "px");
            }
            else
            {
                $("#goTopBox").css("position", "absolute");
                $("#goTopBox").css("top", ($("body").height() - 55 - h3 - 35) + "px");
            }
        }
        else
        {
            if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style)
            {
                $('#goTopBox').get(0).style.setExpression('top', '(-20+document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop,10)||0)-(parseInt(this.currentStyle.marginBottom,10)||0))');
            }
            else
            {
                $("#goTopBox").css("position", "fixed");
                $("#goTopBox").css("top", "auto");
                $("#goTopBox").css("bottom", "20px");
            }
        }
    });
});