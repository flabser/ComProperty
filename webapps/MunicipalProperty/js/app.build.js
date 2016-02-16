/**
 * @author Medet
 */

'use strict';

var nb = {
    APP_NAME: location.hostname,
    LANG_ID: 'RUS',
    debug: true,
    translations: {
        yes: 'Да',
        no: 'Нет',
        ok: 'Ok',
        cancel: 'Отмена',
        select: 'Выбрать',
        dialog_select_value: 'Вы не сделали выбор'
    },
    dialog: {},
    xhr: {}
};

var nbApp = { /* local application namespace */ };

/**
 * ajax
 */
nb.ajax = function(options) {
    return $.ajax(options);
};

/**
 * getText
 */
nb.getText = function(stringKey, defaultText) {
    if (this.translations[stringKey]) {
        return this.translations[stringKey];
    } else {
        return (defaultText !== undefined) ? defaultText : stringKey;
    }
};

/**
 * getForm
 */
nb.getForm = function(el) {
    if (el === null || el === undefined) {
        return el;
    }

    if (typeof(el) === 'string' && (document[el] && document[el].nodeName === 'FORM')) {
        return document[el];
    } else {
        var $pf = $(el).parents('form')
        if ($pf.length) {
            el = $pf.get(0);
        }
    }

    return el.form || el;
};

/**
 * uiBlock
 */
nb.uiBlock = function() {
    var $el = $('#nb-block-ui');
    if ($el.length === 0) {
        $el = $('<div id="nb-block-ui" style="background:rgba(0,0,0,0.1);cursor:wait;position:fixed;top:0;left:0;bottom:0;right:0;z-index:999;"/>');
        $el.appendTo('body');
    }

    $el.css('display', 'block');
};

/**
 * uiUnblock
 */
nb.uiUnblock = function() {
    $('#nb-block-ui').css('display', 'none');
};

// init
$(document).ready(function() {
    nb.LANG_ID = $.cookie('lang') || 'RUS';

    //
    var oreo = localStorage.getItem('side-tree-toggle');
    var ary = [];
    if (oreo != null) {
        ary = oreo.split(',');
    } else {
        localStorage.setItem('side-tree-toggle', '');
    }
    $('[data-nav]', '.aside').each(function() {
        if (ary.indexOf($(this).data('nav')) != -1) {
            $(this).removeClass('nav-link-collapsed');
        }
    });

    $(':checkbox').bind('click', function() {
        var $checkbox = $(this);

        if (!$checkbox.data('toggle')) {
            return true;
        }

        var name = this.name || $checkbox.data('toggle');
        var $el = $('[name=' + name + ']:checkbox:visible');

        if ($checkbox.is(':checked')) {
            $el.each(function() {
                this.checked = true;
            });
        } else {
            $el.each(function() {
                this.checked = false;
            });
        }
    });

    $('[data-role=side-tree-toggle]').click(function(e) {
        e.preventDefault();
        e.stopPropagation();
        var $parent = $(this).parent();
        $parent.toggleClass('nav-link-collapsed');
        //
        var storageKey = 'side-tree-toggle';
        var navId = $parent.data('nav');
        var oreo = localStorage.getItem(storageKey);
        var ary = oreo.split(',');

        if ($parent.hasClass('nav-link-collapsed')) {
            var index = ary.indexOf(navId);
            if (index > -1) {
                ary.splice(index, 1);
            }
            localStorage.setItem(storageKey, ary.join(','));
        } else {
            ary.push(navId);
            localStorage.setItem(storageKey, ary.join(','));
        }
    });

    // toggle=panel
    $(document).on('click', '[data-toggle=panel]', function() {
        var $panel = $(this).parents('.panel');
        if ($panel.hasClass('open')) {
            $panel.removeClass('open');
        } else {
            $panel.addClass('open');
        }
    });

    // toggle=side-nav
    $(document).on('click', '[data-toggle=side-nav]', function(event) {
        event.preventDefault();
        $('body').toggleClass('side-nav-toggle');
    });

    if ($('#content-overlay')) {
        $('#content-overlay').mousedown(function(event) {
            event.preventDefault();
            $('body').removeClass('side-nav-toggle');
        });

        $('#content-overlay')[0].addEventListener('touchstart', function(event) {
            event.preventDefault();
            $('body').removeClass('side-nav-toggle');
        }, false);
    }

    //
    $(window).resize(function() {
        if (window.innerWidth <= 800) {
            $('body').addClass('phone');
        } else {
            $('body').removeClass('phone');
        }
    });

    if (window.innerWidth <= 800) {
        $('body').addClass('phone');
    }

    //
    setTimeout(function() {
        $('body').removeClass('no_transition');
    }, 250);
});

