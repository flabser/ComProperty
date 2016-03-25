nbApp.submitRegForm = function(form) {

    if (!nb.validateForm(form)) {
        var dfd = $.Deferred();
        return dfd.reject(false);
    }

    var notify = nb.notify({
        message: nb.getText('wait_reg_request', 'Пожалуйста ждите... выполнение запроса на регистрацию'),
        type: 'process'
    }).show();

    var xhrArgs = {
        cache: false,
        type: 'POST',
        dataType: 'json',
        url: form.action || form.baseURI,
        data: $(form).serialize(),
        beforeSend: function() {
            nb.uiBlock();
            // clear errors
            $('.required, .has-error', form).removeClass('required has-error');
            $('.error-massage', form).remove();
        },
        success: function(response) {
            notify.set({
                text: response.captions.reg_request_accepted,
                type: 'success notify-large'
            }).remove('click');

            if (response.redirectURL) {
                if (response.redirectURL === '_back') {
                    history.back();
                } else {
                    window.location.href = response.redirectURL;
                }
            }
            return response;
        },
        error: function(err) {
            var response = err.responseJSON;
            var msg = {
                type: 'error'
            };
            if (response.type === 'VALIDATION_ERROR') {
                msg.text = response.captions.type;
                nb.validateForm(form, response.validation);
            } else if (response.type === 'SERVER_ERROR') {
                msg.text = response.captions.type;
            } else {
                msg.text = 'Error: ' + err.status;
            }
            notify.set(msg).remove(2000);
            return err;
        },
        complete: function() {
            nb.uiUnblock();
        }
    };

    return nb.ajax(xhrArgs);
};

document.addEventListener('DOMContentLoaded', function() {
    var form = document.getElementById('sign-up');
    if (form) {
        form.addEventListener('submit', function(e) {
            e.preventDefault();
            nbApp.submitRegForm(this);
        });
    }
});
