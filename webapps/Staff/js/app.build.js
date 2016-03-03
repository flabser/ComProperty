/**
 * @author Medet
 */

'use strict';

var nb = {
    APP_NAME: location.hostname,
    LANG_ID: 'RUS',
    debug: false,
    translations: {
        yes: 'Да',
        no: 'Нет',
        ok: 'Ok',
        cancel: 'Отмена',
        select: 'Выбрать',
        dialog_select_value: 'Вы не сделали выбор'
    },
    tpl: {}
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

/**
 * template
 * @param templateId - template function name defined in nb.tpl
 * @param data - data for tamplate
 */
nb.template = function(templateId, data) {
    if (nb.tpl[templateId]) {
        return nb.tpl[templateId].call(this, data);
    }
    //
    throw new Error('nb.error > Template not found: ' + templateId + ', data: ' + data);
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
        $panel.toggleClass('open');
    });

    // toggle=side-nav
    $(document).on('click', '[data-toggle=side-nav]', function(event) {
        event.preventDefault();
        $('body').toggleClass('side-nav-toggle');
    });

    if ($('#content-overlay').length) {
        $('#content-overlay').mousedown(function(event) {
            event.preventDefault();
            $('body').removeClass('side-nav-toggle search-open');
            if ($('.navbar-search input.q').length) {
                $('.navbar-search input.q')[0].blur();
            }
        });

        $('#content-overlay')[0].addEventListener('touchstart', function(event) {
            event.preventDefault();
            $('body').removeClass('side-nav-toggle search-open');
            if ($('.navbar-search input.q').length) {
                $('.navbar-search input.q')[0].blur();
            }
        }, false);
    }

    //
    $('.navbar-search input.q').on('focus', function() {
        $('body').addClass('search-open');
    });
    $('.navbar-search input.q').on('blur', function() {
        $('body').removeClass('search-open');
    });

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
        opt.width = opt.width || 360;
        opt.height = opt.height || 210;
        opt.buttons = opt.buttons || {
            'Ok': function() {
                $(this).dialog('close');
            }
        };

        return this.show(opt);
    },
    warn: function(opt) {
        opt.className = 'dialog-warn';
        opt.width = opt.width || 360;
        opt.height = opt.height || 210;
        opt.buttons = opt.buttons || {
            'Ok': function() {
                $(this).dialog('close');
            }
        };

        return this.show(opt);
    },
    error: function(opt) {
        opt.className = 'dialog-error';
        opt.width = opt.width || 360;
        opt.height = opt.height || 210;
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
    resize: function($dialog) {
        var $dlgw = $($dialog[0]).parents('[role=dialog]');
        //
        if ($dlgw.css('display') === 'none') {
            return;
        }
        //
        var titleBarHeight = $('.ui-dialog-titlebar', $dlgw[0]).outerHeight();
        var actionBarHeight = $('.ui-dialog-buttonpane', $dlgw[0]).outerHeight()
        var searchBarHeight = $('.dialog-filter', $dlgw[0]).outerHeight()
        var barHeight = titleBarHeight + actionBarHeight + searchBarHeight;
        //
        var wh = window.innerHeight;
        var top, height;
        var containerNode = $('.nb-dialog-container', $dlgw[0]).get(0);
        var fch = containerNode.offsetTop;
        for (var i = 0; i < containerNode.children.length; i++) {
            fch += containerNode.children[i].clientHeight + containerNode.children[i].offsetTop;
        }
        //
        if (wh < 600 && fch > 300) {
            top = window.scrollY + 1;
            height = wh - 3;
        } else {
            if (wh > 800 && fch > 600) {
                height = wh / 1.2;
            } else {
                height = (fch > 60 ? fch : 60) + barHeight;
            }
            top = (wh - height) / 2;
            if (top < 0) {
                top = window.scrollY + 1;
                height = wh - 3;
            } else {
                top = top + window.scrollY;
            }
        }
        //
        var left = (window.innerWidth - $dlgw.outerWidth()) / 2;
        //
        $dlgw.css({
            height: height + 'px',
            top: top + 'px',
            left: left + 'px'
        });
        //
        // console.log('bh:' + barHeight, 'wh:' + wh, 'fch:' + fch, 'top:' + top, 'dlgH:' + $dlgw[0].clientHeight, 'ch:' + containerNode.clientHeight);
        //
        $(containerNode).css({
            height: ($dlgw[0].clientHeight - barHeight) + 'px',
            maxHeight: 'none'
        });
    },
    load: function(url, $container, options) {
        return $.ajax({
            url: url,
            dataType: options.dataType || 'html',
            beforeSend: function() {
                $container.addClass('loading');
            },
            success: function(response, status, xhr) {
                if (status === 'error') {
                    $container.html('<div class="alert alert-danger">' + status + '</div>');
                } else {
                    if (options.dataType === 'json') {
                        $container.html(nb.template.call(options, options.templateId, response));
                    } else {
                        $container.html(response);
                    }

                    if (options.onLoad !== null) {
                        options.onLoad(response, status, xhr);
                    }
                    if (options.filter !== false) {
                        new nb.dialog.Filter($container, options.dialogFilterListItem, 13);
                    }
                }

                try {
                    window.dispatchEvent(new Event('resize'));
                } catch (e) {}

                if (nb.debug === true) {
                    console.log('nb.dialog : load callback', xhr);
                }
            },
            error: function(response, status, xhr) {
                if (status === 'error') {
                    $container.html('<div class="alert alert-danger">' + status + '</div>');
                }

                try {
                    window.dispatchEvent(new Event('resize'));
                } catch (e) {}

                if (nb.debug === true) {
                    console.log('nb.dialog : load error', xhr);
                }
            },
            complete: function() {
                $container.removeClass('loading');
            }
        });
    },
    show: function(options) {
        var self = this;
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
        options.templateId = options.templateId || 'defaultDialogListTemplate';

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

        options.width = options.width || 500;
        options.position = { top: window.scrollY };
        options.resizable = false;
        options.draggable = false;

        if (!options.id && options.href) {
            options.id = 'dlg_' + options.href.replace(/[^a-z0-9]/gi, '');

            $dialog = $('#' + options.id);
            if ($dialog[0]) {
                if ($dialog.dialog('isOpen') === true) {
                    return;
                } else {
                    $dialog.dialog('open');
                    self.resize($dialog);
                    return;
                }
            }
        } else if (options.id) {
            $dialog = $('#' + options.id);
            if ($dialog[0]) {
                if ($dialog.dialog('isOpen') === true) {
                    return;
                } else {
                    $dialog.dialog('open');
                    self.resize($dialog);
                    return;
                }
            }
        }

        var $dlgContainer;

        if (options.href) {
            $dlgContainer = $('<div data-role="nb-dialog" id="' + options.id + '" class="nb-dialog-container loading ' + options.className + '"></div>');
        } else {
            if (options.id) {
                $dlgContainer = $('<div data-role="nb-dialog" id="' + options.id + '" class="nb-dialog-container ' + options.className + '">' + options.message + '</div>');
            } else {
                $dlgContainer = $('<div data-role="nb-dialog" class="nb-dialog-container ' + options.className + '">' + options.message + '</div>');
            }
        }

        $dlgContainer[0].dialogOptions = options;

        if (options.href) {
            self.load(options.href, $dlgContainer, options);

            $dialog = $dlgContainer.dialog(options);

            $dlgContainer.on('click', 'a', function(e) {
                e.preventDefault();
                self.load(this.href, $dlgContainer, options);
            });
        } else {
            $dialog = $dlgContainer.dialog(options);
        }

        if (!options.id) {
            options.close = options.close || function() {
                $dialog.dialog('destroy');
                $dialog.remove();
                $($dialog.resizeEvent).off();
            };
        }

        var doTimeout;
        $dialog.resizeEvent = $(window).on('resize', function() {
            clearTimeout(doTimeout);
            doTimeout = setTimeout(function() {
                self.resize($dialog);
            }, 100);
        });

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
        if ($('.dialog-filter input[data-role=search]', $dlgw).length !== 0) {
            return;
        }

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
        dataType: 'json',
        url: 'Provider',
        data: $(form).serialize(),
        beforeSend: function() {
            nb.uiBlock();
            // clear errors
            $('.required, .has-error', form).removeClass('required has-error');
            $('.error-massage', form).remove();
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
            var $erms = $('<div class=error-massage>' + ers[index].message + '</div>');
            if ($di.length) {
                $di.after($erms);
                $di.parent().addClass('has-error');
            } else {
                $('[name=' + ers[index].field + ']', form).after($erms);
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
    var dialogOptions = $dlgWgt[0].dialogOptions;

    var form = nb.getForm(dialogOptions.targetForm);
    var fields = dialogOptions.fields;

    if (!form) {
        nb.dialog.warn({
            title: 'Error',
            message: 'Error nb.setFormValues > form is not found: ' + form
        });
        return false;
    }

    var dataList = $('[data-type=select]:checked', $dlgw); // коллекция выбранных
    if (dataList.length) {
        return _writeValues();
    } else {
        if (dialogOptions.effect) {
            $dlgw.stop();
            $dlgw.effect(dialogOptions.effect, {
                times: 2
            }, 300);
        }

        if ($('.no-selected-value', $dlgw[0]).length === 0) {
            (function() {
                var $_html = $('<div class=no-selected-value>' + dialogOptions.errorMessage + '</div>');
                $dlgWgt.after($_html);
                setTimeout(function() {
                    $_html.fadeOut({
                        always: function() {
                            $_html.remove();
                        }
                    });
                }, 500);
            })();
        }

        return false;
    }

    // write values to form
    function _writeValues() {
        var isMulti = dataList.length > 1;
        var multiFieldMap = {};
        //
        dataList.each(function() {
            var dataId = this.value;
            //
            var field;
            var $val;
            var $targetFieldNode;
            var value;
            var text;
            //
            for (field in fields) {
                //
                $val = $('[data-id=' + dataId + '][name=' + fields[field][0] + ']', $dlgw);
                $targetFieldNode = $('[name=' + field + ']', form);
                value = $val.val();
                text = $val.data('text');
                if (!text) text = value;
                //
                if (isMulti) {
                    if (multiFieldMap[field] !== true) {
                        $targetFieldNode.remove();
                    }
                    $targetFieldNode = $('<input type="hidden" name="' + field + '" />');
                    $targetFieldNode.appendTo(form);
                } else {
                    var $hf = $('[type=hidden][name=' + field + ']', form);
                    if ($hf.length > 1) {
                        $hf.remove();
                        $targetFieldNode = $('<input type="hidden" name="' + field + '" />');
                        $targetFieldNode.appendTo(form);
                    }
                }
                //
                if ($targetFieldNode.length === 0) {
                    $targetFieldNode = $('<input type="hidden" name="' + field + '" />');
                    $targetFieldNode.appendTo(form);
                }
                //
                $targetFieldNode.val(value);
                //
                if (isMulti) {
                    if (multiFieldMap[field] !== true) {
                        multiFieldMap[field] = true;
                        $('[data-input=' + field.replace('id', '') + ']', form).html('<li>' + text + '</li>');
                    } else {
                        $('[data-input=' + field.replace('id', '') + ']', form).append('<li>' + text + '</li>');
                    }
                } else {
                    $('[data-input=' + field.replace('id', '') + ']', form).html(text);
                }
            }
        });
        return true;
    }
};

/**
 * clearFormFields
 */
nb.clearFormFields = function(fields, el) {
    var form = nb.getForm(el);
    for (var index in fields) {
        $('[name=' + fields[index] + ']', form).val('');
        $('[data-input=' + fields[index] + ']', form).html('');
    }
};

/**
 * notify
 */
nb.notify = function(options) {

    var $nwrap = $('#nb-notify-wrapper');
    if (!$nwrap.length) {
        $nwrap = $('<div id=nb-notify-wrapper class=nb-notify></div>').appendTo('body');
    }
    var $el = $('<div class=nb-notify-entry-' + (options.type || 'info') + '>' + options.message + '</div>').appendTo($nwrap);

    return {
        show: function(timeout, callback) {
            $el.css('display', 'block');
            if (timeout && timeout > 0) {
                this.remove(timeout, callback);
                return;
            }
            return this;
        },
        hide: function() {
            $el.css('display', 'none');
            return this;
        },
        set: function(options) {
            for (var key in options) {
                if (key === 'text') {
                    $el.text(options[key]);
                } else if (key === 'type') {
                    $el.attr('class', 'nb-notify-entry-' + options[key]);
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

/**
 * doDelete
 */
nb.xhrDelete = function(data) {
    return $.ajax({
        type: 'DELETE',
        dataType: 'json',
        url: location.href + '&' + data
    });
};

nb.tpl = {};

/**
 * defaultDialogListTemplate
 */
nb.tpl.defaultDialogListTemplate = function(data) {

    if (!data) {
        return 'data_error';
    }

    var models = data.objects[0];
    if (!models.length) {
        return nb.getText('empty');
    }

    var fields = this.fields;
    var dialogId = this.id;
    var m, index, tfname, fname, ftext, dataText;
    var html = [];

    html.push('<ul class=nb-dialog-list>');
    for (index in models) {
        m = models[index];
        html.push('<li class=nb-dialog-list-it>');
        html.push(' <label ondblclick="nb.dialog.execute(this)">');
        html.push('  <input data-type="select" type="radio" name="select_' + dialogId + '" value="' + m.id + '"/>');
        html.push('  <span>' + m.name + '</span>');

        for (tfname in fields) {
            fname = fields[tfname][0];
            ftext = fields[tfname][1];
            if (ftext) {
                dataText = ' data-text="' + m[ftext] + '"';
            } else {
                dataText = '';
            }
            html.push('<input data-id="' + m.id + '" name="' + fname + '" value="' + m[fname] + '"' + dataText + ' type="hidden"/>');
        }

        html.push(' </label>');
        html.push('</li>');
    }
    html.push('</ul>');

    return html.join('');
};

nb.getSelectedEntityIDs = function(checkboxName) {
    var $checked = $('input[name=' + (checkboxName || 'docid') + ']:checked');
    if ($checked.length === 0) {
        return [];
    }

    var result = [];
    $checked.each(function() {
        result.push(this.value);
    });

    return result;
};

nb.setSearchReferToSessionStorage = function() {
    if (location.href.indexOf('id=search') == -1) {
        sessionStorage.setItem('search_refer', location.href);
    }
};


nb.resetSearchFromRefer = function() {
    var refer = sessionStorage.getItem('search_refer');
    if (refer) {
        location.href = refer;
    } else {
        history.back();
    }
};


// init
$(document).ready(function() {
    $('form[name=ft-search]').on('submit', function() {
        nb.setSearchReferToSessionStorage();
        return true;
    });

    $('[data-action=reset_search]').click(function(event) {
        event.preventDefault();
        nb.resetSearchFromRefer();
    });
});

$(function() {
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
});