/**
 * dialog
 */
nb.dialog = {
    _props: {
        title: nb.APP_NAME
    },
    info: function(opt) {
        opt.className = 'dialog-info';
        opt.width = opt.width || '360';
        opt.height = opt.height || '210';
        opt.buttons = opt.buttons || {
            'Ok': function() {
                $(this).dialog('close');
            }
        };

        return this.show(opt);
    },
    warn: function(opt) {
        opt.className = 'dialog-warn';
        opt.width = opt.width || '360';
        opt.height = opt.height || '210';
        opt.buttons = opt.buttons || {
            'Ok': function() {
                $(this).dialog('close');
            }
        };

        return this.show(opt);
    },
    error: function(opt) {
        opt.className = 'dialog-error';
        opt.width = opt.width || '360';
        opt.height = opt.height || '210';
        opt.buttons = opt.buttons || {
            'Ok': function() {
                $(this).dialog('close');
            }
        };

        return this.show(opt);
    },
    execute: function(dlgInnerNode) {
        var $dlgw = $(dlgInnerNode).parents('[role=dialog]');
        var $dlgWgt = $('[data-role=nb-dialog]', $dlgw);

        $dlgWgt[0].dialogOptions.onExecute(arguments);
    },
    show: function(options) {
        var $dialog;

        options.id = options.id || null;
        options.title = options.title || this._props.title;
        options.href = options.href || null;
        options.className = options.className || '';
        options.message = options.message || null;
        options.filter = options.filter;
        options.dialogFilterListItem = options.dialogFilterListItem || 'li';
        options.buttons = options.buttons || null;
        options.dialogClass = 'nb-dialog ' + (options.dialogClass ? options.dialogClass : '');
        options.errorMessage = options.errorMessage || nb.getText('dialog_select_value');

        options.onLoad = options.onLoad || null;

        // onExecute
        options.onExecute = options.onExecute || function() {
            if (nb.setFormValues($dialog)) {
                $dialog.dialog('close');
            }
        };

        options.autoOpen = true;
        if (options.modal === false) {
            options.modal = false;
        } else {
            options.modal = true;
        }
        options.width = options.width || '360';
        // options.height = options.height || '420';
        options.position = options.position || 'center';
        options.resizable = options.resizable || false;
        options.draggable = options.draggable || false;

        if (options.id === null && options.href) {
            options.id = 'dlg_' + options.href.replace(/[^a-z0-9]/gi, '');

            $dialog = $('#' + options.id);
            if ($dialog[0]) {
                if ($dialog.dialog('isOpen') === true) {
                    return;
                } else {
                    $dialog.dialog('open');
                    return;
                }
            }
        } else if (options.id !== null) {
            $dialog = $('#' + options.id);
            if ($dialog[0]) {
                if ($dialog.dialog('isOpen') === true) {
                    return;
                } else {
                    $dialog.dialog('open');
                    return;
                }
            }
        }

        if (options.id === null) {
            options.close = options.close || function() {
                $dialog.dialog('destroy');
                $dialog.remove();
            };
        }

        var $dlgContainer;

        if (options.href) {
            $dlgContainer = $('<div data-role="nb-dialog" id="' + options.id + '" class="nb-dialog-container ' + options.className + '"><div class="loading-state"></div></div>');
        } else {
            if (options.id) {
                $dlgContainer = $('<div data-role="nb-dialog" id="' + options.id + '" class="nb-dialog-container ' + options.className + '">' + options.message + '</div>');
            } else {
                $dlgContainer = $('<div data-role="nb-dialog" class="nb-dialog-container ' + options.className + '">' + options.message + '</div>');
            }
        }

        if (options.href) {
            $dialog = $dlgContainer.load(options.href, '', function(response, status, xhr) {
                if (status === 'error') {
                    $dlgContainer.html('<div class="alert alert-danger">' + status + '</div>');

                    if (nb.debug === true) {
                        console.log('nb.dialog : load callback', xhr);
                    }
                } else {
                    try {
                        if (options.onLoad !== null) {
                            options.onLoad(response, status, xhr);
                        }
                    } catch (e) {
                        console.log('nb.dialog', e);
                    }

                    try {
                        if (options.filter !== false) {
                            new nb.dialog.Filter($dlgContainer, options.dialogFilterListItem, 13);
                        }
                    } catch (e) {
                        console.log('nb.dialog', e);
                    }
                }
            }).dialog(options);

            $dialog.on('click', 'a', function(e) {
                e.preventDefault();
                $dlgContainer.load(this.href);
            });

            $dialog.on('change', 'select', function(e) {
                e.preventDefault();
                $dlgContainer.load(this.href);
            });
        } else {
            $dialog = $dlgContainer.dialog(options);
        }

        $dialog[0].dialogOptions = options;

        if (nb.debug === true) {
            console.log('nb.dialog: ', options);
        }

        return $dialog;
    }
};

