$(function() {
    $.datepicker.setDefaults($.datepicker.regional['ru']);
    $('[type=date]').datepicker({ dateFormat: nb.options.dateFormat });

    // init select2
    if (nb.isMobile()) {
        $('select[multiple]').select2();
    } else {
        $('select').select2();
    }

    // need dummy input if no select value
    $('select').on('change', function() {
        if ($(this).val()) {
            $('[data-role=dummy-select][name=' + this.name + ']').remove();
        } else {
            $('<input type=hidden data-role=dummy-select name=' + this.name + '>').appendTo($(this).parent()).val('');
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
    if (theme) {
        $('body').addClass(theme);
    }
});
