$(function() {
    $('[data-action=save_and_close]').click(function() {
        nb.submitForm(nb.getForm(this)).then(function() {
            // $('[data-action=close]')[0].click();
        }, function(xhr) {
            /*nb.dialog.error({
                message: xhr.responseJSON.messages[0]
            });*/
        });
    });
});
