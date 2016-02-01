/**
 * saveDocument
 */
nb.xhr.saveDocument = function(options) {

    options = options || {};
    var notify = nb.notify({
        message: nb.getText('wait_while_document_save', 'Пожалуйста ждите... идет сохранение документа'),
        type: 'process'
    }).show();

    var xhrArgs = {
        cache: false,
        type: 'POST',
        datatype: 'XML',
        url: 'Provider',
        data: options.data || $('form').serialize(),
        beforeSend: function() {
            nb.uiBlock();
            $('.required', 'form').removeClass('required').removeAttr('required');
        },
        success: function(response) {
            notify.set({
                'text': nb.getText('document_saved', 'Документ сохранен'),
                'type': 'success'
            });

            return response;
        },
        error: function(err) {
            notify.set({
                'text': nb.getText('error_xhr', 'Ошибка при выполнении запроса'),
                'type': 'error'
            });

            return err;
        },
        complete: function() {
            nb.uiUnblock();
            notify.remove(2000);
        }
    };

    return nb.ajax(xhrArgs);
};

/**
 * deleteDocument
 */
nb.xhr.deleteDocument = function(ck, typeDel) {

    if (nb.debug === true) {
        console.log('nb.xhr.deleteDocument: ', ck, typeDel);
    }

    return nb.ajax({
        type: 'POST',
        datatype: 'XML',
        url: 'Provider',
        data: {
            'type': 'delete',
            'ck': ck,
            'typedel': typeDel
        }
    });
};
