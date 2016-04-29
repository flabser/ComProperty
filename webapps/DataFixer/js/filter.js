$(document).ready(function() {
    $('select', '#property-filter').on('change', function(e) {
        var urlParams = location.search.split('&');
        for (var i in urlParams) {
            if (urlParams[i].split('=')[0] == this.name || urlParams[i].split('=')[0] == 'page') {
                urlParams.splice(i, 1);
            }
        }

        var url = urlParams.join('&') + '&' + $(this).serialize();
        location.href = location.pathname + url;
    });

    $('.pagination select').on('change', function(e) {
        e.preventDefault();
    });

    //
    var filterStatus,
        sbh = [],
        ubh = location.search.split('&'),
        p,
        param;
    for (p in ubh) {
        param = ubh[p].split('=')[0];
        if (param === 'balanceholder') {
            sbh.push('ids=' + ubh[p].split('=')[1]);
        } else if (param === 'status') {
            filterStatus = ubh[p].split('=')[1];
        }
    }
    if (sbh.length) {
        $.ajax({
            url: '/Staff/p?id=get-organizations&' + sbh.join('&'),
            dataType: 'json',
            success: function(data) {
                var list = data.objects[0].list;
                if (list) {
                    for (var m in list) {
                        $('select[name=balanceholder]').append($('<option value="' + list[m].id + '" selected>' + list[m].name + '</option>'));
                    }
                    var opt = nb.getSelectOptions(nbApp.selectOptions['balanceholder']);
                    $('select[name=balanceholder]').select2(opt);
                }
            }
        });
    }
    //
    if (filterStatus) {
        $('select[name=status]').val(filterStatus).trigger('change.select2');
    }
});
