/**
 * Created by hyc on 2017/4/25.
 */
var createJsPath = function(js) {
    var scripts = document.getElementsByTagName("script");
    var path = "";
    for (var i=0; i<scripts.length; i++) {
        var src = scripts[i].src;
        if (src.indexOf(js) != -1) {
            path = src.split(js)[0];
            break;
        }
    }
    if (path.indexOf("https:") == -1 && path.indexOf("http:") == -1 && path.indexOf("file:") == -1 && path.indexOf("\/") != 0) {
        var href = location.href;
        href = href.split("#")[0];
        href = href.split("?")[0];
        var strArr = href.split("/");
        strArr.length = strArr.length - 1;
        href = strArr.concat("/");
        path = href + "/" + path;
    }
    return path;
};

var bootPath = createJsPath("common-css.js");

document.write('<meta charset="utf-8">');
document.write('<meta name="viewport" content="width=device-width, initial-scale=1.0">');
document.write('<meta name="renderer" content="webkit">');
document.write('<meta http-equiv="Cache-Control" content="no-siteapp" />');
document.write('<title>综合抄表平台</title>');
document.write('<meta name="description" content="">');
document.write('<!--[if lt IE 9]> <meta http-equiv="refresh" content="0;ie.html" /> <![endif]-->');

//css
//document.write('<link rel="shortcut icon" href="favicon.ico">');
document.write('<link href="' + bootPath + 'hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">');
document.write('<link href="' + bootPath + 'hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">');
document.write('<link href="' + bootPath + 'hplus/css/animate.min.css" rel="stylesheet">');
document.write('<link href="' + bootPath + 'hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">');
//document.write('<link href="' + bootPath + 'hplus/css/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">');
document.write('<link href="' + bootPath + 'hplus/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">');
document.write('<link href="' + bootPath + 'hplus/css/plugins/chosen/chosen.css" rel="stylesheet">');
document.write('<link href="' + bootPath + 'hplus/css/plugins/jsTree/style.min.css" rel="stylesheet">');
document.write('<link href="' + bootPath + 'hplus/css/fileinput.min.css" rel="stylesheet">');
document.write('<link href="' + bootPath + 'hplus/css/jquery.fileupload.css" rel="stylesheet">');
document.write('<link href="' + bootPath + 'hplus/css/jquery.fileupload-ui.css" rel="stylesheet">');
document.write('<link href="' + bootPath + 'hplus/css/jquery.fileupload-noscript.css" rel="stylesheet">');

/*
document.write('<link href="' + bootPath + 'hplus/css/plugins/steps/jquery.steps.css" rel="stylesheet">');*/