/**
 * nb.dialog.Filter
 */
nb.dialog.Filter = function(_containerNode, _filterNode, _initCount, _triggerLen) {

    var $inputEl = null;
    var initCount = _initCount || 13;
    var triggerLen = _triggerLen || 2;
    var timeout = 300;
    var to = null;
    var enabledViewSearch = false;
    var filterNode = _filterNode || '.item';
    var $containerNode = _containerNode;
    var $dlgw = $containerNode.parents('[role=dialog]');
    var $collection;

    init();

    function init() {
        $collection = $(filterNode, $containerNode[0]);

        var isHierarchical = $('.toggle-response', $containerNode[0]).length > 0;
        if ($collection.length < initCount) {
            if (!isHierarchical) {
                return;
            }
        }

        if ($('.dialog-filter', $dlgw).length === 0) {
            $containerNode.before('<div class=dialog-filter></div>');
        }

        $('.dialog-filter', $dlgw).append('<input type=text name=keyword data-role=search placeholder="' + nb.getText('filter', 'Фильтр') + '" />');

        $inputEl = $('.dialog-filter input[data-role=search]', $dlgw);
        $inputEl.on('keyup', function(e) {
            try {
                clearTimeout(to);
                if (e.keyCode === 13) {
                    return;
                }
            } catch (ex) {
                console.log(ex);
            }

            to = setTimeout(function() {
                $collection = $(filterNode, $containerNode[0]);
                filter(e.target.value);
            }, timeout);
        });
    }

    function filter(value) {
        try {
            if (value.length >= triggerLen) {
                var hiddenCount = 0;
                var re = new RegExp(value, 'gim');
                $collection.attr('style', '');

                $collection.each(function(index, node) {
                    if (!re.test(node.textContent) && node.textContent.indexOf(value) == -1) {
                        if ($(':checked', node).length === 0) {
                            node.style.display = 'none';
                            hiddenCount++;
                        }
                    }
                });

                if ($collection.length > hiddenCount) {
                    $inputEl.attr('title', 'By keyword [' + value + '] filtered ' + ($collection.length - hiddenCount));
                } else {
                    $inputEl.attr('title', nb.getText('filter_no_results', 'Не найдено'));
                }
            } else {
                $collection.attr('style', '');
                $inputEl.attr('title', '');
            }
        } catch (e) {
            console.log(e);
        }
    }
};

