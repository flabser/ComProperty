$(function() {
    var sf = $('form[role=search]');
    if (sf.length) {
        sf[0].reset();
    }

    $('[data-action=save_and_close]').click(function(event) {
        event.preventDefault();
        nb.submitForm(nb.getForm(this));
    });
});
