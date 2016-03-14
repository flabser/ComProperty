$(function() {
    $.datepicker.setDefaults($.datepicker.regional['ru']);
    $('[type=date]').datepicker({ dateFormat: nb.options.dateFormat });

    // init select2
    if (nb.isMobile()) {
        $('select[name][multiple]').select2();
    } else {
        $('select[name]').select2();
    }

    // need dummy input if no select value
    $('select[name]').on('change', function() {
        if ($(this).val()) {
            $('[data-role=dummy-select][name=' + this.name + ']').remove();
        } else {
            $('<input type=hidden data-role=dummy-select name=' + this.name + ' value="">').appendTo($(this).parent());
        }
    });

    $('[data-action=save_and_close]').click(function(event) {
        event.preventDefault();
        nb.submitForm(nb.getForm(this));
    });

    $('[data-action=delete_document]').click(function(event) {
        event.preventDefault();

        var docids = nb.getSelectedEntityIDs('docid');
        if (!docids.length) {
            return;
        }

        nb.xhrDelete('docid=' + docids.join('&docid=')).then(function() {
            location.reload();
        });
    });

    $('[data-action=delete_document]').attr('disabled', true);
    $(':checkbox').bind('change', function() {
        var countChecked = $('[name=docid]:checked').length;
        $('[data-action=delete_document]').attr('disabled', countChecked === 0);
    });

    $('[name=docid]:checked').attr('checked', false);

    // toogle user registration fields
    $('#reguser').on('change', function() {
        var fieldset = $(this).parents('fieldset');
        var $regfields = $('[name=login], [name=email], [type=password]', fieldset);
        $regfields.prop('disabled', !this.checked).val('');
    });

    $('input[name=pwd], input[name=pwd_confirm]').on('change', function() {
        var $inputs = $('input[type=password]');
        if ($('input[name=pwd]').val() != $('input[name=pwd_confirm]').val()) {
            $inputs.addClass('invalid').prop('title', 'Поля пароль и повтор пароля не совпадают');
        } else {
            $inputs.removeClass('invalid').prop('title', '');
        }
    });
});
