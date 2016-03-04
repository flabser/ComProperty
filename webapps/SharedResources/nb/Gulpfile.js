var gulp = require('gulp');
var jshint = require('gulp-jshint');
var concat = require('gulp-concat');
var rename = require('gulp-rename');
var uglify = require('gulp-uglify');
var csso = require('gulp-csso');
var wrap = require('gulp-wrap');
var declare = require('gulp-declare');
var handlebars = require('gulp-handlebars');

var js_files = ['../vendor/handlebars/handlebars.runtime-v4.0.5.js',
    '../vendor/jquery/ui-i18n/*.js'
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

    gulp.watch(hbs_templates, function(event) {
        gulp.run('templates');
    });

    gulp.watch(js_files, function(event) {
        gulp.run('scripts');
    });

    gulp.watch(css_files, function(event) {
        gulp.run('styles');
    });
});
