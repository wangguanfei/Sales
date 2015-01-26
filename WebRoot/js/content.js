
$(function ()
{
    $(window).scroll(function ()
    {
        var h1 = $(document).scrollTop();
        if (h1 > 600) {
        	$("#toBox").css("display","none");
            $('#goTopBox').show();
        }
        if (h1 < 1200) {
        	$("#toBox").css("display","block");
            $('#goTopBox').hide();
        }
        //$("#goTopBox").css("bottom", h1-60+"px");
    });
});
