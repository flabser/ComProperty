$(document).ready(function() {
    // reporttemplate form
    $('form[name=reporttemplate]').on('submit', function(event) {
        event.preventDefault();
        $('[type=submit]', this).attr('disabled', true);

        nb.xhrDownload({
            url: (this.action || this.baseURI || location.href),
            data: $(this).serialize(),
            notify: nb.getText('wait_report', 'Подождите формируется отчет...'),
            blockUi: true,
            success: function() {
                $('[type=submit]', event.target).attr('disabled', false);
            }
        });
    });
});
