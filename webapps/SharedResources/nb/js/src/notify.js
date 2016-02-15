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
