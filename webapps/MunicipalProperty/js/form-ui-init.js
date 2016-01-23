$(function() {

    $('[data-action=save_and_close]').click(function() {
        nb.xhr.saveDocument().then(function() {
            $('[data-action=close]')[0].click();
        });
    });
});
