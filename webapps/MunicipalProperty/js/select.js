nbApp.selectOptions = {
    balanceholder: {
        url: 'p?id=get-organizations'
    },
    orgcategory: {
        url: 'p?id=get-org-categories'
    },
    department: {
        url: 'p?id=get-departments',
        data: ['organization']
    },
    position: {
        url: 'p?id=get-positions'
    },
    propertycode: {
        url: 'p?id=get-property-codes'
    },
    receivingreason: {
        url: 'p?id=get-receiving-reasons'
    },
    district: {
        url: 'p?id=get-districts',
        data: ['region']
    },
    street: {
        url: 'p?id=get-streets',
        data: ['district']
    },
    tags: {
        url: 'p?id=get-tags',
        fields: ['color'],
        templateResult: function(item) {
            if (!item.id || !item.color) {
                return item.text;
            }

            var $item = $('<span style="color:' + item.color + '">' + item.text + '</span>');
            return $item;
        }
    }
};
