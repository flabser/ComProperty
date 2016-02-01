$(function() {
    $('[data-action=save_and_close]').click(function() {
        nb.xhr.saveDocument().then(function() {
            $('[data-action=close]')[0].click();
        }, function(xhr) {
            nb.dialog.error({
                message: xhr.responseJSON.message[0]
            });
        });
    });

    $('[data-toggle-theme]').click(function() {
        var themeName = $(this).data('toggle-theme');
        if ($('body').hasClass('theme1')) {
            $('body').removeClass(themeName);
            localStorage.setItem('theme', '');
        } else {
            $('body').addClass(themeName);
            localStorage.setItem('theme', themeName);
        }
    });

    var theme = localStorage.getItem('theme');
    if (theme != null && theme != '') {
        $('body').addClass(theme);
    }
});
