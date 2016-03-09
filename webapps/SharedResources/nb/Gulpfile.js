var gulp = require('gulp');
var jshint = require('gulp-jshint');
var concat = require('gulp-concat');
var rename = require('gulp-rename');
var uglify = require('gulp-uglify');
var csso = require('gulp-csso');
var wrap = require('gulp-wrap');
var declare = require('gulp-declare');
var handlebars = require('gulp-handlebars');

// npm remove gulp gulp-concat gulp-csso gulp-jshint gulp-rename gulp-uglify jshint

var modules = ['Administrator', 'Accountant', 'Staff', 'Reference', 'MunicipalProperty'];
var _styles = {};
var _templates = {};
var _scripts = {};
var upPath = '../../';

// create module task
for (var i = 0; i < modules.length; i++) {
    var module = modules[i];
    // _styles
    _styles[module] = ['../css/normalize.css',
        'css/nb.min.css',
        upPath + module + '/css/**/*.css',
        '!' + upPath + module + '/css/*.min.css'
    ];
    // _templates
    _templates[module] = [upPath + module + '/js/templates/*.hbs'];
    // _scripts
    _scripts[module] = ['js/nb.build.js',
        upPath + module + '/js/**/*.js',
        '!' + upPath + module + '/js/*.build.js',
        '!' + upPath + module + '/js/*.min.js'
    ];

    console.log('task', module, '\n_styles:\n', _styles[module], '\n_templates:\n', _templates[module], '\n_scripts:\n', _scripts[module]);

    (function() {
        var m = module;
        gulp.task(m + '_styles', function() {
            gulp.src(_styles[m])
                .pipe(concat('all.min.css'))
                .pipe(csso())
                .pipe(gulp.dest(upPath + m + '/css'));
        });

        gulp.task(m + '_templates', function() {
            gulp.src(_templates[m])
                .pipe(handlebars({
                    handlebars: require('handlebars')
                }))
                .pipe(wrap('Handlebars.template(<%= contents %>)'))
                .pipe(declare({
                    namespace: 'nb.templates',
                    noRedeclare: true,
                }))
                .pipe(concat('templates.js'))
                .pipe(gulp.dest(upPath + m + '/js/templates/compiled'));
        });

        gulp.task(m + '_scripts', function() {
            gulp.src(_scripts[m])
                .pipe(concat('app.build.js'))
                .pipe(gulp.dest(upPath + m + '/js'))
                .pipe(rename('app.min.js'))
                .pipe(uglify())
                .pipe(gulp.dest(upPath + m + '/js'));
        });
    })();
}

// nb task
var js_files = ['../vendor/handlebars/handlebars.runtime-v4.0.5.js',
    '../vendor/jquery/ui-i18n/*.js',
    'js/src/nb.js',
    'js/src/**/*.js'
];
var css_files = ['./css/**/*.css',
    '!./css/nb.min.css'
];
var hbs_templates = ['js/src/templates/*.hbs'];

gulp.task('lint', function() {
    gulp.src(js_files)
        .pipe(jshint())
        .pipe(jshint.reporter('default'));
});

gulp.task('templates', function() {
    gulp.src(hbs_templates)
        .pipe(handlebars({
            handlebars: require('handlebars')
        }))
        .pipe(wrap('Handlebars.template(<%= contents %>)'))
        .pipe(declare({
            namespace: 'nb.templates',
            noRedeclare: true,
        }))
        .pipe(concat('templates.js'))
        .pipe(gulp.dest('./js/src/templates/compiled'));
});

gulp.task('scripts', function() {
    gulp.src(js_files)
        .pipe(concat('nb.build.js'))
        .pipe(gulp.dest('./js'))
        .pipe(rename('nb.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('./js'));
});

gulp.task('styles', function() {
    gulp.src(css_files)
        .pipe(concat('nb.min.css'))
        .pipe(csso())
        .pipe(gulp.dest('./css'));
});

gulp.task('default', ['styles', 'templates', 'lint', 'scripts'], function() {

    gulp.watch(css_files, function(event) {
        gulp.run('styles');
    });

    gulp.watch(hbs_templates, function(event) {
        gulp.run('templates');
    });

    gulp.watch(js_files, function(event) {
        gulp.run('scripts');
    });

    // create module watch
    for (var i = 0; i < modules.length; i++) {
        var module = modules[i];

        console.log('watch', module, '\n_styles:\n', _styles[module], '\n_templates:\n', _templates[module], '\n_scripts:\n', _scripts[module]);
        console.log('---------------------------------');

        (function() {
            var m = module;
            gulp.watch(_styles[m], function(event) {
                gulp.run(m + '_styles');
            });
            gulp.watch(_templates[m], function(event) {
                gulp.run(m + '_templates');
            });
            gulp.watch(_scripts[m], function(event) {
                gulp.run(m + '_scripts');
            });
        })();
    }
});
