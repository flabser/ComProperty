$(function() {

    $('[data-action=save_and_close]').click(function() {
        nb.xhr.saveDocument().then(function() {
            $('[data-action=close]')[0].click();
        });
    });

    $('[data-role=side-tree-toggle]').click(function() {
        $(this).parent().toggleClass('side-tree-collapse');
    });

    $(window).resize(function() {
        if (window.innerWidth <= 800) {
            $('body').addClass('phone');
        } else {
            $('body').removeClass('phone');
        }
    });

    if (window.innerWidth <= 800) {
        $('body').addClass('phone');
    }
});
