$(function() {
    $('[data-action=save_and_close]').click(function() {
        nb.submitForm(nb.getForm(this));
    });
});