/**
 * windowOpen
 */
nb.windowOpen = function(url, id, callbacks) {
    var features, width = 800,
        height = 600;
    var top = (window.innerHeight - height) / 2,
        left = (window.innerWidth - width) / 2;
    if (top < 0) top = 0;
    if (left < 0) left = 0;
    features = 'top=' + top + ',left=' + left;
    features += ',height=' + height + ',width=' + width + ',resizable=yes,scrollbars=yes,status=no';

    var wid = 'window-' + (id || url.hashCode());

    var w = window.open('', wid, features);
    if ('about:blank' === w.document.URL || w.document.URL === '') {
        w = window.open(url, wid, features);

        if (callbacks && callbacks.onclose) {
            var timer = setInterval(function() {
                if (w.closed) {
                    clearInterval(timer);
                    callbacks.onclose();
                }
            }, 1000);
        }
    }
    w.focus();
};


/**
 * submitForm
 */
nb.submitForm = function(form) {

    if (!nb.validateForm(form)) {
        var dfd = $.Deferred();
        return dfd.reject(false);
    }

    var notify = nb.notify({
        message: nb.getText('wait_while_document_save', 'Пожалуйста ждите... идет сохранение документа'),
        type: 'process'
    }).show();

    var xhrArgs = {
        cache: false,
        type: 'POST',
        datatype: 'json',
        url: 'Provider',
        data: $(form).serialize(),
        beforeSend: function() {
            nb.uiBlock();
            $('.required', form).removeClass('required');
        },
        success: function(response) {
            notify.set({
                text: response.captions.type,
                type: 'success'
            });

            if (response.redirectURL) {
                window.location.href = response.redirectURL;
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

/**
 * validateForm
 */
nb.validateForm = function(form, validation) {
    var result = true;

    if (validation && validation.errors) {
        var ers = validation.errors;
        for (var index in ers) {
            if (index == 0) {
                $('[name=' + ers[index].field + ']').focus();
            }
            $('[name=' + ers[index].field + ']', form).attr('required', 'required').addClass('required');
            var $di = $('[data-input=' + ers[index].field + ']', form).addClass('required');
            var erms = $('<div class=error-massage>' + ers[index].message + '</div>');
            if ($di.length) {
                $di.after(erms);
                $di.parent().addClass('has-error');
            } else {
                $('[name=' + ers[index].field + ']', form).after(erms);
                $('[name=' + ers[index].field + ']', form).parent().addClass('has-error');
            }
        }
    }

    if ($('[required]:invalid', form).length) {
        $('[required]:invalid', form).first().focus();
        result = false;
    }

    return result;
};

/**
 * setFormValues
 */
nb.setFormValues = function(currentNode) {

    var $dlgw = $(currentNode).parents('[role=dialog]');
    var $dlgWgt = $('[data-role=nb-dialog]', $dlgw);

    var form = nb.getForm($dlgWgt[0].dialogOptions.targetForm);
    var fieldName = $dlgWgt[0].dialogOptions.fieldName;

    var nodeList; // коллекция выбранных
    var isMulti = false;
    var itemSeparate = '';
    var displaySeparate = '<br/>'; // отобразить мульти значения разделителем

    if (!form) {
        nb.dialog.warn({
            title: 'Error',
            message: 'Error nb.setFormValues > form is not found: ' + form
        });
        return false;
    }

    nodeList = $('[data-type=select]:checked', $dlgWgt[0]);
    if (nodeList.length > 0) {
        isMulti = nodeList.length > 1;
        if (!isMulti) {
            itemSeparate = '';
        }

        return _writeValues(nodeList);
    } else {
        if ($dlgWgt[0].dialogOptions.effect) {
            $dlgw.stop();
            $dlgw.effect($dlgWgt[0].dialogOptions.effect, {
                times: 2
            }, 300);
        }

        if ($('.js-no-selected-value', $dlgw[0]).length === 0) {
            (function() {
                var $_html = $('<div class="alert alert-danger js-no-selected-value" style="border-radius:2px;bottom:80px;left:4%;right:4%;position:absolute;">' + $dlgWgt[0].dialogOptions.errorMessage + '</div>');
                $dlgWgt.after($_html);
                setTimeout(function() {
                    $_html.fadeOut({
                        always: function() {
                            $_html.remove();
                        }
                    });
                }, 800);
            })();
        }

        return false;
    }

    // write values to form
    function _writeValues() {
        if (isMulti) {
            $('[name=' + fieldName + ']', form).remove();
            var htm = [];
            nodeList.each(function(index, node) {
                $('<input type="hidden" name="' + fieldName + '" value="' + node.value + '" />').appendTo(form);
                htm.push('<li>' + $(node).data('text') + '</li>');
            });
            $('[data-input=' + fieldName + ']', form).html(htm.join(''));
        } else {
            var $fieldNode = $('[name=' + fieldName + ']', form);
            if ($fieldNode.length === 0) {
                $fieldNode = $('<input type="hidden" name="' + fieldName + '" />');
                $(form).append($fieldNode[0]);
            }

            $fieldNode.val(nodeList[0].value);
            $('[data-input=' + fieldName.replace('id', '') + ']', form).html('<li>' + nodeList.attr('data-text') + '</li>');
        }

        return true;
    }
};

/**
 * clearFormField
 */
nb.clearFormField = function(fieldName, context) {
    $('[name=' + fieldName + ']', context).val('');
    $('[data-input=' + fieldName + ']', context).html('');
};

/**
 * notify
 */
nb.notify = function(opt) {

    var $nwrap = $('#nb-notify-wrapper');
    if (!$nwrap.length) {
        $nwrap = $('<div id=nb-notify-wrapper class=nb-notify></div>').appendTo('body');
    }
    var $el = $('<div class=nb-notify-entry-' + (opt.type || 'info') + '>' + opt.message + '</div>').appendTo($nwrap);

    return {
        show: function(timeout) {
            $el.css('display', 'block');
            if (timeout && timeout > 0) {
                this.remove(timeout);
                return;
            }
            return this;
        },
        hide: function() {
            $el.css('display', 'none');
            return this;
        },
        set: function(opt) {
            for (var key in opt) {
                if (key === 'text') {
                    $el.text(opt[key]);
                } else if (key === 'type') {
                    $el.attr('class', 'nb-notify-entry-' + opt[key]);
                }
            }
            return this;
        },
        remove: function(timeout, callback) {
            if ($el === null) {
                return;
            }

            if (timeout && timeout > 0) {
                var _this = this;
                setTimeout(function() {
                    $el.remove();
                    $el = null;
                    callback && callback();
                }, timeout);
            } else {
                $el.remove();
                $el = null;
                callback && callback();
            }
        }
    };
};


nbApp.dialogChoiceBalanceHolder = function(el) {
    var form = nb.getForm(el);
    var dlg = nb.dialog.show({
        targetForm: form.name,
        fieldName: 'balanceholderid',
        title: el.title,
        href: 'Provider?id=get-organizations',
        buttons: {
            'ok': {
                text: nb.getText('select'),
                click: function() {
                    dlg[0].dialogOptions.onExecute();
                }
            },
            'cancel': {
                text: nb.getText('cancel'),
                click: function() {
                    dlg.dialog('close');
                }
            }
        }
    });
};

$(function() {
    var sf = $('form[role=search]');
    if (sf.length) {
        sf[0].reset();
    }

    $('[data-action=save_and_close]').click(function(event) {
        event.preventDefault();
        nb.submitForm(nb.getForm(this));
    });

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
