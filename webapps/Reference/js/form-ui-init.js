$(function() {
    var sf = $('form[role=search]');
    if (sf.length) {
        sf[0].reset();
    }

    $('[data-action=save_and_close]').click(function() {
        nb.submitForm(nb.getForm(this));
    });
});
