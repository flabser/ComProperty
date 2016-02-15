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
        datatype: 'json',
        url: 'Provider',
        data: options.data || $('form').serialize(),
        beforeSend: function() {
            nb.uiBlock();
            $('.required, [required]', 'form').removeClass('required').removeAttr('required');
        },
        success: function(response) {
            notify.set({
                text: response.captions.type,
                type: 'success'
            });

            window.location.href = response.redirectURL;
            return response;
            /*var jmsg = nb.parseMessageToJson(xml);
             var msgText = jmsg.message[0];
             if (jmsg.status === 'ok') {
                 notify.set({
                     'text': nb.getText('document_saved', 'Документ сохранен'),
                     'type': 'success'
                 });
                 //
                 if (msgText.length > 0) {
                     nb.dialog.info({
                         message: msgText,
                         close: function() {
                             if (jmsg.redirect || options.redirect) {
                                 window.location.href = jmsg.redirect || options.redirect;
                             }
                         }
                     });
                 } else {
                     if (jmsg.redirect || options.redirect) {
                         setTimeout(function() {
                             window.location.href = jmsg.redirect || options.redirect;
                         }, 300);
                     }
                 }
             } else {
                 if (msgText.indexOf('require:') === 0) {
                     var fields = msgText.substr('require:'.length).split(',');
                     for (i = 0; i < fields.length; i++) {
                         $('#' + fields[i] + 'tbl').addClass('required');
                         $('[name=' + fields[i] + ']').attr('required', 'required').addClass('required');
                     }
                     notify.set({
                         'text': nb.getText('required_field_not_filled', 'Не заполнены обязательные поля'),
                         'type': 'error'
                     });
                 } else {
                     notify.set({
                         'text': msgText,
                         'type': 'error'
                     });
                 }
             }*/
        },
        error: function(err) {
            var response = err.responseJSON;
            var msg = {
                type: 'error'
            };
            if (response.type === 'VALIDATION_ERROR') {
                msg.text = response.captions.type;
            } else if (response.type === 'SERVER_ERROR') {
                msg.text = response.captions.type;
            }
            notify.set(msg);
            return err;
        },
        complete: function() {
            nb.uiUnblock();
            notify.remove(2000);
        }
    };

    return nb.ajax(xhrArgs);
};
